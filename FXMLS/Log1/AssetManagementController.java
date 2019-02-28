package FXMLS.Log1;

import FXMLS.Log1.AssetManagement.Modal.DisposeAssetController;
import FXMLS.Log1.ClassFiles.Log1_AssetClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetDisposalClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetEquipmentClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetLandClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetVehiclesClassfiles;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetDisposalModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetLandModel;
import Model.Log1.Log1_AssetModel;
import Model.Log1.Log1_AssetVehicleModel;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AssetManagementController implements Initializable {
    
    ObservableList<String> selectAssetType = FXCollections.observableArrayList("Land","Building","Facility","Vehicle","Equipment");
    ObservableList<String> AllAssetForViewing = FXCollections.observableArrayList("Land","Building","Facility","Vehicle","Equipment","Supplies");
    ObservableList<String> AssetForDisposal = FXCollections.observableArrayList("Equipment","Vehicle");
    
    Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
    
    long DummyCount = 0;
    long GlobalCount = 0;
    
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
    private Label totalNumberOfAssets_txt;
    
    
    
    @FXML
    private TextField AppraisalLandTotalPurchasedValue_txt;
    @FXML
    private TextField AppraisalPercent_txt;
    @FXML
    private TextField YearsHasBeenUsed_txt;
    @FXML
    private JFXButton saveAppraisal_btn;
    @FXML
    private JFXButton cancelAppraisal_btn;
    @FXML
    private Label LandIDforAppraisal_txt;
    @FXML
    private JFXButton computeAppraisal_btn;
    
    
    @FXML
    private Label AppaisalBookValue_txt;
    
    
    
    @FXML
    private BorderPane DepreciationVehicleDetails_pane1;
    @FXML
    private JFXButton computeDepreciatonequip_btn;
    @FXML
    private Label DepreciationEquipBookValue_txt;
    @FXML
    private Label dateTodayEquipDp_txt;
    @FXML
    private Label DepreciationEquipmentID_txt;
    @FXML
    private TitledPane LandList_pane;
    @FXML
    private TableView<Log1_AssetClassfiles> landForTotal_tbl;
    @FXML
    private TableView<Log1_AssetClassfiles> vehicletotal_tbl;
    @FXML
    private TableView<Log1_AssetClassfiles> equipmentTotal_tbl;
    @FXML
    private TextField totalLandValue_txt;
    @FXML
    private TextField totallVehicle_value;
    @FXML
    private TextField totalEquipmentValue_txt;
    @FXML
    private JFXTextField totaltotaltotal_txt;
    @FXML
    private TableView<Log1_AssetClassfiles> Asset_tbl;
    @FXML
    private Label totalNumberOfAssets_lbl;
    @FXML
    private Label dateAppraisal_lbl;
    @FXML
    private ComboBox<String> AssetCategory_combx;
    @FXML
    private JFXButton searchAsset_btn;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private TitledPane AssetList_pane;
    @FXML
    private TableView<Log1_AssetClassfiles> assetForDp_tbl;
    @FXML
    private TitledPane assetDetails_pane;
    @FXML
    private TitledPane dpCompute_pane;
    @FXML
    private TextField dp_purchVal_txt;
    @FXML
    private JFXButton selectDP_btn;
    @FXML
    private JFXButton saveDP_btn;
    @FXML
    private JFXTextField dp_assetTitle_txt;
    @FXML
    private JFXTextField dp_assetType_txt;
    @FXML
    private JFXTextField dp_assetCateg_txt;
    @FXML
    private JFXTextField dp_assetLspan_txt;
    @FXML
    private JFXTextField dp_assetStats_txt;
    @FXML
    private JFXTextField dp_assetPuchPrce_txt;
    @FXML
    private JFXTextField dp_assetPurchDte_txt;
    @FXML
    private JFXTextField dp_assetLoc_txt;
    @FXML
    private JFXTextField dp_assetWarranty_txt;
    @FXML
    private TextField salvageVal_txt;
    @FXML
    private TextField totalDP_txt1;
    @FXML
    private TextField totalDP_txt2;
    @FXML
    private TextField dp_lifeTime_txt;
    @FXML
    private JFXTextField dp_salvageVal_txt;
    @FXML
    private TextField annualDP_txt2;
    @FXML
    private TextField monthlyDP_txt;
    @FXML
    private TextField annualDP_txt1;
    @FXML
    private TextField monthsPerYr_txt;
    @FXML
    private TableView<Log1_AssetClassfiles> ap_land_tbl;
    @FXML
    private JFXButton startAP_btn;
    @FXML
    private TitledPane APdetails_pane;
    @FXML
    private TitledPane APcompute_pane;
    @FXML
    private JFXTextField ap_assetTitle_txt;
    @FXML
    private JFXTextField ap_assetStats_txt;
    @FXML
    private JFXTextField ap_coreLoc_txt;
    @FXML
    private JFXTextField ap_purchYr_txt;
    @FXML
    private JFXTextField ap_assetdescript_txt;
    @FXML
    private JFXTextField ap_totalPrice_txt;
    @FXML
    private TextField APperYear_txt1;
    @FXML
    private TextField APperYear_txt2;
    @FXML
    private TextField months_txt;
    @FXML
    private TextField APperMonth_txt;
    @FXML
    private TableView<Log1_AssetClassfiles> dispose_tbl;
    @FXML
    private ComboBox<String> dpTblCateg_combx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Asset monitoring and regisration tab
        initable();
        AssetCategory_combx.setItems(selectAssetType);
        refresh_btn.setOnMouseClicked(e->ref());
        searchAsset_btn.setOnMouseClicked(e->search());
         AssetCategory_combx.setValue("Select Asset Category");
        //asset liquidation tab
        displayDate();
        startDPtbl();
        hideDPpane();
        startAPtbl();
        hideAPtabs();
        AppraisalPercent_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        AppraisalPercent_txt.setOnKeyReleased(e->computeAPvals());
        // asset report tab
        totalReport();
        initReportTbl();
        loadTotal();
        startDisptbl();
        //asset disposal tab
        dpTblCateg_combx.setItems(AssetForDisposal);
    }
    
    
    
    //Asset Monitoring and registration tab - start
    @FXML
    private void HandleRegisterEquipment(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddEquipment.fxml"),
                 "", null);
    }

    @FXML
    private void HandleRegisterLand(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddLand.fxml"),
                 "", null);
    }

    @FXML
    private void HandleRegisterVehicle(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddVehicle.fxml"),
                 "", null);
    }

    @FXML
    private void HandleRegisterBuildng(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"),
                 "", null);
    }

    @FXML
    private void HandleRegisterFacility(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddFacility.fxml"),
                 "", null);
    }
    
    @FXML
    private void setBuilding(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/SetBuildingRooms.fxml"),
                 "", null);
    }
    
    public void ref(){
        renderAssetTable();
        displayAssetData();
        displayTotalOfAsset();
        AssetCategory_combx.setValue("Select Asset Category");
    }

    public void initable(){
        renderAssetTable();
        displayAssetData();
        displayTotalOfAsset();
        AssetCategory_combx.setValue("Select Asset Category");
    }
    
    public void displayTotalOfAsset(){        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            totalNumberOfAssets_lbl.setText((String.valueOf(hash.get("AssetTotalOfAll"))));
        });
    }
    
    public void renderAssetTable(){
        Asset_tbl.getItems().clear();
        Asset_tbl.getColumns().removeAll(Asset_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> AssetTitle = new TableColumn<>("Asset Title");
        TableColumn<Log1_AssetClassfiles, String> AssetCategory = new TableColumn<>("Category");
        TableColumn<Log1_AssetClassfiles, String> AssetType = new TableColumn<>("Type");
        TableColumn<Log1_AssetClassfiles, String> AssetSerialNumber = new TableColumn<>("Serial Number");
        TableColumn<Log1_AssetClassfiles, String> AssetStatus = new TableColumn<>("Status");
        TableColumn<Log1_AssetClassfiles, String> AssetCoreLocation = new TableColumn<>("Core Location");
        TableColumn<Log1_AssetClassfiles, String> AssetPurchasedDate = new TableColumn<>("Date Acquired");
        TableColumn<Log1_AssetClassfiles, String> AssetRegisteredDate = new TableColumn<>("Registration Date");

        AssetTitle.setCellValueFactory((param) -> param.getValue().AssetTitle);
        AssetCategory.setCellValueFactory((param) -> param.getValue().AssetCategory);
        AssetType.setCellValueFactory((param) -> param.getValue().AssetType);
        AssetSerialNumber.setCellValueFactory((param) -> param.getValue().AssetSerialNumber);
        AssetStatus.setCellValueFactory((param) -> param.getValue().AssetStatus);
        AssetCoreLocation.setCellValueFactory((param) -> param.getValue().AssetCoreLocation);
        AssetPurchasedDate.setCellValueFactory((param) -> param.getValue().AssetPurchasedDate);
        AssetRegisteredDate.setCellValueFactory((param) -> param.getValue().AssetRegisteredDate);

        Asset_tbl.getColumns().addAll(AssetTitle, AssetCategory, AssetType, AssetSerialNumber
                , AssetStatus, AssetCoreLocation, AssetPurchasedDate, AssetRegisteredDate);
    }
    
//    public void displayAssetData(){
//        CompletableFuture.supplyAsync(() -> {
//            while (Session.CurrentRoute.equals("log1AM")) {
//                try {
//                    assetDB.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
//                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
//                    });
//                Asset_tbl.getItems();
//                if (DummyCount != GlobalCount) {
//                List b = assetDB.orderBy("created_at", Model.Sort.DESC).get();
//                assetFields(b);
//                Asset_tbl.setItems(assetCF);
//                GlobalCount = DummyCount;
//                }
//                Thread.sleep(3000);
//                } catch (Exception ex) {
//                    System.err.println("Exception -> " + ex.getMessage());
//                }
//             }
//            return 0;
//        }, Session.SessionThreads);
//    }
    public void displayAssetData(){
        Log1_AssetModel assetDB = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> assetCF = FXCollections.observableArrayList();
        Asset_tbl.getItems(); 
        List b = assetDB.where(new Object[][]{
            {"disposed","=","No"}
        }).orderBy("created_at", Model.Sort.DESC).get();
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            assetCF.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            ));  
        }
        Asset_tbl.setItems(assetCF);  
        AssetCategory_combx.setValue("Select Asset Category");
    }
    

    public void search(){
        Log1_AssetModel assetDBsearch = new Log1_AssetModel();
        ObservableList<Log1_AssetClassfiles> assetCFsearch = FXCollections.observableArrayList();
            List b = assetDBsearch.where(new Object[][]{
                {"AssetCategory", "=", AssetCategory_combx.getValue()}
            }).andWhere("disposed", "=", "No").orderBy("created_at", Model.Sort.DESC).get();
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            assetCFsearch.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            ));  
        }
        Asset_tbl.setItems(assetCFsearch); 
        AssetCategory_combx.setValue("Select Asset Category");
    }
    
    public void assetFields(List b){
        
    }
    //asset monitoring and registtration tab - end
    
    
    
    //asset liquidation tab - start
    public void displayDate(){
        dispCurrentDate();
    }
    
    public void dispCurrentDate(){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    //get current date time with Date()
    Date date = new Date();
    dateTodayEquipDp_txt.setText(dateFormat.format(date));
    dateAppraisal_lbl.setText(dateFormat.format(date));
    }
    
    public void startDPtbl(){
        renderDPtbl();
        populateDPtbl();
    }
    
    public void renderDPtbl(){
        assetForDp_tbl.getItems().clear();
        assetForDp_tbl.getColumns().removeAll(assetForDp_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> AssetTitle = new TableColumn<>("Asset Title");
        TableColumn<Log1_AssetClassfiles, String> AssetCategory = new TableColumn<>("Category");
        TableColumn<Log1_AssetClassfiles, String> AssetStatus = new TableColumn<>("Status");
        TableColumn<Log1_AssetClassfiles, String> AssetStartingPrice = new TableColumn<>("Starting Value");

        AssetTitle.setCellValueFactory((param) -> param.getValue().AssetTitle);
        AssetCategory.setCellValueFactory((param) -> param.getValue().AssetCategory);
        AssetStatus.setCellValueFactory((param) -> param.getValue().AssetStatus);
        AssetStartingPrice.setCellValueFactory((param) -> param.getValue().AssetPurchasedPrice);

        assetForDp_tbl.getColumns().addAll(AssetTitle, AssetCategory, AssetStatus, AssetStartingPrice);
    }
    
    
    
    Log1_AssetModel assetDBforDP = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> assetForDP = FXCollections.observableArrayList();
    public void populateDPtbl(){
        List b = assetDBforDP.where(new Object[][]{
            {"ForDepreciation", "=", "Yes"}
        }).andWhere("disposed", "=", "No").get();
        assetFieldsForDP(b);
        assetForDp_tbl.setItems(assetForDP);
    }
    public void assetFieldsForDP(List b){
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            assetForDP.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPriceCurrency")+" "+hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("AssetPriceCurrency")+" "+hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetPriceCurrency")+" "+hm.get("AssetSalvageValue"))
            ));  
        }
    }
    
    @FXML
    private void selectAssetForDP(MouseEvent event) {
        selectDP_btn.setDisable(false);
    }
    
    public void hideDPpane(){
        assetDetails_pane.setVisible(false);
        dpCompute_pane.setVisible(false);
    }
    
    @FXML
    private void startDP(ActionEvent event) {
        assetDetails_pane.setVisible(true);
        dpCompute_pane.setVisible(true);
        AssetList_pane.setDisable(true);
        saveDP_btn.setDisable(true);
        dp_assetTitle_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetTitle());
        dp_assetType_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetType());
        dp_assetCateg_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetCategory());
        dp_assetPuchPrce_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedPrice());
        dp_assetPurchDte_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedDate());
        dp_assetLspan_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetLifeSpan());
        dp_assetStats_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetStatus());
        dp_assetLoc_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetCoreLocation());
        dp_assetWarranty_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetWarranty());
        dp_salvageVal_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetSalvageValue());
        

        
        dp_purchVal_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedPrice());
        salvageVal_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetSalvageValue());
        
        Long Cost = Long.parseLong(dp_purchVal_txt.getText().split(" ")[1].replace(",",""));
        Long salvageValue = Long.parseLong(salvageVal_txt.getText().split(" ")[1].replace(",",""));
        
        Long totalDPinLong = Long.valueOf(Cost-salvageValue);
        String totalDPinString = String.valueOf(totalDPinLong);
        
        totalDP_txt1.setText(totalDPinString);
        if (totalDP_txt1.getText().isEmpty()) {
            totalDP_txt1.setText("0");
        }else{
            totalDP_txt1.setText("Php"+" "+NumberFormat.getInstance().format(Long.parseLong(totalDP_txt1.getText().replace(",", ""))));
            totalDP_txt1.end();
        }
        
        totalDP_txt2.setText(totalDP_txt1.getText());
        dp_lifeTime_txt.setText(assetForDp_tbl.getSelectionModel().getSelectedItem().getAssetLifeSpan());
        
        Long totalDepreciation2 = Long.parseLong(totalDP_txt2.getText().split(" ")[1].replace(",",""));
        Long usefulLifeSpan = Long.parseLong(dp_lifeTime_txt.getText().replace(",",""));
        
        Long annualDPinLong = Long.valueOf(totalDepreciation2/usefulLifeSpan);
        String annualDPinString = String.valueOf(annualDPinLong);
        
        annualDP_txt1.setText(annualDPinString);
        if (annualDP_txt1.getText().isEmpty()) {
            annualDP_txt1.setText("0");
        }else{
            annualDP_txt1.setText("Php"+" "+NumberFormat.getInstance().format(Long.parseLong(annualDP_txt1.getText().replace(",", ""))));
            annualDP_txt1.end();
        }
        
        annualDP_txt2.setText(annualDP_txt1.getText());
        Long annualDepreciation2 = Long.parseLong(annualDP_txt2.getText().split(" ")[1].replace(",",""));
        
        Long monthlyDPinLong = Long.valueOf(annualDepreciation2/12);
        String monthlyDPinString = String.valueOf(monthlyDPinLong);
        
        monthlyDP_txt.setText(monthlyDPinString);
        if (monthlyDP_txt.getText().isEmpty()) {
            monthlyDP_txt.setText("0");
        }else{
            monthlyDP_txt.setText("Php"+" "+NumberFormat.getInstance().format(Long.parseLong(monthlyDP_txt.getText().replace(",", ""))));
            monthlyDP_txt.end();
        }
        
        
    }
    
    
    @FXML
    private void cancelDP(ActionEvent event) {
        assetDetails_pane.setVisible(false);
        dpCompute_pane.setVisible(false);
        AssetList_pane.setDisable(false);
    }
    
    
    //appraisal
    
    
    public void startAPtbl(){
        renderAPtbl();
        populateAPtbl();
    }
    
    public void renderAPtbl(){
        ap_land_tbl.getItems().clear();
        ap_land_tbl.getColumns().removeAll(ap_land_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> AssetTitle = new TableColumn<>("Asset Title");
        TableColumn<Log1_AssetClassfiles, String> AssetStatus = new TableColumn<>("Status");
        TableColumn<Log1_AssetClassfiles, String> StartingValue = new TableColumn<>("Starting Value");

        AssetTitle.setCellValueFactory((param) -> param.getValue().AssetTitle);
        AssetStatus.setCellValueFactory((param) -> param.getValue().AssetStatus);
        StartingValue.setCellValueFactory((param) -> param.getValue().AssetPurchasedPrice);

        ap_land_tbl.getColumns().addAll(AssetTitle, AssetStatus, StartingValue);
    }
    
    Log1_AssetModel AssetForAP = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> AssetAPCF = FXCollections.observableArrayList();
    public void populateAPtbl(){
            List b = AssetForAP.where(new Object [][]{
                {"ForDepreciation", "=", "ForAP"}
            }).andWhere("disposed", "=", "No").get();
            assetFieldsForAP(b);
            ap_land_tbl.setItems(AssetAPCF);
    }
    
    public void assetFieldsForAP(List b){
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            AssetAPCF.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPriceCurrency")+" "+hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("AssetPriceCurrency")+" "+hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            ));  
        }
    }
    
    public void hideAPtabs(){
        APdetails_pane.setVisible(false);
        APcompute_pane.setVisible(false);
        saveAppraisal_btn.setDisable(true);
    }
    
    @FXML
    private void selectLandForAppraisal(MouseEvent event) {
        startAP_btn.setDisable(false);
    }
    @FXML
    private void startAP(ActionEvent event) {
        APdetails_pane.setVisible(true);
        APcompute_pane.setVisible(true);
        LandList_pane.setDisable(true);
        ap_assetTitle_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetTitle());
        ap_assetStats_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetStatus());
        ap_assetdescript_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetDescription());
        ap_coreLoc_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetCoreLocation());
        ap_purchYr_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedDate());
        ap_totalPrice_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedPrice());
        AppraisalLandTotalPurchasedValue_txt.setText(ap_land_tbl.getSelectionModel().getSelectedItem().getAssetPurchasedPrice());
        
//        if (ap_totalPrice_txt.getText().isEmpty()) {
//            ap_totalPrice_txt.setText("0");
//        }else{
//            ap_totalPrice_txt.setText("Php " +" "+NumberFormat.getInstance().format(Long.parseLong(ap_totalPrice_txt.getText().replace(",", ""))));
//            ap_totalPrice_txt.end();
//        }
//        if (AppraisalLandTotalPurchasedValue_txt.getText().isEmpty()) {
//            AppraisalLandTotalPurchasedValue_txt.setText("0");
//        }else{
//            AppraisalLandTotalPurchasedValue_txt.setText("Php " +" "+NumberFormat.getInstance().format(Long.parseLong(AppraisalLandTotalPurchasedValue_txt.getText().replace(",", ""))));
//            AppraisalLandTotalPurchasedValue_txt.end();
//        }
        
    }
    
    public void computeAPvals(){
        if (AppraisalPercent_txt.getText().isEmpty()) {
            AppraisalPercent_txt.setText("0");
            APperYear_txt1.setText("0");
            APperYear_txt2.setText("0");
            APperMonth_txt.setText("0");
        }else{
        Long landTotalP = Long.parseLong(AppraisalLandTotalPurchasedValue_txt.getText().replace(",","").split(" ")[1]);
        Long percent = Long.parseLong(AppraisalPercent_txt.getText().replace(",",""));
        
        Long x = Long.valueOf(landTotalP*percent);
        Long apPerYearD = Long.valueOf(x/100);
        String apPerYearS = String.valueOf(apPerYearD);
        APperYear_txt1.setText(apPerYearS);
        if (APperYear_txt1.getText().isEmpty()) {
            APperYear_txt1.setText("0");
        }else{
            APperYear_txt1.setText("Php" +" "+NumberFormat.getInstance().format(Long.parseLong(APperYear_txt1.getText().replace(",", ""))));
            APperYear_txt1.end();
        }
        
        APperYear_txt2.setText(APperYear_txt1.getText());
        Long apPeryear2 = Long.parseLong(APperYear_txt2.getText().replace(",","").split(" ")[1]);
        
        Long apPerMonth = Long.valueOf(apPeryear2/12);
        String apPermonthS = String.valueOf(apPerMonth);
        
        APperMonth_txt.setText(apPermonthS);
        if (APperMonth_txt.getText().isEmpty()) {
            APperMonth_txt.setText("0");
        }else{
            APperMonth_txt.setText("Php" +" " +NumberFormat.getInstance().format(Long.parseLong(APperMonth_txt.getText().replace(",", ""))));
            APperMonth_txt.end();
        }
        }
    }
    @FXML
    private void cancelAP(ActionEvent event) {
        APdetails_pane.setVisible(false);
        APcompute_pane.setVisible(false);
        saveAppraisal_btn.setDisable(true);
        LandList_pane.setDisable(false);
    }
    //asset liquidation - end
   
 
    
    
    
    //asset report - start
    @FXML
    private void refReport(Event event) {
        totalReport();
    }
    
    public void totalReport(){
        loadAssetCount();
        totalOfAsset();
    }
    public void loadAssetCount(){        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            totalOfBuildings_txt.setText((String.valueOf(hash.get("AssetBuilding"))));
            totalLand_txt.setText((String.valueOf(hash.get("AssetLand"))));
            totalFacilities_txt.setText((String.valueOf(hash.get("AssetFacility"))));
            totalVehicle_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
            totalOfEquipments_txt.setText((String.valueOf(hash.get("AssetWarehouseEquipment"))));  
        });
    }
    public void totalOfAsset(){
        int totalBuilding =Integer.parseInt(totalOfBuildings_txt.getText());
        int totalLand =Integer.parseInt(totalLand_txt.getText());
        int totalFacility =Integer.parseInt(totalFacilities_txt.getText());
        int totalVehicle =Integer.parseInt(totalVehicle_txt.getText());
        int totalEquipments =Integer.parseInt(totalOfEquipments_txt.getText());
        
        String answer=String.valueOf(totalBuilding+totalLand+totalFacility
        +totalVehicle+totalEquipments);
        
        totalNumberOfAssets_txt.setText(answer);
    }
    public void initReportTbl(){
        renderLandTblfortotal();
        populateLandtblTotal();
        populateVHtblTotal();
        populateEqtblTotal();
    }
    
    public void renderLandTblfortotal(){
        //land tbl
        landForTotal_tbl.getItems().clear();
        landForTotal_tbl.getColumns().removeAll(landForTotal_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> Land = new TableColumn<>("Land Title");
        TableColumn<Log1_AssetClassfiles, String> landPrice = new TableColumn<>("Price");
        
        Land.setCellValueFactory((param) -> param.getValue().AssetTitle);
        landPrice.setCellValueFactory((param) -> param.getValue().CurrentPrice);
        
        landForTotal_tbl.getColumns().addAll(Land, landPrice);
        //vehicle tbl
        vehicletotal_tbl.getItems().clear();
        vehicletotal_tbl.getColumns().removeAll(vehicletotal_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> vh = new TableColumn<>("Vehicle Type");
        TableColumn<Log1_AssetClassfiles, String> vhPrice = new TableColumn<>("Price");
        
        vh.setCellValueFactory((param) -> param.getValue().AssetTitle);
        vhPrice.setCellValueFactory((param) -> param.getValue().CurrentPrice);
        
        vehicletotal_tbl.getColumns().addAll(vh, vhPrice);
        //equipment tbl
        equipmentTotal_tbl.getItems().clear();
        equipmentTotal_tbl.getColumns().removeAll(equipmentTotal_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> eq = new TableColumn<>("Equipment");
        TableColumn<Log1_AssetClassfiles, String> price = new TableColumn<>("Price");
        
        eq.setCellValueFactory((param) -> param.getValue().AssetTitle);
        price.setCellValueFactory((param) -> param.getValue().CurrentPrice);
        
        equipmentTotal_tbl.getColumns().addAll(eq, price);
    }
    //land tbl
    Log1_AssetModel LandDBforTotal = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> LandCFforTotal = FXCollections.observableArrayList();
    public void populateLandtblTotal(){
            List b = LandDBforTotal.where(new Object[][]{
                {"AssetCategory", "=", "Land"}
            }).andWhere("disposed", "=", "No").get();
            LandFieldsForTotal(b);
            landForTotal_tbl.setItems(LandCFforTotal);
    }
    public void LandFieldsForTotal(List b){
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            LandCFforTotal.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("AssetPriceCurrency") + " " + hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            )); 
        }
    }
    //vehicle tbl
    Log1_AssetModel VhDBforTotal = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> VhCFforTotal = FXCollections.observableArrayList();
    public void populateVHtblTotal(){
            List b = VhDBforTotal.where(new Object[][]{
                {"AssetCategory", "=", "Vehicle"}
            }).andWhere("disposed", "=", "No").get();
            VhFieldsForTotal(b);
            vehicletotal_tbl.setItems(VhCFforTotal);
    }
    public void VhFieldsForTotal(List b){
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            VhCFforTotal.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("AssetPriceCurrency") + " " + hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            )); 
        }
    }
    //equipment tbl
    Log1_AssetModel EqDBforTotal = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> EqCFforTotal = FXCollections.observableArrayList();
    public void populateEqtblTotal(){
            List b = EqDBforTotal.where(new Object[][]{
                {"AssetCategory", "=", "Equipment"}
            }).andWhere("disposed", "=", "No").get();
            EqFieldsForTotal(b);
            equipmentTotal_tbl.setItems(EqCFforTotal);
    }
    public void EqFieldsForTotal(List b){
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            EqCFforTotal.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("AssetPriceCurrency") + " " + (hm.get("CurrentPrice"))),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            )); 
        }
    }
    
    //totalization
    public void loadTotal(){
        LandTotal();
        VehicleTotal();
        eqTotal();
        totaltotaltotal();
    }
    
    long total1 = 0;
    public void LandTotal(){
         for(int i = 0; i<landForTotal_tbl.getItems().size(); i++ ){
//             int amount = Integer.parseInt(landForTotal_tbl.getItems().get(i).
//                     CurrentPrice.getValue());
             long apPeryear2 = Long.parseLong(landForTotal_tbl.getItems().get(i).
                     CurrentPrice.getValue().replace(",","").split(" ")[1]);
             total1 = total1+ apPeryear2;
         }
         totalLandValue_txt.setText(String.valueOf(total1));
         if (totalLandValue_txt.getText().isEmpty()) {
            totalLandValue_txt.setText("0");
        }else{
            totalLandValue_txt.setText("Php" + " " + NumberFormat.getInstance().format(Long.parseLong(totalLandValue_txt.getText().replace(",", ""))));
            totalLandValue_txt.end();
        }
     }
    long total2 = 0;
    public void VehicleTotal(){
         for(int i = 0; i<vehicletotal_tbl.getItems().size(); i++ ){
//             int amount = Integer.parseInt(landForTotal_tbl.getItems().get(i).
//                     CurrentPrice.getValue());
             long amount = Long.parseLong(vehicletotal_tbl.getItems().get(i).
                     CurrentPrice.getValue().replace(",","").split(" ")[1]);
             total2 = total2+ amount;
         }
         totallVehicle_value.setText(String.valueOf(total2));
         if (totallVehicle_value.getText().isEmpty()) {
                totallVehicle_value.setText("0");
        }else{
            totallVehicle_value.setText("Php" + " " + NumberFormat.getInstance().format(Long.parseLong(totallVehicle_value.getText().replace(",", ""))));
            totallVehicle_value.end();
        }
     }
    long total3 = 0;
    public void eqTotal(){
         for(int i = 0; i<equipmentTotal_tbl.getItems().size(); i++ ){
//             int amount = Integer.parseInt(landForTotal_tbl.getItems().get(i).
//                     CurrentPrice.getValue());
             long amount = Long.parseLong(equipmentTotal_tbl.getItems().get(i).
                     CurrentPrice.getValue().replace(",","").split(" ")[1]);
             total3 = total3+ amount;
         }
         totalEquipmentValue_txt.setText(String.valueOf(total3));
        if (totalEquipmentValue_txt.getText().isEmpty()) {
                totalEquipmentValue_txt.setText("0");
        }else{
            totalEquipmentValue_txt.setText("Php" + " " + NumberFormat.getInstance().format(Long.parseLong(totalEquipmentValue_txt.getText().replace(",", ""))));
            totalEquipmentValue_txt.end();
        }
     }
    
    public void totaltotaltotal(){
        Long x = Long.valueOf(totalLandValue_txt.getText().split(" ")[1].replace(",",""));
        Long y = Long.valueOf(totallVehicle_value.getText().split(" ")[1].replace(",",""));
        Long z = Long.valueOf(totalEquipmentValue_txt.getText().split(" ")[1].replace(",",""));
//        long y = Long.parseLong(totallVehicle_value.getText());
//        long z = Long.parseLong(totalEquipmentValue_txt.getText());
//        Double apPeryear2 = Double.parseDouble(APperYear_txt2.getText()); 
         
//        String a = String.valueOf(x+y+z);
//        String z = String.valueOf(x);
        Long totalFixedAssets = Long.valueOf(x+y+z);
        String totalFixedAssetsString = String.valueOf(totalFixedAssets);
        totaltotaltotal_txt.setText(totalFixedAssetsString);
        if (totaltotaltotal_txt.getText().isEmpty()) {
                totaltotaltotal_txt.setText("0");
        }else{
            totaltotaltotal_txt.setText("Php" + " " 
                    + NumberFormat.getInstance().format(
                            Long.parseLong(totaltotaltotal_txt.getText().replace(",", ""))));
            totaltotaltotal_txt.end();
        }
     }
    //asset report - end
    
    //asset disposal - start
     @FXML
    private void disposuAsset(ActionEvent event) {
        Log1_AssetClassfiles selectedForDisposal = dispose_tbl.getSelectionModel().getSelectedItem();
        if(selectedForDisposal == null){
            AlertMaker.showErrorMessage("","No Item Selected");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/DisposeAsset.fxml"));
            Parent parent = loader.load();
            
            DisposeAssetController controller = (DisposeAssetController) loader.getController();
            controller.populateFields(selectedForDisposal);
            
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AssetManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewAssetu(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/DisposedAssetList.fxml"),
                 "Disposed Assets", null);
    }
    

    
    public void startDisptbl(){
        renderDisptbl();
        populateDisptbl();
    }
    
    public void renderDisptbl(){
        dispose_tbl.getItems().clear();
        dispose_tbl.getColumns().removeAll(dispose_tbl.getColumns());

        TableColumn<Log1_AssetClassfiles, String> AssetTitle = new TableColumn<>("Asset Title");
        TableColumn<Log1_AssetClassfiles, String> AssetCategory = new TableColumn<>("Category");
        TableColumn<Log1_AssetClassfiles, String> AssetBrand = new TableColumn<>("Brand");
        TableColumn<Log1_AssetClassfiles, String> AssetStatus = new TableColumn<>("Status");

        AssetTitle.setCellValueFactory((param) -> param.getValue().AssetTitle);
        AssetCategory.setCellValueFactory((param) -> param.getValue().AssetCategory);
        AssetBrand.setCellValueFactory((param) -> param.getValue().AssetBrand);
        AssetStatus.setCellValueFactory((param) -> param.getValue().AssetStatus);

        dispose_tbl.getColumns().addAll(AssetTitle, AssetCategory, AssetStatus,AssetBrand);
    }
    
    
    
    
    public void populateDisptbl(){
        Log1_AssetModel disp = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> dispCF = FXCollections.observableArrayList();
        List b = assetDBforDP.where(new Object[][]{
            {"ForDepreciation", "=", "Yes"}
        }).andWhere("disposed", "=", "No").get();
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            dispCF.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            ));  
        }
        dispose_tbl.setItems(dispCF);
    }
    
    @FXML
    private void filterDPtbl(ActionEvent event) {
        Log1_AssetModel disp = new Log1_AssetModel();
    ObservableList<Log1_AssetClassfiles> dispCF = FXCollections.observableArrayList();
        List b = assetDBforDP.where(new Object[][]{
            {"ForDepreciation", "=", "Yes"}
        }).andWhere("disposed", "=", "No").andWhere("AssetCategory","=",dpTblCateg_combx.getValue()).get();
        for(Object d : b){
            //rs = hm
            HashMap hm = (HashMap) d;   //exquisite casting
                
            dispCF.add(new Log1_AssetClassfiles(
                
            String.valueOf(hm.get("AssetID")),
            String.valueOf(hm.get("CategoryID")),
            String.valueOf(hm.get("AssetTitle")),
            String.valueOf(hm.get("AssetDescription")),
            String.valueOf(hm.get("AssetCategory")),
            String.valueOf(hm.get("AssetType")),
            String.valueOf(hm.get("AssetBrand")),
            String.valueOf(hm.get("AssetSerialNumber")),
            String.valueOf(hm.get("AssetPurchasedPrice")),
            String.valueOf(hm.get("AssetPurchasedDate")),
            String.valueOf(hm.get("AssetLifeSpan")),
            String.valueOf(hm.get("AssetWarranty")),
            String.valueOf(hm.get("AssetStatus")),
            String.valueOf(hm.get("AssetCoreLocation")),
            String.valueOf(hm.get("CurrentPrice")),
            String.valueOf(hm.get("PriceUpdatedAt")),
            String.valueOf(hm.get("AssetPriceCurrency")),
            String.valueOf(hm.get("AssetRegisteredDate")),
            String.valueOf(hm.get("AssetSalvageValue"))
            ));  
        }
        dispose_tbl.setItems(dispCF);
        dpTblCateg_combx.setValue("Select asset category");
    }

    @FXML
    private void refreshDpTbl(ActionEvent event) {
        startDisptbl();
        dpTblCateg_combx.setValue("Select asset category");
    }
    
    
    
    


    

    

     

//     int total2 =0;
//     public void totalreport2(){
//         for(int i = 0; i<vehicletotal_tbl.getItems().size(); i++ ){
//             int amount = Integer.parseInt(vehicletotal_tbl.getItems().get(i).
//                     CurrentPrice.getValue());
//             total2 = total2+ amount;
//         }totallVehicle_value.setText(String.valueOf(total2));
//     }
//     int total3 =0;
//     public void totalreport3(){
//         for(int i = 0; i<equipmentTotal_tbl.getItems().size(); i++ ){
//             int amount = Integer.parseInt(equipmentTotal_tbl.getItems().get(i).
//                     CurrentPrice.getValue());
//             total3 = total3+ amount;
//         }totalEquipmentValue_txt.setText(String.valueOf(total3));
//     }

    @FXML
    private void selectVehicleForDisposal(MouseEvent event) {
    }




    

     
}

