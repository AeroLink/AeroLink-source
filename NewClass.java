
import Model.Sample;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;
import java.util.Arrays;
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
        

        //new 
        Users u = new Users();
        
        //u.insert(0, "adminxaasasaaa", Crypt.Encrypt("edenramoneda"));
        
        List list = u.where("id", "like", "%1%").get();
        
        System.out.println(Arrays.asList(list));
//        
//        u.update(new Object[][] {
//            {"username", "admin"}
//        }).where("id", "=", "19").executeUpdate();

        //u.delete().where("id", "=", "20").executeUpdate();
        
    }
    
}
