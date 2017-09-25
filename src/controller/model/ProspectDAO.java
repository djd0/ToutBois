package controller.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;


import controller.util.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ProspectDAO {

	

 
    //*******************************
    //SELECT Employees
    //*******************************
    public static ObservableList<Prospect> searchProspects () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM prospect";
 
        //Execute SELECT statement
        try 
        {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPros = DataBase.dbExecuteQuery(selectStmt);
          
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Prospect> prosList = getProspectList(rsPros);
            
            
            return prosList;
        } 
        catch (SQLException e) 
        {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from employees operation
    private static ObservableList<Prospect> getProspectList(ResultSet rs) throws SQLException, ClassNotFoundException 
    {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Prospect> prosList = FXCollections.observableArrayList();
 
        while (rs.next()) 
        {
        	Prospect pros = new Prospect();
        	
        	pros.setProsId(rs.getInt("prosId"));       	
        	pros.setNom(rs.getString("nom"));
        	pros.setPrenom(rs.getString("prenom"));       	
        	pros.setRue(rs.getString("rue"));
        	pros.setVille(rs.getString("ville"));
        	pros.setPays(rs.getString("pays"));
        	pros.setCodePostal(rs.getInt("codePostal"));
        	pros.setNbCommande(rs.getInt("nbCommande"));
        	pros.setDate(rs.getDate("date").toLocalDate());
        
            //Add employee to the ObservableList
           prosList.add(pros);
        }
        //return empList (ObservableList of Employees)
        return prosList;
    }
 
    //*************************************
    //UPDATE an employee's email address
    //*************************************
    public static void updateProspect (String prosId, String prosNom, String prenomPros, String ruePros, String villePros, String paysPros, int codePostalPros, int nbCommandePros, String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare an UPDATE statement
        String updateStmt =
                        "   UPDATE prospect\n" +
        
                        "      SET nom = '" + prosNom + "'\n" +
                        "      ,prenom = '" + prenomPros + "'\n" +
                        "      ,rue = '" + ruePros + "'\n" +
                        "      ,ville = '" + villePros + "'\n" +
                        "      ,pays = '" + paysPros + "'\n" +
                        "      ,codePostal = '" + codePostalPros + "'\n" +
                        "      ,nbCommande = '" + nbCommandePros + "'\n" +
                        "      ,date = '" + date + "'\n" +                   
                        
                        "    WHERE prosId = " + prosId + ";\n"; 
                                               
 
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
    //DELETE an employee
    //*************************************
    public static void deleteProspectWithId (String prosId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare a DELETE statement
        String updateStmt =
               
                        "   DELETE FROM prospect\n" +
                        "         WHERE prosId ="+ prosId +";\n";
                         
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
    //INSERT an employee
    //*************************************
    public static void insertProspect (String prosNom, String prenomPros, String ruePros, String villePros, String paysPros, int codePostalPros, int nbCommandePros, String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException
    {
        
    	//Declare an INSERT statement
        String updateStmt =
                
                        "INSERT INTO prospect\n" +
                        "(nom, prenom, rue, ville, pays, codePostal, nbCommande, date)\n" +
                        "VALUES\n" +
                        "('"+prosNom+"', '"+prenomPros+"', '"+ruePros+"', '"+villePros+"', '"+paysPros+"', '"+codePostalPros+"', '"+nbCommandePros+"', '"+date+"');\n"; 
                        
 
        //Execute INSERT operation
        try 
        {
            DataBase.dbExecuteUpdate(updateStmt);
        } 
        catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + updateStmt + "\n" + e);
            throw e;
        }
    }
}
