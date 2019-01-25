/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Disbursement_Voucher_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Disbursement_request_classfile;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import Model.Financial.Financial_disbursement_voucher;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_DISBURSEMENTController implements Initializable {

    @FXML
    private TableView<Disbursement_request_classfile> disbursement_tbl;
    @FXML
    private Label daterequest_label;
    @FXML
    private Label department_label;
    @FXML
    private Label claimant_label;
    @FXML
    private TextArea description_txtarea;
    @FXML
    private Label amount_label;
    @FXML
    private JFXButton dv_btn;
    @FXML
    private Label reqno_label;
    @FXML
    private Label prioritylvl_label;
    @FXML
    private Label status_label;
    @FXML
    private Label dr_id_label;
    @FXML
    private TableView<Disbursement_Voucher_classfile> dv_tbl;
    @FXML
    private JFXTextField srch_name_txt;
    @FXML
    private DatePicker srch_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // srch_date.setOnAction(e ->srchDate());
        //srch_name_txt.setOnKeyTyped(e -> srch());
        dv_btn.setOnMouseClicked(e -> disburseBtn());
        
        loadtable_disbursementrequest();
        AddtableColumn_disbursementrequest();
        
        AddtableColumn_disbursementVoucher();
        loadDisbursementVoucher();
        
        
        disbursement_tbl.setOnMouseClicked(e ->{
        Disbursement_request_classfile drc = disbursement_tbl.getSelectionModel().getSelectedItem();
        dr_id_label.setText(drc.getDr_id());
        reqno_label.setText(drc.getRequestno());
        daterequest_label.setText(drc.getDatereq());
        department_label.setText(drc.getDepartment());
        claimant_label.setText(drc.getReqname());
        description_txtarea.setText(drc.getDescription());
        amount_label.setText(drc.getAmount());
        prioritylvl_label.setText(drc.getPriority_level());
        status_label.setText(drc.getDisbursementStatus());
        
        dv_btn.setDisable(false);
        
        
            
        });
        
    }    
    /*
     public void srchDate(){
        Financial_disbursement_voucher fvm1 = new Financial_disbursement_voucher(); 

    //String SearchText = searchno.getText().equals("") ? "[a-z]" : searchno.getText();
        
        try {

            List listrec = fvm1.where(new Object[][]{
                {"dv_date_released","like", "%" + srch_date.getValue() + "%"}
            }).get();

            //VoucherRecord(listrec);
     
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/
    /*
    
    public void srch(){
           dv_tbl.getItems().clear();
        Financial_disbursement_voucher fvm = new Financial_disbursement_voucher(); 

    String SearchText = srch_name_txt.getText().equals("") ? "[a-z]" : srch_name_txt.getText();
        
        try {

            List listrec = fvm.where(new Object[][]{
                {"dv_claimant","like", "%" + SearchText + "%"}
            }).get();

            VoucherRecord(listrec);
     
        } catch (Exception e) {
            System.out.println(e);
        }
          dv_tbl.setItems(dvc);
    }
    */
    public void AddtableColumn_disbursementVoucher(){
        dv_tbl.getItems().clear();
        dv_tbl.getColumns().removeAll(dv_tbl.getColumns());
        
        TableColumn<Disbursement_Voucher_classfile, String> dreq = new TableColumn<>("Date Request");
        TableColumn<Disbursement_Voucher_classfile, String> de = new TableColumn<>("Department");
        TableColumn<Disbursement_Voucher_classfile, String> des = new TableColumn<>("Description");
        TableColumn<Disbursement_Voucher_classfile, String> c = new TableColumn<>("Claimant");
        TableColumn<Disbursement_Voucher_classfile, String> a = new TableColumn<>("Amount");
        TableColumn<Disbursement_Voucher_classfile, String> rb = new TableColumn<>("Received By.");
        TableColumn<Disbursement_Voucher_classfile, String> drel = new TableColumn<>("Date Released");
       
        dreq.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvdreq);
        de.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvdep);
        des.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvdes);
        c.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvclaimant);
        a.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvamount);
        rb.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvreceivedby);
        drel.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_Voucher_classfile, String> param) -> param.getValue().dvdatereleased);
        
        dv_tbl.getColumns().addAll(dreq,de,des,c,a,rb,drel);
    
    }
    
    ObservableList<Disbursement_Voucher_classfile> dvc = FXCollections.observableArrayList();
    Financial_disbursement_voucher fdv = new Financial_disbursement_voucher();
    public void loadDisbursementVoucher(){
         CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_disbursement")) {
                
                try {
                    fdv.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        dv_tbl.getItems();
                            List b = fdv.get("budget_date_req as budget_date_req",
                                    "dv_department as dv_department",
                                    "dv_description as dv_description",
                                    "dv_claimant as dv_claimant",
                                    "dv_amount as dv_amount",
                                    "dv_received_by as dv_received_by",
                                    "CONCAT(dv_disbursed_status, ' - ', dv_date_released) as dv_date_released");
                            VoucherRecord(b);
                     {
                         
                    }
                    dv_tbl.setItems(dvc);
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
    
    private void VoucherRecord(List voucher){
         dv_tbl.getItems().clear();

        try {
             
              for(Object d : voucher)
            {
                HashMap hm = (HashMap) d; 
                //exquisite casting
                hm.get("budget_date_req");
                hm.get("dv_department");
                hm.get("dv_description");
                hm.get("dv_claimant");
                hm.get("dv_amount");
                hm.get("dv_received_by");
                hm.get("dv_date_released");
                
               dvc.add(new Disbursement_Voucher_classfile(
                            String.valueOf(hm.get("budget_date_req")),
                            String.valueOf(hm.get("dv_department")),
                            String.valueOf(hm.get("dv_description")),
                            String.valueOf(hm.get("dv_claimant")),
                            String.valueOf(hm.get("dv_amount")),
                            String.valueOf(hm.get("dv_received_by")),
                            String.valueOf(hm.get("dv_date_released"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        dv_tbl.setItems(dvc);

     
    }
    
    public void disburseBtn(){
        
           Financial_disbursement_request_model fbr = new Financial_disbursement_request_model();
           Financial_disbursement_voucher fddr = new Financial_disbursement_voucher();
            
           try
           {
                if(fbr.update(new Object[][]{
                {"dr_status","Disbursed"}})
                .where(new Object[][]{
                {"dr_id","=",dr_id_label.getText()}
                }).executeUpdate())
                {
                    
           String[][] dr_table =
        {
        {"budget_req_no" ,     reqno_label.getText()},
        {"budget_date_req" ,   daterequest_label.getText()},
        {"dv_department" ,     department_label.getText()},
        {"dv_claimant" ,       claimant_label.getText()},
        {"dv_requestor" ,      claimant_label.getText()},
        {"dv_description" ,    description_txtarea.getText()},
        {"dv_priority_level" , prioritylvl_label.getText()},
        {"dv_amount" ,         amount_label.getText()},
        {"dv_budget_status" ,  "Approved"},
        };           
           
                fddr.insert(dr_table);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Disbursed");
             alert.setContentText("Request Has Been Disbursed"); 
             alert.showAndWait();
               dv_btn.setDisable(true);
               daterequest_label.setText("");
               department_label.setText("");
               claimant_label.setText("");
               description_txtarea.setText("");
               amount_label.setText("");
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
    
    
     public void AddtableColumn_disbursementrequest(){
        
        disbursement_tbl.getItems().clear();
        disbursement_tbl.getColumns().removeAll(disbursement_tbl.getColumns());
        
        
        TableColumn<Disbursement_request_classfile, String> drreqno = new TableColumn<>("Request No");
        TableColumn<Disbursement_request_classfile, String> drdatereq = new TableColumn<>("Date Request");
        TableColumn<Disbursement_request_classfile, String> drrequestor = new TableColumn<>("Requestor");
        TableColumn<Disbursement_request_classfile, String> drdep = new TableColumn<>("Department");
        TableColumn<Disbursement_request_classfile, String> drdes = new TableColumn<>("Description");
        TableColumn<Disbursement_request_classfile, String> dramnt = new TableColumn<>("Amount");
        TableColumn<Disbursement_request_classfile, String> drstat = new TableColumn<>("Status");
       
        
        drreqno.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drReqno);
        drdatereq.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drDatereq);
        drrequestor.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drReqname);
        drdep.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drDepartment);
        drdes.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drDescription);
        dramnt.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drAmount);
        drstat.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_request_classfile, String> param) -> param.getValue().drdisbursementStatus);
        
        disbursement_tbl.getColumns().addAll(drreqno,drdatereq,drrequestor,drdep,drdes,dramnt,drstat);
    
    }
    
    
     long DummyCount = 0;
     long GlobalCount = 0;
     int Global_Count = 0;
     ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Disbursement_request_classfile> brc = FXCollections.observableArrayList();
    Financial_disbursement_request_model drm = new Financial_disbursement_request_model();
    
    
    public void loadtable_disbursementrequest(){
        
          CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_disbursement")) {
                
                try {
                    drm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        disbursement_tbl.getItems();
                            List b = drm.where(new Object[][]{
                             {"dr_status","=","Pending"}
                    }).get();
                            request(b);
                            
                     {
                         
                    }
                    disbursement_tbl.setItems(brc);
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
     
     private void request(List req){
         
        disbursement_tbl.getItems().clear();
        ObservableList<Disbursement_request_classfile> fdrc = FXCollections.observableArrayList();

        try {
             
              for(Object d : req)
            {
                HashMap hm = (HashMap) d; 
                //exquisite casting
                 hm.get("dr_id");
                hm.get("dr_requestno");
                hm.get("dr_daterequest");
                hm.get("dr_department");
                hm.get("dr_requestor");
                hm.get("dr_description");
                hm.get("dr_prioritylvl");
                hm.get("dr_amount");
                hm.get("dr_status");
                
               brc.add(new Disbursement_request_classfile(
                            String.valueOf(hm.get("dr_id")),
                            String.valueOf(hm.get("dr_requestno")),
                            String.valueOf(hm.get("dr_daterequest")),
                            String.valueOf(hm.get("dr_requestor")),
                            String.valueOf(hm.get("dr_department")),
                            String.valueOf(hm.get("dr_description")),
                            String.valueOf(hm.get("dr_prioritylvl")),
                            String.valueOf(hm.get("dr_amount")),
                            String.valueOf(hm.get("dr_status"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        disbursement_tbl.setItems(fdrc);

     }
}
