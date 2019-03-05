/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class RequestReviewController implements Initializable {

    
    @FXML
    private JFXListView<String> reqDetails_view;
    @FXML
    private JFXListView<String> itemDetails_view;
    @FXML
    private Label requestTitle_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    public void populateFields(Log1_WarehouseRequestItemClassfiles reviewRequest) {
        
        requestTitle_txt.setText(reviewRequest.getRequestTitle());
        
        ObservableList<String> reqDetails = FXCollections.observableArrayList();
        
        reqDetails.add("Requested by:  "+reviewRequest.getRequestor());
        reqDetails.add("Reason:  "+reviewRequest.getRequestReason());
        reqDetails.add("Priority Level:  "+reviewRequest.getRequestPriorityLevel());
        reqDetails.add("Department:  "+reviewRequest.getRequestorDepartment());
        reqDetails.add("Location:  "+reviewRequest.getRequestorLocation());
        reqDetails.add("Date Requested:  "+reviewRequest.getDateRequested());
        reqDetails.add("Terms of Recieving:  "+reviewRequest.getTermsOfRecieving());
        reqDetails.add("Item Requested:  "+reviewRequest.getItemName());
        reqDetails.add("Quantity:  "+reviewRequest.getRequestQuantity());
        
        reqDetails_view.getItems().setAll(reqDetails);
        
        ObservableList<String> itemAvailability = FXCollections.observableArrayList();
        
        itemAvailability.add("Item Requested:  "+reviewRequest.getItemName());
        itemAvailability.add("Item Description:  "+reviewRequest.getItemDescription());
        itemAvailability.add("Item Location:  "+reviewRequest.getItemLocation());
        itemAvailability.add("Current Stock:  "+reviewRequest.getItemStock());
        itemAvailability.add("Critical Level:  "+reviewRequest.getItemCriticalLevel());
        itemAvailability.add("Status:  "+reviewRequest.getItemStatus());
        
        itemDetails_view.getItems().setAll(itemAvailability);
    }

    
    @FXML
    private void oke(ActionEvent event) {
        Stage stage = (Stage) reqDetails_view.getScene().getWindow();
        stage.close();
    }
    
}
