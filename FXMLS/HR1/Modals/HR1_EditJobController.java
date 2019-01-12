/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_EditJobSelection;
import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import Model.HR1.JobPosting;
import Model.HR4_JobLimits;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.glass.ui.Application;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_EditJobController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private Label lblJob;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private JFXButton btnPost;
    @FXML
    private TextField txtOpen;
    @FXML
    private JFXDatePicker dtpPublish;
    @FXML
    private JFXDatePicker dtpExpire;
    @FXML
    private ComboBox cboStatus;
    @FXML
    private TextField txtSalary;
    @FXML
    private HTMLEditor txtDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HR4_JobLimits jL = new HR4_JobLimits();

        lblJob.setText(HR1_EditJobSelection.title);

        jL.where(new Object[][]{
            {"id", "=", HR1_EditJobSelection.jPosted}
        }).get().stream().forEach(row -> {
            HashMap c = (HashMap) row;
            txtOpen.setText(c.get("jobOpen").toString());
        });

        JobPosting jp = new JobPosting();

        jp.where(new Object[][]{
            {"id", "=", HR1_EditJobSelection.id}
        }).get().stream().forEach(row -> {
            txtDesc.setHtmlText(((HashMap) row).get("description").toString());
        });

        dtpPublish.setValue(LocalDate.parse(HR1_EditJobSelection.publish_on));
        dtpExpire.setValue(LocalDate.parse(HR1_EditJobSelection.until));
        txtSalary.setText(HR1_EditJobSelection.salary);

        Object[] cbo = {"Full Time", "Part Time"};
        cboStatus.getItems().addAll(cbo);

        cboStatus.getSelectionModel().select(HR1_EditJobSelection.status);

    }

    @FXML
    private void submitPost(ActionEvent event) {
        JobPosting jp = new JobPosting();

        if (jp.update(new Object[][]{
            {"description", txtDesc.getHtmlText().replace("\"", "'").replace(",", ".")},
            {"status", cboStatus.getSelectionModel().getSelectedItem().toString()},
            {"salary", txtSalary.getText()},
            {"publish_on", dtpPublish.getValue().toString()},
            {"until", dtpExpire.getValue().toString()}
        }).where(new Object[][]{
            {"id", "=", HR1_EditJobSelection.id}
        }).executeUpdate()) {
            Helpers.EIS_Response.SuccessResponse("Success", lblJob.getText() + " post was updated");
        }

        //( (Stage) ( (Node) event.getSource() ).getScene().getWindow() ).close();
    }

    @FXML
    private void closeJob(ActionEvent event) {
        JobPosting jp = new JobPosting();

        jp.where(new Object[][]{
            {"id", "=", HR1_EditJobSelection.id}
        }).delete().executeUpdate();

        HR4_JobLimits jL = new HR4_JobLimits();

        jL.where(new Object[][]{
            {"id", "=", HR1_EditJobSelection.jPosted}
        }).update(new Object[][]{
            {"isPosted", 0}
        }).executeUpdate();

        Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully Close and unposted");

        //( (Stage) ( (Node) event.getSource() ).getScene().getWindow() ).close();
    }

}
