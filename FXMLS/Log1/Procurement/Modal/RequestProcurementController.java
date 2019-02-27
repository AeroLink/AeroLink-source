/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_DepartmentModel;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_ProcurementNewItemModel;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class RequestProcurementController implements Initializable {
    
    ObservableList<String> PriorityLvl = 
    FXCollections.observableArrayList("3 - Low","2 - Priority","1 - Emergency");

    @FXML
    private Label date_lbl;
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
    private ComboBox department_combox;
    @FXML
    private ComboBox location_combox;
    @FXML
    private TextArea itemDescription_txt;
    @FXML
    private Label time_lbl;
    @FXML
    private VBox department_tab;
    @FXML
    private TextField unit_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCurrentDate();
        priorityLvl_combox.setItems(PriorityLvl);
        loadBuildingToCombox();
        loadDepartmentToCombox();
        cancero_btn.setOnMouseClicked(e->close());
        submitoRequesto_btn.setOnMouseClicked(e->submit());
        
        qty_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        qty_txt.setOnKeyReleased(value ->{
        if (qty_txt.getText().isEmpty()) {
            qty_txt.setText("");
            }else{
            qty_txt.setText(NumberFormat.getInstance().format(Long.parseLong(qty_txt.getText().replace(",", ""))));
            qty_txt.end();
            }
        });
    }    
    
    public void close(){
        clearAllfields();
        Stage stage = (Stage) priorityLvl_combox.getScene().getWindow();
        stage.close();
    }
    
    public void clearAllfields(){
        itemProcuring_txt.setText("");
        unit_txt.setText("");
        qty_txt.setText("");
        itemDescription_txt.setText("");
        requestTitle_txt.setText("");
        firstName_txt.setText("");
        lastName_txt.setText("");
        department_combox.setValue("");
        location_combox.setValue("");
        youAreTheReason_txt.setText("");
        priorityLvl_combox.setValue("");
    }
    
    public void clearFields(){
        itemProcuring_txt.setText("");
        unit_txt.setText("");
        qty_txt.setText("");
        itemDescription_txt.setText("");
        priorityLvl_combox.setValue("");
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
                location.isEmpty() ||
                plevel.isEmpty() ||
                reason.isEmpty() ||
                item.isEmpty() ||
                qty.isEmpty() ||
                itemdescript.isEmpty();
        
        Log1_ProcurementNewItemModel procDB = new Log1_ProcurementNewItemModel();
        
        if(flag){
            AlertMaker.showErrorMessage("","Please Fill up all the fields");
        }else if(!flag){
            try{
                String[][] reqData ={
                    {"RequestTitle", requestTitle_txt.getText()},
                    {"DateRequested",date_lbl.getText()},
                    {"Requestor", firstName_txt.getText() + ", " + lastName_txt.getText()},
                    {"Department", department_combox.getValue().toString()},
                    {"Location", location_combox.getValue().toString()},
                    {"RequestReason", youAreTheReason_txt.getText()},
                    {"PriorityLevel", priorityLvl_combox.getValue()},
                    {"ItemName", itemProcuring_txt.getText()},
                    {"ItemUnit", unit_txt.getText()},
                    {"Quantity",qty_txt.getText()},
                    {"ItemDescription",itemDescription_txt.getText()},
                    {"RequestStatus","Pending"},
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
}
