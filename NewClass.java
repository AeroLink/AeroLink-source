
import Config.DatabaseConfig;
import Model.Sample;
import Model.UserPermissions;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MSSQL;
import Synapse.DB.MYSQL;
import Synapse.Database;
import Synapse.Model;
import Synapse.Session;
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
        
        Session.Database = DatabaseConfig.DATABASE;
        Session.Ip = DatabaseConfig.IP;
        Session.Password = DatabaseConfig.PASSWORD;
        Session.Port = DatabaseConfig.PORT;
        Session.User = DatabaseConfig.USER;
        
        db.DB_INIT(new MSSQL());
        
        //initialize DB Connection
        db.startConnection();
        //Please, Don't mess up > < ! .. 
        //God Bless this Code ..
        
        UserPermissions up = new UserPermissions();

        up.delete().where(new Object[][] {
            {"id", "=", 3}
        }).executeUpdate();
        
        //List list = up.get();
        
        //System.out.println(Arrays.asList(list));

        
    }
    
}
