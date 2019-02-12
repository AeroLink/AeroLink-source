/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

//import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ProcurementPurchaseRequestModel;
import Model.Log1.Log1_WarehouseItemsModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
//    @FXML
//    private TableView<Log1_fullInventoryList> itemRequest_tbl;
//    @FXML
//    private TableColumn<Log1_fullInventoryList, String> item_col1;
//    @FXML
//    private TableColumn<Log1_fullInventoryList, String> status_ool2;
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
    @FXML
//    private TableColumn<Log1_fullInventoryList, String> Supplier_col;
//    @FXML
    private Label supplierName_txt;
    @FXML
    private Label supplierID_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetchItemData();
//        displayItem();
        PriorityLevel_txt.setItems(PriorLevel);
        SendRequest_btn.setOnMouseClicked(e -> sendRequest());
        cancel_btn.setOnMouseClicked(e -> cancel());
    }
    
//    @FXML
//    private void selectItemToProcure(MouseEvent event) {
//        itemID_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemID());
//        itemDescription_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemDescription());
//        quantity_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getStockQuantity());
//        reorderLevel_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getCriticalQuantity());
//        itemUnit_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getItemUnit());
//        unitPrice_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getUnitPrice());
//        supplierName_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getSupplierName());
////        supplierID_txt.setText(itemRequest_tbl.getSelectionModel().getSelectedItem().getSupplierID());
//    }
    
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
    

    
    public void fetchItemData(){
//        ObservableList<Log1_fullInventoryList> items = FXCollections.observableArrayList();
//        Log1_WarehouseItemsModel searchItem = new Log1_WarehouseItemsModel();
//
//            List b = searchItem.join
//               (Model.JOIN.INNER, "aerolink.tbl_log1_suppliers", "SupplierID", "=", "SupplierID")
//            .where(new Object[][]{
//                {"Status", "=", "Need to Reorder!"}
//            }).get();
//
//        for(Object d : b){
//            HashMap hm = (HashMap) d;
//            items.add(new Log1_fullInventoryList(
//                        
//                String.valueOf(hm.get("ItemID")),
//                String.valueOf(hm.get("SupplierID")),
//                String.valueOf(hm.get("SupplierName")),
//                String.valueOf(hm.get("ItemDescription")),
//                String.valueOf(hm.get("ItemType")),
//                String.valueOf(hm.get("ItemLocation")),
//                String.valueOf(hm.get("ItemUnit")),
//                String.valueOf(hm.get("UnitPrice")),
//                String.valueOf(hm.get("StockQuantity")),
//                String.valueOf(hm.get("CriticalQuantity")),
//                String.valueOf(hm.get("DisposalDate")),
//                String.valueOf(hm.get("Status"))
//            ));
//        }
//        itemRequest_tbl.setItems(items);
     }        
//    
//    public void displayItem(){
//            item_col1.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
//            status_ool2.setCellValueFactory(new PropertyValueFactory<>("Status"));
//            Supplier_col.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
//    }
    
    
    private void sendRequest() {
        String description = Requestor_txt.getText();
        String location = RequestorPosition_txt.getText();
        String quantity = Quantity_txt.getText();
        String date = dateToday_txt.getEditor().getText();
        
        Boolean flag = description.isEmpty() || location.isEmpty()
                || quantity.isEmpty()|| date.isEmpty();
        
        
        Log1_ProcurementPurchaseRequestModel coa = new Log1_ProcurementPurchaseRequestModel();
        if(!flag){
            try{
                String[][] coa_table ={
                
                {"RequestDescription",itemDescription_txt.getText()},
                {"SupplierID", supplierID_txt.getText()},
                {"Requestor",Requestor_txt.getText()},
                {"RequestorPosition",RequestorPosition_txt.getText()},
                {"RequestDepartment","Warehouse Department"},
                {"RequestPriorityLevel",PriorityLevel_txt.getValue()},
                {"RequestQuantity",Quantity_txt.getText()},
                {"RequestPricePerUnit",unitPrice_txt.getText()},
                {"RequestTotalPrice", totalPrice_txt.getText()},
                {"RequestDate" ,dateToday_txt.getEditor().getText()},
                {"RequestBudget", "No Budget"},
                {"RequestStatus","On Process"}};
                    if(coa.insert(coa_table)){
                        AlertMaker.showSimpleAlert("Success", "Request to procure "+ itemDescription_txt.getText() +" has been sent");
                        Log1_WarehouseItemsModel wh = new Log1_WarehouseItemsModel();
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
