/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_AssetDesiredBuildingTypeModel;
import Model.Log1.Log1_AssetDesiredEquipmentTypeModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class SetBuildingTypeController implements Initializable {

    @FXML
    private TextField desiredBuildingType;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton cancel_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        save_btn.setOnMouseClicked(e -> saveBuildingType());
        cancel_btn.setOnMouseClicked(e -> close());
    }
    
    public void close(){
        Stage stage = (Stage) save_btn.getScene().getWindow();
        stage.close();
    }

    private void saveBuildingType() {
        String desiredType = desiredBuildingType.getText();
        
        Boolean flag = desiredType.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_AssetDesiredBuildingTypeModel coa = new Log1_AssetDesiredBuildingTypeModel();
        try{String [][] coa_table ={
            {"DesiredBuildingType",desiredBuildingType.getText()}};
            if(coa.insert(coa_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ desiredBuildingType.getText() +" has been added to Building's Type.");
                desiredBuildingType.setText("");
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
