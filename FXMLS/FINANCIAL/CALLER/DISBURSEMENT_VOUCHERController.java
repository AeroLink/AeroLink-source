/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.STATIC.CLASFILES.Finance_CreateVoucher;
import Model.Financial.Financial_disbursement_request_model;
import Model.Financial.Financial_disbursement_voucher;
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
public class DISBURSEMENT_VOUCHERController implements Initializable {

    @FXML
    private Label reqno_lbl;
    @FXML
    private Label datereq_lbl;
    @FXML
    private Label dep_lbl;
    @FXML
    private Label claimant_lbl;
    @FXML
    private Label amount_lbl;
    @FXML
    private Label status_lbl;
    @FXML
    private Label priority_lbl;
    @FXML
    private JFXTextArea desc_txtarea;
    @FXML
    private Label dr_id_label;
    @FXML
    private JFXButton disburse_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        reqno_lbl.setText(Finance_CreateVoucher.RequestNo);
        datereq_lbl.setText(Finance_CreateVoucher.DateRequest);
        dep_lbl.setText(Finance_CreateVoucher.Department);
        claimant_lbl.setText(Finance_CreateVoucher.Requestor);
        desc_txtarea.setText(Finance_CreateVoucher.Description);
        amount_lbl.setText(Finance_CreateVoucher.Amount);
        status_lbl.setText(Finance_CreateVoucher.Status);
        priority_lbl.setText(Finance_CreateVoucher.PriorityLevel);
        dr_id_label.setText(Finance_CreateVoucher.id);
        
        disburse_btn.setOnMouseClicked(e ->disburseBtn() );
    }    
    
      
 public void disburseBtn(){
        
           Financial_disbursement_request_model fbr = new Financial_disbursement_request_model();
           Financial_disbursement_voucher fddr = new Financial_disbursement_voucher();
            
           try
           {
                if(fbr.update(new Object[][]{
                {"dr_status","Released"}})
                .where(new Object[][]{
                {"dr_id","=",dr_id_label.getText()}
                }).executeUpdate())
                {
                    
           String[][] dr_table =
        {
        {"budget_req_no" ,     reqno_lbl.getText()},
        {"budget_date_req" ,   datereq_lbl.getText()},
        {"dv_department" ,     dep_lbl.getText()},
        {"dv_claimant" ,       claimant_lbl.getText()},
        {"dv_requestor" ,      claimant_lbl.getText()},
        {"dv_description" ,    desc_txtarea.getText()},
        {"dv_priority_level" , priority_lbl.getText()},
        {"dv_amount" ,         amount_lbl.getText()},
        {"dv_budget_status" ,  "Approved"},
        };           
           
                fddr.insert(dr_table);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Disbursed");
             alert.setContentText("Request Has Been Disbursed"); 
             alert.showAndWait();
               disburse_btn.setDisable(true);
             Stage stage =(Stage) reqno_lbl.getScene().getWindow();
             stage.close();
               
               
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Disbursement Failed"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }    
           
    }
    
    
    
    
    
}
