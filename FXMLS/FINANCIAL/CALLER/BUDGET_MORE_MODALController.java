/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_BR_CLASSFILE;
import FXMLS.FINANCIAL.FINANCIAL_BUDGET_MANAGEMENTController;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_MORE_MODALController implements Initializable {

    @FXML
    private JFXTextArea description_txtarea;
    @FXML
    private Label department_lbl;
    @FXML
    private Label daterequest_lbl;
    @FXML
    private Label amount_lbl;
    @FXML
    private Label requestor_lbl;
    @FXML
    private Label status_lbl;
    @FXML
    private Label prioritylvl;
    @FXML
    private JFXButton approved_btn;
    @FXML
    private JFXButton send_dis_btn;
    @FXML
    private Label label_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        approved_btn.setOnMouseClicked(e -> updateBRR());
        send_dis_btn.setOnMouseClicked(e -> SendtoDisbursement());
    }    
    
    
    
    public void SendtoDisbursement(){
        
                Financial_disbursement_request_model fbrr = new Financial_disbursement_request_model();
          try
        {
           String[][] dr_tbl =
        {
        {"disburse_date_req",daterequest_lbl.getText()},
        {"disburse_department",department_lbl.getText()},
        {"disburse_requestor",requestor_lbl.getText()},
        {"diburse_description",description_txtarea.getText()},
        {"disburse_prioritylvl",prioritylvl.getText()},
        {"disburse_amount",amount_lbl.getText()},
        {"disburse_status",status_lbl.getText()}
        };           
           
           
        if(fbrr.insert(dr_tbl)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Send");
             alert.setContentText("Data Has Been Send"); 
             alert.showAndWait();
             send_dis_btn.setDisable(true);
              approved_btn.setDisable(true);
              
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
            
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
 
    }
    public void DisplayData(FINANCIAL_BR_CLASSFILE req){
        
        prioritylvl.setText(req.getBudget_priority_lvl());
        description_txtarea.setText(req.getBudget_description());
        department_lbl.setText(req.getBudget_department());
        daterequest_lbl.setText(req.getBudget_date_request());
        amount_lbl.setText(req.getBudget_amount());
        requestor_lbl.setText(req.getBudget_requestor());
        status_lbl.setText(req.getBudget_status());
        label_id.setText(req.getBudget_id());
    }
    public void updateBRR(){
         Financial_budget_request fbrr = new Financial_budget_request();
    try{
    if(fbrr.update(new Object[][]{
    
         {"budget_status","Approved"}
            
    }).where(new Object[][]{
    
        {"budget_id","=",label_id.getText()}
    }).executeUpdate()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("It Worked");
             alert.setContentText("UPDATED"); 
             alert.showAndWait();
             send_dis_btn.setDisable(false);
             status_lbl.setText("Approved");
    }else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Error");
             alert.setContentText("FAIL"); 
             alert.showAndWait();
    }
    }catch(Exception e){
        e.printStackTrace();
    }
 
    }
    
    
 
    
    
    
}
