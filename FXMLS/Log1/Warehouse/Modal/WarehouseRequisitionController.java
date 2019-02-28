package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_DepartmentModel;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_WarehouseItemModel;
import Model.Log1.Log1_WarehouseRequestItemModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WarehouseRequisitionController implements Initializable {
    
    ObservableList<String> PriorityLvl = 
    FXCollections.observableArrayList("3 - Low","2 - Priority","1 - Emergency");


    @FXML
    private TableView<Log1_WarehouseItemsClassfiles> item_tbl;
    @FXML
    private ComboBox<String> priority_combox;
    @FXML
    private TextField item_txt;
    @FXML
    private TextField quantity_txt;
    @FXML
    private JFXButton submit_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TextField requestTitle_txt;
    @FXML
    private TextField unit_txt;
    @FXML
    private Label dateToday_lbl;
    @FXML
    private TextField search_txt;
    @FXML
    private JFXButton search_btn;
    @FXML
    private TextField firstname_txt;
    @FXML
    private TextField lastname_txt;
    @FXML
    private TextArea reason_txt;
    @FXML
    private Label itemId_txt;
    @FXML
    private ComboBox department_txt;
    @FXML
    private ComboBox location_combx;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private ToggleGroup R;
    @FXML
    private Label radiobtn_lbl;
    @FXML
    private RadioButton pickup;
    @FXML
    private RadioButton Delivery;
    @FXML
    private Label loc_lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priority_combox.setItems(PriorityLvl);
        refresh();
        displayCurrentDate();
        cancel_btn.setOnMouseClicked(e->close());
        submit_btn.setOnMouseClicked(e->sendRequest());
        loadBuildingToCombox();
        search_btn.setOnMouseClicked(e->search());
        refresh_btn.setOnMouseClicked(e->refresh());
        location_combx.setDisable(true);
        loc_lbl.setDisable(true);
        location_combx.setValue("");
        pickup.setOnAction(e-> {
            radiobtn_lbl.setText("Pick Up");
            location_combx.setValue("None");
            location_combx.setDisable(true);
            loc_lbl.setDisable(true);
        });
        Delivery.setOnAction(e-> {
            radiobtn_lbl.setText("Delivery");
            location_combx.setValue("Select location");
            location_combx.setDisable(false);
            loc_lbl.setDisable(false);
        });
        quantity_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        loadDepartmentToCombox();
    }

    public void refresh(){
        renderItemListTbl();
        populateItemTable();
    }
    
    
    
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            location_combx.getItems().add(String.valueOf(hash.get("bAssetTitle")) + " - " + String.valueOf(hash.get("bAssetCoreLocation")));
        });
    }
    
    public void validateAreaSqPerMeters(){
        String area = quantity_txt.getText();
        
        if(area.matches("[a-z]")){
            AlertMaker.showErrorMessage("Invalid", "Only Numbers are acceptable in this field");
            quantity_txt.setText("");
        }
    }
     
    public void displayCurrentDate(){
     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    get current date time with Date()
        Date date = new Date();
        dateToday_lbl.setText(dateFormat.format(date));
    }
    
    public void close(){
        clearFields();
        Stage stage = (Stage) dateToday_lbl.getScene().getWindow();
        stage.close();
    }
    
    public void loadDepartmentToCombox(){
        HR4_DepartmentModel assetBuilding = new HR4_DepartmentModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            department_txt.getItems().add(String.valueOf(hash.get("dept_name")));
        });
    }
    
    
    public void renderItemListTbl(){
        item_tbl.getItems().clear();
        item_tbl.getColumns().removeAll(item_tbl.getColumns());

        TableColumn<Log1_WarehouseItemsClassfiles, String> item = new TableColumn<>("Item");

        item.setCellValueFactory((param) -> param.getValue().ItemName);

        item_tbl.getColumns().addAll(item);
    }
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

    @FXML
    private void selectoItemo(MouseEvent event) {
        itemId_txt.setText(item_tbl.getSelectionModel().getSelectedItem().getItemID());
        item_txt.setText(item_tbl.getSelectionModel().getSelectedItem().getItemName());
        unit_txt.setText(item_tbl.getSelectionModel().getSelectedItem().getItemUnit()); 
    }

    private void clearFields() {
        requestTitle_txt.setText("");
        firstname_txt.setText("");
        lastname_txt.setText("");
        reason_txt.setText("");
        item_txt.setText("");
        unit_txt.setText("");
        quantity_txt.setText("");
        priority_combox.setValue("");
        location_combx.setValue("");
    }
    
    public void sendRequest(){
        String title = requestTitle_txt.getText();
        String fname = firstname_txt.getText();
        String lname = lastname_txt.getText();
        String reason = reason_txt.getText();
        String item = item_txt.getText();
        String unit = unit_txt.getText();
        String qty = quantity_txt.getText();
        
        Boolean flag = title.isEmpty() || fname.isEmpty() || lname.isEmpty() ||
                reason.isEmpty() || item.isEmpty() || unit.isEmpty()
                || qty.isEmpty();
        
        Log1_WarehouseRequestItemModel whRqDB = new Log1_WarehouseRequestItemModel();
        if(!flag){
            try{
                String[][] reqData ={
                    {"ItemID", itemId_txt.getText()},
                    {"RequestTitle", requestTitle_txt.getText()},
                    {"Requestor", firstname_txt.getText() + "," + lastname_txt.getText()},
                    {"RequestReason", reason_txt.getText()},
                    {"RequestQuantity", quantity_txt.getText()},
                    {"RequestPriorityLevel", priority_combox.getValue()},
                    {"RequestorDepartment", department_txt.getValue().toString()},
                    {"RequestorLocation",location_combx.getValue().toString()},
                    {"DateRequested",dateToday_lbl.getText()},
                    {"RequestStatus","Pending"},
                    {"TermsOfRecieving",radiobtn_lbl.getText()},
                    {"ApprovedRequestRemarks","None"},
                    {"DateApproved","None"},
                    {"TimeApproved","None"},
                    {"Packager","None"},
                    {"RecievedBy","None"},
                    {"DateRecieved","None"},
                };
                if(whRqDB.insert(reqData)){
                    AlertMaker.showSimpleAlert("", "Request has been sent!");
                    clearFields();
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("","fill up details");
        }
    
    }
    
    
}
