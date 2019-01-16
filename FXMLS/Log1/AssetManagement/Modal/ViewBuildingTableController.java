/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetBuildingClassfiles;
import FXMLS.Log1.WarehouseManagementController;
import FXMLS.Log1.util.AlertMaker;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_AssetBuildingModel;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ViewBuildingTableController implements Initializable {

    @FXML
    private TableView<Log1_AssetBuildingClassfiles> building_tbl;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> building_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> address_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> contact_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> area_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> yearBuilt_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> Status_col;
    @FXML
    private TableColumn<Log1_AssetBuildingClassfiles, String> Type_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        callBuildingData();
        displayBuildingData();
    } 
    
    public void callBuildingData(){
         Log1_AssetBuildingModel Building = new Log1_AssetBuildingModel();
         ObservableList<Log1_AssetBuildingClassfiles> building = FXCollections.observableArrayList();
          
            List b = Building.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               building.add(new Log1_AssetBuildingClassfiles(
                
                String.valueOf(hm.get("BuildingID")),
                String.valueOf(hm.get("BuildingName")),
                String.valueOf(hm.get("BuildingAddress")),
                String.valueOf(hm.get("BuildingContact")),
                String.valueOf(hm.get("BuildingArea")),
                String.valueOf(hm.get("YearBuilt")),
                String.valueOf(hm.get("BuildingStatus")),
                String.valueOf(hm.get("BuildingFacilityType"))
                ));       
            }
            building_tbl.setItems(building);
    }
    
    public void displayBuildingData(){
        building_col.setCellValueFactory(new PropertyValueFactory<>("BuildingName"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("BuildingAddress"));
        contact_col.setCellValueFactory(new PropertyValueFactory<>("BuildingContact"));
        area_col.setCellValueFactory(new PropertyValueFactory<>("BuildingArea"));
        yearBuilt_col.setCellValueFactory(new PropertyValueFactory<>("YearBuilt"));
        Status_col.setCellValueFactory(new PropertyValueFactory<>("BuildingStatus"));
        Type_col.setCellValueFactory(new PropertyValueFactory<>("BuildingFacilityType"));
    }
    
    @FXML
    private void updateBuildingAction(ActionEvent event) {
        Log1_AssetBuildingClassfiles selectedForEdit = building_tbl.getSelectionModel().getSelectedItem();
        if(selectedForEdit == null){
            AlertMaker.showErrorMessage("No Building Selected","Please Select A Building From the table to proceed");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
            Parent parent = loader.load();
            
            AddBuildingController controller = (AddBuildingController) loader.getController();
            controller.displayBuildingData(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Update Building Details");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AddBuildingAction(ActionEvent event) {
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"),
                 "Add New Building", null);
    }
}
