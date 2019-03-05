
package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_ProcurementBiddersClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementNewItemClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementPostedItemClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementPurchaseListClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementRequestedStocksClassfiles;
import FXMLS.Log1.Procurement.Modal.ProcurementAwardBidderController;
import FXMLS.Log1.Procurement.Modal.ProcurementViewNewItemRequestDetailsController;
import FXMLS.Log1.Procurement.Modal.SupplierOfferDetailsController;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_ProcurementBiddersModel;
import Model.Log1.Log1_ProcurementNewItemModel;
import Model.Log1.Log1_ProcurementPostedItemModel;
import Model.Log1.Log1_ProcurementPurchaseListModel;
import Model.Log1.Log1_ProcurementRequestedStocksModel;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class ProcurementController implements Initializable {

    @FXML
    private TableView<Log1_ProcurementPostedItemClassfiles> RequestedItemList_tbl;
    @FXML
    private TableView<Log1_ProcurementBiddersClassfiles> ItemSuppliers_tbl;
    @FXML
    private Label itemName_txt;
    @FXML
    private Label numBidders_txt;
    @FXML
    private Label numViews_txt;
    @FXML
    private Label RequestedItemID_txt;
    @FXML
    private TableView<Log1_ProcurementNewItemClassfiles> procNewItem_tbl;
    @FXML
    private TableView<Log1_ProcurementRequestedStocksClassfiles> procStock_tbl;
    @FXML
    private TableView<Log1_ProcurementPurchaseListClassfiles> purchaseList_tbl;
    @FXML
    private DatePicker NewItemTbl_datePicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProcRequests();
        fixDateFormat();
        bidderTabtbl();
        setPurchaseListTbl();
    }
    //request tab
    @FXML
    private void reqForm(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Procurement/Modal/RequestProcurement.fxml"),
                 "", null);
    }
    
    public void fixDateFormat(){
        String pattern = "MM/dd/yyyy";

//        datePickur_txt.setPromptText(pattern.toLowerCase());

        NewItemTbl_datePicker.setConverter(new StringConverter<LocalDate>() {
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
    
    @FXML
    private void filterNewItemRequestTbl(ActionEvent event) {
        Log1_ProcurementNewItemModel procNewItemDB = new Log1_ProcurementNewItemModel();
    ObservableList<Log1_ProcurementNewItemClassfiles> procNewItemCF = FXCollections.observableArrayList(); 
        
    List b = procNewItemDB. where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).andWhere("DateRequested", "=", NewItemTbl_datePicker.getEditor().getText())
            .orderBy("PriorityLevel", Model.Sort.ASC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                procNewItemCF.add(new Log1_ProcurementNewItemClassfiles(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("RequestTitle")),
                String.valueOf(hm.get("DateRequested")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("Department")),
                String.valueOf(hm.get("Location")),
                String.valueOf(hm.get("RequestReason")),
                String.valueOf(hm.get("PriorityLevel")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("RequestStatus"))
                ));       
        }
        procNewItem_tbl.setItems(procNewItemCF);
        NewItemTbl_datePicker.getEditor().setText("");
        NewItemTbl_datePicker.getEditor().setPromptText("Select date");
    }

    @FXML
    private void refreshNewItemTbl(ActionEvent event) {
        setProcRequests();
        NewItemTbl_datePicker.getEditor().setText("");
    }
    
    public void setProcRequests(){
        setUpProcurementRequestTablesOryteLmaoEz();
        fetchNewItemProcurement();
        fetchStockRequestProcurement();
    }
       
    public void setUpProcurementRequestTablesOryteLmaoEz(){
        procNewItem_tbl.getItems().clear();
        procNewItem_tbl.getColumns().removeAll(procNewItem_tbl.getColumns());

        TableColumn<Log1_ProcurementNewItemClassfiles, String> RequestTitle = new TableColumn<>("Title");
        TableColumn<Log1_ProcurementNewItemClassfiles, String> DateRequested = new TableColumn<>("Date Requested");
        TableColumn<Log1_ProcurementNewItemClassfiles, String> Requestor = new TableColumn<>("Requestor");
        TableColumn<Log1_ProcurementNewItemClassfiles, String> PriorityLevel = new TableColumn<>("Priority Level");
        TableColumn<Log1_ProcurementNewItemClassfiles, String> ItemName = new TableColumn<>("Item");

        RequestTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        DateRequested.setCellValueFactory(param -> param.getValue().DateRequested);
        Requestor.setCellValueFactory(param -> param.getValue().Requestor);
        PriorityLevel.setCellValueFactory(param -> param.getValue().PriorityLevel);
        ItemName.setCellValueFactory(param -> param.getValue().ItemName);

        procNewItem_tbl.getColumns().addAll(RequestTitle, DateRequested, Requestor, PriorityLevel, ItemName);
        
        
        procStock_tbl.getItems().clear();
        procStock_tbl.getColumns().removeAll(procStock_tbl.getColumns());

        TableColumn<Log1_ProcurementRequestedStocksClassfiles, String> sRequestTitle = new TableColumn<>("Title");
        TableColumn<Log1_ProcurementRequestedStocksClassfiles, String> sDateRequested = new TableColumn<>("Date Requested");
        TableColumn<Log1_ProcurementRequestedStocksClassfiles, String> sRequestor = new TableColumn<>("Requestor");
        TableColumn<Log1_ProcurementRequestedStocksClassfiles, String> sPriorityLevel = new TableColumn<>("Priority Level");
        TableColumn<Log1_ProcurementRequestedStocksClassfiles, String> sItemName = new TableColumn<>("Item");

        sRequestTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        sDateRequested.setCellValueFactory(param -> param.getValue().DateRequested);
        sRequestor.setCellValueFactory(param -> param.getValue().Requestor);
        sPriorityLevel.setCellValueFactory(param -> param.getValue().PriorityLevel);
        sItemName.setCellValueFactory(param -> param.getValue().ItemName);

        procStock_tbl.getColumns().addAll(sRequestTitle, sDateRequested, sRequestor, sPriorityLevel, sItemName);
    }
    
    public void fetchNewItemProcurement(){
    Log1_ProcurementNewItemModel procNewItemDB = new Log1_ProcurementNewItemModel();
    ObservableList<Log1_ProcurementNewItemClassfiles> procNewItemCF = FXCollections.observableArrayList(); 
        
    List b = procNewItemDB. where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).orderBy("PriorityLevel", Model.Sort.ASC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                procNewItemCF.add(new Log1_ProcurementNewItemClassfiles(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("RequestTitle")),
                String.valueOf(hm.get("DateRequested")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("Department")),
                String.valueOf(hm.get("Location")),
                String.valueOf(hm.get("RequestReason")),
                String.valueOf(hm.get("PriorityLevel")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("RequestStatus"))
                ));       
        }
        procNewItem_tbl.setItems(procNewItemCF);
    }
    
    
    public void fetchStockRequestProcurement(){
    Log1_ProcurementRequestedStocksModel stockReqDB = new Log1_ProcurementRequestedStocksModel();
    ObservableList<Log1_ProcurementRequestedStocksClassfiles> stockReqCF = FXCollections.observableArrayList(); 
        
    List b = stockReqDB. where(new Object [][]{
                {"StockRequestStatus", "=", "Pending"}
            }).orderBy("PriorityLevel", Model.Sort.ASC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                stockReqCF.add(new Log1_ProcurementRequestedStocksClassfiles(
                
                String.valueOf(hm.get("ProcureStockItemID")),
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("RequestTitle")),
                String.valueOf(hm.get("DateRequested")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("Department")),
                String.valueOf(hm.get("Location")),
                String.valueOf(hm.get("Reason")),
                String.valueOf(hm.get("PriorityLevel")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("StockRequestStatus")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("ItemStatus"))
                ));       
        }
        procStock_tbl.setItems(stockReqCF);
    }
    @FXML
    private void viewNewItemRequestDetails(ActionEvent event) {
        Log1_ProcurementNewItemClassfiles requestDetails = procNewItem_tbl.getSelectionModel().getSelectedItem();
        if(requestDetails == null){
            AlertMaker.showErrorMessage("","No Request Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Procurement/Modal/ProcurementViewNewItemRequest Details.fxml"));
            Parent parent = loader.load();
            
            ProcurementViewNewItemRequestDetailsController controller = (ProcurementViewNewItemRequestDetailsController) loader.getController();
            controller.RequestDetails(requestDetails);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProcurementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void approveNewItemRequest(ActionEvent event) {
    }

    @FXML
    private void declineNewItemRequest(ActionEvent event) {
    }
    

    
    @FXML
    private void approveRequest(ActionEvent event) {
    }
    
    //request tab end
    
    
    
    //bidder tab
    public void bidderTabtbl(){
        setItemPostedAndBidderTbl();
        loadReqItemData();
    }
    
    public void setItemPostedAndBidderTbl(){
        
    // para sa item na pinost sa vendor portal at bumalik sa procurement
        RequestedItemList_tbl.getItems().clear();
        RequestedItemList_tbl.getColumns().removeAll(RequestedItemList_tbl.getColumns());

        TableColumn<Log1_ProcurementPostedItemClassfiles, String> Item = new TableColumn<>("Item Name");
        TableColumn<Log1_ProcurementPostedItemClassfiles, String> numOfBidders = new TableColumn<>("Number of Bidders");
        TableColumn<Log1_ProcurementPostedItemClassfiles, String> numOfViews = new TableColumn<>("Number of views");
        TableColumn<Log1_ProcurementPostedItemClassfiles, String> postStarted = new TableColumn<>("Post Started");
        TableColumn<Log1_ProcurementPostedItemClassfiles, String> postEnded = new TableColumn<>("Post Ended");

        Item.setCellValueFactory((param) -> param.getValue().ItemName);
        numOfBidders.setCellValueFactory(param -> param.getValue().NumberOfBidders);
        numOfViews.setCellValueFactory(param -> param.getValue().NumberOfViews);
        postStarted.setCellValueFactory(param -> param.getValue().DatePostedStarted);
        postEnded.setCellValueFactory(param -> param.getValue().DatePostedEnded);

        RequestedItemList_tbl.getColumns().addAll(Item, numOfBidders, numOfViews, postStarted, postEnded);        
        // eto yung mga suppliers nila hihe
        ItemSuppliers_tbl.getItems().clear();
        ItemSuppliers_tbl.getColumns().removeAll(ItemSuppliers_tbl.getColumns());

        TableColumn<Log1_ProcurementBiddersClassfiles, String> bidderName = new TableColumn<>("Bidder");
        TableColumn<Log1_ProcurementBiddersClassfiles, String> representative = new TableColumn<>("Representative");
        TableColumn<Log1_ProcurementBiddersClassfiles, String> contact = new TableColumn<>("Contact");
        TableColumn<Log1_ProcurementBiddersClassfiles, String> email = new TableColumn<>("E-mail");
        TableColumn<Log1_ProcurementBiddersClassfiles, String> location = new TableColumn<>("Location");

        bidderName.setCellValueFactory((param) -> param.getValue().BidderName);
        representative.setCellValueFactory(param -> param.getValue().BidderRepresentative);
        contact.setCellValueFactory(param -> param.getValue().BidderContact);
        email.setCellValueFactory(param -> param.getValue().BidderEmail);
        location.setCellValueFactory(param -> param.getValue().BidderLocation);

        ItemSuppliers_tbl.getColumns().addAll(bidderName, representative, contact, email, location);        
    }
    
    
    
    
    
    public void loadReqItemData(){
    Log1_ProcurementPostedItemModel itemRqstedDB = new Log1_ProcurementPostedItemModel();
    ObservableList<Log1_ProcurementPostedItemClassfiles> itemRqstdCF = FXCollections.observableArrayList(); 
        
        List b = itemRqstedDB.
            join(Model.JOIN.INNER, "aerolink.tbl_log1_ProcurementPostedItemStatus", "ItemID", "=", "ItemID")
                .where(new Object[][]{
                    {"Status", "=", "On going"}
                }).get();
            for(Object d : b){
                    //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            itemRqstdCF.add(new Log1_ProcurementPostedItemClassfiles(
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("NumberOfBidders")),
                String.valueOf(hm.get("NumberOfViews")),
                String.valueOf(hm.get("DatePostedStarted")),
                String.valueOf(hm.get("DatePostedEnded"))
            ));       
        }
        RequestedItemList_tbl.setItems(itemRqstdCF);
    }
    
    
    public void loadBidderData(){
    Log1_ProcurementBiddersModel bidderDB = new Log1_ProcurementBiddersModel();
    ObservableList<Log1_ProcurementBiddersClassfiles> bidderCF = FXCollections.observableArrayList(); 
        
    List b = bidderDB.where(new Object[][]{
                {"ItemID", "=", RequestedItemList_tbl.getSelectionModel().getSelectedItem().getItemID()}
            }).andWhere("BidderStatus", "=", "0").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                bidderCF.add(new Log1_ProcurementBiddersClassfiles(
                
                String.valueOf(hm.get("BidderID")),
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("BidderName")),
                String.valueOf(hm.get("BidderRepresentative")),
                String.valueOf(hm.get("BidderContact")),
                String.valueOf(hm.get("BidderEmail")),
                String.valueOf(hm.get("BidderLocation")),
                String.valueOf(hm.get("Currency")+" "+hm.get("BidderPrice")),
                String.valueOf(hm.get("BidderOtherOffer"))
                ));       
        }
        ItemSuppliers_tbl.setItems(bidderCF);
    }
    
    @FXML
    private void viewBidders(MouseEvent event) {
        ItemSuppliers_tbl.setDisable(false);
        itemName_txt.setText(RequestedItemList_tbl.getSelectionModel().getSelectedItem().getItemName());
        numBidders_txt.setText(RequestedItemList_tbl.getSelectionModel().getSelectedItem().getNumberOfBidders());
        numViews_txt.setText(RequestedItemList_tbl.getSelectionModel().getSelectedItem().getNumberOfViews());
        loadBidderData();
    }    


    @FXML
    private void viewSuppliers(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Procurement/Modal/ProcurementListOfCompanySuppliers.fxml"),
                 "", null);
    }

    @FXML
    private void viewBidderOffer(ActionEvent event) {
        Log1_ProcurementPostedItemClassfiles itemDetails = RequestedItemList_tbl.getSelectionModel().getSelectedItem();
        Log1_ProcurementBiddersClassfiles viewOffer = ItemSuppliers_tbl.getSelectionModel().getSelectedItem();
        if(viewOffer == null){
            AlertMaker.showErrorMessage("","No Bidder Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Procurement/Modal/SupplierOfferDetails.fxml"));
            Parent parent = loader.load();
            
            SupplierOfferDetailsController controller = (SupplierOfferDetailsController) loader.getController();
            controller.populateSecondListView(viewOffer);
            
            SupplierOfferDetailsController controller2 = (SupplierOfferDetailsController) loader.getController();
            controller2.populateFirstListView(itemDetails);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProcurementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void awardBidder(ActionEvent event) {
        Log1_ProcurementBiddersClassfiles awardBidder = ItemSuppliers_tbl.getSelectionModel().getSelectedItem();
        if(awardBidder == null){
            AlertMaker.showErrorMessage("","No Bidder Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Procurement/Modal/ProcurementAwardBidder.fxml"));
            Parent parent = loader.load();
            
            ProcurementAwardBidderController controller = (ProcurementAwardBidderController) loader.getController();
            controller.getBidderName(awardBidder);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProcurementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setPurchaseListTbl(){
        setPurchaseList();
        fetchPurchList();
    }
    
    
    public void setPurchaseList(){
        purchaseList_tbl.getItems().clear();
        purchaseList_tbl.getColumns().removeAll(purchaseList_tbl.getColumns());

        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> itemName = new TableColumn<>("Item");
        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> itemUnit = new TableColumn<>("Unit");
        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> Quantity = new TableColumn<>("Quantity");
        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> SupplierName = new TableColumn<>("Supplier");
        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> Price = new TableColumn<>("Price");
        TableColumn<Log1_ProcurementPurchaseListClassfiles, String> Status = new TableColumn<>("Status");

        itemName.setCellValueFactory((param) -> param.getValue().ItemName);
        itemUnit.setCellValueFactory(param -> param.getValue().ItemUnit);
        Quantity.setCellValueFactory(param -> param.getValue().Quantity);
        SupplierName.setCellValueFactory(param -> param.getValue().SupplierName);
        Price.setCellValueFactory(param -> param.getValue().Price);
        Status.setCellValueFactory(param -> param.getValue().Status);

        purchaseList_tbl.getColumns().addAll(itemName, itemUnit, 
                Quantity, SupplierName, Price, Status);
    }
    
    
    public void fetchPurchList(){
    Log1_ProcurementPurchaseListModel purchListDB = new Log1_ProcurementPurchaseListModel();
    ObservableList<Log1_ProcurementPurchaseListClassfiles> purchListCF = FXCollections.observableArrayList(); 
        
    List b = purchListDB.join(Model.JOIN.INNER, "aerolink.tbl_log1_ProcurementPostedItem",
            "ItemID", "=", "ItemID").join(Model.JOIN.INNER, 
                    "aerolink.tbl_log1_ProcurementSuppliers", "SupplierID", "=", "SupplierID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                purchListCF.add(new Log1_ProcurementPurchaseListClassfiles(
                
                String.valueOf(hm.get("PurchasedID")),
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("Currency")+" "+hm.get("Price")),
                String.valueOf(hm.get("Currency")),
                String.valueOf(hm.get("Status")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("SupplierName")),
                String.valueOf(hm.get("SupplierRepresentative")),
                String.valueOf(hm.get("SupplierContact")),
                String.valueOf(hm.get("SupplierEmail")),
                String.valueOf(hm.get("SupplierLocation"))
                ));       
        }
        purchaseList_tbl.setItems(purchListCF);
    }

    

    

    
}
