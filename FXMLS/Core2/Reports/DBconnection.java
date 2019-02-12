/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Reports;

import FXMLS.Core2.Reports.*;
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
        String url = "jdbc:sqlserver://localhost:1433;databaseName = Aerolink;username = core; password = core";
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
