/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ABforEquipmentClassfiles;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_equipmentTypeModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class AddNewEquipmentController implements Initializable {

    @FXML
    private TextField equipmentName_txt;
    @FXML
    private TextField location_txt;
    @FXML
    private TextField modelNumber_txt;
    @FXML
    private TextField serialNumber_txt;
    @FXML
    private DatePicker warrantyDate_txt;
    @FXML
    private TextField price_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private Label buildingID_txt;
    @FXML
    private Label buildingName_txt;
    @FXML
    private TableView<Log1_ABforEquipmentClassfiles> building_tbl;
    @FXML
    private TableColumn<Log1_ABforEquipmentClassfiles, String> id_col;
    @FXML
    private TableColumn<Log1_ABforEquipmentClassfiles, String> buildingName_col;
    @FXML
    private TableColumn<Log1_ABforEquipmentClassfiles, String> buildingAddress_col;
    @FXML
    private ComboBox EquipmentTypeCombox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        save_btn.setOnMouseClicked(e -> saveEquipment());
        cancel_btn.setOnMouseClicked(e -> closeAction());
        callBuildingData();
        displayBuildingData();
        loadItemTypeToCombox();
    }
    
    private void clear() {
        equipmentName_txt.setText("");
        location_txt.setText("");
        modelNumber_txt.setText("");
        serialNumber_txt.setText("");
        price_txt.setText("");
        EquipmentTypeCombox.setValue("Select Equipment Type");
        buildingID_txt.setText("");
        buildingName_txt.setText("");
        warrantyDate_txt.getEditor().setText("");
    }
    
    public void saveEquipment(){
        String Name = equipmentName_txt.getText();
        String Location = location_txt.getText();
        String Model = modelNumber_txt.getText();
        String Serial = serialNumber_txt.getText();
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
        Log1_AssetEquipmentModel et = new Log1_AssetEquipmentModel();
        try{String [][] et_table ={
                {"EquipmentName",equipmentName_txt.getText()},
                {"EquipmentLocation",location_txt.getText()},
                {"EquipmentModelNumber",modelNumber_txt.getText()},
                {"EquipmentSerialNumber",serialNumber_txt.getText()},
                {"EquipmentPrice",price_txt.getText()},
                {"EquipmentType",EquipmentTypeCombox.getValue().toString()},
                {"EquipmentWarrantyDate",warrantyDate_txt.getEditor().getText()},
                {"BuildingID",buildingID_txt.getText()}
            };
            if(et.insert(et_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ equipmentName_txt.getText() +" has been added to Equipment's Database");
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

    @FXML
    private void selectBuildingForEquipmenthehexd(MouseEvent event) {
        buildingID_txt.setText(building_tbl.getSelectionModel().getSelectedItem().getBuildingID());
        buildingName_txt.setText(building_tbl.getSelectionModel().getSelectedItem().getBuildingName());
    }

    @FXML
    private void setEquipmentType(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/setEquipmentType.fxml"),
                 "Set Equipment Type", null);
    }
    
    public void loadItemTypeToCombox(){
        Log1_equipmentTypeModel it = new Log1_equipmentTypeModel();
        List itemType = it.get();

        itemType.stream().forEach(row -> {
            HashMap hash = (HashMap) row;

            EquipmentTypeCombox.getItems().add(String.valueOf(hash.get("EquipmentTypeDesired")));
        });
    }

    private void closeAction() {
        Stage stage = (Stage) EquipmentTypeCombox.getScene().getWindow();
        stage.close();
    }


}
