/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.AP_classfile;
import FXMLS.FINANCIAL.CLASSFILES.AR_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Budget_Request_classfile;
import Model.Financial.Financial_ar_model;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import Model.Financial.Log_assetsales_model;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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
public class FINANCIAL_AP_ARController implements Initializable {

    @FXML
    private TableView<AP_classfile> ap_tbl;
    @FXML
    private TableColumn<AP_classfile, String> amount_id;

    /**
     * Initializes the controller class.
     */


    @FXML private Label total_label;
    @FXML private TableView<AR_classfile> ar_tbl;
    @FXML private TableColumn<?, ?> dateCol;
    @FXML  private TableColumn<?, ?> deptCol;
    @FXML  private TableColumn<?, ?> statusCol;
    @FXML private Label ar_total_lbl;
    @FXML
    private JFXButton pom_btn;
    @FXML
    private JFXButton asset_btn;
    @FXML
    private JFXComboBox salesStatus_cmbbx;
    @FXML
    private JFXComboBox journalStatus_cmbbx;
    
    ObservableList<String> salesstats = FXCollections.observableArrayList("Collected","Uncollected");
    ObservableList<String> journalstats = FXCollections.observableArrayList("Not Posted","Posted");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        AddtableColumn_AP();    
        loadtable_ap();
        
        
        salesStatus_cmbbx.setItems(salesstats);
        AddtableColumn_AR();
        Loadtable_Ar();
     
        pom_btn.setOnMouseClicked(e ->openPOM());
        asset_btn.setOnMouseClicked(e ->openAsset());
       // totalAP();
        //totalAR();
        
        
        
    }   

    
     private void Ar(List arr){
        ar_tbl.getItems().clear();
        try {
              for(Object d : arr)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("ast_date");
                hm.get("ast_id");
                hm.get("ast_description");
                hm.get("ast_amount");
                hm.get("ast_status");
                hm.get("journal_status");
                ars.add(new AR_classfile(
                            String.valueOf(hm.get("ast_date")),
                            String.valueOf(hm.get("ast_id")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_status")),
                            String.valueOf(hm.get("journal_status"))
                ) );   
                 
            }
              
        } catch (Exception e) {
            System.out.println(e);
        }

        ar_tbl.setItems(ars);
   
       
     }
     
     public void openAsset(){
          Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/Asset.fxml").getParent());
          md.open();
         
     }
     public void openPOM(){
         Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/PurchaseOrder.fxml").getParent());
         md.open();
     }
       
    Log_assetsales_model arm = new Log_assetsales_model();
   ObservableList<AR_classfile> ars = FXCollections.observableArrayList();
    double total1 = 0;
    
   public void Loadtable_Ar(){
       CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_apr")) {
                try {
                    arm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                      ar_tbl.getItems();
                    if (DummyCount != GlobalCount) {
                            List b = arm.where(new Object[][]{
                             {"ast_status","=","Uncollected" }
                    }).get();
                            Ar(b);
                            for(int i  = 0; i<ar_tbl.getItems().size(); i++){
                             int amount = Integer.parseInt(ar_tbl.getItems().get(i).arAmount.getValue());
                          total1 = total1+ amount;
                    }
                                     ar_total_lbl.setText(String.valueOf(total1));
                     {
                    }
                    ar_tbl.setItems(ars);
                    GlobalCount = DummyCount;
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_AP_ARController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
                   
   }
  
    public void AddtableColumn_AR(){
        ar_tbl.getItems().clear();
        ar_tbl.getColumns().removeAll(ar_tbl.getColumns());
        
        TableColumn<AR_classfile, String> d = new TableColumn<>("Date");
        TableColumn<AR_classfile, String> in = new TableColumn<>("Invoice No.");
        TableColumn<AR_classfile, String> des = new TableColumn<>("Description");
        TableColumn<AR_classfile, String> am = new TableColumn<>("Amount");
        TableColumn<AR_classfile, String> st = new TableColumn<>("Status");
        TableColumn<AR_classfile, String> js = new TableColumn<>("Journal Status");
       
        d.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDate);
        in.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arInvoiceno);
        des.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDescription);
        am.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arAmount);
        st.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arStatus);
        js.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arJournalStatus);
        
        ar_tbl.getColumns().addAll(d,in,des,am,st,js);
    }
    
    public void AddtableColumn_AP(){
        
        ap_tbl.getItems().clear();
        ap_tbl.getColumns().removeAll(ap_tbl.getColumns());
        TableColumn<AP_classfile, String> apd = new TableColumn<>("Date");
        TableColumn<AP_classfile, String> apin = new TableColumn<>("Invoice No");
        TableColumn<AP_classfile, String> apde = new TableColumn<>("Department");
        TableColumn<AP_classfile, String> apa = new TableColumn<>("Amount");
        TableColumn<AP_classfile, String> aps = new TableColumn<>("Status");
        TableColumn<AP_classfile, String> apjs = new TableColumn<>("Journal Status");
       
        apd.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDate);
        apin.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apinvoice);
        apde.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDepartment);
        apa.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apAmount);
        aps.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apStatus);
        apjs.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apJournalStatus);
        
        ap_tbl.getColumns().addAll(apd,apin,apde,apa,aps,apjs);
    
    }
    
   
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<AP_classfile> brc = FXCollections.observableArrayList();
    Financial_disbursement_request_model fbr = new Financial_disbursement_request_model();
    
    double total2 = 0;
    public void totalAP(){
           for(int i  = 0; i<ap_tbl.getItems().size(); i++){
                             int amount = Integer.parseInt(ap_tbl.getItems().get(i).apAmount.getValue());
                          total2 = total2+ amount;
                    }
                                     total_label.setText(String.valueOf(total2));
    }
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
                            Ap(b);  
                            totalAP();
                             
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
     
     private int Ap(List app){
         
        ap_tbl.getItems().clear();
      

        try {
              for(Object d : app)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                hm.get("dr_daterequest");
                hm.get("dr_id");
                hm.get("dr_department");
                hm.get("dr_amount");
                hm.get("dr_status");
                
               brc.add(new AP_classfile(
                            String.valueOf(hm.get("dr_daterequest")),
                             String.valueOf(hm.get("dr_id")),
                            String.valueOf(hm.get("dr_department")),
                            String.valueOf(hm.get("dr_amount")),
                            String.valueOf(hm.get("dr_status")),
                            String.valueOf(hm.get("dr_journal_status"))
                            
                ) );                 
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ap_tbl.setItems(brc);
        return 0;
     }

    @FXML
    private void searchSales(ActionEvent event) {
        /* Log_assetsales_model arm2 = new Log_assetsales_model();
          try {
            List liststatus = arm2.where(new Object[][]{
                {"ast_status", "=", salesStatus_cmbbx.getSelectionModel().getSelectedItem().toString()}
                }).get();
                Ar(liststatus);
        } catch (Exception e) {
            System.out.println(e);
        }
        */
    }
       
    
    
}
