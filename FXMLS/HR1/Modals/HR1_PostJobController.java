/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import FXMLS.HR1.HR1_RecruitmentController;
import Model.HR1.JobPosting;
import Model.HR1.JobVacancy;
import Model.HR2_CM_Pivot;
import Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_PostJobController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private Label lblJob;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private HTMLEditor txtDesc;
    @FXML
    private JFXButton btnPost;
    @FXML
    private TextField txtOpen;
    @FXML
    private JFXDatePicker dtpPublish;
    @FXML
    private JFXDatePicker dtpExpire;
    private ComboBox cboStatus;
    @FXML
    private TextField txtSalary;

    String htmlText = "";
    @FXML
    private TextField txtStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jobID.setId(HR1_PostJobSelection.jobID);
        txtOpen.setText(HR1_PostJobSelection.OpenPos);
        lblJob.setText(HR1_PostJobSelection.jobTitle);
        txtSalary.setText(HR1_PostJobSelection.salary);
        txtStatus.setText(HR1_PostJobSelection.status);

        if (txtSalary.getText().isEmpty()) {
            txtSalary.setText("0");
        } else {
            txtSalary.setText(NumberFormat.getInstance().format(Double.parseDouble(txtSalary.getText().replace(",", ""))));
        }
        
        HR4_Jobs jobs = new HR4_Jobs();

        jobs.where(new Object[][]{
            {"job_id", "=", HR1_PostJobSelection.jobID}
        }).get().stream().forEach(row -> {
            htmlText += "<h3>Job Description : </h3>  </br></br>" + ((HashMap) row).get("description") + "</br></br>";
        });

        htmlText += "<h3>Competencies : </h3>  </br></br><ul>";

        HR2_CM_Pivot competency = new HR2_CM_Pivot();

        competency.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "=", "skill_id")
                .where(new Object[][]{
            {"aerolink.tbl_hr4_jobs.job_id", "=", HR1_PostJobSelection.jobID}
        }).get("aerolink.tbl_hr2_skillset.skill as skill", "aerolink.tbl_hr2_skillset.skill_description as description").stream().forEach(row -> {
            htmlText += " <li><h5>" + ((HashMap) row).get("skill") + "</h5><p>" + ((HashMap) row).get("description") + "</p></li>";
        });

        htmlText += "</ul></br></br>";

        txtDesc.setHtmlText(htmlText);

    }

    @FXML
    private void submitPost(ActionEvent event) {
        JobPosting jp = new JobPosting();
        JobVacancy jv = new JobVacancy();

        try {
            int id = jp.insert(new Object[][]{
                {"jobPosted_id", HR1_PostJobSelection.id},
                {"title", lblJob.getText()},
                {"description", txtDesc.getHtmlText().replace("\"", "'").replace(",", ".").replace("contenteditable='true'", "")},
                {"status", txtStatus.getText()},
                {"salary", txtSalary.getText()},
                {"publish_on", dtpPublish.getValue().toString()},
                {"until", dtpExpire.getValue().toString()}

            }, true);

            if (id != 0) {

                jv.update(new Object[][]{
                    {"isPosted", 1}
                }).where(new Object[][]{
                    {"id", "=", HR1_PostJobSelection.id}
                }).executeUpdate();

                Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully Posted");
                HR1_RecruitmentController.refresher.setValue(true);
            }

        } catch (Exception e) {
            Helpers.EIS_Response.ErrorResponse("Wait!", "You got some error on your inputs");
        }
    }

}
