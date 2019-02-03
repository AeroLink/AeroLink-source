/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.CM_SkillReq_ModalClass;
import FXMLS.HR2.ClassFiles.CM_Skill_RequisitionClass;
import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_LM_CourseOutlineModal;
import FXMLS.HR2.ClassFiles.HR2_TM_ViewTrainingInfo_Modal;
import FXMLS.HR2.ClassFiles.HR2_Temp_VehicleClass;
import FXMLS.HR2.ClassFiles.HR2_TrainingReq_Class;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import FXMLS.HR2.ClassFiles.TM_AssetFacilities;
import FXMLS.HR2.ClassFiles.TM_DT_ViewParticipantsModalClass;
import FXMLS.HR2.ClassFiles.TM_DefaultTrainings;
import FXMLS.HR2.ClassFiles.TM_FacilityDetailsClass_for_Modal;
import FXMLS.HR2.ClassFiles.TM_Request_BudgetClass;
import FXMLS.HR2.ClassFiles.TM_TrainingRequisition_Class;
import FXMLS.HR2.ClassFiles.TM_VehicleDetailsClassModal;
import FXMLS.HR2.ClassFiles.TM_ViewTrainingReqClassModal;
import Model.Financial.Financial_budget_request;
import Model.HR2_CM_Skills;
import Model.HR2_TM_DefaultTrainings;
import Model.HR2_TM_TrainingInfo;
import Model.HR2_TM_Training_Requisition;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR2_Temp_Facilities;
import Model.HR2_Temp_Vehicles;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class HR2_Training_ManagementController implements Initializable {

    @FXML
    private TableView<TM_TrainingRequisition_Class> tbl_training_req;
    @FXML
    private TableColumn<TM_TrainingRequisition_Class, String> col_req_dept;
    @FXML
    private TableColumn<TM_TrainingRequisition_Class, String> col_req_jp;
    @FXML
    private TableColumn<TM_TrainingRequisition_Class, String> col_req_date_req;
    @FXML
    private TableView<HR2_TrainingReq_Class> tbl_training_mngmt;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_dept;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_jp;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_p;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_from;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_to;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_pn_process;
    @FXML
    private TableColumn<TM_TrainingRequisition_Class, String> col_req_status;
    @FXML
    private ContextMenu CMenu;
    @FXML
    private MenuItem MI_more;
    @FXML
    private MenuItem MI_archive;
    @FXML
    private TableView<TM_AssetFacilities> tbl_req_facility;
    @FXML
    private TableColumn<TM_AssetFacilities, String> col_req_facility;
    @FXML
    private TableView<HR2_Temp_VehicleClass> tbl_req_vehicle;
    @FXML
    private TableColumn<HR2_Temp_VehicleClass, String> col_req_vehicleType;
    @FXML
    private TableColumn<HR2_Temp_VehicleClass, String> col_req_vehicleModel;
    @FXML
    private Label lbl_training_req_notif;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_tm_trainor;
    @FXML
    private TableView<HR2_TrainingReq_Class> tbl_history_of_trainings;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_dept;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_jp;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_p;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_trainor;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_from;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_to;
    @FXML
    private TableColumn<HR2_TrainingReq_Class, String> col_hs_status;
    @FXML
    private JFXComboBox cbox_hs_dept;
    @FXML
    private JFXComboBox cbox_tm_dept;
    @FXML
    private JFXComboBox cbox_tm_trainor;
    @FXML
    private JFXComboBox cbox_hs_trainor;
    @FXML
    private TableColumn<TM_AssetFacilities, String> col_building;
    @FXML
    private TableColumn<TM_AssetFacilities, String> col_facilityStatus;
    @FXML
    private JFXComboBox cbox_filter_Fstatus;
    @FXML
    private TableColumn<TM_AssetFacilities, String> col_f_roomNumber;
    @FXML
    private TableColumn<TM_AssetFacilities, String> col_f_capacity;
    @FXML
    private ContextMenu hs_contextMenu;
    @FXML
    private MenuItem mi_view_hs_training;
    @FXML
    private JFXComboBox cbox_filter_Vstatus;
    @FXML
    private TableColumn<HR2_Temp_VehicleClass, String> col_req_vehicleStatus;
    @FXML
    private TableView<TM_DefaultTrainings> tbl_default_trainings;
    @FXML
    private JFXButton btn_add_training;
    @FXML
    private TableColumn<TM_DefaultTrainings, String> col_t_jp;
    @FXML
    private TableColumn<TM_DefaultTrainings, String> col_t_training_title;
    @FXML
    private TableColumn<TM_DefaultTrainings, String> col_t_trainor;
    @FXML
    private JFXComboBox cbox_filter_t_jp;
    @FXML
    private JFXComboBox cbox_filter_t_trainor;
    @FXML
    private JFXButton btn_req_budget;
    @FXML
    private TableView<TM_Request_BudgetClass> tbl_budget_req;
    @FXML
    private TableColumn<TM_Request_BudgetClass, String> col_req_title;
    @FXML
    private TableColumn<TM_Request_BudgetClass, String> col_priority_level;
    @FXML
    private TableColumn<TM_Request_BudgetClass, String> col_req_amount;
    @FXML
    private TableColumn<TM_Request_BudgetClass, String> col_req_budget_status;
    @FXML
    private JFXButton btn_dt_refresh;
    @FXML
    private JFXButton btn_training_status_refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTrainingRequests();
        ForColumns();
        loadTrainingMngmt();
        loadHistoryOfTraining();
        LoadFacilities();
        LoadVehicles();
        DefaultTrainings();
        int d = tbl_training_req.getItems().size();
        lbl_training_req_notif.setText(String.valueOf(d));
        DisplayDataInCB();
        btn_req_budget.setOnAction(e -> {
            Modal req_b = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml").getParent());
            req_b.open();
        });
        LoadBudget();
        cbox_filter_t_jp.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchDT_Jobs();
        });
        cbox_filter_t_trainor.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchDT_Trainor();
        });
        cbox_tm_dept.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchTM_Dept();
        });
        cbox_tm_trainor.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchTM_Trainor();
        });
        cbox_hs_dept.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchHS_Dept();
        });
        cbox_hs_trainor.getSelectionModel().selectedItemProperty().addListener(listener -> {
            searchHS_Trainor();
        });
        btn_dt_refresh.setOnAction(e -> {
            DefaultTrainings();
        });
    }

    //add training
    @FXML
    public void AddTrainingModal() {
        Modal atm = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_AddTraining.fxml").getParent());
        atm.open();
    }

    public void DisplayDataInCB() {
        HR4_Departments dept = new HR4_Departments();
        HR2_Temp_Employee_Profiles emp = new HR2_Temp_Employee_Profiles();
        HR4_Jobs j = new HR4_Jobs();
        try {
            List c = dept.get();
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                cbox_hs_dept.getItems().add(hm1.get("dept_name"));

            }

            List tm_dept = dept.get();
            for (Object td : tm_dept) {
                HashMap hm3 = (HashMap) td;
                //RS
                cbox_tm_dept.getItems().add(hm3.get("dept_name"));

            }
            List trainors = emp.get();
            for (Object tjp : trainors) {
                HashMap hm4 = (HashMap) tjp;
                //RS
                cbox_tm_trainor.getItems().add(hm4.get("employee_code") + " - " + hm4.get("firstname") + " " + hm4.get("middlename") + " " + hm4.get("lastname"));
                cbox_filter_t_trainor.getItems().add(hm4.get("employee_code") + " - " + hm4.get("firstname") + " " + hm4.get("middlename") + " " + hm4.get("lastname"));
            }
            List trainorsHistory = emp.get();
            for (Object th : trainorsHistory) {
                HashMap hm5 = (HashMap) th;
                //RS
                cbox_hs_trainor.getItems().add(hm5.get("employee_code") + " - " + hm5.get("firstname") + " " + hm5.get("middlename") + " " + hm5.get("lastname"));
            }
            List job_title = j.get();
            for (Object jt : job_title) {
                HashMap hm6 = (HashMap) jt;
                //RS
                cbox_filter_t_jp.getItems().add(hm6.get("title"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void searchDT_Jobs() {
        HR2_TM_DefaultTrainings dt = new HR2_TM_DefaultTrainings();
        List defTrainings = dt.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp", "=", "trainor")
                .where(new Object[][]{
            {"j.title", "=", cbox_filter_t_jp.getSelectionModel().getSelectedItem().toString()},
            {"aerolink.tbl_hr2_default_trainings.isDeleted", "<>", "1"}
        })
                .orderBy("j.title", Model.Sort.ASC)
                .get("dt_id, j.title, training_title, CONCAT(emp.firstname,' ',emp.middlename,' ',emp.lastname)as emp_trainor");

        DisplayDefaultTrainings(defTrainings);
    }

    public void searchDT_Trainor() {
        HR2_TM_DefaultTrainings dt = new HR2_TM_DefaultTrainings();
        List defTrainings = dt.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp", "=", "trainor")
                .where(new Object[][]{
            {"emp.employee_code", "=", cbox_filter_t_trainor.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"aerolink.tbl_hr2_default_trainings.isDeleted", "<>", "1"}
        })
                .orderBy("j.title", Model.Sort.ASC)
                .get("dt_id, j.title, training_title, CONCAT(emp.firstname,' ',emp.middlename,' ',emp.lastname)as emp_trainor");

        DisplayDefaultTrainings(defTrainings);
    }

    public void searchTM_Dept() {
        HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
        List training_req = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .where(new Object[][]{
            {"dept.dept_name", "=", cbox_tm_dept.getSelectionModel().getSelectedItem().toString()},
            {"aerolink.tbl_eis_request_status.req_status_id", "<>", "3"},
            {"ti.isDeleted", "<>", "1"}})
                .orderBy("aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                .get("ti.t_id, aerolink.tbl_hr4_employee_profiles.employee_code,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                        + "aerolink.tbl_hr4_employee_profiles.employee_code, concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                        + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

        DisplayTrainingM(training_req);
    }

    public void searchTM_Trainor() {
        HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
        List training_req = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .where(new Object[][]{
            {"aerolink.tbl_hr4_employee_profiles.employee_code", "=", cbox_tm_trainor.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"aerolink.tbl_eis_request_status.req_status_id", "<>", "3"},
            {"ti.isDeleted", "<>", "1"}})
                .orderBy("aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                .get("ti.t_id, aerolink.tbl_hr4_employee_profiles.employee_code,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                        + "aerolink.tbl_hr4_employee_profiles.employee_code, concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                        + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

        DisplayTrainingM(training_req);
    }

    public void searchHS_Dept() {
        HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
        List training_req_archive = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                .where(new Object[][]{
            {"dept.dept_name", "=", cbox_hs_dept.getSelectionModel().getSelectedItem().toString()},
            {"ti.isDeleted", "=", "1"}
        })
                .orderBy("aerolink.tbl_hr4_employee_profiles.employee_code, aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                .get("ti.t_id,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                        + "concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                        + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

        DisplayHistoryOfTraining(training_req_archive);
    }

    public void searchHS_Trainor() {
        HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
        List training_req = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                .where(new Object[][]{
            {"aerolink.tbl_hr4_employee_profiles.employee_code", "=", cbox_hs_trainor.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"ti.isDeleted", "=", "1"}
        })
                .orderBy("aerolink.tbl_hr4_employee_profiles.employee_code, aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                .get("ti.t_id,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                        + "concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                        + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

        DisplayHistoryOfTraining(training_req);
    }

    //tbl_default_trainings
    public void DefaultTrainings() {
        try {

            HR2_TM_DefaultTrainings dt = new HR2_TM_DefaultTrainings();
            List defTrainings = dt.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp", "=", "trainor")
                    .where(new Object[][]{{"aerolink.tbl_hr2_default_trainings.isDeleted", "<>", "1"}})
                    .orderBy("j.title", Model.Sort.ASC)
                    .get("dt_id, j.title, training_title, CONCAT(emp.firstname,' ',emp.middlename,' ',emp.lastname)as emp_trainor");

            DisplayDefaultTrainings(defTrainings);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void DisplayDefaultTrainings(List m) {
        ObservableList<TM_DefaultTrainings> DTrainings = FXCollections.observableArrayList();
        DTrainings.clear();
        try {

            for (Object d : m) {
                HashMap hm1 = (HashMap) d;
                DTrainings.add(
                        new TM_DefaultTrainings(
                                String.valueOf(hm1.get("dt_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("training_title")),
                                String.valueOf(hm1.get("emp_trainor"))
                        ));
            }

            tbl_default_trainings.setItems(DTrainings);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_default_trainings.getItems().size());
        tbl_default_trainings.getSelectionModel().selectFirst();
    }

    //for training mngmt.
    @FXML
    public void loadTrainingMngmt() {

        try {
            HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
            List training_req = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                    .where(new Object[][]{
                {"aerolink.tbl_eis_request_status.req_status_id", "<>", "3"},
                {"ti.isDeleted", "<>", "1"}})
                    .orderBy("aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                    .get("ti.t_id, aerolink.tbl_hr4_employee_profiles.employee_code,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                            + "aerolink.tbl_hr4_employee_profiles.employee_code, concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                            + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

            DisplayTrainingM(training_req);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @FXML
    public void loadHistoryOfTraining() {

        try {
            HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
            List training_req_archive = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_trainingInfo", "tr_id", "ti", "=", "tr_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "ti", "trainor", true)
                    .where(new Object[][]{{"ti.isDeleted", "<>", "0"}})
                    .orderBy("aerolink.tbl_hr4_employee_profiles.employee_code, aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                    .get("ti.t_id,aerolink.tbl_hr2_training_requisition.tr_id,dept.dept_name,j.title,training_title,no_of_participants,"
                            + "concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname) as trainor,"
                            + "from_day, to_day, aerolink.tbl_eis_request_status.req_status_id, aerolink.tbl_eis_request_status.req_status");

            DisplayHistoryOfTraining(training_req_archive);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void DisplayHistoryOfTraining(List m) {
        ObservableList<HR2_TrainingReq_Class> t_requests = FXCollections.observableArrayList();
        t_requests.clear();
        try {

            for (Object d : m) {
                HashMap hm1 = (HashMap) d;
                System.out.println("TRAINING MANAGEMENT" + d);
                t_requests.add(
                        new HR2_TrainingReq_Class(
                                String.valueOf(hm1.get("tr_id")),
                                String.valueOf(hm1.get("t_id")),
                                String.valueOf(hm1.get("dept_name")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("training_title")),
                                String.valueOf(hm1.get("no_of_participants")),
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("trainor")),
                                String.valueOf(hm1.get("from_day")),
                                String.valueOf(hm1.get("to_day")),
                                String.valueOf(hm1.get("reason")),
                                String.valueOf(hm1.get("req_status_id")),
                                String.valueOf(hm1.get("req_status")),
                                String.valueOf(hm1.get("requested_by")),
                                String.valueOf(hm1.get("date_requested"))
                        ));
            }

            tbl_history_of_trainings.setItems(t_requests);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_history_of_trainings.getItems().size());
        tbl_history_of_trainings.getSelectionModel().selectFirst();
    }

    public void DisplayTrainingM(List m) {
        ObservableList<HR2_TrainingReq_Class> t_requests = FXCollections.observableArrayList();
        t_requests.clear();
        try {

            for (Object d : m) {
                HashMap hm1 = (HashMap) d;
                t_requests.add(
                        new HR2_TrainingReq_Class(
                                String.valueOf(hm1.get("tr_id")),
                                String.valueOf(hm1.get("t_id")),
                                String.valueOf(hm1.get("dept_name")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("training_title")),
                                String.valueOf(hm1.get("no_of_participants")),
                                String.valueOf(hm1.get("employee_code")),
                                String.valueOf(hm1.get("trainor")),
                                String.valueOf(hm1.get("from_day")),
                                String.valueOf(hm1.get("to_day")),
                                String.valueOf(hm1.get("reason")),
                                String.valueOf(hm1.get("req_status_id")),
                                String.valueOf(hm1.get("req_status")),
                                String.valueOf(hm1.get("requested_by")),
                                String.valueOf(hm1.get("date_requested"))
                        ));
            }

            tbl_training_mngmt.setItems(t_requests);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_training_mngmt.getItems().size());
        tbl_training_mngmt.getSelectionModel().selectFirst();
    }

    //for tbl_training_req
    public void loadTrainingRequests() {

        try {
            HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
            List training_req = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                    .where(new Object[][]{
                {"aerolink.tbl_eis_request_status.req_status_id", "=", "3"},
                {"aerolink.tbl_hr2_training_requisition.isDeleted", "<>", "1"}
            })
                    .orderBy("aerolink.tbl_hr2_training_requisition.date_requested", Model.Sort.ASC)
                    .get("tr_id,dept.dept_name, j.title,date_requested,aerolink.tbl_eis_request_status.req_status_id,aerolink.tbl_eis_request_status.req_status");

            DisplayTrainingReq(training_req);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void DisplayTrainingReq(List a) {
        ObservableList<TM_TrainingRequisition_Class> t_requests = FXCollections.observableArrayList();
        t_requests.clear();
        try {
            for (Object d : a) {
                HashMap hm1 = (HashMap) d;

                t_requests.add(
                        new TM_TrainingRequisition_Class(
                                String.valueOf(hm1.get("tr_id")),
                                String.valueOf(hm1.get("dept_name")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("date_requested")),
                                String.valueOf(hm1.get("req_status_id")),
                                String.valueOf(hm1.get("req_status"))
                        ));
            }

            tbl_training_req.setItems(t_requests);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_training_req.getItems().size());
        tbl_training_req.getSelectionModel().selectFirst();
    }

    public void LoadFacilities() {
        try {
            HR2_Temp_Facilities facilities = new HR2_Temp_Facilities();

            List f = facilities.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "ab", "=", "BuildingID")
                    .where(new Object[][]{{"FacilityType", "=", "training"}})
                    .get("FacilityID, FacilityName, FacilityStatus, FacilityRoomNumber, FacilityCapacity, ab.BuildingName");
            DisplayFacilities(f);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void LoadVehicles() {
        try {
            HR2_Temp_Vehicles vehicles = new HR2_Temp_Vehicles();
            List v = vehicles.where(new Object[][]{{"VehicleStatus", "=", "Not in use"}})
                    .get();
            DisplayVehicles(v);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayVehicles(List dv) {
        ObservableList<HR2_Temp_VehicleClass> OLVehicles = FXCollections.observableArrayList();
        OLVehicles.clear();
        try {

            for (Object olf : dv) {
                HashMap hmf = (HashMap) olf;

                OLVehicles.add(
                        new HR2_Temp_VehicleClass(
                                String.valueOf(hmf.get("VehicleID")),
                                String.valueOf(hmf.get("VehicleType")),
                                String.valueOf(hmf.get("VehicleModel")),
                                String.valueOf(hmf.get("VehicleStatus"))
                        ));
            }

            tbl_req_vehicle.setItems(OLVehicles);

        } catch (Exception e) {
            System.out.println(e);
        }
        tbl_req_vehicle.getSelectionModel().selectFirst();
    }

    public void DisplayFacilities(List af) {
        ObservableList<TM_AssetFacilities> OLfacilities = FXCollections.observableArrayList();
        OLfacilities.clear();
        try {

            for (Object olf : af) {
                HashMap hmf = (HashMap) olf;

                OLfacilities.add(
                        new TM_AssetFacilities(
                                String.valueOf(hmf.get("FacilityID")),
                                String.valueOf(hmf.get("FacilityName")),
                                String.valueOf(hmf.get("FacilityRoomNumber")),
                                String.valueOf(hmf.get("FacilityCapacity")),
                                String.valueOf(hmf.get("BuildingName")),
                                String.valueOf(hmf.get("FacilityStatus"))
                        ));
            }

            tbl_req_facility.setItems(OLfacilities);

        } catch (Exception e) {
            System.out.println(e);
        }
        tbl_req_facility.getSelectionModel().selectFirst();
    }

    public void LoadBudget() {
        try {
            Financial_budget_request budget = new Financial_budget_request();

            List b = budget.where(new Object[][]{{"budget_department", "=", "Human Resource - Training Management"}})
                    .orderBy("created_at", Model.Sort.DESC)
                    .get();
            ObservableList<TM_Request_BudgetClass> budget_table = FXCollections.observableArrayList();
            budget_table.clear();

            for (Object budget_training : b) {
                HashMap hmf = (HashMap) budget_training;

                budget_table.add(
                        new TM_Request_BudgetClass(
                                String.valueOf(hmf.get("budget_description")),
                                String.valueOf(hmf.get("budget_priority_lvl")),
                                String.valueOf(hmf.get("budget_amount")),
                                String.valueOf(hmf.get("budget_status"))
                        ));
            }

            tbl_budget_req.setItems(budget_table);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ForColumns() {
        //for tbl_default_trainings
        col_t_jp.setCellValueFactory((TableColumn.CellDataFeatures<TM_DefaultTrainings, String> param) -> param.getValue().job_title);
        col_t_training_title.setCellValueFactory((TableColumn.CellDataFeatures<TM_DefaultTrainings, String> param) -> param.getValue().training_title);
        col_t_trainor.setCellValueFactory((TableColumn.CellDataFeatures<TM_DefaultTrainings, String> param) -> param.getValue().trainor);
        TableColumn<TM_DefaultTrainings, Void> col_btn_viewP = new TableColumn("View Action");

        Callback<TableColumn<TM_DefaultTrainings, Void>, TableCell<TM_DefaultTrainings, Void>> cellViewParticipants
                = new Callback<TableColumn<TM_DefaultTrainings, Void>, TableCell<TM_DefaultTrainings, Void>>() {
            @Override
            public TableCell<TM_DefaultTrainings, Void> call(final TableColumn<TM_DefaultTrainings, Void> param) {

                final TableCell<TM_DefaultTrainings, Void> cellBtn_VP = new TableCell<TM_DefaultTrainings, Void>() {
                    private final Button btn_view_participants = new Button("View Participants");

                    {
                        try {
                            btn_view_participants.setOnAction(e
                                    -> {

                                TM_DefaultTrainings td = (TM_DefaultTrainings) getTableRow().getItem();
                                TM_DT_ViewParticipantsModalClass.init_TM_DT_ViewParticipantsModalClass(
                                        td.dt_id.getValue(),
                                        td.job_title.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_DT_ViewParticipants.fxml").getParent());
                                lq.open();
                            });
                            btn_view_participants.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_view_participants.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_view_participants);
                        }
                    }
                };
                return cellBtn_VP;
            }

        };

        col_btn_viewP.setCellFactory(cellViewParticipants);
        tbl_default_trainings.getColumns().add(col_btn_viewP);
        //for tbl_mngmt
        col_tm_dept.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().dept_name);
        col_tm_jp.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().title);
        col_tm_p.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().no_of_participants);
        col_tm_trainor.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().trainor);
        col_tm_from.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().from_day);
        col_tm_to.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().to_day);
        col_tm_pn_process.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().request_status);
        //for tbl_history_of_trainings
        col_hs_dept.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().dept_name);
        col_hs_jp.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().title);
        col_hs_p.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().no_of_participants);
        col_hs_trainor.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().trainor);
        col_hs_from.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().from_day);
        col_hs_to.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().to_day);
        col_hs_status.setCellValueFactory((TableColumn.CellDataFeatures<HR2_TrainingReq_Class, String> param) -> param.getValue().request_status);
        //for tbl_req
        col_req_dept.setCellValueFactory((TableColumn.CellDataFeatures<TM_TrainingRequisition_Class, String> param) -> param.getValue().dept_name);
        col_req_jp.setCellValueFactory((TableColumn.CellDataFeatures<TM_TrainingRequisition_Class, String> param) -> param.getValue().title);
        col_req_date_req.setCellValueFactory((TableColumn.CellDataFeatures<TM_TrainingRequisition_Class, String> param) -> param.getValue().date_requested);
        col_req_status.setCellValueFactory((TableColumn.CellDataFeatures<TM_TrainingRequisition_Class, String> param) -> param.getValue().status_name);
        TableColumn<TM_TrainingRequisition_Class, Void> MB = new TableColumn("Action");

        Callback<TableColumn<TM_TrainingRequisition_Class, Void>, TableCell<TM_TrainingRequisition_Class, Void>> cellFactory
                = new Callback<TableColumn<TM_TrainingRequisition_Class, Void>, TableCell<TM_TrainingRequisition_Class, Void>>() {
            @Override
            public TableCell<TM_TrainingRequisition_Class, Void> call(final TableColumn<TM_TrainingRequisition_Class, Void> param) {

                final TableCell<TM_TrainingRequisition_Class, Void> cell = new TableCell<TM_TrainingRequisition_Class, Void>() {
                    private final Button more_btn = new Button("More");

                    {
                        try {
                            more_btn.setOnAction(e
                                    -> {

                                TM_TrainingRequisition_Class tr1 = (TM_TrainingRequisition_Class) getTableRow().getItem();
                                TM_ViewTrainingReqClassModal.initVTRClass(
                                        tr1.tr_id.getValue(),
                                        tr1.dept_name.getValue(),
                                        tr1.title.getValue(),
                                        tr1.date_requested.getValue(),
                                        tr1.status_id.getValue(),
                                        tr1.status_name.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewTrainingRequest.fxml").getParent());
                                lq.open();
                            });
                            more_btn.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            more_btn.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(more_btn);
                        }
                    }
                };
                return cell;
            }

        };

        MB.setCellFactory(cellFactory);
        tbl_training_req.getColumns().add(MB);
        //for tbl facilities
        col_req_facility.setCellValueFactory((TableColumn.CellDataFeatures<TM_AssetFacilities, String> param) -> param.getValue().facilityName);
        col_f_roomNumber.setCellValueFactory((TableColumn.CellDataFeatures<TM_AssetFacilities, String> param) -> param.getValue().facilityRoomNumber);
        col_f_capacity.setCellValueFactory((TableColumn.CellDataFeatures<TM_AssetFacilities, String> param) -> param.getValue().facilityCapacity);
        col_building.setCellValueFactory((TableColumn.CellDataFeatures<TM_AssetFacilities, String> param) -> param.getValue().BuildingName);
        col_facilityStatus.setCellValueFactory((TableColumn.CellDataFeatures<TM_AssetFacilities, String> param) -> param.getValue().facilityStatus);
        TableColumn<TM_AssetFacilities, Void> col_btn_viewFacility = new TableColumn("View Action");

        Callback<TableColumn<TM_AssetFacilities, Void>, TableCell<TM_AssetFacilities, Void>> cellFacilityDetails
                = new Callback<TableColumn<TM_AssetFacilities, Void>, TableCell<TM_AssetFacilities, Void>>() {
            @Override
            public TableCell<TM_AssetFacilities, Void> call(final TableColumn<TM_AssetFacilities, Void> param) {

                final TableCell<TM_AssetFacilities, Void> cellBtn_VF = new TableCell<TM_AssetFacilities, Void>() {
                    private final Button btn_view_facility_details = new Button("View Details");

                    {
                        try {
                            btn_view_facility_details.setOnAction(e
                                    -> {

                                TM_AssetFacilities af = (TM_AssetFacilities) getTableRow().getItem();
                                TM_FacilityDetailsClass_for_Modal.FacilityDetails(
                                        af.facilityID.getValue(),
                                        af.facilityName.getValue(),
                                        af.facilityRoomNumber.getValue(),
                                        af.facilityCapacity.getValue(),
                                        af.BuildingName.getValue(),
                                        af.facilityStatus.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewFacilityDetails.fxml").getParent());
                                lq.open();
                            });
                            btn_view_facility_details.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_view_facility_details.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_view_facility_details);
                        }
                    }
                };
                return cellBtn_VF;
            }

        };

        col_btn_viewFacility.setCellFactory(cellFacilityDetails);
        tbl_req_facility.getColumns().add(col_btn_viewFacility);

        TableColumn<TM_AssetFacilities, Void> col_btn_facility = new TableColumn("Request Facility");

        Callback<TableColumn<TM_AssetFacilities, Void>, TableCell<TM_AssetFacilities, Void>> cellFacility
                = new Callback<TableColumn<TM_AssetFacilities, Void>, TableCell<TM_AssetFacilities, Void>>() {
            @Override
            public TableCell<TM_AssetFacilities, Void> call(final TableColumn<TM_AssetFacilities, Void> param) {

                final TableCell<TM_AssetFacilities, Void> cellBtn_F = new TableCell<TM_AssetFacilities, Void>() {
                    private final Button btn_facility = new Button("Request");

                    {
                        try {
                            btn_facility.setOnAction(e
                                    -> {

                                TM_AssetFacilities af = (TM_AssetFacilities) getTableRow().getItem();
                                TM_FacilityDetailsClass_for_Modal.FacilityDetails(
                                        af.facilityID.getValue(),
                                        af.facilityName.getValue(),
                                        af.facilityRoomNumber.getValue(),
                                        af.facilityCapacity.getValue(),
                                        af.BuildingName.getValue(),
                                        af.facilityStatus.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewFacilityDetails.fxml").getParent());
                                lq.open();
                            });
                            btn_facility.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_facility.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_facility);
                        }
                    }
                };
                return cellBtn_F;
            }

        };

        col_btn_facility.setCellFactory(cellFacility);
        tbl_req_facility.getColumns().add(col_btn_facility);

        //for tbl_vehicles
        col_req_vehicleType.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Temp_VehicleClass, String> param) -> param.getValue().vehicle_type);
        col_req_vehicleModel.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Temp_VehicleClass, String> param) -> param.getValue().vehicle_model);
        col_req_vehicleStatus.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Temp_VehicleClass, String> param) -> param.getValue().vehicleStatus);

        TableColumn<HR2_Temp_VehicleClass, Void> col_btn_vehicles = new TableColumn("Request Vehicle");

        Callback<TableColumn<HR2_Temp_VehicleClass, Void>, TableCell<HR2_Temp_VehicleClass, Void>> cellVehicle
                = new Callback<TableColumn<HR2_Temp_VehicleClass, Void>, TableCell<HR2_Temp_VehicleClass, Void>>() {
            @Override
            public TableCell<HR2_Temp_VehicleClass, Void> call(final TableColumn<HR2_Temp_VehicleClass, Void> param) {

                final TableCell<HR2_Temp_VehicleClass, Void> cellBtn_V = new TableCell<HR2_Temp_VehicleClass, Void>() {
                    private final Button btn_req_vehicle = new Button("Request");

                    {
                        try {
                            btn_req_vehicle.setOnAction(e
                                    -> {

                                HR2_Temp_VehicleClass tvc = (HR2_Temp_VehicleClass) getTableRow().getItem();
                                TM_VehicleDetailsClassModal.initVD(
                                        tvc.vehicle_id.getValue(),
                                        tvc.vehicle_type.getValue(),
                                        tvc.vehicle_model.getValue(),
                                        tvc.vehicleStatus.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewFacilityDetails.fxml").getParent());
                                lq.open();
                            });
                            btn_req_vehicle.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_req_vehicle.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_req_vehicle);
                        }
                    }
                };
                return cellBtn_V;
            }

        };

        col_btn_vehicles.setCellFactory(cellVehicle);
        tbl_req_vehicle.getColumns().add(col_btn_vehicles);
        //tbl_budget
        col_req_title.setCellValueFactory((TableColumn.CellDataFeatures<TM_Request_BudgetClass, String> param) -> param.getValue().Request_Title);
        col_priority_level.setCellValueFactory((TableColumn.CellDataFeatures<TM_Request_BudgetClass, String> param) -> param.getValue().Priority_Level);
        col_req_amount.setCellValueFactory((TableColumn.CellDataFeatures<TM_Request_BudgetClass, String> param) -> param.getValue().Amount);
        col_req_budget_status.setCellValueFactory((TableColumn.CellDataFeatures<TM_Request_BudgetClass, String> param) -> param.getValue().Status);
    }

    @FXML
    public void ContextMenu(MouseEvent event) {

        if (event.getButton() == MouseButton.SECONDARY) {
            CMenu.show(tbl_training_mngmt, event.getX(), event.getSceneY());
            MI_more.setOnAction(e
                    -> {

                HR2_TM_ViewTrainingInfo_Modal.init_Trainings(
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().t_id.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().tr_id.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().dept_name.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().title.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().no_of_participants.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().employee_code.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().trainor.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().from_day.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().to_day.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().request_status_id.getValue(),
                        tbl_training_mngmt.getSelectionModel().getSelectedItem().request_status.getValue()
                );
                Modal moreDetails = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewTraining.fxml").getParent());
                moreDetails.open();
            }
            );
            MI_archive.setOnAction(e -> {
                Alert update = new Alert(Alert.AlertType.CONFIRMATION);
                update.setContentText("Are you sure you want to archive this data?");
                Optional<ButtonType> rs = update.showAndWait();

                if (rs.get() == ButtonType.OK) {
                    HR2_TM_TrainingInfo tif = new HR2_TM_TrainingInfo();

                    Boolean a = tif.where(new Object[][]{
                        {"t_id", "=", tbl_training_mngmt.getSelectionModel().getSelectedItem().t_id.get()}
                    }).update(new Object[][]{
                        {"isDeleted", "1"},}).executeUpdate();
                    Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
                    dropnotif.setContentText("Successfully Archived");
                    dropnotif.showAndWait();
                    loadTrainingMngmt();
                    loadHistoryOfTraining();
                }
            });
        }

    }

    @FXML
    public void ContextMenuForHistory(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            hs_contextMenu.show(tbl_history_of_trainings, event.getX(), event.getSceneY());
            mi_view_hs_training.setOnAction(e
                    -> {

                HR2_TM_ViewTrainingInfo_Modal.init_Trainings(
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().t_id.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().tr_id.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().dept_name.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().title.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().no_of_participants.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().employee_code.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().trainor.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().from_day.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().to_day.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().request_status_id.getValue(),
                        tbl_history_of_trainings.getSelectionModel().getSelectedItem().request_status.getValue()
                );
                Modal moreDetails = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewHistoryyOfTraining.fxml").getParent());
                moreDetails.open();
            }
            );

        }
    }

}
