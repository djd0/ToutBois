package controller.view;



import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.MainApp;
import controller.model.Representant;
import controller.model.RepresentantDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class RepresentantController {


	
	@FXML
	private TableView<Representant> representantTable;
	@FXML
	private TableColumn <Representant, String> nomColumn;
	@FXML
	private TableColumn <Representant, String> prenomColumn;
	@FXML
	private TableColumn <Representant, Integer> numeroRepresentantColumn;
	@FXML
	private Button supprimer;
	@FXML
	private Button ajouter;
	@FXML
	private Button modifier;
	@FXML
	private Label numeroRepresentantLabel;
	@FXML
	private Label salaireLabel;
	@FXML
	private Label tauxCommLabel;
	@FXML
	private Label nomLabel;
	@FXML
	private Label prenomLabel;
	@FXML
	private Label paysLabel;
	@FXML
	private Label villeLabel;
	@FXML
	private Label codePostalLabel;
	@FXML
	private Label rueLabel;
	
	
	private MainApp mainApp;

	
	public RepresentantController()
	{
		
	}
	
	@FXML
	private void initialize()
	{
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		numeroRepresentantColumn.setCellValueFactory(cellData -> cellData.getValue().numeroRepresentantProperty().asObject());
		
		// mettre tout a zero
        definirDonneesRepresentant(null);
		
		//Desactive le bouton supprimer si la selection est vide
		supprimer.disableProperty().bind(Bindings.isEmpty(representantTable.getSelectionModel().getSelectedItems()));
		
		// ajout d'un listener qui ecoute les changement et les montrent quand ils changent
        representantTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> definirDonneesRepresentant(newValue));
	}
	
	  // clic sur supprimer
   /* @FXML
    private void handleSupprimer() 
    {
        
    	int selectedIndex = representantTable.getSelectionModel().getSelectedIndex();
    	int option = JOptionPane.showConfirmDialog(null, "Attention ! Le representant va etre supprimer.\n Voulez vous continuer ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    	if(option == JOptionPane.OK_OPTION)
    	{
    		representantTable.getItems().remove(selectedIndex);
    	}
    }*/
    
    //clic sur nouveau
    @FXML
    private void handleNouveauRepresentant() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
    	Representant tempRepresentant = new Representant();
        
        boolean okClicked = mainApp.afficherFenetreAjouterRep(tempRepresentant);
        
        if (okClicked) 
        {
        	MainApp.getDonneesRepresentant().add(tempRepresentant);       	
        	MainApp.setDonneesRepresentant(RepresentantDAO.searchRepresentant()); 
        	populateRepresentants(MainApp.getDonneesRepresentant());
        	
        	
        	
        }
    }

    //clic sur modifier
    @FXML
    private void handleModifierRepresentant() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
    	Representant representantSelectionne = representantTable.getSelectionModel().getSelectedItem();
        
        if (representantSelectionne != null) 
        {
            boolean okClicked = mainApp.afficherFenetreModifierRepresentant(representantSelectionne);
            
            if (okClicked) 
            {
            	definirDonneesRepresentant(representantSelectionne);
            	MainApp.setDonneesRepresentant(RepresentantDAO.searchRepresentant()); 
                populateRepresentants(MainApp.getDonneesRepresentant());  
            }
        }
        else
        {
            // si rien n'est selectionne
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getStagePrincipal());
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Aucun représentant selectionné");
            alert.setContentText("Veuillez selectionner un représentant dans la liste.");

            alert.showAndWait();
        }
    }
	
    
	// Rempli les label avec les données ou les vide si LE REPRESENTANT est null
    public void definirDonneesRepresentant(Representant representant) 
    {
        if (representant != null) {
            
            nomLabel.setText(representant.getNom());
            prenomLabel.setText(representant.getPrenom());
            rueLabel.setText(representant.getRue());
            codePostalLabel.setText(Integer.toString(representant.getCodePostal()));
            villeLabel.setText(representant.getVille());
            paysLabel.setText(representant.getPays());
            tauxCommLabel.setText(Double.toString(representant.getTauxCom()));
            salaireLabel.setText(Double.toString(representant.getSalaire()));
            numeroRepresentantLabel.setText(Integer.toString(representant.getNumeroRepresentant()));
            
        } 
        else 
        {
            
        	nomLabel.setText("");
            prenomLabel.setText("");           
            rueLabel.setText("");
            codePostalLabel.setText("");
            villeLabel.setText("");
            paysLabel.setText("");
            tauxCommLabel.setText("");
            salaireLabel.setText("");
            numeroRepresentantLabel.setText("");
           
     
            
        }

}
    
    //Populate Employees for TableView
  
    public void populateRepresentants (ObservableList<Representant> donneesRepresentant) throws ClassNotFoundException {
        //Set items to the employeeTable
    	representantTable.setItems(donneesRepresentant);
    }
 
    //Populate Employee
   
    @SuppressWarnings("unused")
	private void populateRepresentant (Representant rep) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Representant> donneesRepresentant = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        donneesRepresentant.add(rep);
        //Set items to the employeeTable
        representantTable.setItems(donneesRepresentant);
    }
    
    
    //Delete a representative with a given Id from DB
    @FXML
    private void deleteRepresentant (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {      
	    	try 
	        {    
	    		int selectedIndex = representantTable.getSelectionModel().getSelectedIndex();
	    		
	        	int option = JOptionPane.showConfirmDialog(null, "Attention ! Le representant va etre supprimer.\n Voulez vous continuer ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	
	        	if(option == JOptionPane.OK_OPTION)
	        	{       	
	        		RepresentantDAO.deleteRepWithId(numeroRepresentantLabel.getText());
	                System.out.println("Representant deleted! Representant id: " + numeroRepresentantLabel.getText() + "\n");
	                representantTable.getItems().remove(selectedIndex);
	        	}
	          
	        } 
	        catch (SQLException e)
	        {
	        	// si representant constrainte clé etrangere
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getStagePrincipal());
	            alert.setTitle("Attention !");
	            alert.setHeaderText("Le représentant est rataché à des clients");
	            alert.setContentText("Pour le supprimer, veuillez controler les clients et changer ou supprimer leurs liens avec le représentant");

	            alert.showAndWait();
	        	throw e;
	        }
        
    }
    
    
    
    // clic sur client
    @FXML
    private void handleClients() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {       	   	
    	
    	mainApp.afficherFormulaireClient();
    	
    }

    @FXML
    private void handleProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
    	mainApp.afficherFormulaireProspect();
    }
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//representantTable.setItems(MainApp.getDonneesRepresentant());
	}
	
	
	
}
