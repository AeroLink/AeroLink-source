package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetLandModel;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddBuildingController implements Initializable {
    
    private boolean ifForUpdate = false;
   
    @FXML
    private TextField buildingName_txt;
    @FXML
    private TextField buildingContact_txt;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private JFXButton saveBuilding_btn;
    @FXML
    private TextField BuildingYearBuilt_txt;
    @FXML
    private TextArea buildingDescription_txt;
    @FXML
    private TextField LandAddress_txt;
    @FXML
    private ComboBox selectLand_combox;
    @FXML
    private Label LandID_txt;
    @FXML
    private Label AssetBuildingCount_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveBuilding_btn.setOnMouseClicked(e -> saveBuilding());
        cancel_btn.setOnMouseClicked(e-> clearFields());
        selectLand_combox.setOnAction(e-> getComboxIndex());
        loadLandToCombox();
        loadAssetBuildingCount();
        AssetBuildingCount_txt.setVisible(false);
    }    
    public void saveBuilding(){
        String Name = buildingName_txt.getText();
        String Address = BuildingYearBuilt_txt.getText();
        String Contact = buildingContact_txt.getText();
        String Area = buildingDescription_txt.getText();
        
        Boolean flag = Name.isEmpty() || Address.isEmpty() || Contact.isEmpty() ||
                Area.isEmpty();
        
        if(ifForUpdate){
            useUpdateMethod();
            return;
        }
        Log1_AssetTotalizationModel forAssetTotal = new Log1_AssetTotalizationModel();
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] AB_table ={
                    {"LandID", LandID_txt.getText()},
                    {"BuildingName", buildingName_txt.getText()},
                    {"BuildingDescription", buildingDescription_txt.getText()},
                    {"BuildingContact", buildingContact_txt.getText()},
                    {"BuildingYearBuilt", BuildingYearBuilt_txt.getText()},
                    {"AssetCategory", "Building"},
                };
                String[][] AssetForTotal = {
                    {"AssetName", buildingName_txt.getText()},
                    {"AssetCategory", "Building"},
                    {"AssetUnit", "None"},
                    {"AssetQuantity", "None"},
                    {"AssetPricePerUnit", "None"},
                    {"AssetTotalPrice", "None"},
                    {"AssetCurrentPrice","0"},
                    {"AssetPriceUpdated", "0"}
                };
                if(assetBuilding.insert(AB_table)&&forAssetTotal.insert(AssetForTotal)){
                    AlertMaker.showSimpleAlert("Saved", ""+ buildingName_txt.getText() +" has been registered to Assets");
                    assetLand.update(new Object[][]{
                        {"LandStatus", "Occupied"}
                    }).where(new Object[][]{
                        {"LandID", "=", LandID_txt.getText()}
                    }).executeUpdate();
                    int x=Integer.parseInt(AssetBuildingCount_txt.getText());
                    String answer=String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetBuilding",answer}
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

    private void clearFields() {
         LandID_txt.setText("");
         buildingName_txt.setText("");
         buildingDescription_txt.setText("");
         buildingContact_txt.setText("");
         selectLand_combox.setValue("");
         LandAddress_txt.setText("");
         BuildingYearBuilt_txt.setText("");
    }
    
    
    public void displayBuildingData(Log1_AssetBuildingClassfiles b){
//        buildingName_txt.setText(b.getBuildingName());
//        buildingAddress_txt.setText(b.getBuildingAddress());
//        buildingContact_txt.setText(b.getBuildingContact());
//        buldingArea_txt.setText(b.getBuildingArea());
//        yearBuilt_txt.setText(b.getYearBuilt());
//        buildingID_txt.setText(b.getBuildingID());
//        
//        ifForUpdate = Boolean.TRUE;
    }

    private void useUpdateMethod() {
//        Log1_AssetBuildingModel ab = new Log1_AssetBuildingModel();
//        try{
//            if(ab.update(new Object[][]{
//                {"BuildingName", buildingName_txt.getText()},
//                {"BuildingAddress", buildingAddress_txt.getText()},
//                {"BuildingContact", buildingContact_txt.getText()},
//                {"BuildingArea", buldingArea_txt.getText()},
//                {"YearBuilt", yearBuilt_txt.getText()}
//                
//            }).where(new Object[][]{
//                {"BuildingID", "=", buildingID_txt.getText()}
//            }).executeUpdate()){
//                 AlertMaker.showSimpleAlert("Update", ""+ buildingName_txt.getText()+" has been updated");
//            }else{
//                AlertMaker.showSimpleAlert("Failed", "Fail to update"+ buildingName_txt.getText()+"");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
    
    
    
    public void loadLandToCombox(){
        Log1_AssetLandModel ALM = new Log1_AssetLandModel();
        
        List AssetLand = ALM.where(new Object[][]{
            {"LandStatus", "=", "Vacant"}
        }).get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            selectLand_combox.getItems().add(String.valueOf(hash.get("LandName")));
        });
    }
    public void loadAssetBuildingCount(){
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetBuildingCount_txt.setText((String.valueOf(hash.get("AssetBuilding"))));
        });
    }

    private void getComboxIndex() {
        Log1_AssetLandModel ALM = new Log1_AssetLandModel();
        List list_coa = ALM.where(new Object[][]{
           {"LandName", "=", selectLand_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
                HashMap hm = (HashMap) d;   //exquisite casting
                
                LandAddress_txt.setText(( 
                    String.valueOf(hm.get("LandAddress"))
                )); 
                LandID_txt.setText((
                   String.valueOf(hm.get("LandID"))
                ));
            }
    }
}
