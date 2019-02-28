package FXMLS.Log1;


import FXMLS.Log1.ClassFiles.Log1_ProcurementRequestsClassfiles;
import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import FXMLS.Log1.Warehouse.Modal.AddItemOnWarehouseController;
import FXMLS.Log1.Warehouse.Modal.AddStockWHController;
import FXMLS.Log1.Warehouse.Modal.ApproveRequestController;
import FXMLS.Log1.Warehouse.Modal.DeclineRequestController;
import FXMLS.Log1.Warehouse.Modal.ItemRecieveController;
import FXMLS.Log1.Warehouse.Modal.RequestReviewController;
import FXMLS.Log1.Warehouse.Modal.StockOutWHController;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_WarehouseItemModel;
import Synapse.Model;
import Synapse.Model.JOIN;
import Synapse.Model.Sort;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

public class WarehousingController implements Initializable {
    
    ObservableList<String> priorityLevel = FXCollections.observableArrayList
        ("3 - Low","2 - Priority","1 - Emergency");
    
    long DummyCount = 0;
    long GlobalCount = 0;
    long Global_Count = 0;
    
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> itemReq_tbl;
    @FXML
    private JFXButton viewReq_btn;
    
    
    @FXML
    private JFXButton registerItem_btn;
    @FXML
    private TableView<Log1_WarehouseItemsClassfiles> item_tbl;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> id;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> name;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> location;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> serialNum;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> unit;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> stock;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> criticalLvl;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> status;
    @FXML
    private Label numOfItems_lbl;
    @FXML
    private TextField search_txt;
    @FXML
    private JFXButton search_btn;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> approvedRequests_tbl;
    @FXML
    private JFXButton refresReq_btn;
    @FXML
    private JFXButton startRecieving_btn;
    @FXML
    private AnchorPane dp_review_req_pane;
    @FXML
    private TableView<Log1_ProcurementRequestsClassfiles> procReq_tbl;
    @FXML
    private JFXButton refresh_procReq_btn;
    @FXML
    private AnchorPane dp_review_req_pane1;
    @FXML
    private JFXButton startRecieving_btn1;
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> apprvdReqDelivery_tbl;
    @FXML
    private JFXButton approveReq__btn;
    @FXML
    private JFXButton reqStock_btn;
    @FXML
    private JFXButton decline_btn;
    @FXML
    private ComboBox<String> priorityLevel_combox;
    @FXML
    private DatePicker filterByDateReq_dpicker;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerItem_btn.setOnMouseClicked(e->registerItemPetmalu());
        setItemTbl();
        displayTotalOfAsset();
        setRequestTbl();
        criticalLevelWarning();
        search_btn.setOnMouseClicked(e-> search());
        refresh_btn.setOnMouseClicked(e->setItemTbl());
        viewReq_btn.setOnMouseClicked(e->ReviewReq());
        deploymentPlanningTbl();
        refresReq_btn.setOnMouseClicked(e->setRequestTbl());
        startRecieving_btn.setDisable(true);
        procReqTbl();
        refresh_procReq_btn.setOnMouseClicked(e->procReqTbl());
        initForDeliveryTbl();
        priorityLevel_combox.setItems(priorityLevel);
        fixDateFormat();
    }
    
    
    //inventory tab functions(codes starts here)
    //critical level indicator
    public void criticalLevelWarning(){
        status.setCellFactory(column -> {
        return new TableCell<Log1_WarehouseItemsClassfiles, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                setText(empty ? "" : getItem().toString());
                setGraphic(null);

                TableRow<Log1_WarehouseItemsClassfiles> currentRow = getTableRow();

                if (!isEmpty()) {
                    if(item.equals("Need to Reorder!")) 
                        currentRow.setStyle("-fx-background-color: red");
                    }else{
                        currentRow.setStyle("");
                    }
                }
            };
        });
    }
    //total of items in warehouse indicator
    public void displayTotalOfAsset(){ 
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            numOfItems_lbl.setText((String.valueOf(hash.get("AssetWarehouseSupply"))));
            this.setItemTbl();
        });
    }
    //actions
    // call set items window
    @FXML
    private void setItemUnit(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/SetItemUnit.fxml"),
                 "", null);
    }
    //call set item location window
    @FXML
    private void setWarehouseLocations(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/SetItemLocation.fxml"),
                 "", null);
    }
    //call register item window
    public void registerItemPetmalu(){
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddItemOnWarehouse.fxml"),
                 "", null);
    }
    //call inbounded new items window
    @FXML
    private void inboundedNewItems(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/InboundedNewItems.fxml"),
                 "", null);
    }
    // clal inbounded stocks
    @FXML
    private void InboundedStocks(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/InboundedStocks.fxml"),
                 "", null);
    }
    //preparing the table
    public void setItemTbl(){
        populateItemTable();
        prepareItemTbl();
        renderRequestTable();
        loadRequestData();
    }
    // search item function
    public void search(){
        Log1_WarehouseItemModel itemDB = new Log1_WarehouseItemModel();
        ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 
        
        List b = itemDB.where(new Object [][]{
            {"ItemName", "=", search_txt.getText()}
        }).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table.add(new Log1_WarehouseItemsClassfiles(
                
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemBrand")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("ItemLocation")),
                String.valueOf(hm.get("ItemSKU")),
                String.valueOf(hm.get("ItemSerialNumber")),
                String.valueOf(hm.get("ItemPurchasedPrice")),
                String.valueOf(hm.get("ItemPriceCurrency")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("ItemStock")),
                String.valueOf(hm.get("ItemCriticalLevel")),
                String.valueOf(hm.get("ItemRegisteredBy")),
                String.valueOf(hm.get("ItemRegisteredDate")),
                String.valueOf(hm.get("ItemStatus"))
                ));       
            }
            item_tbl.setItems(table);
    }
    //fetch item data from database
    public void populateItemTable(){
        Log1_WarehouseItemModel itemDB = new Log1_WarehouseItemModel();
        ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 
        
        List b = itemDB.orderBy("created_at", Model.Sort.DESC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table.add(new Log1_WarehouseItemsClassfiles(
                
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemBrand")),
                String.valueOf(hm.get("ItemDescription")),
                String.valueOf(hm.get("ItemLocation")),
                String.valueOf(hm.get("ItemSKU")),
                String.valueOf(hm.get("ItemSerialNumber")),
                String.valueOf(hm.get("ItemPurchasedPrice")),
                String.valueOf(hm.get("ItemPriceCurrency")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("ItemStock")),
                String.valueOf(hm.get("ItemCriticalLevel")),
                String.valueOf(hm.get("ItemRegisteredBy")),
                String.valueOf(hm.get("ItemRegisteredDate")),
                String.valueOf(hm.get("ItemStatus"))
                ));       
            }
            item_tbl.setItems(table);
    }
    //call and set table
    //this will be the container of the data you fetched from the database
    public void prepareItemTbl(){
            id.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
            name.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
            location.setCellValueFactory(new PropertyValueFactory<>("ItemLocation"));
            serialNum.setCellValueFactory(new PropertyValueFactory<>("ItemSerialNumber"));
            unit.setCellValueFactory(new PropertyValueFactory<>("ItemUnit"));
            stock.setCellValueFactory(new PropertyValueFactory<>("ItemStock"));
            criticalLvl.setCellValueFactory(new PropertyValueFactory<>("ItemCriticalLevel"));
            status.setCellValueFactory(new PropertyValueFactory<>("ItemStatus"));
    }
    
    
    //call activity log window
    @FXML
    private void viewActivityLog(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/WarehouseActivityLog.fxml"),
                 "", null);
    }
    
    
    // call edit item details window
    @FXML
    private void EditItemDetails(ActionEvent event) {
        Log1_WarehouseItemsClassfiles selectedForEdit = item_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddItemOnWarehouse.fxml"));
            Parent parent = loader.load();
            
            AddItemOnWarehouseController controller = (AddItemOnWarehouseController) loader.getController();
            controller.populateFieldsForUpdate(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // add stock / return stock functionality
    @FXML
    private void addStock(ActionEvent event) {
        Log1_WarehouseItemsClassfiles selectedForAddStock = item_tbl.getSelectionModel().getSelectedItem();
        if(selectedForAddStock == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddStockWH.fxml"));
            Parent parent = loader.load();
            
            AddStockWHController controller = (AddStockWHController) loader.getController();
            controller.populateFields(selectedForAddStock);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //remove stock functionality
    @FXML
    private void removeStock(ActionEvent event) {
        Log1_WarehouseItemsClassfiles selectedForAddStock = item_tbl.getSelectionModel().getSelectedItem();
        if(selectedForAddStock == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/StockOutWH.fxml"));
            Parent parent = loader.load();
            
            StockOutWHController controller = (StockOutWHController) loader.getController();
            controller.populateFields(selectedForAddStock);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //relocate item inside the warehouse functionality
    @FXML
    private void relocateItem(ActionEvent event) {
    }
    
    //inventory tab functions(codng ends here)
    
    
    //department requests table (coding starts here)
    
    //call request form window
    @FXML
    public void viewRequestformpetmalu(){
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/WarehouseRequisition.fxml"),
                 "", null);
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
    
    
    //initializing request table
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

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        requestor.setCellValueFactory((param) -> param.getValue().Requestor);
        department.setCellValueFactory(param -> param.getValue().RequestorDepartment);
        date.setCellValueFactory(param -> param.getValue().DateRequested);
        level.setCellValueFactory(param -> param.getValue().RequestPriorityLevel);

        itemReq_tbl.getColumns().addAll(reqTitle, requestor, department, date, level);
    }
    
    //fetch request data from databse
    public void loadRequestData(){
    Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).orderBy("RequestPriorityLevel", Sort.ASC).get();
            
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
   
    // settings the review button and approve button undisabled
    @FXML
    private void selectDepartmentRequests(MouseEvent event) {
        decline_btn.setDisable(false);
        approveReq__btn.setDisable(false);
        viewReq_btn.setDisable(false);
    }
    //review selected department request
    @FXML
    public void ReviewReq(){
        Log1_WarehouseRequestItemClassfiles reviewRequest = itemReq_tbl.getSelectionModel().getSelectedItem();
        if(reviewRequest == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/RequestReview.fxml"));
            Parent parent = loader.load();
            
            RequestReviewController controller = (RequestReviewController) loader.getController();
            controller.populateFields(reviewRequest);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void viewDeclinedRequests(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/DeclinedRequestList.fxml"),
                 "", null);
    }
    //call approve request window
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
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void declineRequest(ActionEvent event) {
        Log1_WarehouseRequestItemClassfiles selectedForDecline = itemReq_tbl.getSelectionModel().getSelectedItem();
        if(selectedForDecline == null){
            AlertMaker.showErrorMessage("","No Request Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/DeclineRequest.fxml"));
            Parent parent = loader.load();
            
            DeclineRequestController controller = (DeclineRequestController) loader.getController();
            controller.populateFields(selectedForDecline);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filterByPriiorityLevel(ActionEvent event) {
        Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
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
    private void filterByDate(ActionEvent event) {
        Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
        
    List b = model2.join(JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
            where(new Object [][]{
                {"RequestStatus", "=", "Pending"}
            }).andWhere("DateRequested", "=", filterByDateReq_dpicker.getEditor().getText()).get();
            
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
    
    //department requests coding ends here
    
    
    
    
    //deployment planning tab (coding starts here)
    
    //initiate deplyment planning tables
    @FXML
    private void viewRequestRecords(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/RequestsRecords.fxml"),
                 "", null);
    }
    
    public void deploymentPlanningTbl(){
        renderRequestTableForDeployment();
        loadRequestDataForDeployment();
    }
    public void renderRequestTableForDeployment(){
        approvedRequests_tbl.getItems().clear();
        approvedRequests_tbl.getColumns().removeAll(approvedRequests_tbl.getColumns());

        TableColumn<Log1_WarehouseRequestItemClassfiles, String> reqTitle = new TableColumn<>("Title");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> status = new TableColumn<>("status");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> approver = new TableColumn<>("Approved by");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> level = new TableColumn<>("Priority Level");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> DateApproved = new TableColumn<>("Date Approved");

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        status.setCellValueFactory(param -> param.getValue().RequestStatus);
        approver.setCellValueFactory(param -> param.getValue().RequestApprover);
        level.setCellValueFactory(param -> param.getValue().RequestPriorityLevel);
        DateApproved.setCellValueFactory(param -> param.getValue().DateApproved);

        approvedRequests_tbl.getColumns().addAll(reqTitle, status, approver, level, DateApproved);
    }
    
    //fetch data from database
    public void loadRequestDataForDeployment(){
    Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
    
    List b = model2.join(JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
        where(new Object [][]{
    {"RequestStatus", "=", "Approved"},
    {"TermsOfRecieving", "=", "Pick up"}
    }).orderBy("RequestPriorityLevel", Sort.ASC).get();

        for(Object d : b){
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
            approvedRequests_tbl.setItems(table2);
        }
    }
    //refresh table
    @FXML
    private void refreshDPtable(ActionEvent event) {
        renderRequestTableForDeployment();
        loadRequestDataForDeployment();
    }
    //recieve button un disabled
    @FXML
    private void selectDP(MouseEvent event) {
        startRecieving_btn.setDisable(false);
    }
    //initialize deplyment table for delivery
    public void initForDeliveryTbl(){
        renderRequestTableForDeploymentDelivery();
        loadRequestDataForDeploymentDelivery();
    }
    //set up table
    public void renderRequestTableForDeploymentDelivery(){
        apprvdReqDelivery_tbl.getItems().clear();
        apprvdReqDelivery_tbl.getColumns().removeAll(apprvdReqDelivery_tbl.getColumns());

        TableColumn<Log1_WarehouseRequestItemClassfiles, String> reqTitle = new TableColumn<>("Title");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> status = new TableColumn<>("status");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> approver = new TableColumn<>("Approved by");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> level = new TableColumn<>("Priority Level");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> DateApproved = new TableColumn<>("Date Approved");

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        status.setCellValueFactory(param -> param.getValue().RequestStatus);
        approver.setCellValueFactory(param -> param.getValue().RequestApprover);
        level.setCellValueFactory(param -> param.getValue().RequestPriorityLevel);
        DateApproved.setCellValueFactory(param -> param.getValue().DateApproved);

        apprvdReqDelivery_tbl.getColumns().addAll(reqTitle, status, approver, level, DateApproved);
    }
    //fetch data from database
    public void loadRequestDataForDeploymentDelivery(){
    Log1_WarehouseItemModel model2 = new Log1_WarehouseItemModel();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
    
    List b = model2.join(JOIN.INNER, "aerolink.tbl_log1_WarehouseRequestItem", "ItemID", "=", "ItemID").
        where(new Object [][]{
    {"RequestStatus", "=", "Approved"},
    {"TermsOfRecieving", "=", "Delivery"}
    }).orderBy("RequestPriorityLevel", Sort.ASC).get();
            
        for(Object d : b){
            HashMap hm = (HashMap) d;
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
            apprvdReqDelivery_tbl.setItems(table2);
        }
    }
    //call recieve requested for pick up window
    @FXML
    private void recieveRequest(ActionEvent event) {
        Log1_WarehouseRequestItemClassfiles forPickUp = approvedRequests_tbl.getSelectionModel().getSelectedItem();
        if(forPickUp == null){
            AlertMaker.showErrorMessage("","No Request selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ItemRecieve.fxml"));
            Parent parent = loader.load();
            
            ItemRecieveController controller = (ItemRecieveController) loader.getController();
            controller.populateFields(forPickUp);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehousingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //deployment planning tab (coding ends here)
    

    // request procurement tab (coding starts here)

    @FXML
    private void procureStocks(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/RequestStock.fxml"),
                 "", null);
    }

    @FXML
    private void procureNewItem(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/RequestNewItemToProcurement.fxml"),
                 "", null);
    }
    
    public void procReqTbl(){
        tableToViewProcRequest();
        loadProcReqData();
    }
    
    public void tableToViewProcRequest(){
        procReq_tbl.getItems().clear();
        procReq_tbl.getColumns().removeAll(procReq_tbl.getColumns());

        TableColumn<Log1_ProcurementRequestsClassfiles, String> reqTitle = new TableColumn<>("Title");
        TableColumn<Log1_ProcurementRequestsClassfiles, String> dateReq = new TableColumn<>("Date Requested");
        TableColumn<Log1_ProcurementRequestsClassfiles, String> itemName = new TableColumn<>("Item");
        TableColumn<Log1_ProcurementRequestsClassfiles, String> Quantity = new TableColumn<>("Quantity");
        TableColumn<Log1_ProcurementRequestsClassfiles, String> reqType = new TableColumn<>("Request Type");
        TableColumn<Log1_ProcurementRequestsClassfiles, String> Status = new TableColumn<>("Request Status");

        reqTitle.setCellValueFactory((param) -> param.getValue().RequestTitle);
        dateReq.setCellValueFactory(param -> param.getValue().DateRequested);
        itemName.setCellValueFactory(param -> param.getValue().Item);
        Quantity.setCellValueFactory(param -> param.getValue().Quantity);
        reqType.setCellValueFactory(param -> param.getValue().RequestType);
        Status.setCellValueFactory(param -> param.getValue().RequestStatus);

        procReq_tbl.getColumns().addAll(reqTitle, dateReq, itemName, Quantity, reqType, Status);
    }
    
    public void loadProcReqData(){
//    Log1_ProcurementPurchaseRequestModel procReqDB = new Log1_ProcurementPurchaseRequestModel();
//    ObservableList<Log1_ProcurementRequestsClassfiles> procReqCF = FXCollections.observableArrayList(); 
//        
//    List b = procReqDB. where(new Object [][]{
//                {"RequestStatus", "=", "Pending"}
//            }).orderBy("PriorityLevel", Sort.ASC).get();
//            
//            for(Object d : b)
//                {
//                    //rs = hm
//                HashMap hm = (HashMap) d;   //exquisite casting
//                
//                procReqCF.add(new Log1_ProcurementRequestsClassfiles(
//                
//                String.valueOf(hm.get("ProcureID")),
//                String.valueOf(hm.get("RequestTitle")),
//                String.valueOf(hm.get("NameOfRequestor")),
//                String.valueOf(hm.get("Department")),
//                String.valueOf(hm.get("Location")),
//                String.valueOf(hm.get("Reason")),
//                String.valueOf(hm.get("PriorityLevel")),
//                String.valueOf(hm.get("Item")),
//                String.valueOf(hm.get("Quantity")),
//                String.valueOf(hm.get("ItemDescription")),
//                String.valueOf(hm.get("DateRequested")),
//                String.valueOf(hm.get("RequestStatus")),
//                String.valueOf(hm.get("RequestType"))
//                ));       
//        }
//        procReq_tbl.setItems(procReqCF);
    }
    


    //procurement tab coding ends here

    

    

    

    

    

    
}
