
import Config.DatabaseConfig;
import Controllers.SplashScreen;
import Model.Users;
import Synapse.Database;
import Synapse.HttpClient;
import Synapse.STORED_PROC;
import Synapse.Session;
import Synapse.iDB;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


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

    
    /*
        Code Library
    
        1. Update Requisition Status
            
            STORED_PROC.executeCall("EIS_updateRequestStatus", new Object[][]{
                {"request_id", request_id_galingDB},
                {"request_status", 1} //1 kapag approved, 2 kapag denied, 3 on process
            });
    */
    
    public static void main(String[] args) {

        Session.HttpURL = DatabaseConfig.HttpURL;
        Session.schema = DatabaseConfig.schema;
        Session.provider = DatabaseConfig.PROVIDER;

        //if offline
        if (DatabaseConfig.offline) {
            Session.Database = DatabaseConfig.DATABASE;
            Session.Ip = DatabaseConfig.IP;
            Session.Password = DatabaseConfig.PASSWORD;
            Session.Port = DatabaseConfig.PORT;
            Session.User = DatabaseConfig.USER;
            Session.offline = DatabaseConfig.offline;
        }

        if (Session.offline) {
            try {
                Database.getInstance().DB_INIT((iDB) Class.forName("Synapse.DB." + DatabaseConfig.PROVIDER.toUpperCase()).newInstance()).startConnection();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            HttpClient.post(HttpClient.METHOD.CONNECT, "{\"aerolink_secret_key\" : \"" + DatabaseConfig.key + "\"}", (err, data) -> {
                if (err) {
                    JOptionPane.showMessageDialog(null, "Unable to connect to server");
                    System.exit(0);
                } else {
                    Session.isConnected = true;
                    Session.sestoken = (String) data.get("value");
                }
            });
        }

        List list = STORED_PROC.executeCall("EIS_CreateRequest", new Object[][] {
            {"request", 45},
            {"request_description", 51},
            {"requestor_id", 6}
        });

        System.err.println(Arrays.asList(list.toArray()));
    }

}
