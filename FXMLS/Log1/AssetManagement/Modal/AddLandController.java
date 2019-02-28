package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetLandModel;
import Model.Log1.Log1_AssetModel;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddLandController implements Initializable {
    
    @FXML
    private TextField Area_txt;
    @FXML
    private TextField Address_txt;
    @FXML
    private TextField yearBought_txt;
    @FXML
    private JFXButton saveLand_btn;
    @FXML
    private JFXButton clear_btn;
    @FXML
    private TextField pricePerSqMeters_txt;
    @FXML
    private Label DateDisplayer_label;
    @FXML
    private TextField AssetNumber_txt;
    @FXML
    private TextField LandNumber_txt;
    @FXML
    private TextField AssetTitle_txt;
    @FXML
    private TextArea AssetDescription_txt;
    @FXML
    private Label AssetLandCount_txt;
    @FXML
    private Label AssetCount_txt;
    @FXML
    private JFXTextField totalLandPrice_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetAssetIDandLandID();
        displayCurrentDate();
        
        saveLand_btn.setOnMouseClicked(e-> saveLand());
        clear_btn.setOnMouseClicked(e-> close());
        
        
        
        Area_txt.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });
        pricePerSqMeters_txt.setOnKeyTyped(value -> {
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

        pricePerSqMeters_txt.setOnKeyReleased(value -> {
            if (pricePerSqMeters_txt.getText().isEmpty()) {
                pricePerSqMeters_txt.setText("");
            } else {
                pricePerSqMeters_txt.setText("Php" +" "+NumberFormat.getInstance().
                        format(Long.parseLong(pricePerSqMeters_txt.getText().substring(4).replace(",", ""))));
                pricePerSqMeters_txt.end();
            }
            computeTotalPrice();
        });

        Area_txt.setOnKeyReleased(value -> {
            if (Area_txt.getText().isEmpty()) {
                Area_txt.setText("0");
            } else {
                Area_txt.setText(NumberFormat.getInstance().format(Long.parseLong(Area_txt.getText().replace(",", ""))));
                Area_txt.end();
            }
        });
    }
    
    public void close(){
        clearFields();
        Stage stage = (Stage) totalLandPrice_txt.getScene().getWindow();
        stage.close();
    }
    
    public void displayCurrentDate(){
     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    get current date time with Date()
    Date date = new Date();
    DateDisplayer_label.setText(dateFormat.format(date));
 
//    get current date time with Calendar()
//    Calendar cal = Calendar.getInstance();
//    System.out.println(dateFormat.format(cal.getTime()));
    
    }
    
    //validate area per sq meters and price per sq meters
//    public void validateAreaSqPerMeters(){
//        String area = Area_txt.getText();
//        
//        if(area.matches("[a-z]")){
//            AlertMaker.showErrorMessage("Invalid", "Only Numbers are acceptable in this field");
//            Area_txt.setText("0");
//        }
//    }
//    public void validatePricePerSqMeters(){
//        String Price = pricePerSqMeters_txt.getText();
//        
//        if(Price.matches("[a-z]")){
//            AlertMaker.showErrorMessage("Invalid", "Only Numbers are acceptable in this field");
//            pricePerSqMeters_txt.setText("0");
//        }
//    }
    
    public void computeTotalPrice(){
        Long Area = Long.valueOf(Area_txt.getText().replace(",",""));
        Long PricePerSqMeters = Long.valueOf(pricePerSqMeters_txt.getText().split(" ")[1].replace(",",""));
        
        Long TotalLandPrice = Long.valueOf(Area*PricePerSqMeters);
        String aa = String.valueOf(TotalLandPrice);
        totalLandPrice_txt.setText(aa);
        if (totalLandPrice_txt.getText().isEmpty()) {
                totalLandPrice_txt.setText("0");
            } else {
                totalLandPrice_txt.setText("Php" + " " + NumberFormat.getInstance().format(Long.parseLong(totalLandPrice_txt.getText().replace(",", ""))));
                totalLandPrice_txt.end();
            }
    }
    
    
    //ToGet the AssetID and LandID
    public void baseLandID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetLandCount_txt.setText((String.valueOf(hash.get("AssetLand"))));
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
    public void LandID(){
        int x = Integer.parseInt(AssetLandCount_txt.getText());
        
        String LandIDwillBe = String.valueOf(x+1);
        
        LandNumber_txt.setText(LandIDwillBe);
    }
    public void AssetID(){
        int z = Integer.parseInt(AssetCount_txt.getText());
        
        String AssetIDwillBe = String.valueOf(z+1);
        
        AssetNumber_txt.setText(AssetIDwillBe);
    }
    public void GetAssetIDandLandID(){
        baseLandID();
        baseAssetID();
        LandID();
        AssetID();
    }


    @FXML
    private void maxLenght(KeyEvent event) {
        int length = yearBought_txt.getText().length();
        final int MAX = 4;
        
        if(length > MAX){
            AlertMaker.showErrorMessage("Invalid Input", "Minimum of 4 characters only");
            yearBought_txt.setText("");
            return;
        }
    }
    
    public void clearFields(){
        AssetTitle_txt.setText("");
        AssetDescription_txt.setText("");
        Area_txt.setText("");
        Address_txt.setText("");
        yearBought_txt.setText("");
        pricePerSqMeters_txt.setText("");
        totalLandPrice_txt.setText("");
    }
    
    public void saveLand(){
        String title = AssetTitle_txt.getText();
        String Address = Address_txt.getText();
        String YearBought = yearBought_txt.getText();
        String pricePerSqMeters = pricePerSqMeters_txt.getText();
        String Area = Area_txt.getText();
        
        Boolean flag = title.isEmpty() || Address.isEmpty() || YearBought.isEmpty() ||
                pricePerSqMeters.isEmpty() || Area.isEmpty();
        
        
        Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
        Log1_AssetModel asset = new Log1_AssetModel();
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        if(!flag){
            try{
                String[][] landAsset_table ={
                    {"AssetID", AssetNumber_txt.getText()},
                    {"LAssetTitle", AssetTitle_txt.getText()},
                    {"LAssetDescription", AssetDescription_txt.getText()},
                    {"LAssetCoreLocation", Address_txt.getText()},
                    {"LandYearBought", yearBought_txt.getText()},
                    {"LandArea", Area_txt.getText()},
                    {"LandPricePerSqMeters",pricePerSqMeters_txt.getText().split(" ")[1]},
                    {"LAssetPurchasedPrice",totalLandPrice_txt.getText().split(" ")[1]},
                    {"LAssetPurchasedDate",yearBought_txt.getText()},
                    {"LAssetStatus","Vacant"},
                    {"CurrentPrice",totalLandPrice_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt",DateDisplayer_label.getText()},
                    {"AssetRegisteredDate",DateDisplayer_label.getText()}
                };
                String[][] Asset = {
                    {"CategoryID", LandNumber_txt.getText()},
                    {"AssetTitle", AssetTitle_txt.getText()},
                    {"AssetDescription", AssetDescription_txt.getText()},
                    {"AssetCategory", "Land"},
                    {"AssetType", "Land"},
                    {"AssetBrand", "N/A"},
                    {"AssetSerialNumber", "N/A"},
                    {"AssetPurchasedPrice", totalLandPrice_txt.getText().split(" ")[1]},
                    {"AssetPurchasedDate", yearBought_txt.getText()},
                    {"AssetLifeSpan", "N/A"},
                    {"AssetWarranty", "N/A"},
                    {"AssetStatus", "Vacant"},
                    {"AssetCoreLocation", Address_txt.getText()},
                    {"CurrentPrice", totalLandPrice_txt.getText().split(" ")[1]},
                    {"PriceUpdatedAt", DateDisplayer_label.getText()},
                    {"AssetRegisteredDate",DateDisplayer_label.getText()},
                    {"ForDepreciation","ForAP"}
                };
                if(assetLand.insert(landAsset_table)&&asset.insert(Asset)){
                    AlertMaker.showSimpleAlert("Saved", " has been registered to assets");
                    assetCount.update(new Object[][]{
                        {"AssetLand",LandNumber_txt.getText()},
                        {"AssetTotalOfAll",AssetNumber_txt.getText()},
                    }).where(new Object[][]{
                        {"AssetCountID", "=", "1"}
                    }).executeUpdate();
                    GetAssetIDandLandID();
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
