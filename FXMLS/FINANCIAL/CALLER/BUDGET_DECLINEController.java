/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.STATIC.CLASFILES.Finance_budget_decline;
import Model.Financial.Financial_budget_request;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_DECLINEController implements Initializable {

    @FXML
    private Label reqno_label;
    @FXML
    private JFXTextArea textarea;
    @FXML
    private JFXButton declined_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reqno_label.setText(Finance_budget_decline.reqno);
  
        declined_btn.setOnMouseClicked(e ->UpdateRequest());
        
        textarea.setOnMouseClicked(e -> declined_btn.setDisable(false));
    }    


    
    
    public void UpdateRequest(){
         Financial_budget_request budreq = new Financial_budget_request();
         try{
             if(budreq.update(new Object[][]{
                 {"budget_reason",textarea.getText()},
                 {"budget_status","Declined"},
                 {"budget_approvedby","Admin"}
             }).where(new Object[][]{
                 {"budget_id","=",reqno_label.getText()}
             }).executeUpdate()){
                 
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Declined");
             alert.setContentText("Request Declined"); 
             alert.showAndWait();
               Stage stage =(Stage) declined_btn.getScene().getWindow();
               stage.close();
             
             }else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Error");
             alert.setContentText("Declined ERROR"); 
             alert.showAndWait();
             }
             
         }catch (Exception e){
             System.out.print(e);
         }
    }
    
    
    

}
