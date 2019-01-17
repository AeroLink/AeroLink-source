
package FXMLS.Log1;


import FXMLS.Log1.AssetManagement.Modal.AddBuildingController;
import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetEquipmentClassfiles;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetEquipmentModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class AssetManagementController implements Initializable {

    
    @FXML
    private TableView<Log1_AssetBuildingClassfiles> building_tbl;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> buildingName_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> buildingAddress_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> buildingContact_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> buildingType_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> buildingStatus_col;
    
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> equip_tbl;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipment_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipmentType_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipmentBuilding_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipmentlocation_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> equipmentPrice_col;
    @FXML
    private TableColumn<Log1_AssetEquipmentClassfiles, String> EWarrantydate_col;
    
    @FXML
    private TableView<?> vehicle_tbl;
    @FXML
    private TableColumn<?, ?> vehicle_col;
    @FXML
    private TableColumn<?, ?> Vehicletype_col;
    @FXML
    private TableColumn<?, ?> model_col;
    @FXML
    private TableColumn<?, ?> VehiclePrice_col;
    @FXML
    private TableColumn<?, ?> vbuilding_col;
    @FXML
    private TableColumn<?, ?> vlocation_col;
    @FXML
    private TableColumn<?, ?> vstatus_col;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        callBuildingData();
        displayBuildingData();
        callEquipmentData();
        displayEquipmentData();
    } 
    
    public void callBuildingData(){
         Log1_AssetBuildingModel Building = new Log1_AssetBuildingModel();
         ObservableList<Log1_AssetBuildingClassfiles> building = FXCollections.observableArrayList();
          
            List b = Building.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               building.add(new Log1_AssetBuildingClassfiles(
                
                String.valueOf(hm.get("BuildingID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("BuildingAddress")),
                String.valueOf(hm.get("BuildingContact")),
                String.valueOf(hm.get("BuildingArea")),
                String.valueOf(hm.get("YearBuilt")),
                String.valueOf(hm.get("BuildingStatus")),
                String.valueOf(hm.get("BuildingFacilityType"))
                ));       
            }
            building_tbl.setItems(building);
    }
    
    public void displayBuildingData(){
        buildingName_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        buildingAddress_col.setCellValueFactory(new PropertyValueFactory<>("BuildingAddress"));
        buildingContact_col.setCellValueFactory(new PropertyValueFactory<>("BuildingContact"));
        buildingStatus_col.setCellValueFactory(new PropertyValueFactory<>("BuildingStatus"));
        buildingType_col.setCellValueFactory(new PropertyValueFactory<>("BuildingFacilityType"));
    }
    
    @FXML
    private void updateBuildingAction(ActionEvent event) {
        Log1_AssetBuildingClassfiles selectedForEdit = building_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("No Building Selected","Please Select A Building From the table to proceed");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
            Parent parent = loader.load();
            
            AddBuildingController controller = (AddBuildingController) loader.getController();
            controller.displayBuildingData(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Update Building Details");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AssetManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AddBuildingAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"),
                 "Add New Building", null);
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
        equipmentType_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentType"));
        EWarrantydate_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentWarrantyDate"));
        equipmentlocation_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentLocation"));
        equipmentBuilding_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        equipmentPrice_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentPrice"));
    }
    
    @FXML
    private void addEquipActon(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddNewEquipment.fxml"),
                 "Add New Equipment", null);
    }

    @FXML
    private void editEquipAction(ActionEvent event) {
    }

    
}
