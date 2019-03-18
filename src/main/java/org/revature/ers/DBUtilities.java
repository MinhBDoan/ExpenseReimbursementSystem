package org.revature.ers;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
     
    public class DBUtilities {



        Connection connection = null;
        Statement statement = null; 
        ResultSet resultSet = null; 
        PreparedStatement ps =null;
        public DBUtilities()  {
            
        	try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {

            }
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://ersdatabase.cvhwuqdvfqwu.us-east-2.rds.amazonaws.com:5432/ersdatabase", "MinhDoan777",
        				"12345678");

            } catch (SQLException ex) {
                System.out.println("The following error has occured: " + ex.getMessage());
            }
        }

        public void DisconnectFromDB() {

            try {
                resultSet.close();
                statement.close();
                ps.close();
                connection.close();
                
            }                                            
            catch (Exception ex) {
                System.out.println("The following error has occured: " + ex.getMessage());
            }  
        }

        public ResultSet ReadRecords(String sql_stmt) {
            try {

                statement = connection.createStatement();
                                        
                resultSet = statement.executeQuery(sql_stmt);

                return resultSet;

            } 
            catch (SQLException ex) {
                System.out.println("The following error has occured: " + ex.getMessage());
            }                                                    

            return resultSet;
        }

        public void ExecuteSQLStatement(String sql_stmt) {
            try {
                statement = connection.createStatement();
                                        
                statement.executeUpdate(sql_stmt);
            } 
            catch (SQLException ex) {
                System.out.println("The following error has occured: " + ex.getMessage());
            }
        }
    }

