/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.scene.Node;
import Synapse.Route;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Lei
 */

public class LoadSubSystem {

    public LoadSubSystem(String id, Node n) {
        
    }
    
    public LoadSubSystem(String id, BorderPane borderPane) {
        
        try {    
            Parent root = FXMLLoader.load(getClass().getResource(Route.routes.get(id).toString()));
            Node n = (Node) root;
            borderPane.centerProperty().set(n);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
