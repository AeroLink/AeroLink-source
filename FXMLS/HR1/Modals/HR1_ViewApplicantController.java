/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import Model.HR1.HR1_Applicants;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_ViewApplicantController implements Initializable {

    HR1_Applicants applicant = new HR1_Applicants();
    HR1_Applicants applicant_pivot = new HR1_Applicants(true);
    HR1_Applicants applicant_answers = new HR1_Applicants("pre_screening");
    HR1_Applicants applicant_schedules = new HR1_Applicants("app_schedule");

    @FXML
    private BorderPane centerDrop;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private Label lblAppFull;
    @FXML
    private FontAwesomeIconView jobID1;
    @FXML
    private Label lblAppFull11;
    @FXML
    private Label lblAppFull111;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtCivilStatus;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextField txtEducAttain;
    @FXML
    private TextField txtPrevSchool;
    @FXML
    private Label lblWorkFlowName;
    @FXML
    private Label lblWorkflowDesc;
    @FXML
    private TextField txtPercentage;
    @FXML
    private TextArea txtRemarks;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private ListView<?> listSchedules;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private StackPane spane;
    @FXML
    private AnchorPane workflowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lblAppFull.setText(HR1_Applicant.fullname);
        jobTitle.setText("Applying for " + HR1_Applicant.job_title);
        txtEducAttain.setText(HR1_Applicant.educAttain);
        txtPrevSchool.setText(HR1_Applicant.school);
        txtEmail.setText(HR1_Applicant.email);

        applicant.join(Model.JOIN.INNER, "aerolink.tbl_hr1_civil_status", "id", "=", "civil_status_id").where(new Object[][]{
            {"aerolink.tbl_hr1_applicants.id", "=", HR1_Applicant.app_id}
        }).get().stream().forEach(current -> {

            HashMap row = (HashMap) current;
            txtDate.setText(row.get("date_of_birth").toString());
            txtPlace.setText(row.get("place_of_birth").toString());
            txtGender.setText(row.get("gender").toString());
            txtCivilStatus.setText(row.get("civil_status").toString());
            txtHeight.setText(row.get("height").toString());
            txtWeight.setText(row.get("weight").toString());
            txtContactNumber.setText(row.get("contact_number").toString());
            HR1_Applicant.ansPivot = row.get("answerpivot_id").toString();

        });

        if (HR1_Applicant.stage_id.equals("0")) {
            workflowPane.setDisable(true);
            ProceedToInit();
        }
    }

    @FXML
    private void btnViewPreScreen(ActionEvent event) {
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("Viewing Answers of Pre-Screening Questions"));

        TextArea ans1 = new TextArea();
        TextArea ans2 = new TextArea();
        TextArea ans3 = new TextArea();
        TextArea ans4 = new TextArea();
        TextArea ans5 = new TextArea();
        TextArea ans6 = new TextArea();

        applicant_answers.where(new Object[][]{
            {"answerpivot_id", "=", HR1_Applicant.ansPivot}
        }).get().stream().forEach(row -> {
            HashMap current = (HashMap) row;

            ans1.setText(current.get("Q1").toString());
            ans2.setText(current.get("Q2").toString());
            ans3.setText(current.get("Q3").toString());
            ans4.setText(current.get("Q4").toString());
            ans5.setText(current.get("Q5").toString());
            ans6.setText(current.get("Q6").toString());

        });

        ans1.setEditable(false);
        ans2.setEditable(false);
        ans3.setEditable(false);
        ans4.setEditable(false);
        ans5.setEditable(false);
        ans6.setEditable(false);

        ans1.setWrapText(true);
        ans2.setWrapText(true);
        ans3.setWrapText(true);
        ans4.setWrapText(true);
        ans5.setWrapText(true);
        ans6.setWrapText(true);

        VBox vbox = new VBox(
                new Label(""),
                new Label("#1: Why do you want to work for this company?"),
                ans1,
                new Label(""),
                new Label("#2: Why are you looking for a new job?"),
                ans2,
                new Label(""),
                new Label("#3: What are two key contributions you can bring to this role?"),
                ans3,
                new Label(""),
                new Label("#4: What tools or technology do you use to stay organized?"),
                ans4,
                new Label(""),
                new Label("#5: What motivates you to perform your best work?"),
                ans5,
                new Label(""),
                new Label("#6: What is your perfect work environment?"),
                ans6
        );

        layout.setBody(vbox);

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        dialog.show();
    }

    @FXML
    private void btnDownloadCV(ActionEvent event) {
    }

    @FXML
    private void btnAppSched(ActionEvent event) {
    }

    public void ProceedToInit() {
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("Proceeding to Initial Interview"));

        DatePicker dt = new DatePicker();

        VBox vbox = new VBox(
                new Label(""),
                new Label("Set a Schedule for Initial Interview"),
                dt,
                new Label("")
        );

        JFXButton btnSubmitInit = new JFXButton("Submit");

        btnSubmitInit.getStyleClass().add("btn-primary");

        btnSubmitInit.setOnMouseClicked(event -> {
            LocalDate f = dt.getValue();
            f.format(DateTimeFormatter.ISO_LOCAL_DATE);
            if (applicant_schedules.insert(new Object[][]{
                {"app_id", HR1_Applicant.app_id},
                {"scheduled_date", f.toString()},
                {"purpose", "Initial Interview"}
            })) {
                
                applicant_pivot.update(new Object[][]{
                    {"status", 1}
                }).where(new Object[][]{
                    {"app_id", "=", HR1_Applicant.app_id}
                }).executeUpdate();

                // Helpers.EIS_Response.SuccessResponse("Success", "Applicant was successfully transfered to Initial Interview Stage");
            }
        });

        layout.setBody(vbox);
        layout.setActions(btnSubmitInit);

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        dialog.show();
    }

}
