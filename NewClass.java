
import Model.Sample;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lei
 */
public class NewClass {
    
    public static void main(String[] args){
        
        //test
        Database db =  Database.getInstance();
        db.DB_INIT(new MySql());
        db.startConnection();
        
        
        Users u = new Users();
        
        HashMap user = Crypt.Encrypt("test");
        u.insert(0, "username", user.get("cipher"), user.get("iv"));
        
        System.out.println("User Saved");
    
    }
    
}
