
import Model.Sample;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;

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
        
        //u.insert(0, "adminxaasasaaa", Crypt.Encrypt("edenramoneda"));
        
        
        u.update(new Object[][] {
            {"username", "admin"}
        }).where("id", "=", "19").executeUpdate();

        //u.delete().where("id", "=", "20").executeUpdate();
        
    }
    
}
