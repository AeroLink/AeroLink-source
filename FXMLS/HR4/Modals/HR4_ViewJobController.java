/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_MIZ;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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
    @FXML
    private JFXToggleButton switchOpen;

    ExecutorService e = Executors.newFixedThreadPool(1);

    public void initialize(URL url, ResourceBundle rb) {

        System.err.println("Testing mis " + HR4_MIZ.id);
        CompletableFuture
                .supplyAsync(() -> getLimit(HR4_MIZ.id), e)
                .thenAcceptAsync((limit) -> applyLimit(limit));

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

    }

    public List getLimit(String id) {
        return jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_limit", "job_id", "=", "job_id").where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", id}
        }).get("aerolink.tbl_hr4_job_limit.job_limit", "aerolink.tbl_hr4_job_limit.jobOpen");
    }

    public List getTotalPopulation(String id) {
        List l = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "job_id", "=", "job_id").where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", id}
        }).get("COUNT(*) as population");
        applyPopulation(l);
        return l;
    }

    public void applyLimit(List limit) {
        for (Object c : limit) {
            HashMap hash = (HashMap) c;

            jobLimit = new SimpleStringProperty(String.valueOf(hash.get("job_limit")));
            jobOpen = new SimpleStringProperty(String.valueOf(hash.get("jobOpen")));
            txtLimit.textProperty().bind(jobLimit);
            txtOpen.textProperty().bind(jobOpen);

        }

        if (Integer.parseInt(jobOpen.get()) > 0) {
            switchOpen.setSelected(true);
        }
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
        if (Integer.parseInt(jobOpen.get()) == 0) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setHeading(new Text("Opening Job Positions"));

            Label lbl = new Label("How many positions do you want to open?");
            Spinner num = new Spinner(0, Integer.parseInt(jobLimit.get()), 0);
            VBox vbox = new VBox(lbl, num);
            layout.setBody(vbox);

            JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);

            JFXButton btn = new JFXButton("Submit");
            JFXButton btnCancel = new JFXButton("Cancel");
            JFXButton btnDummy = new JFXButton(" ");

            btnDummy.setDisable(true);

            num.valueProperty().addListener((observable) -> {
                jobOpen.setValue(String.valueOf(num.getValue()));
            });

            btn.setOnAction((event) -> {

                Boolean t = new HR4_JobLimits().update(new Object[][]{
                    {"jobOpen", num.getValue()}
                }).where(new Object[][]{
                    {"job_id", "=", HR4_MIZ.id}
                }).executeUpdate();

                if (t) {
                    Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully opened with " + num.getValue() + " " + (Integer.parseInt(num.getValue().toString()) <= 1 ? "position" : "positions."));
                    switchOpen.setSelected(true);
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
        } else {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setHeading(new Text("Closing Job Position"));

            Label lbl = new Label("Are you sure you want to close this position?");
            VBox vbox = new VBox(lbl);
            layout.setBody(vbox);

            JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);

            JFXButton btn = new JFXButton("Submit");
            JFXButton btnCancel = new JFXButton("Cancel");
            JFXButton btnDummy = new JFXButton(" ");

            btnDummy.setDisable(true);

            btn.setOnAction((event) -> {

                Boolean t = new HR4_JobLimits().update(new Object[][]{
                    {"jobOpen", 0}
                }).where(new Object[][]{
                    {"job_id", "=", HR4_MIZ.id}
                }).executeUpdate();

                if (t) {
                    Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully Closed");
                    switchOpen.setSelected(false);
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
        }

    }

    @FXML
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

}
