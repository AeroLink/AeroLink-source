/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import Model.Core2.CORE2_type;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SNviewInternationalBranchController implements Initializable {

    @FXML
    private AnchorPane SNrootPane;
    @FXML
    private JFXButton SNviewN;
    @FXML
    private JFXButton SNviewM;
    @FXML
    private JFXButton SNviewR;
    @FXML
    private ComboBox cbmType;
    @FXML
    private JFXButton SNviewL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    // ILAGAY NLNG SA ON ACTION FUNCTION SA-->CODE 
    // PARA GUMANA YUNG CALL NG MODAL

    @FXML
    public void viewN() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewBranch.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewM(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewMonitoring.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewR(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewReport.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewL(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceNetwork.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }
}
