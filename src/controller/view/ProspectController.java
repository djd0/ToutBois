package controller.view;


import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import controller.MainApp;
import controller.model.Prospect;
import controller.model.ProspectDAO;
import controller.util.DateUtil;
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


public class ProspectController {
	@FXML
	private TableView <Prospect> ProspectTable;
	@FXML
	private TableColumn <Prospect, String> nomColumn;
	@FXML
	private TableColumn <Prospect, String> prenomColumn;
	@FXML
	private TableColumn <Prospect, LocalDate> dateColumn;
	@FXML
	private Button supprimer;
	@FXML
	private Button ajouter;
	@FXML
	private Button modifier;
	@FXML
	private Label dateLabel;
	@FXML
	private Label nbCommandeLabel;
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
	@FXML
	private Label prosId;
	
	
	private MainApp mainApp;

	
	public ProspectController()
	{
		
	}

	@FXML
	private void initialize()
	{
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		
		// mettre tout a zero
        definirDonneesProspect(null);
		
		//Desactive le bouton supprimer si la selection est vide
		supprimer.disableProperty().bind(Bindings.isEmpty(ProspectTable.getSelectionModel().getSelectedItems()));
		
		 // ajout d'un listener qui ecoute les changement et les montrent quand ils changent
        ProspectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> definirDonneesProspect(newValue));
	}
	
	 // clic sur supprimer
    @FXML
    private void handleSupprimer(ActionEvent actionEvent) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
    	 try 
         {    
     		int selectedIndex = ProspectTable.getSelectionModel().getSelectedIndex();
     		
         	int option = JOptionPane.showConfirmDialog(null, "Attention ! Le prospect va etre supprimer.\n Voulez vous continuer ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

         	if(option == JOptionPane.OK_OPTION)
         	{       	
         		 ProspectDAO.deleteProspectWithId(prosId.getText());
                 System.out.println("Prospect deleted! Representant id: " + prosId.getText() + "\n");
                 ProspectTable.getItems().remove(selectedIndex);
         	}
           
         } 
         catch (SQLException e)
         {
             throw e;
         }
    }
    
    //clic sur nouveau
    @FXML
    private void handleNouveauProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
        Prospect tempProspect = new Prospect();
        
        boolean okClicked = mainApp.afficherFenetreAjouterProspect(tempProspect);
        
        if (okClicked) 
        {
        	MainApp.getDonneesProspect().add(tempProspect);
        	MainApp.setDonneesProspect(ProspectDAO.searchProspects()); 
        	populateProspects(MainApp.getDonneesProspect());
        }
    }

    //clic sur modifier
    @FXML
    private void handleModifierProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
        Prospect prospectSelectionne = ProspectTable.getSelectionModel().getSelectedItem();
        
        if (prospectSelectionne != null) 
        {
            boolean okClicked = mainApp.afficherFenetreModifierProspect(prospectSelectionne);
            
            if (okClicked) 
            {
            	definirDonneesProspect(prospectSelectionne);
            	MainApp.setDonneesProspect(ProspectDAO.searchProspects()); 
                populateProspects(MainApp.getDonneesProspect());  
            }
        }
        else
        {
            // si rien n'est selectionne
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getStagePrincipal());
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Aucun prospect selectionné");
            alert.setContentText("Veuillez selectionner un prospect dans la liste.");

            alert.showAndWait();
        }
    }
	
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//ProspectTable.setItems(MainApp.getDonneesProspect());
	}
	
	
	// Rempli les label avec les données ou les vide si LE Prospect est null
    public void definirDonneesProspect(Prospect prospect) 
    {
        if (prospect != null) {
                   	
        	nomLabel.setText(prospect.getNom());
            prenomLabel.setText(prospect.getPrenom());
            rueLabel.setText(prospect.getRue());
            codePostalLabel.setText(Integer.toString(prospect.getCodePostal()));
            villeLabel.setText(prospect.getVille());
            paysLabel.setText(prospect.getPays());
            nbCommandeLabel.setText(Integer.toString(prospect.getNbCommande()));  
            dateLabel.setText(DateUtil.format(prospect.getDate())); 
            prosId.setText(Integer.toString(prospect.getProsId()));
            
            
            
        } 
        else 
        {
                   
        	nomLabel.setText("");
            prenomLabel.setText("");           
            rueLabel.setText("");
            codePostalLabel.setText("");
            villeLabel.setText("");
            paysLabel.setText("");
            nbCommandeLabel.setText("");
            dateLabel.setText("");
         	prosId.setText("");
            
        }

}
    
    // clic sur client
   @FXML
    private void handleClients() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {       	   	
    	
    	mainApp.afficherFormulaireClient();
    	
    	
    }
    
    //clic represesntant
    @FXML
    private void handleRepresentant() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
    	mainApp.afficherFormulaireRepresentant();
    	
    }



	//Populate Employees for TableView
	
	public void populateProspects (ObservableList<Prospect> donneesProspect) throws ClassNotFoundException 
	{
	    //Set items to the employeeTable
		ProspectTable.setItems(donneesProspect);
	}
	
	//Populate Employee
	
	@SuppressWarnings("unused")
	private void populateProspect (Prospect pros) throws ClassNotFoundException 
	{
	    //Declare and ObservableList for table view
	    ObservableList<Prospect> donneesProspect = FXCollections.observableArrayList();
	    //Add employee to the ObservableList
	    donneesProspect.add(pros);
	    //Set items to the employeeTable
	    ProspectTable.setItems(donneesProspect);
	}

	public TableView<Prospect> getProspectTable() {
		return ProspectTable;
	}

	public void setProspectTable(TableView<Prospect> prospectTable) {
		ProspectTable = prospectTable;
	}
		
	
	
}		
