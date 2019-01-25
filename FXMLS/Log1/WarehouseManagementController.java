package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.Warehouse.Modal.AddItemOnWarehouseController;
import FXMLS.Log1.Warehouse.Modal.AddStockWHController;
import FXMLS.Log1.Warehouse.Modal.StockOutWHController;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_WarehouseItemsModel;
import Synapse.Model;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WarehouseManagementController implements Initializable {

    long DummyCount = 0;
    long GlobalCount = 0;
    
    private TableView<Log1_fullInventoryList> ItemWH_tbl;
    private TableColumn<Log1_fullInventoryList, String> ItemDescript_col2;
    private TableColumn<Log1_fullInventoryList, String> ItemType_col3;
    private TableColumn<Log1_fullInventoryList, String> itemLoc_col4;
    private TableColumn<Log1_fullInventoryList, String> itemUnit_col5;
    private TableColumn<Log1_fullInventoryList, String> price_col6;
    private TableColumn<Log1_fullInventoryList, String> Stock_col7;
    private TableColumn<Log1_fullInventoryList, String> reorderLevle_col8;
    private TableColumn<Log1_fullInventoryList, String> status_col11;
    private TableColumn<Log1_fullInventoryList, String> supp_col;
    private TextField whSearch_txt;
    @FXML
    private Label itemID_txt;
    @FXML
    private Label supplierID_txt;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        callWarehouseItems();
//        displayWarehouseItem();
//        whSearch_txt.setOnKeyReleased(e -> searchItem());
    }
    
    //search functionality
    
    public void searchItem() {
        Log1_WarehouseItemsModel searchItem = new Log1_WarehouseItemsModel(); 

            try{
            List list_coa = searchItem.join(Model.JOIN.INNER, "aerolink.tbl_log1_suppliers", 
                    "SupplierID", "=", "SupplierID").where(new Object[][]{
            {"ItemDescription", "like", "%" + whSearch_txt.getText() + "%"}
            }).get();    
            ObservableList<Log1_fullInventoryList> items = FXCollections.observableArrayList();
            for(Object d : list_coa)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
               items.add(new Log1_fullInventoryList(
                
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("SupplierID")),
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
                       
                ));}
            ItemWH_tbl.setItems(items);
            
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
    
    //fetching data
    public void callWarehouseItems(){
        Log1_WarehouseItemsModel coa = new Log1_WarehouseItemsModel();
        ObservableList<Log1_fullInventoryList> ItemsXD = FXCollections.observableArrayList();
          

        List b = coa.join
            (Model.JOIN.INNER, "aerolink.tbl_log1_suppliers", "SupplierID", "=", "SupplierID").where(new Object[][]{
            {"ShowInMainWindow", "=", "yes"}
            }).get(); 
                
        
        for(Object d : b){//rs = hm
            
                HashMap hm = (HashMap) d;   //exquisite casting
                
                ItemsXD.add(new Log1_fullInventoryList( 
                    String.valueOf(hm.get("ItemID")),
                    String.valueOf(hm.get("SupplierID")),
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
                ItemWH_tbl.setItems(ItemsXD);
    }
    
    //displaying on table column
    public void displayWarehouseItem(){
            supp_col.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
            ItemDescript_col2.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
            ItemType_col3.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
            itemLoc_col4.setCellValueFactory(new PropertyValueFactory<>("ItemLocation"));
            itemUnit_col5.setCellValueFactory(new PropertyValueFactory<>("ItemUnit"));
            price_col6.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
            Stock_col7.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));
            reorderLevle_col8.setCellValueFactory(new PropertyValueFactory<>("CriticalQuantity"));
            status_col11.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

    @FXML
    private void handleMenuStockIn(ActionEvent event) {
        Log1_fullInventoryList selectedForEdit = ItemWH_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("No Item selected","Please select an Item");
            return;
        } 
        Log1_fullInventoryList selectedForAddStock = ItemWH_tbl.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddStockWH.fxml"));
            Parent parent = loader.load();
            
            AddStockWHController controller = (AddStockWHController) loader.getController();
            controller.inflateUI(selectedForAddStock);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Stock");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleMenuStockOut(ActionEvent event) {
        Log1_fullInventoryList selectedForEdit = ItemWH_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("No Item selected","Please select an Item");
            return;
        } 
        Log1_fullInventoryList selectedForAddStock = ItemWH_tbl.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/StockOutWH.fxml"));
            Parent parent = loader.load();
            
            StockOutWHController controller = (StockOutWHController) loader.getController();
            controller.inflateUI(selectedForAddStock);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Take out Stock");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void addNewItemAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddItemOnWarehouse.fxml"),
                 "Add New Item", null);
    }

    //edit data details
    @FXML
    private void updateItemAction(ActionEvent event) {
       Log1_fullInventoryList selectedForEdit = ItemWH_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("No Item selected","Please select an Item");
            return;
        } 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/AddItemOnWarehouse.fxml"));
            Parent parent = loader.load();
            
            AddItemOnWarehouseController controller = (AddItemOnWarehouseController) loader.getController();
            controller.displaySelectedIndex(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Update Item Details");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleActivityLogAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/WarehouseActivityLog.fxml"),
                 "Activity Log", null);
    }

    private void handleRequestsViewAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ViewRequestItems.fxml"),
                 "Requested items by other department", null);
    }

    private void requestToProcure(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/PurchaseRequestWarehouse.fxml"),
                 "Request to Procurement", null);
    }

    private void handleViewCountReportAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/WarehouseCountReport.fxml"),
                 "Count Report", null);
    }

    private void showRequestedStocks(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ViewRequestedStocks.fxml"),
                 "Requested Stocks", null);
    }

    private void RequestItemOnWarehouseAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/RequestItemsOnWarehouse.fxml"),
                 "Requested Items", null);
    }

    @FXML
    private void selectItemToProcure(MouseEvent event) {
    }

    @FXML
    private void quantityTimesPrice(KeyEvent event) {
    }
}
