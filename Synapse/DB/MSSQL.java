package Synapse.DB;

import Synapse.Session;
import Synapse.iDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lei
 */
public class MSSQL extends Session implements iDB{

    private Connection CONNECTION;
    private String Host = "jdbc:sqlserver://";
    
    @Override
    public Boolean start() {        
        
        try {
            
            Connection connection = (Connection) DriverManager.getConnection(this.Host + this.Ip + ":" + this.Port + ";databaseName=" + this.Database + ";user=" + this.User + ";password=" + this.Password + ";");
            this.CONNECTION = connection;
            return true;
            
        } catch (SQLException ex) {
  
            System.out.println(ex.getMessage());
            return false;
        
        }
    }

    @Override
    public Connection getConnection() {
        return this.CONNECTION;
    }

    @Override
    public Boolean hasConnection() {
        try {
            return this.CONNECTION != null && !this.CONNECTION.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public void close() {
        try {
            this.CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void config(String server, String port, String schema, String username, String password) {
        this.Ip = server;
        this.Port = port;
        this.Database = schema;
        this.User = username;
        this.Password = password;
    }
    
}
