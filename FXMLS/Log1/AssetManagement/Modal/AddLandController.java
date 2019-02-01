package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddLandController implements Initializable {
    
    Log1_AssetCountModel assetCount = new Log1_AssetCountModel();

    @FXML
    private TextField Area_txt;
    @FXML
    private TextField Address_txt;
    @FXML
    private TextField yearBought_txt;
    @FXML
    private TextField LandName_txt;
    @FXML
    private RadioButton occupied_radioButton;
    @FXML
    private RadioButton vacant_radioButton;
    @FXML
    private JFXButton saveLand_btn;
    @FXML
    private JFXButton clear_btn;
    @FXML
    private ToggleGroup status;
    @FXML
    private TextField pricePerSqMeters_txt;
    @FXML
    private TextField totalPrice_txt;
    @FXML
    private Label radioButtonLabel;
    @FXML
    private Label AssetLandCount_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveLand_btn.setOnMouseClicked(e-> saveLand());
        clear_btn.setOnMouseClicked(e-> clearFields());
        Area_txt.setText("0");
        pricePerSqMeters_txt.setText("0");
        pricePerSqMeters_txt.setOnKeyReleased(e->computeTotalPrice());
        Area_txt.setOnKeyReleased(e->computeTotalPrice());
        
        occupied_radioButton.setOnAction(e-> {
            radioButtonLabel.setText("Occupied");
        });
        vacant_radioButton.setOnAction(e-> {
            radioButtonLabel.setText("Vacant");
        });
        radioButtonLabel.setVisible(false);
        loadAssetFacilityCount();
        AssetLandCount_txt.setVisible(false);

    }
    
    public void computeTotalPrice(){
        int Area = Integer.parseInt(Area_txt.getText());
        int PricePerSqMeters = Integer.parseInt(pricePerSqMeters_txt.getText());
        
        
        
        String TotalLandPrice = String.valueOf(Area*PricePerSqMeters);
        totalPrice_txt.setText(TotalLandPrice);
    }
    
    public void clearFields(){
        Area_txt.setText("0");
        Address_txt.setText("");
        yearBought_txt.setText("");
        pricePerSqMeters_txt.setText("0");
        LandName_txt.setText("");
        occupied_radioButton.setSelected(false);
        vacant_radioButton.setSelected(false);
        totalPrice_txt.setText("");
        radioButtonLabel.setText("");
    }
    
    public void saveLand(){
        String Area = Area_txt.getText();
        String Address = Address_txt.getText();
        String YearBought = yearBought_txt.getText();
        String pricePerSqMeters = pricePerSqMeters_txt.getText();
        String LandName = LandName_txt.getText();
        
        Boolean flag = Area.isEmpty() || Address.isEmpty() || YearBought.isEmpty() ||
                pricePerSqMeters.isEmpty() || LandName.isEmpty();
        
        Log1_AssetLandModel assetLand = new Log1_AssetLandModel();
        Log1_AssetTotalizationModel AT = new Log1_AssetTotalizationModel();
        if(!flag){
            try{
                String[][] landAsset_table ={
                    {"LandName", LandName_txt.getText()},
                    {"LandArea", Area_txt.getText()},
                    {"LandAddress", Address_txt.getText()},
                    {"LandPricePerSqMeters", pricePerSqMeters_txt.getText()},
                    {"LandPurchasedDate", yearBought_txt.getText()},
                    {"LandStatus",radioButtonLabel.getText()},
                    {"AssetCategory", "Land"}
                };
                String[][] AssetForTotal = {
                    {"AssetName", LandName_txt.getText()},
                    {"AssetCategory", "Land"},
                    {"AssetUnit", "Square Meters"},
                    {"AssetQuantity", Area_txt.getText()},
                    {"AssetPricePerUnit", pricePerSqMeters_txt.getText()},
                    {"AssetTotalPrice", totalPrice_txt.getText()},
                    {"AssetCurrentPrice","0"},
                    {"AssetPriceUpdated", "0"}
                };
                if(assetLand.insert(landAsset_table)&&AT.insert(AssetForTotal)){
                    AlertMaker.showSimpleAlert("Saved", ""+ LandName_txt.getText() +" has been registered to assets");
                    int x=Integer.parseInt(AssetLandCount_txt.getText());
                    String answer=String.valueOf(x+1);
                    assetCount.update(new Object[][]{
                        {"AssetLand",answer}
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
            AssetLandCount_txt.setText((String.valueOf(hash.get("AssetLand"))));
        });
    }
}
