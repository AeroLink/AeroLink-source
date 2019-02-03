package FXMLS.Log1;


import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import FXMLS.Log1.Warehouse.Modal.WarehouseViewItemRequestedAvailabilityController;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_WarehouseItemsModel;
import Model.Log1.Log1_WarehouseRequestItem;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WarehouseManagementController implements Initializable {
    
    Log1_WarehouseItemsModel model = new Log1_WarehouseItemsModel();
    ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 
    Log1_WarehouseRequestItem model2 = new Log1_WarehouseRequestItem();
    ObservableList<Log1_WarehouseRequestItemClassfiles> table2 = FXCollections.observableArrayList(); 
    
    @FXML
    private Label itemID_txt;
    @FXML
    private Label supplierID_txt;
    @FXML
    private TextField itemName_txt;
    @FXML
    private TextField itemLocation_txt;
    @FXML
    private TextField itemUnit_txt;
    @FXML
    private TextField itemStock_txt;
    @FXML
    private TextField itemCriticalLevel_txt;
    @FXML
    private DatePicker itemExpDate_txt;
    @FXML
    private TextField itemPurchasedPrice_txt;
    @FXML
    private JFXButton saveItem_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private Label itemID_txt1;
    @FXML
    private Label supplierID_txt1;
    @FXML
    private TextField itemBrando_txt;
    @FXML
    private TableView<Log1_WarehouseItemsClassfiles> item_tbl;
    @FXML
    private VBox requestList_window;
    @FXML
    private TableView<Log1_WarehouseRequestItemClassfiles> request_tbl;
    @FXML
    private TitledPane requestDetails_pane;
    @FXML
    private JFXButton ReviewRequest_btn;
    @FXML
    private Label rRequestor_txt;
    @FXML
    private Label rRequestTitle_txt;
    @FXML
    private Label rLocation_txt;
    @FXML
    private Label rItem_txt;
    @FXML
    private Label rUnit_txt;
    @FXML
    private Label rQuantity_txt;
    @FXML
    private Label rDateRequested_txt;
    @FXML
    private Label rPrioritylvl_txt;
    @FXML
    private Label rPurpose_txt;
    @FXML
    private JFXButton cancelRequestViewing_btn;
    @FXML
    private JFXButton viewRequestForm_btn;
    @FXML
    private Label ritemID_txt;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveItem_btn.setOnMouseClicked(e->AddItem());
        renderItemTable();
        loadItemData();
        renderRequestTable();
        loadRequestData();
        ReviewRequest_btn.setOnMouseClicked(e->selectRequestForReviewing());
        cancelRequestViewing_btn.setOnMouseClicked(e->cancelRequestViewing());
        viewRequestForm_btn.setOnMouseClicked(e->viewRequestformpetmalu());
    }
    
    public void viewRequestformpetmalu(){
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/WarehouseRequisition.fxml"),
                 "Request Form", null);
    }
    
    public void AddItem(){
        String item = itemID_txt.getText();
        String brand = itemBrando_txt.getText();
        String loc = itemLocation_txt.getText();
        String unit = itemUnit_txt.getText();
        String stock = itemStock_txt.getText();
        String critlvl = itemCriticalLevel_txt.getText();
        String price = itemPurchasedPrice_txt.getText();    
        
        
        Boolean flag = item.isEmpty() || brand.isEmpty() || loc.isEmpty()
            || unit.isEmpty() || stock.isEmpty() || critlvl.isEmpty() ||
            price.isEmpty();
    
        if(!flag){
            try{
                String [][] tbl={
                    {"ItemName", itemName_txt.getText()},
                    {"ItemBrand", itemBrando_txt.getText()},
                    {"ItemLocation", itemLocation_txt.getText()},
                    {"ItemUnit", itemUnit_txt.getText()},
                    {"ItemStock", itemStock_txt.getText()},
                    {"ItemCriticalLevel", itemCriticalLevel_txt.getText()},
                    {"ItemExpirationDate", itemExpDate_txt.getEditor().getText()},
                    {"PurchasedPrice", itemPurchasedPrice_txt.getText()},
                    {"ItemStatus", "Good On Stock"}
                };
                if(model.insert(tbl)){
                    AlertMaker.showSimpleAlert("", ""+ itemName_txt.getText() +" has been registered to Items");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void renderItemTable(){
        item_tbl.getItems().clear();
        item_tbl.getColumns().removeAll(item_tbl.getColumns());

        TableColumn<Log1_WarehouseItemsClassfiles, String> item = new TableColumn<>("Item");
        TableColumn<Log1_WarehouseItemsClassfiles, String> loc = new TableColumn<>("Location");
        TableColumn<Log1_WarehouseItemsClassfiles, String> unit = new TableColumn<>("Item Unit");
        TableColumn<Log1_WarehouseItemsClassfiles, String> stock = new TableColumn<>("Stock");
        TableColumn<Log1_WarehouseItemsClassfiles, String> critlvl = new TableColumn<>("Critical level");
        TableColumn<Log1_WarehouseItemsClassfiles, String> status = new TableColumn<>("Status");

        item.setCellValueFactory((param) -> param.getValue().ItemName);
        loc.setCellValueFactory(param -> param.getValue().ItemLocation);
        unit.setCellValueFactory(param -> param.getValue().ItemUnit);
        stock.setCellValueFactory(param -> param.getValue().ItemStock);
        critlvl.setCellValueFactory(param -> param.getValue().ItemCriticalLevel);
        status.setCellValueFactory(param -> param.getValue().ItemStatus);

        item_tbl.getColumns().addAll(item, loc, unit, stock, critlvl, status);
    }
    public void loadItemData(){
        List b = model.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table.add(new Log1_WarehouseItemsClassfiles(
                
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemBrand")),
                String.valueOf(hm.get("ItemLocation")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("ItemStock")),
                String.valueOf(hm.get("ItemCriticalLevel")),
                String.valueOf(hm.get("ItemExpirationDate")),
                String.valueOf(hm.get("PurchasedPrice")),
                String.valueOf(hm.get("ItemStatus"))
                ));       
        }
        item_tbl.getItems().clear();
        item_tbl.setItems(table);
    }
    
    public void renderRequestTable(){
        request_tbl.getItems().clear();
        request_tbl.getColumns().removeAll(request_tbl.getColumns());

        TableColumn<Log1_WarehouseRequestItemClassfiles, String> title = new TableColumn<>("Request Title");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> priority = new TableColumn<>("Priority Level");
        TableColumn<Log1_WarehouseRequestItemClassfiles, String> date = new TableColumn<>("Date Requested");

        title.setCellValueFactory((param) -> param.getValue().RequestTitle);
        priority.setCellValueFactory(param -> param.getValue().RequestPriorityLevel);
        date.setCellValueFactory(param -> param.getValue().DateRequested);

        request_tbl.getColumns().addAll(title, priority, date);
    }
    public void loadRequestData(){
        List b = model2.join(Model.JOIN.INNER, "aerolink.tbl_log1_WarehouseItems", "ItemID", "=", "ItemID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table2.add(new Log1_WarehouseRequestItemClassfiles(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("RequestTitle")),
                String.valueOf(hm.get("RequestLocation")),
                String.valueOf(hm.get("RequestQuantity")),
                String.valueOf(hm.get("RequestPurpose")),
                String.valueOf(hm.get("RequestPriorityLevel")),
                String.valueOf(hm.get("DateRequested"))
                ));       
        }
        request_tbl.getItems().clear();
        request_tbl.setItems(table2);
    }
    
    @FXML
    private void handleMenuStockIn(ActionEvent event) {
    }
    @FXML
    private void handleMenuStockOut(ActionEvent event) {
    }

    @FXML
    private void updateItemAction(ActionEvent event) {
    }

    @FXML
    private void selectItemToProcure(MouseEvent event) {
    }

    @FXML
    private void quantityTimesPrice(KeyEvent event) {
    }

    @FXML
    private void selectDepartmentRequests(MouseEvent event) {
        ReviewRequest_btn.setDisable(false);
    }
    public void selectRequestForReviewing(){
        requestDetails_pane.setDisable(false);
        requestList_window.setDisable(true);
        rItem_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getItemName());
        rDateRequested_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getDateRequested());
        rRequestor_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestor());        
        rRequestTitle_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestTitle());
        rLocation_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestLocation());
        rUnit_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getItemUnit());
        rQuantity_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestQuantity());
        rPrioritylvl_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestPriorityLevel());
        rPurpose_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getRequestPurpose());
        ritemID_txt.setText(request_tbl.getSelectionModel().getSelectedItem().getItemID());
        
    }
    public void cancelRequestViewing(){
        rItem_txt.setText("");
        rDateRequested_txt.setText("");
        rRequestor_txt.setText("");
        rRequestTitle_txt.setText(""); 
        rLocation_txt.setText("");
        rUnit_txt.setText("");
        rQuantity_txt.setText("");
        rPrioritylvl_txt.setText("");
        rPurpose_txt.setText("");
        requestDetails_pane.setDisable(true);
        requestList_window.setDisable(false);
    }

    @FXML
    private void checkItemAvailability(ActionEvent event) {

    }
  
}
