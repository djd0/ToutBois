package controller.view;

import java.sql.SQLException;

import controller.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {

	
	
	@SuppressWarnings("unused")
	private MainApp mainApp;
	
	
	
	public void setMainApp(MainApp mainApp) 
	{
        this.mainApp = mainApp;
    }


   

    // ouvre une fenetre a propos
    @FXML
    private void handleAPropos()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText("Societe ToutBois\n\nCentre de gestion des prospects, clients et représentants");
        alert.setContentText("Auteurs : - D'HAESE Quentin\n               - IMOHAMMADIAN Bilal");

        alert.showAndWait();
    }

    // ferme l'application
    @FXML
    private void handleExit() throws SQLException 
    {   	
    	Platform.exit();
    }
}


