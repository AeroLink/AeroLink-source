/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Synapse.DB.MySql;
import Synapse.*;

/**
 *
 * @author Lei
 */
public class SplashScreen{
    
    
    public boolean checkCon(){
        
       Database.getInstance().DB_INIT(new MySql()).startConnection();
       return Session.INSTANCE.hasConnection();
       
    }
}
