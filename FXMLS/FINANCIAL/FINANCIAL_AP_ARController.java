/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.AP_classfile;
import FXMLS.FINANCIAL.CLASSFILES.AR_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Budget_Request_classfile;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import Synapse.Session;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FINANCIAL_AP_ARController implements Initializable {

    @FXML
    private TableView<AP_classfile> ap_tbl;
    @FXML
    private TableColumn<AP_classfile, String> amount_id;

    /**
     * Initializes the controller class.
     */


    @FXML
    private Label total_label;
    @FXML
    private TableView<AR_classfile> ar_tbl;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        AddtableColumn_AP();    
        loadtable_ap();
 
        //total_label.setText(String.valueOf(TotalLabel()));
        
        AddtableColumn_AR();
    }   
    
    
    
    
    
    double total = 0;
  
    public void AddtableColumn_AR(){
        ar_tbl.getItems().clear();
        ar_tbl.getColumns().removeAll(ar_tbl.getColumns());
        
        TableColumn<AR_classfile, String> d = new TableColumn<>("Date");
        TableColumn<AR_classfile, String> in = new TableColumn<>("Invoice No.");
        TableColumn<AR_classfile, String> des = new TableColumn<>("Description");
        TableColumn<AR_classfile, String> am = new TableColumn<>("Amount");
        TableColumn<AR_classfile, String> st = new TableColumn<>("Status");
       
        d.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDate);
        in.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arInvoiceno);
        des.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDescription);
        am.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arAmount);
        st.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arStatus);
        
        ar_tbl.getColumns().addAll(d,in,des,am,st);
    }
    
    
   
    
    public void AddtableColumn_AP(){
        
        ap_tbl.getItems().clear();
        ap_tbl.getColumns().removeAll(ap_tbl.getColumns());
        TableColumn<AP_classfile, String> apd = new TableColumn<>("Date");
        TableColumn<AP_classfile, String> apde = new TableColumn<>("Department");
        TableColumn<AP_classfile, String> apa = new TableColumn<>("Amount");
        TableColumn<AP_classfile, String> aps = new TableColumn<>("Status");
       
        apd.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDate);
        apde.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDepartment);
        apa.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apAmount);
        aps.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apStatus);
        
        ap_tbl.getColumns().addAll(apd,apde,apa,aps);
    
    }
    
   
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<AP_classfile> brc = FXCollections.observableArrayList();
    Financial_disbursement_request_model fbr = new Financial_disbursement_request_model();
    
  
    
    public void loadtable_ap(){
        
          CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_apr")) {
                
                try {
                    fbr.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                            ap_tbl.getItems();
                            List b = fbr.where("dr_status","=","Pending").get();
                           
                            request(b);
                            total_label.setText(String.valueOf(fbr.where("dr_status","=","Pending").get("sum(dr_amount)")));
                    // total_label.setText(String.valueOf(fbr.where("dr_status","=","Pending").get("sum(dr_amount)")));
                             
                     {
                       
                         
                    }
                    ap_tbl.setItems(brc);
                        GlobalCount = DummyCount;
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

     }
     
     private int request(List req){
         
        ap_tbl.getItems().clear();
        ObservableList<AP_classfile> fdrc = FXCollections.observableArrayList();

        try {
             
              for(Object d : req)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("dr_requestno");
                hm.get("dr_daterequest");
                hm.get("dr_department");
                hm.get("dr_requestor");
                hm.get("dr_description");
                hm.get("dr_prioritylvl");
                hm.get("dr_amount");
                hm.get("dr_status");
                
               brc.add(new AP_classfile(
                            String.valueOf(hm.get("dr_daterequest")),
                            String.valueOf(hm.get("dr_department")),
                            String.valueOf(hm.get("dr_amount")),
                            String.valueOf(hm.get("dr_status"))
                ) );   
               
        /* for(int i  = 0; i<ap_tbl.getItems().size(); i++){
         int amount = Integer.parseInt(ap_tbl.getItems().get(3).apAmount.getValue());
         total += amount;
                    }
        total_label.setText(String.valueOf(total));
      
        */

            }
                
        } catch (Exception e) {
            System.out.println(e);
        }

        ap_tbl.setItems(fdrc);
        return 0;
       
     }
       
    
    
}
