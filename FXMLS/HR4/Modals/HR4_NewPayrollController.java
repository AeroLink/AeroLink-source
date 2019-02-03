/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass;
import FXMLS.HR4.Filler.HR4_NewPayrollFill;
import FXMLS.HR4.ClassFiles.HR4_PayrollClass;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.Model.HR4_PayrollModel;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_NewPayrollController implements Initializable {

    @FXML
    private TextField lbl_pc;
    @FXML
    private TextField lbl_sd;
    @FXML
    private TextField lbl_ed;
    
    ObservableList<HR4_EmpInfoClass> obj1 = FXCollections.observableArrayList();
    HR4_PayrollModel payroll = new HR4_PayrollModel();
    HR4_EmployeeInfo emp = new HR4_EmployeeInfo();
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private TableView<HR4_EmpInfoClass> tbl_payroll_emp;
    @FXML
    private TextField lbl_dpt;
    Boolean searchStatus = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateTable1();
        populateTable1();
        lbl_pc.setText(HR4_NewPayrollFill.jae);
        lbl_sd.setText(HR4_NewPayrollFill.jae1);
        lbl_ed.setText(HR4_NewPayrollFill.jae2);
        lbl_dpt.setText(HR4_NewPayrollFill.jae3);
        
    
    }
    public void generateTable1() {

        tbl_payroll_emp.getItems().clear();
        tbl_payroll_emp.getColumns().removeAll(tbl_payroll_emp.getColumns());
        TableColumn<HR4_EmpInfoClass, String> Select = new TableColumn<>("Select");
        TableColumn<HR4_EmpInfoClass, String> employee_code = new TableColumn<>("EE/ER Code");
        TableColumn<HR4_EmpInfoClass, String> fnn = new TableColumn<>("Fullname");
        TableColumn<HR4_EmpInfoClass, String> job_id = new TableColumn<>("Position");
        TableColumn<HR4_EmpInfoClass, String> dept_id = new TableColumn<>("Department");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        Select.setCellValueFactory(new PropertyValueFactory<HR4_EmpInfoClass,String>("Select"));
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().employee_code);
        fnn.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().fnn);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().job_id);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().dept_id);
        //</editor-fold>
        tbl_payroll_emp.getColumns()
                .addAll(Select, employee_code, fnn, job_id, dept_id);
    }
   

    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    emp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_payroll_emp.getItems();
                        List rs = emp
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                .get(
                                        "tblD.employee_code",
                                        "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnn",
                                        "aerolink.tbl_hr4_jobs.title as job_id",
                                        "aerolink.tbl_hr4_department.dept_name as dept_id"
                                );

                        AddJobToTable1(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable1(List rs) {

        obj1.clear();
        tbl_payroll_emp.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String employee_code = String.valueOf(crow.get("employee_code"));
            String fnn = (String) crow.get("fnn");
            String job_id = (String) crow.get("job_id");
            String dept_id = (String) crow.get("dept_id");
            String status_id = (String) crow.get("status_id");
            obj1.add(new HR4_EmpInfoClass(employee_code, fnn, job_id, dept_id, status_id));
        }

        tbl_payroll_emp.setItems(obj1);

    }
    
}
