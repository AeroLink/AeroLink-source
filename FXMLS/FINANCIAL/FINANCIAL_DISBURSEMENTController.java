/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_DR_CLASSFILE;
import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_DVR_CLASSFILE;
import Model.Financial.Financial_disbursement_request_model;

import Model.Financial.Financial_voucher_model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_DISBURSEMENTController implements Initializable {

    @FXML private TableView<FINANCIAL_DR_CLASSFILE> dr_tbl;
    @FXML private DatePicker drequest;
    @FXML private JFXTextField dept_txt;
    @FXML private JFXTextField amnt_txt;
    @FXML private Label stat_lbl;
    
    @FXML private JFXComboBox<String> vt_cmbobx;
    @FXML private JFXTextField clmnt_txt;
    @FXML private JFXTextField org_txt;
    @FXML private DatePicker drelease;
        @FXML private JFXButton save_voucher_btn;

    ObservableList<String> voucher = FXCollections.observableArrayList("New", "Regular");
    @FXML
    private TableView<FINANCIAL_DVR_CLASSFILE> dvr_tbl;
    @FXML
    private JFXTextField SearchDVR;
    @FXML
    private JFXComboBox<String> cmbobxDvr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbobxDvr.setItems(voucher);
        vt_cmbobx.setItems(voucher);
        save_voucher_btn.setOnMouseClicked(e -> saveBtn());
        SearchDVR.setOnKeyTyped(e -> searchdvr());
        
        
        showDR();
        loadDR();
        
        showDVR();
        loadDVR();
        dr_tbl.setOnMouseClicked(e -> {
        FINANCIAL_DR_CLASSFILE cc = dr_tbl.getSelectionModel().getSelectedItem();
       
 
        drequest.getEditor().setText(cc.getDisburse_date_request());
        dept_txt.setText(cc.getDisburse_department());
        amnt_txt.setText(cc.getDisburse_amount());
        stat_lbl.setText(cc.getDisburse_description());
        
        
              });
    }    
      
   private void searchRequestor(List requestor) {

        dvr_tbl.getItems().clear();

        ObservableList<FINANCIAL_DVR_CLASSFILE> fdrc = FXCollections.observableArrayList();

        try {
             
            for(Object d : requestor)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("drv_date_req");
                hm.get("drv_requestor");
                hm.get("dr_amount");
                hm.get("dr_department");
                hm.get("drv_vt");
                hm.get("drv_claimant");
                hm.get("drv_organization");
                hm.get("drv_date_rel");
                
               fdrc.add(new FINANCIAL_DVR_CLASSFILE(
                String.valueOf(hm.get("drv_id")),
                String.valueOf(hm.get("drv_date_req")),
                String.valueOf(hm.get("drv_requestor")),
                String.valueOf(hm.get("drv_amount")),
                String.valueOf(hm.get("drv_department")),
                String.valueOf(hm.get("drv_vt")),
                String.valueOf(hm.get("drv_claimant")),
                String.valueOf(hm.get("drv_organization")),
                String.valueOf(hm.get("drv_date_rel"))
                ) );   

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        dvr_tbl.setItems(fdrc);

    }
 
    public void searchdvr(){
        
        
       Financial_voucher_model fvm = new Financial_voucher_model(); 

    String SearchText = SearchDVR.getText().equals("") ? "[a-z]" : SearchDVR.getText();
        
        try {

            List listreq = fvm.where(new Object[][]{
                {"drv_requestor", "Like", "%"+SearchText+"%"}
            }).get();

            searchRequestor(listreq);
     
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
     //Disbursement Voucher Record Module
     public void showDVR(){
    
        dvr_tbl.getItems().clear();
        dvr_tbl.getColumns().removeAll(dvr_tbl.getColumns());

        TableColumn<FINANCIAL_DVR_CLASSFILE, String> dreq = new TableColumn<>("Date Request");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> de = new TableColumn<>("Department");
       // TableColumn<FINANCIAL_DVR_CLASSFILE, String> re = new TableColumn<>("Requestor");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> amn = new TableColumn<>("Amount");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> vt = new TableColumn<>("Voucher Type");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> cl = new TableColumn<>("Claimant");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> or = new TableColumn<>("Organization");
        TableColumn<FINANCIAL_DVR_CLASSFILE, String> drel = new TableColumn<>("Date Release");

        
        dreq.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrDatereq);
        de.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrDepartment);
        //re.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrRequestor);
        amn.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrAmount);
        vt.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrVt);
        cl.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrClaimant);
        or.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrOrganization);
        drel.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DVR_CLASSFILE, String> param) -> param.getValue().dvrDateRelease);
        
        dvr_tbl.getColumns().addAll(dreq,de,amn,vt,cl,or,drel);
    }
    
     
      public void loadDVR(){
         Financial_voucher_model fdrvm = new Financial_voucher_model();
         ObservableList<FINANCIAL_DVR_CLASSFILE> fdrc = FXCollections.observableArrayList();
          
            List b = fdrvm.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("drv_date_req");
                hm.get("drv_requestor");
                hm.get("dr_amount");
                hm.get("dr_department");
                hm.get("drv_vt");
                hm.get("drv_claimant");
                hm.get("drv_organization");
                hm.get("drv_date_rel");
                
               fdrc.add(new FINANCIAL_DVR_CLASSFILE(
                
                String.valueOf(hm.get("drv_id")),
                String.valueOf(hm.get("drv_date_req")),
                String.valueOf(hm.get("drv_requestor")),
                String.valueOf(hm.get("drv_amount")),
                String.valueOf(hm.get("drv_department")),
                String.valueOf(hm.get("drv_vt")),
                String.valueOf(hm.get("drv_claimant")),
                String.valueOf(hm.get("drv_organization")),
                String.valueOf(hm.get("drv_date_rel"))
                ) );   
               
            }
            dvr_tbl.setItems(fdrc);
            
            }
     
     
    //Disbursement Request
    public void saveBtn(){
            Financial_voucher_model fddr = new Financial_voucher_model();
            
           try
        {
           String[][] dr_table =
        {
        {"drv_date_req" , drequest.getEditor().getText()},
        {"drv_requestor" , stat_lbl.getText()},
        {"drv_amount" , amnt_txt.getText()},
        {"drv_department" , dept_txt.getText()},
        {"drv_vt" , vt_cmbobx.getValue()},
        {"drv_claimant" , clmnt_txt.getText()},
        {"drv_organization" , org_txt.getText()},
        {"drv_date_rel" , drelease.getValue().toString()}
        };           
           
        if(fddr.insert(dr_table)){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
             vt_cmbobx.setValue(null);
             org_txt.clear();
             clmnt_txt.clear();
             drelease.setValue(null);
             stat_lbl.setText(null);
             amnt_txt.clear();
             dept_txt.clear();
             drequest.setValue(null);
             
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
        showDVR();
        loadDVR();
            // show1();     
            //dr_tbl.getItems().remove(0,1);  
    }
  
     public void showDR(){
    
        dr_tbl.getItems().clear();
        dr_tbl.getColumns().removeAll(dr_tbl.getColumns());

        TableColumn<FINANCIAL_DR_CLASSFILE, String> de = new TableColumn<>("Department");
        TableColumn<FINANCIAL_DR_CLASSFILE, String> na = new TableColumn<>("Name");
        TableColumn<FINANCIAL_DR_CLASSFILE, String> am = new TableColumn<>("Amount");
        TableColumn<FINANCIAL_DR_CLASSFILE, String> dr = new TableColumn<>("Date Request");

        
        de.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DR_CLASSFILE, String> param) -> param.getValue().disburseDepartment);
        na.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DR_CLASSFILE, String> param) -> param.getValue().disburseDescription);
        am.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DR_CLASSFILE, String> param) -> param.getValue().disburseAmount);
        dr.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_DR_CLASSFILE, String> param) -> param.getValue().disburseDateRequest);

        dr_tbl.getColumns().addAll(de,na,am,dr);
    }
    
    long DummyCount = 0;
    long GlobalCount = 0;
    Financial_disbursement_request_model fba = new Financial_disbursement_request_model();
    ObservableList<FINANCIAL_DR_CLASSFILE> fdr = FXCollections.observableArrayList();
    
    
     public void loadDR(){
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_disbursement")) {
                fba.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {

                    dr_tbl.getItems().clear();
                    List b = fba.get();

                    for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("disburse_id");
                hm.get("disburse_datesend");
                hm.get("disburse_date_req");
                hm.get("disburse_department");
                
               fdr.add(new FINANCIAL_DR_CLASSFILE(
                            String.valueOf(hm.get("disburse_id")),
                            String.valueOf(hm.get("disburse_datesend")),
                            String.valueOf(hm.get("disburse_date_req")),
                            String.valueOf(hm.get("disburse_department")),
                            String.valueOf(hm.get("disburse_requestor")),
                            String.valueOf(hm.get("diburse_description")),
                            String.valueOf(hm.get("disburse_prioritylvl")),
                            String.valueOf(hm.get("disburse_amount")),
                            String.valueOf(hm.get("disburse_status"))
                ) );   
               

                    }
                    dr_tbl.setItems(fdr);
                    GlobalCount = DummyCount;
                }
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_DISBURSEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    
     }

    @FXML
    private void cbxdvr(ActionEvent event) {
          Financial_voucher_model fvm = new Financial_voucher_model(); 

    //    String SearchText = SearchDVR.getText().equals("") ? "[a-z]" : SearchDVR.getText();
        
        try {

            List listreq = fvm.where(new Object[][]{
                {"drv_vt", "=", cmbobxDvr.getSelectionModel().getSelectedItem().toString()}
            }).get();

            searchRequestor(listreq);
     
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
