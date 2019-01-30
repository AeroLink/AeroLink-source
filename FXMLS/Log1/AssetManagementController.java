package FXMLS.Log1;

import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetFacilityClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetLandClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetVehiclesClassfiles;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AssetManagementController implements Initializable {
    
    ObservableList<String> selectAssetType = FXCollections.observableArrayList("Land","Building","Facility","Vehicle");
    ObservableList<String> AllAssetForViewing = FXCollections.observableArrayList("Land","Building","Facility","Vehicle","Equipment","Supplies");
    ObservableList<String> asd = FXCollections.observableArrayList("Occupied","Vacant");
    
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
    private JFXTextField DepreciationVehicleLastUpdatedPrice_txt1;
    @FXML
    private JFXTextField DepreciationVehiclePreviousPrice_txt1;
    @FXML
    private TableView<Log1_AssetVehiclesClassfiles> VehicleDepreciation_tbl;
    @FXML
    private TableView<?> equipmentTableForDepreciation_tbl;
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        computeDepreciationValue_pane.setDisable(true);
        DepreciationVehicleDetails_window.setDisable(true);
        selectAssetType_combox.setItems(selectAssetType);
        selectAssetForMonitoring_combox.setItems(AllAssetForViewing);
        renderLandTable();
        callLandData();
        renderBuildingTable();
        callBuildingData();
        renderFacilityTable();
        callFacilityData();
        renderVehicleTable();
        callVehicleData();
        MonitoringLand_tbl.setVisible(false);
        MonitoringBuilding_tbl.setVisible(false);
        MonitoringFacility_tbl.setVisible(false);
        MonitoringVehicle_tbl.setVisible(false);
        renderLandTableForMonitoring();
        callLandDataForMonitoring();
        renderBuildingTableForMonitoring();
        callBuildingDataForMonitoring();
        renderFacilityTableForMonitoring();
        callFacilityDataForMonitoring();
        renderVehicleTableForMonitoring();
        callVehicleDataForMonitoring();
        loadAssetCount();
        totalOfAsset();
//        tite_combox.setVisible(false);
//        searchItem();
        renderLandTableForAppraisal();
        callLandDataForAppraisal();
        
        AppraisalAssetDetails.setDisable(true);
        AppraisalValueUpdate.setDisable(true);
        
        AppraisalLandName_txt.setVisible(false);
        AppraisalLandAddress_txt.setVisible(false);
        AppraisalLandArea_txt.setVisible(false);
        AppraisalLandPricePerSqMeters_txt.setVisible(false);
        AppraisalLandPurchasedYear_txt.setVisible(false);
        AppraisalLandStatus_txt.setVisible(false);
        
        StartValueAppraisal.setOnMouseClicked(e->startAppraisal());
        cancelAppraisal_btn.setOnMouseClicked(e->cancelAppraisal());
        LandIDforAppraisal_txt.setVisible(false);
        saveAppraisal_btn.setDisable(true);
        AppraisalPercent_txt.setText("0");
        AppraisalPercent_txt.setOnKeyReleased(e->computeAppraisal());
        computeAppraisal_btn.setOnMouseClicked(e->computeAppraisalBookValue());
        //Deppreciation
        updateDepreciationvalue_btn.setDisable(true);
        renderVehicleTableForDepreciation();
        callVehicleDataforDepreciation();
        disableVehicleTxtFields();
        StartDepreciation_btn.setOnMouseClicked(e-> startDepreciation());
        computeDepreciationValue_pane.setDisable(true);
        DepreciationVehicleDetails_window.setDisable(true);
        DepreciationVehicleID_txt.setVisible(false);
        DepreciationLifeTimeYears_txt.setOnKeyReleased(e->computeDepreciationValue());
        computeDepreciaton_btn.setOnMouseClicked(e-> computeDepreciationBookValue());
        salvageValue_txt.setOnKeyReleased(e->computeDepreciationValue());
        DepreciationVehicleID_txt.setVisible(true);
        
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
    
    public void computeAppraisal(){
        int totalLandPrice = Integer.parseInt(AppraisalLandTotalPurchasedValue_txt.getText());
        int Percentage =Integer.parseInt(AppraisalPercent_txt.getText());
        
        int PercentageAndTotalLandPrice = Integer.valueOf(totalLandPrice*Percentage);
        
        String appraisalPerYear = String.valueOf(PercentageAndTotalLandPrice/100);
        AppraisalPerYear_txt.setText(appraisalPerYear);
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
    }
    
    public void startAppraisal(){
        AppraisalLandName_txt.setVisible(true);
        AppraisalLandAddress_txt.setVisible(true);
        AppraisalLandArea_txt.setVisible(true);
        AppraisalLandPricePerSqMeters_txt.setVisible(true);
        AppraisalLandPurchasedYear_txt.setVisible(true);
        AppraisalLandStatus_txt.setVisible(true);
        
        AppraisalAssetDetails.setDisable(false);
        AppraisalValueUpdate.setDisable(false);
        
        int x = Integer.parseInt(AppraisalLandArea_txt.getText());
        int y = Integer.parseInt(AppraisalLandPricePerSqMeters_txt.getText());
        String answer = String.valueOf(x*y);
        AppraisalLandTotalPurchasedValue_txt.setText(answer);
    }
    
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
    
    @FXML
    private void showTableView(ActionEvent event) {
        String AssetTable = selectAssetForMonitoring_combox.getValue();
        
        if(AssetTable=="Land"){
            MonitoringLand_tbl.setVisible(true);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(false);
        }else if(AssetTable=="Building"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(true);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(false);
        }else if(AssetTable=="Facility"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(true);
            MonitoringVehicle_tbl.setVisible(false);
        }else if(AssetTable=="Vehicle"){
            MonitoringLand_tbl.setVisible(false);
            MonitoringBuilding_tbl.setVisible(false);
            MonitoringFacility_tbl.setVisible(false);
            MonitoringVehicle_tbl.setVisible(true);
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
    public void renderLandTableForMonitoring() {
        MonitoringLand_tbl.getItems().clear();
        MonitoringLand_tbl.getColumns().removeAll(MonitoringLand_tbl.getColumns());

        TableColumn<Log1_AssetLandClassfiles, String> LandName = new TableColumn<>("Land Name");
        TableColumn<Log1_AssetLandClassfiles, String> LandArea = new TableColumn<>("Land Area");
        TableColumn<Log1_AssetLandClassfiles, String> LandAddress = new TableColumn<>("Address");
        TableColumn<Log1_AssetLandClassfiles, String> LandPricePerSqMeters = new TableColumn<>("Price(per sq meters)");
        TableColumn<Log1_AssetLandClassfiles, String> LandPurchasedDate = new TableColumn<>("Purchased Date");
        TableColumn<Log1_AssetLandClassfiles, String> LandStatus = new TableColumn<>("Status");
        
        LandName.setCellValueFactory((param) -> param.getValue().LandName);
        LandArea.setCellValueFactory(param -> param.getValue().LandArea);
        LandAddress.setCellValueFactory(param -> param.getValue().LandAddress);
        LandPricePerSqMeters.setCellValueFactory(param -> param.getValue().LandPricePerSqMeters);
        LandPurchasedDate.setCellValueFactory(param -> param.getValue().LandPurchasedDate);
        LandStatus.setCellValueFactory(param -> param.getValue().LandStatus);

        MonitoringLand_tbl.getColumns().addAll(LandName, LandArea, LandAddress, LandPricePerSqMeters
        ,LandPurchasedDate, LandStatus);
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

        MonitoringVehicle_tbl.getColumns().addAll(VehicleType, VehicleModel, VehicleColor, VehicleSerialNumber
        ,VehicleChassisNumber, VehicleYearBought, VehicleWarrantyDate, VehiclePurchasedPrice,
        VehicleFuelType, VehicleUsability, VehicleStatus, BuildingLocated);

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
                String.valueOf(hm.get("LandStatus"))
                ));       
        }
        land_tbl.getItems().clear();
        land_tbl.setItems(Land);
        
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
                String.valueOf(hm.get("LandStatus"))
                ));       
        }
        MonitoringLand_tbl.getItems().clear();
        MonitoringLand_tbl.setItems(Land);
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
                String.valueOf(hm.get("VehicleStatus"))
                ));       
        }
        Vehicle_tbl.getItems().clear();
        Vehicle_tbl.setItems(vehicle);
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
                String.valueOf(hm.get("VehicleStatus"))
                ));       
        }
        MonitoringVehicle_tbl.getItems().clear();
        MonitoringVehicle_tbl.setItems(vehicle);
    }
    
//    public void displayEquipmentData(){
//        equipment_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));
//        equipmentType_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentType"));
//        EWarrantydate_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentWarrantyDate"));
//        equipmentlocation_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentLocation"));
//        equipmentBuilding_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
//        equipmentPrice_col.setCellValueFactory(new PropertyValueFactory<>("EquipmentPrice"));
//    }

//    public void callVehicleData() {
//        Log1_AssetVehiclesModel AE = new Log1_AssetVehiclesModel();
//         ObservableList<Log1_AssetVehiclesClassfiles> vehicles = FXCollections.observableArrayList();
//          
//            List b = AE.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "=", "BuildingID").get();
//            
//            for(Object d : b)
//                {
//                    //rs = hm
//                HashMap hm = (HashMap) d;   //exquisite casting
//                
//                vehicles.add(new Log1_AssetVehiclesClassfiles(
//                
//                String.valueOf(hm.get("VehicleID")),
//                String.valueOf(hm.get("BuildingName")),
//                String.valueOf(hm.get("VehicleDescription")),
//                String.valueOf(hm.get("VehicleType")),
//                String.valueOf(hm.get("VehiclePrice")),
//                String.valueOf(hm.get("ChassisNumber")),
//                String.valueOf(hm.get("DateBought")),
//                String.valueOf(hm.get("VehicleLocation")),
//                String.valueOf(hm.get("VehicleManufacturer")),
//                String.valueOf(hm.get("VehicleWarranty")),
//                        String.valueOf(hm.get("VehicleStatus"))
//                ));       
//        }
//        vehicle_tbl.setItems(vehicles);
//        
//    }

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
            building_tbl.setDisable(false);
            building_tbl.setVisible(true);
            
            Vehicle_tbl.setDisable(true);
            Vehicle_tbl.setVisible(false);
            
            land_tbl.setDisable(true);
            land_tbl.setVisible(false);
            
            facility_tbl.setDisable(true);
            facility_tbl.setVisible(false);
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
            AnchorPane pane3 = FXMLLoader.load(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddFacility.fxml"));
            registrationForm_pane.getChildren().setAll(pane3);
        }
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
                String.valueOf(hm.get("LandStatus"))
                ));       
        }
        LandLiquidation_tbl.getItems().clear();
        LandLiquidation_tbl.setItems(Land);
    }
    
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
                String.valueOf(hm.get("VehicleStatus"))
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
        DepreciationVehicleID_txt.setVisible(true);
    }

    private void startDepreciation() {
        computeDepreciationValue_pane.setDisable(false);
        DepreciationVehicleDetails_window.setDisable(false);
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
}
