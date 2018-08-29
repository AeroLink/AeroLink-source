/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS.GENERAL_LEDGER;

import FXMLS.FINANCIALS.CLASSFILES.FINANCIAL_AP;
import FXMLS.FINANCIALS.CLASSFILES.FINANCIAL_GL_COA;
import Model.FINANCE_GL;

import Model.FINANCIAL_APR;
import Model.FINANCIAL_DISBURSEMENT_VOUCHER;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.bcel.internal.generic.Select;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class ChartOfAccountsController implements Initializable {

    @FXML
    private JFXTextField acc_title_txtfield;
    @FXML
    private TableView<FINANCIAL_GL_COA> coa_table;
    @FXML
    private JFXButton coa_add_btn;
    @FXML
    private JFXTextField code_txtfield;
    
ObservableList<String> sdl = FXCollections.observableArrayList();

    @FXML
    private TableColumn<FINANCIAL_GL_COA, String> gl_coa_id;
    @FXML
    private TableColumn<FINANCIAL_GL_COA, String> gl_acc_title_id;
    
   // FINANCE_GL searchCOA;
    private JFXTextField search_coa;
    @FXML
    private JFXButton search_btn;
    @FXML
    private JFXTextField search_coa_txtfield;
    @FXML
    private JFXButton coa_new_btn;
    @FXML
    private JFXButton coa_edit_btn;
    @FXML
    private JFXButton coa_delete_btn;
 List del;
  ObservableList<FINANCIAL_GL_COA> gl_coa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gl_coa= FXCollections.observableArrayList();
        coa_add_btn.setOnMouseClicked(e -> save());
        
        //coa_delete_btn.setOnMouseClicked(e -> Delete());
    
        //coa_edit_btn.setOnMouseClicked(e -> UpdateTable());
        search_coa_txtfield.setOnKeyTyped(e -> SearchCOA());
        coa_new_btn.setOnMouseClicked(e -> enable());
        displayData();
        loadDataCOA();
        disable();
     
   
    }    
    
    public void disable(){
        coa_edit_btn.setDisable(true);
        coa_delete_btn.setDisable(true);
        coa_add_btn.setDisable(true);
        code_txtfield.setDisable(true);
        acc_title_txtfield.setDisable(true);
        
    }
    public void enable(){
        coa_edit_btn.setDisable(false);
        coa_delete_btn.setDisable(false);
        coa_add_btn.setDisable(false);
        code_txtfield.setDisable(false);
        acc_title_txtfield.setDisable(false);
        
    }
    public void SearchCOA()
    {
        //object conditions[][] ={
            FINANCE_GL searchCOA = new FINANCE_GL();  
            try{
            List list = searchCOA.where(new Object[][]{
            {"code_no", "like", "%" + search_coa_txtfield.getText() + "%"}
        }).get();
            }catch(Exception e){
                e.printStackTrace();
            }
            
    }
    
    
    public void displayData(){
       gl_coa_id.setCellValueFactory(new PropertyValueFactory<>("code_no"));
       gl_acc_title_id.setCellValueFactory(new PropertyValueFactory<>("account_title"));
    }
    
    
    
    public void loadDataCOA(){
         FINANCE_GL coa = new FINANCE_GL();
         ObservableList<FINANCIAL_GL_COA> gl_coa1 = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("code_no");
                hm.get("account_title");
                
               gl_coa1.add(new FINANCIAL_GL_COA(
                String.valueOf(hm.get("code_no")),
                String.valueOf(hm.get("account_title"))
                ) );   
               
            }
            coa_table.setItems(gl_coa1);
            
            }

    
    
    public void save(){
           
            FINANCE_GL coa = new FINANCE_GL();
            
          try
        {
           String[][] coa_table =
        {
        {"code_no" , code_txtfield.getText()},
        {"account_title" , acc_title_txtfield.getText()}
        };           
           
           
        if(coa.insert(coa_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
             code_txtfield.clear();
             acc_title_txtfield.clear();
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("PLEASE FILL THE EMPTY FIELDS"); 
             alert.showAndWait();
             code_txtfield.clear();
             acc_title_txtfield.clear();
            
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
   displayData();
   loadDataCOA();
    disable();
    }
    
    
    
    @FXML
    private void coa_add_button(ActionEvent event) {
                 
    }

    
    
}
