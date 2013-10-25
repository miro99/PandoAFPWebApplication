/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import java.sql.*;
import java.util.logging.*;
import javax.naming.*;
import javax.sql.DataSource;

/**
 *
 * @author ajmiro
 */
public class DataStore {
    private static Connection databaseConnection = null; //connection to the database    
    private static int pageSize = 8;        
    private DataSource dataSource;       
    private String  sqlStatement,
                    sqlCountStatement;    
    private int numberOfPages = -1;
    private int numberOfResults = -1;
    private PreparedStatement countResultsStatement;

    public DataStore(String sqlStatment, String sqlCountStatement) {
                
        this.sqlStatement = sqlStatment;
        this.sqlCountStatement = sqlCountStatement;
        Connection connection = getDatabaseConnection();
        prepareCountResultsStatement(connection);
        initDataSource();
    }
    
    public int GetNumberOfResults(){
        if (numberOfResults == -1) {
            try {         
                ResultSet result = countResultsStatement.executeQuery();
                if (result.next()) {
                    numberOfResults = result.getInt(1);
                }
            } catch (SQLException ex) {
                Logger
                .getLogger(DataStore.class.getName())
                        .log(Level.SEVERE, null, ex);
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
        
        Connection connection = getDatabaseConnection();
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
        String sqlStatement 
                = "Select question from column_key Where column_key.key = '";
        String columnName = "";
        Connection connection = getDatabaseConnection();
        try {
            StringBuilder sb = 
                 new StringBuilder(sqlStatement)
                    .append(id).append("'");
            
            PreparedStatement statement =
                    connection.prepareStatement(sb.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            columnName = result.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataStore.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return columnName;
    }
       
    private Connection getDatabaseConnection() {        
        if (databaseConnection == null) {
            try {                                
                databaseConnection = dataSource.getConnection();                
            } catch (SQLException ex) {
                Logger.getLogger(DataStore.class.getName())
                        .log(Level.SEVERE, null, ex);
            } 
        }        
        return databaseConnection;
    }
    
    public void Close(){        
        try {
            databaseConnection.close();
            databaseConnection = null;
        } catch (SQLException ex) {
            Logger.getLogger(DataStore.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }           

    private void prepareCountResultsStatement(Connection connection) {
        try {            
            countResultsStatement = 
                            connection.prepareStatement(this.sqlCountStatement);
        } catch (SQLException ex) {
            Logger
            .getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initDataSource(){
        try {
            Context initailContext = new InitialContext();
            Context ctx = (Context) initailContext.lookup("java:comp/env");
            dataSource = (DataSource) ctx.lookup("jdbc/pando_afp");
        } catch (NamingException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
