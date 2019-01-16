/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_BuildingTypeModel;
import Model.Log1.Log1_equipmentTypeModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class AddBuildingController implements Initializable {
    
    private boolean ifForUpdate = false;

    @FXML
    private TextArea buildingAddress_txt;
    @FXML
    private TextField buildingName_txt;
    @FXML
    private TextField buildingContact_txt;
    @FXML
    private TextField buldingArea_txt;
    @FXML
    private TextField yearBuilt_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TextField buildingID_txt;
    @FXML
    private ComboBox buildingType_combox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        save_btn.setOnMouseClicked(e -> saveBuilding());
        cancel_btn.setOnMouseClicked(e -> closeAction());
        loadBuildingToCombox();
        buildingType_combox.setValue("Select Building Type");
    }    
    public void saveBuilding(){
        String Name = buildingName_txt.getText();
        String Address = buildingAddress_txt.getText();
        String Contact = buildingContact_txt.getText();
        String Area = buldingArea_txt.getText();
        String Year = yearBuilt_txt.getText();
        
        Boolean flag = Name.isEmpty() || Address.isEmpty() || Contact.isEmpty() ||
                Area.isEmpty() || Year.isEmpty();
        
        if(ifForUpdate){
            useUpdateMethod();
            return;
        }
        
        Log1_AssetBuildingModel AB = new Log1_AssetBuildingModel();
        if(!flag){
            try{
                String[][] AB_table ={
                    {"BuildingName", buildingName_txt.getText()},
                    {"BuildingAddress", buildingAddress_txt.getText()},
                    {"BuildingContact", buildingContact_txt.getText()},
                    {"BuildingArea", buldingArea_txt.getText()},
                    {"YearBuilt", yearBuilt_txt.getText()},
                    {"BuildingFacilityType", buildingType_combox.getValue().toString()},
                    {"BuildingStatus", "Not in Use"}
                }; if(AB.insert(AB_table)){
                    AlertMaker.showSimpleAlert("Saved", ""+ buildingName_txt.getText() +" has been saved");
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
         buildingName_txt.setText("");
         buildingAddress_txt.setText("");
         buildingContact_txt.setText("");
         buldingArea_txt.setText("");
         yearBuilt_txt.setText("");
    }
    
    public void closeAction(){
        Stage stage = (Stage) yearBuilt_txt.getScene().getWindow();
        stage.close();
    }
    
    public void displayBuildingData(Log1_AssetBuildingClassfiles b){
        buildingName_txt.setText(b.getBuildingName());
        buildingAddress_txt.setText(b.getBuildingAddress());
        buildingContact_txt.setText(b.getBuildingContact());
        buldingArea_txt.setText(b.getBuildingArea());
        yearBuilt_txt.setText(b.getYearBuilt());
        buildingID_txt.setText(b.getBuildingID());
        
        ifForUpdate = Boolean.TRUE;
    }

    private void useUpdateMethod() {
        Log1_AssetBuildingModel ab = new Log1_AssetBuildingModel();
        try{
            if(ab.update(new Object[][]{
                {"BuildingName", buildingName_txt.getText()},
                {"BuildingAddress", buildingAddress_txt.getText()},
                {"BuildingContact", buildingContact_txt.getText()},
                {"BuildingArea", buldingArea_txt.getText()},
                {"YearBuilt", yearBuilt_txt.getText()}
                
            }).where(new Object[][]{
                {"BuildingID", "=", buildingID_txt.getText()}
            }).executeUpdate()){
                 AlertMaker.showSimpleAlert("Update", ""+ buildingName_txt.getText()+" has been updated");
            }else{
                AlertMaker.showSimpleAlert("Failed", "Fail to update"+ buildingName_txt.getText()+"");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void setBuildingTypeAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/SetBuildingType.fxml"),
                 "Add Building Type", null);
    }
    
    public void loadBuildingToCombox(){
        Log1_BuildingTypeModel bt = new Log1_BuildingTypeModel();
        List BuildingType = bt.get();

        BuildingType.stream().forEach(row -> {
            HashMap hash = (HashMap) row;

            buildingType_combox.getItems().add(String.valueOf(hash.get("DesiredBuildingType")));
        });
    }
}
