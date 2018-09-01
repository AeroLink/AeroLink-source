/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.core1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Core1_pomController implements Initializable {

    private AnchorPane rootPane;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void load_summary(ActionEvent event) throws IOException {
        AnchorPane sum = FXMLLoader.load(getClass().getResource("Core1_pom_purchase_sum.fxml"));
          mainPane.getChildren().setAll(sum);
        
        
    }

    @FXML
    private void load_entry(ActionEvent event) throws IOException {
        
         AnchorPane entry = FXMLLoader.load(getClass().getResource("Core1_pom_purchase_ent.fxml"));
          mainPane.getChildren().setAll(entry);
    }
    
}
