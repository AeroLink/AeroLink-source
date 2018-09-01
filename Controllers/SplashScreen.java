/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.DatabaseConfig;
import Synapse.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lei
 */
public class SplashScreen{
    
    public void startConnection(){
        try {
            Database.getInstance().DB_INIT((iDB)Class.forName("Synapse.DB." + DatabaseConfig.PROVIDER.toUpperCase()).newInstance()).startConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public boolean checkCon(){
        this.startConnection();
       return Session.INSTANCE.hasConnection();
    }
}
