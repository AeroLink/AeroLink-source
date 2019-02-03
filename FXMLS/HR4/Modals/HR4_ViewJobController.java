/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_EmpInfoClass;
import FXMLS.HR4.Filler.HR4_MIZ;
import Model.HR4_Classification;
import Model.HR4_Departments;
import Model.HR4_Designation;
import Model.HR4_JobLimits;
import FXMLS.HR4.Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_ViewJobController implements Initializable {

    @FXML
    private Label lblJob;
    @FXML
    private JFXComboBox cboDes;
    @FXML
    private JFXComboBox cboClass;
    @FXML
    private JFXComboBox cboDept;
    @FXML
    private TextArea txtDesc;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnEdit;

    HR4_Departments dept = new HR4_Departments();
    HR4_Designation designation = new HR4_Designation();
    HR4_Classification classification = new HR4_Classification();
    HR4_Jobs jobs = new HR4_Jobs();

    StringProperty jobLimit;
    StringProperty jobOpen;

    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtLimit;
    @FXML
    private TextField txtOpen;
    @FXML
    private StackPane stackpane;
    private JFXToggleButton switchOpen;

    ExecutorService e = Executors.newFixedThreadPool(1);
    @FXML
    private TableView<TOpen> tbl_openJobs;
    @FXML
    private JFXButton btnNewJobOpening;

    ObservableList<TOpen> objTOPEN = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {

        System.err.println("Testing mis " + HR4_MIZ.id);

        lblJob.setText(HR4_MIZ.t);
        cboDept.getItems().add(HR4_MIZ.dpt);
        cboDes.getItems().add(HR4_MIZ.des);
        cboClass.getItems().add(HR4_MIZ.c);
        txtDesc.setText(HR4_MIZ.d);
        jobID.idProperty().set(HR4_MIZ.id);
        cboDept.getSelectionModel().selectFirst();
        cboDes.getSelectionModel().selectFirst();
        cboClass.getSelectionModel().selectFirst();

        Platform.runLater(() -> getTotalPopulation(HR4_MIZ.id));

        List<HashMap> limit = jobs.where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", HR4_MIZ.id}
        }).get("aerolink.tbl_hr4_jobs.population_limit");

        jobLimit = new SimpleStringProperty(String.valueOf(limit.get(0).get("population_limit")));
        txtLimit.textProperty().bind(jobLimit);

        List<HashMap> totalOpen = jobs
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_limit", "job_id", "=", "job_id")
                .where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", HR4_MIZ.id}
        }).get("COALESCE(SUM(aerolink.tbl_hr4_job_limit.jobOpen), 0) as totalOpen");

        txtOpen.setText(totalOpen.get(0).get("totalOpen").toString());
        
        this.generateTable();
        this.renderData();
        
        btnNewJobOpening.setOnAction(e -> createDialog());

    }

    public void generateTable() {
        tbl_openJobs.getColumns().removeAll(tbl_openJobs.getColumns());

        TableColumn<TOpen, String> eType = new TableColumn<>("Employment Status");
        TableColumn<TOpen, String> charP = new TableColumn<>("Add. Character Preference");
        TableColumn<TOpen, String> numP = new TableColumn<>("Number of Positions Opened");
        TableColumn<TOpen, String> eSalary = new TableColumn<>("Salary");
        
        eType.setCellValueFactory(param -> param.getValue().statusType);
        charP.setCellValueFactory(param -> param.getValue().char_pref);
        numP.setCellValueFactory(param -> param.getValue().num_open);
        eSalary.setCellValueFactory(param -> param.getValue().salary);

        tbl_openJobs.getColumns().addAll(eType, charP, numP, eSalary);

    }

    public void renderData() {
        tbl_openJobs.getItems().removeAll(tbl_openJobs.getItems());
        
        List<HashMap> op = new HR4_JobLimits().where(new Object[][]{
            {"aerolink.tbl_hr4_job_limit.job_id", "=", HR4_MIZ.id}
        }).get();
        
        for(HashMap row : op) {
           objTOPEN.add(
                   new TOpen(
                           row.get("status_type").toString().equals("0") ? "Full Time" : "Part Time", 
                           row.get("additional_character_preference").toString(), 
                           row.get("jobOpen").toString(), 
                           row.get("salary").toString())
           );
        }
        
        tbl_openJobs.setItems(objTOPEN);
    }

    public List getTotalPopulation(String id) {
        List l = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "job_id", "=", "job_id").where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", id}
        }).get("COUNT(*) as population");
        applyPopulation(l);
        return l;
    }

    public void applyPopulation(List population) {
        for (Object d : population) {
            HashMap hash = (HashMap) d;
            txtTotal.setText(String.valueOf(hash.get("population")).isEmpty() ? "0" : String.valueOf(hash.get("population")));
        }
    }

    @FXML
    private void editJob(ActionEvent event) {
        txtDesc.setEditable(true);
        populateComboBox();
        btnUpdate.setDisable(false);
        txtLimit.setEditable(true);
    }

    @FXML
    private void submitJob(ActionEvent event) {

        Boolean rs = jobs.update(new Object[][]{
            {"dept_id", cboDept.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"classification_id", cboClass.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"designation_id", cboDes.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"description", txtDesc.getText()}
        }).where(new Object[][]{
            {"job_id", "=", jobID.idProperty().get()}
        }).executeUpdate();

        if (rs) {
            Helpers.EIS_Response.SuccessResponse("Successfully Updated", lblJob.getText() + " is successfuly updated");
        }

    }

    public void populateComboBox() {
        List dept_list = dept.get();
        List designation_list = designation.get();
        List classification_list = classification.get();

        for (Object o : dept_list) {
            HashMap hm = (HashMap) o;
            cboDept.getItems().add(hm.get("id") + " - " + hm.get("dept_name"));
        }

        for (Object o : classification_list) {
            HashMap hm = (HashMap) o;
            cboClass.getItems().add(hm.get("id") + " - " + hm.get("class_name"));
        }

        for (Object o : designation_list) {
            HashMap hm = (HashMap) o;
            cboDes.getItems().add(hm.get("id") + " - " + hm.get("designation"));
        }
    }

    public void createDialog() {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Opening Job Positions"));

        Label lbl = new Label("How many positions do you want to open?");
        Spinner num = new Spinner(0, Integer.parseInt(jobLimit.get()), 0);

        Label lbl_2 = new Label("What employment status?");
        String[] listCombo = {"Full Time", "Part Time"};
        ComboBox<String> combo = new ComboBox();
        combo.getItems().addAll(listCombo);
        combo.getSelectionModel().selectFirst();

        Label lbl_3 = new Label("Additional Character Reference (Optional)");
        TextArea text = new TextArea("");

        Label lbl_4 = new Label("Job Salary");
        TextField salary = new TextField("");
        
        VBox vbox = new VBox(lbl, num, new Label(""), lbl_2, combo, new Label(""), lbl_3, text, new Label(""), lbl_4, salary);

        layout.setBody(vbox);

        JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);

        JFXButton btn = new JFXButton("Submit");
        JFXButton btnCancel = new JFXButton("Cancel");
        JFXButton btnDummy = new JFXButton(" ");

        btnDummy.setDisable(true);

        btn.setOnAction((event) -> {

            Boolean t = new HR4_JobLimits().insert(new Object[][]{
                {"job_id", HR4_MIZ.id},
                {"jobOpen", num.getValue()},
                {"status_type", combo.getSelectionModel().getSelectedIndex()},
                {"additional_character_preference", text.getText()},
                {"salary", salary.getText()}
            });

            if (t) {
                Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully opened with " + num.getValue() + " " + (Integer.parseInt(num.getValue().toString()) <= 1 ? "position" : "positions."));
                dialog.close();
            }
        });

        btnCancel.setOnAction((event) -> {
            dialog.close();
        });

        btn.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        layout.setActions(btn, btnDummy, btnCancel);
        dialog.show();
//        if (Integer.parseInt(jobOpen.get()) == 0) {
//
//        } else {
//            JFXDialogLayout layout = new JFXDialogLayout();
//            layout.setHeading(new Text("Closing Job Position"));
//
//            Label lbl = new Label("Are you sure you want to close this position?");
//            VBox vbox = new VBox(lbl);
//            layout.setBody(vbox);
//
//            JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);
//
//            JFXButton btn = new JFXButton("Submit");
//            JFXButton btnCancel = new JFXButton("Cancel");
//            JFXButton btnDummy = new JFXButton(" ");
//
//            btnDummy.setDisable(true);
//
//            btn.setOnAction((event) -> {
//
//                Boolean t = new HR4_JobLimits().update(new Object[][]{
//                    {"jobOpen", 0}
//                }).where(new Object[][]{
//                    {"job_id", "=", HR4_MIZ.id}
//                }).executeUpdate();
//
//                if (t) {
//                    Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully Closed");
//                    switchOpen.setSelected(false);
//                    dialog.close();
//                }
//            });
//
//            btnCancel.setOnAction((event) -> {
//                dialog.close();
//            });
//
//            btn.getStyleClass().add("btn-primary");
//            btnCancel.getStyleClass().add("btn-danger");
//
//            layout.setActions(btn, btnDummy, btnCancel);
//            dialog.show();
//        }

    }

    private void toggleJob(ActionEvent event) {

        if (switchOpen.isSelected()) {
            createDialog();
        } else {
            if (Integer.parseInt(txtTotal.getText()) < Integer.parseInt(txtLimit.getText())) {
                createDialog();
            } else {
                Helpers.EIS_Response.ErrorResponse("Opps!", "Total Population is already on its limit, Adjust the job limit");
            }
        }

    }

    public class TOpen {

        SimpleStringProperty statusType;
        SimpleStringProperty char_pref;
        SimpleStringProperty num_open;
        SimpleStringProperty salary;

        public TOpen(String statusTypeS, String char_prefS, String num_openS, String salaryS) {
            this.statusType = new SimpleStringProperty(statusTypeS);
            this.char_pref = new SimpleStringProperty(char_prefS);
            this.num_open = new SimpleStringProperty(num_openS);
            this.salary = new SimpleStringProperty(salaryS);
        }
    }
}
