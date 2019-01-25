
import Config.DatabaseConfig;
import Controllers.SplashScreen;
import Model.Users;
import Synapse.Database;
import Synapse.STORED_PROC;
import Synapse.Session;
import Synapse.iDB;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    public static void main(String[] args) {

        Session.schema = DatabaseConfig.schema;
        Session.provider = DatabaseConfig.PROVIDER;

        Session.Database = DatabaseConfig.DATABASE;
        Session.Ip = DatabaseConfig.IP;
        Session.Password = DatabaseConfig.PASSWORD;
        Session.Port = DatabaseConfig.PORT;
        Session.User = DatabaseConfig.USER;
        Session.offline = true;
        
        List list = STORED_PROC.executeCall("getEmployee", new Object[][]{
            {"EMP_CODE", "EMP1819001028"}
        });

        System.err.println(Arrays.asList(list.toArray()));
    }

}
