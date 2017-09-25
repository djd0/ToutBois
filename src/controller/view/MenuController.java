package controller.view;



import java.sql.SQLException;

import controller.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

	
	@FXML
	private Button Clients;
	@FXML
	private Button Représentant;
	@FXML
	private Button Prospects;
	
	private MainApp mainApp;
	
    @SuppressWarnings("unused")
	private Stage clientStage;

    
    
	
	//Constructeur
	public MenuController() {
		
	}
	
    @FXML
    private void initialize() {
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

    
    @FXML
    private void handleProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
    {
    	mainApp.afficherFormulaireProspect();    	
    }
 

    
	public void setClientStage(Stage clientStage) {
		this.clientStage = clientStage;
	}

	public void setMainApp(MainApp mainApp) 
	{
        this.mainApp = mainApp;
	}

    
    

}
