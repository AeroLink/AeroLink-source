/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseDesiredItemUnitModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ItemUnitController implements Initializable {

    @FXML
    private TextField desiredItemUnit_txt;
    @FXML
    private JFXButton addItemUnit_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addItemUnit_btn.setOnMouseClicked(e -> saveItemUnit());
        // TODO
    }    
    public void saveItemUnit(){
        String desiredUnit = desiredItemUnit_txt.getText();
        
        Boolean flag = desiredUnit.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_WarehouseDesiredItemUnitModel coa = new Log1_WarehouseDesiredItemUnitModel();
        try{String [][] coa_table ={
            {"DesiredItemUnit",desiredItemUnit_txt.getText()}};
            if(coa.insert(coa_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ desiredItemUnit_txt.getText() +" has been added to Item's Units ");
                desiredItemUnit_txt.setText("");
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    }    

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) desiredItemUnit_txt.getScene().getWindow();
        stage.close();
    }
}
