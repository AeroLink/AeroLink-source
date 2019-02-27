
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_DepartmentModel;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_ProcurementRequestedStocksModel;
import Model.Log1.Log1_WarehouseItemModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RequestStockController implements Initializable {
    
    ObservableList<String> PriorityLvl = 
    FXCollections.observableArrayList("3 - Low","2 - Priority","1 - Emergency");

    @FXML
    private Label date_lbl;
    @FXML
    private Label time_lbl;
    @FXML
    private TableView<Log1_WarehouseItemsClassfiles> item_tbl;
    @FXML
    private JFXButton submitoRequesto_btn;
    @FXML
    private JFXButton cancero_btn;
    @FXML
    private TextField requestTitle_txt;
    @FXML
    private TextField firstName_txt;
    @FXML
    private TextField lastName_txt;
    @FXML
    private TextArea youAreTheReason_txt;
    @FXML
    private TextField itemProcuring_txt;
    @FXML
    private TextField qty_txt;
    @FXML
    private ComboBox<String> priorityLvl_combox;
    @FXML
    private VBox department_tab;
    @FXML
    private ComboBox department_combox;
    @FXML
    private ComboBox location_combox;
    @FXML
    private TextArea itemDescription_txt;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> name;
    @FXML
    private TableColumn<Log1_WarehouseItemsClassfiles, String> status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setItemtbl();
        displayCurrentDate();
        priorityLvl_combox.setItems(PriorityLvl);
        loadBuildingToCombox();
        loadDepartmentToCombox();
        cancero_btn.setOnMouseClicked(e->close());
        submitoRequesto_btn.setOnMouseClicked(e->submit());
        department_tab.setDisable(true);
    }    
    
    public void close(){
        clearFields();
        Stage stage = (Stage) priorityLvl_combox.getScene().getWindow();
        stage.close();
    }
    
    public void clearFields(){
        requestTitle_txt.setText("");
        firstName_txt.setText("");
        lastName_txt.setText("");
        department_combox.setValue("");
        location_combox.setValue("");
        priorityLvl_combox.setValue("");
        youAreTheReason_txt.setText("");
        itemProcuring_txt.setText("");
        qty_txt.setText("");
        itemDescription_txt.setText("");
    }
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//      get current date time with the power of friendship
        Date date = new Date();
        date_lbl.setText(dateFormat.format(date));
        
        Calendar cal = Calendar.getInstance();
        time_lbl.setText(timeFormat.format(cal.getTime()));
    }
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            location_combox.getItems().add(String.valueOf(hash.get("bAssetTitle")) + " - " + String.valueOf(hash.get("bAssetCoreLocation")));
        });
    }
    public void loadDepartmentToCombox(){
        HR4_DepartmentModel assetBuilding = new HR4_DepartmentModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            department_combox.getItems().add(String.valueOf(hash.get("dept_name")));
        });
    }
    public void submit(){
        String date = date_lbl.getText();
        String title = requestTitle_txt.getText();
        String fname = firstName_txt.getText();
        String lname = lastName_txt.getText();
        String location = location_combox.getValue().toString();
        String plevel = priorityLvl_combox.getValue();
        String reason = youAreTheReason_txt.getText();
        String item = itemProcuring_txt.getText();
        String qty = qty_txt.getText();
        String itemdescript = itemDescription_txt.getText();
        
        Boolean flag = 
                date.isEmpty() ||
                title.isEmpty() ||
                fname.isEmpty() ||
                lname.isEmpty() ||
                location.matches("") ||
                plevel.matches("") ||
                reason.isEmpty() ||
                item.isEmpty() ||
                qty.isEmpty() ||
                itemdescript.isEmpty();
        
        Log1_ProcurementRequestedStocksModel procDB = new Log1_ProcurementRequestedStocksModel();
        
        if(flag){
            AlertMaker.showErrorMessage("","Please Fill up all the fields");
        }else if(!flag){
            try{
                String[][] reqData ={
                    {"ItemID", item_tbl.getSelectionModel().getSelectedItem().getItemID()},
                    {"RequestTitle", requestTitle_txt.getText()},
                    {"DateRequested",date_lbl.getText()},
                    {"Requestor", firstName_txt.getText() + ", " + lastName_txt.getText()},
                    {"Department", "Logistics - Warehouse Management"},
                    {"Location", location_combox.getValue().toString()},
                    {"Reason", youAreTheReason_txt.getText()},
                    {"PriorityLevel", priorityLvl_combox.getValue()},
                    {"Quantity",qty_txt.getText()},
                    {"StockRequestStatus","Pending"},
                };
                if(procDB.insert(reqData)){
                    AlertMaker.showSimpleAlert("", "Request has been sent!");
                    clearFields();
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }
    }
    
    public void setItemtbl(){
        populateItemTable();
        prepareItemTbl();
    }
    
    public void populateItemTable(){
        Log1_WarehouseItemModel itemDB = new Log1_WarehouseItemModel();
        ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 
        
        List b = itemDB.where(new Object[][]{
            {"ItemStatus", "=", "Need to Reorder!"}
        }).orderBy("created_at", Model.Sort.DESC).get();
            
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
    
    public void prepareItemTbl(){
            name.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
            status.setCellValueFactory(new PropertyValueFactory<>("ItemStatus"));
    }

    @FXML
    private void selectItem(MouseEvent event) {
        itemProcuring_txt.setText(item_tbl.getSelectionModel().getSelectedItem().getItemName());
    }
    
}
