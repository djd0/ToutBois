package controller.view;



import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.MainApp;
import controller.model.Client;
import controller.model.ClientDAO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ClientController {

	
	//Attribues pour javafx
	@FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> nomColumn;
    @FXML
    private TableColumn<Client, String> prenomColumn;
    @FXML
    private TableColumn<Client, Integer> numeroClientColumn;
    @FXML
    private TableColumn<Client, Integer> numeroRepresentantColumn;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label societeLabel;
    @FXML
    private Label siretLabel;
    @FXML
    private Label rueLabel;
    @FXML
    private Label codePostalLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label paysLabel;
    @FXML
    private Label nbCommandeLabel;
    @FXML
    private Label numeroClientLabel;
    @FXML
    private Label numeroRepresentantLabel;
    @FXML
    private Button supprimer;

    
    @SuppressWarnings("unused")
	private Client client;

    private Stage clientStage;
   
    // reference a mainApp
    private MainApp mainApp;

    //Constructeur ( appeler avant la fonction initialiser )
    public ClientController() {
    }

    // Rempli les label avec les données ou les vide si client est null
    public void definirDonneesClient(Client client) 
    {
    	
    	
        if (client != null) {
            
            nomLabel.setText(client.getNom());
            prenomLabel.setText(client.getPrenom());
            emailLabel.setText(client.getEmail());
            telephoneLabel.setText(client.getTelephone());
            societeLabel.setText(client.getEnseigne());
            siretLabel.setText(client.getSiret());
            rueLabel.setText(client.getRue());
            codePostalLabel.setText(Integer.toString(client.getCodePostal()));
            villeLabel.setText(client.getVille());
            paysLabel.setText(client.getPays());
            nbCommandeLabel.setText(Integer.toString(client.getNbCommande()));  
            numeroClientLabel.setText(Integer.toString(client.getNumeroClient()));                    
            numeroRepresentantLabel.setText(Integer.toString(client.getNumeroRepresentant()));
        } 
        else 
        {
            
        	nomLabel.setText("");
            prenomLabel.setText("");
            emailLabel.setText("");
            telephoneLabel.setText("");
            societeLabel.setText("");
            siretLabel.setText("");
            rueLabel.setText("");
            codePostalLabel.setText("");
            villeLabel.setText("");
            paysLabel.setText("");
            nbCommandeLabel.setText("");
            numeroClientLabel.setText("");
            numeroRepresentantLabel.setText("");
            
        }
    }

    
    // Initialiser la class controller. methode appellée automatiquement quand un fxml se charge
    @FXML
    private void initialize() 
    {
        // Initialise la table Client avec 4 colonnes
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        numeroClientColumn.setCellValueFactory(cellData -> cellData.getValue().numeroClientProperty().asObject());
        //associer le numero au lieu de le mettre dans
        numeroRepresentantColumn.setCellValueFactory(cellData -> cellData.getValue().numeroRepresentantProperty().asObject());
     
        // mettre tout a zero
        definirDonneesClient(null);

        // ajout d'un listener qui ecoute les changement et les montrent quand ils changent
        clientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> definirDonneesClient(newValue));
    
        //Desactive le bouton supprimer si la selection est vide
        supprimer.disableProperty().bind(Bindings.isEmpty(clientTable.getSelectionModel().getSelectedItems()));
    
    }
    
    
    // clic sur supprimer
    @FXML
    private void handleSupprimer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
    	try 
        { 
	    	int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
	        
	        int option = JOptionPane.showConfirmDialog(null, "Attention ! Le client va etre supprimer.\n Voulez vous continuer ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	
	    	if(option == JOptionPane.OK_OPTION)
	    	{
	    		ClientDAO.deleteClientWithId(numeroClientLabel.getText());
	            System.out.println("Client deleted! Client id: " + numeroClientLabel.getText() + "\n");
	            clientTable.getItems().remove(selectedIndex);
	    	}
        }
    	catch (SQLException e)
        {
            throw e;
        }
    }
    
    //clic sur nouveau
    @FXML
    private void handleNouveauClient() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
        Client tempClient = new Client();
        
        boolean okClicked = mainApp.afficherFenetreAjouterClient(tempClient);
        
        if (okClicked) 
        {
        	MainApp.getDonneesClient().add(tempClient);
        	MainApp.setDonneesClient(ClientDAO.searchClient()); 
        	populateClients(MainApp.getDonneesClient());
        }
    }

    //clic sur modifier
    @FXML
    private void handleModifierClient() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
        Client clientSelectionne = clientTable.getSelectionModel().getSelectedItem();
        
        if (clientSelectionne != null) 
        {
            boolean okClicked = mainApp.afficherFenetreModifierClient(clientSelectionne);
            
            if (okClicked) 
            {
            	definirDonneesClient(clientSelectionne);
            	MainApp.setDonneesClient(ClientDAO.searchClient()); 
                populateClients(MainApp.getDonneesClient());  
            }
        }
        else
        {
            // si rien n'est selectionne
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getStagePrincipal());
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Aucun client selectionné");
            alert.setContentText("Veuillez selectionner un client dans la liste.");

            alert.showAndWait();
        }
    }

    
    //clic represesntant
    @FXML
    private void handleRepresentant() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
    	mainApp.afficherFormulaireRepresentant();
    }

    
    @FXML
    private void handleProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
    	mainApp.afficherFormulaireProspect();
    }
  
    
    //Populate Employees for TableView
    
    public void populateClients (ObservableList<Client> donneesClient) throws ClassNotFoundException {
        //Set items to the employeeTable
    	clientTable.setItems(donneesClient);
    }
 
    //Populate Employee
    
    @SuppressWarnings("unused")
	private void populateClient (Client client) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Client> donneesClient = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        donneesClient.add(client);
        //Set items to the employeeTable
        clientTable.setItems(donneesClient);
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // ajout a la table d'une liste de donnees observable
       // clientTable.setItems(MainApp.getDonneesClient());
    }
    
    public void setClientStage(Stage clientStage) {
        this.clientStage = clientStage;
    }

	public Stage getClientStage() {
		return clientStage;
	}
	


	
}
