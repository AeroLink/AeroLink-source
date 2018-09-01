/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.Connection;

/**
 *
 * @author Lei
 */
public class Database {
    
    
    private iDB INSTANCE_DATABASE;
    private Database(){}
    
    private static class dbOwnInstance{
        private static final Database Own = new Database();
    }
    
    
    public static Database getInstance(){
        return dbOwnInstance.Own;
    }
 
    public Database DB_INIT(iDB db){
        
        this.INSTANCE_DATABASE = db;
        Session.INSTANCE = this.getINSTANCE_DATABASE();
        return getInstance();
    }
        
    
    public iDB getINSTANCE_DATABASE() {
        return this.INSTANCE_DATABASE;
    }
    
    public Boolean startConnection() {
        return this.INSTANCE_DATABASE.start();
    }
    
    public Connection getConnection() {
        return this.INSTANCE_DATABASE.getConnection();
    }
    
    public void closeConnection(){
        this.INSTANCE_DATABASE.close();
    }
    
    public void config(String server, String port, String schema, String username, String password) {
        this.INSTANCE_DATABASE.config(server, port, schema, username, password);
    }
    
    public Boolean hasConnection(){
        return this.INSTANCE_DATABASE.hasConnection();
    }
}
