package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetLandModel;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
    private ComboBox selectLand_combox;
    @FXML
    private Label LandID_txt;
    @FXML
    private Label AssetBuildingCount_txt;
    @FXML
    private TextField AddressOfLand_txt;
    @FXML
    private Label DateDisplayer_label;
    @FXML
    private TextField AssetNumber_txt;
    @FXML
    private TextField BuildingNumber_txt;
    @FXML
    private Label AssetCount_txt;
    @FXML
    private TextField cost_txt;
    @FXML
    private TextField floors_txt;
    @FXML
    private TextField Area_txt;
    @FXML
    private TextField rooms_txt;
    
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetAllID();
        saveBuilding_btn.setOnMouseClicked(e -> saveBuilding());
        cancel_btn.setOnMouseClicked(e-> close());
        selectLand_combox.setOnAction(e-> getComboxIndex());
        loadLandToCombox();
        AssetBuildingCount_txt.setVisible(false);
        displayCurrentDate();
        buildingContact_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        BuildingYearBuilt_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        cost_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        floors_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        rooms_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        cost_txt.setOnKeyReleased(value ->{
        if (cost_txt.getText().isEmpty()) {
                cost_txt.setText("0");
            }else{
            cost_txt.setText("Php" + " " + NumberFormat.getInstance().format(Long.parseLong(cost_txt.getText().substring(4).replace(",", ""))));
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
        Stage stage = (Stage) AssetBuildingCount_txt.getScene().getWindow();
        stage.close();
    }
    
    public void saveBuilding(){
        String Name = buildingName_txt.getText();
        String Address = BuildingYearBuilt_txt.getText();
        String Contact = buildingContact_txt.getText();
        String Area = buildingDescription_txt.getText();
        
        Boolean flag = Name.isEmpty() || Address.isEmpty() || Contact.isEmpty() ||
                Area.isEmpty();
        
//        if(ifForUpdate){
//            useUpdateMethod();
//            return;
//        }
        Log1_AssetModel asset = new Log1_AssetModel();
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] AB_table ={
                    {"AssetID", AssetNumber_txt.getText()},
                    {"LandID", LandID_txt.getText()},
                    {"bAssetTitle", buildingName_txt.getText()},
                    {"bAssetDescription", buildingDescription_txt.getText()},
                    {"BuildingContact", buildingContact_txt.getText()},
                    {"BuildingYearBuilt", BuildingYearBuilt_txt.getText()},
                    {"bAssetCoreLocation", AddressOfLand_txt.getText()},
                    {"AssetRegisteredDate",DateDisplayer_label.getText()},
                    {"RoomNumberCapacity",rooms_txt.getText()},
                    {"Cost",cost_txt.getText().split(" ")[1]},
                    {"Floors",floors_txt.getText()},
                    {"Area",Area_txt.getText()},
                };
                String[][] flatAsset = {
                    {"CategoryID",BuildingNumber_txt.getText()},
                    {"AssetTitle", buildingName_txt.getText()},
                    {"AssetDescription", buildingDescription_txt.getText()},
                    {"AssetCategory", "Building"},
                    {"AssetType", "Building"},
                    {"AssetBrand", "N/A"},
                    {"AssetSerialNumber", "N/A"},
                    {"AssetPurchasedPrice",cost_txt.getText().split(" ")[1]},
                    {"AssetPurchasedDate", BuildingYearBuilt_txt.getText()},
                    {"AssetLifeSpan", "N/A"},
                    {"AssetWarranty", "N/A"},
                    {"AssetStatus", "Registered"},
                    {"AssetCoreLocation", AddressOfLand_txt.getText()},
                    {"CurrentPrice", cost_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetPriceCurrency", "Php"},
                    {"AssetRegisteredDate",DateDisplayer_label.getText()},
                    {"ForDepreciation","ForAP"}
                };
                if(assetBuilding.insert(AB_table)&&asset.insert(flatAsset)){
                    AlertMaker.showSimpleAlert("Saved", ""+ buildingName_txt.getText() +" has been registered to Assets");
                    assetLand.update(new Object[][]{
                        {"LAssetStatus", "Occupied"}
                    }).where(new Object[][]{
                        {"LandID", "=", LandID_txt.getText()}
                    }).executeUpdate();
                    asset.update(new Object[][]{
                        {"AssetStatus", "Occupied"}
                    }).where(new Object[][]{
                        {"AssetTitle", "=", selectLand_combox.getValue()}
                    }).executeUpdate();
                    assetCount.update(new Object[][]{
                        {"AssetBuilding",BuildingNumber_txt.getText()},
                        {"AssetTotalOfAll",AssetNumber_txt.getText()}
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
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

    private void clearFields() {
         LandID_txt.setText("");
         buildingName_txt.setText("");
         buildingDescription_txt.setText("");
         buildingContact_txt.setText("");
         selectLand_combox.setValue("");
         AddressOfLand_txt.setText("");
         BuildingYearBuilt_txt.setText("");
         cost_txt.setText("");
         Area_txt.setText("");
         floors_txt.setText("");
         rooms_txt.setText("");
    }
    
    public void loadLandToCombox(){
        Log1_AssetLandModel ALM = new Log1_AssetLandModel();
        
        List AssetLand = ALM.where(new Object[][]{
            {"LAssetStatus", "=", "Vacant"}
        }).get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            selectLand_combox.getItems().add(String.valueOf(hash.get("LAssetTitle")));
        });
    }

    private void getComboxIndex() {
        Log1_AssetLandModel ALM = new Log1_AssetLandModel();
        List list_coa = ALM.where(new Object[][]{
           {"LAssetTitle", "=", selectLand_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
                HashMap hm = (HashMap) d;   //exquisite casting
                
                AddressOfLand_txt.setText(( 
                    String.valueOf(hm.get("LAssetCoreLocation"))
                )); 
                LandID_txt.setText((
                   String.valueOf(hm.get("LandID"))
                ));
                Area_txt.setText((
                   String.valueOf(hm.get("LandArea"))
                ));
            }
            if (Area_txt.getText().isEmpty()) {
                Area_txt.setText("0");
            }else{
            Area_txt.setText(NumberFormat.getInstance().format(Long.parseLong(Area_txt.getText().replace(",", ""))));
            Area_txt.end();
            }
    }

    @FXML
    private void maxLength(KeyEvent event) {
        int length = BuildingYearBuilt_txt.getText().length();
        final int MAX = 4;
        
        if(length > MAX){
            AlertMaker.showErrorMessage("Invalid Input", "Minimum of 4 characters only");
            BuildingYearBuilt_txt.setText("");
            return;
        }
    }

    //start getting id
    public void baseBuildingID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetBuildingCount_txt.setText((String.valueOf(hash.get("AssetBuilding"))));
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
    public void buildingID(){
        int x = Integer.parseInt(AssetBuildingCount_txt.getText());
        
        String buildingIDwillBe = String.valueOf(x+1);
        
        BuildingNumber_txt.setText(buildingIDwillBe);
    }
    public void AssetID(){
        int z = Integer.parseInt(AssetCount_txt.getText());
        
        String AssetIDwillBe = String.valueOf(z+1);
        
        AssetNumber_txt.setText(AssetIDwillBe);
    }
    public void GetAllID(){
        baseBuildingID();
        baseAssetID();
        buildingID();
        AssetID();
    }
}
