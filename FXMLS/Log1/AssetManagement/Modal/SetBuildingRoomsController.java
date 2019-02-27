/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetBuildingModel;
import Model.Log1.Log1_AssetBuildingRoomsModel;
import Model.Log1.Log1_AssetLandModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class SetBuildingRoomsController implements Initializable {

    @FXML
    private ComboBox building_combox;
    @FXML
    private Label buildingID_txt;
    @FXML
    private Label capacity_txt;
    @FXML
    private Label numOfRooms_txt;
    @FXML
    private TextField roomNumber_txt;
    @FXML
    private Label tot_txt;
    @FXML
    private HBox enterVal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadBuildingToCombox();
        building_combox.setOnAction(e-> getComboxIndex());
        enterVal.setDisable(true);
        
    }    

    public void loadBuildingToCombox(){
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        
        List AssetLand = assetBuilding.get();

        AssetLand.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            building_combox.getItems().add(String.valueOf(hash.get("bAssetTitle")));
        });
    }
    
    public void clear(){
        roomNumber_txt.setText("");
        numOfRooms_txt.setText("0");
        capacity_txt.setText("0");
        building_combox.setValue("");
    }
    
    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) building_combox.getScene().getWindow();
        stage.close();
    }
    
    private void getComboxIndex() {
        Log1_AssetBuildingModel assetBuilding = new Log1_AssetBuildingModel();
        List list_coa = assetBuilding.where(new Object[][]{
           {"bAssetTitle", "=", building_combox.getSelectionModel().getSelectedItem().toString()}
        }).get();
        for(Object d : list_coa){//rs = hm
            
            HashMap hm = (HashMap) d;   //exquisite casting
                
            buildingID_txt.setText(( 
                String.valueOf(hm.get("BuildingID"))
            ));
            capacity_txt.setText((
                String.valueOf(hm.get("RoomNumberCapacity"))
            ));
            numOfRooms_txt.setText((
                String.valueOf(hm.get("TotalOfRoomNumber"))
            ));
            limit();
            
            
        }
    }

    public void limit(){
        int x = Integer.parseInt(capacity_txt.getText());
        int z = Integer.parseInt(numOfRooms_txt.getText());
        
        if(x==z){
            enterVal.setDisable(true);
            AlertMaker.showErrorMessage("", "Building already reached it's limit");
            return;
        } else{
            enterVal.setDisable(false);
        }
    }
    
    @FXML
    private void add(ActionEvent event) {
        String input = roomNumber_txt.getText();
        
        Boolean flag = input.isEmpty();
        
        int x = Integer.parseInt(numOfRooms_txt.getText());
        String z = String.valueOf(x+1);
        tot_txt.setText(z);
        
        if(flag){
            AlertMaker.showErrorMessage("", "Invalid");
        }else{
            try{
                Log1_AssetBuildingRoomsModel ABR = new Log1_AssetBuildingRoomsModel();
        Log1_AssetBuildingModel LM = new Log1_AssetBuildingModel();
                String[][] roomNumber ={
                    {"BuildingID",buildingID_txt.getText()},
                    {"bAssetTitle",building_combox.getValue().toString()},
                    {"RoomCode",building_combox.getValue().toString()+ " - " + "Room Number " +roomNumber_txt.getText()},
                    {"Status", "Vacant"},
                };
                if(ABR.insert(roomNumber)){
                    AlertMaker.showSimpleAlert("", "Room Added to building");
                    LM.update(new Object[][]{
                        {"TotalOfRoomNumber" ,tot_txt.getText()}
                    }).where(new Object [][]{
                        {"BuildingID", "=", buildingID_txt.getText()}
                    }).executeUpdate();
                    clear();
                    enterVal.setDisable(true);
                    return;
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
