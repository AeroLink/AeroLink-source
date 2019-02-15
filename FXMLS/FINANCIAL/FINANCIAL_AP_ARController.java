/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.APAR_Entries_classfile;
import FXMLS.FINANCIAL.CLASSFILES.AP_classfile;
import FXMLS.FINANCIAL.CLASSFILES.AR_classfile;
import Model.Financial.Financial_disbursement_request_model;
import Model.Financial.Financial_entries;
import Model.Financial.Log_assetsales_model;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> deptCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private Label ar_total_lbl;
    @FXML
    private JFXButton pom_btn;
    @FXML
    private JFXButton asset_btn;
    @FXML
    private JFXComboBox comboboxSales;
    
    ObservableList<String> s = FXCollections.observableArrayList("Uncollected","Collected");
    @FXML
    private TableView<AR_classfile> arPosting_tbl;
    @FXML
    private TableView<AP_classfile> apPosting_tbl;
    @FXML
    private ContextMenu arContextMenu;
    @FXML
    private MenuItem ARmenuItem;
    @FXML
    private TableView<APAR_Entries_classfile> tbl_entries;
    @FXML
    private Label invoiceid;
    @FXML
    private Label date;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label description;
    @FXML
    private Label amount;
    @FXML
    private Label status;
    @FXML
    private Label typestatus;
    @FXML
    private Label journalstatus;
    @FXML
    private Label id_ast;
    @FXML
    private ContextMenu APcontexmenu;
    @FXML
    private MenuItem APmenuitem;
    @FXML
    private JFXButton view_coa_btn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AddtableColumn_AP();
        loadtable_ap();
        
        
      comboboxSales.setItems(s);
        AddtableColumn_AR();
        Loadtable_Ar();
     
        addTableARposting();
        LoadARposting();
        
        AddtableAPposting();
        LoadAPposting();
          
        AddtableEntries();
        LoadEntriesTable();
        pom_btn.setOnMouseClicked(e ->openPOM());
        asset_btn.setOnMouseClicked(e ->openAsset());
      // comboboxSales.setOnAction(e ->FilterStatus());
      ar_tbl.setContextMenu(arContextMenu);
     ARmenuItem.setOnAction(e -> PostToJournal());
    
      arPosting_tbl.setOnMouseClicked(e -> {
      AR_classfile ar = arPosting_tbl.getSelectionModel().getSelectedItem();
      invoiceid.setText(ar.getInvoiceno());
      date.setText(ar.getDate());
      firstname.setText(ar.getFirstname());
      lastname.setText(ar.getLastname());
      description.setText(ar.getDescription());
      amount.setText(ar.getAmount());
      status.setText(ar.getStatus());
      typestatus.setText(ar.getTypestatus());
              
      });
     apPosting_tbl.setContextMenu(APcontexmenu);
     APmenuitem.setOnAction(e -> apPostingtoLedger());
     
     
     apPosting_tbl.setOnMouseClicked(e -> {
     AP_classfile ap = apPosting_tbl.getSelectionModel().getSelectedItem();
     invoiceid.setText(ap.getInvoice());
     date.setText(ap.getDate());
     firstname.setText(ap.getDescription());
     lastname.setText(ap.getAmount());
     description.setText(ap.getJs());
     amount.setText(ap.getStatus());
     status.setText(ap.getName());
     
     
     
     });
    }   
    
    
    public void viewCOA(){
         Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/PurchaseOrder.fxml").getParent());
         md.open();
    }
    
    
    Financial_entries fe1 = new Financial_entries();
    public void apPostingtoLedger(){
        Financial_disbursement_request_model lam1 = new Financial_disbursement_request_model();
        
        lam1.update(new Object[][]{
                {"dr_journal_status","Posted"}
                }).where(new Object[][]{
                {"dr_id","=",invoiceid.getText()}
                }).executeUpdate();
          try
        {
           String[][] entries_tbl =
        {
        {"InvoiceEntries" ,        invoiceid.getText()},
        {"DateEntries" ,           date.getText()},
        {"FirstnameEntries" ,   status.getText()},
        {"DescriptionEntries" ,    firstname.getText() },
        {"AmountEntries" ,         lastname.getText()  },
        {"JournalStatusEntries" ,  description.getText() },
        {"StatusEntries" ,         amount.getText() },
        
        };
        if(fe1.insert(entries_tbl)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Posted");
             alert.setContentText("Posted"); 
             alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Fail");
             alert.setContentText("Failed"); 
             alert.showAndWait();
        }
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    }
    Financial_entries fe = new Financial_entries();
    ObservableList<APAR_Entries_classfile> aec = FXCollections.observableArrayList();
    public void AddtableEntries(){
         tbl_entries.getItems().clear();
         tbl_entries.getColumns().removeAll(tbl_entries.getColumns());
        
        TableColumn<APAR_Entries_classfile, String> id = new TableColumn<>("Entries No");
        TableColumn<APAR_Entries_classfile, String> date = new TableColumn<>("Date");
        TableColumn<APAR_Entries_classfile, String> name = new TableColumn<>("Name");
        TableColumn<APAR_Entries_classfile, String> description = new TableColumn<>("Descriptinon");
        TableColumn<APAR_Entries_classfile, String> amount = new TableColumn<>("Amount");
        TableColumn<APAR_Entries_classfile, String> status = new TableColumn<>("Status");
       
        id.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().IdEntries);
        date.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().DateEntries);
        name.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().FirstnameEntries);
        description.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().DescriptionEntries);
        amount.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().AmountEntries);
        status.setCellValueFactory((TableColumn.CellDataFeatures<APAR_Entries_classfile, String> param) -> param.getValue().AccountStatusEntries);
        
        tbl_entries.getColumns().addAll(id,date,name,description,amount,status);
    }
    public void LoadEntriesTable(){
         CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_apr")) {
                try {
                    fe.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                     tbl_entries.getItems();
                     if (DummyCount != GlobalCount) {
                         List b = fe.where("AccountStatus","=","Pending")
                                  .get("e_id as e_id",
                                       "DateEntries as DateEntries",
                                       "CONCAT(FirstnameEntries,', ',LastnameEntries) as FirstnameEntries",
                                       "DescriptionEntries as DescriptionEntries",
                                       "AmountEntries as AmountEntries",
                                       "AccountStatus as AccountStatus");
                         
                         Entries(b);
                         tbl_entries.setItems(aec);
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
    public void Entries(List enries){
        tbl_entries.getItems().clear();
        try {
              for(Object d : enries)
            {
                HashMap hm = (HashMap) d; 
                hm.get("e_id");
                hm.get("DateEntries");
                hm.get("InvoiceEntries");
                hm.get("FirstnameEntries");
                hm.get("LastnameEntries");
                hm.get("AmountEntries");
                aec.add(new APAR_Entries_classfile(
                            String.valueOf(hm.get("e_id")),
                            String.valueOf(hm.get("DateEntries")),
                            String.valueOf(hm.get("InvoiceEntries")),
                            String.valueOf(hm.get("FirstnameEntries")),
                            String.valueOf(hm.get("LastnameEntries")),
                            String.valueOf(hm.get("DescriptionEntries")),
                            String.valueOf(hm.get("AmountEntries")),
                            String.valueOf(hm.get("StatusEntries")),
                            String.valueOf(hm.get("TypeStatusEntries")),
                             String.valueOf(hm.get("AccountStatus")),
                            String.valueOf(hm.get("JournalStatusEntries"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        tbl_entries.setItems(aec);
    }
    public void PostToJournal(){
        Log_assetsales_model lam = new Log_assetsales_model();
        
        lam.update(new Object[][]{
                {"journal_status","Posted"}
                }).where(new Object[][]{
                {"ast_id","=",invoiceid.getText()}
                }).executeUpdate();
          try
        {
           String[][] entries_tbl =
        {
        {"InvoiceEntries" ,      invoiceid.getText()},
        {"DateEntries" ,         date.getText()},
        {"FirstnameEntries" ,    firstname.getText() },
        {"LastnameEntries" ,     lastname.getText()  },
        {"DescriptionEntries" ,  description.getText() },
        {"AmountEntries" ,       amount.getText() },
        {"StatusEntries" ,       status.getText() },
        {"TypeStatusEntries" ,   typestatus.getText() },
        {"JournalStatusEntries" ,"Posted"},
        };
        if(fe.insert(entries_tbl)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Posted");
             alert.setContentText("Posted"); 
             alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Fail");
             alert.setContentText("Failed"); 
             alert.showAndWait();
        }
            }catch(Exception e)
                
               {
            e.printStackTrace();
               }
    }
   
  /*  public void FilterStatus(){
         Log_assetsales_model arm4 = new Log_assetsales_model();
         try {
            List fss = arm4.where(new Object[][]{
                {"ast_status", "=", comboboxSales.getSelectionModel().getSelectedItem().toString()}
            }).get();
                 Ar(fss);
     
        } catch (Exception e) {
            System.out.println(e);
        }

    }*/
    
    Log_assetsales_model arm5 = new Log_assetsales_model();
    ObservableList<AR_classfile> ars5 = FXCollections.observableArrayList();
   
    public void addTableARposting(){
         arPosting_tbl.getItems().clear();
         arPosting_tbl.getColumns().removeAll(arPosting_tbl.getColumns());
        
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
        
        arPosting_tbl.getColumns().addAll(d,in,des,am,st,js);
    }
    public void LoadARposting(){
         CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_apr")) {
                try {
                    arm5.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                      arPosting_tbl.getItems();
                    if (DummyCount != GlobalCount) {
                            List b = arm5.where(new Object[][]{
                             {"journal_status","=","Not Posted" } }).get();
                            arPOSTING(b);
                    arPosting_tbl.setItems(ars5);
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
    private void arPOSTING(List post){
        arPosting_tbl.getItems().clear();
        try {
              for(Object d : post)
            {
                HashMap hm = (HashMap) d; 
                hm.get("ast_date");
                hm.get("ast_id");
                hm.get("ast_description");
                hm.get("ast_amount");
                hm.get("ast_status");
                hm.get("journal_status");
                ars5.add(new AR_classfile(
                            String.valueOf(hm.get("ast_date")),
                            String.valueOf(hm.get("ast_id")),
                            String.valueOf(hm.get("ast_firstname")),
                            String.valueOf(hm.get("ast_lastname")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_status")),
                            String.valueOf(hm.get("ast_type")),
                            String.valueOf(hm.get("journal_status"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        arPosting_tbl.setItems(ars5);
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
    
   private void Ar(List arr){
        ar_tbl.getItems().clear();
        try {
            for (Object d : arr) {
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
                            String.valueOf(hm.get("ast_firstname")),
                            String.valueOf(hm.get("ast_lastname")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_status")),
                            String.valueOf(hm.get("ast_type")),
                            String.valueOf(hm.get("journal_status"))
                ) );   
                 
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        ar_tbl.setItems(ars);
   
       
     }
    
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
                            
                       /*  for(int i  = 0; i<ar_tbl.getItems().size(); i++){
                           int amount = Integer.parseInt(ar_tbl.getItems().get(i).arAmount.getValue());
                          total1 = total1+ amount;
                    }   // yung ar_tbl tableview
                        // yung arAmount galing classfile simplestring
                        // total1 sya yung double
                                     ar_total_lbl.setText(String.valueOf(total1));*/
                         
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
       
        d.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDate);
        in.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arInvoiceno);
        des.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arDescription);
        am.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arAmount);
        st.setCellValueFactory((TableColumn.CellDataFeatures<AR_classfile, String> param) -> param.getValue().arStatus);
        
        ar_tbl.getColumns().addAll(d,in,des,am,st);
    }
    
    
   
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    
    ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<AP_classfile> brc = FXCollections.observableArrayList();
    Financial_disbursement_request_model fbr = new Financial_disbursement_request_model();
    double total2 = 0;

    public void totalAP() {
        for (int i = 0; i < ap_tbl.getItems().size(); i++) {
            int amount = Integer.parseInt(ap_tbl.getItems().get(i).apAmount.getValue());
            total2 = total2 + amount;
        }
        total_label.setText(String.valueOf(total2));
    }
    public void AddtableColumn_AP(){
        
        ap_tbl.getItems().clear();
        ap_tbl.getColumns().removeAll(ap_tbl.getColumns());
        TableColumn<AP_classfile, String> apd = new TableColumn<>("Date");
        TableColumn<AP_classfile, String> apin = new TableColumn<>("Invoice No");
        TableColumn<AP_classfile, String> apdes = new TableColumn<>("Description");
        TableColumn<AP_classfile, String> apa = new TableColumn<>("Amount");
        TableColumn<AP_classfile, String> aps = new TableColumn<>("Status");
       
        apd.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDate);
        apin.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apinvoice);
        apdes.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDescription);
        apa.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apAmount);
        aps.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apStatus);
        
        ap_tbl.getColumns().addAll(apd,apin,apdes,apa,aps);
    
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
                            List b = fbr.where("dr_status","=","Unpaid").get();
                            Ap(b);  
                            totalAP();
                             
                     

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
            for (Object d : app) {
                HashMap hm = (HashMap) d;   //exquisite casting
                hm.get("dr_daterequest");
                hm.get("dr_id");
                hm.get("dr_description");
                hm.get("dr_amount");
                hm.get("dr_status");
                
               brc.add(new AP_classfile(
                            String.valueOf(hm.get("dr_daterequest")),
                             String.valueOf(hm.get("dr_id")),
                            String.valueOf(hm.get("dr_description")),
                            String.valueOf(hm.get("dr_amount")),
                            String.valueOf(hm.get("dr_status")),
                            String.valueOf(hm.get("dr_journal_status")),
                            String.valueOf(hm.get("dr_requestor"))
                            
                ) );                 
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ap_tbl.setItems(brc);
        return 0;
    }

    ObservableList<AP_classfile> brc5 = FXCollections.observableArrayList();
    Financial_disbursement_request_model fbr5 = new Financial_disbursement_request_model();
      
    public void AddtableAPposting(){
        apPosting_tbl.getItems().clear();
        apPosting_tbl.getColumns().removeAll(apPosting_tbl.getColumns());
        TableColumn<AP_classfile, String> apd = new TableColumn<>("Date");
        TableColumn<AP_classfile, String> apin = new TableColumn<>("Invoice No");
        TableColumn<AP_classfile, String> apdes = new TableColumn<>("Description");
        TableColumn<AP_classfile, String> apa = new TableColumn<>("Amount");
        TableColumn<AP_classfile, String> aps = new TableColumn<>("Status");
        TableColumn<AP_classfile, String> apjs = new TableColumn<>("Journal Status");
       
        apd.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDate);
        apin.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apinvoice);
        apdes.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apDescription);
        apa.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apAmount);
        aps.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apStatus);
        apjs.setCellValueFactory((TableColumn.CellDataFeatures<AP_classfile, String> param) -> param.getValue().apJournalStatus);
        
        apPosting_tbl.getColumns().addAll(apd,apin,apdes,apa,aps,apjs);
    }
    public void LoadAPposting(){
         CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_apr")) {
                try {
                    fbr5.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount != GlobalCount) {
                           apPosting_tbl.getItems();
                            List b = fbr5.where("dr_journal_status","=","Not Posted").get();
                                    apPosting(b);  
                         apPosting_tbl.setItems(brc5);
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
    private int apPosting(List apPost){
          apPosting_tbl.getItems().clear();
        try {
              for(Object d : apPost)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
               brc5.add(new AP_classfile(
                            String.valueOf(hm.get("dr_daterequest")),
                            String.valueOf(hm.get("dr_id")),
                            String.valueOf(hm.get("dr_description")),
                            String.valueOf(hm.get("dr_amount")),
                            String.valueOf(hm.get("dr_status")),
                            String.valueOf(hm.get("dr_journal_status")),
                            String.valueOf(hm.get("dr_requestor"))
                ) );                 
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        apPosting_tbl.setItems(brc5);
        return 0;
    }
}
