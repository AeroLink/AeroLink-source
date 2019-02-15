/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Onodera
 */
public class DBconnection {
    public static Connection con(){
        Connection con = null;
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://139.99.62.2:8420;databaseName = Aerolink;username = sa; password = PYWETgMDWTaS834fo9ijGkENpl43yrFtXpcJ9ovB0$yPS38; ";
        con = DriverManager.getConnection(url);
    
    
    }catch(ClassNotFoundException ex){
        Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(SQLException ex){
        Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return con;
    }
    
    
}
