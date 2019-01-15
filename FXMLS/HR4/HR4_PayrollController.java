/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;
import FXMLS.HR4.ClassFiles.HR4_PayrollClass;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import FXMLS.HR4.Model.HR4_PayrollModel;
import Model.HR4_Classification;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_PayrollController implements Initializable {
    int Global_Count = 0;
    @FXML
    private TableView<HR4_PayrollClass> tbl_payrollrep;
    ObservableList<HR4_PayrollClass> obj = FXCollections.observableArrayList();
    ExecutorService e = Executors.newFixedThreadPool(1);
    HR4_PayrollModel payroll = new HR4_PayrollModel();
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private Tab tbl_rep;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.populateTable();
        this.generateTable();
    }
    public void generateTable() {

        tbl_payrollrep.getItems().clear();
        tbl_payrollrep.getColumns().removeAll(tbl_payrollrep.getColumns());
        TableColumn<HR4_PayrollClass, String> employee_code = new TableColumn<>("ER/EE Code");
        TableColumn<HR4_PayrollClass, String> name = new TableColumn<>("Name");
        TableColumn<HR4_PayrollClass, String> job_id = new TableColumn<>("Position");
        TableColumn<HR4_PayrollClass, String> dept_id = new TableColumn<>("Department");
        TableColumn<HR4_PayrollClass, String> classification_id = new TableColumn<>("Classification");
        TableColumn<HR4_PayrollClass, String> gross_pay = new TableColumn<>("Gross Pay");
        TableColumn<HR4_PayrollClass, String> net_pay = new TableColumn<>("Net Pay");
        TableColumn<HR4_PayrollClass, String> start_date = new TableColumn<>("Date Start");
        TableColumn<HR4_PayrollClass, String> end_date = new TableColumn<>("Date End");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().employee_code);
        name.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().name);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().job_id);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().dept_id);
        classification_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().classification_id);
        gross_pay.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().gross_pay);
        net_pay.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().net_pay);
        start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().start_date);
        end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollClass, String> param) -> param.getValue().end_date);
        
        //</editor-fold>
        tbl_payrollrep.getColumns().addAll(employee_code, name, job_id, dept_id, classification_id, gross_pay, net_pay, start_date, end_date);
    }
    
    
    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    payroll.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_payrollrep.getItems().removeAll(obj);
                            List rs = payroll
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_classifications", "id", "=", "aerolink.tbl_hr4_jobs","classification_id",true)
                                    
                                    //.where(new Object[][]{{"tblC.class_name", "=", ckasscb.getSelectionModel().getSelectedItem().toString()}})
                                    .get(   
                                            "tblD.employee_code",
                                            "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as name",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "aerolink.tbl_hr4_department.dept_name as dept_id",
                                            "aerolink.tbl_hr4_job_classifications.class_name as classification_id",
                                            "gross_pay",
                                            "net_pay"
                                            //"start_date"
                                            //"end_date"
                                            );
                        AddJobToTable(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable(List rs) {
        obj.clear();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String employee_code = String.valueOf(crow.get("employee_code"));
            String name = (String) crow.get("name");
            String job_id = (String) crow.get("job_id");
            String dept_id = (String) crow.get("dept_id");
            String classification_id = (String) crow.get("classification_id");
            String gross_pay = (String) crow.get("gross_pay");
            String net_pay = (String) crow.get("net_pay");
            String start_date = (String) crow.get("start_date");
            String end_date = (String) crow.get("end_date");
            obj.add(new HR4_PayrollClass(employee_code, name, job_id, dept_id, classification_id, gross_pay, net_pay, start_date, end_date));
        }
        tbl_payrollrep.setItems(obj);
    }

}

