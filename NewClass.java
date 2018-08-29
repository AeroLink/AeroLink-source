
import Model.Sample;
import Model.UserPermissions;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MySql;
import Synapse.Database;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import Synapse.Model;
>>>>>>> 6634ad01e80c1cab3e552daa47b308f35291d7af
>>>>>>> 9258e00b4ac8c48cf1042f7365f42be7c423bfae
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
        
        //Please, Don't mess up > < ! .. 
        //God Bless this Code ..
        
<<<<<<< HEAD
        List list = u.where("id", "like", "%1%").get();
        
<<<<<<< HEAD
        List list = u.where("id", "like", "%1%").get();
        
        System.out.println(Arrays.asList(list));
//        
//        u.update(new Object[][] {
//            {"username", "admin"}
//        }).where("id", "=", "19").executeUpdate();
=======
        System.out.println(Arrays.asList(list));
//        
//        u.update(new Object[][] {
//            {"username", "admin"}
//        }).where("id", "=", "19").executeUpdate();
=======
        UserPermissions up = new UserPermissions();
        List list = up
                .join(Model.JOIN.INNER, "tbl_users", "id", "=", "user_id")
                .join(Model.JOIN.INNER, "tbl_permissions", "id", "=", "permission_id")
                .get("tbl_users.id", "GROUP_CONCAT(tbl_permissions.permission) as permissions");
        
        System.out.println(Arrays.asList(list));
>>>>>>> 6634ad01e80c1cab3e552daa47b308f35291d7af
>>>>>>> 9258e00b4ac8c48cf1042f7365f42be7c423bfae

        
    }
    
}
