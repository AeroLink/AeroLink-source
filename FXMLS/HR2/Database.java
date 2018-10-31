/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author EdenRamoneda
 */
public class Database {
    
     private static String Host = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Aerolink";
    private static String User = "sa";
    private static String Password = "adminroot";

    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = null;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(Host, User, Password);
            ps = connection.prepareStatement(sql);
            System.out.println("Connected");
            return ps;

        } catch (ClassNotFoundException | SQLException ex)
            {
                System.out.println(ex.getMessage());
                return null;
            }
        }
}
