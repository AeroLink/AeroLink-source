/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetEquipmentClassfiles;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetEquipmentModel;
import Synapse.Model;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ViewEquipmentTableController implements Initializable {

    @FXML
    private JFXTextField search_txt;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> equip_tbl;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipment_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> type_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> building_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> location_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> modelNumber_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> serial_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> warrantyDate_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> price_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        callEquipmentData();
        displayEquipmentData();
    }    

    @FXML
    private void addEquipActon(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddNewEquipment.fxml"),
                 "Add New Equipment", null);
    }

    @FXML
    private void editEquipAction(ActionEvent event) {
    }
    
    public void callEquipmentData(){
         Log1_AssetEquipmentModel AE = new Log1_AssetEquipmentModel();
         ObservableList<Log1_AssetEquipmentClassfiles> Equipment = FXCollections.observableArrayList();
          
            List b = AE.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Equipment.add(new Log1_AssetEquipmentClassfiles(
                
                String.valueOf(hm.get("EquipmentID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("EquipmentName")),
                String.valueOf(hm.get("EquipmentType")),
                String.valueOf(hm.get("EquipmentLocation")),
                String.valueOf(hm.get("EquipmentModelNumber")),
                String.valueOf(hm.get("EquipmentSerialNumber")),
                String.valueOf(hm.get("EquipmentWarrantyDate")),
                String.valueOf(hm.get("EquipmentPrice"))
                ));       
        }
        equip_tbl.setItems(Equipment);
    }
    
    public void displayEquipmentData(){
        equipment_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentType"));
        location_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentLocation"));
        modelNumber_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentModelNumber"));
        serial_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentSerialNumber"));
        warrantyDate_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentWarrantyDate"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentPrice"));
        building_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
    }
}
