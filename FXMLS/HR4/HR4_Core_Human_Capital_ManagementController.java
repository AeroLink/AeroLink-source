/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_EmpInfoFillClass;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.Filler.HR4_MIZ;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import FXMLS.HR4.Filler.HR4_EmpInfoFill;
import FXMLS.HR4.Model.HR4_ClassificationModel;
import FXMLS.HR4.Model.HR4_InfoChartModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import FXMLS.HR4.Model.HR4_Jobs;
import FXMLS.HR4.Model.HR4_Status;
import Model.HR4_Classification;
import Model.HR4_Departments;
import Model.HR4_Designation;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_Core_Human_Capital_ManagementController implements Initializable {

    ObservableList<TableModel_Jobs> obj = FXCollections.observableArrayList();
    ObservableList<HR4_EmpInfoClass> obj1 = FXCollections.observableArrayList();
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);
    HR4_Jobs jobs = new HR4_Jobs();
    HR4_EmployeeInfo emp = new HR4_EmployeeInfo();
    Boolean searchStatus = false;

    HR4_Departments dept = new HR4_Departments();
    HR4_Classification classification = new HR4_Classification();

    @FXML
    private TableView<TableModel_Jobs> tbl_jobs;
    @FXML
    private JFXButton btnNewJob;
    @FXML
    private ContextMenu contextMenuJobs;

    @FXML
    private JFXButton btnNewDept;
    @FXML
    private TableView<HR4_EmpInfoClass> tbl_chc;
    @FXML
    private ComboBox statcb;

    private ComboBox ckasscb;
    @FXML
    private JFXTextField srch1;
    @FXML
    private PieChart pieChartGender;
    private final ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
    @FXML
    private PieChart pieChartDept;
    private final ObservableList<PieChart.Data> piee = FXCollections.observableArrayList();
    @FXML
    private PieChart pieChartAge;
    @FXML
    private JFXButton btnNewEmployee;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private CustomTextField txtSearch;
    @FXML
    private JFXCheckBox chkFilter;
    @FXML
    private ComboBox btnDepts;
    @FXML
    private ComboBox btnClassifications;
    @FXML
    private JFXButton btnRefresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //re write all codes of HR4 CHC 
        //kapag mag cocode ka ayusin mo ang naming convention mo para di ka naliligaw kapag nag dedebug ka

        this.generateTable();
        this.populateTable();

        //creating combo box list for filter
        this.createComboBoxList();

        //refresh button -> if atat na atat na ang user mag refresh
        this.btnRefresh.setOnAction(value -> {
            GlobalCount = 0;
        });

        //filtering checkbox toggle
        this.chkFilter.setOnAction(value -> {
            if (chkFilter.isSelected()) {
                btnClassifications.setDisable(false);
                btnDepts.setDisable(false);
            } else {
                btnClassifications.setDisable(true);
                btnDepts.setDisable(true);
            }
        });

    }

    public void createComboBoxList() {
        List dept_list = dept.get();
        List classification_list = classification.get();

        btnDepts.getItems().add("DEPT00A - ALL DEPARTMENTS");
        btnClassifications.getItems().add("CLASS00A - ALL CLASSIFICATIONS");

        for (Object o : dept_list) {
            HashMap hm = (HashMap) o;
            btnDepts.getItems().add("DEPT00" + hm.get("id") + " - " + hm.get("dept_name"));
        }

        for (Object o : classification_list) {
            HashMap hm = (HashMap) o;
            btnClassifications.getItems().add("CLASS00" + hm.get("id") + " - " + hm.get("class_name"));
        }

        btnDepts.getSelectionModel().selectFirst();
        btnClassifications.getSelectionModel().selectFirst();

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

    /*public void SearchJOB() {

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
    }*/
    HR4_InfoChartModel gcm = new HR4_InfoChartModel();

    public void Piechart1() {

        pie.clear();
        List<HashMap> list3x = gcm
                .groupBy("gender")
                .get("gender,COUNT(gender) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("gender").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        pieChartGender.setData(pie);
        pieChartGender.setLegendSide(Side.BOTTOM);
        pieChartGender.setStartAngle(90);

    }

    public void Piechart2() {
        HR4_Jobs jobs = new HR4_Jobs();
        piee.clear();
        List<HashMap> list3x = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "dept_id")
                .groupBy("dept_name")
                .get("dept_name, COUNT(dept_name) as Total");
        list3x.forEach((row) -> {
            piee.add(new PieChart.Data(row.get("dept_name").toString(),
                    Double.parseDouble(row.get("Total").toString())));

        });
        pieChartDept.setData(piee);
        pieChartDept.setLegendSide(Side.BOTTOM);
        pieChartDept.setStartAngle(90);

    }

    public void Piechart3() {
        HR4_Jobs jobs = new HR4_Jobs();
        piee.clear();
        List<HashMap> list3x = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "dept_id")
                .groupBy("dept_name")
                .get("dept_name, COUNT(dept_name) as Total");
        list3x.forEach((row) -> {
            piee.add(new PieChart.Data(row.get("dept_name").toString(),
                    Double.parseDouble(row.get("Total").toString())));

        });
        pieChartDept.setData(piee);
        pieChartDept.setLegendSide(Side.BOTTOM);
        pieChartDept.setStartAngle(90);

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

                    if (DummyCount != GlobalCount) {

                        Model builder = jobs
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_classifications", "id", "tblC", "=", "classification_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_designations", "id", "tblDD", "=", "designation_id");

                        if (chkFilter.isSelected()) {
                            String dept_id = btnDepts.getSelectionModel().getSelectedItem().toString().split(" - ")[0].replace("DEPT00", "");
                            String class_id = btnClassifications.getSelectionModel().getSelectedItem().toString().split(" - ")[0].replace("CLASS00", "");

                            builder = builder.where(new Object[][]{
                                {"tblD.id", (dept_id.equals("A") ? "like" : "="), dept_id.equals("A") ? "%%" : dept_id},
                                {"tblC.id", (class_id.equals("A") ? "like" : "="), class_id.equals("A") ? "%%" : class_id}
                            });
                        }
                        List rs = builder
                                .get(
                                        "job_id",
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
        tbl_jobs.getItems().clear();
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

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().employee_code);
        fnn.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().fnn);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().job_id);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().dept_id);
        status_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().status_id);

        TableColumn<HR4_EmpInfoClass, Void> addButton = new TableColumn("More Options");

        Callback<TableColumn<HR4_EmpInfoClass, Void>, TableCell<HR4_EmpInfoClass, Void>> cellFactory
                = new Callback<TableColumn<HR4_EmpInfoClass, Void>, TableCell<HR4_EmpInfoClass, Void>>() {
            @Override
            public TableCell<HR4_EmpInfoClass, Void> call(final TableColumn<HR4_EmpInfoClass, Void> param) {

                final TableCell<HR4_EmpInfoClass, Void> cell = new TableCell<HR4_EmpInfoClass, Void>() {
                    private final Button btn = new Button("Request");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                HR4_EmpInfoFillClass fc = (HR4_EmpInfoFillClass) getTableRow().getItem();

                                HR4_EmpInfoFill.CreateNew(
                                        fc.a.getValue(),
                                        fc.b.getValue(),
                                        fc.c.getValue(),
                                        fc.d.getValue(),
                                        fc.e.getValue(),
                                        fc.f.getValue(),
                                        fc.g.getValue(),
                                        fc.h.getValue(),
                                        fc.i.getValue(),
                                        fc.j.getValue(),
                                        fc.k.getValue(),
                                        fc.l.getValue(),
                                        fc.m.getValue(),
                                        fc.n.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_InfoCHC.fxml").getParent());
                                lq.open();
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
        tbl_chc.getColumns().add(addButton);
        //</editor-fold>
        tbl_chc.getColumns()
                .addAll(employee_code, fnn, job_id, dept_id, status_id);
    }

    long DummyCount = 0;
    long GlobalCount = 0;

    long DummyCountCombo = 0;
    long GlobalCountCombo = 0;

    long DummyCountCombo2 = 0;
    long GlobalCountCombo2 = 0;

    long DummyCount2 = 0;
    long GlobalCount2 = 0;

    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4chc")) {
                try {
                    emp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount2 != GlobalCount2) {

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
                        GlobalCount2 = DummyCount2;
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
        tbl_chc.getItems().clear();

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
        System.out.println("jobID : " + tbl_jobs.getSelectionModel().getSelectedItem().id.getValue());
        HR4_MIZ.init_viewJob(tbl_jobs.getSelectionModel().getSelectedItem().id.getValue(),
                tbl_jobs.getSelectionModel().getSelectedItem().title.getValue(),
                tbl_jobs.getSelectionModel().getSelectedItem().description.getValue(),
                tbl_jobs.getSelectionModel().getSelectedItem().department.getValue(),
                tbl_jobs.getSelectionModel().getSelectedItem().designation.getValue(),
                tbl_jobs.getSelectionModel().getSelectedItem().classification.getValue());

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
