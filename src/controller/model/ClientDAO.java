package controller.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import controller.util.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDAO {

		
		
	 
	    //*******************************
	    //SELECT Employees
	    //*******************************
	    public static ObservableList<Client> searchClient () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	    {
	        //Declare a SELECT statement
	        String selectStmt = "SELECT * FROM client";
	 
	        //Execute SELECT statement
	        try 
	        {
	           
	        	//Get ResultSet from dbExecuteQuery method
	            ResultSet rsReps = DataBase.dbExecuteQuery(selectStmt);
	          
	            //Send ResultSet to the getEmployeeList method and get employee object
	            ObservableList<Client> clientList = getClientList(rsReps);
	            
	            //Return employee object
	            return clientList;
	            
	        } 
	        catch (SQLException e) 
	        {
	            System.out.println("SQL select operation has been failed: " + e);
	            //Return exception
	            throw e;
	        }
	    }
	 
	    //Select * from employees operation
	    private static ObservableList<Client> getClientList(ResultSet rs) throws SQLException, ClassNotFoundException 
	    {
	        //Declare a observable List which comprises of Employee objects
	        ObservableList<Client> clientList = FXCollections.observableArrayList();
	 
	        while (rs.next()) 
	        {
	        	Client client = new Client();
	        	
	        	client.setNumeroClient(rs.getInt("numeroClient"));
	        	client.setNom(rs.getString("nom"));
	        	client.setPrenom(rs.getString("prenom"));
	        	client.setRue(rs.getString("rue"));
	        	client.setVille(rs.getString("ville"));
	        	client.setPays(rs.getString("pays"));
	        	client.setCodePostal(rs.getInt("codePostal"));
	        	client.setTelephone(rs.getString("telephone"));
	        	client.setEmail(rs.getString("email"));
	        	client.setEnseigne(rs.getString("enseigne"));
	        	client.setSiret(rs.getString("siret"));
	        	client.setNbCommande(rs.getInt("nbCommande"));
	        	client.setNumeroRepresentant(rs.getInt("numeroRepresentant"));
	        	
	            //Add employee to the ObservableList
	        	clientList.add(client);
	        }
	        //return empList (ObservableList of Employees)
	        return clientList;
	    }
	 
	    //*************************************
	    //UPDATE a representant
	    //*************************************
	    public static void updateClient (String numeroClient, String nomClient, String prenomClient, String rueClient, String villeClient, String paysClient, int codePostalClient, String telephoneClient, String emailClient, String enseigneClient, String siretClient, int nbCommandeClient, int numeroRepClient ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	    {
	        //Declare an UPDATE statement
	        String updateStmt =
	        					"      UPDATE client\n" +
	        
	        					"      SET nom = '" + nomClient + "'\n" +
		                        "      ,prenom = '" + prenomClient + "'\n" +
		                        "      ,rue = '" + rueClient + "'\n" +
		                        "      ,ville = '" + villeClient + "'\n" +
		                        "      ,pays = '" + paysClient + "'\n" +
		                        "      ,codePostal = '" + codePostalClient + "'\n" +
		                        "      ,telephone = '" + telephoneClient + "'\n" +
		                        "      ,email = '" + emailClient + "'\n" +
		                        "      ,enseigne = '" + enseigneClient + "'\n" +
		                        "      ,siret = '" + siretClient + "'\n" +
		                        "      ,nbCommande = '" + nbCommandeClient + "'\n" +
		                        "      ,numeroRepresentant = '" + numeroRepClient + "'\n" +
		                        
		                        "      WHERE numeroClient = " + numeroClient + ";\n"; 
	                                               
	 
	        //Execute UPDATE operation
	        try 
	        {
	            DataBase.dbExecuteUpdate(updateStmt);
	        } 
	        catch (SQLException e) 
	        {
	            System.out.print("Error occurred while UPDATE Operation: " + updateStmt + "\n" + e);
	            throw e;
	        }
	    }
	 
	    //*************************************
	    //DELETE a rep
	    //*************************************
	    public static void deleteClientWithId (String numeroClient) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	    {
	        //Declare a DELETE statement
	        String updateStmt =
	               
	                        "   DELETE FROM client\n" +
	                        "         WHERE numeroClient ="+ numeroClient +";\n";
	                         
	        //Execute UPDATE operation
	        try 
	        {
	            DataBase.dbExecuteUpdate(updateStmt);
	            
	        } 
	        catch (SQLException e) 
	        {
	            System.out.print("Error occurred while DELETE Operation: " + updateStmt + "\n" + e);
	            throw e;
	        }
	    }
	 
	    //*************************************
	    //INSERT a rep
	    //*************************************
	    public static void insertClient (String nomClient, String prenomClient, String rueClient, String villeClient, String paysClient, int codePostalClient, String telephoneClient, String emailClient, String enseigneClient, String siretClient, int nbCommandeClient, int numeroRepresentant ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
	   
	    	//Declare an INSERT statement
	        String insertStmt =
	                
	        		"INSERT INTO client (nom, prenom, rue, ville, pays, codePostal, telephone, email, enseigne, siret, nbCommande, numeroRepresentant) VALUES ('"+nomClient+"', '"+prenomClient+"', '"+rueClient+"', '"+villeClient+"', '"+paysClient+"', '"+codePostalClient+"', '"+telephoneClient+"', '"+emailClient+"', '"+enseigneClient+"', '"+siretClient+"', '"+nbCommandeClient+"', '"+numeroRepresentant+"')";
	                        
	 
	        //Execute INSERT operation
	        try 
	        {
	            DataBase.dbExecuteUpdate(insertStmt);
	        } 
	        catch (SQLException e)
	        {
	            System.out.print("Error occurred while INSERT Operation: " + insertStmt + "\n" + e);
	            throw e;
	        }
	    }
	    
	    public static void insertClientWithProspect(String nomClient, String prenomClient, String rueClient, String villeClient, String paysClient, int codePostalClient, int nbCommandeClient) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	    {
	    	String insertStmt = 
	    						"INSERT INTO client (nom, prenom, rue, ville, pays, codePostal, nbCommande) VALUES ('"+nomClient+"', '"+prenomClient+"', '"+rueClient+"', '"+villeClient+"', '"+paysClient+"', '"+codePostalClient+"', '"+nbCommandeClient+"')";
	    	
	    	try
	    	{
	    		DataBase.dbExecuteUpdate(insertStmt);
	    	}
	    	catch (SQLException e)
	    	{
	    		System.out.print("Error occurred while INSERT Operation: " + insertStmt + "\n" + e);
	    		throw e;
	    	}
	    }
	}



