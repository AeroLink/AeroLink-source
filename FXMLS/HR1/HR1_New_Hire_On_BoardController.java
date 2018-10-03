/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHire;
import Model.HR4.HR4_Employee;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_New_Hire_On_BoardController implements Initializable {

    HR4_Employee employee = new HR4_Employee();
    HR4_Employee employeeProfile = new HR4_Employee("profile");
    HR4_Employee employeeJob = new HR4_Employee("job");

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private TableView<TableModel_NewHire> tblNewHire;
    @FXML
    private MenuItem menuPost;
    @FXML
    private ContextMenu contextMenuEmployees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.GenerateTable();
        this.RenderTable();

        this.tblNewHire.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (event.getClickCount() == 2) {
                    this.contextMenuEmployees.show(tblNewHire, event.getX(), event.getY());
                }
            }
        });

        this.tblNewHire.setContextMenu(contextMenuEmployees);
        this.menuPost.setOnAction(action -> viewInformation());

    }

    public void viewInformation() {
        employeeProfile
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_civil_status", "id", "=", "civil_status_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "=", "aerolink.tbl_hr4_employee_profiles", "suffix_id", true)
                .where(new Object[][]{
            {"employee_code", "=", tblNewHire.getSelectionModel().getSelectedItem().empCode.getValue()}
        }).get().stream().forEach(row -> {
            HashMap action = (HashMap) row;
            HR1_NewHireClass.init(
                    tblNewHire.getSelectionModel().getSelectedItem().name.getValue(),
                    tblNewHire.getSelectionModel().getSelectedItem().empCode.getValue(),
                    this.tblNewHire.getSelectionModel().getSelectedItem().job.getValue(),
                    action.get("date_of_birth").toString(),
                    action.get("place_of_birth").toString(),
                    action.get("gender").toString(),
                    action.get("civil_status").toString(),
                    action.get("email").toString(),
                    action.get("height").toString(),
                    action.get("weight").toString(),
                    action.get("contact_number").toString());
        });

        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewNewHire.fxml").getParent());
        md.open();
    }

    public void GenerateTable() {

        this.tblNewHire.getColumns().clear();

        TableColumn<TableModel_NewHire, String> EmployeeCode = new TableColumn<>("Employee Code");
        TableColumn<TableModel_NewHire, String> Name = new TableColumn<>("Employee Name");
        TableColumn<TableModel_NewHire, String> Job = new TableColumn<>("Job");
        TableColumn<TableModel_NewHire, String> Email = new TableColumn<>("Email");
        TableColumn<TableModel_NewHire, String> Contact = new TableColumn<>("Contact");

        EmployeeCode.setCellValueFactory(value -> value.getValue().empCode);
        Name.setCellValueFactory(value -> value.getValue().name);
        Job.setCellValueFactory(value -> value.getValue().job);
        Contact.setCellValueFactory(value -> value.getValue().contact);
        Email.setCellValueFactory(value -> value.getValue().email);

        this.tblNewHire.getColumns().addAll(EmployeeCode, Name, Job, Email, Contact);

    }

    private long DummyCount = 0;
    private long GlobalCount = 0;
    private ObservableList employeeProb = FXCollections.observableArrayList();

    public void RenderTable() {

        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("newHire")) {
                employee.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    System.err.println(DummyCount);
                });

                if (DummyCount != GlobalCount) {
                    employee.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
                            .join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "=", "aerolink.tbl_hr4_employee_profiles", "suffix_id", true)
                            .where(new Object[][]{
                        {"type_id", "=", 5}
                    }).get().stream().forEach(action -> {
                        HashMap row = (HashMap) action;

                        String empJob = "";
                        List jobList = employeeJob.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                                .where(new Object[][]{
                            {"employee_code", "=", row.get("employee_code").toString()}
                        }).get();

                        for (Object rx : jobList) {
                            System.err.println(((HashMap) rx).get("title").toString());
                            empJob = ((HashMap) rx).get("title").toString();
                        }

                        System.err.println(empJob);
                        employeeProb.add(new TableModel_NewHire(
                                row.get("employee_code").toString(),
                                (row.get("lastname").toString() + ", "
                                + row.get("firstname").toString() + " "
                                + (row.get("suffix_name").toString().equals("None") ? "" : row.get("suffix_name").toString()) + " "
                                + row.get("middlename").toString()),
                                row.get("email").toString(),
                                row.get("contact_number").toString(),
                                empJob
                        ));
                    });

                    tblNewHire.getItems().clear();
                    tblNewHire.setItems(employeeProb);

                    GlobalCount = DummyCount;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR1_New_Hire_On_BoardController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return 0;
        }, Session.SessionThreads);

    }

}
