/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ABforEquipmentClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetVehiclesModel;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class AddVehicleController implements Initializable {
    
    ObservableList<String> VehicleType = FXCollections.observableArrayList(
            "Company Vehicle", 
            "Delivery Vehicle");

    @FXML
    private TextField vehicleName_txt;
    private ComboBox<String> vehicleTypeCombox;
    @FXML
    private TextField location_txt;
    @FXML
    private TextField modelNumber_txt;
    private DatePicker dateBought_txt;
    private TextField price_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    private Label buildingID_txt;
    private Label buildingName_txt;
    @FXML
    private TextField manufacturer_txt;
    private DatePicker warrantyDate_txt;
    private TableView<Log1_ABforEquipmentClassfiles> building_tbl;
    private TableColumn<Log1_ABforEquipmentClassfiles, String> id_col;
    private TableColumn<Log1_ABforEquipmentClassfiles, String> buildingName_col;
    private TableColumn<Log1_ABforEquipmentClassfiles, String> buildingAddress_col;
    @FXML
    private TextField chassisNumber_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        vehicleTypeCombox.setItems(VehicleType);
//        // TODO
//        save_btn.setOnMouseClicked(e -> saveEquipment());
//        callBuildingData();
//        displayBuildingData();
    }   
    
    private void clear() {
        vehicleName_txt.setText("");
        location_txt.setText("");
        modelNumber_txt.setText("");
        chassisNumber_txt.setText("");
        price_txt.setText("");
        vehicleTypeCombox.setValue("Select Equipment Type");
        buildingID_txt.setText("");
        buildingName_txt.setText("");
        warrantyDate_txt.getEditor().setText("");
    }
    
    public void saveEquipment(){
        String Name = vehicleName_txt.getText();
        String Location = location_txt.getText();
        String Model = modelNumber_txt.getText();
        String Serial = chassisNumber_txt.getText();
        String price = price_txt.getText();
        
        Boolean flag =  Name.isEmpty() ||
                        Location.isEmpty()||
                        Model.isEmpty()||
                        Serial.isEmpty()||
                        price.isEmpty();
        
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_AssetVehiclesModel et = new Log1_AssetVehiclesModel();
        try{String [][] et_table ={
                {"VehicleDescription",vehicleName_txt.getText()},
                {"VehicleType",vehicleTypeCombox.getValue()},
                {"VehicleModel",modelNumber_txt.getText()},
                {"VehiclePrice",price_txt.getText()},
                {"ChassisNumber",chassisNumber_txt.getText()},
                {"DateBought",dateBought_txt.getEditor().getText()},
                {"VehicleLocation",location_txt.getText()},
                {"VehicleManufacturer",manufacturer_txt.getText()},
                {"VehicleWarranty",warrantyDate_txt.getEditor().getText()},
                {"VehicleStatus","Not in used"}
            };
            if(et.insert(et_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ vehicleName_txt.getText() +" has been added to Equipment's Database");
                clear();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
        public void callBuildingData(){
         Log1_AssetBuildingModel coa = new Log1_AssetBuildingModel();
         ObservableList<Log1_ABforEquipmentClassfiles> building = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                building.add(new Log1_ABforEquipmentClassfiles(
                
                String.valueOf(hm.get("BuildingID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("BuildingAddress"))
                ));       
        }
        building_tbl.setItems(building);
    }
    public void displayBuildingData(){
        id_col.setCellValueFactory(new PropertyValueFactory<>("BuildingID"));
        buildingName_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        buildingAddress_col.setCellValueFactory(new PropertyValueFactory<>("BuildingAddress"));
    }
    
    private void selectBuildingForEquipmenthehexd(MouseEvent event) {
        buildingID_txt.setText(building_tbl.getSelectionModel().getSelectedItem().getBuildingID());
        buildingName_txt.setText(building_tbl.getSelectionModel().getSelectedItem().getBuildingName());
    }
    
}
