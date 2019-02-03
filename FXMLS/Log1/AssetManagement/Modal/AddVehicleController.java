package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetTotalizationModel;
import Model.Log1.Log1_AssetVehiclesModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddVehicleController implements Initializable {
    
    Log1_AssetCountModel assetCount = new Log1_AssetCountModel();

    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TextField VehicleModel_txt;
    @FXML
    private TextField VehicleColor_txt;
    @FXML
    private TextField VehicleSerialNumber_txt;
    @FXML
    private TextField yearBought_txt;
    @FXML
    private DatePicker warranty_datepicker;
    @FXML
    private TextField PurchasedPrize_txt;
    @FXML
    private TextField fuelType_txt;
    @FXML
    private RadioButton private_RadioBtn;
    @FXML
    private ToggleGroup VehicleuUablity;
    @FXML
    private RadioButton Public_RadioBtn;
    @FXML
    private Label radioButtonLabel_txt;
    @FXML
    private ComboBox building_combox;
    @FXML
    private Label buildingID_txt;
    @FXML
    private TextField VehicleType_txt;
    @FXML
    private TextField VehicleChassisNumber_txt;
    @FXML
    private Label AssetVehicleCount_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildingID_txt.setVisible(false);
        
        private_RadioBtn.setOnAction(e-> {
            radioButtonLabel_txt.setText("Private");
        });
        Public_RadioBtn.setOnAction(e-> {
            radioButtonLabel_txt.setText("Public");
        });
        building_combox.setOnAction(e-> getComboxIndex());
        save_btn.setOnMouseClicked(e-> saveVehicle());
        cancel_btn.setOnMouseClicked(e-> clearFields());
        loadBuildingToCombox();
        loadAssetFacilityCount();
        AssetVehicleCount_txt.setVisible(false);
    }
    
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            building_combox.getItems().add(String.valueOf(hash.get("BuildingName")));
        });
    }
    
    private void getComboxIndex() {
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        List list_coa = assetBuilding.where(new Object[][]{
           {"BuildingName", "=", building_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
            HashMap hm = (HashMap) d;   //exquisite casting
                
            buildingID_txt.setText(( 
                String.valueOf(hm.get("BuildingID"))
            ));
        }
    }
    
    public void clearFields(){
        VehicleType_txt.setText("");
        VehicleModel_txt.setText("");
        VehicleColor_txt.setText("");
        VehicleSerialNumber_txt.setText("");
        yearBought_txt.setText("");
        warranty_datepicker.getEditor().setText("");
        PurchasedPrize_txt.setText("");
        fuelType_txt.setText("");
        buildingID_txt.setText("");
        building_combox.setValue("");
        private_RadioBtn.setSelected(false);
        Public_RadioBtn.setSelected(false);
        radioButtonLabel_txt.setText("");
        VehicleChassisNumber_txt.setText("");
    }
    
    public void saveVehicle(){
        String VehicleType = VehicleType_txt.getText();
        String VehicleModel = VehicleModel_txt.getText();
        String VehicleColor = VehicleColor_txt.getText();
        String VehicleSerialNumber = VehicleSerialNumber_txt.getText();
        String YearBought = yearBought_txt.getText();
        String PurchasedPrice = PurchasedPrize_txt.getText();
        String FuelType = fuelType_txt.getText();
        
        Boolean flag = VehicleType.isEmpty() || VehicleModel.isEmpty() || VehicleColor.isEmpty() ||
                VehicleSerialNumber.isEmpty() || YearBought.isEmpty()|| PurchasedPrice.isEmpty() ||
                FuelType.isEmpty();
        
        Log1_AssetVehiclesModel assetVehicle = new Log1_AssetVehiclesModel();
        Log1_AssetTotalizationModel AT = new Log1_AssetTotalizationModel();
        if(!flag){
            try{
                String[][] landAsset_table ={
                    {"BuildingID", buildingID_txt.getText()},
                    {"VehicleType", VehicleType_txt.getText()},
                    {"VehicleModel", VehicleModel_txt.getText()},
                    {"VehicleColor", VehicleColor_txt.getText()},
                    {"VehicleSerialNumber",VehicleSerialNumber_txt.getText()},
                    {"VehicleChassisNumber", VehicleChassisNumber_txt.getText()},
                    {"VehicleYearBought",yearBought_txt.getText()},
                    {"VehicleWarrantyDate",warranty_datepicker.getEditor().getText()},
                    {"VehiclePurchasedPrice",PurchasedPrize_txt.getText()},
                    {"VehicleFuelType",fuelType_txt.getText()},
                    {"VehicleUsability",radioButtonLabel_txt.getText()},
                    {"CurrentPice",PurchasedPrize_txt.getText()},
                    {"VehicleStatus", "Not in used"}
                };
                String[][] AssetForTotal = {
                    {"AssetName", VehicleType_txt.getText()},
                    {"AssetCategory", "Vehicle"},
                    {"AssetUnit", "Piece"},
                    {"AssetQuantity", "1"},
                    {"AssetPricePerUnit", PurchasedPrize_txt.getText()},
                    {"AssetTotalPrice", PurchasedPrize_txt.getText()},
                    {"AssetCurrentPrice","0"},
                    {"AssetPriceUpdated", "0"}
                };
                if(assetVehicle.insert(landAsset_table)&&AT.insert(AssetForTotal)){
                    AlertMaker.showSimpleAlert("Saved", ""+ VehicleModel_txt.getText() +" has been registered to assets");
                    int x=Integer.parseInt(AssetVehicleCount_txt.getText());
                    String answer=String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetVehicle",answer}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                    clearFields();
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("Invalid", "fill up all the details");
        }
    }
    
    public void loadAssetFacilityCount(){        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetVehicleCount_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
        });
    }
}
