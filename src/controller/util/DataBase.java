package controller.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sun.rowset.CachedRowSetImpl;


public class DataBase {
	
	
	@SuppressWarnings("unused")
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 
    //Connection
    private static Connection conn = null;
 
    //Connection String
    private static final String connStr = "jdbc:mysql://localhost:3306/toutbois";
 
     
    //Connect to DB
    //public static void dbConnect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    private DataBase() throws ClassNotFoundException, SQLException
    {
        //Setting phpMyAdmin JDBC Driver
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("Where is your Mysql JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
 
        System.out.println("Mysql JDBC Driver Registered!");
 
        //Establish the Mysql Connection using Connection String
        try 
        {
            conn = DriverManager.getConnection(connStr,"root","");
        } 
        catch (SQLException e) 
        {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }
    
  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance() throws ClassNotFoundException, SQLException{
     if(conn == null){
       new DataBase();
     }
     return conn;   
   }
 
    //Close Connection
    public static void dbDisconnect() throws SQLException 
    {
        try
        {
            if (conn != null && !conn.isClosed()) 
            {
                conn.close();
            }
        }
        catch (Exception e)
        {
           throw e;
        }
    }
 
    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        
        try 
        {
            //Connect to DB 
            //dbConnect();
        	getInstance();
            System.out.println("Select statement: " + queryStmt + "\n");
 
            //Create statement
            stmt = conn.createStatement();
 
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
 
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            
            crs.populate(resultSet);
            
        } 
        catch (SQLException e) 
        {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } 
        finally 
        {
            if (resultSet != null) 
            {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) 
            {
                //Close Statement
                stmt.close();
            }
            //Close connection
            //dbDisconnect();
        }
        
        //Return CachedRowSet
        return crs;
    }
 
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        //Declare statement as null
        Statement stmt = null;
        
        try 
        {
            //Connect to DB (Establish Oracle Connection)
            //dbConnect();
        	getInstance();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
            
        } 
        catch (SQLException e)
        {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } 
        finally 
        {
            if (stmt != null) 
            {
                //Close statement
                stmt.close();
            }
            //Close connection
           // dbDisconnect();
        }
    }
    
    
    
}
