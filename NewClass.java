
import Model.Sample;
import Model.UserPermissions;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;
<<<<<<< HEAD
=======
import Synapse.Model;
>>>>>>> 6b280e19310fa651afadf627a1a1e4773021d4bf
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
        
        //Declaring new Instance of Database
        Database db =  Database.getInstance();
        db.DB_INIT(new MySql());
        
        //initialize DB Connection
        db.startConnection();
        
<<<<<<< HEAD
        List list = u.where("id", "like", "%1%").get();
        
        System.out.println(Arrays.asList(list));
//        
//        u.update(new Object[][] {
//            {"username", "admin"}
//        }).where("id", "=", "19").executeUpdate();
=======
        //Please, Don't mess up > < ! .. 
        //God Bless this Code ..
        UserPermissions up = new UserPermissions();
        List list = up
                .join(Model.JOIN.INNER, "tbl_users", "id", "=", "user_id")
                .join(Model.JOIN.INNER, "tbl_permissions", "id", "=", "permission_id")
                .get("tbl_users.id", "GROUP_CONCAT(tbl_permissions.permission) as permissions");
        
        System.out.println(Arrays.asList(list));
>>>>>>> 6b280e19310fa651afadf627a1a1e4773021d4bf

        
    }
    
}
