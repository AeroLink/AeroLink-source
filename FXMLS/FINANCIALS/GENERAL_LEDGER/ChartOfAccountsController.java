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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coa_add_btn.setOnMouseClicked(e -> save());
        search_coa_txtfield.setOnKeyTyped(e -> SearchCOA());
        displayData();
        loadDataCOA();
     
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
         ObservableList<FINANCIAL_GL_COA> gl_coa = FXCollections.observableArrayList();
         
            List b = coa.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("code_no");
                hm.get("account_title");
               
                
               gl_coa.add(new FINANCIAL_GL_COA(
                String.valueOf(hm.get("code_no")),
                String.valueOf(hm.get("account_title"))
                ) );   
            }
            coa_table.setItems(gl_coa);
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
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("CAUTION!");
             alert.setContentText("PLEASE FILL THE EMPTY FIELDS"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
   displayData();
   loadDataCOA();
    }
    
    
    
    @FXML
    private void coa_add_button(ActionEvent event) {
                 
    }

    
    
}
