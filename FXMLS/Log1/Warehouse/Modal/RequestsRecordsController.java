/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import Model.Log1.Log1_WarehouseItemModel;
import Synapse.Model;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class RequestsRecordsController implements Initializable {

    @FXML
    private DatePicker filterByDateReq_dpicker;
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> itemReq_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> dateR = new TableColumn<>("Date Recieved");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> timeR = new TableColumn<>("Time Recieved");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> Rby = new TableColumn<>("Recieved by");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> Rfrom = new TableColumn<>("Recieved from");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> packager = new TableColumn<>("Packaged by");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> item = new TableColumn<>("Item");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> quantity = new TableColumn<>("Quantity");

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        requestor.setCellValueFactory((param) -> param.getValue().Requestor);
        department.setCellValueFactory(param -> param.getValue().RequestorDepartment);
        date.setCellValueFactory(param -> param.getValue().DateRequested);
        dateR.setCellValueFactory(param -> param.getValue().DateRecieved);
        timeR.setCellValueFactory(param -> param.getValue().TimeRecieved);
        Rby.setCellValueFactory(param -> param.getValue().RecievedBy);
        Rfrom.setCellValueFactory(param -> param.getValue().RecievedFrom);
        packager.setCellValueFactory(param -> param.getValue().PackagedBy);
        item.setCellValueFactory(param -> param.getValue().ItemName);
        quantity.setCellValueFactory(param -> param.getValue().RequestQuantity);

        itemReq_tbl.getColumns().addAll(reqTitle, requestor, department, 
                date, dateR, timeR, Rby, Rfrom, packager, item, quantity);
    }
    
    //fetch request data from databse
    public void loadRequestData(){
    Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(Model.JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Request Completed!"}
            }).orderBy("DateRecieved", Model.Sort.DESC).get();
            
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
                {"RequestStatus", "=", "Request Completed!"}
            }).andWhere("DateRecieved", "=", filterByDateReq_dpicker.getEditor().getText()).get();
            
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
    private void refresh(ActionEvent event) {
        setRequestTbl();
    }
    
}
