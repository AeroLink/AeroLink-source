/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.ClassFiles.HR4_Benefits1Class;
import FXMLS.HR4.ClassFiles.HR4_BenefitsClass;
import FXMLS.HR4.ClassFiles.HR4_Deductions1Class;
import FXMLS.HR4.ClassFiles.HR4_DeductionsClass;
import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass;
import FXMLS.HR4.ClassFiles.HR4_PHClass;
import FXMLS.HR4.ClassFiles.HR4_SSSClass;
import FXMLS.HR4.ClassFiles.HR4_SalaryClass;
import FXMLS.HR4.ClassFiles.HR4_SalaryGradeUpClass;
import FXMLS.HR4.ClassFiles.HR4_TaxClass;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import FXMLS.HR4.Filler.HR4_DeducBenefitsFill;
import FXMLS.HR4.Filler.HR4_DeductionsFill;
import FXMLS.HR4.Filler.HR4_NewCompensationFill;
import static FXMLS.HR4.Filler.HR4_NewCompensationFill.idxx;
import FXMLS.HR4.Filler.HR4_NewPayrollFill;
import FXMLS.HR4.Filler.HR4_NewPayrollFill2;
import FXMLS.HR4.Model.HR4_Benefits1Model;
import FXMLS.HR4.Model.HR4_BenefitsModel;
import FXMLS.HR4.Model.HR4_Deductions1Model;
import FXMLS.HR4.Model.HR4_DeductionsModel;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import FXMLS.HR4.Model.HR4_PHModel;
import FXMLS.HR4.Model.HR4_PayrollEmployeeModel;
import FXMLS.HR4.Model.HR4_SSSModel;
import FXMLS.HR4.Model.HR4_SalaryGradeUpModel;
import FXMLS.HR4.Model.HR4_SalaryModel;
import FXMLS.HR4.Model.HR4_TaxModel;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_Compensation_and_PlanningController implements Initializable {
    String[] StepCbx = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8"};
    ObservableList<HR4_SalaryGradeUpClass> obj = FXCollections.observableArrayList();
    ObservableList<HR4_SalaryClass> obj1 = FXCollections.observableArrayList();
    ObservableList<HR4_BenefitsClass> obj2 = FXCollections.observableArrayList();
    ObservableList<HR4_DeductionsClass> obj3 = FXCollections.observableArrayList();
    HR4_SalaryGradeUpModel sgm = new HR4_SalaryGradeUpModel();
    HR4_SalaryModel sm = new HR4_SalaryModel();
    HR4_BenefitsModel bm = new HR4_BenefitsModel();
    Boolean searchStatus = false;
    HR4_DeductionsModel dm = new HR4_DeductionsModel();
    @FXML
    private TableView<HR4_SalaryGradeUpClass> tbl_salary_up;
    @FXML
    private TableView<HR4_SalaryClass> tbl_salarygrade;
    @FXML
    private TableView<HR4_BenefitsClass> tbl_benefits;
    @FXML
    private JFXButton AddNewBenefitsBtn;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    @FXML
    private ComboBox<String> stepcombobox;
    @FXML
    private ComboBox<String> gradecombobox;
    @FXML
    private TableView<HR4_Benefits1Class> tbl_benEmp;
    ObservableList<HR4_Benefits1Class> data;
    @FXML
    private TableView<HR4_DeductionsClass> tbl_selection_deducs;
    @FXML
    private TableView<HR4_Deductions1Class> tbl_deducs;
    @FXML
    private JFXButton AddDeductionBtn;

    
    public void initialize(URL url, ResourceBundle rb) {
    this.generateTable();
    this.populateTable();
    this.generateTable1();
    this.populateTable1();
    this.generateTable2();
    this.populateTable2();
    this.generateTable1010();
    this.populateDeductionsTable();
    this.generateDeductionsTable();
    this.generateDeductionsTable1();
    
            AddDeductionBtn.setOnMouseClicked(e -> {
            AddNewDeductionsModal();
            });
    
            tbl_benefits.getSelectionModel().selectFirst();
            tbl_benefits.setOnMouseClicked(e -> {
            populate();
            });

            tbl_selection_deducs.getSelectionModel().selectFirst();
            tbl_selection_deducs.setOnMouseClicked(e -> {
            populateDeductionsTable1();
            });
            
    for (String str : StepCbx) {
        stepcombobox.getItems().add(str);
    }
    
    gradecombobox.getSelectionModel().selectedItemProperty().addListener(listener -> {
        GlobalCount = 0;
        DummyCount = 0;
    });
    
    tbl_salary_up.setOnMouseClicked(e -> {
        HR4_SalaryGradeUpClass r = tbl_salary_up.getSelectionModel().getSelectedItem();
            a.setText(r.getEmpCode());
            b.setText(r.getFN());
            c.setText(r.getJobs());
            d.setText(r.getDept());
    });
    AddNewBenefitsBtn.setOnMouseClicked(e -> BenefitsModal()); 
    }
    public void AddNewDeductionsModal(){
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_AddNewDeductions.fxml").getParent());
        md.open();
    }
    public void BenefitsModal(){
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewBenefits.fxml").getParent());
        md.open();
    }
    
    public void generateTable() {

        tbl_salary_up.getItems().clear();
        tbl_salary_up.getColumns().removeAll(tbl_salary_up.getColumns());
        TableColumn<HR4_SalaryGradeUpClass, String> emp_code = new TableColumn<>("Employee Code");
        TableColumn<HR4_SalaryGradeUpClass, String> fnn = new TableColumn<>("Full Name");
        TableColumn<HR4_SalaryGradeUpClass, String> grade = new TableColumn<>("Grade");
        TableColumn<HR4_SalaryGradeUpClass, String> status = new TableColumn<>("Status");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        emp_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryGradeUpClass, String> param) -> param.getValue().a);
        fnn.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryGradeUpClass, String> param) -> param.getValue().d);
        grade.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryGradeUpClass, String> param) -> param.getValue().b);
        status.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryGradeUpClass, String> param) -> param.getValue().c);
        TableColumn<HR4_SalaryGradeUpClass, Boolean> btnAction = new TableColumn<>("Actions");
        btnAction.setSortable(false);

        btnAction.setCellFactory((TableColumn<HR4_SalaryGradeUpClass, Boolean> param) -> new TableCell<HR4_SalaryGradeUpClass, Boolean>() {

            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            private final MenuButton btn = new MenuButton("Actions", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");
                
                MenuItem view = new MenuItem("View");
                FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                fx.getStyleClass().add("fontIconMenu");
                view.setGraphic(fx);
                view.setOnAction(event -> {
                });
                MenuItem reject = new MenuItem("Declined");
                FontAwesomeIconView fx1 = new FontAwesomeIconView(FontAwesomeIcon.HAND_ALT_DOWN);
                fx1.getStyleClass().add("fontIconMenu");
                reject.setGraphic(fx1);
                reject.setOnAction(event -> {
                
                });
                btn.getItems().add(view);
                btn.getItems().add(reject);
            }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(btn);
                } else {
                    setGraphic(null);
                }
            }

        });

        btnAction.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryGradeUpClass, Boolean> param) -> new SimpleBooleanProperty(false));

        //</editor-fold>
        tbl_salary_up.getColumns().addAll(emp_code, fnn, grade, status,btnAction);
    }
    
    long DummyCount = 0;
    long GlobalCount = 0;
    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    sgm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount != GlobalCount){    
                        tbl_salary_up.getItems();
                        List rs = sgm
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "emp_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "emp_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                 .get(
                                            "tblD.employee_code as emp_code",
                                            "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnn",
                                            "aerolink.tbl_hr4_jobs.title as job_id",
                                            "aerolink.tbl_hr4_department.dept_name as dept_id",
                                            "grade",
                                            "status"
                                            );
                        AddJobToTable(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Compensation_and_PlanningController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable(List rs) {
        obj.clear();
        tbl_salary_up.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String emp_code = String.valueOf(crow.get("emp_code"));
            String fnn = (String) crow.get("fnn");
            String grade = (String) crow.get("grade");
            String status = (String) crow.get("status");
            String job_id = (String) crow.get("job_id");
            String dept_id = (String) crow.get("dept_id");
            obj.add(new HR4_SalaryGradeUpClass(emp_code, fnn, grade, status, job_id, dept_id));
        }
        tbl_salary_up.setItems(obj);
    }
    
    //Salary Grade Table
    public void generateTable1() {

        tbl_salarygrade.getItems().clear();
        tbl_salarygrade.getColumns().removeAll(tbl_salarygrade.getColumns());
        TableColumn<HR4_SalaryClass, String> salary_grade = new TableColumn<>("Salary Grade");
        TableColumn<HR4_SalaryClass, String> step1 = new TableColumn<>("Step 1");
        TableColumn<HR4_SalaryClass, String> step2 = new TableColumn<>("Step 2");
        TableColumn<HR4_SalaryClass, String> step3 = new TableColumn<>("Step 3");
        TableColumn<HR4_SalaryClass, String> step4 = new TableColumn<>("Step 4");
        TableColumn<HR4_SalaryClass, String> step5 = new TableColumn<>("Step 5");
        TableColumn<HR4_SalaryClass, String> step6 = new TableColumn<>("Step 6");
        TableColumn<HR4_SalaryClass, String> step7 = new TableColumn<>("Step 7");
        TableColumn<HR4_SalaryClass, String> step8 = new TableColumn<>("Step 8");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        salary_grade.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().a);
        step1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().b);
        step2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().c);
        step3.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().d);
        step4.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().e);
        step5.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().f);
        step6.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().g);
        step7.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().h);
        step8.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().i);
     
        //</editor-fold>
        tbl_salarygrade.getColumns().addAll(salary_grade, step1, step2, step3, step4, step5, step6, step7, step8);
    }
    
    long DummyCount1 = 0;
    long GlobalCount1 = 0;
    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    sm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount1 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount1 != GlobalCount1){    
                        tbl_salarygrade.getItems();
                        List rs = sm
                                    .get("salary_grade",
                                            "step1",
                                            "step2",
                                            "step3",
                                            "step4",
                                            "step5",
                                            "step6",
                                            "step7",
                                            "step8");
                        AddJobToTable1(rs);
                        GlobalCount1 = DummyCount1;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Compensation_and_PlanningController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable1(List rs) {
        obj1.clear();
        tbl_salarygrade.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String salary_grade = String.valueOf(crow.get("salary_grade"));
            String step1 = (String) crow.get("step1");
            String step2 = (String) crow.get("step2");
            String step3 = (String) crow.get("step3");
            String step4 = (String) crow.get("step4");
            String step5 = (String) crow.get("step5");
            String step6 = (String) crow.get("step6");
            String step7 = (String) crow.get("step7");
            String step8 = (String) crow.get("step8");
            obj1.add(new HR4_SalaryClass(salary_grade,step1,step2,step3,step4,step5,step6,step7,step8));
        }
        tbl_salarygrade.setItems(obj1);
    }
    
    // For Benefits Table
    public void generateTable2() {

        tbl_benefits.getItems().clear();
        tbl_benefits.getColumns().removeAll(tbl_benefits.getColumns());
        TableColumn<HR4_BenefitsClass, String> title = new TableColumn<>("Title");
        TableColumn<HR4_BenefitsClass, String> amount = new TableColumn<>("Amount");
        TableColumn<HR4_BenefitsClass, String> description = new TableColumn<>("Description");
        TableColumn<HR4_BenefitsClass, String> days = new TableColumn<>("Days");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        title.setCellValueFactory((TableColumn.CellDataFeatures<HR4_BenefitsClass, String> param) -> param.getValue().a);
        amount.setCellValueFactory((TableColumn.CellDataFeatures<HR4_BenefitsClass, String> param) -> param.getValue().b);
        description.setCellValueFactory((TableColumn.CellDataFeatures<HR4_BenefitsClass, String> param) -> param.getValue().c);
        days.setCellValueFactory((TableColumn.CellDataFeatures<HR4_BenefitsClass, String> param) -> param.getValue().d);
        TableColumn<HR4_BenefitsClass, Boolean> btnAction = new TableColumn<>("Actions");

        btnAction.setSortable(false);

        btnAction.setCellFactory((TableColumn<HR4_BenefitsClass, Boolean> param) -> new TableCell<HR4_BenefitsClass, Boolean>() {

            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            private final MenuButton btn = new MenuButton("Actions", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");
                
                MenuItem Add = new MenuItem("Add Employees");
                FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.USER);
                fx.getStyleClass().add("fontIconMenu");
                Add.setGraphic(fx);
                Add.setOnAction(event -> {
                      HR4_BenefitsClass fc = (HR4_BenefitsClass) getTableRow().getItem();
                      HR4_NewCompensationFill.EditBenefits(
                    fc.z.getValue(),
                    fc.idxx.getValue(),
                    fc.a.getValue(),
                    fc.b.getValue(),
                    fc.c.getValue(),
                    fc.d.getValue());
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_AddEmployeeToBenefits.fxml").getParent());
                lq.open();
                });
                
                MenuItem Edit = new MenuItem("Edit");
                FontAwesomeIconView fx1 = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                fx1.getStyleClass().add("fontIconMenu");
                Edit.setGraphic(fx1);
                Edit.setOnAction(event -> {
                    HR4_BenefitsClass fc = (HR4_BenefitsClass) getTableRow().getItem();
                    HR4_NewCompensationFill.EditBenefits(
                    fc.z.getValue(),
                    fc.idxx.getValue(),
                    fc.a.getValue(),
                    fc.b.getValue(),
                    fc.c.getValue(),
                    fc.d.getValue());
                
                Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_EditBenefits.fxml").getParent());
                md.open();
                });
                
                MenuItem Delete = new MenuItem("Delete");
                FontAwesomeIconView fx2 = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                fx2.getStyleClass().add("fontIconMenu");
                Delete.setGraphic(fx2);
                Delete.setOnAction(event -> {HR4_BenefitsModel m = new HR4_BenefitsModel();
                m.delete().where(new Object[][]{
                {"title", "=", tbl_benefits.getSelectionModel().getSelectedItem().a.getValue()}
                }).executeUpdate();
                });
                
                btn.getItems().add(Add);
                btn.getItems().add(Edit);
                btn.getItems().add(Delete);
            }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(btn);
                } else {
                    setGraphic(null);
                }
            }

        });

        btnAction.setCellValueFactory((TableColumn.CellDataFeatures<HR4_BenefitsClass, Boolean> param) -> new SimpleBooleanProperty(false));

        //</editor-fold>
        tbl_benefits.getColumns().addAll(title, amount, description, days,btnAction);
    }
    
    long DummyCount2 = 0;
    long GlobalCount2 = 0;
    public void populateTable2() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    bm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount2 != GlobalCount2){    
                        tbl_benefits.getItems();
                        List rs = bm
                                    .get(   "id",
                                            "benefits_id",
                                            "title",
                                            "amount",
                                            "description",
                                            "days");
                        AddJobToTable2(rs);
                        GlobalCount2 = DummyCount2;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Compensation_and_PlanningController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }
    
    public void generateDeductionsTable() {

        tbl_selection_deducs.getItems().clear();
        tbl_selection_deducs.getColumns().removeAll(tbl_selection_deducs.getColumns());
        TableColumn<HR4_DeductionsClass, String> deduc_code = new TableColumn<>("Deduction Code");
        TableColumn<HR4_DeductionsClass, String> title = new TableColumn<>("Title");
        

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        deduc_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_DeductionsClass, String> param) -> param.getValue().a);
        title.setCellValueFactory((TableColumn.CellDataFeatures<HR4_DeductionsClass, String> param) -> param.getValue().b);
        TableColumn<HR4_DeductionsClass, Boolean> btnAction = new TableColumn<>("Actions");
        btnAction.setSortable(false);

        btnAction.setCellFactory((TableColumn<HR4_DeductionsClass, Boolean> param) -> new TableCell<HR4_DeductionsClass, Boolean>() {

            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            private final MenuButton btn = new MenuButton("Actions", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");
                
                MenuItem edit = new MenuItem("Edit");
                FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                fx.getStyleClass().add("fontIconMenu");
                edit.setGraphic(fx);
                edit.setOnAction(event -> {
                    HR4_DeductionsClass fc = (HR4_DeductionsClass) getTableRow().getItem();
                    HR4_DeductionsFill.DeductionsOptions(
                    fc.a.getValue(),
                    fc.b.getValue());
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_EditDeductions.fxml").getParent());
                lq.open();
                });
                MenuItem add = new MenuItem("CRUD Table");
                FontAwesomeIconView fx1 = new FontAwesomeIconView(FontAwesomeIcon.HAND_ALT_DOWN);
                fx1.getStyleClass().add("fontIconMenu");
                add.setGraphic(fx1);
                add.setOnAction(event -> {
                    HR4_DeductionsClass fc = (HR4_DeductionsClass) getTableRow().getItem();
                    HR4_DeductionsFill.DeductionsOptions(
                    fc.a.getValue(),
                    fc.b.getValue());
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_AddNewDeductionTable.fxml").getParent());
                lq.open();  
                });
                btn.getItems().add(edit);
                btn.getItems().add(add);
            }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(btn);
                } else {
                    setGraphic(null);
                }
            }

        });

        btnAction.setCellValueFactory((TableColumn.CellDataFeatures<HR4_DeductionsClass, Boolean> param) -> new SimpleBooleanProperty(false));

        //</editor-fold>
        tbl_selection_deducs.getColumns().addAll(deduc_code,title,btnAction);
    }
    
    long DummyCount3 = 0;
    long GlobalCount3 = 0;
    public void populateDeductionsTable() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    dm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount3 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                    if (DummyCount3 != GlobalCount3){    
                        tbl_selection_deducs.getItems();
                        List rs = dm
                                 .get(
                                            "deduc_code",
                                            "title"
                                            );
                        AddDeductionsToTable(rs);
                        GlobalCount3 = DummyCount3;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Compensation_and_PlanningController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddDeductionsToTable(List rs) {
        obj3.clear();
        tbl_selection_deducs.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String deduc_code = String.valueOf(crow.get("deduc_code"));
            String title = (String) crow.get("title");
            obj3.add(new HR4_DeductionsClass(deduc_code,title));
        }
        tbl_selection_deducs.setItems(obj3);
    }
    
    public void AddJobToTable2(List rs) {
        obj2.clear();
        tbl_benefits.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String id = String.valueOf(crow.get("id"));
            String benefits_id = String.valueOf(crow.get("benefits_id"));
            String title = String.valueOf(crow.get("title"));
            String amount = (String) crow.get("amount");
            String description = (String) crow.get("description");
            String days = (String) crow.get("days");
            obj2.add(new HR4_BenefitsClass(id, benefits_id, title, amount, description, days));
        }
        tbl_benefits.setItems(obj2);
    }
    
    public void generateTable1010(){
        tbl_benEmp.getItems().clear();
        tbl_benEmp.getColumns().removeAll(tbl_benEmp.getColumns());
        TableColumn<HR4_Benefits1Class, String> benefits_id = new TableColumn<>("Benefits Code");
        TableColumn<HR4_Benefits1Class, String> benN = new TableColumn<>("Benefits Name");
        TableColumn<HR4_Benefits1Class, String> emp_code = new TableColumn<>("Employee Code");
        TableColumn<HR4_Benefits1Class, String> fnN = new TableColumn<>("Fullname");
        TableColumn<HR4_Benefits1Class, String> balance = new TableColumn<>("Balance");
        TableColumn<HR4_Benefits1Class, String> araw = new TableColumn<>("Days");
        TableColumn<HR4_Benefits1Class, String> files = new TableColumn<>("Files");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        benefits_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().a);
        benN.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().b);
        emp_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().c);
        fnN.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().d);
        balance.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().e);
        araw.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().f);
        files.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, String> param) -> param.getValue().g);
        TableColumn<HR4_Benefits1Class, Boolean> btnAction = new TableColumn<>("Actions");

        btnAction.setSortable(false);

        btnAction.setCellFactory((TableColumn<HR4_Benefits1Class, Boolean> param) -> new TableCell<HR4_Benefits1Class, Boolean>() {

            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            private final MenuButton btn = new MenuButton("Actions", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");
                
                MenuItem Use = new MenuItem("Use Benefits");
                FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.USER);
                fx.getStyleClass().add("fontIconMenu");
                Use.setGraphic(fx);
                Use.setOnAction(event -> {
                    HR4_Benefits1Class fc = (HR4_Benefits1Class) getTableRow().getItem();
                    HR4_DeducBenefitsFill.FillDeduc(
                    fc.a.getValue(),
                    fc.b.getValue(),
                    fc.c.getValue(),
                    fc.d.getValue(),
                    fc.e.getValue(),
                    fc.ff.getValue());
                        
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_DeducBenefit.fxml").getParent());
                lq.open();
                });
                
                MenuItem Edit = new MenuItem("Edit");
                FontAwesomeIconView fx1 = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                fx1.getStyleClass().add("fontIconMenu");
                Edit.setGraphic(fx1);
                Edit.setOnAction(event -> {
                });
                
                btn.getItems().add(Use);
                btn.getItems().add(Edit);
            }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(btn);
                } else {
                    setGraphic(null);
                }
            }

        });

        btnAction.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Benefits1Class, Boolean> param) -> new SimpleBooleanProperty(false));
        //</editor-fold>
        tbl_benEmp.getColumns()
        .addAll(benefits_id,benN,emp_code,fnN,balance,araw,files,btnAction);
   }
    
    public void populate() {

        try {
            //tbl employees
            HR4_Benefits1Model pem = new HR4_Benefits1Model();

            List e = pem.join(Model.JOIN.INNER, "aerolink.tbl_hr4_benefits", "benefits_id", "tblD", "=", "benefits_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "emp_code")
                    .where(new Object[][]{{"tblD.benefits_id", "=", tbl_benefits.getSelectionModel().getSelectedItem().idxx.getValue()}})
                    .get("tblD.benefits_id as benefits_id",
                         "tblD.title as benN",
                         "tblDD.employee_code as emp_code",
                         "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnN",
                         "balance",
                         "araw",
                         "files",
                         "tblD.amount as Amt");
            Stored(e);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   
    public void Stored(List b1) {
        ObservableList<HR4_Benefits1Class> obj3 = FXCollections.observableArrayList();
        obj3.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj3.add(
                        new HR4_Benefits1Class(
                                String.valueOf(hm.get("benefits_id")),
                                String.valueOf(hm.get("benN")),
                                String.valueOf(hm.get("emp_code")),
                                String.valueOf(hm.get("fnN")),
                                String.valueOf(hm.get("balance")),
                                String.valueOf(hm.get("araw")),
                                String.valueOf(hm.get("files")),
                                String.valueOf(hm.get("Amt"))
                        ));

            }
            tbl_benEmp.setItems(obj3);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generateDeductionsTable1() {

        tbl_deducs.getItems().clear();
        tbl_deducs.getColumns().removeAll(tbl_deducs.getColumns());
        TableColumn<HR4_Deductions1Class, String> C1 = new TableColumn<>("Column 1");
        TableColumn<HR4_Deductions1Class, String> C2 = new TableColumn<>("Column 2");
        TableColumn<HR4_Deductions1Class, String> C3 = new TableColumn<>("Column 3");
        TableColumn<HR4_Deductions1Class, String> C4 = new TableColumn<>("Column 4");
        TableColumn<HR4_Deductions1Class, String> C5 = new TableColumn<>("Column 5");
        TableColumn<HR4_Deductions1Class, String> C6 = new TableColumn<>("Column 6");
        TableColumn<HR4_Deductions1Class, String> C7 = new TableColumn<>("Column 7");
        TableColumn<HR4_Deductions1Class, String> C8 = new TableColumn<>("Column 8");
        TableColumn<HR4_Deductions1Class, String> C9 = new TableColumn<>("Column 9");
        TableColumn<HR4_Deductions1Class, String> C10 = new TableColumn<>("Column 10");
        TableColumn<HR4_Deductions1Class, String> C11 = new TableColumn<>("Column 11");
        TableColumn<HR4_Deductions1Class, String> C12 = new TableColumn<>("Column 12");
        TableColumn<HR4_Deductions1Class, String> C13 = new TableColumn<>("Column 13");
        TableColumn<HR4_Deductions1Class, String> C14 = new TableColumn<>("Column 14");
        TableColumn<HR4_Deductions1Class, String> C15 = new TableColumn<>("Column 15");
        

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        C1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().b);
        C2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().c);
        C3.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().d);
        C4.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().e);
        C5.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().f);
        C6.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().g);
        C7.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().h);
        C8.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().i);
        C9.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().j);
        C10.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().k);
        C11.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().l);
        C12.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().m);
        C13.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().n);
        C14.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().o);
        C15.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().p);
        
        //</editor-fold>
        tbl_deducs.getColumns().addAll(C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C13,C14,C15);
    }
    
    public void populateDeductionsTable1() {
        HR4_Deductions1Model dm1 = new HR4_Deductions1Model();
        try {
            List e = dm1
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_deductions", "deduc_code", "tblD", "=", "deduc_code")
                    .where(new Object[][]{{"tblD.deduc_code", "=", tbl_selection_deducs.getSelectionModel().getSelectedItem().a.getValue()}})
                                        .get(
                                            "tblD.deduc_code as deduc_code",
                                            "C1",      
                                            "C2",
                                            "C3",
                                            "C4",
                                            "C5",
                                            "C6",
                                            "C7",
                                            "C8",
                                            "C9",
                                            "C10",
                                            "C11",
                                            "C12",
                                            "C13",
                                            "C14",
                                            "C15",
                                            "aerolink.tbl_hr4_deductions1.id");
            Stored1(e);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   
    public void Stored1(List b1) {
    ObservableList<HR4_Deductions1Class> obj4 = FXCollections.observableArrayList();
        obj4.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj4.add(
                        new HR4_Deductions1Class(
                                String.valueOf(hm.get("deduc_code")),
                                String.valueOf(hm.get("C1")),
                                String.valueOf(hm.get("C2")),
                                String.valueOf(hm.get("C3")),
                                String.valueOf(hm.get("C4")),
                                String.valueOf(hm.get("C5")),
                                String.valueOf(hm.get("C6")),
                                String.valueOf(hm.get("C7")),
                                String.valueOf(hm.get("C8")),
                                String.valueOf(hm.get("C9")),
                                String.valueOf(hm.get("C10")),
                                String.valueOf(hm.get("C11")),
                                String.valueOf(hm.get("C12")),
                                String.valueOf(hm.get("C13")),
                                String.valueOf(hm.get("C14")),
                                String.valueOf(hm.get("C15")),
                                String.valueOf(hm.get("id"))
                        ));

            }
            tbl_deducs.setItems(obj4);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
