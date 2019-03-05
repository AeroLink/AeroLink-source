/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcurementNewItemClassfiles;
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
public class ProcurementViewNewItemRequestDetailsController implements Initializable {

    @FXML
    private Label requestTitle_txt;
    @FXML
    private JFXListView<String> requestDetails_view;
    @FXML
    private JFXListView<String> itemDetails_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void RequestDetails(Log1_ProcurementNewItemClassfiles q) {
        
        requestTitle_txt.setText(q.getRequestTitle());
        
        ObservableList<String> reqDetails = FXCollections.observableArrayList();
        
        reqDetails.add("Date Requested:  " + q.getDateRequested());
        reqDetails.add("Requested by:  " + q.getRequestor());
        reqDetails.add("Department:  " + q.getDepartment());
        reqDetails.add("Location:  " + q.getLocation());
        reqDetails.add("Request Reason:  " + q.getRequestReason());
        reqDetails.add("Priority Level:  " + q.getPriorityLevel());
        
        requestDetails_view.getItems().setAll(reqDetails);
        
        ObservableList<String> itemDetails = FXCollections.observableArrayList();
        
        itemDetails.add("Item:  " + q.getItemName());
        itemDetails.add("Item Unit:  " + q.getItemUnit());
        itemDetails.add("Quantity:  " + q.getQuantity());
        itemDetails.add("Description:  " + q.getItemDescription());
        
        itemDetails_view.getItems().setAll(itemDetails);
        
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) requestTitle_txt.getScene().getWindow();
        stage.close();
    }
    
}
