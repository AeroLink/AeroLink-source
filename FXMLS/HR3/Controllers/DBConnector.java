/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author my
 */
public class DBConnector {
    public static Connection getConnection() {
        Connection conn = null;
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1;databaseName=AeroLink;username=sa;password=qweqwe";
       conn = DriverManager.getConnection(url);
       
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex){
             Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
            
        
        
       
    }
}
       
