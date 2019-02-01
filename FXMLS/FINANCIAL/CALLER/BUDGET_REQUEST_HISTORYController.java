/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.CLASSFILES.Budget_Request_classfile;
import Model.Financial.Financial_budget_request;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class BUDGET_REQUEST_HISTORYController implements Initializable {

    @FXML
    private TableView<Budget_Request_classfile> requestHistory_tbl;
    @FXML
    private JFXButton btn;
    @FXML
    private JFXComboBox combobc_filter;

   
    ObservableList<String> fs = FXCollections.observableArrayList("Approved", "Declined");
    @FXML
    private Label no;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
        combobc_filter.setItems(fs);
        AddTableColumn_RequestHistory();
        LoadRequestHistory();
      
           int a = requestHistory_tbl.getItems().size();
           no.setText(String.valueOf(a));
          
    }    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Budget_Request_classfile> brc = FXCollections.observableArrayList();
    Financial_budget_request fbr = new Financial_budget_request();
    

    
    public void AddTableColumn_RequestHistory() {

        requestHistory_tbl.getItems().clear();
        requestHistory_tbl.getColumns().removeAll(requestHistory_tbl.getColumns());
        TableColumn<Budget_Request_classfile, String> reqno = new TableColumn<>("Request No");
        TableColumn<Budget_Request_classfile, String> brdate = new TableColumn<>("Date");
        TableColumn<Budget_Request_classfile, String> brrn = new TableColumn<>("Requestor");
        TableColumn<Budget_Request_classfile, String> brdep = new TableColumn<>("Department");
        TableColumn<Budget_Request_classfile, String> brdesc = new TableColumn<>("Description");
        TableColumn<Budget_Request_classfile, String> brpl = new TableColumn<>("Priority Level");
        TableColumn<Budget_Request_classfile, String> bramount = new TableColumn<>("Amount");
        TableColumn<Budget_Request_classfile, String> brstats = new TableColumn<>("Status");
        TableColumn<Budget_Request_classfile, String> brAB = new TableColumn<>("Process By");
        TableColumn<Budget_Request_classfile, String> brr = new TableColumn<>("Reason");
        
        
        reqno.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brrequestno);
        brdate.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdate);
        brrn.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brrequestorname);
        brdep.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdepartment);
        brdesc.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdescription);
        brpl.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brprioritylevel);
        bramount.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().bramount);
        brstats.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brstatus);
        brAB.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brApprovedBy);
        brr.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brReason);
        
        requestHistory_tbl.getColumns().addAll(reqno,brdate,brrn,brdep,brdesc,brpl,bramount,brstats,brAB,brr);
       
    } 
    

    public void LoadRequestHistory(){
            try{
                 List b = fbr.where(new Object[][]{
                             {"budget_status","=","Approved" }
                    }).orWhere("budget_status","=","Declined" ).get();
                            requestHistory(b);
            }catch(Exception e){
                 System.err.println(e);
            }

     }
    private void requestHistory(List req){
         
        requestHistory_tbl.getItems().clear();
        try {
             
              for(Object d : req)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("budget_id");
                hm.get("budget_date_request");
                hm.get("budget_department");
                hm.get("budget_requestor");
                hm.get("budget_description");
                hm.get("budget_priority_lvlment");
                hm.get("budget_amount");
                hm.get("budget_status");
                hm.get("budget_reason");
                
               brc.add(new Budget_Request_classfile(
                            String.valueOf(hm.get("budget_id")),
                            String.valueOf(hm.get("budget_date_request")),
                            String.valueOf(hm.get("budget_department")),
                            String.valueOf(hm.get("budget_requestor")),
                            String.valueOf(hm.get("budget_description")),
                            String.valueOf(hm.get("budget_priority_lvl")),
                            String.valueOf(hm.get("budget_amount")),
                            String.valueOf(hm.get("budget_status")),
                            String.valueOf(hm.get("budget_approvedby")),
                            String.valueOf(hm.get("budget_reason"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        requestHistory_tbl.setItems(brc);

     }

    @FXML
    private void FilterStatus(ActionEvent event) {
                  Financial_budget_request fbrs = new Financial_budget_request();
        try {

            List listreq = fbrs.where(new Object[][]{
                {"budget_status", "=", combobc_filter.getSelectionModel().getSelectedItem().toString()}
            }).get();

            requestHistory(listreq);
     
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }

    @FXML
    private void resetbtn(ActionEvent event) {
         Financial_budget_request fbrs = new Financial_budget_request();
        try {

            List b = fbrs.where(new Object[][]{
                    {"budget_status","=","Approved" }
                    }).orWhere("budget_status","=","Declined" ).get();
                            requestHistory(b);
     
        } catch (Exception e) {
            System.out.println(e);
        }

        
        
        
        
    }
    
    
    
}
