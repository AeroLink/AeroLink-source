/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetCountModel;
import Model.Log1.Log1_AssetDisposalModel;
import Model.Log1.Log1_AssetEquipmentModel;
import Model.Log1.Log1_AssetModel;
import Model.Log1.Log1_AssetVehicleModel;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class DisposeAssetController implements Initializable {

    @FXML
    private Label dateTodeh_txt;
    @FXML
    private TextField tite_txt;
    @FXML
    private TextField categ_txt;
    @FXML
    private TextField loc_txt;
    @FXML
    private TextField serialNum_txt;
    @FXML
    private TextField stats_txt;
    @FXML
    private TextField curVal_txt;
    @FXML
    private TextArea remarks_txt;
    @FXML
    private TextField fname_txt;
    @FXML
    private TextField Lname_txt;
    @FXML
    private Label assetID_txt;
    @FXML
    private Label categID_txt;
    @FXML
    private Label EquipmentNumber_txt;
    @FXML
    private Label AssetNumber_txt;
    @FXML
    private Label AssetVehicleCount_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCurrentDate();
        baseEquipmentID();
        baseAssetID();
        baseVehicleID();
    }    
    public void baseVehicleID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetVehicleCount_txt.setText((String.valueOf(hash.get("AssetVehicle"))));
        });
    }
    public void baseEquipmentID(){  
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            EquipmentNumber_txt.setText((String.valueOf(hash.get("AssetWarehouseEquipment"))));
        });
    }
    public void baseAssetID(){ 
        Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
        List count = assetCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            AssetNumber_txt.setText((String.valueOf(hash.get("AssetTotalOfAll"))));
        });
    }
    
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//      get current date time with Date()
        Date date = new Date();
        dateTodeh_txt.setText(dateFormat.format(date));
        
        Calendar cal = Calendar.getInstance();
//        time_txt.setText(timeFormat.format(cal.getTime()));
    }
    
    @FXML
    private void clus(ActionEvent event) {
        clearFields();
        Stage stage = (Stage) dateTodeh_txt.getScene().getWindow();
        stage.close();
    }

    public void claws(){
        Stage stage = (Stage) dateTodeh_txt.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void despus(ActionEvent event) {
        String fname = fname_txt.getText();
        String lname = Lname_txt.getText();
        String remarks = remarks_txt.getText();
        
        Boolean flag = fname.isEmpty() || lname.isEmpty() || remarks.isEmpty();
        
        if(flag){
            AlertMaker.showErrorMessage("Empty fields","please fill up the details");
            return;
        }else{
            Log1_AssetDisposalModel DisposeDB = new Log1_AssetDisposalModel();
            Log1_AssetModel assetDB = new Log1_AssetModel();
            Log1_AssetEquipmentModel equipmentDB = new Log1_AssetEquipmentModel();
            Log1_AssetVehicleModel vhDB = new Log1_AssetVehicleModel();
            Log1_AssetCountModel assetCount = new Log1_AssetCountModel();
            try{
                String[][] disposeData = {
                    {"AssetID", assetID_txt.getText()},
                    {"DisposedBy", fname_txt.getText()+", "+Lname_txt.getText()},
                    {"Remarks", remarks_txt.getText()},
                    {"DisposalDate", dateTodeh_txt.getText()},
                };
                if(DisposeDB.insert(disposeData)){
                    AlertMaker.showSimpleAlert("", "Asset has been successfuly disposed!");
                    assetDB.update(new Object[][]{
                        {"disposed", "Yes"}
                    }).where(new Object[][]{
                        {"AssetID", "=", assetID_txt.getText()}
                    }).executeUpdate();
                    String categ = categ_txt.getText();
                    if(categ=="Equipment"){
                        equipmentDB.update(new Object[][]{
                            {"disposed","Yes"}
                        }).where(new Object[][]{
                            {"EquipmentID","=",categID_txt.getText()}
                        }).executeUpdate();
                        int x = Integer.parseInt(EquipmentNumber_txt.getText());
                        int z = Integer.valueOf(x-1);
                        String zToString = String.valueOf(z);
                        int a = Integer.parseInt(AssetNumber_txt.getText());
                        int b = Integer.valueOf(a-1);
                        String bToString = String.valueOf(b);
                        assetCount.update(new Object[][]{
                            {"AssetWarehouseEquipment",zToString},
                            {"AssetTotalOfAll",bToString},
                        }).where(new Object[][]{
                            {"AssetCountID", "=", "1"}
                        }).executeUpdate();
                    }else if(categ=="Vehicle"){
                        int y = Integer.parseInt(AssetVehicleCount_txt.getText());
                        int w = Integer.valueOf(y-1);
                        String wToString = String.valueOf(w);
                        int c = Integer.parseInt(AssetNumber_txt.getText());
                        int d = Integer.valueOf(c-1);
                        String dToString = String.valueOf(d);
                        assetCount.update(new Object[][]{
                            {"AssetVehicle",wToString},
                            {"AssetTotalOfAll",dToString}
                        }).where(new Object[][]{
                            {"AssetCountID", "=", "1"}
                        }).executeUpdate();
                        vhDB.update(new Object[][]{
                            {"disposed","Yes"}
                        }).where(new Object[][]{
                            {"VehicleID", "=",categID_txt.getText()}
                        }).executeUpdate();
                    }
                    clearFields();
                    claws();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void populateFields(Log1_AssetClassfiles asset) {
        tite_txt.setText(asset.getAssetTitle());
        categ_txt.setText(asset.getAssetCategory());
        loc_txt.setText(asset.getAssetCoreLocation());
        serialNum_txt.setText(asset.getAssetSerialNumber());
        stats_txt.setText(asset.getAssetStatus());
        curVal_txt.setText(asset.getCurrentPrice());
        assetID_txt.setText(asset.getAssetID());
        categID_txt.setText(asset.getCategoryID());
    }

    private void clearFields() {
        tite_txt.setText("");
        categ_txt.setText("");
        loc_txt.setText("");
        serialNum_txt.setText("");
        stats_txt.setText("");
        curVal_txt.setText("");
        fname_txt.setText("");
        Lname_txt.setText("");
        remarks_txt.setText("");
        assetID_txt.setText("");
    }
    
}
