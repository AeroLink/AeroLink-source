/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_ItemRequestsClassfiles;
import FXMLS.Log1.WarehouseManagementController;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseRequestItemModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Synapse.Model;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ViewRequestItemsController implements Initializable {

    @FXML
    private JFXButton notifyRequestor_btn;
    @FXML
    private JFXButton itemRequestLogs_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TableView<Log1_ItemRequestsClassfiles> ItemRequests_tbl;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> itemReq_col;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> quantity_col;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> requestedBy_col;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> destination_col;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> dateRequested_col;
    @FXML
    private TableColumn<Log1_ItemRequestsClassfiles, String> status_col;
    @FXML
    private JFXButton approveRequest_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        callItemRequestsData();
        displayRequestData();
        cancel_btn.setOnAction(e -> close());
    }

    public void close(){
        Stage stage = (Stage) notifyRequestor_btn.getScene().getWindow();
        stage.close();
    }

    public void callItemRequestsData(){
         Log1_WarehouseRequestItemModel ir = new Log1_WarehouseRequestItemModel();
         ObservableList<Log1_ItemRequestsClassfiles> requests = FXCollections.observableArrayList();
          
            List b = ir.join(Model.JOIN.INNER,"aerolink.tbl_log1_ItemWH", "ItemID", "=", "ItemID").where(new Object[][]{
                {"RequestStatus", "=", "For Approval"}
            }).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               requests.add(new Log1_ItemRequestsClassfiles(
                
                String.valueOf(hm.get("RequestOnWarehouseID")),
                       
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("StockQuantity")),
                String.valueOf(hm.get("Status")),
                String.valueOf(hm.get("ItemLocation")),
                String.valueOf(hm.get("CriticalQuantity")),
                       
                String.valueOf(hm.get("QuantityRequested")),
                String.valueOf(hm.get("RequestedBy")),
                String.valueOf(hm.get("Destination")),
                String.valueOf(hm.get("DateItemRequested")),
                String.valueOf(hm.get("RequestStatus"))
                       
                ));       
        }
        ItemRequests_tbl.setItems(requests);
    }

    private void displayRequestData(){
        itemReq_col.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("QuantityRequested"));
        requestedBy_col.setCellValueFactory(new PropertyValueFactory<>("RequestedBy"));
        destination_col.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        dateRequested_col.setCellValueFactory(new PropertyValueFactory<>("DateItemRequested"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("RequestStatus"));
    }

    @FXML
    private void ApproveRequestAction(ActionEvent event) {
        
            Log1_ItemRequestsClassfiles checkIfNull = ItemRequests_tbl.getSelectionModel().getSelectedItem();
            if(checkIfNull == null){
                AlertMaker.showErrorMessage("No Item selected","Please select an Item");
                return;
            }
            Log1_ItemRequestsClassfiles selectedForApproval = ItemRequests_tbl.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Modal/ApproveWarehouseRequest.fxml"));
            Parent parent = loader.load();
            
            ApproveWarehouseRequestController controller = (ApproveWarehouseRequestController) loader.getController();
            controller.inflateUI(selectedForApproval);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Approve Request");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewRequestItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
