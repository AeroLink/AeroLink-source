package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetFacilityModel;
import Model.Log1.Log1_AssetModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEquipmentController implements Initializable {
    
    ObservableList<String> assetStatus = FXCollections.observableArrayList("In used","Stored","Moving");

    @FXML
    private TextField lifeSpan_txt;
    @FXML
    private DatePicker warranty_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private TextField purchasedPrice_txt;
    @FXML
    private DatePicker purchasedDate_txt;
    @FXML
    private Label buildingID_txt;
    @FXML
    private Label facilityID_txt;
    @FXML
    private Label LandID_txt;
    @FXML
    private Label equipmentCount_txt;
    @FXML
    private JFXButton cancel_txt;
    @FXML
    private Label DateDisplayer_label;
    @FXML
    private TextField AssetTitle_txt;
    @FXML
    private TextField EquipmentType_txt;
    @FXML
    private TextField EquipmentBrand_txt;
    @FXML
    private TextField AssetNumber_txt;
    @FXML
    private TextField EquipmentNumber_txt;
    @FXML
    private TextArea Description_txt;
    @FXML
    private TextField EquipmentSerialNumber_txt;
    @FXML
    private ComboBox AssetCoreLocation_Combox;
    @FXML
    private ComboBox<String> AssetStatus_Combox;
    @FXML
    private Label AssetEquipmentCount_txt;
    @FXML
    private Label AssetCount_txt;
    @FXML
    private Label warning_lbl;
    @FXML
    private TextField salvageVal_txt;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        save_btn.setOnMouseClicked(e->saveEquipment());
        displayCurrentDate();
        cancel_txt.setOnMouseClicked(e-> close());
        GetAllID();
        loadLocationToCombox();
        AssetStatus_Combox.setItems(assetStatus);
        
        purchasedPrice_txt.setOnKeyTyped(value -> {
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
        salvageVal_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        
        purchasedPrice_txt.setOnKeyReleased(value -> {
            if (purchasedPrice_txt.getText().isEmpty()) {
                purchasedPrice_txt.setText("");
            } else {
                purchasedPrice_txt.setText("Php" +" "+NumberFormat.getInstance().
                        format(Long.parseLong(purchasedPrice_txt.getText().substring(4).replace(",", ""))));
                purchasedPrice_txt.end();
            }
        });
        salvageVal_txt.setOnKeyReleased(value -> {
            if (salvageVal_txt.getText().isEmpty()) {
                salvageVal_txt.setText("");
            } else {
                salvageVal_txt.setText("Php" +" "+NumberFormat.getInstance().
                        format(Long.parseLong(salvageVal_txt.getText().substring(4).replace(",", ""))));
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
    public void baseEquipmentID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetEquipmentCount_txt.setText((String.valueOf(hash.get("AssetWarehouseEquipment"))));
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
    public void equipmentID(){
        int x = Integer.parseInt(AssetEquipmentCount_txt.getText());
        
        String equipmentIDwillBe = String.valueOf(x+1);
        
        EquipmentNumber_txt.setText(equipmentIDwillBe);
    }
    public void AssetID(){
        int z = Integer.parseInt(AssetCount_txt.getText());
        
        String AssetIDwillBe = String.valueOf(z+1);
        
        AssetNumber_txt.setText(AssetIDwillBe);
    }
    public void GetAllID(){
        baseEquipmentID();
        baseAssetID();
        equipmentID();
        AssetID();
    }    
    
    public void validateSalvageVal(){
        long zero = 0;
        Long purchPrice = Long.parseLong(purchasedPrice_txt.getText().split(" ")[1].replace(",",""));
        Long salvageVal = Long.parseLong(salvageVal_txt.getText().split(" ")[1].replace(",",""));
        
        if(purchPrice<=salvageVal){
            AlertMaker.showErrorMessage("Invalid Salvage value","Salvage value should be less than original Price!");
            salvageVal_txt.setText("");
        }else if(purchPrice==zero){
            AlertMaker.showErrorMessage("Purchase Price is 0","You can't enter a salvage value without a purchase price.");
            salvageVal_txt.setText("");
        }
    }
    
    public void clearFields(){
        AssetTitle_txt.setText("");
        Description_txt.setText("");
        EquipmentType_txt.setText("");
        EquipmentBrand_txt.setText("");
        EquipmentSerialNumber_txt.setText("");
        purchasedPrice_txt.setText("");
        purchasedDate_txt.getEditor().setText("");
        lifeSpan_txt.setText("");
        warranty_txt.getEditor().setText("");
        AssetStatus_Combox.setValue("Select Asset Status");
        AssetCoreLocation_Combox.setValue("Select Location");
        salvageVal_txt.setText("");
    }
    
    public void loadLocationToCombox(){
        Log1_AssetFacilityModel loc = new Log1_AssetFacilityModel();
        
        List equipLocation = loc.get();

        equipLocation.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetCoreLocation_Combox.getItems().add(String.valueOf(hash.get("AssetCoreLocation")));
        });
    }
    
    public void saveEquipment(){
        String title = AssetTitle_txt.getText();
        String type = EquipmentType_txt.getText();
        String brand = EquipmentBrand_txt.getText();
        String serialNum = EquipmentSerialNumber_txt.getText();
        String lifeSpan = lifeSpan_txt.getText();
        String purchasedPrice = purchasedPrice_txt.getText();
        String purchasedDate = purchasedDate_txt.getEditor().getText();
        String warranty = warranty_txt.getEditor().getText();
        
        
        Boolean flag = title.isEmpty() || type.isEmpty() || brand.isEmpty() ||
                serialNum.isEmpty()|| lifeSpan.isEmpty() || purchasedPrice.isEmpty()
                || purchasedDate.isEmpty() || warranty.isEmpty();
        
        
        Log1_AssetModel forMainAsset = new Log1_AssetModel();
        Log1_AssetEquipmentModel assetBuilding = new Log1_AssetEquipmentModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] AB_table ={
                    {"AssetID",AssetNumber_txt.getText()},
                    {"eAssetTitle", AssetTitle_txt.getText()},
                    {"eAssetDescription", Description_txt.getText()},
                    {"eAssetType", EquipmentType_txt.getText()},
                    {"eAssetBrand", EquipmentBrand_txt.getText()},
                    {"eAssetSerialNumber", EquipmentSerialNumber_txt.getText()},
                    {"ePurchasedPrice", purchasedPrice_txt.getText().split(" ")[1]},
                    {"ePurchasedDate", purchasedDate_txt.getEditor().getText()},
                    {"eLifeSpan", lifeSpan_txt.getText()},
                    {"eWarranty", warranty_txt.getEditor().getText()},
                    {"eAssetStatus", AssetStatus_Combox.getValue()},
                    {"AssetCoreLocation", AssetCoreLocation_Combox.getValue().toString()},
                    {"CurrentPrice", purchasedPrice_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetRegisteredDate", DateDisplayer_label.getText()},
                    {"AssetSalvageValue", salvageVal_txt.getText().split(" ")[1]},
                };
                String[][] MainAsset = {
                    {"CategoryID", EquipmentNumber_txt.getText()},
                    {"AssetTitle", AssetTitle_txt.getText()},
                    {"AssetDescription", Description_txt.getText()},
                    {"AssetCategory", "Equipment"},
                    {"AssetType", EquipmentType_txt.getText()},
                    {"AssetBrand", EquipmentBrand_txt.getText()},
                    {"AssetSerialNumber",EquipmentSerialNumber_txt.getText()},
                    {"AssetPurchasedPrice", purchasedPrice_txt.getText().split(" ")[1]},
                    {"AssetPurchasedDate", purchasedDate_txt.getEditor().getText()},
                    {"AssetLifeSpan", lifeSpan_txt.getText()},
                    {"AssetWarranty", warranty_txt.getEditor().getText()},
                    {"AssetStatus", AssetStatus_Combox.getValue()},
                    {"AssetCoreLocation", AssetCoreLocation_Combox.getValue().toString()},
                    {"CurrentPrice", purchasedPrice_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetRegisteredDate", DateDisplayer_label.getText()},
                    {"AssetSalvageValue", salvageVal_txt.getText().split(" ")[1]},
                    {"ForDepreciation","Yes"}
                };
                if(assetBuilding.insert(AB_table)&&forMainAsset.insert(MainAsset)){
                    AlertMaker.showSimpleAlert("Saved", ""+ AssetTitle_txt.getText() +" has been registered to Assets");
                    assetCount.update(new Object[][]{
                        {"AssetWarehouseEquipment",EquipmentNumber_txt.getText()},
                        {"AssetTotalOfAll",AssetNumber_txt.getText()},
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
            
//            warning_lbl.setStyle("-fx-background-color:black;-fx-text-fill:Red");
        }
    }
}
