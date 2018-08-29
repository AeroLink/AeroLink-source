/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS;

import FXMLS.FINANCIALS.CLASSFILES.FINANCIAL_DVC_CLASS;
import Model.FINANCIAL_DISBURSEMENT_VOUCHER;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class FINANCIAL_DISBURSEMENTController implements Initializable {

    @FXML
    private JFXDatePicker disburse_date;
    @FXML
    private JFXTextField claimants_txtffield;
    @FXML
    private JFXTextArea particulars_txtarea;
    @FXML
    private JFXTextField disburse_amount;

    
    
    ObservableList<String> dv = FXCollections.observableArrayList();
    @FXML
    private JFXButton dis_save;
LocalDate Date;
    @FXML
    private TableView<FINANCIAL_DVC_CLASS> disbursement_table;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvno_col;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvdate_col;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvclaimants_col;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvparticular_col;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvamount_col;
    @FXML
    private TableColumn<FINANCIAL_DVC_CLASS, String> dvbc_col;
    @FXML
    private JFXButton dvprint_btn;
    @FXML
    private JFXButton dvnew_btn;
    private JFXButton d_record_btn;
    @FXML
    private JFXDatePicker d_record_date;
    @FXML
    private JFXTextField d_record_claimant_txtfield;
    @FXML
    private JFXTextField d_record_particular_txtfield;
    @FXML
    private JFXButton d_record_button;
    @FXML
    private JFXTextField d_amount_txtfield;
    @FXML
    private JFXButton d_record_print_bttn;
    @FXML
    private JFXButton d_record_edit_bttn;
    @FXML
    private JFXButton d_record_del_bttn;
    @FXML
    private JFXButton d_clear_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dis_save.setOnMouseClicked(e -> d_save());
        dvnew_btn.setOnMouseClicked(e -> d_enable());
        d_record_button.setOnMouseClicked(e -> d_record());
        d_clear_btn.setOnMouseClicked(e -> d_clear());
       
        d_loadData();
        d_displayData();
        d_disable();
        d_record_disable();
    }    
    
    public void d_displayData(){
         dvno_col.setCellValueFactory(new PropertyValueFactory<>("dv_no"));
         dvdate_col.setCellValueFactory(new PropertyValueFactory<>("date"));
         dvclaimants_col.setCellValueFactory(new PropertyValueFactory<>("claimants"));
         dvparticular_col.setCellValueFactory(new PropertyValueFactory<>("particular"));
         dvamount_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
         
    }
    public void d_clear(){
        disburse_date.setValue(null);
        claimants_txtffield.clear();
        particulars_txtarea.clear();
        disburse_amount.clear();
                
    }
    public void d_enable(){
        disburse_date.setDisable(false);
        claimants_txtffield.setDisable(false);
        particulars_txtarea.setDisable(false);
        disburse_amount.setDisable(false);
        dis_save.setDisable(false);
        dvprint_btn.setDisable(false);
        dvnew_btn.setDisable(true);
      
         
        
    }
    public void d_disable(){
        disburse_date.setDisable(true);
        claimants_txtffield.setDisable(true);
        particulars_txtarea.setDisable(true);
        disburse_amount.setDisable(true);
        dis_save.setDisable(true);
        dvprint_btn.setDisable(false);
       
    }
    
    
    public void d_record_disable(){
        d_record_button.setDisable(true);
        d_record_print_bttn.setDisable(true);
        d_record_edit_bttn.setDisable(true);
        d_record_del_bttn.setDisable(true);
    }
        
    
    
    public void d_loadData(){
         FINANCIAL_DISBURSEMENT_VOUCHER dvc = new FINANCIAL_DISBURSEMENT_VOUCHER();
         ObservableList<FINANCIAL_DVC_CLASS> dvClass = FXCollections.observableArrayList();
          
            List b = dvc.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("dv_no");
                hm.get("date");
                hm.get("claimants");
                hm.get("particular");
                hm.get("amount");
                
                
               dvClass.add(new FINANCIAL_DVC_CLASS(
                String.valueOf(hm.get("dv_no")),
                String.valueOf(hm.get("date")),
                String.valueOf(hm.get("claimants")),
                String.valueOf(hm.get("particular")),
                String.valueOf(hm.get("amount"))
                ) );   
               
            }
            disbursement_table.setItems(dvClass);
            
            }
    
    //Disbursement Record
   public void d_record(){
       
   FINANCIAL_DISBURSEMENT_VOUCHER fdv = new FINANCIAL_DISBURSEMENT_VOUCHER();
    try{
           String[][] disburse_table =
        {
        {"date" , d_record_date.getValue().toString()},
        {"claimants" , d_record_claimant_txtfield.getText()},
        {"particular" , d_record_particular_txtfield.getText()},
        {"amount" , d_amount_txtfield.getText()}
        };           
           
           if(fdv.insert(disburse_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
           }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
           }
                                       
            }catch(Exception e)
               {
           Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Check Again");
             alert.setContentText("Please Fill the fields"); 
             alert.showAndWait();
               }
        d_loadData();
        d_displayData();  
           
    }

   
   //Disbursement Voucher
     public void d_save(){
           
            FINANCIAL_DISBURSEMENT_VOUCHER fdv = new FINANCIAL_DISBURSEMENT_VOUCHER();
    try{
           String[][] disburse_table =
        {
        {"date" , disburse_date.getValue().toString()},
        {"claimants" , claimants_txtffield.getText()},
        {"particular" , particulars_txtarea.getText()},
        {"amount" , disburse_amount.getText()}
        };           
           
           if(fdv.insert(disburse_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
           }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
           }
                                       
            }catch(Exception e)
               {
           Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Check Again");
             alert.setContentText("Please Fill the fields"); 
             alert.showAndWait();
               }
       d_loadData();
       d_displayData();
           d_disable();
           dvnew_btn.setDisable(false);
           
    }

    
}
