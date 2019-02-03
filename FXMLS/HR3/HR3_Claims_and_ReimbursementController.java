/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.ClassFiles.ClaimClass;
import FXMLS.HR3.ClassFiles.Reimbursement_RequestClass;
import Model.HR3.HR3_Claim_Request;
import Model.HR3.HR3_Reimbursement_Request;
import Model.HR3.HR3_Sending_claim;
import Model.HR3.HR3_sending_budget;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Claims_and_ReimbursementController implements Initializable {

    @FXML
    private TableView<Reimbursement_RequestClass> table_reimbursement;
    @FXML
    private JFXTextField txt_or;
    @FXML
    private JFXTextField txt_date;
    @FXML
    private JFXTextField txt_department;
    @FXML
    private JFXTextField txt_recieved;
    @FXML
    private JFXTextField txt_expenses;
    @FXML
    private JFXTextField txt_particulars;
    @FXML
    private JFXTextField txt_cash;
    @FXML
    private JFXTextField txt_total;
    @FXML
    private JFXButton btn_request;
    @FXML
    private JFXButton btn_pending;
    @FXML
    private JFXButton btn_cancel;

    /**
     * Initializes the controller class.
     */
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private TableView<ClaimClass> table_claim;
    @FXML
    private JFXTextField txt_code;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXTextField txt_amount;
    @FXML
    private JFXTextField txt_deduction;
    @FXML
    private JFXTextField txt_earnings;
    @FXML
    private JFXTextField txt_salary;
    @FXML
    private JFXTextField txt_net;
    @FXML
    private Button btn_request_c;
    @FXML
    private Button btn_pending_c;
    @FXML
    private JFXTextField txt_department_c;
    @FXML
    private JFXTextField txt_date_c;
    @FXML
    private TableView<?> table_history_C;
    @FXML
    private JFXTextField searchhistory;
    @FXML
    private TableView<?> table_history_R;
    @FXML
    private JFXTextField searchhistory1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generatetablereimbursement();
        this.loadDatanewLeave();
        this.clean();
        this.generatetableclaim();
        this.loadDatanewClaimR();
        this.cleaners();

        btn_request.setOnAction(e -> send_request());
        btn_cancel.setOnAction(e -> Erase());
        btn_request_c.setOnAction(e -> send_requestclaim());
        

        table_reimbursement.setOnMouseClicked((MouseEvent event) -> {

            txt_or.setText(table_reimbursement.getSelectionModel().getSelectedItem().or_no.getValue());
            txt_date.setText(table_reimbursement.getSelectionModel().getSelectedItem().date.getValue());
            txt_department.setText(table_reimbursement.getSelectionModel().getSelectedItem().department.getValue());
            txt_recieved.setText(table_reimbursement.getSelectionModel().getSelectedItem().recieved.getValue());
            txt_expenses.setText(table_reimbursement.getSelectionModel().getSelectedItem().expenses.getValue());
            txt_particulars.setText(table_reimbursement.getSelectionModel().getSelectedItem().particulars.getValue());
            txt_cash.setText(table_reimbursement.getSelectionModel().getSelectedItem().cash_returned.getValue());
            txt_total.setText(table_reimbursement.getSelectionModel().getSelectedItem().total_amount.getValue());

            btn_request.setDisable(false);
            btn_pending.setDisable(false);
            btn_cancel.setDisable(false);
        });
        
        table_claim.setOnMouseClicked((MouseEvent event) -> {

            txt_code.setText(table_claim.getSelectionModel().getSelectedItem().employee_code.getValue());
            txt_department_c.setText(table_claim.getSelectionModel().getSelectedItem().department.getValue());
            txt_date_c.setText(table_claim.getSelectionModel().getSelectedItem().date.getValue());
            txt_description.setText(table_claim.getSelectionModel().getSelectedItem().description.getValue());
            txt_amount.setText(table_claim.getSelectionModel().getSelectedItem().amount.getValue());
            txt_deduction.setText(table_claim.getSelectionModel().getSelectedItem().deduction.getValue());
            txt_earnings.setText(table_claim.getSelectionModel().getSelectedItem().earnings.getValue());
            txt_salary.setText(table_claim.getSelectionModel().getSelectedItem().salary.getValue());
            txt_net.setText(table_claim.getSelectionModel().getSelectedItem().net_amount.getValue());

            
            btn_request_c.setDisable(false);
            btn_pending_c.setDisable(false);
           
        });

    }

    HR3_Claim_Request haha = new HR3_Claim_Request();
    HR3_Reimbursement_Request asd = new HR3_Reimbursement_Request();
    HR3_sending_budget qwe = new HR3_sending_budget("dummy");
    HR3_Sending_claim qweasd = new HR3_Sending_claim("dummy");
    ObservableList<Reimbursement_RequestClass> ob = FXCollections.observableArrayList();
    ObservableList<ClaimClass> obc = FXCollections.observableArrayList();

    public void generatetablereimbursement() {
        table_reimbursement.getColumns().removeAll(table_reimbursement.getColumns());

        TableColumn<Reimbursement_RequestClass, String> a = new TableColumn<>("OR_No.");
        TableColumn<Reimbursement_RequestClass, String> b = new TableColumn<>("Date");
        TableColumn<Reimbursement_RequestClass, String> c = new TableColumn<>("Department");
        TableColumn<Reimbursement_RequestClass, String> d = new TableColumn<>("Recieved_Php");
        TableColumn<Reimbursement_RequestClass, String> e = new TableColumn<>("Expenses");
        TableColumn<Reimbursement_RequestClass, String> f = new TableColumn<>("Particulars");
        TableColumn<Reimbursement_RequestClass, String> g = new TableColumn<>("Cash Returned");
        TableColumn<Reimbursement_RequestClass, String> h = new TableColumn<>("Total Amount");

        a.setCellValueFactory(value -> value.getValue().or_no);
        b.setCellValueFactory(value -> value.getValue().date);
        c.setCellValueFactory(value -> value.getValue().department);
        d.setCellValueFactory(value -> value.getValue().recieved);
        e.setCellValueFactory(value -> value.getValue().expenses);
        f.setCellValueFactory(value -> value.getValue().particulars);
        g.setCellValueFactory(value -> value.getValue().cash_returned);
        h.setCellValueFactory(value -> value.getValue().total_amount);

        table_reimbursement.getColumns().addAll(a, b, c, d, e, f, g, h);
    }
// reimbursement request to!!!!

    public void loadDatanewLeave() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr3cnr")) {
                asd.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    table_reimbursement.getItems().clear();
                    List<HashMap> hash = asd.get();
                    hash.stream().forEach(e -> {
                        ob.add(new Reimbursement_RequestClass(
                                String.valueOf(e.get("or_no")),
                                String.valueOf(e.get("date")),
                                String.valueOf(e.get("department")),
                                String.valueOf(e.get("recieved")),
                                String.valueOf(e.get("expenses")),
                                String.valueOf(e.get("particulars")),
                                String.valueOf(e.get("cash_returned")),
                                String.valueOf(e.get("total_amount"))
                        ));
                    });
                    table_reimbursement.setItems(ob);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(HR3_Leave_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    // save nd data ni reim. papunta kay  budget
    public void send_request() {
        qwe.insert(new Object[][]{
            {"or_no", txt_or.getText()},
            {"department", txt_department.getText()},
            {"recieved", txt_recieved.getText()},
            {"expenses", txt_expenses.getText()},
            {"particulars", txt_particulars.getText()},
            {"cash_returned", txt_cash.getText()},
            {"total_amount", txt_total.getText()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "Request Budget was successfully Sent");
        clean();
        Erase();
    }

    //cancel button sa reim
    public void Erase() {
        txt_or.setText("");
        txt_date.setText("");
        txt_department.setText("");
        txt_recieved.setText("");
        txt_expenses.setText("");
        txt_particulars.setText("");
        txt_cash.setText("");
        txt_total.setText("");

        clean();
    }

    public void clean() {
        btn_request.setDisable(true);
        btn_pending.setDisable(true);
        btn_cancel.setDisable(true);
    }

    public void generatetableclaim() {
        table_claim.getColumns().removeAll(table_claim.getColumns());

        TableColumn<ClaimClass, String> a = new TableColumn<>("Employee_code.");
        TableColumn<ClaimClass, String> b = new TableColumn<>("Department");
        TableColumn<ClaimClass, String> c = new TableColumn<>("Date");
        TableColumn<ClaimClass, String> d = new TableColumn<>("Description");
        TableColumn<ClaimClass, String> e = new TableColumn<>("Amount");
        TableColumn<ClaimClass, String> f = new TableColumn<>("Deduction");
        TableColumn<ClaimClass, String> g = new TableColumn<>("Earnings");
        TableColumn<ClaimClass, String> h = new TableColumn<>("Salary");
        TableColumn<ClaimClass, String> i = new TableColumn<>("Net_amount");

        a.setCellValueFactory(value -> value.getValue().employee_code);
        b.setCellValueFactory(value -> value.getValue().department);
        c.setCellValueFactory(value -> value.getValue().date);
        d.setCellValueFactory(value -> value.getValue().description);
        e.setCellValueFactory(value -> value.getValue().amount);
        f.setCellValueFactory(value -> value.getValue().deduction);
        g.setCellValueFactory(value -> value.getValue().earnings);
        h.setCellValueFactory(value -> value.getValue().salary);
        i.setCellValueFactory(value -> value.getValue().net_amount);

        table_claim.getColumns().addAll(a, b, c, d, e, f, g, h, i);
    }

    public void loadDatanewClaimR() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr3cnr")) {
                haha.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    table_claim.getItems().clear();
                    List<HashMap> hash = haha.get();
                    hash.stream().forEach(e -> {
                        obc.add(new ClaimClass(
                                String.valueOf(e.get("employee_code")),
                                String.valueOf(e.get("department")),
                                String.valueOf(e.get("date")),
                                String.valueOf(e.get("description")),
                                String.valueOf(e.get("amount")),
                                String.valueOf(e.get("deduction")),
                                String.valueOf(e.get("earnings")),
                                String.valueOf(e.get("salary")),
                                String.valueOf(e.get("net_amount"))
                        ));
                    });
                    table_claim.setItems(obc);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(HR3_Leave_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    public void cleaners() {
        btn_pending_c.setDisable(true);
        btn_request_c.setDisable(true);
    }
    //papunta kay payroll
     public void send_requestclaim() {
        qweasd.insert(new Object[][]{
            {"employee_code", txt_code.getText()},
            {"department", txt_department_c.getText()},
            {"date", txt_date_c.getText()},
            {"description", txt_description.getText()},
            {"amount", txt_amount.getText()},
            {"deduction", txt_deduction.getText()},
            {"earnings", txt_earnings.getText()},
            {"salary", txt_salary.getText()},
            {"net_amount", txt_net.getText()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "Request Claim was successfully Sent");
        cleaners();
        EraseClaim();
    }
     
     public void EraseClaim(){
         txt_code.setText("");
         txt_department_c.setText("");
         txt_date_c.setText("");
         txt_description.setText("");
         txt_amount.setText("");
         txt_deduction.setText("");
         txt_earnings.setText("");
         txt_salary.setText("");
         txt_net.setText("");
         
     }
     /*
      public void generatetablerehistory() {
        table_history_C.getColumns().removeAll(table_history_C.getColumns());

        TableColumn<Reimbursement_RequestClass, String> a = new TableColumn<>("OR_No.");
        TableColumn<Reimbursement_RequestClass, String> b = new TableColumn<>("Date");
        TableColumn<Reimbursement_RequestClass, String> c = new TableColumn<>("Department");
        TableColumn<Reimbursement_RequestClass, String> d = new TableColumn<>("Recieved_Php");
        TableColumn<Reimbursement_RequestClass, String> e = new TableColumn<>("Expenses");
        TableColumn<Reimbursement_RequestClass, String> f = new TableColumn<>("Particulars");
        TableColumn<Reimbursement_RequestClass, String> g = new TableColumn<>("Cash Returned");
        TableColumn<Reimbursement_RequestClass, String> h = new TableColumn<>("Total Amount");

        a.setCellValueFactory(value -> value.getValue().or_no);
        b.setCellValueFactory(value -> value.getValue().date);
        c.setCellValueFactory(value -> value.getValue().department);
        d.setCellValueFactory(value -> value.getValue().recieved);
        e.setCellValueFactory(value -> value.getValue().expenses);
        f.setCellValueFactory(value -> value.getValue().particulars);
        g.setCellValueFactory(value -> value.getValue().cash_returned);
        h.setCellValueFactory(value -> value.getValue().total_amount);

        table_history_C.getColumns().addAll(a, b, c, d, e, f, g, h);
    }*/

}
