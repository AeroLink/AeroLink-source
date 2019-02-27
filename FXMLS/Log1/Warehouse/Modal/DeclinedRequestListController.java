/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import FXMLS.Log1.WarehousingController;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseItemModel;
import Synapse.Model;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class DeclinedRequestListController implements Initializable {
    
    ObservableList<String> priorityLevel = FXCollections.observableArrayList
        ("3 - Low","2 - Priority","1 - Emergency");

    @FXML
    private DatePicker filterByDateReq_dpicker;
    @FXML
    private ComboBox<String> priorityLevel_combox;
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> itemReq_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priorityLevel_combox.setItems(priorityLevel);
        // TODO
        fixDateFormat();
        setRequestTbl();
    }    

    public void fixDateFormat(){
        String pattern = "MM/dd/yyyy";

//        datePickur_txt.setPromptText(pattern.toLowerCase());

        filterByDateReq_dpicker.setConverter(new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

        @Override 
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                return null;
                }
            }
        });
    }
    
    
    public void setRequestTbl(){
        renderRequestTable();
        loadRequestData();
        priorityLevel_combox.setValue("Select Priority Level");
        filterByDateReq_dpicker.getEditor().setText("Select date");
    }
    //preparing request table 
    public void renderRequestTable(){
        itemReq_tbl.getItems().clear();
        itemReq_tbl.getColumns().removeAll(itemReq_tbl.getColumns());

        TableColumn<Log1_WarehouseRequestItemClassfiles, String> reqTitle = new TableColumn<>("Title");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> requestor = new TableColumn<>("Requestor");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> department = new TableColumn<>("From");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> date = new TableColumn<>("Date Requested");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> level = new TableColumn<>("Priority Level");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> declinedby = new TableColumn<>("Declined by");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> declinedDate = new TableColumn<>("Declined date");

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        requestor.setCellValueFactory((param) -> param.getValue().Requestor);
        department.setCellValueFactory(param -> param.getValue().RequestorDepartment);
        date.setCellValueFactory(param -> param.getValue().DateRequested);
        level.setCellValueFactory(param -> param.getValue().RequestPriorityLevel);
        declinedby.setCellValueFactory(param -> param.getValue().RequestApprover);
        declinedDate.setCellValueFactory(param -> param.getValue().DateApproved);

        itemReq_tbl.getColumns().addAll(reqTitle, requestor, department, date, level, declinedby, declinedDate);
    }
    
    //fetch request data from databse
    public void loadRequestData(){
    Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(Model.JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Declined"}
            }).orderBy("RequestPriorityLevel", Model.Sort.ASC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table2.add(new Log1_WarehouseRequestItemClassfiles(
                
                    String.valueOf(hm.get("RequestID")),
                    String.valueOf(hm.get("RequestTitle")),
                    String.valueOf(hm.get("Requestor")),
                    String.valueOf(hm.get("RequestReason")),
                    String.valueOf(hm.get("RequestQuantity")),
                    String.valueOf(hm.get("RequestPriorityLevel")),
                    String.valueOf(hm.get("RequestorDepartment")),
                    String.valueOf(hm.get("RequestorLocation")),
                    String.valueOf(hm.get("DateRequested")),
                    String.valueOf(hm.get("RequestStatus")),
                    String.valueOf(hm.get("TermsOfRecieving")),
                    String.valueOf(hm.get("ApprovedRequestRemarks")),
                    String.valueOf(hm.get("RequestApprover")),
                    String.valueOf(hm.get("DateApproved")),
                    String.valueOf(hm.get("TimeApproved")),
                        
                    String.valueOf(hm.get("RecievedFrom")),    
                    String.valueOf(hm.get("PackagedBy")),
                    String.valueOf(hm.get("RecievedBy")),
                    String.valueOf(hm.get("RecievedRemarks")),
                    String.valueOf(hm.get("DateRecieved")),
                    String.valueOf(hm.get("TimeRecieved")),
                    
                        
                    String.valueOf(hm.get("ItemID")),
                    String.valueOf(hm.get("ItemName")),
                    String.valueOf(hm.get("ItemBrand")),
                    String.valueOf(hm.get("ItemDescription")),
                    String.valueOf(hm.get("ItemLocation")),
                    String.valueOf(hm.get("ItemSKU")),
                    String.valueOf(hm.get("ItemSerialNumber")),
                    String.valueOf(hm.get("ItemPurchasedDate")),
                    String.valueOf(hm.get("ItemPurchasedPrice")),
                    String.valueOf(hm.get("ItemPriceCurrency")),
                    String.valueOf(hm.get("ItemUnit")),
                    String.valueOf(hm.get("ItemStock")),
                    String.valueOf(hm.get("ItemCriticalLevel")),
                    String.valueOf(hm.get("ItemRegisteredDate")),
                    String.valueOf(hm.get("ItemStatus"))  
                ));       
        }
        itemReq_tbl.setItems(table2);
    }
    
    
    @FXML
    private void filterByDate(ActionEvent event) {
        Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(Model.JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).andWhere("DateApproved", "=", filterByDateReq_dpicker.getEditor().getText()).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table2.add(new Log1_WarehouseRequestItemClassfiles(
                
                    String.valueOf(hm.get("RequestID")),
                    String.valueOf(hm.get("RequestTitle")),
                    String.valueOf(hm.get("Requestor")),
                    String.valueOf(hm.get("RequestReason")),
                    String.valueOf(hm.get("RequestQuantity")),
                    String.valueOf(hm.get("RequestPriorityLevel")),
                    String.valueOf(hm.get("RequestorDepartment")),
                    String.valueOf(hm.get("RequestorLocation")),
                    String.valueOf(hm.get("DateRequested")),
                    String.valueOf(hm.get("RequestStatus")),
                    String.valueOf(hm.get("TermsOfRecieving")),
                    String.valueOf(hm.get("ApprovedRequestRemarks")),
                    String.valueOf(hm.get("RequestApprover")),
                    String.valueOf(hm.get("DateApproved")),
                    String.valueOf(hm.get("TimeApproved")),
                        
                    String.valueOf(hm.get("RecievedFrom")),    
                    String.valueOf(hm.get("PackagedBy")),
                    String.valueOf(hm.get("RecievedBy")),
                    String.valueOf(hm.get("RecievedRemarks")),
                    String.valueOf(hm.get("DateRecieved")),
                    String.valueOf(hm.get("TimeRecieved")),
                    
                        
                    String.valueOf(hm.get("ItemID")),
                    String.valueOf(hm.get("ItemName")),
                    String.valueOf(hm.get("ItemBrand")),
                    String.valueOf(hm.get("ItemDescription")),
                    String.valueOf(hm.get("ItemLocation")),
                    String.valueOf(hm.get("ItemSKU")),
                    String.valueOf(hm.get("ItemSerialNumber")),
                    String.valueOf(hm.get("ItemPurchasedDate")),
                    String.valueOf(hm.get("ItemPurchasedPrice")),
                    String.valueOf(hm.get("ItemPriceCurrency")),
                    String.valueOf(hm.get("ItemUnit")),
                    String.valueOf(hm.get("ItemStock")),
                    String.valueOf(hm.get("ItemCriticalLevel")),
                    String.valueOf(hm.get("ItemRegisteredDate")),
                    String.valueOf(hm.get("ItemStatus"))  
                ));       
        }
        itemReq_tbl.setItems(table2);
    }

    @FXML
    private void filterByPriiorityLevel(MouseEvent event) {
        Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(Model.JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).andWhere("RequestPriorityLevel", "=", priorityLevel_combox.getSelectionModel().getSelectedItem().toString()).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table2.add(new Log1_WarehouseRequestItemClassfiles(
                
                    String.valueOf(hm.get("RequestID")),
                    String.valueOf(hm.get("RequestTitle")),
                    String.valueOf(hm.get("Requestor")),
                    String.valueOf(hm.get("RequestReason")),
                    String.valueOf(hm.get("RequestQuantity")),
                    String.valueOf(hm.get("RequestPriorityLevel")),
                    String.valueOf(hm.get("RequestorDepartment")),
                    String.valueOf(hm.get("RequestorLocation")),
                    String.valueOf(hm.get("DateRequested")),
                    String.valueOf(hm.get("RequestStatus")),
                    String.valueOf(hm.get("TermsOfRecieving")),
                    String.valueOf(hm.get("ApprovedRequestRemarks")),
                    String.valueOf(hm.get("RequestApprover")),
                    String.valueOf(hm.get("DateApproved")),
                    String.valueOf(hm.get("TimeApproved")),
                        
                    String.valueOf(hm.get("RecievedFrom")),    
                    String.valueOf(hm.get("PackagedBy")),
                    String.valueOf(hm.get("RecievedBy")),
                    String.valueOf(hm.get("RecievedRemarks")),
                    String.valueOf(hm.get("DateRecieved")),
                    String.valueOf(hm.get("TimeRecieved")),
                    
                        
                    String.valueOf(hm.get("ItemID")),
                    String.valueOf(hm.get("ItemName")),
                    String.valueOf(hm.get("ItemBrand")),
                    String.valueOf(hm.get("ItemDescription")),
                    String.valueOf(hm.get("ItemLocation")),
                    String.valueOf(hm.get("ItemSKU")),
                    String.valueOf(hm.get("ItemSerialNumber")),
                    String.valueOf(hm.get("ItemPurchasedDate")),
                    String.valueOf(hm.get("ItemPurchasedPrice")),
                    String.valueOf(hm.get("ItemPriceCurrency")),
                    String.valueOf(hm.get("ItemUnit")),
                    String.valueOf(hm.get("ItemStock")),
                    String.valueOf(hm.get("ItemCriticalLevel")),
                    String.valueOf(hm.get("ItemRegisteredDate")),
                    String.valueOf(hm.get("ItemStatus"))  
                ));       
        }
        itemReq_tbl.setItems(table2);
                
    }

    @FXML
    private void ReviewReq(ActionEvent event) {
        Log1_WarehouseRequestItemClassfiles reviewRequest = itemReq_tbl.getSelectionModel().getSelectedItem();
        if(reviewRequest == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ReviewDeclinedRequest.fxml"));
            Parent parent = loader.load();
            
            ReviewDeclinedRequestController controller = (ReviewDeclinedRequestController) loader.getController();
            controller.populateFields(reviewRequest);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DeclinedRequestListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void approveReq(ActionEvent event) {
        Log1_WarehouseRequestItemClassfiles selectedForApproval = itemReq_tbl.getSelectionModel().getSelectedItem();
        if(selectedForApproval == null){
            AlertMaker.showErrorMessage("","No Request Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ApproveRequest.fxml"));
            Parent parent = loader.load();
            
            ApproveRequestController controller = (ApproveRequestController) loader.getController();
            controller.populateFields(selectedForApproval);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DeclinedRequestListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        setRequestTbl();
    }
    
}
