/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1;


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
 * @author Crenz
 */
public class AssetManagementController implements Initializable {

    @FXML
    private AnchorPane AssetMngtPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void ViewBuildingTableAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/ViewBuildingTable.fxml"));
        AssetMngtPane.getChildren().setAll(pane);
    }

    @FXML
    private void ViewEquipmentTableAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/ViewEquipmentTable.fxml"));
        AssetMngtPane.getChildren().setAll(pane);
    }

    @FXML
    private void ViewCalendar(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/CalenderView.fxml"));
        AssetMngtPane.getChildren().setAll(pane);
    }  

    @FXML
    private void ViewCompanyVehicles(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/VehicleTab.fxml"));
        AssetMngtPane.getChildren().setAll(pane);
    }

    @FXML
    private void ViewDashboard(ActionEvent event) {
    }

    
}
