/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_equipmentTypeModel;
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
public class SetEquipmentTypeController implements Initializable {

    @FXML
    private TextField desiredEquipmentType;
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
        save_btn.setOnMouseClicked(e -> saveEquipmentType());
        cancel_btn.setOnMouseClicked(e -> close());
    }
    
    public void close(){
        Stage stage = (Stage) save_btn.getScene().getWindow();
        stage.close();
    }
    
    public void saveEquipmentType(){
        String desiredType = desiredEquipmentType.getText();
        
        Boolean flag = desiredType.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_equipmentTypeModel coa = new Log1_equipmentTypeModel();
        try{String [][] coa_table ={
            {"EquipmentTypeDesired",desiredEquipmentType.getText()}};
            if(coa.insert(coa_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ desiredEquipmentType.getText() +" has been added to Equipment's Type.");
                desiredEquipmentType.setText("");
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
