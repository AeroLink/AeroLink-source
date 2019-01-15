/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_CM_CLASSFILE;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import Model.Financial.Financial_cm_model;
import Model.Financial.Financial_core1_type;
import Synapse.Session;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import Synapse.Model;
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_COLLECTIONController implements Initializable {

    @FXML
    private JFXTextField searchcm;
    @FXML
    private JFXComboBox col_combobox;
    @FXML
    private TableView<FINANCIAL_CM_CLASSFILE> cm_tbl;

    
    
    
  private ContextMenu contextMenuJobs;
  int Global_Count = 0;
  ExecutorService e = Executors.newFixedThreadPool(1);   
ObservableList<FINANCIAL_CM_CLASSFILE> collection = FXCollections.observableArrayList();
Financial_cm_model fcm = new Financial_cm_model();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     combobox();
        col_combobox.getSelectionModel().selectedItemProperty().addListener(listener -> {
            DummyCount = 0;
            GlobalCount = 0;
        });
        
        
         collection.addListener((ListChangeListener.Change<? extends Object> c) -> {
          cm_tbl.setItems(collection);
        });
     
         searchcm.setOnKeyReleased(e -> SearchCol());
         
        this.generateTable();
        this.populateTables();
        cm_tbl.setContextMenu(contextMenuJobs);
    }    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    
     public void combobox() {
        Financial_core1_type cc = new Financial_core1_type();
        try {
            List bo2 = cc.get();
            for (Object bo : bo2) {
                HashMap ka = (HashMap) bo;
                col_combobox.getItems().add(ka.get("title"));
            }

            col_combobox.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     
     
public void generateTable() {

        cm_tbl.getItems().clear();
        cm_tbl.getColumns().removeAll(cm_tbl.getColumns());
        TableColumn<FINANCIAL_CM_CLASSFILE, String> inv_no1 = new TableColumn<>("Invoice No");
        TableColumn<FINANCIAL_CM_CLASSFILE, String> coltype = new TableColumn<>("Type");
        TableColumn<FINANCIAL_CM_CLASSFILE, String> colname = new TableColumn<>("Name");
        TableColumn<FINANCIAL_CM_CLASSFILE, String> coldesc = new TableColumn<>("Description");
        TableColumn<FINANCIAL_CM_CLASSFILE, String> coldate = new TableColumn<>("Date & Amount");
       
    
        inv_no1.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_CM_CLASSFILE, String> param) -> param.getValue().inv);
        colname.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_CM_CLASSFILE, String> param) -> param.getValue().name);
        coldesc.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_CM_CLASSFILE, String> param) -> param.getValue().desc);
        coldate.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_CM_CLASSFILE, String> param) -> param.getValue().date);
        coltype.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_CM_CLASSFILE, String> param) -> param.getValue().type);
      

        
        cm_tbl.getColumns().addAll(inv_no1,colname,coldesc,coldate,coltype);
       
    } 

//a code for search  
    public void SearchCol() {
      
         cm_tbl.getItems().clear();
         List rs = fcm
                .join(Model.JOIN.LEFT, "aerolink.tbl_type", "id", "tblD", "=", "col_type1")
                .join(Model.JOIN.LEFT, "aerolink.tbl_ast","id","tbl","=","asset_id")
                .where(new Object[][]{
                {"tbl.invoice_no1","like", "%" + searchcm.getText() + "%"}})
                 .get("tbl.invoice_no1 as invoices1",
                "CONCAT(tblD.id,'   -  ',tblD.title) as col_type1",
                "tbl.name as col_name",
                "CONCAT(tbl.date , '            -           ', tbl.amount) as col_date",
                "tbl.description as col_description");
        this.AddJobToTable(rs);
        
        
    }
    public void populateTables() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_collection")) {
                try {
                    fcm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        cm_tbl.getItems().removeAll(collection);
                            List rs = fcm
                                    .join(Model.JOIN.INNER,"aerolink.tbl_ast","id","tbl","=","asset_id")
                                    .join(Model.JOIN.INNER,"aerolink.tbl_type","id","tbl1","=","col_type1")
                                     .where(new Object[][]{{"tbl1.title", "=", col_combobox.getSelectionModel().getSelectedItem().toString()}})
                                    .get( "tbl.invoice_no1 as invoices1",
                                          "CONCAT(tbl1.id,'   -  ',tbl1.title) as col_type1",
                                          "tbl.name as col_name",
                                          "CONCAT(tbl.date , '            -           ', tbl.amount) as col_date",
                                          "tbl.description as col_description"
                                          );
                        AddJobToTable(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }
 
 public void AddJobToTable(List rs) {
        cm_tbl.refresh();
        collection.clear();
        
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            
            
            String inv1 = String.valueOf(crow.get("invoices1"));
            String cn = (String) crow.get("col_name");
            String cd = (String) crow.get("col_description");
            String cdt = (String) crow.get("col_date");
            String ct = (String) crow.get("col_type1");
            String cm = (String) crow.get("col_amount");
            
            
            collection.add(new FINANCIAL_CM_CLASSFILE(inv1,cn,cd,cdt,ct,cm));
        }
         cm_tbl.setItems(collection);
    }
 
}
