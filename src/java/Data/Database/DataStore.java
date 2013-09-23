/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajmiro
 */
public class DataStore {
    private static Connection connection; //connection to the database    
    private static int pageSize = 8;
    
    /**
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }
    private String connectionString, 
                    userName, 
                    password, 
                    sqlStatement,
                    sqlCountStatement;
    
    private int numberOfPages = -1;
    private int numberOfResults = -1;
    private PreparedStatement countResultsStatement;

    public DataStore(String sqlStatment, String sqlCountStatement) {
        this.connectionString = "jdbc:mysql://localhost:3306/pando_afp";
        this.userName = "root";
        this.password = "0foobar0";
        this.sqlStatement = sqlStatment;
        this.sqlCountStatement = sqlCountStatement;
        
        if (connection == null) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection = DriverManager.getConnection(this.connectionString, userName, password);
            } catch (SQLException ex) {
                Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            countResultsStatement = 
                            connection.prepareStatement(this.sqlCountStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int GetNumberOfResults(){
        if (numberOfResults == -1) {
            try {         
                ResultSet result = countResultsStatement.executeQuery();
                if (result.next()) {
                    numberOfResults = result.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return numberOfResults;
    }
    
    public int GetTotalPages(){
        try {
            if (numberOfPages == -1) {
                ResultSet result = countResultsStatement.executeQuery();
                if (result.next()) {
                    numberOfPages = result.getInt(1) / pageSize;
                }
            }                                
        } catch (SQLException ex) {
            Logger
                 .getLogger(DataStore.class.getName())
                 .log(Level.SEVERE, null, ex);
        }
        return numberOfPages;                
    }
    
    /**
     *This method will return null if no results were found or if 
     * there was an error in the SQL statement
     * @param pageNumber
     * @return
     */
    public ResultSet GetPage(int pageNumber){
        pageNumber = pageNumber - 1; //pages start at 0
        
        String pagedSqlStatement = this.sqlStatement + 
                " LIMIT " + 
                pageNumber + 
                ", " + 
                pageSize;               
        try {
            PreparedStatement statement = 
                connection.prepareStatement(pagedSqlStatement);
            
            ResultSet result = statement.executeQuery();
            
            return result;
        } catch (SQLException ex) {
            Logger
                .getLogger(DataStore.class.getName())
                    .log(Level.SEVERE, null, ex);
        }   
        return null;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }
    
    public String GetColumnNameByID(String id){
        
        String columnName = "";
        try {
            StringBuilder sb = 
                 new StringBuilder("Select question from column_key Where column_key.key = '")
                    .append(id).append("'");
            
            PreparedStatement statement =
                    connection.prepareStatement(sb.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            columnName = result.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnName;
    }
    
    public void Close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
