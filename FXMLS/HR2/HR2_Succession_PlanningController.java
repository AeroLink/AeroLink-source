/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_Organizational_List;
import Model.HR2_Courses;
import Model.HR2_Temp_Employee_Jobs;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Succession_PlanningController implements Initializable {

    @FXML
    private JFXButton btn_job_vacancy;
    @FXML
    private JFXComboBox cbox_department;
    @FXML
    private TableView<HR2_Organizational_List> tbl_view_positions;
    @FXML
    private TableColumn<HR2_Organizational_List, String> col_positions;
    @FXML
    private TableColumn<HR2_Organizational_List, String> col_employees;

    long DummyCount = 0;
    long GlobalCount = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectDepartment();
        DisplayDataInJTable();
        populateTableBySelectDept();

      /*  cbox_department.getSelectionModel().selectedItemProperty().addListener(listener -> {
            populateTableBySelectDept();
        });*/
    }

    @FXML
    public void jobVacancyModal() {
        Modal m = Modal.getInstance(new Form("/FXMLS/HR2/Modals/ViewJobVacancy.fxml").getParent());
        m.open();
    }

    public void selectDepartment() {
        HR4_Departments dept = new HR4_Departments();

        try {
            List c = dept.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS

                cbox_department.getItems().add("DEPT" + hm1.get("id") + " - " + hm1.get("dept_name"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void populateTableBySelectDept() {

        try {

            HR2_Temp_Employee_Jobs ej = new HR2_Temp_Employee_Jobs();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("succession_planning")) {
                    ej.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {
                        
                        try
                        {

                        List sp = ej.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp",
                                "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                            /*    .where(new Object[][]{{"jobs.dept_id", "=" + cbox_department.getSelectionModel()
                            .getSelectedItem().toString().substring(4).toString().split(" - ")[0]}})*/
                                .get("jobs.title as position", "concat(emp.firstname, ' ',emp.middlename, ' ' ,emp.lastname)as employees");
                        Data(sp);
                        }catch(Exception e)
                        {
                            System.out.println(e);
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

    public void Data(List b) {
        ObservableList<HR2_Organizational_List> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {

                HashMap hm = (HashMap) d;
                System.out.println(hm);
                obj.add(
                        new HR2_Organizational_List(
                                String.valueOf(hm.get("position")),
                                String.valueOf(hm.get("employees"))));

            }
            tbl_view_positions.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {
        col_positions.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Organizational_List, String> param) -> param.getValue().Job_Title);
        col_employees.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Organizational_List, String> param) -> param.getValue().Fullname);
        TableColumn<HR2_Organizational_List, Void> addButton = new TableColumn("View Employee Info");

        Callback<TableColumn<HR2_Organizational_List, Void>, TableCell<HR2_Organizational_List, Void>> cellFactory
                = new Callback<TableColumn<HR2_Organizational_List, Void>, TableCell<HR2_Organizational_List, Void>>() {
            @Override
            public TableCell<HR2_Organizational_List, Void> call(final TableColumn<HR2_Organizational_List, Void> param) {

                final TableCell<HR2_Organizational_List, Void> cell = new TableCell<HR2_Organizational_List, Void>() {
                    private final Button btn = new Button("View");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                            });
                            btn.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }

        };

        addButton.setCellFactory(cellFactory);
        tbl_view_positions.getColumns().add(addButton);
    }

}
