/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.DatabaseConfig;
import Synapse.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lei
 */
public class SplashScreen {

    public void startConnection() {

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

    }

    public boolean checkCon() {
        this.startConnection();
        return Session.offline ? Session.INSTANCE.hasConnection() : Session.isConnected;
    }
}
