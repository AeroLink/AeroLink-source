/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import Model.HR1.JobPosting;
import Model.HR4_Jobs;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
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
    private TextArea txtDesc;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jobID.setId(HR1_PostJobSelection.jobID);
        txtOpen.setText(HR1_PostJobSelection.OpenPos);
        lblJob.setText(HR1_PostJobSelection.jobTitle);
        
        HR4_Jobs jobs = new HR4_Jobs();
        
        jobs.where(new Object[][] {
            {"job_id", "=", HR1_PostJobSelection.jobID}
        }).get().stream().forEach(row -> {
            txtDesc.appendText("Job Description\n\n" + ( (HashMap) row ).get("description") + "\n\n" );
        });
        
        Object[] cbo = {"Full Time", "Part Time"};
        cboStatus.getItems().addAll(cbo);
        
    }    

    @FXML
    private void submitPost(ActionEvent event) {
        JobPosting jp = new JobPosting();
        
        if(jp.insert(new Object[][] {
            {"jobPosted_id", HR1_PostJobSelection.id},
            {"title", lblJob.getText()},
            {"description", txtDesc.getText()},
            {"status", cboStatus.getSelectionModel().getSelectedItem().toString()},
            {"salary", txtSalary.getText()},
            {"publish_on", dtpPublish.getValue().toString()},
            {"until", dtpExpire.getValue().toString() }
                
        })) {
            Helpers.EIS_Response.SuccessResponse("Success", "Job was successfully Posted");
        }
    }
    
}
