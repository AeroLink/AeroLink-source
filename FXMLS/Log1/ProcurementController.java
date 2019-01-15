/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_ProcRequestsClassFile;
import FXMLS.Log1.ClassFiles.Log1_SupplierClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ProcurementRequestModel;
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
    private TableColumn<Log1_ProcRequestsClassFile, String> item_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> requestor_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> requestorPosition_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> department_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> priorityLevel_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> amount_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> pricePerUnit_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> RequestsStatus_col;
    @FXML
    private JFXTextField ProcSearch_txt;
    @FXML
    private JFXButton ApproveProcRequest_btn;
    @FXML
    private JFXButton addRequest;
    @FXML
    private JFXButton addRequest1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadProcurementRequestData();
        displayProcurementRequestData();
        ProcSearch_txt.setOnKeyReleased(e -> searchy());
        callSupplierData();
        displaySupplierData();
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
    
    public void searchy() {
        Log1_ProcurementRequestModel searchRequest = new Log1_ProcurementRequestModel(); 
            try{
            List list_coa = searchRequest.where(new Object[][]{
            {"RequestID", "like", "%" + ProcSearch_txt.getText() + "%"}
            }).get();    
        ObservableList<Log1_ProcRequestsClassFile> requests = FXCollections.observableArrayList();
            for(Object d : list_coa)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
               requests.add(new Log1_ProcRequestsClassFile(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("RequestDescription")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("RequestorPosition")),
                String.valueOf(hm.get("RequestDepartment")),
                String.valueOf(hm.get("RequestPriorityLevel")),
                String.valueOf(hm.get("RequestQuantity")),
                String.valueOf(hm.get("RequestPrice")),
                String.valueOf(hm.get("RequestStatus")),
                String.valueOf(hm.get("RequestDate"))
                       
                ));}
            ProcRequest_tbl.setItems(requests);
            
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
    public void loadProcurementRequestData(){
         Log1_ProcurementRequestModel coa = new Log1_ProcurementRequestModel();
         ObservableList<Log1_ProcRequestsClassFile> ProcRequests = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                ProcRequests.add(new Log1_ProcRequestsClassFile(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("RequestDate")),
                String.valueOf(hm.get("RequestDescription")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("RequestorPosition")),
                String.valueOf(hm.get("RequestDepartment")),
                String.valueOf(hm.get("RequestPriorityLevel")),
                String.valueOf(hm.get("RequestQuantity")),
                String.valueOf(hm.get("RequestPrice")),
                String.valueOf(hm.get("RequestStatus"))
                
                ));       
            }
                ProcRequest_tbl.setItems(ProcRequests);
    }
    
    public void displayProcurementRequestData(){
            dateRequested_col.setCellValueFactory(new PropertyValueFactory<>("RequestDate"));
            item_col.setCellValueFactory(new PropertyValueFactory<>("RequestDescription"));
            requestor_col.setCellValueFactory(new PropertyValueFactory<>("Requestor"));
            requestorPosition_col.setCellValueFactory(new PropertyValueFactory<>("RequestorPosition"));
            department_col.setCellValueFactory(new PropertyValueFactory<>("RequestDepartment"));
            priorityLevel_col.setCellValueFactory(new PropertyValueFactory<>("RequestPriorityLevel"));
            amount_col.setCellValueFactory(new PropertyValueFactory<>("RequestQuantity"));
            pricePerUnit_col.setCellValueFactory(new PropertyValueFactory<>("RequestPrice"));
            RequestsStatus_col.setCellValueFactory(new PropertyValueFactory<>("RequestStatus"));
    }
    
    @FXML
    public void approveRequest(){
        Log1_ProcRequestsClassFile ifSelectedIsNull = ProcRequest_tbl.getSelectionModel().getSelectedItem();
        if(ifSelectedIsNull == null){
            AlertMaker.showErrorMessage("No Request selected","Please select the request you want to approve");
            return;
        }
        Log1_ProcRequestsClassFile selectedforapproval = ProcRequest_tbl.getSelectionModel().getSelectedItem();
            
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Procurement/Modal/ProcurementRequestApproval.fxml"));
            Parent parent;
            
//          StockOutWHController controller = (StockOutWHController) loader.getController();
//          controller.inflateUI(selectedforapproval);
 
            parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Request Approval");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProcurementController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @FXML
    private void addRequest(ActionEvent event) {
    }

    @FXML
    private void updateRequest(ActionEvent event) {
    }
}
