/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components.Modal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class ModalController implements Initializable {

    @FXML
    private BorderPane DropPoint;
    @FXML
    private AnchorPane acpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acpane.setPrefHeight(((AnchorPane) Synapse.Components.Modal.Modal.ow).getPrefHeight());
        acpane.setPrefWidth(((AnchorPane) Synapse.Components.Modal.Modal.ow).getPrefWidth());
        DropPoint.centerProperty().set((Node) Synapse.Components.Modal.Modal.ow);
    }    
    
}
