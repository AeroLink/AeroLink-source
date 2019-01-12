/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author ARIELLECIAS
 */
public class Session implements IModule {

    private static HashMap session;
    
    @Override
    public void init() {
        session  = new HashMap();
    }
    
    /**
     * 
     * Session put method - allows you to add new value inside of Session
     * 
     * Example:
     * 
     * Session s = new Session();
     * 
     * s.put("userID", "1234567890");
     * 
     * @param key  
     * @param value 
     */
    public static void put(Object key, Object value) {
       session.put(key, value);
    }
    
    public static void putIfNotExist(Object key, Object value){
        session.putIfAbsent(key, value);
    }
    
    /**
     * Session pull method - allows you to get a value
     * 
     * @param key
     * @return Object - if exist, 0 - if not
     */
    public static Object pull(Object key) {
        return session.getOrDefault(key, 0);
    }
    
    public static HashMap getSession() {
        return session;   
    }
   
    
    //Database
    public static iDB INSTANCE;
    public static Boolean isConnected = false;
    
    // static vars
    public static String Ip = "localhost";
    public static String Port = "3306";
    public static String Database = "aerolink";
    public static String User = "root";
    public static String Password = "";
    public static String schema = "";
    public static String provider = "";
    
    //API
    public static String HttpURL = "";
    public static String token = "";
    public static String sestoken = "";
    
    public static String[][] links;
    
    public static HashMap ModularPermission;
    
    private static List userPermission = new ArrayList<>();
    
    public static void addPermission(String p){
         userPermission.add(p);
    }
    
    public static Boolean hasPermission(String permission){
        return userPermission.contains(permission);
    }
    
    public static List getPermissions(){
        return userPermission;
    }
    
    public static String table = "";
    public static Boolean where = false;
    public static String whereValues = "";
    
    
    //Threads
    public static ExecutorService SessionThreads = Executors.newCachedThreadPool();
    
    //forms
    public static String CurrentRoute = "";
}
