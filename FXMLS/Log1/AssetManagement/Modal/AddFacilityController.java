package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetBuildingRoomsModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetFacilityModel;
import Model.Log1.Log1_AssetModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFacilityController implements Initializable {
    

    @FXML
    private ComboBox buildingLocated_combox;
    @FXML
    private ComboBox roomNumber_txt;
    @FXML
    private TextField faciltyType_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private Label buildingID_txt;
    @FXML
    private Label AssetFacilityCount_txt;
    @FXML
    private JFXTextField buildingAddress_txt;
    @FXML
    private Label LandID_txt;
    @FXML
    private TextField dimension_txt;
    @FXML
    private TextArea description_txt;
    @FXML
    private Label DateDisplayer_label;
    @FXML
    private TextField AssetNumber_txt;
    @FXML
    private TextField FacilityNumber_txt;
    @FXML
    private TextField AssetTitle_txt;
    @FXML
    private Label AssetCount_txt;
    @FXML
    private TextField cost_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.displayCurrentDate();
        cancel_btn.setOnMouseClicked(e-> close());
        this.GetAllID();
        loadBuildingToCombox();
        buildingLocated_combox.setOnAction(e-> getComboxIndex());
        save_btn.setOnMouseClicked(e-> saveFacility());
        loadRoomsToCombox();
        
        cost_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        cost_txt.setOnKeyReleased(value -> {
            if (cost_txt.getText().isEmpty()) {
                cost_txt.setText("0");
            } else {
                cost_txt.setText("Php"+" "+NumberFormat.getInstance().format(Long.parseLong(cost_txt.getText().substring(4).replace(",", ""))));
                cost_txt.end();
            }
        });
    }
    
    public void displayCurrentDate(){
     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    get current date time with Date()
        Date date = new Date();
        DateDisplayer_label.setText(dateFormat.format(date));
    }
    
    public void close(){
        clearFields();
        Stage stage = (Stage) DateDisplayer_label.getScene().getWindow();
        stage.close();
    }
    
     //start getting id
    public void baseFacilityID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetFacilityCount_txt.setText((String.valueOf(hash.get("AssetFacility"))));
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
    public void facilityID(){
        int x = Integer.parseInt(AssetFacilityCount_txt.getText());
        
        String facilityIDwillBe = String.valueOf(x+1);
        
        FacilityNumber_txt.setText(facilityIDwillBe);
    }
    public void AssetID(){
        int z = Integer.parseInt(AssetCount_txt.getText());
        
        String AssetIDwillBe = String.valueOf(z+1);
        
        AssetNumber_txt.setText(AssetIDwillBe);
    }
    public void GetAllID(){
        baseFacilityID();
        baseAssetID();
        facilityID();
        AssetID();
    }
    
    
    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            buildingLocated_combox.getItems().add(String.valueOf(hash.get("bAssetTitle")));
        });
    }
    public void loadRoomsToCombox(){
        Log1_AssetBuildingRoomsModel roomDB = new Log1_AssetBuildingRoomsModel();
        
        List room = roomDB.where(new Object[][]{
            {"BuildingID", "=", buildingID_txt.getText()},
            {"Status", "=", "Vacant"}
        }).orderBy("created_at", Model.Sort.DESC).get();

        room.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            roomNumber_txt.getItems().add(String.valueOf(hash.get("RoomCode")));
        });
//        roomNumber_txt.getItems().clear();
    }
    
    public void clearRoomCombx(){
        roomNumber_txt.getItems().clear();
        loadRoomsToCombox();
    }
    
    private void getComboxIndex() {
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        List list_coa = assetBuilding.where(new Object[][]{
           {"bAssetTitle", "=", buildingLocated_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
            HashMap hm = (HashMap) d;   //exquisite casting
                
            buildingID_txt.setText(( 
                String.valueOf(hm.get("BuildingID"))
            ));
            buildingAddress_txt.setText((
                String.valueOf(hm.get("bAssetCoreLocation"))
            ));
            clearRoomCombx();
        }
    }
    
    
    private void clearFields() {
         AssetTitle_txt.setText("");
         description_txt.setText("");
         faciltyType_txt.setText("");
         dimension_txt.setText("");
         cost_txt.setText("");
         buildingLocated_combox.setValue("Select Building");
         buildingAddress_txt.setText("");
         roomNumber_txt.setValue("Select Room code");
    }
    
    public void saveFacility(){
        String AssetTitle = AssetTitle_txt.getText();
        String AssetType = faciltyType_txt.getText();
        String AssetDimension = dimension_txt.getText();
        String FacilityRoomNumber = roomNumber_txt.getValue().toString();
        String BuildingAddress = buildingAddress_txt.getText();
        String cost = cost_txt.getText();
        
        Boolean flag = AssetTitle.isEmpty() ||
                AssetType.isEmpty() || AssetDimension.isEmpty() || FacilityRoomNumber.isEmpty()
                || BuildingAddress.isEmpty() || cost.isEmpty();
        
//        if(ifForUpdate){
//            useUpdateMethod();
//            return;
//        }
        Log1_AssetModel AssetMain = new Log1_AssetModel();
        Log1_AssetBuildingRoomsModel Rooms = new Log1_AssetBuildingRoomsModel();
        Log1_AssetFacilityModel assetFacility = new Log1_AssetFacilityModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] facility_table ={
                    {"BuildingID", buildingID_txt.getText()},
                    {"AssetID", AssetNumber_txt.getText()},
                    {"fAssetTitle", AssetTitle_txt.getText()},
                    {"fAssetDescription", description_txt.getText()},
                    {"FacilityType", faciltyType_txt.getText()},
                    {"FacilityRoomNumber",roomNumber_txt.getValue().toString()},
                    {"FacilityDimension",dimension_txt.getText()},
                    {"FacilityStatus","Vacant"},
                    {"AssetRegisteredDate",DateDisplayer_label.getText()},
                    {"AssetCoreLocation",roomNumber_txt.getValue().toString()},
                    {"cost",cost_txt.getText().split(" ")[1]},
                };
                String[][] MainAsset = {
                    {"CategoryID", FacilityNumber_txt.getText()},
                    {"AssetTitle", AssetTitle_txt.getText()},
                    {"AssetDescription", description_txt.getText()},
                    {"AssetCategory", "Facility"},
                    {"AssetType", faciltyType_txt.getText()},
                    {"AssetBrand", "N/A"},
                    {"AssetSerialNumber", "N/A"},
                    {"AssetPurchasedPrice",cost_txt.getText().split(" ")[1]},
                    {"AssetPurchasedDate", DateDisplayer_label.getText()},
                    {"AssetLifeSpan", "N/A"},
                    {"AssetWarranty", "N/A"},
                    {"AssetStatus", "Vacant"},
                    {"AssetCoreLocation",roomNumber_txt.getValue().toString()},
                    {"CurrentPrice", cost_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetRegisteredDate", DateDisplayer_label.getText()},
                    {"ForDepreciation","No"}
                };
                if(assetFacility.insert(facility_table)&&AssetMain.insert(MainAsset)){
                    AlertMaker.showSimpleAlert("Saved", ""+ AssetTitle_txt.getText() +" has been registered to Assets");
                    assetCount.update(new Object[][]{
                        {"AssetFacility",FacilityNumber_txt.getText()},
                        {"AssetTotalOfAll",AssetNumber_txt.getText()}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                    Rooms.update(new Object[][]{
                        {"Status","Occupied"},
                    }).where(new Object[][]{
                        {"RoomCode", "=", roomNumber_txt.getValue().toString()}
                    }).executeUpdate();
                    GetAllID();
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
