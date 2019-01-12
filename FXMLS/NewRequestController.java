/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS;

import Helpers.AlertResponse;
import Model.HR4.HR4_Employee;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Model.Requisition.Requests;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class NewRequestController implements Initializable {

    ToggleGroup toggleGroup = new ToggleGroup();

    HR4_Departments dept = new HR4_Departments();

    HR4_Employee employees = new HR4_Employee();
    HR4_Employee empJob = new HR4_Employee("job");
    HR4_Jobs jobs = new HR4_Jobs();

    @FXML
    private ComboBox cboDept;
    @FXML
    private RadioButton rdoWholeDept;
    @FXML
    private RadioButton rdoSpecificPerson;
    @FXML
    private TableView<TableModel> tblPersons;

    @FXML
    private TextField txtTitle;
    @FXML
    private HTMLEditor txtMessage;
    @FXML
    private JFXButton btnSend;

    class TableModel {

        private SimpleStringProperty employeename;
        private SimpleStringProperty position;
        private SimpleStringProperty employeeCode;

        public TableModel(String semployeename, String sposition, String sec) {
            this.employeename = new SimpleStringProperty(semployeename);
            this.position = new SimpleStringProperty(sposition);
            this.employeeCode = new SimpleStringProperty(sec);
        }

        public SimpleStringProperty getEmployeename() {
            return employeename;
        }

        public SimpleStringProperty getPosition() {
            return position;
        }

        public SimpleStringProperty getEmployeeCode() {
            return employeeCode;
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdoSpecificPerson.setToggleGroup(toggleGroup);
        rdoWholeDept.setToggleGroup(toggleGroup);

        dept.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            cboDept.getItems().add(row.get("id").toString() + " - " + row.get("dept_name").toString());
        });

        rdoWholeDept.setSelected(true);

        tblPersons.getItems().clear();
        tblPersons.getColumns().removeAll(tblPersons.getColumns());

        TableColumn<TableModel, String> employeeCode = new TableColumn<>("ID");
        TableColumn<TableModel, String> employeeName = new TableColumn<>("Employee Name");
        TableColumn<TableModel, String> employeePosition = new TableColumn<>("Employee Position");

        employeeCode.setCellValueFactory(value -> value.getValue().employeeCode);
        employeeName.setCellValueFactory(value -> value.getValue().employeename);
        employeePosition.setCellValueFactory(value -> value.getValue().position);

        tblPersons.getColumns().addAll(employeeCode, employeeName, employeePosition);

        cboDept.getSelectionModel().selectedItemProperty().addListener(listener -> {
            this.renderTable();
        });

        toggleGroup.selectedToggleProperty().addListener(listener -> {
            if (rdoSpecificPerson.isSelected()) {
                tblPersons.setDisable(false);
            }

            if (rdoWholeDept.isSelected()) {
                tblPersons.setDisable(true);
            }

        });
        
        btnSend.setOnAction(value -> SendRequest());
    }

    public void renderTable() {

        ObservableList<TableModel> list = FXCollections.observableArrayList();

        empJob.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                .where(new Object[][]{
            {"aerolink.tbl_hr4_department.id", "=", cboDept.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"aerolink.tbl_hr4_employees.status_id", "=", 1}
        }).get("aerolink.tbl_hr4_employee_profiles.lastname", "aerolink.tbl_hr4_employee_profiles.firstname", "aerolink.tbl_hr4_employee_profiles.middlename", "aerolink.tbl_hr4_jobs.title", "aerolink.tbl_hr4_employees.employee_code")
                .stream().forEach(action -> {

                    HashMap row = (HashMap) action;

                    list.add(new TableModel(
                            (row.get("lastname").toString() + ", "
                            + row.get("firstname").toString() + " "
                            + row.get("middlename").toString()),
                            row.get("title").toString(),
                            row.get("employee_code").toString()
                    ));

                });

        tblPersons.getItems().clear();
        tblPersons.setItems(list);
        if (!tblPersons.getItems().isEmpty()) {
            tblPersons.getSelectionModel().select(0);
        }
    }

    public void SendRequest() {
        Requests req = new Requests();
        
        req.insert(new Object[][] {
            {"request_title", txtTitle.getText()},
            {"request_content", txtMessage.getHtmlText().replace("\"", "'")},
            {"target_dept", cboDept.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"request_from", Session.pull("username")},
            {"request_to", rdoWholeDept.isSelected() ? "*" : tblPersons.getSelectionModel().getSelectedItem().getEmployeeCode().getValue()}
        });
        
        Helpers.EIS_Response.SuccessResponse("Response", "Success!", "Request was sent successfully!");
    }
}
