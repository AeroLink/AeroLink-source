/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Budget_Allocation_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Budget_Request_classfile;
import Model.Financial.Financial_allocation_model;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_BUDGET_MANAGEMENTController implements Initializable {

    @FXML private JFXButton brForm_btn;
    @FXML private JFXButton allocate_btn;
    @FXML private TableView<Budget_Allocation_classfile> allocation_tbl;
    @FXML private TableView<Budget_Request_classfile> budgetrequest_tbl;
    @FXML private JFXButton approved_btn;
    @FXML private Label label_id;
    @FXML private Label status_lbl;
    @FXML private JFXTextField searchno;
    
    
    @FXML private Label drno_label;
    @FXML private Label datereq_label;
    @FXML private Label dept_label;
    @FXML private Label requestor_label;
    @FXML private Label desc_label;
    @FXML private Label prioritylvl_label;
    @FXML private Label amount_label;
    @FXML private Label status_label;
    @FXML
    private JFXButton budget_history_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        brForm_btn.setOnMouseClicked(e -> openbrForm());
        allocate_btn.setOnMouseClicked(e -> openAllocation());
        approved_btn.setOnMouseClicked(e ->updateRequest());
       //searchno.setOnKeyTyped(e ->searchno());
        budget_history_btn.setOnMouseClicked(e -> openBudgetHistory());
        AddTableColumn_Budget_Allocation();
        loadAllocation();
        
        
        AddTableColumn_Budget_Request();
        LoadBudgetRequest();
        
        budgetrequest_tbl.setOnMouseClicked(e ->{
        Budget_Request_classfile r = budgetrequest_tbl.getSelectionModel().getSelectedItem();
      drno_label.setText(r.getRequestNo());
      datereq_label.setText(r.getDate());
      dept_label.setText(r.getDepartment());
      requestor_label.setText(r.getRequestor_name());
      desc_label.setText(r.getDescription());
      prioritylvl_label.setText(r.getPrioritylevel());
      amount_label.setText(r.getAmount());
      approved_btn.setDisable(false);
      label_id.setText(r.getRequestNo());
        });
        
      
    }    
    
    //budget request
     public void openbrForm(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Request Form");
        stage.setScene(new Scene(root1));
        stage.show();

        
    }
     
     /*
       public void searchno(){
       Financial_budget_request fvm = new Financial_budget_request(); 

    //String SearchText = searchno.getText().equals("") ? "[a-z]" : searchno.getText();
        
        try {

            List listreq = fvm.where(new Object[][]{
                {"budget_id","like", "%" + searchno.getText() + "%"}
            }).get();

            request(listreq);
     
        } catch (Exception e) {
            System.out.println(e);
        }

     }

     */
     
      public void AddTableColumn_Budget_Request() {

        budgetrequest_tbl.getItems().clear();
        budgetrequest_tbl.getColumns().removeAll(budgetrequest_tbl.getColumns());
        TableColumn<Budget_Request_classfile, String> reqno = new TableColumn<>("Request No");
        TableColumn<Budget_Request_classfile, String> brdate = new TableColumn<>("Date");
        TableColumn<Budget_Request_classfile, String> brrn = new TableColumn<>("Requestor Name");
        TableColumn<Budget_Request_classfile, String> brdep = new TableColumn<>("Department");
        TableColumn<Budget_Request_classfile, String> brdesc = new TableColumn<>("Description");
        TableColumn<Budget_Request_classfile, String> brpl = new TableColumn<>("Priority Level");
        TableColumn<Budget_Request_classfile, String> bramount = new TableColumn<>("Amount");
        TableColumn<Budget_Request_classfile, String> brstats = new TableColumn<>("Status");
       
        reqno.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brrequestno);
        brdate.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdate);
        brrn.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brrequestorname);
        brdep.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdepartment);
        brdesc.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brdescription);
        brpl.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brprioritylevel);
        bramount.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().bramount);
        brstats.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Request_classfile, String> param) -> param.getValue().brstatus);
        
        budgetrequest_tbl.getColumns().addAll(reqno,brdate,brrn,brdep,brdesc,brpl,bramount,brstats);
       
    } 
      
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
     ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Budget_Request_classfile> brc = FXCollections.observableArrayList();
    Financial_budget_request fbr = new Financial_budget_request();
    
     public void LoadBudgetRequest(){
             CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_budget")) {
                
                try {
                    fbr.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        budgetrequest_tbl.getItems();
                            List b = fbr.where(new Object[][]{
                             {"budget_status","=","Pending"}
                    }).get();
                            request(b);
                     {
                    }
                    budgetrequest_tbl.setItems(brc);
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
         
        budgetrequest_tbl.getItems().clear();
        ObservableList<Budget_Request_classfile> fdrc = FXCollections.observableArrayList();

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
                
               brc.add(new Budget_Request_classfile(
                            String.valueOf(hm.get("budget_id")),
                            String.valueOf(hm.get("budget_date_request")),
                            String.valueOf(hm.get("budget_department")),
                            String.valueOf(hm.get("budget_requestor")),
                            String.valueOf(hm.get("budget_description")),
                            String.valueOf(hm.get("budget_priority_lvl")),
                            String.valueOf(hm.get("budget_amount")),
                            String.valueOf(hm.get("budget_status"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        budgetrequest_tbl.setItems(fdrc);

     }
     
     public void updateRequest(){
         Financial_disbursement_request_model drm = new Financial_disbursement_request_model();
         Financial_budget_request fbrr = new Financial_budget_request();
         
         try{
            if(fbrr.update(new Object[][]{
                {"budget_status","Approved"}})
                .where(new Object[][]{
                {"budget_id","=",label_id.getText()}
                }).executeUpdate()) 
            {
            String[][] insertodisbursement =
            {
                    {"dr_requestno",drno_label.getText()},
                    {"dr_daterequest",datereq_label.getText()},
                    {"dr_department",dept_label.getText()},
                    {"dr_requestor",requestor_label.getText()},
                    {"dr_description",desc_label.getText()},
                    {"dr_prioritylvl",prioritylvl_label.getText()},
                    {"dr_amount",amount_label.getText()},
                    {"dr_budget_status","Approved"}
            };
             drm.insert(insertodisbursement);
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Approved");
             alert.setContentText("Request Approved, and has been send to Disbursement"); 
             alert.showAndWait();
         
             approved_btn.setDisable(true);
             
    }else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Error");
             alert.setContentText("FAIL"); 
             alert.showAndWait();
    }
    }catch(Exception e){
        e.printStackTrace();
    }
 
    }
     
     
    public void openBudgetHistory(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/FINANCIAL/CALLER/BUDGET_HISTORY.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }
     
    
     //Allocation 
      public void openAllocation(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/FINANCIAL/CALLER/BUDGET_NEWALLOCATION.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("ADD NEW BUDGET");
        stage.setScene(new Scene(root1));
        stage.show();

    }
     
      public void AddTableColumn_Budget_Allocation() {
        allocation_tbl.getItems().clear();
        allocation_tbl.getColumns().removeAll(allocation_tbl.getColumns());
        TableColumn<Budget_Allocation_classfile, String> badep = new TableColumn<>("Department");
        TableColumn<Budget_Allocation_classfile, String> baa = new TableColumn<>("Estimated Amount");
       
        badep.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Allocation_classfile, String> param) -> param.getValue().badepartment);
        baa.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Allocation_classfile, String> param) -> param.getValue().baamount);
        
        allocation_tbl.getColumns().addAll(badep,baa);
     
    } 
        Financial_allocation_model fam = new Financial_allocation_model();
        ObservableList<Budget_Allocation_classfile> fac = FXCollections.observableArrayList();
        
      public void loadAllocation(){
          CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_budget")) {
                try {
                    fam.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount != GlobalCount) {

                        allocation_tbl.getItems().removeAll(fac);
                            List b = fam.get();
                              for(Object d : b)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("ba_date");
                hm.get("ba_department");
                hm.get("ba_amount");
                
               fac.add(new Budget_Allocation_classfile(
                            String.valueOf(hm.get("ba_date")),
                            String.valueOf(hm.get("ba_department")),
                           String.valueOf(hm.get("ba_amount"))
                ) );   
            } 
                      allocation_tbl.setItems(fac);
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
      
      
      
}
