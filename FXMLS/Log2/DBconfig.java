/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Randelle
 */
public class DBconfig {
    
    
    public static Connection con(){
        Connection con = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://139.99.62.2:8420;databaseName = AeroLink; username = sa; password = PYWETgMDWTaS834fo9ijGkENpl43yrFtXpcJ9ovB0$yPS38;";
            con = DriverManager.getConnection(url);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
    
    
    return con;
    }
}
