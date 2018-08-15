
import Model.Sample;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;
import Synapse.Model;
import java.nio.charset.StandardCharsets;
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
        
        List results = u.get(); // get all rows
        
       
        for(int i = 0; i < results.size(); i++){
            HashMap hash = (HashMap) results.get(i); // get all colums and values .. username = "BlackStar"
            
            System.out.println(hash.get("username"));
        }
        
        
        
        
        
    }
    
}
