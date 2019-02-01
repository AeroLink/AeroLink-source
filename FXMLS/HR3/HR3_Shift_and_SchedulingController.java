/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.Core2.ClassFiles.core1_TableModel_Customer_Details;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import FXMLS.HR3.ClassFiles.Dummy_shift;
import FXMLS.HR3.ClassFiles.HR3_new_emplo_create;
import FXMLS.HR3.ClassFiles.HR3_table_schedule_record;
import FXMLS.HR3.ClassFiles.HR3_table_shift_request;
import FXMLS.HR3.Table.hr3_tbl_schedule_records;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import Model.HR3.HR3_Save_new_employee;
import Model.HR3.HR3_Schedule_Recordss;
import Model.HR3.HR3_ShiftStatus;
import Model.HR3.HR3_New_Employee;
import Model.HR3.HR3_Shifting;
import Model.HR3.HR3_dummy_reord_Schedule;
import Model.HR3.HR3_dummy_shift;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Shift_and_SchedulingController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private BorderPane hr3sns;

    long DummyCount = 0;
    long GlobalCount = 0;

    @FXML
    private TableView<HR3_table_shift_request> tbl_request_shifting;
    @FXML
    private TextField s_employee_code;
    @FXML
    private TextField s_employee_name;
    @FXML
    private TextField s_reason;
    @FXML
    private TextField s_attachment;
    @FXML
    private Button s_btn_view;
    @FXML
    private Button s_btn_submit;
    @FXML
    private ComboBox<String> s_cmb_status;
    @FXML
    private Button s_btn_cancel;
    @FXML
    private ComboBox<?> s_cmb_department;
    @FXML
    private TextField s_search;
    @FXML
    private TableView<HR3_table_schedule_record> tbl_schedule_record;
    @FXML
    private JFXTextField tx_search;
    @FXML
    private ComboBox<?> cm_department;
    @FXML
    private TableView<HR3_new_emplo_create> tbl_creating_schedule;
    @FXML
    private ComboBox<String> cmb_schedule;
    @FXML
    private JFXTextField txt_employee_code;
    @FXML
    private JFXTextField txt_employee_name;
    @FXML
    private JFXTextField txt_position;
    @FXML
    private JFXTextField txt_department;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    @FXML
    private ComboBox<?> cmb_department;

    int Global_Count = 0;

    /* FOR TABLE START */
    ObservableList<HR3_table_schedule_record> detail = FXCollections.observableArrayList();

    /* FOR TABLE END */
    public void initialize(URL location, ResourceBundle resources) {

        detail.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tbl_schedule_record.setItems(detail);
        });

        this.generatetable();
        this.generatetable_new_emplo();
        this.generatetable_schedule_record();
        this.populateTable_new_employee();
        this.populateTable();
        this.populateTable_shift();
        // this.loadDatadummyschedulerecord();

        s_btn_submit.setOnAction(e -> saveShift_request_status());
        s_btn_cancel.setOnAction(e -> cleardatashift());
        btn_save.setOnAction(e -> save_create_to_record());
        btn_cancel.setOnAction(e -> cancel());

        ObservableList<String> status = FXCollections.observableArrayList("Approved", "Declined");
        s_cmb_status.setItems(status);
        ObservableList<String> schedule = FXCollections.observableArrayList("8:00 AM - 5:00 PM", "9:00 AM - 10:00 PM", "10:00 AM - 7:00 PM");
        cmb_schedule.setItems(schedule);
// sa shift
        tbl_request_shifting.setOnMouseClicked((MouseEvent event) -> {

            s_employee_code.setText(tbl_request_shifting.getSelectionModel().getSelectedItem().employee_code.getValue());
            s_employee_name.setText(tbl_request_shifting.getSelectionModel().getSelectedItem().schedule.getValue());
            s_reason.setText(tbl_request_shifting.getSelectionModel().getSelectedItem().reason.getValue());
            s_attachment.setText(tbl_request_shifting.getSelectionModel().getSelectedItem().attachment.getValue());
            disableshow();

        });
// sa create sched
        tbl_creating_schedule.setOnMouseClicked((MouseEvent event) -> {

            txt_employee_code.setText(tbl_creating_schedule.getSelectionModel().getSelectedItem().employee_code.getValue());
            txt_employee_name.setText(tbl_creating_schedule.getSelectionModel().getSelectedItem().name.getValue());
            txt_position.setText(tbl_creating_schedule.getSelectionModel().getSelectedItem().title.getValue());
            txt_department.setText(tbl_creating_schedule.getSelectionModel().getSelectedItem().dept.getValue());
            disablefalsecreate();

        });

    }
    // load data
    HR3_dummy_shift shifts = new HR3_dummy_shift("dummyshift");
    ObservableList<HR3_table_shift_request> dummyshiftss = FXCollections.observableArrayList();

    ObservableList<HR3_new_emplo_create> dummy = FXCollections.observableArrayList();
    ObservableList<hr3_tbl_schedule_records> asd = FXCollections.observableArrayList();

    // save data
    HR3_ShiftStatus status = new HR3_ShiftStatus("shift");
    HR3_Save_new_employee yups = new HR3_Save_new_employee("yup");

    // inner join
    HR3_Schedule_Recordss srd = new HR3_Schedule_Recordss();
    HR3_New_Employee qwe = new HR3_New_Employee();
    HR3_Shifting shiftshift = new HR3_Shifting();
    

    public void generatetable() {
        tbl_request_shifting.getColumns().removeAll(tbl_request_shifting.getColumns());

        TableColumn<HR3_table_shift_request, String> a = new TableColumn<>("Employee ID");
        TableColumn<HR3_table_shift_request, String> b = new TableColumn<>("Employee Name");
        TableColumn<HR3_table_shift_request, String> c = new TableColumn<>("Position");
        TableColumn<HR3_table_shift_request, String> d = new TableColumn<>("Department");
        TableColumn<HR3_table_shift_request, String> e = new TableColumn<>("Date");
        TableColumn<HR3_table_shift_request, String> f = new TableColumn<>("Schedule");
        TableColumn<HR3_table_shift_request, String> g = new TableColumn<>("Reason");
        TableColumn<HR3_table_shift_request, String> h = new TableColumn<>("Attachment");

        a.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().employee_code);
        b.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().employee_name);
        c.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().position);
        d.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().department);
        e.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().date);
        f.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().schedule);
        g.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().reason);
        h.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_shift_request, String> param) -> param.getValue().attachment);

        tbl_request_shifting.getColumns().addAll(a, b, c, d, e, f, g, h);
    }
// para sa schedule record

    public void generatetable_schedule_record() {
        tbl_schedule_record.getItems().clear();
        tbl_schedule_record.getColumns().removeAll(tbl_schedule_record.getColumns());

        TableColumn<HR3_table_schedule_record, String> a = new TableColumn<>("Employee ID");
        TableColumn<HR3_table_schedule_record, String> b = new TableColumn<>("Employee Name");
        TableColumn<HR3_table_schedule_record, String> c = new TableColumn<>("Department");
        TableColumn<HR3_table_schedule_record, String> c1 = new TableColumn<>("Position");
        TableColumn<HR3_table_schedule_record, String> d = new TableColumn<>("Monday");
        TableColumn<HR3_table_schedule_record, String> e = new TableColumn<>("Tuesday");
        TableColumn<HR3_table_schedule_record, String> f = new TableColumn<>("Wednesday");
        TableColumn<HR3_table_schedule_record, String> g = new TableColumn<>("Thursday");
        TableColumn<HR3_table_schedule_record, String> h = new TableColumn<>("Friday");

        a.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().employee_code);
        b.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().name);
        c.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().department);
        c1.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().position);
        d.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().monday);
        e.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().tuesday);
        f.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().wednesday);
        g.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().thursday);
        h.setCellValueFactory((TableColumn.CellDataFeatures<HR3_table_schedule_record, String> param) -> param.getValue().friday);

        tbl_schedule_record.getColumns().addAll(a, b, c, c1, d, e, f, g, h);
    }
//para sa new employee 

    public void generatetable_new_emplo() {
        // tbl_creating_schedule.getItems().clear();
        tbl_creating_schedule.getColumns().removeAll(tbl_creating_schedule.getColumns());

        TableColumn<HR3_new_emplo_create, String> a = new TableColumn<>("Employee ID");
        TableColumn<HR3_new_emplo_create, String> b = new TableColumn<>("Employee Name");
        TableColumn<HR3_new_emplo_create, String> c = new TableColumn<>("Position");
        TableColumn<HR3_new_emplo_create, String> d = new TableColumn<>("Department");

        a.setCellValueFactory((TableColumn.CellDataFeatures<HR3_new_emplo_create, String> param) -> param.getValue().employee_code);
        b.setCellValueFactory((TableColumn.CellDataFeatures<HR3_new_emplo_create, String> param) -> param.getValue().name);
        c.setCellValueFactory((TableColumn.CellDataFeatures<HR3_new_emplo_create, String> param) -> param.getValue().title);
        d.setCellValueFactory((TableColumn.CellDataFeatures<HR3_new_emplo_create, String> param) -> param.getValue().dept);

        tbl_creating_schedule.getColumns().addAll(a, b, c ,d);
    }

    @FXML
    private void TableClick(MouseEvent event) {
    }

    @FXML
    private void viewRow(MouseEvent event) {
    }
//sa shift

    public void clearshift() {
        s_employee_code.setDisable(true);
        s_employee_name.setDisable(true);
        s_reason.setDisable(true);
        s_attachment.setDisable(true);
        s_btn_view.setDisable(true);
        s_btn_submit.setDisable(true);
        s_btn_cancel.setDisable(true);
        s_cmb_status.setDisable(true);
    }
// sa shift

    public void disableshow() {
        s_employee_code.setDisable(false);
        s_employee_name.setDisable(false);
        s_reason.setDisable(false);
        s_attachment.setDisable(false);
        s_btn_view.setDisable(false);
        s_btn_submit.setDisable(false);
        s_btn_cancel.setDisable(false);
        s_cmb_status.setDisable(false);
    }

    public void saveShift_request_status() {
        status.insert(new Object[][]{
            {"request_id", s_employee_code.getText()},
            {"schedule", s_employee_name.getText()},
            {"reason", s_reason.getText()},
            {"attachment", s_attachment.getText()},
            {"status", s_cmb_status.getValue()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New schedule request was successfully submitted");
        tbl_request_shifting.getColumns().removeAll(tbl_request_shifting.getColumns());
        clearshift();
    }

    public void cleardatashift() {
        s_employee_code.setText("");
        s_employee_name.setText("");
        s_reason.setText("");
        s_attachment.setText("");
        clearshift();
    }

    public void populateTable_new_employee() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = qwe.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = qwe
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")

                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "e", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "e", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "fv", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    
                                    
                                    .get(
                                            "e.employee_code",
                                            "CONCAT(fv.lastname,', ',fv.firstname,' ',fv.middlename,'.') as FullName",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "aerolink.tbl_hr4_department.dept_name as dept_id");
                                            
                            loadDatadummylangcreate(rs);

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

    // loading data ng new employee
    public void loadDatadummylangcreate(List afg) {
        ObservableList<HR3_new_emplo_create> sns = FXCollections.observableArrayList();
        sns.clear();
        try {

            for (Object d : afg) {
                HashMap hm1 = (HashMap) d;

                sns.add(
                        new HR3_new_emplo_create(
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("FullName")),
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("dept_id"))
                        ));
            }

            tbl_creating_schedule.setItems(sns);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_creating_schedule.getItems().size());
        tbl_creating_schedule.getSelectionModel().selectFirst();
    }

    public void disabletruecreate() {
        cmb_schedule.setDisable(true);
        txt_employee_code.setDisable(true);
        txt_employee_name.setDisable(true);
        txt_position.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save.setDisable(true);
    }

    public void disablefalsecreate() {
        cmb_schedule.setDisable(false);
        txt_employee_code.setDisable(false);
        txt_employee_name.setDisable(false);
        txt_position.setDisable(false);
        btn_cancel.setDisable(false);
        btn_save.setDisable(false);
    }

    // sa save ng create para ma save sa record
    public void save_create_to_record() {
        yups.insert(new Object[][]{
            {"employee_code", txt_employee_code.getText()},
            //{"name", s_employee_name.getText()},
            //{"position", txt_position.getText()},
            {"mon", cmb_schedule.getValue()},
            {"tues", cmb_schedule.getValue()},
            {"wed", cmb_schedule.getValue()},
            {"thurs", cmb_schedule.getValue()},
            {"fri", cmb_schedule.getValue()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New schedule was successfully submitted");
        tbl_creating_schedule.getColumns().removeAll(tbl_creating_schedule.getColumns());
        disabletruecreate();
        cancel();
    }

    public void cancel() {
        txt_employee_code.setText("");
        txt_employee_name.setText("");
        txt_position.setText("");
        disabletruecreate();
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = srd.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = srd
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr3_weekdays", "employee_code", "ez", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "e", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "e", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "fv", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    
                                    
                                    .get(
                                            "ez.employee_code",
                                            "CONCAT(fv.lastname,', ',fv.firstname,' ',fv.middlename,'.') as FullName",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "aerolink.tbl_hr3_weekdays.mon, aerolink.tbl_hr3_weekdays.tues, aerolink.tbl_hr3_weekdays.wed, aerolink.tbl_hr3_weekdays.thurs, aerolink.tbl_hr3_weekdays.fri ",
                                    "aerolink.tbl_hr4_department.dept_name as dept_id");
                            Data(rs);

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

    // para sa schedule record
    public void Data(List cmgmt) {
        ObservableList<HR3_table_schedule_record> sns = FXCollections.observableArrayList();
        sns.clear();
        try {

            for (Object d : cmgmt) {
                HashMap hm1 = (HashMap) d;

                sns.add(
                        new HR3_table_schedule_record(
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("FullName")),
                                String.valueOf(hm1.get("dept_id")),
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("mon")),
                                String.valueOf(hm1.get("tues")),
                                String.valueOf(hm1.get("wed")),
                                String.valueOf(hm1.get("thurs")),
                                String.valueOf(hm1.get("fri"))
                        ));
            }

            tbl_schedule_record.setItems(sns);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_schedule_record.getItems().size());
        tbl_schedule_record.getSelectionModel().selectFirst();
    }

    // sa shifting request inner join
    public void populateTable_shift() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = shiftshift.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = shiftshift
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr3_shifting_request", "employee_code", "ez", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "e", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "e", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "fv", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    
                                    
                                    .get(
                                            "ez.employee_code",
                                            "CONCAT(fv.lastname,', ',fv.firstname,' ',fv.middlename,'.') as FullName",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "ez.date, ez.schedule, ez.reason, ez.attachment ",
                                    "aerolink.tbl_hr4_department.dept_name as dept_id");
                            Data_ng_Shifting(rs);

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

    // para sa schedule record
    public void Data_ng_Shifting(List cmgmt) {
        ObservableList<HR3_table_shift_request> snd = FXCollections.observableArrayList();
        snd.clear();
        try {

            for (Object d : cmgmt) {
                HashMap hm1 = (HashMap) d;

                snd.add(
                        new HR3_table_shift_request(
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("FullName")),
                                String.valueOf(hm1.get("dept_id")),
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("date")),
                                String.valueOf(hm1.get("schedule")),
                                String.valueOf(hm1.get("reason")),
                                String.valueOf(hm1.get("attachment"))
                        ));
            }

            tbl_request_shifting.setItems(snd);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_request_shifting.getItems().size());
        tbl_request_shifting.getSelectionModel().selectFirst();
    }

}
