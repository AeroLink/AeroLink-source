
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_SupplierClassfiles;
import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Model.Log1.Log1_SupplierModel;
import Model.Log1.Log1_WarehouseItemsModel;
import Model.Log1.Log1_WarehouseDesiredItemTypeModel;
import Model.Log1.Log1_WarehouseDesiredItemUnitModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddItemOnWarehouseController implements Initializable {
    
    private boolean ifForUpdate = false;
    
    @FXML
    private TextField itemDescription_txt;
    @FXML
    private ComboBox itemType_combox;
    @FXML
    private TextField itemLoc_txt;
    @FXML
    private ComboBox itemUnit_combox;
    @FXML
    private TextField unitPrice_txt;
    @FXML
    private TextField quantityStocked_txt;
    @FXML
    private TextField reorderQuantity_txt;
    @FXML
    private DatePicker disposalDate_datepick;
    @FXML
    private JFXTextField cantDoBooleanSoFuckThisShit;
    @FXML
    private JFXButton saveItem_btn;
    @FXML
    private JFXTextField itemID_txt;
    @FXML
    private TableView<Log1_SupplierClassfiles> Supplier_tbl;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> name_col;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> contact_col;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> address_col;
    @FXML
    private TableColumn<Log1_SupplierClassfiles, String> representative_col;
    @FXML
    private Label supp_id;
    private JFXTextField desiredItemType_txt;
    @FXML
    private Label supplierName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveItem_btn.setOnMouseClicked(e -> saveUpdateOrAddAction());
        cantDoBooleanSoFuckThisShit.setText("");
        itemUnit_combox.setValue("Select Unit");
        itemType_combox.setValue("Select Item Type");
        callSupplierData();
        displaySupplierData();
        loadItemUnitToCombox();
        loadItemTypeToCombox();
        
    }  
    
    public void callSupplierData(){
         Log1_SupplierModel coa = new Log1_SupplierModel();
         ObservableList<Log1_SupplierClassfiles> supplier = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               supplier.add(new Log1_SupplierClassfiles(
                
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("SupplierName")),
                String.valueOf(hm.get("SupplierAddress")),
                String.valueOf(hm.get("SupplierContact")),
                String.valueOf(hm.get("SupplierRepresentative"))
                ));       
        }
        Supplier_tbl.setItems(supplier);
    }
    
    public void displaySupplierData(){
        name_col.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        contact_col.setCellValueFactory(new PropertyValueFactory<>("SupplierContact"));
        representative_col.setCellValueFactory(new PropertyValueFactory<>("SupplierRepresentative"));
    }
    
    public void loadItemUnitToCombox(){
        Log1_WarehouseDesiredItemUnitModel iu = new Log1_WarehouseDesiredItemUnitModel();
        List itemUnit = iu.get();

        itemUnit.stream().forEach(row -> {
            HashMap hash = (HashMap) row;

            itemUnit_combox.getItems().add(String.valueOf(hash.get("DesiredItemUnit")));
        });
    }
    public void loadItemTypeToCombox(){
        Log1_WarehouseDesiredItemTypeModel it = new Log1_WarehouseDesiredItemTypeModel();
        List itemUnit = it.get();

        itemUnit.stream().forEach(row -> {
            HashMap hash = (HashMap) row;

            itemType_combox.getItems().add(String.valueOf(hash.get("DesiredItemType")));
        });
    }
    

    @FXML
    private void handleCloseAction() {
        Stage stage = (Stage) itemDescription_txt.getScene().getWindow();
        stage.close();
    }

    private void saveUpdateOrAddAction() {
        String description = itemDescription_txt.getText();
        String location = itemLoc_txt.getText();
        String price = unitPrice_txt.getText();
        String quantity = quantityStocked_txt.getText();
        String reorder = reorderQuantity_txt.getText();
        
        
        Boolean flag = description.isEmpty() || location.isEmpty()
                || price.isEmpty() || quantity.isEmpty() || reorder.isEmpty();
        
        if(ifForUpdate){
            useUpdateMethod();
            return;
        }
        Log1_WarehouseActivityLogModel coa2 = new Log1_WarehouseActivityLogModel();
        Log1_WarehouseItemsModel coa = new Log1_WarehouseItemsModel();
        if(!flag){
            try{
                String[][] coa_table ={
                {"ItemDescription",itemDescription_txt.getText()},
                {"SupplierID",supp_id.getText()},
                {"ItemLocation",itemLoc_txt.getText()},
                {"ItemType",itemType_combox.getValue().toString()},
                {"ItemUnit",itemUnit_combox.getValue().toString()},
                {"UnitPrice","Php" + unitPrice_txt.getText()+""},
                {"StockQuantity",quantityStocked_txt.getText()},
                {"CriticalQuantity",reorderQuantity_txt.getText()},
                {"DisposalDate",disposalDate_datepick.getEditor().getText()},
                {"Status","Good on stock"},
                {"ShowInMainWindow", "yes"}
                }; 
                    if(coa.insert(coa_table)){
                        AlertMaker.showSimpleAlert("Saved", ""+ itemDescription_txt.getText() +" has been saved");
                        coa2.insert(new String [][]{
                            {"ActivityItemName",itemDescription_txt.getText()},
                            {"ActivityUser", "rb"},
                            {"ActivityAction", "New Item Added"},
                            {"ActivityValueAddedOrRemoved", quantityStocked_txt.getText()}
                            });
                        clear();
                        return;
                        }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("please fill up all the details");
            alert.showAndWait();
            return;
        }
    }
    
    public void clear(){
        itemDescription_txt.setText("");
        itemType_combox.setValue("");
        itemLoc_txt.setText("");
        itemUnit_combox.setValue("");
        unitPrice_txt.setText("");
        quantityStocked_txt.setText("");
        reorderQuantity_txt.setText("");
        disposalDate_datepick.getEditor().setText("");
    }
    
    public void displaySelectedIndex(Log1_fullInventoryList item){
        itemID_txt.setText(item.getItemID());
        itemDescription_txt.setText(item.getItemDescription());
        itemType_combox.setValue(item.getItemType());
        itemLoc_txt.setText(item.getItemLocation());
        itemUnit_combox.setValue(item.getItemUnit());
        unitPrice_txt.setText(item.getUnitPrice());
        quantityStocked_txt.setText(item.getStockQuantity());
        reorderQuantity_txt.setText(item.getCriticalQuantity());
        disposalDate_datepick.getEditor().setText(item.getDisposalDate());
        cantDoBooleanSoFuckThisShit.setText(item.getStatus());
        
        ifForUpdate = Boolean.TRUE;
    }
    
    private void useUpdateMethod() {
        Log1_WarehouseItemsModel wh = new Log1_WarehouseItemsModel();
            try{
                if(wh.update(new Object[][]{ 
                    {"ItemDescription",itemDescription_txt.getText()},
                    {"SupplierID",supp_id.getText()},
                    {"ItemType",itemType_combox.getValue()},
                    {"ItemLocation",itemLoc_txt.getText()},
                    {"ItemUnit",itemUnit_combox.getValue()},
                    {"UnitPrice",unitPrice_txt.getText()},
                    {"StockQuantity",quantityStocked_txt.getText()},
                    {"CriticalQuantity",reorderQuantity_txt.getText()},
                    {"DisposalDate",disposalDate_datepick.getEditor().getText()},
                    {"Status",cantDoBooleanSoFuckThisShit.getText()},
                 }).where(new Object[][]{
                     {"itemID","=",itemID_txt.getText()}
                }).executeUpdate()){
                     AlertMaker.showSimpleAlert("Update", ""+ desiredItemType_txt.getText()+" has been updated!");
                }else{
                     AlertMaker.showErrorMessage("Failed", ""+ desiredItemType_txt.getText()+" has not been updated.");
                }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
    }
    
    @FXML
    private void supplierClickEvent(MouseEvent event) {
        supp_id.setText(Supplier_tbl.getSelectionModel().getSelectedItem().getSupplierID());
        supplierName.setText(Supplier_tbl.getSelectionModel().getSelectedItem().getSupplierName());
    }

    @FXML
    private void showSetUnit(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ItemUnit.fxml"),
                 "Set Item Unit", null);
    }

    @FXML
    private void showSetType(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ItemType.fxml"),
                 "Set Item Type", null);
    }
}
