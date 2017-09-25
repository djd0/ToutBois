package controller.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import controller.util.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RepresentantDAO {

	
	
 
    //*******************************
    //SELECT Representant
    //*******************************
    public static ObservableList<Representant> searchRepresentant () throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM representant";
 
        //Execute SELECT statement
        try 
        {
           
        	//Get ResultSet from dbExecuteQuery method
            ResultSet rsReps = DataBase.dbExecuteQuery(selectStmt);
          
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Representant> repList = getRepresentantList(rsReps);
            
            //Return employee object
            return repList;
            
        } 
        catch (SQLException e) 
        {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from employees operation
    private static ObservableList<Representant> getRepresentantList(ResultSet rs) throws SQLException, ClassNotFoundException 
    {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Representant> repList = FXCollections.observableArrayList();
 
        while (rs.next()) 
        {
        	Representant rep = new Representant();
        	
        	rep.setNumeroRepresentant(rs.getInt("numeroRepresentant"));
        	rep.setNom(rs.getString("nom"));
        	rep.setPrenom(rs.getString("prenom"));
        	rep.setRue(rs.getString("rue"));
        	rep.setVille(rs.getString("ville"));
        	rep.setPays(rs.getString("pays"));
        	rep.setCodePostal(rs.getInt("codePostal"));
        	rep.setSalaire(rs.getDouble("salaire"));
        	rep.setTauxCom(rs.getDouble("tauxCom"));
        	
            //Add employee to the ObservableList
            repList.add(rep);
        }
        //return empList (ObservableList of Employees)
        return repList;
    }
 
    //*************************************
    //UPDATE a representant
    //*************************************
    public static void updateRep (String repID, String repNom, String repPrenom, String repRue, String repVille, String repPays, int repCodePostal, double repTauxCom, double repSalaire) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //Declare an UPDATE statement
        String updateStmt =
                        "   UPDATE representant\n" +
                        "      SET nom = '" + repNom + "'\n" +
                        "      ,prenom = '" + repPrenom + "'\n" +
                        "      ,rue = '" + repRue + "'\n" +
                        "      ,ville = '" + repVille + "'\n" +
                        "      ,pays = '" + repPays + "'\n" +
                        "      ,codePostal = '" + repCodePostal + "'\n" +
                        "      ,tauxCom = '" + repTauxCom + "'\n" +
                        "      ,salaire = '" + repSalaire + "'\n" +
                        "    WHERE numeroRepresentant = " + repID + ";\n"; 
                                               
 
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
    public static void deleteRepWithId (String repId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare a DELETE statement
        String updateStmt =
               
                        "   DELETE FROM representant\n" +
                        "         WHERE numeroRepresentant ="+ repId +";\n";
                         
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
    public static void insertRep (String repNom, String repPrenom, String repRue, String repVille, String repPays, int repCodePostal, double repTauxCom, double repSalaire ) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
   
    	//Declare an INSERT statement
        String updateStmt =
                		
        				"INSERT INTO representant (nom, prenom, rue, ville, pays, codePostal, tauxCom, salaire) VALUES ('"+repNom+"', '"+repPrenom+"', '"+repRue+"', '"+repVille+"', '"+repPays+"', '"+repCodePostal+"', '"+repTauxCom+"', '"+repSalaire+"')";

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
