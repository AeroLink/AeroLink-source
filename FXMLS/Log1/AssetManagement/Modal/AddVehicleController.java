package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetModel;
import Model.Log1.Log1_AssetVehicleModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddVehicleController implements Initializable {

    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private Label buildingID_txt;
    @FXML
    private TextField VehicleType_txt;
    @FXML
    private Label AssetVehicleCount_txt;
    @FXML
    private TextField FuelType_txt;
    @FXML
    private TextField FuelCapacity_txt;
    @FXML
    private TextField brand_txt;
    @FXML
    private TextField model_txt;
    @FXML
    private TextField color_txt;
    @FXML
    private TextField capacity_txt;
    @FXML
    private TextField plateNum_txt;
    @FXML
    private TextField chassisNum_txt;
    @FXML
    private TextField purchasePrice_txt;
    @FXML
    private DatePicker warranty_txt;
    @FXML
    private Label DateDisplayer_label;
    @FXML
    private TextField AssetNumber_txt;
    @FXML
    private TextField VehicleID_txt;
    @FXML
    private TextField AssetTitle_txt;
    @FXML
    private TextArea description_txt;
    @FXML
    private ComboBox coreLocation_txt;
    @FXML
    private Label AssetCount_txt;
    @FXML
    private Label warning_lbl;
    @FXML
    private TextField lifeSpan_txt;
    @FXML
    private TextField yearBought_txt;
    @FXML
    private TextField salvageVal_txt;
    @FXML
    private TextField ORC_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayCurrentDate();
        GetAllID();
//        purchasePrice_txt.setOnKeyReleased(e-> validatePriceNum());
        loadLocationToCombox();
        salvageVal_txt.setDisable(true);
        save_btn.setOnMouseClicked(e-> saveVehicle());
        cancel_btn.setOnMouseClicked(e-> close());
        yearBought_txt.setOnKeyReleased(e-> maxLenghtt2());
        lifeSpan_txt.setOnKeyReleased(e-> maxLenghtt());
        yearBought_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        yearBought_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        purchasePrice_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        lifeSpan_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        
        purchasePrice_txt.setOnKeyReleased(value -> {
            if (purchasePrice_txt.getText().isEmpty()) {
                purchasePrice_txt.setText("0");
            } else {
                purchasePrice_txt.setText(NumberFormat.getInstance().format(Double.parseDouble(purchasePrice_txt.getText().replace(",", ""))));
                purchasePrice_txt.end();
            }
            salvageVal_txt.setDisable(false);
        });
        salvageVal_txt.setOnKeyReleased(value -> {
            if (salvageVal_txt.getText().isEmpty()) {
                salvageVal_txt.setText("0");
            } else {
                salvageVal_txt.setText(NumberFormat.getInstance()
                        .format(Double.parseDouble(salvageVal_txt.getText().replace(",", ""))));
                salvageVal_txt.end();
            }
            validateSalvageVal();
        });
        
    }
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //get current date time with Date()
        Date date = new Date();
        DateDisplayer_label.setText(dateFormat.format(date));
    }
    public void close(){
        clearFields();
        Stage stage = (Stage) DateDisplayer_label.getScene().getWindow();
        stage.close();
    }
         //start getting id
    public void baseVehicleID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetVehicleCount_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
        });
    }
    public void baseAssetID(){ 
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetCount_txt.setText((String.valueOf(hash.get("AssetTotalOfAll"))));
        });
    }
        // what will be its Land ID and Asset ID
    public void vehicleID(){
        int x = Integer.parseInt(AssetVehicleCount_txt.getText());
        
        String vehicleIDwillBe = String.valueOf(x+1);
        
        VehicleID_txt.setText(vehicleIDwillBe);
    }
    public void AssetID(){
        int z = Integer.parseInt(AssetCount_txt.getText());
        
        String AssetIDwillBe = String.valueOf(z+1);
        
        AssetNumber_txt.setText(AssetIDwillBe);
    }
    public void GetAllID(){
        baseVehicleID();
        baseAssetID();
        vehicleID();
        AssetID();
    } 
    
    public void loadLocationToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            coreLocation_txt.getItems().add(String.valueOf(hash.get("bAssetTitle")));
        });
    }
    
    public void maxLenghtt() {
        int length = lifeSpan_txt.getText().length();
        final int MAX = 4;
        
        if(length > MAX){
            AlertMaker.showErrorMessage("","Maximum of 4 Characters only.");
            lifeSpan_txt.setText("");
            return;
        }
    }
    public void maxLenghtt2() {
        int length = yearBought_txt.getText().length();
        final int MAX = 4;
        
        if(length > MAX){
            AlertMaker.showErrorMessage("","Maximum of 4 Characters only.");
            yearBought_txt.setText("");
            return;
        }
    }
    
    public void clearFields(){
        AssetTitle_txt.setText("");
        description_txt.setText("");
        VehicleType_txt.setText("");
        brand_txt.setText("");
        model_txt.setText("");
        color_txt.setText("");
        capacity_txt.setText("");
        yearBought_txt.setText("");
        warranty_txt.getEditor().setText("");
        plateNum_txt.setText("");
        chassisNum_txt.setText("");
        purchasePrice_txt.setText("");
        FuelType_txt.setText("");
        FuelCapacity_txt.setText("");
        coreLocation_txt.setValue("");
    }
    
    public void saveVehicle(){
        String title = AssetTitle_txt.getText();
        String type = VehicleType_txt.getText();
        String brand = brand_txt.getText();
        String model = model_txt.getText();
        String color = color_txt.getText();
        String capacity = capacity_txt.getText();
        String pdate = yearBought_txt.getText();
        String pnum = plateNum_txt.getText();
        String cnum = chassisNum_txt.getText();
        String pprice = purchasePrice_txt.getText();
        String lspan = lifeSpan_txt.getText();
        String ftype = FuelType_txt.getText();
        String fcapacity = FuelCapacity_txt.getText();
        
        Boolean flag = title.isEmpty() ||
                type.isEmpty() ||
                brand.isEmpty() ||
                model.isEmpty() || 
                color.isEmpty() || 
                capacity.isEmpty() ||
                pdate.isEmpty() ||
                pnum.isEmpty() ||
                cnum.isEmpty() ||
                pprice.isEmpty() ||
                lspan.isEmpty() ||
                ftype.isEmpty() ||
                fcapacity.isEmpty();
                
        
        Log1_AssetVehicleModel assetVehicle = new Log1_AssetVehicleModel();
        Log1_AssetModel assetMain = new Log1_AssetModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] assetVehicleinfo ={
                    {"AssetID", AssetNumber_txt.getText()},
                    {"vAssetTitle", AssetTitle_txt.getText()},
                    {"vAssetDescription", description_txt.getText()},
                    {"VehicleType", VehicleType_txt.getText()},
                    {"VehicleBrand",brand_txt.getText()},
                    {"VehicleModel", model_txt.getText()},
                    {"VehicleColor",color_txt.getText()},
                    {"VehicleCapacity",capacity_txt.getText()},
                    {"VehicleYearBought",yearBought_txt.getText()},
                    {"VehicleWarrantyDate",warranty_txt.getEditor().getText()},
                    {"VehiclePlateNumber",plateNum_txt.getText()},
                    {"VehicleChassisNumber",chassisNum_txt.getText()},
                    {"VehiclePurchasedPrice", purchasePrice_txt.getText()},
                    {"VehicleFuelType", FuelType_txt.getText()},
                    {"VehicleFuelCapacity", FuelCapacity_txt.getText()},
                    {"vAssetCoreLocation", coreLocation_txt.getValue().toString()},
                    {"AssetRegisteredDate", DateDisplayer_label.getText()},
                    {"VehicleStatus", "Registered"},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"CurrentPrice", purchasePrice_txt.getText()},
                    {"VehicleYearSpan", lifeSpan_txt.getText()},
                    {"ForDepreciation", "Yes"},
                    {"AssetSalvageValue", salvageVal_txt.getText()},
                    {"ORCnumber", ORC_txt.getText()},
                };
                String[][] mainAsset = {
                    {"CategoryID", VehicleID_txt.getText()},
                    {"AssetTitle", AssetTitle_txt.getText()},
                    {"AssetDescription", description_txt.getText()},
                    {"AssetCategory", "Vehicle"},
                    {"AssetType", VehicleType_txt.getText()},
                    {"AssetBrand", brand_txt.getText()},
                    {"AssetSerialNumber","Plate Number" + " - " + plateNum_txt.getText()},
                    {"AssetPurchasedPrice", purchasePrice_txt.getText()},
                    {"AssetPurchasedDate", yearBought_txt.getText()},
                    {"AssetLifeSpan", lifeSpan_txt.getText()},
                    {"AssetWarranty", warranty_txt.getEditor().getText()},
                    {"AssetStatus", "Registered"},
                    {"AssetCoreLocation", coreLocation_txt.getValue().toString()},
                    {"CurrentPrice", purchasePrice_txt.getText()},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetRegisteredDate", DateDisplayer_label.getText()},
                    {"AssetSalvageValue", salvageVal_txt.getText()},
                };
                if(assetVehicle.insert(assetVehicleinfo) && assetMain.insert(mainAsset)){
                    AlertMaker.showSimpleAlert("Saved", ""+ VehicleType_txt.getText() +" has been registered to assets");
                    assetCount.update(new Object[][]{
                        {"AssetVehicle",VehicleID_txt.getText()},
                        {"AssetTotalOfAll",AssetNumber_txt.getText()}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                    GetAllID();
                    clearFields();
                    warning_lbl.setVisible(false);
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            warning_lbl.setVisible(true);
            warning_lbl.setText("Please Fill up all the details needed");
            warning_lbl.setStyle("-fx-text-fill:Red");
        }
    }

    private void validateSalvageVal() {
        double zero = 0;
        Double purchPrice = Double.parseDouble(purchasePrice_txt.getText().replace(",",""));
        Double salvageVal = Double.parseDouble(salvageVal_txt.getText().replace(",",""));
        
        if(salvageVal>=purchPrice){
            AlertMaker.showErrorMessage("Invalid Salvage value","Salvage value should be less than original Price!");
            salvageVal_txt.setText("");
            return;
        } else if(purchPrice==zero){
            AlertMaker.showErrorMessage("","You can't enter a salvage value without a purchase price.");
            salvageVal_txt.setText("");
            return;
        }
    }

    
}
