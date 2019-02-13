/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;
import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import FXMLS.HR1.ClassFiles.TableModel_jLimit;
import FXMLS.HR1.HR1_RecruitmentController;
import FXMLS.HR2.ClassFiles.HR2_ExaminationClass;
import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass1;
import FXMLS.HR4.Filler.HR4_NewPayrollFill;
import FXMLS.HR4.Filler.HR4_NewPayrollFill2;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollMainClass;
import FXMLS.HR4.ClassFiles.HR4_PayrollEmployeeClass;
import FXMLS.HR4.Model.HR4_NewPayrollMainModel;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import FXMLS.HR4.Model.HR4_PayrollEmployeeModel;
import FXMLS.HR4.Model.HR4_PayrollModel;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;



/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_PayrollController implements Initializable {
    HR4_PayrollModel pm = new HR4_PayrollModel();
    HR4_NewPayrollModel npm = new HR4_NewPayrollModel();
    HR4_NewPayrollMainModel npmm = new HR4_NewPayrollMainModel();
    ObservableList<HR4_NewPayrollMainClass> obj1 = FXCollections.observableArrayList();
    ObservableList<HR4_NewPayrollClass> obj2 = FXCollections.observableArrayList();
    ObservableList<HR4_NewPayrollClass1> obj3 = FXCollections.observableArrayList();
    ExecutorService e = Executors.newFixedThreadPool(1);
    @FXML
    private TableView<HR4_NewPayrollClass> tbl_selection;
    @FXML
    private TableView<HR4_PayrollEmployeeClass> tbl_employees;
    @FXML
    private JFXButton AddBtn;
    @FXML
    private JFXButton DeleteBtn;
    @FXML
    private JFXButton SettingsBtnManual;
    @FXML
    private TableView<HR4_NewPayrollClass1> tbl_payroll1;
    @FXML
    private TableView<HR4_PayrollEmployeeClass> tbl_employees1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.generateTable2();
        this.populateTable2();
        this.generateTable3();
        this.populateTable3();
        this.generateTable1010();
        
            tbl_selection.getSelectionModel().selectFirst();
            tbl_selection.setOnMouseClicked(e -> {
            populate();
            });
            
            SettingsBtnManual.setOnMouseClicked(e -> {
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_PayrollSettings.fxml").getParent());
                lq.open();
            });
            
            AddBtn.setOnMouseClicked(e -> {
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewPayrollMain.fxml").getParent());
                lq.open();
            });
            DeleteBtn.setOnMouseClicked(e -> {
                HR4_NewPayrollModel m = new HR4_NewPayrollModel();
   
                m.delete().where(new Object[][]{
                {"payroll_code", "=", tbl_selection.getSelectionModel().getSelectedItem().a.getValue()}
                }).executeUpdate();
            });
    }    
                
    public void generateTable2(){
        tbl_selection.getItems().clear();
        tbl_selection.getColumns().removeAll(tbl_selection.getColumns());
        TableColumn<HR4_NewPayrollClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_NewPayrollClass, String> start_date = new TableColumn<>("Start Date");
        TableColumn<HR4_NewPayrollClass, String> end_date = new TableColumn<>("End Date");
        TableColumn<HR4_NewPayrollClass, String> total_salaries = new TableColumn<>("Total Salary");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().a);
        start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().b);
        end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().c);
        total_salaries.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().d);
        /*TableColumn<HR4_NewPayrollClass, Void> addButton = new TableColumn("More Options");
        Callback<TableColumn<HR4_NewPayrollClass, Void>, TableCell<HR4_NewPayrollClass, Void>> cellFactory
                = new Callback<TableColumn<HR4_NewPayrollClass, Void>, TableCell<HR4_NewPayrollClass, Void>>() {
            @Override
            public TableCell<HR4_NewPayrollClass, Void> call(final TableColumn<HR4_NewPayrollClass, Void> param) {

                final TableCell<HR4_NewPayrollClass, Void> cell = new TableCell<HR4_NewPayrollClass, Void>() {
                    private final Button btn = new Button("Request");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                HR4_NewPayrollClass fc = (HR4_NewPayrollClass) getTableRow().getItem();

                                HR4_NewPayrollFill2.SendRequest(
                                        fc.a.getValue(),
                                        fc.b.getValue(),
                                        fc.c.getValue(),
                                        fc.d.getValue(),
                                        fc.e.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml").getParent());
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
        tbl_selection.getColumns().add(addButton);
        */
        //</editor-fold>
        TableColumn<HR4_NewPayrollClass, Boolean> btnAction = new TableColumn<>("Actions");

        btnAction.setSortable(false);

        btnAction.setCellFactory((TableColumn<HR4_NewPayrollClass, Boolean> param) -> new TableCell<HR4_NewPayrollClass, Boolean>() {

            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            private final MenuButton btn = new MenuButton("Actions", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");

                MenuItem Request = new MenuItem("Request to Financials");
                FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
                fx.getStyleClass().add("fontIconMenu");
                Request.setGraphic(fx);
                Request.setOnAction(event -> {
                    HR4_NewPayrollClass fc = (HR4_NewPayrollClass) getTableRow().getItem();
                                        HR4_NewPayrollFill2.SendRequest(
                                        fc.a.getValue(),
                                        fc.b.getValue(),
                                        fc.c.getValue(),
                                        fc.d.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml").getParent());
                                lq.open();
                });
                MenuItem GG = new MenuItem("Add Employees");
                FontAwesomeIconView fx1 = new FontAwesomeIconView(FontAwesomeIcon.USER);
                fx1.getStyleClass().add("fontIconMenu");
                GG.setGraphic(fx1);
                GG.setOnAction(event -> {
                    HR4_NewPayrollClass fc = (HR4_NewPayrollClass) getTableRow().getItem();
                                        HR4_NewPayrollFill.CreateNew(
                                        fc.a.getValue());
                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewPayroll.fxml").getParent());
                lq.open();
                
                });

                btn.getItems().add(Request);
                btn.getItems().add(GG);
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

        btnAction.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, Boolean> param) -> new SimpleBooleanProperty(false));

        tbl_selection.getColumns()
        .addAll(payroll_code,start_date,end_date,total_salaries,btnAction);
   }
   long DummyCount1 = 0;
   long GlobalCount1 = 0;
   public void populateTable2(){
       CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    npm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount1 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount1 != GlobalCount1) {
                            List rs = npm
                                    .get(   
                                        "payroll_code","start_date","end_date","total_salaries"    
                                            );
                        AddToTable2(rs);
                        GlobalCount1 = DummyCount1;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

   }
   public void AddToTable2(List rs){
        obj2.clear();
        tbl_selection.getItems().clear();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String payroll_code = String.valueOf(crow.get("payroll_code"));
            String start_date = (String) crow.get("start_date");
            String end_date = (String) crow.get("end_date");
            String total_salaries = (String) crow.get("total_salaries");
            obj2.add(new HR4_NewPayrollClass(payroll_code, start_date, end_date, total_salaries));
        }
        tbl_selection.setItems(obj2);   
   }
    public void generateTable1010(){
        tbl_employees.getItems().clear();
        tbl_employees.getColumns().removeAll(tbl_employees.getColumns());
        TableColumn<HR4_PayrollEmployeeClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_PayrollEmployeeClass, String> employee_code = new TableColumn<>("Employee Code");
        TableColumn<HR4_PayrollEmployeeClass, String> Fullname = new TableColumn<>("Fullname");
        TableColumn<HR4_PayrollEmployeeClass, String> job_id = new TableColumn<>("Position");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().a);
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().b);
        Fullname.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().c);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().d);
         //</editor-fold>
        tbl_employees.getColumns()
        .addAll(payroll_code,employee_code,Fullname,job_id);
   }
    public void populate() {

        try {
            //tbl employees
            HR4_PayrollEmployeeModel pem = new HR4_PayrollEmployeeModel();

            List e = pem.join(Model.JOIN.INNER, "aerolink.tbl_hr4_payroll", "payroll_code", "tblD", "=", "payroll_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblDDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblDDD", "job_id", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                    .where(new Object[][]{{"tblD.payroll_code", "=", tbl_selection.getSelectionModel().getSelectedItem().a.getValue()}})
                    .get("tbl_hr4_payroll3.payroll_code",
                         "tblDD.employee_code",
                         "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as Fullname",
                         "aerolink.tbl_hr4_jobs.title as job_id",
                         "aerolink.tbl_hr4_department.dept_name as dept_id");
            Stored(e);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   public void Stored(List b1) {
        ObservableList<HR4_PayrollEmployeeClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj.add(
                        new HR4_PayrollEmployeeClass(
                                String.valueOf(hm.get("payroll_code")),
                                String.valueOf(hm.get("employee_code")),
                                String.valueOf(hm.get("Fullname")),
                                String.valueOf(hm.get("job_id")),
                                String.valueOf(hm.get("dept_id"))
                        

                        
                        ));

            }
            tbl_employees.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
   public void generateTable3(){
        tbl_payroll1.getItems().clear();
        tbl_payroll1.getColumns().removeAll(tbl_payroll1.getColumns());
        TableColumn<HR4_NewPayrollClass1, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_NewPayrollClass1, String> start_date = new TableColumn<>("Start Date");
        TableColumn<HR4_NewPayrollClass1, String> end_date = new TableColumn<>("End Date");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass1, String> param) -> param.getValue().a);
        start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass1, String> param) -> param.getValue().b);
        end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass1, String> param) -> param.getValue().c);
        /*TableColumn<HR4_NewPayrollClass, Void> addButton = new TableColumn("More Options");
        Callback<TableColumn<HR4_NewPayrollClass, Void>, TableCell<HR4_NewPayrollClass, Void>> cellFactory
                = new Callback<TableColumn<HR4_NewPayrollClass, Void>, TableCell<HR4_NewPayrollClass, Void>>() {
            @Override
            public TableCell<HR4_NewPayrollClass, Void> call(final TableColumn<HR4_NewPayrollClass, Void> param) {

                final TableCell<HR4_NewPayrollClass, Void> cell = new TableCell<HR4_NewPayrollClass, Void>() {
                    private final Button btn = new Button("Request");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                HR4_NewPayrollClass fc = (HR4_NewPayrollClass) getTableRow().getItem();

                                HR4_NewPayrollFill2.SendRequest(
                                        fc.a.getValue(),
                                        fc.b.getValue(),
                                        fc.c.getValue(),
                                        fc.d.getValue(),
                                        fc.e.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_REQUESTFORM.fxml").getParent());
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
        tbl_selection.getColumns().add(addButton);
        */
        //</editor-fold>
        
        tbl_payroll1.getColumns()
        .addAll(payroll_code,start_date,end_date);
   }
   long DummyCount2 = 0;
   long GlobalCount2 = 0;
   public void populateTable3(){
       CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    pm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount2 != GlobalCount2) {
                            List rs = pm
                                    .get(       
                                        "payroll_code","start_date","end_date"  
                                            );
                        AddToTable3(rs);
                        GlobalCount2 = DummyCount2;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

   }
   public void AddToTable3(List rs){
        obj3.clear();
        tbl_payroll1.getItems().clear();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String payroll_code = String.valueOf(crow.get("payroll_code"));
            String start_date = (String) crow.get("start_date");
            String end_date = (String) crow.get("end_date");
            obj3.add(new HR4_NewPayrollClass1(payroll_code, start_date, end_date));
        }
        tbl_payroll1.setItems(obj3);   
   }

   public void generateTable10101(){
        tbl_employees1.getItems().clear();
        tbl_employees1.getColumns().removeAll(tbl_employees.getColumns());
        TableColumn<HR4_PayrollEmployeeClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_PayrollEmployeeClass, String> employee_code = new TableColumn<>("Employee Code");
        TableColumn<HR4_PayrollEmployeeClass, String> Fullname = new TableColumn<>("Fullname");
        TableColumn<HR4_PayrollEmployeeClass, String> job_id = new TableColumn<>("Position");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().a);
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().b);
        Fullname.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().c);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().d);
         //</editor-fold>
        tbl_employees1.getColumns()
        .addAll(payroll_code,employee_code,Fullname,job_id);
   }
    public void populate1() {

        try {
            //tbl employees
            HR4_PayrollEmployeeModel pem = new HR4_PayrollEmployeeModel();

            List e = pem.join(Model.JOIN.INNER, "aerolink.tbl_hr4_payroll", "payroll_code", "tblD", "=", "payroll_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblDDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblDDD", "job_id", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                    .where(new Object[][]{{"tblD.payroll_code", "=", tbl_selection.getSelectionModel().getSelectedItem().a.getValue()}})
                    .get("tbl_hr4_payroll3.payroll_code",
                         "tblDD.employee_code",
                         "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as Fullname",
                         "aerolink.tbl_hr4_jobs.title as job_id",
                         "aerolink.tbl_hr4_department.dept_name as dept_id");
            Stored(e);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   public void Stored1(List b1) {
        ObservableList<HR4_PayrollEmployeeClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj.add(
                        new HR4_PayrollEmployeeClass(
                                String.valueOf(hm.get("payroll_code")),
                                String.valueOf(hm.get("employee_code")),
                                String.valueOf(hm.get("Fullname")),
                                String.valueOf(hm.get("job_id")),
                                String.valueOf(hm.get("dept_id"))
                        ));

            }
            tbl_employees1.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
   }
}
