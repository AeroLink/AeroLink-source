/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Config.DatabaseConfig;
import Synapse.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lei
 */
public class SplashScreen {

    public void startConnection() {

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

    public boolean checkCon() {
        this.startConnection();
        return Session.isConnected;
    }
}
