/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.ClassFiles.HR4_MIZ;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import FXMLS.HR4.Model.HR4_ClassificationModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import FXMLS.HR4.Model.HR4_Jobs;
import FXMLS.HR4.Model.HR4_Status;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_Core_Human_Capital_ManagementController implements Initializable {

    @FXML
    private TableView<TableModel_Jobs> tbl_jobs;
    @FXML
    private JFXButton btnNewJob;

    @FXML
    private ContextMenu contextMenuJobs;
    ObservableList<TableModel_Jobs> obj = FXCollections.observableArrayList();
    ObservableList<HR4_EmpInfoClass> obj1 = FXCollections.observableArrayList();
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);
    HR4_Jobs jobs = new HR4_Jobs();
    HR4_EmployeeInfo emp = new HR4_EmployeeInfo();
    Boolean searchStatus = false;
    

    @FXML
    private JFXButton btnNewDept;
    @FXML
    private JFXTextField Search_Job;
    @FXML
    private TableView<HR4_EmpInfoClass> tbl_chc;
    @FXML
    private ComboBox statcb;
    @FXML
    private ComboBox ckasscb;
    @FXML
    private JFXTextField srch1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO -> TRYING TO ENTER THE QUANTUM REALM (https://youtu.be/nN6VR92V70M)
        //For Jobs
        weabo();
        weabo1();
        statcb.getSelectionModel().selectedItemProperty().addListener(listener -> {
            DummyCount = 0;
            GlobalCount = 0;

        });
        ckasscb.getSelectionModel().selectedItemProperty().addListener(listener -> {
            DummyCount = 0;
            GlobalCount = 0;
        });

        this.generateTable();
        this.populateTable();

        tbl_jobs.setContextMenu(contextMenuJobs);
        //SearchOfJob
        obj.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tbl_jobs.setItems(obj);
        });
        tbl_jobs.setContextMenu(contextMenuJobs);

        Search_Job.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!Search_Job.getText().equals("")) {
                searchStatus = true;
                SearchJOB();
            }
        });
        ///////2ndobj

        obj1.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tbl_chc.setItems(obj1);
        });

        srch1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Search();
        });
        srch1.setOnMouseClicked(event -> Search());

        this.generateTable1();
        this.populateTable1();

    }

    public void weabo1() {
        HR4_ClassificationModel cc = new HR4_ClassificationModel();
        try {
            List bo2 = cc.get();
            for (Object bo : bo2) {
                HashMap ka = (HashMap) bo;
                ckasscb.getItems().add(ka.get("class_name"));
            }

            ckasscb.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void weabo() {
        HR4_Status st = new HR4_Status();
        try {
            List bo2 = st.get();
            for (Object bo : bo2) {
                HashMap ka = (HashMap) bo;
                statcb.getItems().add(ka.get("status_name"));
            }

            statcb.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void SearchJOB() {

        tbl_jobs.getItems().clear();
        List rs = jobs
                .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_job_classifications", "id", "tblC", "=", "classification_id")
                .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_job_designations", "id", "tblDD", "=", "designation_id")
                .where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.title", "like", "%" + Search_Job.getText() + "%"}
        })
                .get(
                        "title",
                        "description",
                        "tblD.dept_name as department",
                        "tblC.class_name as classification",
                        "tblDD.designation as designation");
        this.AddJobToTable(rs);
    }

    public void Search() {

        tbl_chc.getItems().clear();
        List rs = emp
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employeeStatus", "status_id", "st", "=", "status_id")
                .where(new Object[][]{{"tblDD.lastname", "like", "%" + srch1.getText() + "%"}
        })
                .get(
                        "tblD.employee_code",
                        "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnn",
                        "aerolink.tbl_hr4_jobs.title as job_id",
                        "aerolink.tbl_hr4_department.dept_name as dept_id",
                        "st.status_name as status_id");
        this.AddJobToTable1(rs);
    }

    public void generateTable() {

        tbl_jobs.getItems().clear();
        tbl_jobs.getColumns().removeAll(tbl_jobs.getColumns());
        TableColumn<TableModel_Jobs, String> title = new TableColumn<>("Title");
        TableColumn<TableModel_Jobs, String> description = new TableColumn<>("Description");
        TableColumn<TableModel_Jobs, String> department = new TableColumn<>("Department");
        TableColumn<TableModel_Jobs, String> classification = new TableColumn<>("Classification");
        TableColumn<TableModel_Jobs, String> designation = new TableColumn<>("Designation");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        title.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().title);
        description.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().description);
        department.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().department);
        classification.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().classification);
        designation.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().designation);

        //</editor-fold>
        tbl_jobs.getColumns().addAll(title, description, department, classification, designation);
    }

    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4chc")) {
                try {
                    jobs.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if ((DummyCount != GlobalCount) && !searchStatus ) {
                        List rs = jobs
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_classifications", "id", "tblC", "=", "classification_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_designations", "id", "tblDD", "=", "designation_id")
                                .where(new Object[][]{{"tblC.class_name", "=", ckasscb.getSelectionModel().getSelectedItem().toString()}})
                                .get(
                                        "title",
                                        "description",
                                        "tblD.dept_name as department",
                                        "tblC.class_name as classification",
                                        "tblDD.designation as designation");
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
        tbl_jobs.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String id = String.valueOf(crow.get("job_id"));
            String department = (String) crow.get("department");
            String title1 = (String) crow.get("title");
            String description = (String) crow.get("description");
            String classification = (String) crow.get("classification");
            String designation = (String) crow.get("designation");
            obj.add(new TableModel_Jobs(id, department, title1, description, classification, designation));
        }
        tbl_jobs.setItems(obj);
    }

    //2nd
    public void generateTable1() {

        tbl_chc.getItems().clear();
        tbl_chc.getColumns().removeAll(tbl_chc.getColumns());
        TableColumn<HR4_EmpInfoClass, String> employee_code = new TableColumn<>("EE/ER Code");
        TableColumn<HR4_EmpInfoClass, String> fnn = new TableColumn<>("Fullname");
        TableColumn<HR4_EmpInfoClass, String> job_id = new TableColumn<>("Position");
        TableColumn<HR4_EmpInfoClass, String> dept_id = new TableColumn<>("Department");
        TableColumn<HR4_EmpInfoClass, String> status_id = new TableColumn<>("Status");
        TableColumn More = new TableColumn<>("More Options");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().employee_code);
        fnn.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().fnn);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().job_id);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().dept_id);
        status_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().status_id);

        More.setSortable(false);

        More.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HR4_EmpInfoClass, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<HR4_EmpInfoClass, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });

        More.setCellFactory(new Callback<TableColumn<HR4_EmpInfoClass, Boolean>, TableCell<HR4_EmpInfoClass, Boolean>>() {
            @Override
            public TableCell<HR4_EmpInfoClass, Boolean> call(TableColumn<HR4_EmpInfoClass, Boolean> param) {
                return new TableCell<HR4_EmpInfoClass, Boolean>() {
                    private final Button btnMore = new Button("More");

                    {
                        btnMore.setOnAction(value -> {
                            Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_InfoCHC.fxml").getParent());
                            md.open();
                        });
                    }

                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnMore);
                        }
                    }
                };
            }

        });
        //</editor-fold>
        tbl_chc.getColumns()
                .addAll(employee_code, fnn, job_id, dept_id, status_id, More);
    }
    /*
    
    System.err.println("a");
                        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_InfoCHC.fxml").getParent());
                        md.open();
    
    TableColumn<HR2_ExaminationClass, Void> listOfQuestions = new TableColumn("List of Questions");

        Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>>() {
            @Override
            public TableCell<HR2_ExaminationClass, Void> call(final TableColumn<HR2_ExaminationClass, Void> param) {

                final TableCell<HR2_ExaminationClass, Void> cell = new TableCell<HR2_ExaminationClass, Void>() {
                    private final Button btn_q_list = new Button("List of Questions");

                    {
                        try {
                            btn_q_list.setOnAction(e
                                    -> {
                                HR2_LMClass_For_AddQuestion_Modal.initCourseTitle(
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_id.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_name.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_desc.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().id.get());
                                Modal md = Modal.getInstance(new Form("/FXMLS/HR2/Modals/List_Of_Questions.fxml").getParent());
                                md.open();
                            });
                            btn_q_list.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_q_list.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_q_list);
                        }
                    }
                };
                return cell;
            }

        };

        listOfQuestions.setCellFactory(cellFactory);
        tbl_exam.getColumns().add(listOfQuestions);
     */

    long DummyCount = 0;
    long GlobalCount = 0;

    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4chc")) {
                try {
                    emp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_chc.getItems();
                        List rs = emp
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employeeStatus", "status_id", "st", "=", "status_id")
                                .where(new Object[][]{{"st.status_name", "=", statcb.getSelectionModel().getSelectedItem().toString()}
                        })
                                .get(
                                        "tblD.employee_code",
                                        "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnn",
                                        "aerolink.tbl_hr4_jobs.title as job_id",
                                        "aerolink.tbl_hr4_department.dept_name as dept_id",
                                        "st.status_name as status_id"
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
        tbl_chc.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String employee_code = String.valueOf(crow.get("employee_code"));
            String fnn = (String) crow.get("fnn");
            String job_id = (String) crow.get("job_id");
            String dept_id = (String) crow.get("dept_id");
            String status_id = (String) crow.get("status_id");
            obj1.add(new HR4_EmpInfoClass(employee_code, fnn, job_id, dept_id, status_id));
        }

        tbl_chc.setItems(obj1);

    }

    @FXML
    private void OpenModalNewJob(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewJob.fxml").getParent());
        md.open();

    }

    private void OpenModalInfo(ActionEvent event) {
        Modal md11 = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_InfoCHC.fxml").getParent());
        md11.open();

    }

    int click_count = 0;
    int current_row = 0;

    public void viewJob() {
        HR4_MIZ.init_viewJob(tbl_jobs.getSelectionModel().getSelectedItem().id.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().title.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().description.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().department.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().designation.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().classification.get());

        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_ViewJob.fxml").getParent());
        md.open();

    }

    @FXML
    private void viewRow(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() >= 2) {
                viewJob();
            }
        }

        if (event.getButton() == MouseButton.SECONDARY) {
            contextMenuJobs.show(tbl_jobs, event.getX(), event.getSceneY());
        }

    }

    @FXML
    private void OpenModalNewDept(ActionEvent event) {
        Modal dd = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewDept.fxml").getParent());
        dd.open();

    }

    /*@FXML
    private void deletebtn(){
    
    try{
    jobs.delete().where(new Object[][]{
    
        {"id", "=",()}
    }).executeUpdate();
    
    }catch(Exception e){
        e.printStackTrace();
    }
     
        this.generateTable();
        this.populateTable();
    }*/
}
