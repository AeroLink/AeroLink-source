/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import Model.Financial.Financial_allocation_model;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_NEWALLOCATIONController implements Initializable {

    @FXML
    private JFXComboBox<String> ba_cmbo;
    @FXML
    private JFXTextField ba_amount_txtfield;
    @FXML
    private JFXButton ba_insert_btn;

    
    
        ObservableList<String> sdl = FXCollections.observableArrayList("Training Management", 
                                                                                "Procurement", 
                                                                                "Reimbursement", 
                                                                                "Payroll");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ba_cmbo.setItems(sdl);
        ba_insert_btn.setOnMouseClicked(e -> insertNewAllocation());
    }    
      public void insertNewAllocation(){
         Financial_allocation_model fba = new Financial_allocation_model();
            
          try
        {
           String[][] fba_table =
        {
        {"ba_department" , ba_cmbo.getValue().toString()},
        {"ba_amount" , ba_amount_txtfield.getText()}
        };           
           
           
        if(fba.insert(fba_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVED"); 
             alert.showAndWait();
             ba_cmbo.setValue(null);
             ba_amount_txtfield.clear();
             Stage stage = (Stage) ba_insert_btn.getScene().getWindow();
             stage.close();
            
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("PLEASE FILL THE EMPTY FIELDS"); 
             alert.showAndWait();
             ba_cmbo.setValue(null);
             ba_amount_txtfield.clear();
            
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }

    }
}
