package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetDisposalClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetEquipmentClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetFacilityClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetLandClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetVehiclesClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetDisposalModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetFacilityModel;
import Model.Log1.Log1_AssetLandModel;
import Model.Log1.Log1_AssetVehiclesModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class AssetManagementController implements Initializable {
    
        ObservableList<String> selectAssetType = FXCollections.observableArrayList("Land","Building","Facility","Vehicle","Equipment");
        ObservableList<String> AllAssetForViewing = FXCollections.observableArrayList("Land","Building","Facility","Vehicle","Equipment","Supplies");
    ObservableList<String> AssetForDisposal = FXCollections.observableArrayList("Equipment","Vehicle","Supply");
    
    Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
    @FXML
    private AnchorPane registrationForm_pane;
    @FXML
    private ComboBox<String> selectAssetType_combox;
    @FXML
    private BorderPane computeAppraisalValuePane;
    @FXML
    private AnchorPane assetTable_pane;
    @FXML
    private TableView<Log1_AssetLandClassfiles> land_tbl;
    @FXML
    private TableView<Log1_AssetBuildingClassfiles> building_tbl;
    @FXML
    private TableView<Log1_AssetFacilityClassfiles> facility_tbl;
    @FXML
    private TableView<Log1_AssetVehiclesClassfiles> Vehicle_tbl;
    @FXML
    private ComboBox<String> selectAssetForMonitoring_combox;
    @FXML
    private JFXTextField searchLand_txt;
    @FXML
    private TableView<Log1_AssetLandClassfiles> MonitoringLand_tbl;
    @FXML
    private TableView<Log1_AssetBuildingClassfiles> MonitoringBuilding_tbl;
    @FXML
    private TableView<Log1_AssetFacilityClassfiles> MonitoringFacility_tbl;
    @FXML
    private TableView<Log1_AssetVehiclesClassfiles> MonitoringVehicle_tbl;
    @FXML
    private Label totalOfBuildings_txt;
    @FXML
    private Label totalLand_txt;
    @FXML
    private Label totalFacilities_txt;
    @FXML
    private Label totalVehicle_txt;
    @FXML
    private Label totalOfEquipments_txt;
    @FXML
    private Label totalOfSupplies_txt;
    @FXML
    private Label totalNumberOfAssets_txt;
    
    
    @FXML
    private TableView<Log1_AssetLandClassfiles> LandLiquidation_tbl;
    @FXML
    private JFXButton StartValueAppraisal;
    @FXML
    private TitledPane AppraisalAssetDetails;
    @FXML
    private JFXTextField AppraisalLandName_txt;
    @FXML
    private JFXTextField AppraisalLandAddress_txt;
    @FXML
    private JFXTextField AppraisalLandArea_txt;
    @FXML
    private JFXTextField AppraisalLandPricePerSqMeters_txt;
    @FXML
    private JFXTextField AppraisalLandPurchasedYear_txt;
    @FXML
    private JFXTextField AppraisalLandStatus_txt;
    @FXML
    private TitledPane AppraisalValueUpdate;
    @FXML
    private TextField AppraisalLandTotalPurchasedValue_txt;
    @FXML
    private TextField AppraisalPercent_txt;
    @FXML
    private TextField YearsHasBeenUsed_txt;
    @FXML
    private JFXTextField AppraisalPerYear_txt;
    @FXML
    private JFXButton saveAppraisal_btn;
    @FXML
    private JFXButton cancelAppraisal_btn;
    @FXML
    private Label LandIDforAppraisal_txt;
    @FXML
    private JFXButton computeAppraisal_btn;
    
    
    @FXML
    private TableView<Log1_AssetVehiclesClassfiles> VehicleDepreciation_tbl;
    @FXML
    private JFXTextField DepraciationVehicleType_txt;
    @FXML
    private JFXTextField DepreciationVehicleModel_txt;
    @FXML
    private JFXTextField DepreciationVehicleColor_txt;
    @FXML
    private JFXTextField DepreciationVehicleYearBought_txt;
    @FXML
    private JFXTextField DepreciatonVehicleStatus_txt;
    @FXML
    private Label DepreciationVehicleID_txt;
    @FXML
    private JFXTextField DepreciationVehiclePurchasedPrice_txt;
    @FXML
    private Label AppaisalBookValue_txt;
    @FXML
    private JFXButton StartDepreciation_btn;
    @FXML
    private TitledPane DepreciationVehicleDetails_window;
    @FXML
    private JFXButton computeDepreciaton_btn;
    @FXML
    private BorderPane DepreciationVehicleDetails_pane;
    @FXML
    private TitledPane computeDepreciationValue_pane;
    @FXML
    private JFXButton updateDepreciationvalue_btn;
    @FXML
    private TextField depreciationPurchaseValue_txt;
    @FXML
    private TextField salvageValue_txt;
    @FXML
    private TextField DepreciationLifeTimeYears_txt;
    @FXML
    private TextField DepreciationYearsHasBeenUsed_txt;
    @FXML
    private Label DepreciationBookValue_txt;
    @FXML
    private JFXTextField depreciatonValue_txt;
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> Equipment_tbl;
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> MonitoringEquipment_tbl;
    @FXML
    private TableView<?> MonitoringWarehouseSupplies_tbl;
    @FXML
    private TableView<?> assetTotalization_tbl;
    
    
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> disposeEquipment_tbl;
    @FXML
    private Label disposeAssetName_txt;
    @FXML
    private Label disposalValue_txt;
    @FXML
    private DatePicker disposalDate_txt;
    @FXML
    private TextArea disposalNote_txt;
    @FXML
    private TableView<Log1_AssetDisposalClassfiles> DisposedAssets_tbl;
    @FXML
    private DatePicker datePriceUpdated_txt;
    @FXML
    private DatePicker DPdatePriceUpdated_txt;
    @FXML
    private JFXButton cancelDepreciaton_btn;
    
    
    
    @FXML
    private TableView<Log1_AssetEquipmentClassfiles> EquipmentDepreciation_tbl;
    @FXML
    private JFXButton StartEquipmentDepreciation_btn;
    @FXML
    private BorderPane DepreciationVehicleDetails_pane1;
    @FXML
    private TitledPane EquipmentDetailsDepreciation_window;
    @FXML
    private JFXTextField equipDepraciationType_txt;
    @FXML
    private JFXTextField depreciationEquipBrand_txt;
    @FXML
    private JFXTextField equipmentLifeSpanDepreciation_txt;
    @FXML
    private JFXTextField equipPurchasedPrice_txt;
    @FXML
    private JFXTextField equipmentYearBought_txt;
    @FXML
    private TitledPane computeEquipmentDepreciation_pane;
    @FXML
    private JFXButton computeDepreciatonequip_btn;
    @FXML
    private JFXButton updateDepreciationvalueEquip_btn;
    @FXML
    private JFXButton cancelDepreciatonEquipment_btn;
    @FXML
    private TextField purchaseValueEquipment_txt;
    @FXML
    private TextField salvageValueEquipment_txt;
    @FXML
    private Label DepreciationEquipBookValue_txt;
    @FXML
    private TextField equipmentDepreciationLifeTimeYears_txt1;
    @FXML
    private JFXTextField depreciatonValueEquipment_txt;
    @FXML
    private TextField DepreciationYearsHasBeenUsedequipment_txt;
    @FXML
    private DatePicker dateTodayEquipDp_txt;
    @FXML
    private JFXTextField EquipmentNameForDepreciation_txt;
    @FXML
    private TitledPane equipmentList_pane;
    @FXML
    private Label DepreciationEquipmentID_txt;
    @FXML
    private TitledPane vehicleList_pane;
    @FXML
    private TitledPane LandList_pane;
    @FXML
    private ComboBox<String> selectAssetForDisposal_combox;
    @FXML
    private TableView<?> disposeWarehouseSupply_tbl;
    @FXML
    private TableView<Log1_AssetVehiclesClassfiles> disposeVehicle_tbl;
    @FXML
    private Label AssetID_txt;
    @FXML
    private TextField quantity_txt;
    @FXML
    private Text total_txt;
    @FXML
    private Label AssetEquipmentCount_txt;
    @FXML
    private Label AssetVehicleCount_txt;
    @FXML
    private JFXButton disposeEquipment_btn;
    @FXML
    private JFXButton disposeVehicle_btn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshData();
        //registration tab*******************************************
        selectAssetType_combox.setItems(selectAssetType);
        //***********************************************************


        //Monitoring tab*********************************************
        selectAssetForMonitoring_combox.setItems(AllAssetForViewing);
        MonitoringLand_tbl.setVisible(false);
        MonitoringBuilding_tbl.setVisible(false);
        MonitoringFacility_tbl.setVisible(false);
        MonitoringVehicle_tbl.setVisible(false);
        //***********************************************************
        

        //liquidation***********************************************
        //appraisal*****************************************
        AppraisalAssetDetails.setDisable(true);
        AppraisalValueUpdate.setDisable(true);
        AppraisalLandName_txt.setVisible(false);
        AppraisalLandAddress_txt.setVisible(false);
        AppraisalLandArea_txt.setVisible(false);
        AppraisalLandPricePerSqMeters_txt.setVisible(false);
        AppraisalLandPurchasedYear_txt.setVisible(false);
        AppraisalLandStatus_txt.setVisible(false);
        AppraisalPercent_txt.setText("0");
        LandIDforAppraisal_txt.setVisible(false);
        saveAppraisal_btn.setDisable(true);
        StartValueAppraisal.setOnMouseClicked(e->startAppraisal());
        cancelAppraisal_btn.setOnMouseClicked(e->cancelAppraisal());
        AppraisalPercent_txt.setOnKeyReleased(e->computeAppraisal());
        computeAppraisal_btn.setOnMouseClicked(e->computeAppraisalBookValue());
        saveAppraisal_btn.setOnMouseClicked(e->updatedAssetTotalizationPrice());
        computeDepreciationValue_pane.setDisable(true);
        DepreciationVehicleDetails_window.setDisable(true);
        //********************************************************************
        //Depreciation*****************************************************
        updateDepreciationvalue_btn.setDisable(true);
        computeDepreciationValue_pane.setDisable(true);
        DepreciationVehicleDetails_window.setDisable(true);
        DepreciationVehicleID_txt.setVisible(false);
        StartDepreciation_btn.setOnMouseClicked(e-> startVehicleDepreciation());
        DepreciationLifeTimeYears_txt.setOnKeyReleased(e->computeDepreciationValue());
        computeDepreciaton_btn.setOnMouseClicked(e-> computeDepreciationBookValue());
        salvageValue_txt.setOnKeyReleased(e->computeDepreciationValue());
        updateDepreciationvalue_btn.setOnMouseClicked(e-> updateDepreciateValue());
        cancelDepreciaton_btn.setOnMouseClicked(e->cancelDepreciation());
        //*******************************************************************
        computeEquipmentDepreciation_pane.setDisable(true);
        EquipmentDetailsDepreciation_window.setDisable(true);
        StartEquipmentDepreciation_btn.setOnMouseClicked(e->startEquipmentDepreciation());
        salvageValueEquipment_txt.setOnKeyReleased(e->computeEquipmentDepreciationValue());
        computeDepreciatonequip_btn.setOnMouseClicked(e-> computeEquipmentDepreciationBookValue());
        updateDepreciationvalueEquip_btn.setOnMouseClicked(e-> updateEquipmentDepreciateValue());
        cancelDepreciatonEquipment_btn.setOnMouseClicked(e->cancelEquipmentDepreciation());
        //Disposal
        selectAssetForDisposal_combox.setItems(AssetForDisposal);
        disposeEquipment_btn.setOnMouseClicked(e-> disposeEquipment());
        disposeVehicle_btn.setOnMouseClicked(e-> disposeVehicle());
    }
    
        //deadmeme
//    private void updateBuildingAction(ActionEvent event) {
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
//    }
    
    @FXML
    public void refreshData(){
        //registration tab**************************************************
        renderLandTable();
        renderBuildingTable();
        renderFacilityTable();
        renderVehicleTable();
        renderEquipmentTable();
        callLandData();
        callBuildingData();
        callFacilityData();
        callVehicleData();
        callEquipmentData();
        //Monitoring tab*****************************************************
        renderLandTableForMonitoring();
        renderBuildingTableForMonitoring();
        renderFacilityTableForMonitoring();
        renderVehicleTableForMonitoring();
        renderEquipmentTableForMonitoring();
        callLandDataForMonitoring();
        callBuildingDataForMonitoring();
        callFacilityDataForMonitoring();
        callVehicleDataForMonitoring();
        callEquipmentDataForMonitoring();
        
        //Liquidation********************************************************
        //Appraisal********************
        renderLandTableForAppraisal();
        callLandDataForAppraisal();
        //Deprciation***************************************************
        //Vehicle depreciation***************************************
        renderVehicleTableForDepreciation();
        callVehicleDataforDepreciation();
        disableVehicleTxtFields();
        //equipment depreciation****************************************
        renderEquipmentTableForDepreciation();
        callEquipmentDataForDepraciation();
        disableEquipmentTxtFields();
        //report************************************************************
        loadAssetCount();
        totalOfAsset();
        //Disposal**********************************************************
        renderEquipmentTableForDisposal();
        callEquipmentDataForDisposal();
        loadAssetEquipmentCount();
        renderDisposeAssetTable();
        callDisposeAssetData();
        renderVehicleTableForDisposal();
        callVehicleDataforDisposal();
    }
    //registration tab***************************************************************
    //*******************************************************************************
    @FXML
    private void showRegisForm(ActionEvent event) throws IOException {
        String AssetType = selectAssetType_combox.getValue();
        
        if(AssetType=="Building"){
            building_tbl.setDisable(false);
            building_tbl.setVisible(true);
            
            Vehicle_tbl.setDisable(true);
            Vehicle_tbl.setVisible(false);
            
            land_tbl.setDisable(true);
            land_tbl.setVisible(false);
            
            facility_tbl.setDisable(true);
            facility_tbl.setVisible(false);
            
            Equipment_tbl.setDisable(true);
            Equipment_tbl.setVisible(false);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
            registrationForm_pane.getChildren().setAll(pane);
        }
        else if(AssetType =="Vehicle"){
            Vehicle_tbl.setDisable(false);
            Vehicle_tbl.setVisible(true);
            
            building_tbl.setDisable(true);
            building_tbl.setVisible(false);
            
            land_tbl.setDisable(true);
            land_tbl.setVisible(false);
            
            facility_tbl.setDisable(true);
            facility_tbl.setVisible(false);
            
            Equipment_tbl.setDisable(true);
            Equipment_tbl.setVisible(false);
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddVehicle.fxml"));
            registrationForm_pane.getChildren().setAll(pane1);
        }
        else if(AssetType =="Land"){
            land_tbl.setDisable(false);
            land_tbl.setVisible(true);
            
            building_tbl.setDisable(true);
            building_tbl.setVisible(false);
            
            Vehicle_tbl.setDisable(true);
            Vehicle_tbl.setVisible(false);
            
            facility_tbl.setDisable(true);
            facility_tbl.setVisible(false);
            
            Equipment_tbl.setDisable(true);
            Equipment_tbl.setVisible(false);
            AnchorPane pane2 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddLand.fxml"));
            registrationForm_pane.getChildren().setAll(pane2);
        }
        else if(AssetType =="Facility"){
            facility_tbl.setDisable(false);
            facility_tbl.setVisible(true);
            
            land_tbl.setDisable(true);
            land_tbl.setVisible(false);
            
            building_tbl.setDisable(true);
            building_tbl.setVisible(false);
            
            Vehicle_tbl.setDisable(true);
            Vehicle_tbl.setVisible(false);
            
            Equipment_tbl.setDisable(true);
            Equipment_tbl.setVisible(false);
            AnchorPane pane3 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddFacility.fxml"));
            registrationForm_pane.getChildren().setAll(pane3);
        }else if(AssetType =="Equipment"){
            facility_tbl.setDisable(true);
            facility_tbl.setVisible(false);
            
            land_tbl.setDisable(true);
            land_tbl.setVisible(false);
            
            building_tbl.setDisable(true);
            building_tbl.setVisible(false);
            
            Vehicle_tbl.setDisable(true);
            Vehicle_tbl.setVisible(false);
            
            Equipment_tbl.setDisable(false);
            Equipment_tbl.setVisible(true);
            AnchorPane pane3 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddEquipment.fxml"));
            registrationForm_pane.getChildren().setAll(pane3);
        }
    }
    public void renderLandTable() {
        land_tbl.getItems().clear();
        land_tbl.getColumns().removeAll(land_tbl.getColumns());

        TableColumn<Log1_AssetLandClassfiles, String> LandName = new TableColumn<>("Land Name");
        TableColumn<Log1_AssetLandClassfiles, String> LandPurchasedDate = new TableColumn<>("Purchased Year");
        TableColumn<Log1_AssetLandClassfiles, String> Address = new TableColumn<>("Address");

        LandName.setCellValueFactory((param) -> param.getValue().LandName);
        LandPurchasedDate.setCellValueFactory(param -> param.getValue().LandPurchasedDate);
        Address.setCellValueFactory(param -> param.getValue().LandAddress);

        land_tbl.getColumns().addAll(LandName, LandPurchasedDate, Address);
    }
    public void renderBuildingTable() {
        building_tbl.getItems().clear();
        building_tbl.getColumns().removeAll(building_tbl.getColumns());

        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingName = new TableColumn<>("Building Name");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingAddress = new TableColumn<>("Address");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingContact = new TableColumn<>("Contact");

        BuildingName.setCellValueFactory((param) -> param.getValue().BuildingName);
        BuildingAddress.setCellValueFactory(param -> param.getValue().LandAddress);
        BuildingContact.setCellValueFactory(param -> param.getValue().BuildingContact);

        building_tbl.getColumns().addAll(BuildingName, BuildingAddress, BuildingContact);

    }
    public void renderFacilityTable() {
        facility_tbl.getItems().clear();
        facility_tbl.getColumns().removeAll(facility_tbl.getColumns());

        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityName = new TableColumn<>("Facility Name");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityType = new TableColumn<>("Facility Type");
        TableColumn<Log1_AssetFacilityClassfiles, String> BuildingName = new TableColumn<>("Building Located");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityRoomNumber = new TableColumn<>("Room Number");

        BuildingName.setCellValueFactory((param) -> param.getValue().BuildingName);
        FacilityName.setCellValueFactory(param -> param.getValue().FacilityName);
        FacilityType.setCellValueFactory(param -> param.getValue().FacilityType);
        FacilityRoomNumber.setCellValueFactory(param -> param.getValue().FacilityRoomNumber);

        facility_tbl.getColumns().addAll(BuildingName, FacilityName, FacilityType, FacilityRoomNumber);
    }
    public void renderVehicleTable() {
        Vehicle_tbl.getItems().clear();
        Vehicle_tbl.getColumns().removeAll(Vehicle_tbl.getColumns());

        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleType = new TableColumn<>("Vehicle Type");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleModel = new TableColumn<>("Model");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleColor = new TableColumn<>("Color");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleUsability = new TableColumn<>("Usability");

        VehicleType.setCellValueFactory((param) -> param.getValue().VehicleType);
        VehicleModel.setCellValueFactory(param -> param.getValue().VehicleModel);
        VehicleColor.setCellValueFactory(param -> param.getValue().VehicleColor);
        VehicleUsability.setCellValueFactory(param -> param.getValue().VehicleUsability);

        Vehicle_tbl.getColumns().addAll(VehicleType, VehicleModel, VehicleColor, VehicleUsability);

    }
    public void renderEquipmentTable() {
        Equipment_tbl.getItems().clear();
        Equipment_tbl.getColumns().removeAll(Equipment_tbl.getColumns());

        TableColumn<Log1_AssetEquipmentClassfiles, String> equipName = new TableColumn<>("Equipment Name");
        TableColumn<Log1_AssetEquipmentClassfiles, String> equipType = new TableColumn<>("Type");
        TableColumn<Log1_AssetEquipmentClassfiles, String> equipBrand = new TableColumn<>("Brand");

        equipName.setCellValueFactory((param) -> param.getValue().EquipmentName);
        equipType.setCellValueFactory(param -> param.getValue().EquipmentType);
        equipBrand.setCellValueFactory(param -> param.getValue().EquipmentBrand);

        Equipment_tbl.getColumns().addAll(equipName, equipType, equipBrand);
    }
    public void callLandData(){
         Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
         ObservableList<Log1_AssetLandClassfiles> Land = FXCollections.observableArrayList();
          
            List b = assetLand.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Land.add(new Log1_AssetLandClassfiles(
                
                String.valueOf(hm.get("LandID")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("LandName")),
                String.valueOf(hm.get("LandArea")),
                String.valueOf(hm.get("LandAddress")),
                String.valueOf(hm.get("LandPricePerSqMeters")),
                String.valueOf(hm.get("LandPurchasedDate")),
                String.valueOf(hm.get("LandStatus")),
                String.valueOf(hm.get("LandCurrentPrice")),
                String.valueOf(hm.get("LandPriceUpdated"))
                ));       
        }
        land_tbl.getItems().clear();
        land_tbl.setItems(Land);
    }
    public void callBuildingData(){
         Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
         ObservableList<Log1_AssetBuildingClassfiles> Building = FXCollections.observableArrayList();
          
            List b = assetBuilding.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetLand", "LandID", "=", "LandID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Building.add(new Log1_AssetBuildingClassfiles(
                
                String.valueOf(hm.get("BuildingID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("BuildingDescription")),
                String.valueOf(hm.get("LandName")),
                String.valueOf(hm.get("LandAddress")),
                String.valueOf(hm.get("BuildingContact")),
                String.valueOf(hm.get("BuildingYearBuilt")),
                String.valueOf(hm.get("AssetCategory"))
                ));       
        }
        building_tbl.getItems().clear();
        building_tbl.setItems(Building);
    }
    public void callFacilityData(){
         Log1_AssetFacilityModel assetFacility = new Log1_AssetFacilityModel();
         ObservableList<Log1_AssetFacilityClassfiles> facility = FXCollections.observableArrayList();
          
            List b = assetFacility.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                facility.add(new Log1_AssetFacilityClassfiles(
                
                String.valueOf(hm.get("FacilityID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("FacilityName")),
                String.valueOf(hm.get("FacilityType")),
                String.valueOf(hm.get("FacilityRoomNumber")),
                String.valueOf(hm.get("FacilityCapacity")),
                String.valueOf(hm.get("FacilityStatus")),
                String.valueOf(hm.get("AssetCategory"))
                ));       
        }
        facility_tbl.getItems().clear();
        facility_tbl.setItems(facility);
    }
    public void callVehicleData(){
         Log1_AssetVehiclesModel assetVehicle = new Log1_AssetVehiclesModel();
         ObservableList<Log1_AssetVehiclesClassfiles> vehicle = FXCollections.observableArrayList();
          
            List b = assetVehicle.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                vehicle.add(new Log1_AssetVehiclesClassfiles(
                
                String.valueOf(hm.get("VehicleID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("VehicleType")),
                String.valueOf(hm.get("VehicleModel")),
                String.valueOf(hm.get("VehicleColor")),
                String.valueOf(hm.get("VehicleSerialNumber")),
                String.valueOf(hm.get("VehicleChassisNumber")),
                String.valueOf(hm.get("VehicleYearBought")),
                String.valueOf(hm.get("VehicleWarrantyDate")),
                String.valueOf(hm.get("VehiclePurchasedPrice")),
                String.valueOf(hm.get("VehicleFuelType")),
                String.valueOf(hm.get("VehicleUsability")),
                String.valueOf(hm.get("VehicleStatus")),
                String.valueOf(hm.get("PriceUpdatedAt")),
                String.valueOf(hm.get("CurrentPrice"))
                ));       
        }
        Vehicle_tbl.getItems().clear();
        Vehicle_tbl.setItems(vehicle);
    }    
    public void callEquipmentData(){
         Log1_AssetEquipmentModel equipmentModel = new Log1_AssetEquipmentModel();
         ObservableList<Log1_AssetEquipmentClassfiles> equipment = FXCollections.observableArrayList();
          
            List b = equipmentModel.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                equipment.add(new Log1_AssetEquipmentClassfiles(
                
                String.valueOf(hm.get("EquipmentID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("EquipmentName")),
                String.valueOf(hm.get("EquipmentRoomNumber")),
                String.valueOf(hm.get("EquipmentType")),
                String.valueOf(hm.get("EquipmentBrand")),
                String.valueOf(hm.get("EquipmentPurchasedPrice")),
                String.valueOf(hm.get("EquipmentPurchasedDate")),
                String.valueOf(hm.get("EquipmentLifeSpan")),
                String.valueOf(hm.get("EquipmentWarranty")),
                String.valueOf(hm.get("CurrentPrice")),
                String.valueOf(hm.get("PriceUpdatedAt"))
                ));       
        }
        Equipment_tbl.getItems().clear();
        Equipment_tbl.setItems(equipment);
    }    
    //*********************************************************************************
    
    //Monitoring tab***************************************************************************
    //****************************************************************************************
    @FXML
    private void showTableView(ActionEvent event) {
        String AssetTable = selectAssetForMonitoring_combox.getValue();
        
        if(AssetTable=="Land"){
            MonitoringLand_tbl.setVisible(true);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(false);
            MonitoringEquipment_tbl.setVisible(false);
        }else if(AssetTable=="Building"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(true);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(false);
            MonitoringEquipment_tbl.setVisible(false);
        }else if(AssetTable=="Facility"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(true);
            MonitoringVehicle_tbl.setVisible(false);
            MonitoringEquipment_tbl.setVisible(false);
        }else if(AssetTable=="Vehicle"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(true);
            MonitoringEquipment_tbl.setVisible(false);
        }else if(AssetTable=="Equipment"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(false);
            MonitoringEquipment_tbl.setVisible(true);
        }
    }    
    public void renderLandTableForMonitoring() {
        MonitoringLand_tbl.getItems().clear();
        MonitoringLand_tbl.getColumns().removeAll(MonitoringLand_tbl.getColumns());

        TableColumn<Log1_AssetLandClassfiles, String> LandName = new TableColumn<>("Land Name");
        TableColumn<Log1_AssetLandClassfiles, String> LandArea = new TableColumn<>("Land Area");
        TableColumn<Log1_AssetLandClassfiles, String> LandAddress = new TableColumn<>("Address");
        TableColumn<Log1_AssetLandClassfiles, String> LandPricePerSqMeters = new TableColumn<>("Price(per sq meters)");
        TableColumn<Log1_AssetLandClassfiles, String> LandPurchasedDate = new TableColumn<>("Purchased Date");
        TableColumn<Log1_AssetLandClassfiles, String> LandStatus = new TableColumn<>("Status");
        TableColumn<Log1_AssetLandClassfiles, String> LandCurrentPrice = new TableColumn<>("Current Price");
        
        LandName.setCellValueFactory((param) -> param.getValue().LandName);
        LandArea.setCellValueFactory(param -> param.getValue().LandArea);
        LandAddress.setCellValueFactory(param -> param.getValue().LandAddress);
        LandPricePerSqMeters.setCellValueFactory(param -> param.getValue().LandPricePerSqMeters);
        LandPurchasedDate.setCellValueFactory(param -> param.getValue().LandPurchasedDate);
        LandStatus.setCellValueFactory(param -> param.getValue().LandStatus);
        LandCurrentPrice.setCellValueFactory(param -> param.getValue().LandCurrentPrice);

        MonitoringLand_tbl.getColumns().addAll(LandName, LandArea, LandAddress, LandPricePerSqMeters
        ,LandPurchasedDate, LandStatus, LandCurrentPrice);
    }
    public void renderBuildingTableForMonitoring() {
        MonitoringBuilding_tbl.getItems().clear();
        MonitoringBuilding_tbl.getColumns().removeAll(MonitoringBuilding_tbl.getColumns());

        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingName = new TableColumn<>("Building Name");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingDescription = new TableColumn<>("Description");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingContact = new TableColumn<>("Contact");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingAddress = new TableColumn<>("Address");
        TableColumn<Log1_AssetBuildingClassfiles, String> BuildingYearBuilt = new TableColumn<>("Year Built");

        BuildingName.setCellValueFactory((param) -> param.getValue().BuildingName);
        BuildingDescription.setCellValueFactory(param -> param.getValue().BuildingDescription);
        BuildingContact.setCellValueFactory(param -> param.getValue().BuildingContact);
        BuildingAddress.setCellValueFactory(param -> param.getValue().LandAddress);
        BuildingYearBuilt.setCellValueFactory(param -> param.getValue().BuildingYearBuilt);

        MonitoringBuilding_tbl.getColumns().addAll(BuildingName, BuildingDescription, BuildingContact,
                BuildingAddress, BuildingYearBuilt);
    }
    public void renderFacilityTableForMonitoring() {
        MonitoringFacility_tbl.getItems().clear();
        MonitoringFacility_tbl.getColumns().removeAll(MonitoringFacility_tbl.getColumns());

        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityName = new TableColumn<>("Facility Name");
        TableColumn<Log1_AssetFacilityClassfiles, String> BuildingLocated = new TableColumn<>("Building Located");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityType = new TableColumn<>("Type");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityRoomNumber = new TableColumn<>("Room Number");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityCapacity = new TableColumn<>("Capacity");
        TableColumn<Log1_AssetFacilityClassfiles, String> FacilityStatus = new TableColumn<>("Status");

        FacilityName.setCellValueFactory((param) -> param.getValue().FacilityName);
        BuildingLocated.setCellValueFactory(param -> param.getValue().BuildingName);
        FacilityType.setCellValueFactory(param -> param.getValue().FacilityType);
        FacilityRoomNumber.setCellValueFactory(param -> param.getValue().FacilityRoomNumber);
        FacilityCapacity.setCellValueFactory(param -> param.getValue().FacilityCapacity);
        FacilityStatus.setCellValueFactory(param -> param.getValue().FacilityStatus);

        MonitoringFacility_tbl.getColumns().addAll(BuildingLocated, FacilityName, FacilityType,
                FacilityRoomNumber, FacilityCapacity, FacilityStatus);
    }
    public void renderVehicleTableForMonitoring() {
        MonitoringVehicle_tbl.getItems().clear();
        MonitoringVehicle_tbl.getColumns().removeAll(MonitoringVehicle_tbl.getColumns());

        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleType = new TableColumn<>("Vehicle Type");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleModel = new TableColumn<>("Model");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleColor = new TableColumn<>("Color");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleSerialNumber = new TableColumn<>("Serial No.");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleChassisNumber = new TableColumn<>("Chassis No.");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleYearBought = new TableColumn<>("Year Bought");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleWarrantyDate = new TableColumn<>("Warranty");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehiclePurchasedPrice = new TableColumn<>("Price");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleFuelType = new TableColumn<>("Fuel Type");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleUsability = new TableColumn<>("Usability");
        TableColumn<Log1_AssetVehiclesClassfiles, String> VehicleStatus = new TableColumn<>("Status");
        TableColumn<Log1_AssetVehiclesClassfiles, String> BuildingLocated = new TableColumn<>("Location");
        TableColumn<Log1_AssetVehiclesClassfiles, String> currentPrice = new TableColumn<>("Current Price");

        VehicleType.setCellValueFactory((param) -> param.getValue().VehicleType);
        VehicleModel.setCellValueFactory(param -> param.getValue().VehicleModel);
        VehicleColor.setCellValueFactory(param -> param.getValue().VehicleColor);
        VehicleSerialNumber.setCellValueFactory(param -> param.getValue().VehicleSerialNumber);
        VehicleChassisNumber.setCellValueFactory(param -> param.getValue().VehicleChassisNumber);
        VehicleYearBought.setCellValueFactory(param -> param.getValue().VehicleYearBought);
        VehicleWarrantyDate.setCellValueFactory(param -> param.getValue().VehicleWarrantyDate);
        VehiclePurchasedPrice.setCellValueFactory(param -> param.getValue().VehiclePurchasedPrice);
        VehicleFuelType.setCellValueFactory(param -> param.getValue().VehicleFuelType);
        VehicleUsability.setCellValueFactory(param -> param.getValue().VehicleUsability);
        VehicleStatus.setCellValueFactory(param -> param.getValue().VehicleStatus);
        BuildingLocated.setCellValueFactory(param -> param.getValue().BuildingName);
        currentPrice.setCellValueFactory(param -> param.getValue().CurrentPrice);

        MonitoringVehicle_tbl.getColumns().addAll(VehicleType, VehicleModel, VehicleColor, VehicleSerialNumber
        ,VehicleChassisNumber, VehicleYearBought, VehicleWarrantyDate, VehiclePurchasedPrice,
        VehicleFuelType, VehicleUsability, VehicleStatus, BuildingLocated,currentPrice);

    }
    public void renderEquipmentTableForMonitoring() {
        MonitoringEquipment_tbl.getItems().clear();
        MonitoringEquipment_tbl.getColumns().removeAll(MonitoringEquipment_tbl.getColumns());

        TableColumn<Log1_AssetEquipmentClassfiles, String> equipName = new TableColumn<>("Equipment Name");
        TableColumn<Log1_AssetEquipmentClassfiles, String> equipType = new TableColumn<>("Type");
        TableColumn<Log1_AssetEquipmentClassfiles, String> equipBrand = new TableColumn<>("Brand");
        TableColumn<Log1_AssetEquipmentClassfiles, String> facilityRoomNumber = new TableColumn<>("Room Number");
        TableColumn<Log1_AssetEquipmentClassfiles, String> equipWarranty = new TableColumn<>("Warranty");

        equipName.setCellValueFactory((param) -> param.getValue().EquipmentName);
        equipType.setCellValueFactory(param -> param.getValue().EquipmentType);
        equipBrand.setCellValueFactory(param -> param.getValue().EquipmentBrand);
        facilityRoomNumber.setCellValueFactory(param -> param.getValue().EquipmentRoomNumber);
        equipWarranty.setCellValueFactory(param -> param.getValue().EquipmentWarranty);
        
        MonitoringEquipment_tbl.getColumns().addAll(equipName, equipType, equipBrand,facilityRoomNumber,equipWarranty);
    }
    public void callLandDataForMonitoring(){
         Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
         ObservableList<Log1_AssetLandClassfiles> Land = FXCollections.observableArrayList();
          
            List b = assetLand.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Land.add(new Log1_AssetLandClassfiles(
                
                String.valueOf(hm.get("LandID")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("LandName")),
                String.valueOf(hm.get("LandArea")),
                String.valueOf(hm.get("LandAddress")),
                String.valueOf(hm.get("LandPricePerSqMeters")),
                String.valueOf(hm.get("LandPurchasedDate")),
                String.valueOf(hm.get("LandStatus")),
                String.valueOf(hm.get("LandCurrentPrice")),
                String.valueOf(hm.get("LandPriceUpdated"))
                ));       
        }
        MonitoringLand_tbl.getItems().clear();
        MonitoringLand_tbl.setItems(Land);
    }
    public void callBuildingDataForMonitoring(){
         Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
         ObservableList<Log1_AssetBuildingClassfiles> Building = FXCollections.observableArrayList();
          
            List b = assetBuilding.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetLand", "LandID", "=", "LandID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Building.add(new Log1_AssetBuildingClassfiles(
                
                String.valueOf(hm.get("BuildingID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("BuildingDescription")),
                String.valueOf(hm.get("LandName")),
                String.valueOf(hm.get("LandAddress")),
                String.valueOf(hm.get("BuildingContact")),
                String.valueOf(hm.get("BuildingYearBuilt")),
                String.valueOf(hm.get("AssetCategory"))
                ));       
        }
        MonitoringBuilding_tbl.getItems().clear();
        MonitoringBuilding_tbl.setItems(Building);
    }
    public void callFacilityDataForMonitoring(){
         Log1_AssetFacilityModel assetFacility = new Log1_AssetFacilityModel();
         ObservableList<Log1_AssetFacilityClassfiles> facility = FXCollections.observableArrayList();
          
            List b = assetFacility.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                facility.add(new Log1_AssetFacilityClassfiles(
                
                String.valueOf(hm.get("FacilityID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("FacilityName")),
                String.valueOf(hm.get("FacilityType")),
                String.valueOf(hm.get("FacilityRoomNumber")),
                String.valueOf(hm.get("FacilityCapacity")),
                String.valueOf(hm.get("FacilityStatus")),
                String.valueOf(hm.get("AssetCategory"))
                ));       
        }
        MonitoringFacility_tbl.getItems().clear();
        MonitoringFacility_tbl.setItems(facility);
    }
    public void callVehicleDataForMonitoring(){
         Log1_AssetVehiclesModel assetVehicle = new Log1_AssetVehiclesModel();
         ObservableList<Log1_AssetVehiclesClassfiles> vehicle = FXCollections.observableArrayList();
          
            List b = assetVehicle.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                vehicle.add(new Log1_AssetVehiclesClassfiles(
                
                String.valueOf(hm.get("VehicleID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("VehicleType")),
                String.valueOf(hm.get("VehicleModel")),
                String.valueOf(hm.get("VehicleColor")),
                String.valueOf(hm.get("VehicleSerialNumber")),
                String.valueOf(hm.get("VehicleChassisNumber")),
                String.valueOf(hm.get("VehicleYearBought")),
                String.valueOf(hm.get("VehicleWarrantyDate")),
                String.valueOf(hm.get("VehiclePurchasedPrice")),
                String.valueOf(hm.get("VehicleFuelType")),
                String.valueOf(hm.get("VehicleUsability")),
                String.valueOf(hm.get("VehicleStatus")),
                String.valueOf(hm.get("PriceUpdatedAt")),
                String.valueOf(hm.get("CurrentPrice"))
                ));       
        }
        MonitoringVehicle_tbl.getItems().clear();
        MonitoringVehicle_tbl.setItems(vehicle);
    }
    public void callEquipmentDataForMonitoring(){
         Log1_AssetEquipmentModel equipmentModel = new Log1_AssetEquipmentModel();
         ObservableList<Log1_AssetEquipmentClassfiles> equipment = FXCollections.observableArrayList();
          
            List b = equipmentModel.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                equipment.add(new Log1_AssetEquipmentClassfiles(
                
                String.valueOf(hm.get("EquipmentID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("EquipmentName")),
                String.valueOf(hm.get("EquipmentRoomNumber")),
                String.valueOf(hm.get("EquipmentType")),
                String.valueOf(hm.get("EquipmentBrand")),
                String.valueOf(hm.get("EquipmentPurchasedPrice")),
                String.valueOf(hm.get("EquipmentPurchasedDate")),
                String.valueOf(hm.get("EquipmentLifeSpan")),
                String.valueOf(hm.get("EquipmentWarranty")),
                String.valueOf(hm.get("CurrentPrice")),
                String.valueOf(hm.get("PriceUpdatedAt"))
                ));       
        }
        MonitoringEquipment_tbl.getItems().clear();
        MonitoringEquipment_tbl.setItems(equipment);
    }
    //****************************************************************************
    
    
    //ligquidation tab***********************************************************************
    //appraisal****************************************************************************
    public void startAppraisal(){
        AppraisalLandName_txt.setVisible(true);
        AppraisalLandAddress_txt.setVisible(true);
        AppraisalLandArea_txt.setVisible(true);
        AppraisalLandPricePerSqMeters_txt.setVisible(true);
        AppraisalLandPurchasedYear_txt.setVisible(true);
        AppraisalLandStatus_txt.setVisible(true);
        LandList_pane.setDisable(true);
        AppraisalAssetDetails.setDisable(false);
        AppraisalValueUpdate.setDisable(false);
        
        int x = Integer.parseInt(AppraisalLandArea_txt.getText());
        int y = Integer.parseInt(AppraisalLandPricePerSqMeters_txt.getText());
        String answer = String.valueOf(x*y);
        AppraisalLandTotalPurchasedValue_txt.setText(answer);
    }
    public void computeAppraisal(){
        int totalLandPrice = Integer.parseInt(AppraisalLandTotalPurchasedValue_txt.getText());
        int Percentage =Integer.parseInt(AppraisalPercent_txt.getText());
        
        int PercentageAndTotalLandPrice = Integer.valueOf(totalLandPrice*Percentage);
        
        String appraisalPerYear = String.valueOf(PercentageAndTotalLandPrice/100);
        AppraisalPerYear_txt.setText(appraisalPerYear);
    }    
    public void computeAppraisalBookValue(){
        int purchaseValue = Integer.parseInt(AppraisalLandTotalPurchasedValue_txt.getText());
        int appraisalPerYear = Integer.parseInt(AppraisalPerYear_txt.getText());
        int yearsHasBeenUsed = Integer.parseInt(YearsHasBeenUsed_txt.getText());
        
        int TotalAppraisal = Integer.valueOf(appraisalPerYear*yearsHasBeenUsed);
        
        String updatedLandPrice = String.valueOf(TotalAppraisal+purchaseValue);
        
        AppaisalBookValue_txt.setText(updatedLandPrice);
        saveAppraisal_btn.setDisable(false);
    }
    public void cancelAppraisal(){
        AppraisalLandName_txt.setText("");
        AppraisalLandAddress_txt.setText("");
        AppraisalLandArea_txt.setText("");
        AppraisalLandPricePerSqMeters_txt.setText("");
        AppraisalLandPurchasedYear_txt.setText("");
        AppraisalLandStatus_txt.setText("");
        AppraisalAssetDetails.setDisable(true);
        AppraisalValueUpdate.setDisable(true);
        AppraisalLandTotalPurchasedValue_txt.setText("");
        AppraisalPercent_txt.setText("");
        AppraisalPerYear_txt.setText("");
        YearsHasBeenUsed_txt.setText("");
        AppaisalBookValue_txt.setText("");
        AppraisalLandName_txt.setVisible(false);
        AppraisalLandAddress_txt.setVisible(false);
        AppraisalLandArea_txt.setVisible(false);
        AppraisalLandPricePerSqMeters_txt.setVisible(false);
        AppraisalLandPurchasedYear_txt.setVisible(false);
        AppraisalLandStatus_txt.setVisible(false);
        datePriceUpdated_txt.getEditor().setText("");
        LandList_pane.setDisable(false);
    }
    public void renderLandTableForAppraisal() {
        LandLiquidation_tbl.getItems().clear();
        LandLiquidation_tbl.getColumns().removeAll(LandLiquidation_tbl.getColumns());

        TableColumn<Log1_AssetLandClassfiles, String> LandName = new TableColumn<>("Land Name");

        LandName.setCellValueFactory((param) -> param.getValue().LandName);

        LandLiquidation_tbl.getColumns().addAll(LandName);
    }
    public void callLandDataForAppraisal(){
         Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
         ObservableList<Log1_AssetLandClassfiles> Land = FXCollections.observableArrayList();
          
            List b = assetLand.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                Land.add(new Log1_AssetLandClassfiles(
                
                String.valueOf(hm.get("LandID")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("LandName")),
                String.valueOf(hm.get("LandArea")),
                String.valueOf(hm.get("LandAddress")),
                String.valueOf(hm.get("LandPricePerSqMeters")),
                String.valueOf(hm.get("LandPurchasedDate")),
                String.valueOf(hm.get("LandStatus")),
                String.valueOf(hm.get("LandCurrentPrice")),
                String.valueOf(hm.get("LandPriceUpdated"))
                ));       
        }
        LandLiquidation_tbl.getItems().clear();
        LandLiquidation_tbl.setItems(Land);
    }
    @FXML
    private void selectLandForAppraisal(MouseEvent event) {
        LandIDforAppraisal_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandID());
        AppraisalLandName_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandName());
        AppraisalLandAddress_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandAddress());
        AppraisalLandArea_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandArea());
        AppraisalLandPricePerSqMeters_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandPricePerSqMeters());
        AppraisalLandPurchasedYear_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandPurchasedDate());
        AppraisalLandStatus_txt.setText(LandLiquidation_tbl.getSelectionModel().getSelectedItem().getLandStatus());
    }
    private void updatedAssetTotalizationPrice() {
        Log1_AssetLandModel al = new Log1_AssetLandModel();
            try{
                if(al.update(new Object[][]{ 
                    {"LandCurrentPrice",AppaisalBookValue_txt.getText()},
                    {"LandPriceUpdated",datePriceUpdated_txt.getEditor().getText()},
                 }).where(new Object[][]{
                     {"LandID","=",LandIDforAppraisal_txt.getText()}
                }).executeUpdate()){
                     AlertMaker.showSimpleAlert("Update", "Land price has been updated!");
                     cancelAppraisal();
                }else{
                     AlertMaker.showErrorMessage("Failed", "failed to update.");
                }
             }catch(Exception e){
                e.printStackTrace();
            }
    }
    //depraciation vehicle***********************************************************************
    public void renderVehicleTableForDepreciation(){
        VehicleDepreciation_tbl.getItems().clear();
        VehicleDepreciation_tbl.getColumns().removeAll(VehicleDepreciation_tbl.getColumns());

        TableColumn<Log1_AssetVehiclesClassfiles, String> Type = new TableColumn<>("Vehicle Type");
        TableColumn<Log1_AssetVehiclesClassfiles, String> Model = new TableColumn<>("Model");
        TableColumn<Log1_AssetVehiclesClassfiles, String> Color = new TableColumn<>("Color");

        Type.setCellValueFactory((param) -> param.getValue().VehicleType);
        Model.setCellValueFactory((param) -> param.getValue().VehicleModel);
        Color.setCellValueFactory((param) -> param.getValue().VehicleColor);

        VehicleDepreciation_tbl.getColumns().addAll(Type, Model, Color);
    }
    public void callVehicleDataforDepreciation(){
         Log1_AssetVehiclesModel assetVehicle = new Log1_AssetVehiclesModel();
         ObservableList<Log1_AssetVehiclesClassfiles> vehicle = FXCollections.observableArrayList();
          
            List b = assetVehicle.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                vehicle.add(new Log1_AssetVehiclesClassfiles(
                
                String.valueOf(hm.get("VehicleID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("VehicleType")),
                String.valueOf(hm.get("VehicleModel")),
                String.valueOf(hm.get("VehicleColor")),
                String.valueOf(hm.get("VehicleSerialNumber")),
                String.valueOf(hm.get("VehicleChassisNumber")),
                String.valueOf(hm.get("VehicleYearBought")),
                String.valueOf(hm.get("VehicleWarrantyDate")),
                String.valueOf(hm.get("VehiclePurchasedPrice")),
                String.valueOf(hm.get("VehicleFuelType")),
                String.valueOf(hm.get("VehicleUsability")),
                String.valueOf(hm.get("VehicleStatus")),
                String.valueOf(hm.get("PriceUpdatedAt")),
                String.valueOf(hm.get("CurrentPrice"))
                ));       
        }
        VehicleDepreciation_tbl.getItems().clear();
        VehicleDepreciation_tbl.setItems(vehicle);
    }
    @FXML
    private void selectVehicleForDepreciation(MouseEvent event) {
        DepraciationVehicleType_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleType());
        DepreciationVehicleModel_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleModel());
        DepreciationVehicleColor_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleColor());
        DepreciationVehicleYearBought_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleYearBought());
        DepreciationVehiclePurchasedPrice_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehiclePurchasedPrice());
        DepreciatonVehicleStatus_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleStatus());
        DepreciationVehicleID_txt.setText(VehicleDepreciation_tbl
                .getSelectionModel().getSelectedItem().getVehicleID());
    }
    public void disableVehicleTxtFields(){
        DepraciationVehicleType_txt.setVisible(false);
        DepreciationVehicleModel_txt.setVisible(false);
        DepreciationVehicleColor_txt.setVisible(false);
        DepreciationVehicleYearBought_txt.setVisible(false);
        DepreciationVehiclePurchasedPrice_txt.setVisible(false);
        DepreciatonVehicleStatus_txt.setVisible(false);
        DepreciationVehicleID_txt.setVisible(false);
        updateDepreciationvalue_btn.setDisable(true);
    }
    public void EnableVehicleFields(){
        DepraciationVehicleType_txt.setVisible(true);
        DepreciationVehicleModel_txt.setVisible(true);
        DepreciationVehicleColor_txt.setVisible(true);
        DepreciationVehicleYearBought_txt.setVisible(true);
        DepreciationVehiclePurchasedPrice_txt.setVisible(true);
        DepreciatonVehicleStatus_txt.setVisible(true);
    }
    private void cancelDepreciation(){
        disableVehicleTxtFields();
        computeDepreciationValue_pane.setDisable(true);
        DepreciationVehicleDetails_window.setDisable(true);
        DepraciationVehicleType_txt.setText("");
        DepreciationVehicleModel_txt.setText("");
        DepreciationVehicleColor_txt.setText("");
        DepreciationVehicleYearBought_txt.setText("");
        DepreciationVehiclePurchasedPrice_txt.setText("");
        DepreciatonVehicleStatus_txt.setText("");
        DepreciationVehicleID_txt.setText("");
        DPdatePriceUpdated_txt.getEditor().setText("");
        depreciationPurchaseValue_txt.setText("");
        salvageValue_txt.setText("");
        DepreciationLifeTimeYears_txt.setText("");
        depreciatonValue_txt.setText("");
        DepreciationYearsHasBeenUsed_txt.setText("");
        DepreciationBookValue_txt.setText("0");
        vehicleList_pane.setDisable(false);
    }
    private void startVehicleDepreciation() {
        computeDepreciationValue_pane.setDisable(false);
        DepreciationVehicleDetails_window.setDisable(false);
        vehicleList_pane.setDisable(true);
        EnableVehicleFields();
        depreciationPurchaseValue_txt.setText(DepreciationVehiclePurchasedPrice_txt.getText());
    }
    public void computeDepreciationValue(){
        int cost = Integer.parseInt(DepreciationVehiclePurchasedPrice_txt.getText());
        int salvage = Integer.parseInt(salvageValue_txt.getText());
        int life = Integer.parseInt(DepreciationLifeTimeYears_txt.getText());
        
        int firstValue = Integer.valueOf(cost-salvage);
        String DepreciationValue = String.valueOf(firstValue/life);
        
        depreciatonValue_txt.setText(DepreciationValue);
    }
    public void computeDepreciationBookValue(){
        int depreciationValue = Integer.parseInt(depreciatonValue_txt.getText());
        int yearsHasbeenUsed = Integer.parseInt(DepreciationYearsHasBeenUsed_txt.getText());
        int cost = Integer.parseInt(DepreciationVehiclePurchasedPrice_txt.getText());
        
        int depreciationOverTheYears = Integer.valueOf(depreciationValue*yearsHasbeenUsed);
        
        String DepreciationBookValue = String.valueOf(cost-depreciationOverTheYears);
        
        DepreciationBookValue_txt.setText(DepreciationBookValue);
        updateDepreciationvalue_btn.setDisable(false);
    }
    private void updateDepreciateValue() {
        Log1_AssetVehiclesModel av = new Log1_AssetVehiclesModel();
            try{
                if(av.update(new Object[][]{ 
                    {"CurrentPrice",DepreciationBookValue_txt.getText()},
                    {"PriceUpdatedAt",DPdatePriceUpdated_txt.getEditor().getText()},
                 }).where(new Object[][]{
                     {"VehicleID","=",DepreciationVehicleID_txt.getText()}
                }).executeUpdate()){
                     AlertMaker.showSimpleAlert("Update", "Vehicle price has been updated!");
                     cancelDepreciation();
                }else{
                     AlertMaker.showErrorMessage("Failed", "failed to update.");
                }
             }catch(Exception e){
                e.printStackTrace();
            }
    }
    //Dpreciation equipment***************************************************
    public void renderEquipmentTableForDepreciation(){
        EquipmentDepreciation_tbl.getItems().clear();
        EquipmentDepreciation_tbl.getColumns().removeAll(EquipmentDepreciation_tbl.getColumns());

        TableColumn<Log1_AssetEquipmentClassfiles, String> Name = new TableColumn<>("Equipment Name");

        Name.setCellValueFactory((param) -> param.getValue().EquipmentName);

        EquipmentDepreciation_tbl.getColumns().addAll(Name);
    }
    public void callEquipmentDataForDepraciation(){
         Log1_AssetEquipmentModel equipmentModel = new Log1_AssetEquipmentModel();
         ObservableList<Log1_AssetEquipmentClassfiles> equipment = FXCollections.observableArrayList();
          
            List b = equipmentModel.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                equipment.add(new Log1_AssetEquipmentClassfiles(
                
                String.valueOf(hm.get("EquipmentID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("EquipmentName")),
                String.valueOf(hm.get("EquipmentRoomNumber")),
                String.valueOf(hm.get("EquipmentType")),
                String.valueOf(hm.get("EquipmentBrand")),
                String.valueOf(hm.get("EquipmentPurchasedPrice")),
                String.valueOf(hm.get("EquipmentPurchasedDate")),
                String.valueOf(hm.get("EquipmentLifeSpan")),
                String.valueOf(hm.get("EquipmentWarranty")),
                String.valueOf(hm.get("CurrentPrice")),
                String.valueOf(hm.get("PriceUpdatedAt"))
                ));       
        }
        EquipmentDepreciation_tbl.getItems().clear();
        EquipmentDepreciation_tbl.setItems(equipment);
    }    
    @FXML
    private void selectEquipmentForDepreciation(MouseEvent event) {
        EquipmentNameForDepreciation_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentName());
        equipDepraciationType_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentType());
        depreciationEquipBrand_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentBrand());
        equipPurchasedPrice_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentPurchasedPrice());
        equipmentYearBought_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentPurchasedDate());
        equipmentLifeSpanDepreciation_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentLifeSpan());
        DepreciationEquipmentID_txt.setText(EquipmentDepreciation_tbl
                .getSelectionModel().getSelectedItem().getEquipmentID());
    }
    private void startEquipmentDepreciation() {
        EquipmentDetailsDepreciation_window.setDisable(false);
        computeEquipmentDepreciation_pane.setDisable(false);
        updateDepreciationvalueEquip_btn.setDisable(true);
        equipmentList_pane.setDisable(true);
        EnableEquipmentFields();
        purchaseValueEquipment_txt.setText(equipPurchasedPrice_txt.getText());
        equipmentDepreciationLifeTimeYears_txt1.setText(equipmentLifeSpanDepreciation_txt.getText());
    }
    public void computeEquipmentDepreciationValue(){
        int cost = Integer.parseInt(purchaseValueEquipment_txt.getText());
        int salvage = Integer.parseInt(salvageValueEquipment_txt.getText());
        int life = Integer.parseInt(equipmentDepreciationLifeTimeYears_txt1.getText());
        
        int firstValue = Integer.valueOf(cost-salvage);
        String DepreciationValue = String.valueOf(firstValue/life);
        
        depreciatonValueEquipment_txt.setText(DepreciationValue);
    }
    public void computeEquipmentDepreciationBookValue(){
        int depreciationValue = Integer.parseInt(depreciatonValueEquipment_txt.getText());
        int yearsHasbeenUsed = Integer.parseInt(DepreciationYearsHasBeenUsedequipment_txt.getText());
        int cost = Integer.parseInt(purchaseValueEquipment_txt.getText());
        
        int depreciationOverTheYears = Integer.valueOf(depreciationValue*yearsHasbeenUsed);
        
        String DepreciationBookValue = String.valueOf(cost-depreciationOverTheYears);
        
        DepreciationEquipBookValue_txt.setText(DepreciationBookValue);
        updateDepreciationvalueEquip_btn.setDisable(false);
    }
    public void disableEquipmentTxtFields(){
        EquipmentNameForDepreciation_txt.setVisible(false);
        equipDepraciationType_txt.setVisible(false);
        depreciationEquipBrand_txt.setVisible(false);
        equipPurchasedPrice_txt.setVisible(false);
        equipmentYearBought_txt.setVisible(false);
        equipmentLifeSpanDepreciation_txt.setVisible(false);
    }
    private void EnableEquipmentFields() {
        EquipmentNameForDepreciation_txt.setVisible(true);
        equipDepraciationType_txt.setVisible(true);
        depreciationEquipBrand_txt.setVisible(true);
        equipPurchasedPrice_txt.setVisible(true);
        equipmentYearBought_txt.setVisible(true);
        equipmentLifeSpanDepreciation_txt.setVisible(true);
    }
    private void cancelEquipmentDepreciation(){
        disableEquipmentTxtFields();
        computeEquipmentDepreciation_pane.setDisable(true);
        EquipmentDetailsDepreciation_window.setDisable(true);
        EquipmentNameForDepreciation_txt.setText("");
        equipDepraciationType_txt.setText("");
        depreciationEquipBrand_txt.setText("");
        equipPurchasedPrice_txt.setText("");
        equipmentYearBought_txt.setText("");
        equipmentLifeSpanDepreciation_txt.setText("");
        dateTodayEquipDp_txt.getEditor().setText("");
        purchaseValueEquipment_txt.setText("");
        salvageValueEquipment_txt.setText("");
        equipmentDepreciationLifeTimeYears_txt1.setText("");
        depreciatonValueEquipment_txt.setText("");
        DepreciationYearsHasBeenUsedequipment_txt.setText("");
        DepreciationEquipBookValue_txt.setText("0");
        equipmentList_pane.setDisable(false);
        DepreciationEquipmentID_txt.setText("");
    }
    private void updateEquipmentDepreciateValue() {
        Log1_AssetEquipmentModel ae = new Log1_AssetEquipmentModel();
            try{
                if(ae.update(new Object[][]{ 
                    {"CurrentPrice",DepreciationEquipBookValue_txt.getText()},
                    {"PriceUpdatedAt",dateTodayEquipDp_txt.getEditor().getText()},
                 }).where(new Object[][]{
                     {"EquipmentID","=",DepreciationEquipmentID_txt.getText()}
                }).executeUpdate()){
                     AlertMaker.showSimpleAlert("Update", "Equipment price has been updated!");
                     cancelEquipmentDepreciation();
                }else{
                     AlertMaker.showErrorMessage("Failed", "failed to update.");
                }
             }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    
    
    //*************************************************************************************
    
    
    //asset report**************************************************************************
    public void totalOfAsset(){
        int totalBuilding =Integer.parseInt(totalOfBuildings_txt.getText());
        int totalLand =Integer.parseInt(totalLand_txt.getText());
        int totalFacility =Integer.parseInt(totalFacilities_txt.getText());
        int totalVehicle =Integer.parseInt(totalVehicle_txt.getText());
        int totalSupply =Integer.parseInt(totalOfSupplies_txt.getText());
        int totalEquipments =Integer.parseInt(totalOfEquipments_txt.getText());
        
        String answer=String.valueOf(totalBuilding+totalLand+totalFacility
        +totalVehicle+totalSupply+totalEquipments);
        
        totalNumberOfAssets_txt.setText(answer);
    }
    public void loadAssetCount(){        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            totalOfBuildings_txt.setText((String.valueOf(hash.get("AssetBuilding"))));
            totalLand_txt.setText((String.valueOf(hash.get("AssetLand"))));
            totalFacilities_txt.setText((String.valueOf(hash.get("AssetFacility"))));
            totalVehicle_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
            totalOfSupplies_txt.setText((String.valueOf(hash.get("AssetWarehouseSupply"))));
            totalOfEquipments_txt.setText((String.valueOf(hash.get("AssetWarehouseEquipment"))));  
        });
    }
    //*************************************************************************************
    //Asset disposal***********************************************************
    public void renderEquipmentTableForDisposal(){
        disposeEquipment_tbl.getItems().clear();
        disposeEquipment_tbl.getColumns().removeAll(disposeEquipment_tbl.getColumns());

        TableColumn<Log1_AssetEquipmentClassfiles, String> Name = new TableColumn<>("Equipment Name");
        TableColumn<Log1_AssetEquipmentClassfiles, String> Type = new TableColumn<>("Type");
        TableColumn<Log1_AssetEquipmentClassfiles, String> Brand = new TableColumn<>("Brand");
        TableColumn<Log1_AssetEquipmentClassfiles, String> Warranty = new TableColumn<>("Warranty");
        TableColumn<Log1_AssetEquipmentClassfiles, String> value = new TableColumn<>("Value");

        Name.setCellValueFactory((param) -> param.getValue().EquipmentName);
        Type.setCellValueFactory((param) -> param.getValue().EquipmentType);
        Brand.setCellValueFactory((param) -> param.getValue().EquipmentBrand);
        Warranty.setCellValueFactory((param) -> param.getValue().EquipmentWarranty);
        value.setCellValueFactory((param) -> param.getValue().CurrentPrice);

        disposeEquipment_tbl.getColumns().addAll(Name, Type, Brand, Warranty, value);
    }
    public void renderVehicleTableForDisposal(){
        disposeVehicle_tbl.getItems().clear();
        disposeVehicle_tbl.getColumns().removeAll(disposeVehicle_tbl.getColumns());

        TableColumn<Log1_AssetVehiclesClassfiles, String> type = new TableColumn<>("Vehicle Type");
        TableColumn<Log1_AssetVehiclesClassfiles, String> model = new TableColumn<>("Model");
        TableColumn<Log1_AssetVehiclesClassfiles, String> color = new TableColumn<>("Color");
        TableColumn<Log1_AssetVehiclesClassfiles, String> value = new TableColumn<>("Current Value");
        

        type.setCellValueFactory((param) -> param.getValue().VehicleType);
        model.setCellValueFactory((param) -> param.getValue().VehicleModel);
        color.setCellValueFactory((param) -> param.getValue().VehicleColor);
        value.setCellValueFactory((param) -> param.getValue().CurrentPrice);
        

        disposeVehicle_tbl.getColumns().addAll(type, model, color, value);
    }
    public void callEquipmentDataForDisposal(){
        Log1_AssetEquipmentModel equipmentModel = new Log1_AssetEquipmentModel();
         ObservableList<Log1_AssetEquipmentClassfiles> equipment = FXCollections.observableArrayList();
          
            List b = equipmentModel.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                equipment.add(new Log1_AssetEquipmentClassfiles(
                
                String.valueOf(hm.get("EquipmentID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("EquipmentName")),
                String.valueOf(hm.get("EquipmentRoomNumber")),
                String.valueOf(hm.get("EquipmentType")),
                String.valueOf(hm.get("EquipmentBrand")),
                String.valueOf(hm.get("EquipmentPurchasedPrice")),
                String.valueOf(hm.get("EquipmentPurchasedDate")),
                String.valueOf(hm.get("EquipmentLifeSpan")),
                String.valueOf(hm.get("EquipmentWarranty")),
                String.valueOf(hm.get("CurrentPrice")),
                String.valueOf(hm.get("PriceUpdatedAt"))
                ));       
        }
        disposeEquipment_tbl.getItems().clear();
        disposeEquipment_tbl.setItems(equipment);
    }
    public void renderDisposeAssetTable(){
        DisposedAssets_tbl.getItems().clear();
        DisposedAssets_tbl.getColumns().removeAll(DisposedAssets_tbl.getColumns());

        TableColumn<Log1_AssetDisposalClassfiles, String> Name = new TableColumn<>("Asset Name");
        TableColumn<Log1_AssetDisposalClassfiles, String> Value = new TableColumn<>("Disposal Value");
        TableColumn<Log1_AssetDisposalClassfiles, String> Qty = new TableColumn<>("Quantity");
        TableColumn<Log1_AssetDisposalClassfiles, String> Date = new TableColumn<>("Disposal Date");
        TableColumn<Log1_AssetDisposalClassfiles, String> Note = new TableColumn<>("Note");

        Name.setCellValueFactory((param) -> param.getValue().AssetName);
        Value.setCellValueFactory((param) -> param.getValue().DisposalValue);
        Qty.setCellValueFactory((param) -> param.getValue().Quantity);
        Date.setCellValueFactory((param) -> param.getValue().DisposalDate);
        Note.setCellValueFactory((param) -> param.getValue().Note);

        DisposedAssets_tbl.getColumns().addAll(Name, Value, Qty, Date, Note);
    }
    public void callDisposeAssetData(){
        Log1_AssetDisposalModel model = new Log1_AssetDisposalModel();
         ObservableList<Log1_AssetDisposalClassfiles> table = FXCollections.observableArrayList();
          
            List b = model.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table.add(new Log1_AssetDisposalClassfiles(
                
                String.valueOf(hm.get("DisposalID")),
                String.valueOf(hm.get("AssetName")),
                String.valueOf(hm.get("DisposalValue")),
                String.valueOf(hm.get("Quantity")),
                String.valueOf(hm.get("DisposalDate")),
                String.valueOf(hm.get("Note"))
                
                ));       
        }
        DisposedAssets_tbl.getItems().clear();
        DisposedAssets_tbl.setItems(table);
    }
    public void callVehicleDataforDisposal(){
         Log1_AssetVehiclesModel assetVehicle = new Log1_AssetVehiclesModel();
         ObservableList<Log1_AssetVehiclesClassfiles> vehicle = FXCollections.observableArrayList();
          
            List b = assetVehicle.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                vehicle.add(new Log1_AssetVehiclesClassfiles(
                
                String.valueOf(hm.get("VehicleID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("AssetCategory")),
                String.valueOf(hm.get("VehicleType")),
                String.valueOf(hm.get("VehicleModel")),
                String.valueOf(hm.get("VehicleColor")),
                String.valueOf(hm.get("VehicleSerialNumber")),
                String.valueOf(hm.get("VehicleChassisNumber")),
                String.valueOf(hm.get("VehicleYearBought")),
                String.valueOf(hm.get("VehicleWarrantyDate")),
                String.valueOf(hm.get("VehiclePurchasedPrice")),
                String.valueOf(hm.get("VehicleFuelType")),
                String.valueOf(hm.get("VehicleUsability")),
                String.valueOf(hm.get("VehicleStatus")),
                String.valueOf(hm.get("PriceUpdatedAt")),
                String.valueOf(hm.get("CurrentPrice"))
                ));       
        }
        disposeVehicle_tbl.getItems().clear();
        disposeVehicle_tbl.setItems(vehicle);
    } 
    @FXML
    private void selectEquipmentForDisposal(MouseEvent event) {
        disposeAssetName_txt.setText(disposeEquipment_tbl.getSelectionModel().getSelectedItem().getEquipmentName());
        disposalValue_txt.setText(disposeEquipment_tbl.getSelectionModel().getSelectedItem().getCurrentPrice());
        AssetID_txt.setText(disposeEquipment_tbl.getSelectionModel().getSelectedItem().getEquipmentID());
    }

    @FXML
    private void selecWarehouseForDisposal_txt(MouseEvent event) {
    }

    @FXML
    private void selectVehicleForDisposal(MouseEvent event) {
        disposeAssetName_txt.setText(disposeVehicle_tbl.getSelectionModel().getSelectedItem().getVehicleType());
        disposalValue_txt.setText(disposeVehicle_tbl.getSelectionModel().getSelectedItem().getCurrentPrice());
        AssetID_txt.setText(disposeVehicle_tbl.getSelectionModel().getSelectedItem().getVehicleID());
    }

    @FXML
    private void displayCategoryforDisposal_combox(ActionEvent event) {
        String AssetForDisposal = selectAssetForDisposal_combox.getValue();
        
        if(AssetForDisposal=="Equipment"){
            disposeEquipment_tbl.setVisible(true);
            disposeWarehouseSupply_tbl.setVisible(false);
            disposeVehicle_tbl.setVisible(false);
            quantity_txt.setText("0");
            quantity_txt.setEditable(true);
            disposeEquipment_btn.setVisible(true);
            disposeEquipment_btn.setDisable(false);
            disposeVehicle_btn.setVisible(false);
            disposeVehicle_btn.setDisable(true);
        }else if(AssetForDisposal=="Vehicle"){
            disposeEquipment_tbl.setVisible(false);
            disposeWarehouseSupply_tbl.setVisible(false);
            disposeVehicle_tbl.setVisible(true);
            quantity_txt.setText("1");
            quantity_txt.setEditable(false);
            disposeEquipment_btn.setVisible(false);
            disposeEquipment_btn.setDisable(true);
            disposeVehicle_btn.setDisable(false);
            disposeVehicle_btn.setVisible(true);
        }
        else if(AssetForDisposal=="Supply"){
            disposeEquipment_tbl.setVisible(false);
            disposeWarehouseSupply_tbl.setVisible(true);
            disposeVehicle_tbl.setVisible(false);
            quantity_txt.setText("0");
            quantity_txt.setEditable(true);
        }
    }

    private void disposeEquipment() {
        String note = disposalNote_txt.getText();
        String qty = quantity_txt.getText();
        
        Boolean flag = note.isEmpty() || qty.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_AssetEquipmentModel emodel = new Log1_AssetEquipmentModel();
        Log1_AssetDisposalModel model = new Log1_AssetDisposalModel();
        try{String [][] coa_table ={
            {"AssetName",disposeAssetName_txt.getText()},
            {"DisposalValue", disposalValue_txt.getText()},
            {"Quantity", quantity_txt.getText()},
            {"DisposalDate", disposalDate_txt.getEditor().getText()},
            {"Note", disposalNote_txt.getText()}
        
            };
            if(model.insert(coa_table)){
                AlertMaker.showSimpleAlert("", ""+ disposeAssetName_txt.getText()+" has been disposed.");
                    emodel.delete().where(new Object[][]{
                    {"EquipmentID", "=", AssetID_txt.getText()}
                    }).executeUpdate();
                    int x=Integer.parseInt(AssetEquipmentCount_txt.getText());
                    String answer=String.valueOf(x-1);
                    assetCount.update(new Object[][]{
                        {"AssetEquipment",answer}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                clear();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void disposeVehicle() {
        String note = disposalNote_txt.getText();
        String qty = quantity_txt.getText();
        
        Boolean flag = note.isEmpty() || qty.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_AssetEquipmentModel emodel = new Log1_AssetEquipmentModel();
        Log1_AssetDisposalModel model = new Log1_AssetDisposalModel();
        try{String [][] coa_table ={
            {"AssetName",disposeAssetName_txt.getText()},
            {"DisposalValue", total_txt.getText()},
            {"Quantity", quantity_txt.getText()},
            {"DisposalDate", disposalDate_txt.getEditor().getText()},
            {"Note", disposalNote_txt.getText()}
            };
            if(model.insert(coa_table)){
                AlertMaker.showSimpleAlert("", ""+ disposeAssetName_txt.getText()+" has been disposed.");
                    emodel.delete().where(new Object[][]{
                    {"VehicleID", "=", AssetID_txt.getText()}
                    }).executeUpdate();
                    int x=Integer.parseInt(AssetVehicleCount_txt.getText());
                    String answer=String.valueOf(x-1);
                    assetCount.update(new Object[][]{
                        {"AssetVehicle",answer}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                clear();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clear(){
            disposalNote_txt.setText("");
                disposalDate_txt.getEditor().setText("");
                disposeAssetName_txt.setText("0");
                disposalValue_txt.setText("0");
                quantity_txt.setText("0");
                total_txt.setText("0");
                quantity_txt.setEditable(true);
    }
    
    @FXML
    private void calcalatetotal_txt(KeyEvent event) {
        int x = Integer.parseInt(disposalValue_txt.getText());
        int y = Integer.parseInt(quantity_txt.getText());
        
        String ans = String.valueOf(x*y);
        
        total_txt.setText(ans);
    }
    public void loadAssetEquipmentCount(){
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetEquipmentCount_txt.setText((String.valueOf(hash.get("AssetEquipment"))));
        });
    }
    public void loadAssetVehicleCount(){
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetVehicleCount_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
        });
    }

   

    

}
