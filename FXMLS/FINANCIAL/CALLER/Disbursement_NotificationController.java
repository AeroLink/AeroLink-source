/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.CLASSFILES.Disbursement_notif_classfile;
import FXMLS.FINANCIAL.FINANCIAL_AP_ARController;
import Model.Financial.Financial_disbursement_voucher;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class Disbursement_NotificationController implements Initializable {

    @FXML
    private TableView<Disbursement_notif_classfile> received_tbl;
    @FXML
    private JFXButton okay_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        addTableCol();
        loadtble();
        
       // okay_btn.setOnAction(e ->okaybtn());
    }    
    
  /*  
    public void okaybtn(){
          Financial_disbursement_voucher fdv1 = new Financial_disbursement_voucher();
         try{
             if(fdv1.update(new Object[][]{
                 {"dv_status2","Done"}
             }).where(new Object[][]{
                 {"dv_received_by","=","Received"}
             }).executeUpdate());
                 }catch(Exception e){
                     System.err.println();
                 }

        Stage stage =(Stage) received_tbl.getScene().getWindow();
             stage.close();
    }
    */
    
    
    
    
    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);
     ObservableList<Disbursement_notif_classfile> brc = FXCollections.observableArrayList();
    Financial_disbursement_voucher fdv1 = new Financial_disbursement_voucher();
    public void addTableCol(){
        received_tbl.getItems().clear();
        received_tbl.getColumns().removeAll(received_tbl.getColumns());
        TableColumn<Disbursement_notif_classfile, String> reqno = new TableColumn<>("Disburse No");
        TableColumn<Disbursement_notif_classfile, String> brdate = new TableColumn<>("Status");
        
        
        reqno.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_notif_classfile, String> param) -> param.getValue().dvno);
        brdate.setCellValueFactory((TableColumn.CellDataFeatures<Disbursement_notif_classfile, String> param) -> param.getValue().status);
        
        received_tbl.getColumns().addAll(reqno,brdate);
    }
    
    public void loadtble(){
         CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_disbursement")) {
                try {
                    fdv1.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                     received_tbl.getItems();
                     if (DummyCount != GlobalCount) {
                         List b = fdv1.where("dv_received_by","=","Received").andWhere("dv_status2", "=", "None").get();
                         
                         dstatus(b);
                         received_tbl.setItems(brc);
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
    
    
    private void dstatus(List st){
        
          received_tbl.getItems().clear();
        try {

            for (Object d : st) {
                HashMap hm = (HashMap) d;   //exquisite casting
                brc.add(new Disbursement_notif_classfile(
                        String.valueOf(hm.get("dv_id")),
                        String.valueOf(hm.get("dv_received_by"))
                ));
            }
            
        received_tbl.setItems(brc);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
}
