/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_JV_With_Skills_for_SP;
import FXMLS.HR2.ClassFiles.HR2_Job_VacancyClass;
import FXMLS.HR2.ClassFiles.HR2_Organizational_List;
import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
import FXMLS.HR2.ClassFiles.SP_JV_with_Skills_Modal;
import Model.HR2_CM_Pivot;
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
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
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
    private JFXComboBox cbox_department;
    @FXML
    private TableView<HR2_Organizational_List> tbl_view_positions;
    @FXML
    private TableColumn<HR2_Organizational_List, String> col_positions;
    @FXML
    private TableColumn<HR2_Organizational_List, String> col_employees;

    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private TableView<HR2_JV_With_Skills_for_SP> tbl_job_vacancy;
    @FXML
    private TableColumn<HR2_JV_With_Skills_for_SP, String> col_job;
    @FXML
    private JFXTextField txt_jv;
    @FXML
    private TableColumn<HR2_JV_With_Skills_for_SP, String> col_dept;
    @FXML
    private TableColumn<HR2_Organizational_List, String> col_classification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectDepartment();
        DisplayDataInJTable();
        viewJobVacancy();
        populateTableBySelectDept();
        cbox_department.getSelectionModel().selectedItemProperty().addListener(listener -> {
            populateTableBySelectDept();
        });
    }

    //Job Vacancy
    public void viewJobVacancy() {
        HR4_Jobs jobs = new HR4_Jobs();
        List jv_data = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_limit", "job_id", "j_limit", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "d", "=", "dept_id")
                .where(new Object[][]{{"j_limit.jobOpen", "!=", "0"}})
                .get("d.dept_name as department,aerolink.tbl_hr4_jobs.title");
        JV(jv_data);
    }

    public void JV(List jv) {
        ObservableList<HR2_JV_With_Skills_for_SP> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : jv) {
                HashMap hm = (HashMap) d;
                obj.add(
                        new HR2_JV_With_Skills_for_SP(
                                String.valueOf(hm.get("department")),
                                String.valueOf(hm.get("title"))
                        ));

            }
            tbl_job_vacancy.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Organizational List
    public void selectDepartment() {
        HR4_Departments dept = new HR4_Departments();

        try {
            List c = dept.get();
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                //  cbox_department.getItems().add("DEPT" + hm1.get("id") + " - " + hm1.get("dept_name"));
                cbox_department.getItems().add(hm1.get("dept_name"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void populateTableBySelectDept() {

        try {

            HR2_Temp_Employee_Jobs ej = new HR2_Temp_Employee_Jobs();
            List sp = ej.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp",
                    "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "jobs", "dept_id", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_classifications", "id", "=", "jobs", "classification_id", true)
                    .where(new Object[][]{{"aerolink.tbl_hr4_department.dept_name", "=", cbox_department.getSelectionModel()
                .getSelectedItem().toString()}})
                    .orderBy("aerolink.tbl_hr4_job_classifications.class_level", Model.Sort.ASC)
                    .get("jobs.title as position", "concat(emp.firstname, ' ',emp.middlename, ' ' ,emp.lastname)as employees",
                            "aerolink.tbl_hr4_job_classifications.class_name as Classification");
            Data(sp);
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
                                String.valueOf(hm.get("employees")),
                                String.valueOf(hm.get("Classification"))
                        ));

            }
            /*       Collections.sort(b,new Comparator<HashMap<String, String>>() {
                public int compare(HashMap<String, String> one, HashMap<String, String> two) {
                    return one.get("site_name")
                    .compareTo(two.get("site_name"));
                }
            }
            );*/
            tbl_view_positions.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {
        col_dept.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JV_With_Skills_for_SP, String> param) -> param.getValue().department);
        col_job.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JV_With_Skills_for_SP, String> param) -> param.getValue().title);
        SkillsButton();

        //Organizational List
        col_classification.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Organizational_List, String> param) -> param.getValue().classification);
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
                                    -> 
                            {
                            HR2_Organizational_List ol = (HR2_Organizational_List) getTableRow().getItem();
                            SP_Employee_Info_Modal.init_EmpInfo(
                                    ol.Fullname.get(),
                                    ol.Job_Title.get());
                            Modal viewEmp = Modal.getInstance(new Form("/FXMLS/HR2/Modals/SP_ViewEmployeeInfo.fxml").getParent());
                            viewEmp.open();
                        });
                        btn.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                        btn.setCursor(javafx.scene.Cursor.HAND);
                    }
                    catch (Exception ex) {
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

    public void SkillsButton() {
        TableColumn<HR2_JV_With_Skills_for_SP, Void> addButton = new TableColumn("Action");

        Callback<TableColumn<HR2_JV_With_Skills_for_SP, Void>, TableCell<HR2_JV_With_Skills_for_SP, Void>> cellFactory
                = new Callback<TableColumn<HR2_JV_With_Skills_for_SP, Void>, TableCell<HR2_JV_With_Skills_for_SP, Void>>() {
            @Override
        public TableCell<HR2_JV_With_Skills_for_SP, Void> call(final TableColumn<HR2_JV_With_Skills_for_SP, Void> param) {

                final TableCell<HR2_JV_With_Skills_for_SP, Void> cell = new TableCell<HR2_JV_With_Skills_for_SP, Void>() {
                    private final Button btn = new Button("View Skills");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                HR2_JV_With_Skills_for_SP skills_sp = (HR2_JV_With_Skills_for_SP) getTableRow().getItem();
                                SP_JV_with_Skills_Modal.view_skill(
                                        skills_sp.title.get());
                                Modal viewEmp = Modal.getInstance(new Form("/FXMLS/HR2/Modals/SP_ViewSkills.fxml").getParent());
                                viewEmp.open();
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
        tbl_job_vacancy.getColumns().add(addButton);
    }
}
