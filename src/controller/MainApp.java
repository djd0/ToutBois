package controller;

import java.io.IOException;
import java.sql.SQLException;

import controller.model.Client;
import controller.model.ClientDAO;
import controller.model.Prospect;
import controller.model.ProspectDAO;
import controller.model.Representant;
import controller.model.RepresentantDAO;
import controller.util.DataBase;
import controller.view.ClientController;
import controller.view.MenuController;
import controller.view.ModifierPersonneController;
import controller.view.ProspectController;
import controller.view.RepresentantController;
import controller.view.RootLayoutController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MainApp extends Application {

	
	private Stage stagePrincipal;
	private BorderPane rootLayout;
	
	
	
	
	//Listes observable
	public static ObservableList<Client> donneesClient = FXCollections.observableArrayList();
	public static ObservableList<Representant> donneesRepresentant = FXCollections.observableArrayList();
	public static ObservableList<Prospect> donneesProspect = FXCollections.observableArrayList();
	
	
	//Constructeur
	public MainApp()
	{
		 //5 Representant
		/*Representant r1 = new Representant("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, 0.2, 1300);
		donneesRepresentant.add(r1);
        donneesRepresentant.add(new Representant("BIFET", "Henry", "36 rue du marechal ferrand", "lille", "FRANCE", 59000, 0.2, 1300));
        donneesRepresentant.add(new Representant("COUS", "Paul", "20 rue paul corteville", "roubaix", "FRANCE", 59100, 0.2, 1300));
        donneesRepresentant.add(new Representant("DALISET", "Gerard", "40/5 rue du terminal", "lille", "FRANCE", 59000, 0.2, 1300));
        donneesRepresentant.add(new Representant("ERMOT", "Michelle", "13 rue jean jura", "croix", "FRANCE", 59170, 0.2, 1300));
		
		// ajout de 5 clients
		donneesClient.add(new Client("ARIS", "Mathieu", "36 rue du fel", "Lille", "FRANCE", 59000, "03.20.12.13.14", "mathieu.aris@gmail.com", "VILASTIS", "71203480000201", 10, 8));
		donneesClient.add(new Client("BEST", "Loic", "12 impasse du plomeut", "Wasquehal", "FRANCE", 59290, "03.20.26.27.28", "loic.best@gmail.com", "ENEDIS", "71203480000201", 20, 2));
		donneesClient.add(new Client("CARRY", "Bernard", "2 bd de l'egalite", "Roubaix", "FRANCE", 59100, "03.20.30.31.32", "b.carry@gmail.com", "LDLC", "7120348000020", 30, 6));
		donneesClient.add(new Client("DENDE", "Lucy", "22 rue pierre mol", "Lille", "FRANCE", 59000, "03.20.45.46.47", "l.dende@gmail.com", "COFIDIS", "71203480000201", 40, 4));
		donneesClient.add(new Client("ERMIS", "David", "10 rue de brette", "Croix", "FRANCE", 59170, "03.20.58.59.60", "d.ermiss@gmail.com", "IBM", "71203480000201", 50, 12));
		
        
        // 5 Prospect
        donneesProspect.add(new Prospect("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, LocalDate.of(2014, 6, 30)));
        donneesProspect.add(new Prospect("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, LocalDate.of(2014, 6, 30)));
        donneesProspect.add(new Prospect("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, LocalDate.of(2014, 6, 30)));
        donneesProspect.add(new Prospect("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, LocalDate.of(2014, 6, 30)));
        donneesProspect.add(new Prospect("AISAR", "Lily", "9 rue girond", "wasquehal", "FRANCE", 59290, LocalDate.of(2014, 6, 30)));
*/
        
        
       
    }

	
	
	//retourner les donnees en tant que liste observable
	public static ObservableList<Client> getDonneesClient() {
        return donneesClient;
    }
	
	public static ObservableList<Representant> getDonneesRepresentant() {
        return donneesRepresentant;
    }

	public static ObservableList<Prospect> getDonneesProspect() {
        return donneesProspect;
    }
	
	

	
	
	
	public static void setDonneesClient(ObservableList<Client> donneesClient) {
		MainApp.donneesClient = donneesClient;
	}



	public static void setDonneesRepresentant(ObservableList<Representant> donneesRepresentant) {
		MainApp.donneesRepresentant = donneesRepresentant;
	}



	public static void setDonneesProspect(ObservableList<Prospect> donneesProspect) {
		MainApp.donneesProspect = donneesProspect;
	}



	@Override
	public void start(Stage stagePrincipal) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		this.stagePrincipal = stagePrincipal;
		
		// TITRE
        this.stagePrincipal.setTitle("Centre de gestion TOUTBOIS");
        
       
        
        
        initRootLayout();
        afficherFormulaireClient();
        afficherFormulaireProspect();
        afficherFormulaireRepresentant();
        
        afficherMenu();
        
        
	}
	
	// Chargement fichiers fxml
	
	public void initRootLayout() 
	{
        try 
        {
            // Creation d'un chargeur fxml
            FXMLLoader loader = new FXMLLoader();
            
            //acces a l'URL par le chargeur
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
           
            //Chargement
            rootLayout = (BorderPane) loader.load();

            // Creation d'une scene qui contient le root
            Scene scene = new Scene(rootLayout);
            
            // afectation de la scene au menuStage
            stagePrincipal.setScene(scene);
            
            // Donne la main au controlleur
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);


            // Afficher le menuStage
            stagePrincipal.show();
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }


    }
	
	
	public void afficherMenu()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/Menu.fxml"));
			
			BorderPane menu = (BorderPane) loader.load();
			
			rootLayout.setCenter(menu);
			
			MenuController controller = loader.getController();
	        controller.setMainApp(this);

		}
		
		catch ( IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void afficherFormulaireClient() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
	    try 
	    {
	    	FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/FormulaireClient.fxml"));
			
			AnchorPane ficheClient = (AnchorPane) loader.load();
			
			rootLayout.setCenter(ficheClient);
			
			ClientController controller = loader.getController();
	        controller.setMainApp(this);
	        
	        donneesClient = ClientDAO.searchClient();
            controller.populateClients(donneesClient);
	        
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public void afficherFormulaireRepresentant() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/FormulaireRepresentant.fxml"));
			
			AnchorPane ficheRepresentant = (AnchorPane) loader.load();
			
			rootLayout.setCenter(ficheRepresentant);
			
			RepresentantController controller = loader.getController();
			controller.setMainApp(this);
			
			donneesRepresentant = RepresentantDAO.searchRepresentant();
           
            controller.populateRepresentants(donneesRepresentant);
		}
		catch ( IOException e)
		{
			 e.printStackTrace();
		}
	}
	
	
	public void afficherFormulaireProspect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
	
			loader.setLocation(MainApp.class.getResource("view/FormulaireProspect.fxml"));

			AnchorPane ficheProspect = (AnchorPane) loader.load();
			
			rootLayout.setCenter(ficheProspect);

			ProspectController controller = loader.getController();
			controller.setMainApp(this);
	
			donneesProspect = ProspectDAO.searchProspects();
            controller.populateProspects(donneesProspect);
			
	
			
			
            
		}
		catch ( IOException e)
		{
			 e.printStackTrace();
		}
	}
	
	// Ouvre une fenetre pour modifier un client
	// Si le user clic sur ok, les changement sont enregistre dans l'objet client et true est retourné
	public boolean afficherFenetreModifierClient(Client client)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/ModClient.fxml"));
			
			AnchorPane fenetreModifierClient = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Modifier client");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreModifierClient);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setClient(client);
			controller.setMainApp(this);
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	
	public boolean afficherFenetreModifierClient(Prospect prospect)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/ModClient.fxml"));
			
			AnchorPane fenetreModifierClient = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Modifier client");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreModifierClient);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setProspect(prospect);
			controller.setMainApp(this);
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	public boolean afficherFenetreModifierProspect(Prospect prospect)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/ModProspect.fxml"));
			
			AnchorPane fenetreModifierProspect = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Modifier prospect");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreModifierProspect);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setProspect(prospect);
			
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	public boolean afficherFenetreModifierRepresentant(Representant representant)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/ModRepresentant.fxml"));
			
			AnchorPane fenetreModifierRepresentant = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Modifier representant");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreModifierRepresentant);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setRepresentant(representant);
			
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	public boolean afficherFenetreAjouterRep(Representant representant)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/AjoutRepresentant.fxml"));
			
			AnchorPane fenetreAjoutRep = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Nouveau representant");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreAjoutRep);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setRepresentant(representant);
			
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	public boolean afficherFenetreAjouterProspect(Prospect prospect)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/AjoutProspect.fxml"));
			
			AnchorPane fenetreAjoutProspect = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Nouveau prospect");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreAjoutProspect);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setProspect(prospect);

			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	
	public boolean afficherFenetreAjouterClient(Client client)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(MainApp.class.getResource("view/AjoutClient.fxml"));
			
			AnchorPane fenetreAjoutClient = (AnchorPane) loader.load();
			
			// creation du stage de la fenetre
	        Stage fenetreStage = new Stage();
	        
	        fenetreStage.setTitle("Nouveau client");
	        fenetreStage.initModality(Modality.WINDOW_MODAL);
	        fenetreStage.initOwner(stagePrincipal);
	        
	        Scene scene = new Scene(fenetreAjoutClient);
	        fenetreStage.setScene(scene);

			
			ModifierPersonneController controller = loader.getController();
			controller.setFenetreStage(fenetreStage);
			controller.setClient(client);
			controller.setMainApp(this);
	
			// Montre la fenetre et attend que le user la ferme
	        fenetreStage.showAndWait();

	        return controller.isOkClicked();

		}
		catch ( IOException e)
		{
			 e.printStackTrace();
			 return false;
		}
	}
	

	public Stage getStagePrincipal() {
		return stagePrincipal;
	}
	
	
	public void stop() throws SQLException
    {
    	DataBase.dbDisconnect();
		System.out.println("Deconnexion de la base");    	
    	Platform.exit();
    }

	

	public static void main(String[] args) 
	{
		launch(args);
	}
}
	

