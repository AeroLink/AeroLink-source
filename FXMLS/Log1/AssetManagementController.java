
package FXMLS.Log1;


import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetEquipmentClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetVehiclesClassfiles;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetVehiclesModel;
import Synapse.Model;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class AssetManagementController implements Initializable {
    
    ObservableList<String> selectAssetType = FXCollections.observableArrayList(
            "Land",
            "Building",
            "Facility",
            "Vehicle");
   
    @FXML
    private AnchorPane registrationForm_pane;
    @FXML
    private ComboBox<String> selectAssetType_combox;
    @FXML
    private Label registerCategory_txt;
    @FXML
    private BorderPane computeAppraisalValuePane;
    @FXML
    private Label title_txt;
    @FXML
    private BorderPane computeDepreciationValuePane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectAssetType_combox.setItems(selectAssetType);
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
//            building_tbl.setItems(building);
    }
    
    public void displayBuildingData(){
//        buildingName_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
//        buildingAddress_col.setCellValueFactory(new PropertyValueFactory<>("BuildingAddress"));
//        buildingContact_col.setCellValueFactory(new PropertyValueFactory<>("BuildingContact"));
//        buildingStatus_col.setCellValueFactory(new PropertyValueFactory<>("BuildingStatus"));
//        buildingType_col.setCellValueFactory(new PropertyValueFactory<>("BuildingFacilityType"));
    }
    
    private void updateBuildingAction(ActionEvent event) {
//        Log1_AssetBuildingClassfiles selectedForEdit = building_tbl.getSelectionModel().getSelectedItem();
//        if(selectedForEdit == null){
//            AlertMaker.showErrorMessage("No Building Selected","Please Select A Building From the table to proceed");
//            return;
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
//            Parent parent = loader.load();
//            
//            AddBuildingController controller = (AddBuildingController) loader.getController();
//            controller.displayBuildingData(selectedForEdit);
//            
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("Update Building Details");
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(AssetManagementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

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
//        equip_tbl.setItems(Equipment);
    }
    
    public void displayEquipmentData(){
//        equipment_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));
//        equipmentType_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentType"));
//        EWarrantydate_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentWarrantyDate"));
//        equipmentlocation_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentLocation"));
//        equipmentBuilding_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
//        equipmentPrice_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentPrice"));
    }
    
    private void addEquipActon(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddNewEquipment.fxml"),
                 "Add New Equipment", null);
    }


    private void addVehicleAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddVehicle.fxml"),
                 "Add New Equipment", null);
    }

    public void callVehicleData() {
        Log1_AssetVehiclesModel AE = new Log1_AssetVehiclesModel();
         ObservableList<Log1_AssetVehiclesClassfiles> vehicles = FXCollections.observableArrayList();
          
            List b = AE.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                vehicles.add(new Log1_AssetVehiclesClassfiles(
                
                String.valueOf(hm.get("VehicleID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("VehicleDescription")),
                String.valueOf(hm.get("VehicleType")),
                String.valueOf(hm.get("VehiclePrice")),
                String.valueOf(hm.get("ChassisNumber")),
                String.valueOf(hm.get("DateBought")),
                String.valueOf(hm.get("VehicleLocation")),
                String.valueOf(hm.get("VehicleManufacturer")),
                String.valueOf(hm.get("VehicleWarranty")),
                        String.valueOf(hm.get("VehicleStatus"))
                ));       
        }
//        vehicle_tbl.setItems(vehicles);
        
    }

    public void displayVehicleDate() {
//        vehicle_col.setCellValueFactory(new PropertyValueFactory<>("VehicleDescription"));
//        Vehicletype_col.setCellValueFactory(new PropertyValueFactory<>("VehicleType"));
//        VehiclePrice_col.setCellValueFactory(new PropertyValueFactory<>("VehiclePrice"));
//        vbuilding_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
//        vlocation_col.setCellValueFactory(new PropertyValueFactory<>("VehicleLocation"));
//        vstatus_col.setCellValueFactory(new PropertyValueFactory<>("VehicleStatus"));
    }

    @FXML
    private void showRegisForm(ActionEvent event) throws IOException {
        String AssetType = selectAssetType_combox.getValue();
        
        if(AssetType=="Building"){
            registerCategory_txt.setText("Register Building");
            registerCategory_txt.setVisible(true);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
            registrationForm_pane.getChildren().setAll(pane);
        }
        else if(AssetType =="Vehicle"){
            registerCategory_txt.setText("Register Vehicle");
            registerCategory_txt.setVisible(true);
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddVehicle.fxml"));
            registrationForm_pane.getChildren().setAll(pane1);
        }
        else if(AssetType =="Land"){
            registerCategory_txt.setText("Register Land");
            registerCategory_txt.setVisible(true);
            AnchorPane pane2 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddLand.fxml"));
            registrationForm_pane.getChildren().setAll(pane2);
        }
        else if(AssetType =="Facility"){
            registerCategory_txt.setText("Register Facility");
            registerCategory_txt.setVisible(true);
            AnchorPane pane3 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddFacility.fxml"));
            registrationForm_pane.getChildren().setAll(pane3);
        }
    }

    @FXML
    private void selectAssetForAppraisal(ActionEvent event) {
        computeAppraisalValuePane.setVisible(true);
        computeAppraisalValuePane.setDisable(false);
        computeDepreciationValuePane.setVisible(false);
        computeDepreciationValuePane.setDisable(true);
        title_txt.setText("Compute Appraisal Value");
    }

    @FXML
    private void selectAssetForDepreciation(ActionEvent event) {
        computeDepreciationValuePane.setVisible(true);
        computeDepreciationValuePane.setDisable(false);
        computeAppraisalValuePane.setVisible(false);
        computeAppraisalValuePane.setDisable(true);
        title_txt.setText("Compute Depreciation Value");
    }

    
}
