/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.ClassFiles.HR3_tpLeaves;
import FXMLS.HR3.ClassFiles.Leave_ManagementRequestClass;
import FXMLS.HR3.ClassFiles.Shift_RequestClass;
import Model.HR3.HR3_Leaves;
import Model.HR3.HR_RequestLeaves;
import Synapse.Model.JOIN;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class HR3_Leave_ManagementController implements Initializable {

    @FXML
    private Button btnrefresh1;
    @FXML
    private TableView<Leave_ManagementRequestClass> tablerequest;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> ID;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> Name;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> Position;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> TypeofLeave;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> RangeofLeave;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> DateStart;
    @FXML
    private TableColumn<Leave_ManagementRequestClass, String> DateEnd;
    @FXML
    private TableColumn<?, ?> Status;
    @FXML
    private TableView<?> tableleave;
    @FXML
    private TableColumn<?, ?> tbl_id;
    @FXML
    private TableColumn<?, ?> tbl_name;
    @FXML
    private TableColumn<?, ?> tbl_position;
    @FXML
    private TableColumn<?, ?> tbl_leave;
    @FXML
    private TableColumn<?, ?> tbl_range;
    @FXML
    private TableColumn<?, ?> tbl_start;
    @FXML
    private TableColumn<?, ?> tbl_end;
    @FXML
    private TableColumn<?, ?> tbl_status;
    @FXML
    private Button btnrefresh;
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
    private JFXTextField rid;
    @FXML
    private JFXTextField rname;
    @FXML
    private JFXTextField rposition;
    @FXML
    private JFXTextField rtype;
    @FXML
    private JFXTextField rrange;
    @FXML
    private JFXTextField rstart;
    @FXML
    private JFXTextField rend;
    @FXML
    private Button rsubmit;
    @FXML
    private JFXComboBox<?> rstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Type of Leaves Generate
        this.generatetable();
        this.loadData();
        this.loadRequestleave();

        btnAdd.setOnAction(e -> addLeave());
        btnSave.setOnAction(e -> saveLeave());
        btnEdit.setOnAction(e -> EditLeave());
        btnUpdate.setOnAction(e -> UpdateLeave());

        tbl_tpLeaves.setOnMouseClicked((MouseEvent event) -> {

            txtLeaveDesc.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LeaveName.getValue());
            txtLimit.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LeaveLimit.getValue());
            txtNumDays.setText(tbl_tpLeaves.getSelectionModel().getSelectedItem().LimitDays.getValue());

            btnEdit.setDisable(false);
        });

    }

    //Global Vars
    HR_RequestLeaves leaverequest = new HR_RequestLeaves();
    HR3_Leaves leaves = new HR3_Leaves("typeofleaves");
    long DummyCount = 0;
    long GlobalCount = 0;
    ObservableList<HR3_tpLeaves> ob = FXCollections.observableArrayList();

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

    public void loadRequestleave() {

        try {

            HR_RequestLeaves leaverequest = new HR_RequestLeaves();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("competency_management")) {
                    leaverequest.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List c = leaverequest.join(JOIN.INNER, "aerolink.tbl_hr3_leave", "leave_id", "rl", "=", "leave_id")
                                .join(JOIN.INNER, "aerolink.tbl_hr3_typeofleaves", "leave_id", "s", "=", "leave_id")
                                //.where(new Object[][]{{"s.isDeleted", "=", "0"}})
                                .get("rl.employee_code", "rl.leave_id", "s.leave_name", "rl.Range_of_Leave", "rl.Date_Start", "rl.Date_end");

                        ObservableList<Leave_ManagementRequestClass> dv = FXCollections.observableArrayList();
                        List b = leaverequest.get();

                        for (Object d : b) {
                            HashMap hm = (HashMap) d;

                            dv.add(
                                    new Leave_ManagementRequestClass(
                                            String.valueOf(hm.get("employee_code")),
                                            String.valueOf(hm.get("leave_id")),
                                            String.valueOf(hm.get("leave_name")),
                                            String.valueOf(hm.get("Range_of_Leave")),
                                            String.valueOf(hm.get("Date_Start")),
                                            String.valueOf(hm.get("Thursday")),
                                            String.valueOf(hm.get("Date_end"))
                                    ));

                        }
                        GlobalCount = DummyCount;
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(HR2_Competency_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                return 0;
            }, Session.SessionThreads);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
