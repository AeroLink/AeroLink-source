/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;
import FXMLS.HR2.ClassFiles.HR2_ExaminationClass;
import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollFill;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollFill2;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollMainClass;
import FXMLS.HR4.ClassFiles.HR4_PayrollClass;
import FXMLS.HR4.ClassFiles.HR4_PayrollEmployeeClass;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.Model.HR4_NewPayrollMainModel;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import FXMLS.HR4.Model.HR4_PayrollEmployeeModel;
import FXMLS.HR4.Model.HR4_PayrollModel;
import Model.HR2_Examination;
import Model.HR4_Classification;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_PayrollController implements Initializable {
    HR4_NewPayrollModel npm = new HR4_NewPayrollModel();
    HR4_NewPayrollMainModel npmm = new HR4_NewPayrollMainModel();
    ObservableList<HR4_NewPayrollMainClass> obj1 = FXCollections.observableArrayList();
    ObservableList<HR4_NewPayrollClass> obj2 = FXCollections.observableArrayList();
    ExecutorService e = Executors.newFixedThreadPool(1);
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private TableView<HR4_NewPayrollMainClass> tbl_ps;
    @FXML
    private TableView<HR4_NewPayrollClass> tbl_eer;
    @FXML
    private TableView<HR4_PayrollEmployeeClass> tlb_eer1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.populateTable1();
        this.generateTable1();
        this.populateTable2();
        this.generateTable2();
        this.generateTable1010();
        
        tbl_eer.getSelectionModel().selectFirst();
        tbl_eer.setOnMouseClicked(e -> {
        populate();
        });
    }
    
   public void generateTable1(){
       tbl_ps.getItems().clear();
        tbl_ps.getColumns().removeAll(tbl_ps.getColumns());
        TableColumn<HR4_NewPayrollMainClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_NewPayrollMainClass, String> start_date = new TableColumn<>("Start Date");
        TableColumn<HR4_NewPayrollMainClass, String> end_date = new TableColumn<>("End Date");
        TableColumn<HR4_NewPayrollMainClass, String> dept_id = new TableColumn<>("Department");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollMainClass, String> param) -> param.getValue().a);
        start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollMainClass, String> param) -> param.getValue().b);
        end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollMainClass, String> param) -> param.getValue().c);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollMainClass, String> param) -> param.getValue().d);
        
        
        TableColumn<HR4_NewPayrollMainClass, Void> addButton = new TableColumn("More Options");
        Callback<TableColumn<HR4_NewPayrollMainClass, Void>, TableCell<HR4_NewPayrollMainClass, Void>> cellFactory
                = new Callback<TableColumn<HR4_NewPayrollMainClass, Void>, TableCell<HR4_NewPayrollMainClass, Void>>() {
            @Override
            public TableCell<HR4_NewPayrollMainClass, Void> call(final TableColumn<HR4_NewPayrollMainClass, Void> param) {

                final TableCell<HR4_NewPayrollMainClass, Void> cell = new TableCell<HR4_NewPayrollMainClass, Void>() {
                    private final Button btn = new Button("Add");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {

                                HR4_NewPayrollMainClass fc = (HR4_NewPayrollMainClass) getTableRow().getItem();

                                HR4_NewPayrollFill.CreateNew(
                                        fc.a.getValue(),
                                        fc.b.getValue(),
                                        fc.c.getValue(),
                                        fc.d.getValue());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewPayroll.fxml").getParent());
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
        tbl_ps.getColumns().add(addButton);

        //</editor-fold>
        tbl_ps.getColumns()
        .addAll(payroll_code,start_date,end_date,dept_id);
   }
   public void generateTable1010(){
        tlb_eer1.getItems().clear();
        tlb_eer1.getColumns().removeAll(tlb_eer1.getColumns());
        TableColumn<HR4_PayrollEmployeeClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_PayrollEmployeeClass, String> employee_code = new TableColumn<>("Employee Code");
        TableColumn<HR4_PayrollEmployeeClass, String> Fullname = new TableColumn<>("Fullname");
        TableColumn<HR4_PayrollEmployeeClass, String> job_id = new TableColumn<>("Position");
        TableColumn<HR4_PayrollEmployeeClass, String> dept_id = new TableColumn<>("Department");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().a);
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().b);
        Fullname.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().c);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().d);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PayrollEmployeeClass, String> param) -> param.getValue().d);
         //</editor-fold>
        tlb_eer1.getColumns()
        .addAll(payroll_code,employee_code,Fullname,job_id,dept_id);
   }
        
   public void populateTable1(){
       CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    npmm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_ps.getItems();
                            List rs = npmm
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                                    .get(   
                                        "payroll_code","start_date","end_date","tblD.dept_name as dept_id"    
                                            );
                        AddToTable1(rs);
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
   public void AddToTable1(List rs){
        obj1.clear();
        tbl_ps.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String payroll_code = String.valueOf(crow.get("payroll_code"));
            String start_date = (String) crow.get("start_date");
            String end_date = (String) crow.get("end_date");
            String dept_id = (String) crow.get("dept_id");
            obj1.add(new HR4_NewPayrollMainClass(payroll_code, start_date, end_date, dept_id));
        }
        tbl_ps.setItems(obj1);   
   }

   public void generateTable2(){
       tbl_eer.getItems().clear();
        tbl_eer.getColumns().removeAll(tbl_eer.getColumns());
        TableColumn<HR4_NewPayrollClass, String> payroll_code = new TableColumn<>("Payroll Code");
        TableColumn<HR4_NewPayrollClass, String> start_date = new TableColumn<>("Start Date");
        TableColumn<HR4_NewPayrollClass, String> end_date = new TableColumn<>("End Date");
        TableColumn<HR4_NewPayrollClass, String> total_salaries = new TableColumn<>("Total Salary");
        TableColumn<HR4_NewPayrollClass, String> dept_id = new TableColumn<>("Department");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        payroll_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().a);
        start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().b);
        end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().c);
        total_salaries.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().d);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_NewPayrollClass, String> param) -> param.getValue().e);
        TableColumn<HR4_NewPayrollClass, Void> addButton = new TableColumn("More Options");
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
        tbl_eer.getColumns().add(addButton);


        //</editor-fold>
        tbl_eer.getColumns()
        .addAll(payroll_code,start_date,end_date,total_salaries,dept_id);
   }
   public void populateTable2(){
       CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    npm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_eer.getItems();
                            List rs = npm
                                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                                    .get(   
                                        "payroll_code","start_date","end_date","total_salaries","tblD.dept_name as dept_id"    
                                            );
                        AddToTable2(rs);
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
   public void AddToTable2(List rs){
        obj2.clear();
        tbl_eer.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String payroll_code = String.valueOf(crow.get("payroll_code"));
            String start_date = (String) crow.get("start_date");
            String end_date = (String) crow.get("end_date");
            String total_salaries = (String) crow.get("total_salaries");
            String dept_id = (String) crow.get("dept_id");
            obj2.add(new HR4_NewPayrollClass(payroll_code, start_date, end_date, total_salaries, dept_id));
        }
        tbl_eer.setItems(obj2);   
   }
   public void populate() {

        try {
            //tbl courses
            HR4_PayrollEmployeeModel pem = new HR4_PayrollEmployeeModel();

            List e = pem.join(Model.JOIN.INNER, "aerolink.tbl_hr4_payroll", "payroll_code", "tblD", "=", "payroll_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblDDD", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblDDD", "job_id", true)
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                    .where(new Object[][]{{"tblD.payroll_code", "=", tbl_eer.getSelectionModel().getSelectedItem().a.getValue()}})
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
            tlb_eer1.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void addbtn(ActionEvent event) {
        Modal lq = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewPayrollMain.fxml").getParent());
        lq.open();
    }

    @FXML
    private void dltbtn(ActionEvent event) {
    }
}

