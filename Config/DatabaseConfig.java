/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;

/**
 *
 * @author Eden Ramoneda
 */
public class DatabaseConfig {

    public static String PROVIDER = "mssql";
    public static String schema = "aerolink"; //will be change if you were using mssql
    public static String IP = "127.0.0.1";
    public static String PORT = "1433";
    public static String DATABASE = "AeroLink";
    public static String USER = "sa";
    public static String PASSWORD = "root";

 
}
