/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SNviewMonitoringController implements Initializable {

    @FXML
    private AnchorPane SNrootPane1;
    @FXML
    private JFXButton SNback2;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVP;
    @FXML
    private MenuItem menuVA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back2(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceNetwork.fxml"));
        SNrootPane1.getChildren().setAll(pane);
    }

    @FXML
    private void pacnoModal(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewPackage.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewPackageController.modalOpen = false;
        });
    }

    @FXML
    private void airlineModal(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewAirline.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewAirlineController.modalOpen = false;
        });
    }
    
}
