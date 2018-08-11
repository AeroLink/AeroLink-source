/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Helpers.LoadSubSystem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ARIELLECIAS
 */
public class MainDashController implements Initializable {

    @FXML
    private BorderPane DropPoint;
    @FXML
    private JFXTabPane TabPane;
    @FXML
    private JFXButton btnRight;
    @FXML
    private AnchorPane ACPaneRight;

    
    public Boolean paneOpen = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void triggerButton(ActionEvent event) {
        LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem(((JFXButton) event.getSource()).getId(), DropPoint);
    }

    @FXML
    private void pullRight(ActionEvent event) {
        if(this.paneOpen) {
            ACPaneRight.setPrefWidth(0);
            this.paneOpen = false;
        } else {
            ACPaneRight.setPrefWidth(262);
            this.paneOpen = true;
        }
      
    }

    @FXML
    private void btnClose(ActionEvent event) {
        System.exit(0);
    }

}
