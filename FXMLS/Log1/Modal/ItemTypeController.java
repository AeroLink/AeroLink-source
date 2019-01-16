/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseDesiredItemTypeModel;
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
public class ItemTypeController implements Initializable {

    @FXML
    private TextField desiredItemType_txt;
    @FXML
    private JFXButton addItemType_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addItemType_btn.setOnMouseClicked(e -> saveItemType());
    }    
    public void saveItemType(){
        String desiredType = desiredItemType_txt.getText();
        
        Boolean flag = desiredType.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_WarehouseDesiredItemTypeModel coa = new Log1_WarehouseDesiredItemTypeModel();
        try{String [][] coa_table ={
            {"DesiredItemType",desiredItemType_txt.getText()}};
            if(coa.insert(coa_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ desiredItemType_txt.getText()+" added to List of Item Types");
                desiredItemType_txt.setText("");
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) desiredItemType_txt.getScene().getWindow();
        stage.close();
    }
}
