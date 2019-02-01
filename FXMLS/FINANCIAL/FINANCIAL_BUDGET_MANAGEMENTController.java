/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Annual_budget_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Budget_Allocation_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Budget_Request_classfile;
import FXMLS.FINANCIAL.CLASSFILES.Pendingbudget_classfile;
import FXMLS.FINANCIAL.STATIC.CLASFILES.Finance_budget_decline;
import Model.Financial.Financial_allocation_model;
import Model.Financial.Financial_annualbudget_model;
import Model.Financial.Financial_budget_request;
import Model.Financial.Financial_disbursement_request_model;
import Model.Financial.Financial_entries;
import Model.Financial.users_tbl;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
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
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_BUDGET_MANAGEMENTController implements Initializable {

    @FXML
    private JFXButton brForm_btn;
    @FXML
    private JFXButton allocate_btn;
    @FXML
    private TableView<Budget_Allocation_classfile> allocation_tbl;
    @FXML
    private TableView<Budget_Request_classfile> budgetrequest_tbl;
    @FXML
    private JFXButton approved_btn;
    @FXML
    private Label label_id;
    @FXML
    private Label status_lbl;
    @FXML
    private JFXTextField searchno;

    @FXML
    private Label drno_label;
    @FXML
    private Label datereq_label;
    @FXML
    private Label dept_label;
    @FXML
    private Label requestor_label;
    @FXML
    private Label desc_label;
    @FXML
    private Label prioritylvl_label;
    @FXML
    private Label amount_label;
    @FXML
    private Label status_label;
    @FXML
    private JFXButton budget_history_btn;
    @FXML
    private AnchorPane ancrpane3;
    @FXML
    private JFXButton enable_btn;
    @FXML
    private TableView<Annual_budget_classfile> ab_tbl;
    @FXML
    private JFXButton reqHistory_btn;
    @FXML
    private JFXButton declined_btn;

    private final ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
    users_tbl user = new users_tbl();
    @FXML
    private PieChart Piechart1;
    @FXML
    private PieChart disbursebudget_piechart;
    
  private final ObservableList<PieChart.Data> pie2 = FXCollections.observableArrayList();
    @FXML
    private Label pendingreq_label;
    @FXML
    private TableView<Pendingbudget_classfile> pendingno_tbl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        declined_btn.setOnMouseClicked(e -> declineRequest());

        brForm_btn.setOnMouseClicked(e -> openbrForm());
        allocate_btn.setOnMouseClicked(e -> openAllocation());
        approved_btn.setOnMouseClicked(e -> updateRequest());
        //searchno.setOnKeyTyped(e ->searchno());
        budget_history_btn.setOnMouseClicked(e -> openBudgetHistory());
        AddTableColumn_Budget_Allocation();
        loadAllocation();

  

        AddTableColumn_Budget_Request();
        LoadBudgetRequest();

        loadAB();
        AddTableColumn_AnnualBudgets();

        ancrpane3.setOnMouseClicked(e -> {
         approved_btn.setDisable(true);
        });
        enable_btn.setOnMouseClicked(e -> enable());
        budgetrequest_tbl.setOnMouseClicked(e -> {
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
            declined_btn.setDisable(false);
        });

        reqHistory_btn.setOnMouseClicked(e -> OpenReqHistory());
        this.Piechart();
        this.Piechart2();
        
        addpendingtable();
        loadpending();
        
           int a = pendingno_tbl.getItems().size();
           pendingreq_label.setText(String.valueOf(a));
    }
 
    

    
      ObservableList<Pendingbudget_classfile> op = FXCollections.observableArrayList();
    Financial_budget_request p = new Financial_budget_request();
    public void addpendingtable(){
         pendingno_tbl.getItems().clear();
        pendingno_tbl.getColumns().removeAll(pendingno_tbl.getColumns());
        
        TableColumn<Pendingbudget_classfile, String> id = new TableColumn<>("astAmount");
        
        id.setCellValueFactory((TableColumn.CellDataFeatures<Pendingbudget_classfile,
                String> param) -> param.getValue().id);
      
        pendingno_tbl.getColumns().addAll(id);
        
    }
    public void loadpending(){
        try{
          List rs = p.where("budget_status", "=", "Pending").get();
                            pend(rs);
    }catch(Exception e){
        System.err.println(e);
    }
    }
   private int pend(List pendings){
         pendingno_tbl.getItems().clear();
        try {
              for(Object d : pendings)
            {
              HashMap hm = (HashMap) d;   //exquisite casting
             op.add(new Pendingbudget_classfile(
                            String.valueOf(hm.get("budget_status"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        pendingno_tbl.setItems(op);
        return 0;
    }
    
    
    
     Financial_disbursement_request_model disbursement= new Financial_disbursement_request_model();
     public void Piechart2() {

        pie2.clear();
        List<HashMap> list3x = disbursement
                .groupBy("dr_status")
                .get("dr_status,SUM(dr_amount) as DTotal");
        list3x.forEach((row) -> {
            pie2.add(new PieChart.Data(row.get("dr_status").toString(),
                    Double.parseDouble(row.get("DTotal").toString())));
        });
        disbursebudget_piechart.setData(pie2);
        disbursebudget_piechart.setLegendSide(Side.RIGHT);
        disbursebudget_piechart.setStartAngle(90);

    }
     Financial_annualbudget_model annual = new Financial_annualbudget_model();
     Financial_allocation_model totalAllocate = new Financial_allocation_model();
   public void Piechart() {

        pie.clear();
        List<HashMap> list3x = totalAllocate
                .groupBy("ba_department")
                .get("ba_department,SUM(ba_amount) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("ba_department").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        Piechart1.setData(pie);
        Piechart1.setLegendSide(Side.RIGHT);
        Piechart1.setStartAngle(90);

    }

    public void OpenReqHistory() {
        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUEST_HISTORY.fxml").getParent());
        md.open();
    }

    public void enable() {
        allocate_btn.setDisable(false);
        enable_btn.setDisable(true);
    }

    //budget request
    public void openbrForm() {
        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml").getParent());
        md.open();
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

        budgetrequest_tbl.getColumns().addAll(reqno, brdate, brrn, brdep, brdesc, brpl, bramount, brstats);

    }

    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);
    ObservableList<Budget_Request_classfile> brc = FXCollections.observableArrayList();
    Financial_budget_request fbr = new Financial_budget_request();

    public void LoadBudgetRequest() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_budget")) {

                try {
                    fbr.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    budgetrequest_tbl.getItems();
                    if (DummyCount != GlobalCount) {
                        List b = fbr.where(new Object[][]{
                            {"budget_status", "=", "Pending"}
                        }).get();
                        request(b);

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
        System.err.println(budgetrequest_tbl.getItems().size());
    }

    private void request(List req) {

        budgetrequest_tbl.getItems().clear();
        try {

            for (Object d : req) {
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
                ));
            }
            
        budgetrequest_tbl.setItems(brc);
        } catch (Exception e) {
            System.out.println(e);
        }

   
    }

    public void declineRequest() {
        Finance_budget_decline.reqno = budgetrequest_tbl.getSelectionModel().getSelectedItem().brrequestno.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_DECLINE.fxml").getParent());
        md.open();
        approved_btn.setDisable(true);
        declined_btn.setDisable(true);
    }

    public void updateRequest() {
        Financial_disbursement_request_model drm = new Financial_disbursement_request_model();
        Financial_budget_request fbrr = new Financial_budget_request();

        try {

            if (fbrr.update(new Object[][]{
                {"budget_status", "Approved"},
                {"budget_approvedby", "Admin"}

            }).where(new Object[][]{
                {"budget_id", "=", label_id.getText()}
            }).executeUpdate()) {
                String[][] insertodisbursement
                        = {
                            {"dr_requestno", drno_label.getText()},
                            {"dr_daterequest", datereq_label.getText()},
                            {"dr_department", dept_label.getText()},
                            {"dr_requestor", requestor_label.getText()},
                            {"dr_description", desc_label.getText()},
                            {"dr_prioritylvl", prioritylvl_label.getText()},
                            {"dr_amount", amount_label.getText()},
                            {"dr_budget_status", "Approved"}
                        };
                drm.insert(insertodisbursement);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Approved");
                alert.setContentText("Request Approved, and has been send to Disbursement");
                alert.showAndWait();

                approved_btn.setDisable(true);
                declined_btn.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Error");
                alert.setContentText("FAIL");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openBudgetHistory() {
        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_HISTORY.fxml").getParent());
        md.open();
    }

    //Allocation 
    public void openAllocation() {
        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_NEWALLOCATION.fxml").getParent());
        md.open();
        allocate_btn.setDisable(true);
        enable_btn.setDisable(false);
    }

    public void AddTableColumn_Budget_Allocation() {
        allocation_tbl.getItems().clear();
        allocation_tbl.getColumns().removeAll(allocation_tbl.getColumns());
        TableColumn<Budget_Allocation_classfile, String> badep = new TableColumn<>("Department");
        TableColumn<Budget_Allocation_classfile, String> baa = new TableColumn<>("Estimated Amount");

        badep.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Allocation_classfile, String> param) -> param.getValue().badepartment);
        baa.setCellValueFactory((TableColumn.CellDataFeatures<Budget_Allocation_classfile, String> param) -> param.getValue().baamount);

        allocation_tbl.getColumns().addAll(badep, baa);

    }
    Financial_allocation_model fam = new Financial_allocation_model();
    ObservableList<Budget_Allocation_classfile> fac = FXCollections.observableArrayList();

    public void loadAllocation() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_budget")) {
                try {
                    fam.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount != GlobalCount) {

                        allocation_tbl.getItems();
                        List b = fam.get("ba_department as ba_department",
                                            "ba_amount as ba_amount");
                        alloc(b);
                      
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

    private void alloc(List allc) {

        allocation_tbl.getItems().clear();

        try {

            for (Object d : allc) {
                HashMap hm = (HashMap) d;   //exquisite casting

                hm.get("ba_date");
                hm.get("ba_department");
                hm.get("ba_amount");

                fac.add(new Budget_Allocation_classfile(
                        String.valueOf(hm.get("ba_date")),
                        String.valueOf(hm.get("ba_department")),
                        String.valueOf(hm.get("ba_amount"))
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        allocation_tbl.setItems(fac);

    }

    public void AddTableColumn_AnnualBudgets() {
        ab_tbl.getItems().clear();
        ab_tbl.getColumns().removeAll(ab_tbl.getColumns());
        TableColumn<Annual_budget_classfile, String> y = new TableColumn<>("Year");
        TableColumn<Annual_budget_classfile, String> b = new TableColumn<>("Budget");

        y.setCellValueFactory((TableColumn.CellDataFeatures<Annual_budget_classfile, String> param) -> param.getValue().abYear);
        b.setCellValueFactory((TableColumn.CellDataFeatures<Annual_budget_classfile, String> param) -> param.getValue().abBudget);

        ab_tbl.getColumns().addAll(y, b);

    }

    Financial_annualbudget_model abm = new Financial_annualbudget_model();
    ObservableList<Annual_budget_classfile> abc = FXCollections.observableArrayList();

    private void Annual(List ab) {

        ab_tbl.getItems().clear();

        try {
            for (Object d : ab) {
                HashMap hm = (HashMap) d;   //exquisite casting

                hm.get("ab_year");
                hm.get("ab_amount");

                abc.add(new Annual_budget_classfile(
                        String.valueOf(hm.get("ab_year")),
                        String.valueOf(hm.get("ab_amount"))
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ab_tbl.setItems(abc);

    }

    public void loadAB() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_budget")) {
                try {
                    abm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount != GlobalCount) {

                        ab_tbl.getItems();
                        List b = abm.get("ab_year as ab_year",
                                "ab_amount as ab_amount");
                        Annual(b);

                        ab_tbl.setItems(abc);
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
