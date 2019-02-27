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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_REQUESTFORMController implements Initializable {

    @FXML private JFXTextField requestor;
    @FXML private JFXComboBox<String> department;
    @FXML private JFXTextArea description;
    @FXML private JFXTextField amount;
    @FXML private JFXButton submit_btn;
    @FXML private JFXComboBox<String> prioritylevel;

      ObservableList<String> level = FXCollections.observableArrayList("","Urgent","Emergency");
      ObservableList<String> chooseDept = FXCollections.observableArrayList("",
                                                                            "Human Resource - Training Management",
                                                                            "Human Resource - Payroll",
                                                                            "Human Resource - Reimbursement",
                                                                            "Logistic - Procurement");
    @FXML
    private JFXTextField lastname;
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        department.setItems(chooseDept);
        prioritylevel.setItems(level);
        
        submit_btn.setOnMouseClicked(e ->submitRequest());
    }    
    
     public void submitRequest(){
        
          Financial_budget_request fbr = new Financial_budget_request();
          try
        {
           String[][] b_tbl =
        {
        {"budget_firstname",requestor.getText()},
        {"budget_lastname",lastname.getText()},
        {"budget_description",description.getText()},
        {"budget_priority_lvl",prioritylevel.getValue()},
        {"budget_amount",amount.getText()},
        {"budget_department",department.getValue()},
        };           
           
        if(fbr.insert(b_tbl)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Send");
             alert.setContentText("Request Submitted"); 
             alert.showAndWait();
             requestor.clear();
             description.clear();
             prioritylevel.setValue(null);
             amount.clear();
             department.setValue(null);
             Stage stage =(Stage) requestor.getScene().getWindow();
             stage.close();
              
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
             requestor.clear();
             description.clear();
             prioritylevel.setValue(null);
             amount.clear();
             department.setValue(null);
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    }

    @FXML
    private void requestor_limit(KeyEvent event) {
         if (requestor.getText().length() == 50) {
            event.consume();
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setContentText("Characters Only");
            alert.showAndWait();
            
          
    }
    
    
    }
    
    
    
}
