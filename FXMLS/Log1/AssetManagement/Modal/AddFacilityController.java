package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFacilityController implements Initializable {
    
    Log1_AssetCountModel assetCount = new Log1_AssetCountModel();

    @FXML
    private ComboBox buildingLocated_combox;
    @FXML
    private TextField roomNumber_txt;
    @FXML
    private TextField capacity_txt;
    @FXML
    private TextField faciltyType_txt;
    @FXML
    private TextField facilityName_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private Label buildingID_txt;
    @FXML
    private Label AssetFacilityCount_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadBuildingToCombox();
        buildingID_txt.setVisible(false);
        buildingLocated_combox.setOnAction(e-> getComboxIndex());
        save_btn.setOnMouseClicked(e-> saveFacility());
        cancel_btn.setOnAction(e-> clearFields());
        loadAssetFacilityCount();
        AssetFacilityCount_txt.setVisible(false);
    }
    
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            buildingLocated_combox.getItems().add(String.valueOf(hash.get("BuildingName")));
        });
    }
    
    private void getComboxIndex() {
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        List list_coa = assetBuilding.where(new Object[][]{
           {"BuildingName", "=", buildingLocated_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
            HashMap hm = (HashMap) d;   //exquisite casting
                
            buildingID_txt.setText(( 
                String.valueOf(hm.get("BuildingID"))
            ));
            
        }
    }
    
    private void clearFields() {
         buildingLocated_combox.setValue("");
         roomNumber_txt.setText("");
         capacity_txt.setText("");
         faciltyType_txt.setText("");
         facilityName_txt.setText("");
         buildingID_txt.setText("");
    }
    
    public void saveFacility(){
        String roomNumber = roomNumber_txt.getText();
        String capacity = capacity_txt.getText();
        String type = faciltyType_txt.getText();
        
        Boolean flag = roomNumber.isEmpty() || capacity.isEmpty() ||
                type.isEmpty();
        
//        if(ifForUpdate){
//            useUpdateMethod();
//            return;
//        }
        Log1_AssetTotalizationModel forAssetTotal = new Log1_AssetTotalizationModel();
        Log1_AssetFacilityModel assetBuilding = new Log1_AssetFacilityModel();
        if(!flag){
            try{
                String[][] facility_table ={
                    {"FacilityName", facilityName_txt.getText()},
                    {"FacilityType", faciltyType_txt.getText()},
                    {"FacilityRoomNumber", roomNumber_txt.getText()},
                    {"BuildingID", buildingID_txt.getText()},
                    {"FacilityCapacity", capacity_txt.getText()},
                    {"FacilityStatus", "Vacant"},
                    {"AssetCategory", "Facility"}
                };
                String[][] AssetForTotal = {
                    {"AssetName", facilityName_txt.getText()},
                    {"AssetCategory", "Facility"},
                    {"AssetUnit", "None"},
                    {"AssetQuantity", "None"},
                    {"AssetPricePerUnit", "None"},
                    {"AssetTotalPrice", "None"},
                    {"AssetCurrentPrice","0"},
                    {"AssetPriceUpdated", "0"}
                };
                if(assetBuilding.insert(facility_table)&&forAssetTotal.insert(AssetForTotal)){
                    AlertMaker.showSimpleAlert("Saved", ""+ facilityName_txt.getText() +" has been registered to Assets");
                    int x=Integer.parseInt(AssetFacilityCount_txt.getText());
                    String answer=String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetFacility",answer}
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
            AssetFacilityCount_txt.setText((String.valueOf(hash.get("AssetFacility"))));
        });
    }

}
