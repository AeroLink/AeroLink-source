/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import Model.Financial.Financial_budget_request;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_REQUESTController implements Initializable {

    
        ObservableList<String> priority = FXCollections.observableArrayList("Critical", "Low");
        ObservableList<String> dept = FXCollections.observableArrayList("Logistic 1 - Procurement",
                                                                        "Human Resource - Payroll",
                                                                        "Human Resource - Reimbursement",
                                                                        "Human Resource - Training Management");
    @FXML
    private JFXTextField requestor;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXComboBox<String> priority_level;
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXComboBox<String> department;
    @FXML
    private JFXButton submit_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      priority_level.setItems(priority);
      department.setItems(dept);
      
      submit_btn.setOnMouseClicked(e -> submitRequest());
    }    
 
    
    
    
    public void submitRequest(){
        
                Financial_budget_request fbr = new Financial_budget_request();
          try
        {
           String[][] b_tbl =
        {
        {"budget_requestor",requestor.getText()},
        {"budget_description",description.getText()},
        {"budget_priority_lvl",priority_level.getValue()},
        {"budget_amount",amount.getText()},
        {"budget_department",department.getValue()}
        };           
           
           
        if(fbr.insert(b_tbl)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Send");
             alert.setContentText("Request Submitted"); 
             alert.showAndWait();
             requestor.clear();
             description.clear();
             priority_level.setValue(null);
             amount.clear();
             department.setValue(null);
              
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
             requestor.clear();
             description.clear();
             priority_level.setValue(null);
             amount.clear();
             department.setValue(null);
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    }
}
