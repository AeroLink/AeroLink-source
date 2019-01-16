/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JPEG
 */
public class DesperadoDBConn {
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
      
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AeroLink","core","core");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    } 
}
