/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.HR1_Employee;
import FXMLS.HR1.ClassFiles.TableModel_EmployeeList;
import Model.HR1.HR1_PerformanceGrade;
import Model.HR4.HR4_Employee;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.STORED_PROC;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_Performance_ManagementController implements Initializable {

    HR4_Departments dept = new HR4_Departments();
    HR4_Employee employees = new HR4_Employee();
    HR4_Employee empJob = new HR4_Employee("job");
    HR4_Jobs jobs = new HR4_Jobs();

    @FXML
    private StackPane spane;
    @FXML
    private ComboBox cboDept;
    @FXML
    private TableView<TableModel_EmployeeList> tblEmployeeList;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem viewX;
    @FXML
    private ContextMenu secondContext;
    @FXML
    private MenuItem viewGrade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dept.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            cboDept.getItems().add(row.get("dept_name").toString());
        });

        this.generateTable();
        //this.renderTable();

        cboDept.getSelectionModel().selectedItemProperty().addListener(listener -> {
            this.renderTable();
        });

        tblEmployeeList.setOnMouseClicked(action -> {

            if (action.getButton() == MouseButton.SECONDARY) {
                if (!"Evaluated".equals(tblEmployeeList.getSelectionModel().getSelectedItem().status.getValue())) {
                    tblEmployeeList.setContextMenu(contextMenu);
                    contextMenu.show(tblEmployeeList, action.getX(), action.getY());
                } else {
                    tblEmployeeList.setContextMenu(secondContext);
                    secondContext.show(tblEmployeeList, action.getX(), action.getY());
                }

            }

        });

        viewX.setOnAction(value -> ViewModal());
        viewGrade.setOnAction(value -> ViewModal2());

    }

    public void ViewModal() {
        HR1_Employee.emp_code = tblEmployeeList.getSelectionModel().getSelectedItem().empCode.getValue();
        HR1_Employee.email = tblEmployeeList.getSelectionModel().getSelectedItem().email.getValue();
        HR1_Employee.contact = tblEmployeeList.getSelectionModel().getSelectedItem().contact.getValue();
        HR1_Employee.fullname = tblEmployeeList.getSelectionModel().getSelectedItem().name.getValue();
        HR1_Employee.job_title = tblEmployeeList.getSelectionModel().getSelectedItem().job.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_Performance_Criteria_1.fxml").getParent());
        md.open();
    }

    public void ViewModal2() {
        HR1_Employee.emp_code = tblEmployeeList.getSelectionModel().getSelectedItem().empCode.getValue();
        HR1_Employee.email = tblEmployeeList.getSelectionModel().getSelectedItem().email.getValue();
        HR1_Employee.contact = tblEmployeeList.getSelectionModel().getSelectedItem().contact.getValue();
        HR1_Employee.fullname = tblEmployeeList.getSelectionModel().getSelectedItem().name.getValue();
        HR1_Employee.job_title = tblEmployeeList.getSelectionModel().getSelectedItem().job.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewPerformance.fxml").getParent());
        md.open();
    }

    public void generateTable() {
        tblEmployeeList.getItems().clear();
        tblEmployeeList.getColumns().removeAll(tblEmployeeList.getColumns());

        TableColumn<TableModel_EmployeeList, String> EmployeeCode = new TableColumn<>("Employee Code");
        TableColumn<TableModel_EmployeeList, String> Name = new TableColumn<>("Employee Name");
        TableColumn<TableModel_EmployeeList, String> Job = new TableColumn<>("Job");
        TableColumn<TableModel_EmployeeList, String> Email = new TableColumn<>("Email");
        TableColumn<TableModel_EmployeeList, String> Contact = new TableColumn<>("Contact");
        TableColumn<TableModel_EmployeeList, String> Status = new TableColumn<>("Status");

        EmployeeCode.setCellValueFactory(value -> value.getValue().empCode);
        Name.setCellValueFactory(value -> value.getValue().name);
        Job.setCellValueFactory(value -> value.getValue().job);
        Contact.setCellValueFactory(value -> value.getValue().contact);
        Email.setCellValueFactory(value -> value.getValue().email);
        Status.setCellValueFactory(value -> value.getValue().status);

        this.tblEmployeeList.getColumns().addAll(EmployeeCode, Name, Job, Email, Contact, Status);

    }

    HR1_PerformanceGrade grd = new HR1_PerformanceGrade();

    public void renderTable() {

        //eto yung code ko na sobrang haba, ngayon gagamitan natin ng bagong STORED_PROC CLASS
        ObservableList<TableModel_EmployeeList> list = FXCollections.observableArrayList();

        STORED_PROC.executeCall("HR1_PERFOMANCE_MODULE_getAllEmployees", new Object[][]{
            {"dept", cboDept.getSelectionModel().getSelectedItem().toString()},
            {"type_id",  5},
            {"status_id", 1}
        }).stream().forEach(action -> {

            HashMap row = (HashMap) action;

            list.add(new TableModel_EmployeeList(row.get("employee_code").toString(),
                    (row.get("lastname").toString() + ", "
                    + row.get("firstname").toString() + " "
                    + row.get("middlename").toString()),
                    row.get("email").toString(),
                    row.get("contact_number").toString(),
                    row.get("title").toString(),
                    grd.where(new Object[][]{{"employee_code", "=", row.get("employee_code").toString()}}).get().stream().count() != 0 ? "Evaluated" : "Not Evaluated"
            ));

        });
        
//        empJob.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
//                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "=", "employee_code")
//                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
//                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
//                .where(new Object[][]{
//            
//        }).get()
//                .stream().forEach(action -> {
//
//                    HashMap row = (HashMap) action;
//
//                    list.add(new TableModel_EmployeeList(row.get("employee_code").toString(),
//                            (row.get("lastname").toString() + ", "
//                            + row.get("firstname").toString() + " "
//                            + row.get("middlename").toString()),
//                            row.get("email").toString(),
//                            row.get("contact_number").toString(),
//                            row.get("title").toString(),
//                            grd.where(new Object[][]{{"employee_code", "=", row.get("employee_code").toString()}}).get().stream().count() != 0 ? "Evaluated" : "Not Evaluated"
//                    ));
//
//                });
        tblEmployeeList.getItems().clear();
        tblEmployeeList.setItems(list);

    }
}
