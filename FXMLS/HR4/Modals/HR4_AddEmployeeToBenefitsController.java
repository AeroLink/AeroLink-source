/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_BenefitsClass;
import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollClass;
import FXMLS.HR4.Filler.HR4_EmployeeFill;
import FXMLS.HR4.Filler.HR4_NewCompensationFill;
import FXMLS.HR4.Filler.HR4_NewPayrollFill;
import FXMLS.HR4.Filler.HR4_NewPayrollFill2;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_BenefitsModel;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_AddEmployeeToBenefitsController implements Initializable {
    ObservableList<HR4_EmpInfoClass> obj1 = FXCollections.observableArrayList();
    HR4_EmployeeInfo emp = new HR4_EmployeeInfo();
    
    @FXML
    private TableView<HR4_EmpInfoClass> tbl_AddBen;
    @FXML
    private TextField z;
    private TextField b;
    private TextField a;
    @FXML
    private TextField bal;
    @FXML
    private TextField idx;
    @FXML
    private TextField title;
    @FXML
    private TextField days;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bal.setText(HR4_NewCompensationFill.b);
        z.setText(HR4_NewCompensationFill.z);
        idx.setText(HR4_NewCompensationFill.idxx);
        title.setText(HR4_NewCompensationFill.a);
        days.setText(HR4_NewCompensationFill.d);
        this.generateTable1();
        this.populateTable1();
    
    }
    public void generateTable1() {

        tbl_AddBen.getItems().clear();
        tbl_AddBen.getColumns().removeAll(tbl_AddBen.getColumns());
        TableColumn<HR4_EmpInfoClass, String> employee_code = new TableColumn<>("EE/ER Code");
        TableColumn<HR4_EmpInfoClass, String> fnn = new TableColumn<>("Fullname");
        TableColumn<HR4_EmpInfoClass, String> job_id = new TableColumn<>("Position");
        TableColumn<HR4_EmpInfoClass, String> dept_id = new TableColumn<>("Department");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        employee_code.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().employee_code);
        fnn.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().fnn);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().job_id);
        dept_id.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmpInfoClass, String> param) -> param.getValue().dept_id);
        TableColumn<HR4_EmpInfoClass, Void> addButton = new TableColumn("More Options");
        Callback<TableColumn<HR4_EmpInfoClass, Void>, TableCell<HR4_EmpInfoClass, Void>> cellFactory
                = new Callback<TableColumn<HR4_EmpInfoClass, Void>, TableCell<HR4_EmpInfoClass, Void>>() {
            @Override
            public TableCell<HR4_EmpInfoClass, Void> call(final TableColumn<HR4_EmpInfoClass, Void> param) {

            final TableCell<HR4_EmpInfoClass, Void> cell = new TableCell<HR4_EmpInfoClass, Void>() {
            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.USER_PLUS);
            private final Button btn = new Button("Add", f);
            {
                f.getStyleClass().add("fontIconTable");
                btn.getStyleClass().add("btnTable");
                btn.setGraphic(f);
                btn.setOnAction(event -> {
                String idxx = idx.getText();
                String balC = bal.getText();
                String dd = days.getText();
                String tt = title.getText();
                HR4_EmpInfoClass fc = (HR4_EmpInfoClass) getTableRow().getItem();
                HR4_EmployeeFill.EmpFill(
                fc.employee_code.getValue(),
                fc.fnn.getValue());
                
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("HR4_Saving.fxml"));
                try{
                Loader.load();
                }catch(IOException ex){
                    Logger.getLogger(HR4_AddEmployeeToBenefitsController.class.getName()).log(Level.SEVERE,null,ex);
                }
                HR4_SavingController sc = Loader.getController();
                sc.setText(idxx, balC, dd, tt);
                
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();
                });}
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
        //</editor-fold>
        tbl_AddBen.getColumns()
                .addAll(employee_code, fnn, job_id, dept_id, addButton);
    }
   

    
    long DummyCount = 0;
    long GlobalCount = 0;
    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    emp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tbl_AddBen.getItems();
                        List rs = emp
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "tblDD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "tblD", "=", "employee_code")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "tblD", "job_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                                .get(
                                        "tblD.employee_code",
                                        "CONCAT(tblDD.lastname,', ',tblDD.firstname,' ',tblDD.middlename,'.') as fnn",
                                        "aerolink.tbl_hr4_jobs.title as job_id",
                                        "aerolink.tbl_hr4_department.dept_name as dept_id"
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
        tbl_AddBen.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String employee_code = String.valueOf(crow.get("employee_code"));
            String fnn = (String) crow.get("fnn");
            String job_id = (String) crow.get("job_id");
            String dept_id = (String) crow.get("dept_id");
            String status_id = (String) crow.get("status_id");
            obj1.add(new HR4_EmpInfoClass(employee_code, fnn, job_id, dept_id, status_id));
        }

        tbl_AddBen.setItems(obj1);

    }
}
