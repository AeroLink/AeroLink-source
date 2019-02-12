/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.ClassFiles.HR3_tpLeaves;
import FXMLS.HR3.ClassFiles.Leave_ManagementClass;
import FXMLS.HR3.ClassFiles.Leave_ManagementRequestClass;

import Model.HR3.HR3_Leaves;
import Model.HR3.HR3_Leaves_new;
import Model.HR3.HR3_leave_record;
import Model.HR3.HR_RequestLeaves;
import Synapse.Model;
import Synapse.Model.JOIN;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class HR3_Leave_ManagementController implements Initializable {

    @FXML
    private TableView<Leave_ManagementRequestClass> tablerequest;
    @FXML
    private TableView<Leave_ManagementClass> tableleave;
    @FXML
    private TableView<HR3_tpLeaves> tbl_tpLeaves;
    @FXML
    private JFXTextField txtLeaveDesc;
    @FXML
    private JFXTextField txtNumDays;
    @FXML
    private JFXTextField txtLimit;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;

    @FXML
    private Button btn_r_cancel;
    @FXML
    private TextField txt_r_id;
    @FXML
    private TextField txt_r_name;
    @FXML
    private TextField txt_r_range;
    @FXML
    private TextField txt_r_date;
    @FXML
    private TextField txt_r_start;
    @FXML
    private TextField txt_r_end;
    @FXML
    private TextField txt_r_reason;
    @FXML
    private TextField txt_r_attachment;
    @FXML
    private Button btn_r_submit;
    @FXML
    private ComboBox<String> cmb_r_status;

    /**
     * Initializes the controller class.
     */
    int Global_Count = 0;
    @FXML
    private Button rsubmit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> status = FXCollections.observableArrayList("Approve", "Decline");
        cmb_r_status.setItems(status);

        //Type of Leaves Generate
        this.generatetable();
        this.loadData();
        this.generatetableLeaveRequest();
        this.generatetableLeaveRecord();
        this.populateTableLeave_REquest();
        this.populateTableLeave_record();

        btnAdd.setOnAction(e -> addLeave());
        btnSave.setOnAction(e -> saveLeave());
        btnEdit.setOnAction(e -> EditLeave());
        btnUpdate.setOnAction(e -> UpdateLeave());
        //sa leave request button
        btn_r_submit.setOnAction(e -> saveLeave_request());
        btn_r_cancel.setOnAction(e -> Data_form_request_Leave());

        tbl_tpLeaves.setOnMouseClicked((MouseEvent event) -> {

            txtLeaveDesc.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LeaveName.getValue());
            txtLimit.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LeaveLimit.getValue());
            txtNumDays.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LimitDays.getValue());

            btnEdit.setDisable(false);
        });
        
        tablerequest.setOnMouseClicked((MouseEvent event) -> {

            txt_r_id.setText(tablerequest.getSelectionModel().getSelectedItem().employee_code.getValue());
            txt_r_name.setText(tablerequest.getSelectionModel().getSelectedItem().leave_name.getValue());
            txt_r_date.setText(tablerequest.getSelectionModel().getSelectedItem().range_leave.getValue());
            txt_r_start.setText(tablerequest.getSelectionModel().getSelectedItem().date.getValue());
            txt_r_end.setText(tablerequest.getSelectionModel().getSelectedItem().date_start.getValue());
            txt_r_range.setText(tablerequest.getSelectionModel().getSelectedItem().date_end.getValue());
            txt_r_attachment.setText(tablerequest.getSelectionModel().getSelectedItem().attachment.getValue());
            txt_r_reason.setText(tablerequest.getSelectionModel().getSelectedItem().reason.getValue());

            data_form_unvisible();
        });

    }

    //Global Vars
    HR_RequestLeaves leaverequest = new HR_RequestLeaves();
    HR3_Leaves leaves = new HR3_Leaves("typeofleaves");

    HR3_leave_record qweqwe = new HR3_leave_record();
    long DummyCount = 0;
    long GlobalCount = 0;
    ObservableList<HR3_tpLeaves> ob = FXCollections.observableArrayList();
    ObservableList<Leave_ManagementRequestClass> obj = FXCollections.observableArrayList();
    ObservableList<Leave_ManagementRequestClass> objsss = FXCollections.observableArrayList();
    ObservableList<Leave_ManagementClass> record = FXCollections.observableArrayList();

    //inner join
    HR3_Leaves_new lmr = new HR3_Leaves_new();

    //Type of Leaves Generate
    public void generatetable() {
        tbl_tpLeaves.getColumns().removeAll(tbl_tpLeaves.getColumns());

        TableColumn<HR3_tpLeaves, String> leave_name = new TableColumn<>("Description");
        TableColumn<HR3_tpLeaves, String> leave_limit_days = new TableColumn<>("Limit Days");
        TableColumn<HR3_tpLeaves, String> leave_limit = new TableColumn<>("Leave Limit");

        leave_name.setCellValueFactory(value -> value.getValue().LeaveName);
        leave_limit_days.setCellValueFactory(value -> value.getValue().LimitDays);
        leave_limit.setCellValueFactory(value -> value.getValue().LeaveLimit);

        tbl_tpLeaves.getColumns().addAll(leave_name, leave_limit_days, leave_limit);
    }

    //Type of Leaves Load Data
    public void loadData() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr3lm")) {
                leaves.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    tbl_tpLeaves.getItems().clear();
                    List<HashMap> hash = leaves.get();
                    hash.stream().forEach(e -> {
                        ob.add(new HR3_tpLeaves(
                                String.valueOf(e.get("leave_id")),
                                String.valueOf(e.get("leave_name")),
                                String.valueOf(e.get("limit_days")),
                                String.valueOf(e.get("leave_limit"))
                        ));
                    });
                    tbl_tpLeaves.setItems(ob);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR3_Leave_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    public void addLeave() {
        txtLeaveDesc.setText("");
        txtLimit.setText("");
        txtNumDays.setText("");
        txtLeaveDesc.setDisable(false);
        txtLimit.setDisable(false);
        txtNumDays.setDisable(false);
        btnSave.setDisable(false);
    }

    public void cleaning() {
        txtLeaveDesc.setText("");
        txtLimit.setText("");
        txtNumDays.setText("");
        txtLeaveDesc.setDisable(true);
        txtLimit.setDisable(true);
        txtNumDays.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnEdit.setDisable(true);

    }

    public void saveLeave() {
        leaves.insert(new Object[][]{
            {"leave_name", txtLeaveDesc.getText()},
            {"limit_days", txtNumDays.getText()},
            {"leave_limit", txtLimit.getText()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New leave was successfully added");
        cleaning();
    }

    public void typeOfLeavesWorkFlows() {

    }

    public void EditLeave() {
        txtLeaveDesc.setDisable(false);
        txtLimit.setDisable(false);
        txtNumDays.setDisable(false);

        btnUpdate.setDisable(false);
    }

    public void UpdateLeave() {

        leaves.where(new Object[][]{
            {"leave_id", "=", tbl_tpLeaves.getSelectionModel().getSelectedItem().LeaveID.getValue()}
        }).update(new Object[][]{
            {"leave_name", txtLeaveDesc.getText()},
            {"limit_days", txtNumDays.getText()},
            {"leave_limit", txtLimit.getText()}
        }).executeUpdate();

        Helpers.EIS_Response.SuccessResponse("Success!", "New leave was successfully added");
        cleaning();
    }

    private void Data(List courses) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void generatetableLeaveRequest() {
        tablerequest.getColumns().removeAll(tablerequest.getColumns());

        TableColumn<Leave_ManagementRequestClass, String> a = new TableColumn<>("Employee ID");
        TableColumn<Leave_ManagementRequestClass, String> b = new TableColumn<>("Employee Name");
        TableColumn<Leave_ManagementRequestClass, String> c = new TableColumn<>("Position");
        TableColumn<Leave_ManagementRequestClass, String> d = new TableColumn<>("Department");
        TableColumn<Leave_ManagementRequestClass, String> e = new TableColumn<>("Leave Name");
        TableColumn<Leave_ManagementRequestClass, String> f = new TableColumn<>("Range of Leave");
        TableColumn<Leave_ManagementRequestClass, String> g = new TableColumn<>("Date");
        TableColumn<Leave_ManagementRequestClass, String> h = new TableColumn<>("Date Start");
        TableColumn<Leave_ManagementRequestClass, String> i = new TableColumn<>("Date End");
        TableColumn<Leave_ManagementRequestClass, String> j = new TableColumn<>("Reason");
        TableColumn<Leave_ManagementRequestClass, String> k = new TableColumn<>("Attachment");

        a.setCellValueFactory(value -> value.getValue().employee_code);
        b.setCellValueFactory(value -> value.getValue().employee_name);
        c.setCellValueFactory(value -> value.getValue().position);
        d.setCellValueFactory(value -> value.getValue().department);
        e.setCellValueFactory(value -> value.getValue().leave_name);
        f.setCellValueFactory(value -> value.getValue().range_leave);
        g.setCellValueFactory(value -> value.getValue().date);
        h.setCellValueFactory(value -> value.getValue().date_start);
        i.setCellValueFactory(value -> value.getValue().date_end);
        j.setCellValueFactory(value -> value.getValue().reason);
        k.setCellValueFactory(value -> value.getValue().attachment);

        tablerequest.getColumns().addAll(a, b, c, d, e, f, g, h, i, j, k);
    }

    public void generatetableLeaveRecord() {
        tableleave.getColumns().removeAll(tableleave.getColumns());

        TableColumn<Leave_ManagementClass, String> a = new TableColumn<>("Employee ID");
        TableColumn<Leave_ManagementClass, String> b = new TableColumn<>("Employee Name");
        TableColumn<Leave_ManagementClass, String> c = new TableColumn<>("Position");
        TableColumn<Leave_ManagementClass, String> d = new TableColumn<>("Department");
        TableColumn<Leave_ManagementClass, String> e = new TableColumn<>("Leave Name");
        TableColumn<Leave_ManagementClass, String> f = new TableColumn<>("Range of Leave");
        TableColumn<Leave_ManagementClass, String> g = new TableColumn<>("Date");
        TableColumn<Leave_ManagementClass, String> h = new TableColumn<>("Date Start");
        TableColumn<Leave_ManagementClass, String> i = new TableColumn<>("Date End");
        TableColumn<Leave_ManagementClass, String> j = new TableColumn<>("Reason");
        TableColumn<Leave_ManagementClass, String> k = new TableColumn<>("Attachment");
        TableColumn<Leave_ManagementClass, String> l = new TableColumn<>("status");

        a.setCellValueFactory(value -> value.getValue().emp_code);
        b.setCellValueFactory(value -> value.getValue().emp_name);
        c.setCellValueFactory(value -> value.getValue().emp_position);
        d.setCellValueFactory(value -> value.getValue().emp_department);
        e.setCellValueFactory(value -> value.getValue().emp_leave_name);
        f.setCellValueFactory(value -> value.getValue().emp_range_leave);
        g.setCellValueFactory(value -> value.getValue().emp_date);
        h.setCellValueFactory(value -> value.getValue().emp_date_start);
        i.setCellValueFactory(value -> value.getValue().emp_date_end);
        j.setCellValueFactory(value -> value.getValue().emp_reason);
        k.setCellValueFactory(value -> value.getValue().emp_attachment);
        l.setCellValueFactory(value -> value.getValue().emp_status);

        tableleave.getColumns().addAll(a, b , c, d, e, f, g, h, i, j, k, l);
    }

    
    public void populateTableLeave_REquest() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = lmr.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = lmr
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr3_leave_request_new", "employee_code", "ez", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "e", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "e", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "fv", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    .get(
                                            "ez.employee_code",
                                            "CONCAT(fv.lastname,', ',fv.firstname,' ',fv.middlename,'.') as FullName",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "ez.leave_name, ez.range_leave, ez.date, ez.date_start, ez.date_end, ez.reason, ez.attachment ",
                                            "aerolink.tbl_hr4_department.dept_name as dept_id");

                            Data_ng_Leave_request(rs);

                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {

                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }

    public void Data_ng_Leave_request(List cmgmt) {
        ObservableList<Leave_ManagementRequestClass> snd = FXCollections.observableArrayList();
        snd.clear();
        try {

            for (Object d : cmgmt) {
                HashMap hm1 = (HashMap) d;

                snd.add(
                        new Leave_ManagementRequestClass(
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("FullName")),
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("dept_id")),
                                String.valueOf(hm1.get("leave_name")),
                                String.valueOf(hm1.get("range_leave")),
                                String.valueOf(hm1.get("date")),
                                String.valueOf(hm1.get("date_start")),
                                String.valueOf(hm1.get("date_end")),
                                String.valueOf(hm1.get("reason")),
                                String.valueOf(hm1.get("attachment"))
                        ));
            }

            tablerequest.setItems(snd);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tablerequest.getItems().size());
        tablerequest.getSelectionModel().selectFirst();
    }

    public void populateTableLeave_record() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = qweqwe.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rss = new ArrayList<>();
                        if (count != Global_Count) {
                            rss = qweqwe
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr3_leave_request_new", "employee_code", "ez", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr3_leave_record", "employee_code", "ez1", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "e", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "e", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "fv", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    .get(
                                            "ez1.employee_code",
                                            "CONCAT(fv.lastname,', ',fv.firstname,' ',fv.middlename,'.') as FullName",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "ez.leave_name, ez.range_leave, ez.date, ez.date_start, ez.date_end, ez.reason, ez.attachment ",
                                            "ez1.status",
                                            "aerolink.tbl_hr4_department.dept_name as dept_id");

                            Data_ng_Leave_record(rss);

                            Global_Count = count;
                        }
                        return rss;
                    }).thenAccept((rss) -> {
                        if (!rss.isEmpty()) {

                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }
    
    public void Data_ng_Leave_record(List lml) {
        ObservableList<Leave_ManagementClass> send = FXCollections.observableArrayList();
        send.clear();
        try {

            for (Object d : lml) {
                HashMap hm1 = (HashMap) d;

                send.add(
                        new Leave_ManagementClass(
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("FullName")),
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("dept_id")),
                                String.valueOf(hm1.get("leave_name")),
                                String.valueOf(hm1.get("range_leave")),
                                String.valueOf(hm1.get("date")),
                                String.valueOf(hm1.get("date_start")),
                                String.valueOf(hm1.get("date_end")),
                                String.valueOf(hm1.get("reason")),
                                String.valueOf(hm1.get("attachment")),
                                String.valueOf(hm1.get("status"))
                        ));
            }

            tableleave.setItems(send);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tableleave.getItems().size());
        tableleave.getSelectionModel().selectFirst();
    }
    
    public void saveLeave_request() {
        qweqwe.insert(new Object[][]{
            {"employee_code", txt_r_id.getText()},
            {"leave_name", txt_r_name.getText()},
            {"status", cmb_r_status.getValue()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New leave was successfully added");
        Data_form_request_Leave();
        data_form_visible();
    }
    
    public void Data_form_request_Leave(){
        txt_r_attachment.setText("");
        txt_r_date.setText("");
        txt_r_end.setText("");
        txt_r_id.setText("");
        txt_r_name.setText("");
        txt_r_range.setText("");
        txt_r_start.setText("");
        cmb_r_status.setValue("");
        data_form_visible();
    }
    
    public void data_form_visible(){
        btn_r_cancel.setDisable(true);
        btn_r_submit.setDisable(true);
        cmb_r_status.setDisable(true);
    }
    
    public void data_form_unvisible(){
        btn_r_cancel.setDisable(false);
        btn_r_submit.setDisable(false);
        cmb_r_status.setDisable(false);
    }
    
}
