/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import com.jfoenix.controls.JFXButton;
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
 * @author JPEG
 */
public class SOPviewInspectionGuidelinesController implements Initializable {

    @FXML
    private AnchorPane SOProotPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    // mga button sa taas
    @FXML
    private void packagedetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/StandardOperationalProcedure.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void manageguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPcreateManageGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void packageinspection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewPackageInspection.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }
}
