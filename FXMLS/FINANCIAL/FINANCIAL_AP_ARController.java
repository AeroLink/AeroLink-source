/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_COA_CLASSFILE;
import Model.Financial.Financial_coa_model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_AP_ARController implements Initializable {

    @FXML
    private TableView<FINANCIAL_COA_CLASSFILE> coa_tbl;
    
    
    private JFXComboBox<String> cdno_cmbbx;
    private JFXTextField account_title;

    
      ObservableList<String> coaObs = FXCollections.observableArrayList("Cash",  "Petty Cash", "Supplies",
                                                                        "Prepaid Insurance","Sales","Advertising Expense",
                                                                        "Insurance Expense","Repair Expense","Utilities Expense",
                                                                        "Office Equipment");
    @FXML private JFXButton saveCoa_btn;
    @FXML private JFXButton updateCoa_btn;
    @FXML private JFXComboBox<String> account_title_cmbobx;
    @FXML private JFXTextField codeno_txt;
    @FXML
    private Label label_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showCoa();
       loadDataCOA();
       
       saveCoa_btn.setOnMouseClicked(e -> saveCoa());
         updateCoa_btn.setOnMouseClicked(e -> updateBRR());
        account_title_cmbobx.setItems(coaObs);
        coa_tbl.setOnMouseClicked(e -> {
        FINANCIAL_COA_CLASSFILE cc = coa_tbl.getSelectionModel().getSelectedItem();
        
        label_id.setText(cc.getCoa_id());
        account_title_cmbobx.setValue(cc.getAcc_title());
        codeno_txt.setText(cc.getCode_no());
        updateCoa_btn.setDisable(false);
              });
    }    
    
    public void updateBRR(){
          Financial_coa_model fcm = new Financial_coa_model();
    try{
    if(fcm.update(new Object[][]{
       {"code_no",codeno_txt.getText()},
       {"acc_title",account_title_cmbobx.getValue().toString()}
    }).where(new Object[][]{
        {"coa_id","=",label_id.getText()}
    }).executeUpdate()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("UPDATED"); 
             alert.showAndWait();
             codeno_txt.clear();
             account_title_cmbobx.setValue(null);
             updateCoa_btn.setDisable(true);
    }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Error");
             alert.setContentText("FAIL"); 
             alert.showAndWait();
    }
    }catch(Exception e){
        e.printStackTrace();
    }
     showCoa();
     loadDataCOA();
    
    }

    
      public void saveCoa(){
           
            Financial_coa_model coa = new Financial_coa_model();
            
          try
        {
           String[][] coa_table =
        {
        {"code_no" , codeno_txt.getText()},
        {"acc_title" , account_title_cmbobx.getValue()}
        };           
           
           
        if(coa.insert(coa_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
             codeno_txt.clear();
             account_title_cmbobx.setValue(null);
             
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("PLEASE FILL THE EMPTY FIELDS"); 
             alert.showAndWait();
            
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
          
          
       showCoa();
       loadDataCOA();
       
    }
    public void showCoa(){
         coa_tbl.getItems().clear();
        coa_tbl.getColumns().removeAll(coa_tbl.getColumns());

        TableColumn<FINANCIAL_COA_CLASSFILE, String> cn = new TableColumn<>("Code No");
        TableColumn<FINANCIAL_COA_CLASSFILE, String> at = new TableColumn<>("Account Title");

        
        cn.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_COA_CLASSFILE, String> param) -> param.getValue().cn);
        at.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_COA_CLASSFILE, String> param) -> param.getValue().at);
      
        coa_tbl.getColumns().addAll(cn,at);
    }
    
    public void loadDataCOA(){
         Financial_coa_model coa = new Financial_coa_model();
         ObservableList<FINANCIAL_COA_CLASSFILE> gl_coa1 = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                 hm.get("coa_id");
                hm.get("code_no");
                hm.get("acc_title");
                
               gl_coa1.add(new FINANCIAL_COA_CLASSFILE(
                       
                String.valueOf(hm.get("coa_id")),
                String.valueOf(hm.get("code_no")),
                String.valueOf(hm.get("acc_title"))
                ) );   
               
            }
            coa_tbl.setItems(gl_coa1);
            }
    
    
    
    
    
}
