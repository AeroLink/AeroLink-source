/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class ScheduleRatesController implements Initializable {

    @FXML
    private AnchorPane SRrootPane;
    @FXML
    private JFXButton SRviewRD;
    @FXML
    private TextField srSearch;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVS;
    @FXML
    private MenuItem menuVPD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   
    @FXML
    public void slotModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SRviewSlotNo.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SRviewSlotNoController.modalOpen = false;
        });
    }

    
    @FXML
    public void packageModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SRviewPackageDetail.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SRviewPackageDetailController.modalOpen = false;
        });
    }

    @FXML
    public void viewRD() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SRinsertRateDashboard.fxml"));
        SRrootPane.getChildren().setAll(pane);
    }

//    private void viewAL(ActionEvent event) throws IOException{
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SRviewAirlineSchedule.fxml"));
//        SRrootPane.getChildren().setAll(pane);
//    }

}
