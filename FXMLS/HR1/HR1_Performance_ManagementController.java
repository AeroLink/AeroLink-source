/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.TableModel_EmployeeList;
import Model.HR4.HR4_Employee;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    }

    public void generateTable() {
        tblEmployeeList.getItems().clear();
        tblEmployeeList.getColumns().removeAll(tblEmployeeList.getColumns());

        TableColumn<TableModel_EmployeeList, String> EmployeeCode = new TableColumn<>("Employee Code");
        TableColumn<TableModel_EmployeeList, String> Name = new TableColumn<>("Employee Name");
        TableColumn<TableModel_EmployeeList, String> Job = new TableColumn<>("Job");
        TableColumn<TableModel_EmployeeList, String> Email = new TableColumn<>("Email");
        TableColumn<TableModel_EmployeeList, String> Contact = new TableColumn<>("Contact");

        EmployeeCode.setCellValueFactory(value -> value.getValue().empCode);
        Name.setCellValueFactory(value -> value.getValue().name);
        Job.setCellValueFactory(value -> value.getValue().job);
        Contact.setCellValueFactory(value -> value.getValue().contact);
        Email.setCellValueFactory(value -> value.getValue().email);

        this.tblEmployeeList.getColumns().addAll(EmployeeCode, Name, Job, Email, Contact);

    }

    public void renderTable() {
        
    }
}
