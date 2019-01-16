/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Modal;

import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ProcurementRequestModel;
import Model.Log1.Log1_WarehouseItems;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class PurchaseRequestWarehouseController implements Initializable {
    
    ObservableList<String> PriorLevel = FXCollections.observableArrayList(
            "Low", 
            "Medium", 
            "High",
            "Critical");
    
    long DummyCount = 0;
    long GlobalCount = 0;
    
    @FXML
    private Label itemDescription_txt;
    @FXML
    private Label quantity_txt;
    @FXML
    private Label reorderLevel_txt;
    @FXML
    private TextField Requestor_txt;
    @FXML
    private TextField RequestorPosition_txt;
    @FXML
    private ComboBox<String> PriorityLevel_txt;
    @FXML
    private Label itemUnit_txt;
    @FXML
    private Label unitPrice_txt;
    @FXML
    private TableView<Log1_fullInventoryList> itemRequest_tbl;
    @FXML
    private TableColumn<Log1_fullInventoryList, String> item_col1;
    @FXML
    private TableColumn<Log1_fullInventoryList, String> status_ool2;
    @FXML
    private JFXButton SendRequest_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TextField Quantity_txt;
    @FXML
    private Label totalPrice_txt;
    @FXML
    private Label itemID_txt;
    @FXML
    private DatePicker dateToday_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetchItemData();
        displayItem();
        PriorityLevel_txt.setItems(PriorLevel);
        SendRequest_btn.setOnMouseClicked(e -> sendRequest());
        cancel_btn.setOnMouseClicked(e -> cancel());
    }
    
    @FXML
    private void selectItemToProcure(MouseEvent event) {
        itemID_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemID());
        itemDescription_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemDescription());
        quantity_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getStockQuantity());
        reorderLevel_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getCriticalQuantity());
        itemUnit_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemUnit());
        unitPrice_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getUnitPrice());
    }
    
    public void cancel(){
        Stage stage = (Stage) itemDescription_txt.getScene().getWindow();
        stage.close();
    }
    
    public void clear(){
        itemID_txt.setText("");
        itemDescription_txt.setText("");
        quantity_txt.setText("");
        reorderLevel_txt.setText("");
        itemUnit_txt.setText("");
        unitPrice_txt.setText("");
        totalPrice_txt.setText("");
        
        Requestor_txt.setText("");
        RequestorPosition_txt.setText("");
        PriorityLevel_txt.setValue("SelectPrioritLevel");
        Quantity_txt.setText("");
        dateToday_txt.getEditor().setText("");
    }
    
//    public void fetchItemData(){ 
//        Log1_WarehouseItems searchItem = new Log1_WarehouseItems();
//        CompletableFuture.supplyAsync(() -> {
//            
//            while (Session.CurrentRoute.equals("log1WM")) {
//                searchItem.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
//                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
//                });
//
//                if (DummyCount != GlobalCount) {
//
//                    itemRequest_tbl.getItems().clear();
//                    List b = searchItem.get();
//        
//         
//            try{
//                List list_coa = searchItem.where(new Object[][]{
//                    {"Status", "=", "Need to Reorder!"}
//                }).get(); 
//        ObservableList<Log1_fullInventoryList> items = FXCollections.observableArrayList();
//            for(Object d : list_coa)
//                {
//                    HashMap hm = (HashMap) d;   //exquisite casting
//                
//                    items.add(new Log1_fullInventoryList(
//                        String.valueOf(hm.get("ItemID")),
//                        String.valueOf(hm.get("SupplierName")),
//                        String.valueOf(hm.get("ItemDescription")),
//                        String.valueOf(hm.get("ItemType")),
//                        String.valueOf(hm.get("ItemLocation")),
//                        String.valueOf(hm.get("ItemUnit")),
//                        String.valueOf(hm.get("UnitPrice")),
//                        String.valueOf(hm.get("StockQuantity")),
//                        String.valueOf(hm.get("CriticalQuantity")),
//                        String.valueOf(hm.get("DisposalDate")),
//                        String.valueOf(hm.get("Status"))
//                        ));
//                }
//                itemRequest_tbl.setItems(items);
//            }catch(Exception e){
//                e.printStackTrace();
//        }}, Session.SessionThreads); 
//    }
    
    public void fetchItemData(){
        ObservableList<Log1_fullInventoryList> items = FXCollections.observableArrayList();
        Log1_WarehouseItems searchItem = new Log1_WarehouseItems();
        
        CompletableFuture.supplyAsync(() -> {

        while(Session.CurrentRoute.equals("log1WM")) {
            searchItem.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
               DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
            });

            if(DummyCount != GlobalCount) {
                itemRequest_tbl.getItems().clear();
                List list_coa = searchItem.where(new Object[][]{
                   {"Status", "=", "Need to Reorder!"}
                }).get();

        for(Object d : list_coa){
            HashMap hm = (HashMap) d;
               items.add(new Log1_fullInventoryList(
                        
                    String.valueOf(hm.get("ItemID")),
                    String.valueOf(hm.get("SupplierName")),
                    String.valueOf(hm.get("ItemDescription")),
                    String.valueOf(hm.get("ItemType")),
                    String.valueOf(hm.get("ItemLocation")),
                    String.valueOf(hm.get("ItemUnit")),
                    String.valueOf(hm.get("UnitPrice")),
                    String.valueOf(hm.get("StockQuantity")),
                    String.valueOf(hm.get("CriticalQuantity")),
                    String.valueOf(hm.get("DisposalDate")),
                    String.valueOf(hm.get("Status"))
                    ));
                }
                itemRequest_tbl.setItems(items);
                GlobalCount = DummyCount;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PurchaseRequestWarehouseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return 0;
        }, Session.SessionThreads);
     }        
    
    public void displayItem(){
            item_col1.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
            status_ool2.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }
    
    
    private void sendRequest() {
        String description = Requestor_txt.getText();
        String location = RequestorPosition_txt.getText();
        String quantity = Quantity_txt.getText();
        String date = dateToday_txt.getEditor().getText();
        
        Boolean flag = description.isEmpty() || location.isEmpty()
                || quantity.isEmpty()|| date.isEmpty();
        
        
        Log1_ProcurementRequestModel coa = new Log1_ProcurementRequestModel();
        if(!flag){
            try{
                String[][] coa_table ={
                
                {"RequestDescription",itemDescription_txt.getText()},
                {"Requestor",Requestor_txt.getText()},
                {"RequestorPosition",RequestorPosition_txt.getText()},
                {"RequestDepartment","Warehouse Department"},
                {"RequestPriorityLevel",PriorityLevel_txt.getValue()},
                {"RequestQuantity",Quantity_txt.getText()},
                {"RequestPrice",totalPrice_txt.getText()},
                {"RequestDate" ,dateToday_txt.getEditor().getText()},
                {"RequestStatus","for Approval"}};
                    if(coa.insert(coa_table)){
                        AlertMaker.showSimpleAlert("Success", "Request to procure "+ itemDescription_txt.getText() +" has been sent");
                        Log1_WarehouseItems wh = new Log1_WarehouseItems();
                        wh.where(new Object[][]{
                            {"ItemID", "=", itemID_txt.getText()}
                        }).update(new Object[][]{
                            {"Status", "Request Sent"},
                            {"ShowInMainWindow", "No"}    
                        }).executeUpdate();
                        clear();
                        return;
                    }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("Error", "Please fill up all the details");
            return;
        }
    }

    @FXML
    private void quantityTimesPrice(KeyEvent event) {
        int x=Integer.parseInt(unitPrice_txt.getText());
        int y=Integer.parseInt(Quantity_txt.getText());
        String total = String.valueOf(x*y);
        
        totalPrice_txt.setText(total);
    }
}
