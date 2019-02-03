package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetFacilityModel;
import Model.Log1.Log1_AssetTotalizationModel;
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
import javafx.scene.control.TextField;

public class AddEquipmentController implements Initializable {

    @FXML
    private TextField equipment_txt;
    @FXML
    private TextField type_txt;
    @FXML
    private TextField brand_txt;
    @FXML
    private TextField lifeSpan_txt;
    @FXML
    private DatePicker warranty_txt;
    @FXML
    private TextField roomNumber_txt;
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
    private ComboBox building_combox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBuildingToCombox();
        roomNumber_txt.setText("0");
        loadAssetEquipmentCount();
        save_btn.setOnMouseClicked(e->saveEquipment());
        cancel_txt.setOnMouseClicked(e->clearFields());
    } 
    
    public void clearFields(){
        equipment_txt.setText("");
        purchasedDate_txt.getEditor().setText("");
        purchasedPrice_txt.setText("");
        roomNumber_txt.setText("");
        warranty_txt.getEditor().setText("");
        equipment_txt.setText("");
        lifeSpan_txt.setText("");
        brand_txt.setText("");
        type_txt.setText("");
        buildingID_txt.setText("");
        facilityID_txt.setText("");
        LandID_txt.setText("");
        building_combox.setValue("");
    }
    
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel ALM = new Log1_AssetBuildingModel();
        
        List AssetLand = ALM.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            building_combox.getItems().add(String.valueOf(hash.get("BuildingName")));
        });
    }
    
    @FXML
    private void getComboxIndex() {
        Log1_AssetBuildingModel ALM = new Log1_AssetBuildingModel();
        List list_coa = ALM.where(new Object[][]{
           {"BuildingName", "=", building_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
                HashMap hm = (HashMap) d;   //exquisite casting
                
                buildingID_txt.setText((
                    String.valueOf(hm.get("BuildingID"))
                ));
            }
    }

    public void loadAssetEquipmentCount(){
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            equipmentCount_txt.setText((String.valueOf(hash.get("AssetWarehouseEquipment"))));
        });
    }
    public void saveEquipment(){
        String Name = equipment_txt.getText();
        String Address = type_txt.getText();
        String Contact = brand_txt.getText();
        String Area = purchasedPrice_txt.getText();
        String asd = lifeSpan_txt.getText();
        
        Boolean flag = Name.isEmpty() || Address.isEmpty() || Contact.isEmpty() ||
                Area.isEmpty()|| asd.isEmpty();
        
        
        Log1_AssetTotalizationModel forAssetTotal = new Log1_AssetTotalizationModel();
        Log1_AssetEquipmentModel assetBuilding = new Log1_AssetEquipmentModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] AB_table ={
                    {"BuildingID",buildingID_txt.getText()},
                    {"EquipmentName", equipment_txt.getText()},
                    {"EquipmentRoomNumber", roomNumber_txt.getText()},
                    {"EquipmentType", type_txt.getText()},
                    {"EquipmentBrand", brand_txt.getText()},
                    {"EquipmentPurchasedPrice", purchasedPrice_txt.getText()},
                    {"EquipmentPurchasedDate", purchasedDate_txt.getEditor().getText()},
                    {"EquipmentLifeSpan", lifeSpan_txt.getText()},
                    {"EquipmentWarranty", warranty_txt.getEditor().getText()},
                    {"CurrentPrice", purchasedPrice_txt.getText()},
                    {"PriceUpdatedAt", purchasedDate_txt.getEditor().getText()}
                };
                String[][] AssetForTotal = {
                    {"AssetName", equipment_txt.getText()},
                    {"AssetCategory", "Equipment"},
                    {"AssetUnit", "None"},
                    {"AssetQuantity", "None"},
                    {"AssetPricePerUnit", "None"},
                    {"AssetTotalPrice", purchasedPrice_txt.getText()},
                    {"AssetCurrentPrice",purchasedPrice_txt.getText()},
                    {"AssetPriceUpdated", "0"}
                };
                if(assetBuilding.insert(AB_table)&&forAssetTotal.insert(AssetForTotal)){
                    AlertMaker.showSimpleAlert("Saved", ""+ equipment_txt.getText() +" has been registered to Assets");
                    int x=Integer.parseInt(equipmentCount_txt.getText());
                    String answer=String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetWarehouseEquipment",answer}
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
}
