/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

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
public class CRMviewReportController implements Initializable {

    @FXML
    private AnchorPane CRMrootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void viewComplaints(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewComplaints.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewReports(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewReport.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewMonitoring(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/CustomerRelationshipManagement.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

}
