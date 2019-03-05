
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcurementSuppliersClassfiles;
import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_EmployeeProfilesModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_ProcurementSupplierModel;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Model.Log1.Log1_WarehouseItemLocationModel;
import Model.Log1.Log1_WarehouseItemModel;
import Model.Log1.Log1_WarehouseItemUnitModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddItemOnWarehouseController implements Initializable {
    
    private boolean isInEditMode = false;
    
    @FXML
    private Label dateItemAdded_txt;
    @FXML
    private Label AssetWarehouseCount_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton close_btn;
    @FXML
    private TextField itemName_txt;
    @FXML
    private TextField itemBrando_txt;
    @FXML
    private ComboBox itemLocation_txt;
    @FXML
    private TextField SKU_txt;
    @FXML
    private TextField SerialNum_txt;
    @FXML
    private TextField purchPrice_txt;
    @FXML
    private TextArea description_txt;
    @FXML
    private ComboBox itemUnit_txt;
    @FXML
    private TextField itemStock_txt;
    @FXML
    private TextField critLevel_txt;
    @FXML
    private Label time_txt;
    @FXML
    private Label itemID_txt;
    @FXML
    private Label dateToday_lbl;
    @FXML
    private Label register_lbl;
    @FXML
    private ComboBox addedBy_txt;
    @FXML
    private Label usertxt_lvl;
    @FXML
    private TableView<Log1_ProcurementSuppliersClassfiles> supplier_tbl;
    @FXML
    private TextField supplier_txt;
    @FXML
    private Label locationLimit_txt;
    @FXML
    private Label locationCount_txt;
    @FXML
    private TextField searchSupplier_txt;
    @FXML
    private Label locLimit_txt;
    @FXML
    private Label currentItemCounInLocation_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpSuppTbl();
        displayCurrentDate();
        close_btn.setOnMouseClicked(e->close());
        loadAssetcount();
        save_btn.setOnMouseClicked(e->saveUpdateOrAddAction());
        
        purchPrice_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        itemStock_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        critLevel_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        //pam pa comma ng mga big numbers and sh1ts
        

        purchPrice_txt.setOnKeyReleased(value -> {
            if (purchPrice_txt.getText().isEmpty()) {
                purchPrice_txt.setText("");
            } else {
                purchPrice_txt.setText(NumberFormat.getInstance().format(Double.parseDouble(purchPrice_txt.getText().replace(",", ""))));
                purchPrice_txt.end();
            }
        });
        setCombox();
        itemUnit_txt.setOnAction(e->loop());
        itemLocation_txt.setDisable(true);
    }  
    
     public void setCombox(){
        loadEmployeeToCombx();
        loadItemUnitToCombox();
    }
    
    public void loadItemUnitToCombox(){
        
        Log1_WarehouseItemUnitModel unitDB = new Log1_WarehouseItemUnitModel();
        
        List itemUnit = unitDB.get();

        itemUnit.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            itemUnit_txt.getItems().add(String.valueOf(hash.get("itemUnit")));
        });
        
    }
    
    public void loop(){
        locLimit_txt.setText("0");
        currentItemCounInLocation_txt.setText("0");
        itemLocation_txt.setDisable(false);
        itemLocation_txt.getItems().clear();
        loadLocationToCombx();
    }
    
    public void loadLocationToCombx(){
        Log1_WarehouseItemLocationModel locDB = new Log1_WarehouseItemLocationModel();
        
        List location = locDB.where(new Object[][]{
            {"itemUnit", "=",itemUnit_txt.getSelectionModel().getSelectedItem().toString()}
        }).andWhere("Status", "=", "Not full").get();

        location.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            itemLocation_txt.getItems().add(String.valueOf(hash.get("locationCode")));
        });
    }
    
    @FXML
    private void loadCount(ActionEvent event) {
        Log1_WarehouseItemLocationModel locDB = new Log1_WarehouseItemLocationModel();
        List locCount = locDB.where(new Object[][]{
            {"locationCode", "=", itemLocation_txt.getSelectionModel().getSelectedItem().toString()}
        }).get();

        for(Object d : locCount){//rs = hm
            
            HashMap hm = (HashMap) d;   //exquisite casting
                
            locLimit_txt.setText(( 
                String.valueOf(hm.get("limit"))
            ));
            currentItemCounInLocation_txt.setText((
                String.valueOf(hm.get("currentItemCount"))
            ));
        }
    }
    
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//      get current date time with Date()
        Date date = new Date();
        dateItemAdded_txt.setText(dateFormat.format(date));
    }
    
    public void close(){
        clearFields();
        Stage stage = (Stage) purchPrice_txt.getScene().getWindow();
        stage.close();
    }
    
    
    public void setUpSuppTbl(){
        setPurchaseList();
        fetchPurchList();
    }
    
    public void setPurchaseList(){
        supplier_tbl.getItems().clear();
        supplier_tbl.getColumns().removeAll(supplier_tbl.getColumns());

        TableColumn<Log1_ProcurementSuppliersClassfiles, String> supplierName = new TableColumn<>("Supplier Name");

        supplierName.setCellValueFactory((param) -> param.getValue().SupplierName);

        supplier_tbl.getColumns().addAll(supplierName);
    }
    
    public void fetchPurchList(){
    Log1_ProcurementSupplierModel suppDB = new Log1_ProcurementSupplierModel();
    ObservableList<Log1_ProcurementSuppliersClassfiles> suppCF = FXCollections.observableArrayList(); 
        
    List b = suppDB.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                suppCF.add(new Log1_ProcurementSuppliersClassfiles(
                
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("SupplierName")),
                String.valueOf(hm.get("SupplierRepresentative")),
                String.valueOf(hm.get("SupplierContact")),
                String.valueOf(hm.get("SupplierEmail")),
                String.valueOf(hm.get("SupplierLocation")),
                String.valueOf(hm.get("ContractStarted")),
                String.valueOf(hm.get("ContractEnd")),
                String.valueOf(hm.get("AwardedBy")),
                String.valueOf(hm.get("Remarks")),
                String.valueOf(hm.get("SupplierStatus"))
                ));       
        }
        supplier_tbl.setItems(suppCF);
    }
    
    @FXML
    private void selectSupplier(MouseEvent event) {
        supplier_txt.setText(supplier_tbl.getSelectionModel().getSelectedItem().getSupplierName());
    }
    
    public void loadAssetcount(){
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetWarehouseCount_txt.setText((String.valueOf(hash.get("AssetWarehouseSupply"))));
        });
    }
    

    private void clearFields() {
        addedBy_txt.setValue("");
        itemName_txt.setText("");
        itemBrando_txt.setText("");
        description_txt.setText("");
        supplier_txt.setText("");
        itemLocation_txt.setDisable(true);
        SKU_txt.setText("");
        SerialNum_txt.setText("");
        itemUnit_txt.setValue("Select Unit");
        purchPrice_txt.setText("");
        itemStock_txt.setText("");
        critLevel_txt.setText("");
        locLimit_txt.setText("0");
        currentItemCounInLocation_txt.setText("0");
    }
    
    
    public void loadEmployeeToCombx(){
        HR4_EmployeeProfilesModel employeeDB = new HR4_EmployeeProfilesModel();
        
        List employeeProfile = employeeDB.get();

        employeeProfile.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            addedBy_txt.getItems().add(String.valueOf(hash.get("firstname")) + ", " + String.valueOf(hash.get("lastname")));
        });
    }
    
    
    private void saveUpdateOrAddAction() {
        String name = itemName_txt.getText();
        String brand = itemBrando_txt.getText();
        String loc = itemLocation_txt.getValue().toString();
        String sku = SKU_txt.getText();
        String serialn = SerialNum_txt.getText();
        String pprice = purchPrice_txt.getText();
        String unit = itemUnit_txt.getValue().toString();
        String stock = itemStock_txt.getText();
        String critlvl = critLevel_txt.getText();
        
        
        Boolean flag = name.isEmpty() || brand.isEmpty()
                || loc.isEmpty() || sku.isEmpty() || serialn.isEmpty()
                || pprice.isEmpty() || unit.isEmpty()
                || stock.isEmpty() || critlvl.isEmpty();
        
        if(isInEditMode){
            useUpdateMethod();
            return;
        }
        
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        String timeAdded = String.valueOf(timeFormat.format(cal.getTime()));
        
        Log1_WarehouseActivityLogModel ActLogDB = new Log1_WarehouseActivityLogModel();
        Log1_WarehouseItemModel itemsDB = new Log1_WarehouseItemModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        Log1_WarehouseItemLocationModel locDB = new Log1_WarehouseItemLocationModel();
        
        String a = locationCount_txt.getText();
        
        String currentItemCountOnThatLocation = String.valueOf(a+1);
        
        if(!flag){
            try{
                String[][] itemData ={
                {"ItemName",itemName_txt.getText()},
                {"SupplierID",supplier_tbl.getSelectionModel().getSelectedItem().getSupplierID()},
                {"ItemBrand",itemBrando_txt.getText()},
                {"ItemDescription",description_txt.getText()},
                {"ItemLocation",itemLocation_txt.getValue().toString()},
                {"ItemSKU",SKU_txt.getText()},
                {"ItemSerialNumber",SerialNum_txt.getText()},
                {"ItemPurchasedPrice",purchPrice_txt.getText()},
                {"ItemUnit", itemUnit_txt.getValue().toString()},
                {"ItemStock", itemStock_txt.getText()},
                {"ItemCriticalLevel", critLevel_txt.getText()},
                {"ItemRegisteredDate", dateItemAdded_txt.getText()},
                {"ItemRegisteredBy",addedBy_txt.getValue().toString()},
                };
                String[][] actLogData ={
                {"ActivityUser",addedBy_txt.getValue().toString()},
                {"ActivityItem",itemName_txt.getText()},
                {"ActivityItemStock","New item"},
                {"ActivityAction","Added New Item"},
                {"ActivityValue",itemStock_txt.getText()},
                {"ActivityItemStockRemaining",itemStock_txt.getText()},
                {"ActivityPurpose","New Item Register"},
                {"ActivityDate",dateItemAdded_txt.getText()},
                {"ActivityTime",timeAdded},
                };
                if(itemsDB.insert(itemData)&& ActLogDB.insert(actLogData)){
                    AlertMaker.showSimpleAlert("", itemName_txt.getText() +" has been Registered to Warehouse");
                    int x = Integer.parseInt(AssetWarehouseCount_txt.getText());
                    String ans = String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetWarehouseSupply",ans},
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                    locDB.update(new Object[][]{
                        {"currentItemCount", currentItemCountOnThatLocation}
                    }).where(new Object[][]{
                        {"locationCode", "=", itemLocation_txt.getValue().toString()}
                    }).executeUpdate();
                    clearFields();
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("","Please fill up the details");
        }
    }
    
    public void populateFieldsForUpdate(Log1_WarehouseItemsClassfiles item){
        itemID_txt.setText(item.getItemID());
        itemName_txt.setText(item.getItemName());
        itemBrando_txt.setText(item.getItemBrand());
        description_txt.setText(item.getItemDescription());
        itemLocation_txt.setValue(item.getItemLocation());
        SKU_txt.setText(item.getItemSKU());
        SerialNum_txt.setText(item.getItemSerialNumber());
        purchPrice_txt.setText(item.getItemPurchasedPrice());
        itemUnit_txt.setValue(item.getItemUnit());
        itemStock_txt.setText(item.getItemStock());
        critLevel_txt.setText(item.getItemCriticalLevel());
        dateItemAdded_txt.setVisible(false);
        dateToday_lbl.setVisible(false);
        save_btn.setText("Update");
        close_btn.setText("Cancel");
        register_lbl.setText("Update " + itemName_txt.getText());
        usertxt_lvl.setText("Updated by:");
        
        isInEditMode = Boolean.TRUE;
    }

    private void useUpdateMethod() {
        Log1_WarehouseItemModel itemDB = new Log1_WarehouseItemModel();
            try{
                if(itemDB.update(new Object[][]{ 
                    {"ItemName",itemName_txt.getText()},
                    {"ItemBrand",itemBrando_txt.getText()},
                    {"ItemDescription",description_txt.getText()},
                    {"ItemLocation",itemLocation_txt.getValue().toString()},
                    {"ItemSKU",SKU_txt.getText()},
                    {"ItemSerialNumber",SerialNum_txt.getText()},
                    {"ItemPurchasedPrice",purchPrice_txt.getText()},
                    {"ItemPriceCurrency", "Php"},
                    {"ItemUnit", itemUnit_txt.getValue().toString()},
                    {"ItemStock", itemStock_txt.getText()},
                    {"ItemCriticalLevel", critLevel_txt.getText()},
                    {"ItemRegisteredBy",addedBy_txt.getValue().toString()},
                    {"ItemRegisteredDate", dateItemAdded_txt.getText()},
                    {"ItemStatus", "Good on Stock"},
                 }).where(new Object[][]{
                     {"itemID","=",itemID_txt.getText()}
                }).executeUpdate()){
                     AlertMaker.showSimpleAlert("Update", ""+ itemName_txt.getText()+" has been updated!");
                     clearFields();
                     return;
                }else{
                     AlertMaker.showErrorMessage("Failed", ""+ itemName_txt.getText()+" has not been updated.");
                     return;
                }
            }catch(Exception e){
                e.printStackTrace();
        }
    }

    

    @FXML
    private void searchSupplier(ActionEvent event) {
    }

    
    
    
    
}
