/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.Connection;
import java.util.HashMap;

/**
 *
 * @author Lei
 */
public interface iDB {
   
    public Boolean start();
    public Connection getConnection();
    public Boolean hasConnection();
    public void close();
    public void config(String server, String port, String schema, String username, String password);
    
    
}
