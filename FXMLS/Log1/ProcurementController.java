/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_ProcRequestsClassFile;
import FXMLS.Log1.ClassFiles.Log1_SupplierClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ProcurementPurchaseRequestModel;
import Model.Log1.Log1_SupplierModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ProcurementController implements Initializable {

    @FXML
    private TableView<Log1_ProcRequestsClassFile> ProcRequest_tbl;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> dateRequested_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> requestor_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> priorityLevel_col;
    @FXML
    private JFXButton ApproveProcRequest_btn;
    @FXML
    private JFXTextField ProcSearch_txt1;
    @FXML
    private TableView<Log1_SupplierClassfiles> Supplier_tbl;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> Supplier_col;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> Address_coll;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> contact_col;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> Representative_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> RequestsBudgetStatus_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> RequestsStatus_col1;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> itemRequested_col;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        loadProcurementRequestData();
//        displayProcurementRequestData();
//        callSupplierData();
//        displaySupplierData();
    }
    
    public void callSupplierData(){
         Log1_SupplierModel coa = new Log1_SupplierModel();
         ObservableList<Log1_SupplierClassfiles> supplier = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               supplier.add(new Log1_SupplierClassfiles(
                
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("SupplierName")),
                String.valueOf(hm.get("SupplierAddress")),
                String.valueOf(hm.get("SupplierContact")),
                String.valueOf(hm.get("SupplierRepresentative"))
                ));       
        }
        Supplier_tbl.setItems(supplier);
    }

    public void displaySupplierData(){
        Supplier_col.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        Address_coll.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        contact_col.setCellValueFactory(new PropertyValueFactory<>("SupplierContact"));
        Representative_col.setCellValueFactory(new PropertyValueFactory<>("SupplierRepresentative"));
    }    
    

    public void loadProcurementRequestData(){
         Log1_ProcurementPurchaseRequestModel coa = new Log1_ProcurementPurchaseRequestModel();
         ObservableList<Log1_ProcRequestsClassFile> ProcRequests = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                ProcRequests.add(new Log1_ProcRequestsClassFile(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("RequestDate")),
                String.valueOf(hm.get("RequestDescription")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("RequestorPosition")),
                String.valueOf(hm.get("RequestDepartment")),
                String.valueOf(hm.get("RequestPriorityLevel")),
                String.valueOf(hm.get("RequestQuantity")),
                String.valueOf(hm.get("RequestItemUnit")),
                        String.valueOf(hm.get("RequestPricePerUnit")),
                        String.valueOf(hm.get("RequestTotalPrice")),
                String.valueOf(hm.get("RequestBudget")),
                String.valueOf(hm.get("RequestStatus"))
                
                ));       
            }
                ProcRequest_tbl.setItems(ProcRequests);
    }
    
    public void displayProcurementRequestData(){
            dateRequested_col.setCellValueFactory(new PropertyValueFactory<>("RequestDate"));
            itemRequested_col.setCellValueFactory(new PropertyValueFactory<>("RequestDescription"));
            requestor_col.setCellValueFactory(new PropertyValueFactory<>("Requestor"));
            priorityLevel_col.setCellValueFactory(new PropertyValueFactory<>("RequestPriorityLevel"));;
            RequestsStatus_col1.setCellValueFactory(new PropertyValueFactory<>("RequestStatus"));
            RequestsBudgetStatus_col.setCellValueFactory(new PropertyValueFactory<>("RequestBudget"));
    }

    @FXML
    private void approveRequest(ActionEvent event) {
    }
}
