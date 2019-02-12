/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import FXMLS.HR1.ClassFiles.HR1_GenerateEC;
import FXMLS.HR1.ClassFiles.TableModel_Schedules;
import Model.HR1.HR1_AppExam;
import Model.HR1.HR1_AppStages;
import Model.HR1.HR1_Applicants;
import Model.HR1.HR1_JobOffers;
import Model.HR1.JobPosting;
import Model.HR1.JobVacancy;
import Model.HR2_Examination;
import Model.HR4.HR4_Employee;
import Model.UserPermissions;
import Model.Users;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.STORED_PROC;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_ViewApplicantController implements Initializable {

    HR1_JobOffers j = new HR1_JobOffers();
    HR1_Applicants applicant = new HR1_Applicants();
    HR1_Applicants applicant_pivot = new HR1_Applicants(true);
    HR1_Applicants applicant_answers = new HR1_Applicants("pre_screening");
    HR1_Applicants applicant_schedules = new HR1_Applicants("app_schedule");
    HR1_AppStages hiring_stages = new HR1_AppStages();
    HR1_AppStages hiring_stages_results = new HR1_AppStages("stage_results");

    StringProperty StageID;

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
    private TableView<TableModel_Schedules> listSchedules;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private StackPane spane;
    @FXML
    private AnchorPane workflowPane;
    @FXML
    private JFXButton btn0;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn6;
    @FXML
    private JFXButton btn5;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private MenuItem menuER;
    @FXML
    private MenuItem menuJobOffer;
    @FXML
    private MenuItem menuEC;
    @FXML
    private MenuItem menuWR;
    @FXML
    private AnchorPane jobOfferPane;
    @FXML
    private JFXButton btnOfferAccepted;
    @FXML
    private JFXButton btnOfferDeclined;
    @FXML
    private AnchorPane paneRatings;
    @FXML
    private MenuItem menuDeny;
    @FXML
    private AnchorPane paneContract;
    @FXML
    private TextArea txtpath;
    @FXML
    private MenuItem menuHiring;
    @FXML
    private AnchorPane panelExamResult;
    @FXML
    private Label lblExamResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        xInit();
    }

    public void closeWind() {
        // get a handle to the stage
        Stage stage = (Stage) lblAppFull.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void xInit() {
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

        StageID = new SimpleStringProperty(" ");

        StageID.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            generateWorkflow();
        });

        StageID.setValue(HR1_Applicant.stage_id);

        this.generateSchedules();
        this.renderSchedules();

        btnSubmit.setOnAction(event -> {
            if (hiring_stages_results.insert(new Object[][]{
                {"app_id", HR1_Applicant.app_id},
                {"stage_id", StageID.getValue()},
                {"result", txtPercentage.getText()},
                {"remarks", txtRemarks.getText()}
            })) {
                StageID.setValue(String.valueOf(Integer.parseInt(StageID.getValue()) + 1));
                applicant_pivot.update(new Object[][]{
                    {"status", StageID.getValue()}
                }).where(new Object[][]{
                    {"app_id", "=", HR1_Applicant.app_id}
                }).executeUpdate();

                Helpers.EIS_Response.SuccessResponse("Success", "Applicant was successfully evaluated.. This window will be close for update.");
                closeWind();
            }
        });

        menuWR.setOnAction(value -> viewResults());
        menuDeny.setOnAction(value -> {
            DenyApplication();
        });

        btnOfferDeclined.setOnMouseClicked(value -> DeniedApplication());
        btnOfferAccepted.setOnMouseClicked(value -> AcceptedOffer());
    }

    public void generateWorkflow() {
        if (StageID.getValue().equals("0")) {
            workflowPane.setDisable(true);
            ProceedToInit();
        } else {
            OpeningStages(StageID.getValue());

            hiring_stages.where(new Object[][]{
                {"stage_id", "=", StageID.getValue()}
            }).get().stream().forEach(event -> {
                HashMap row = (HashMap) event;

                lblWorkFlowName.setText(row.get("stage_name").toString());
                lblWorkflowDesc.setText(row.get("stage_description").toString());

            });

            paneRatings.setVisible(true);

            if (Integer.parseInt(StageID.getValue()) >= 2) {
                menuER.setDisable(false);
            }

            if (Integer.parseInt(StageID.getValue()) < 2 || Integer.parseInt(StageID.getValue()) > 2) {
                panelExamResult.setVisible(false);
            }

            if (Integer.parseInt(StageID.getValue()) == 2) {
                panelExamResult.setVisible(true);
                checkExam();
            }

            if (Integer.parseInt(StageID.getValue()) >= 5) {

                if (j.where(new Object[][]{{"app_id", "=", HR1_Applicant.app_id}}).get().stream().count() == 0) {
                    btnOfferAccepted.setVisible(false);
                    btnOfferDeclined.setVisible(false);
                } else {
                    btnOfferAccepted.setVisible(true);
                    btnOfferDeclined.setVisible(true);
                }

                paneRatings.setVisible(false);
                jobOfferPane.setVisible(true);
                menuJobOffer.setDisable(false);
            }

            if (Integer.parseInt(StageID.getValue()) == 6) {

                jobOfferPane.setVisible(false);
                paneRatings.setVisible(false);
                menuEC.setDisable(false);
                paneContract.setVisible(true);
            }
        }

        menuHiring.setOnAction(value -> this.Hiring());
        menuEC.setOnAction(value -> this.ECGen());
        menuER.setOnAction(value -> this.generateExam());
    }

    long DummyC = 0;
    long GlobalC = 0;
    ExamResult exam_result = new ExamResult();

    public void checkExam() {
        CompletableFuture.supplyAsync(() -> {

            while (true) {
                exam_result.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyC = Long.parseLong(((HashMap) row).get("chk").toString());
                });

                if (GlobalC != DummyC) {
                    List<HashMap> count = exam_result.where(new Object[][]{
                        {"applicant_id", "=", HR1_Applicant.app_id}
                    }).groupBy("applicant_id").get("COUNT(*) as cox");

                    if (Integer.parseInt(count.get(0).get("cox").toString()) > 0) {
                        List<HashMap> exr = exam_result.where(new Object[][]{
                            {"applicant_id", "=", HR1_Applicant.app_id}
                        }).get();

                        exr.stream().forEach(e -> {
                            if (e.get("isTaken").toString().equals("0")) {
                                Platform.runLater(() -> {
                                    panelExamResult.setVisible(true);
                                    lblExamResult.setText("Exam was not yet taken by the applicant");
                                    btnSubmit.setDisable(true);
                                });
                            } else {
                                Platform.runLater(() -> {
                                    panelExamResult.setVisible(true);
                                    lblExamResult.setText("The Applicant got " + e.get("score").toString() + "% of score");
                                    txtPercentage.setText(e.get("score").toString());
                                    btnSubmit.setDisable(false);
                                });
                            }
                        });
                    } else {

                        Platform.runLater(() -> {
                            lblExamResult.setText("Please, Apply an Examination for the Applicant : 'Workflow Procedures > Apply an examination' ");
                            btnSubmit.setDisable(true);
                        });
                    }

                    GlobalC = DummyC;
                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR1_ViewApplicantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }, Session.SessionThreads);
    }
    String jid = "";
    int rjid = 0;

    public void generateExam() {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Apply Examination!"));
        ComboBox<String> combo = new ComboBox();
        JobPosting jp = new JobPosting();

        jp.where(new Object[][]{
            {"id", "=", HR1_Applicant.jobPosted_id}
        }).get().stream().forEach(action -> {
            jid = ((HashMap) action).get("jobPosted_id").toString();
        });

        JobVacancy jv = new JobVacancy();

        jv.where(new Object[][]{
            {"id", "=", jid}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            rjid = (Integer.parseInt(row.get("job_id").toString()));
        });

        HR2_Examination exams = new HR2_Examination();
        List<HashMap> list = exams.join(Model.JOIN.INNER, "aerolink.tbl_hr2_courses", "course_id", "=", "course_id").where(new Object[][]{
            {"aerolink.tbl_hr2_courses.job_id", "=", rjid}
        }).get();

        list.stream().forEach((HashMap e) -> {
            combo.getItems().add("EXAM00" + e.get("exam_id").toString() + " - " + e.get("exam_name").toString());
        });

        combo.getSelectionModel().selectFirst();

        VBox vbox = new VBox(
                new Label("Please choose an exam that you want to apply for the applicant " + HR1_Applicant.fullname),
                new Label(""),
                new Label("You could change the exam of the applicant as long as it wasn't already taken and already have a score"),
                new Label(""),
                combo
        );

        layout.setBody(vbox);

        JFXButton btn = new JFXButton("Submit");
        JFXButton btncc = new JFXButton("Cancel");

        btn.getStyleClass().add("btn-primary");
        btncc.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btn.setOnMouseClicked(value -> {
            HR1_AppExam appEx = new HR1_AppExam();
            String examID = combo.getSelectionModel().getSelectedItem().split(" - ")[0].replace("EXAM00", "");

            appEx.where(new Object[][]{
                {"applicant_id", "=", HR1_Applicant.app_id}
            }).get("COUNT(*) as c").stream().forEach(e -> {
                HashMap hash = (HashMap) e;
                if (hash.get("c").toString().equals("0")) {
                    Boolean f = appEx.insert(new Object[][]{
                        {"exam_id", examID},
                        {"applicant_id", HR1_Applicant.app_id}
                    });

                    if (f) {
                        Notifications nBuilder = Notifications.create()
                                .title("Success")
                                .text("Exam was applied successfully")
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.CENTER);

                        nBuilder.showConfirm();
                        dialog.close();
                    }
                } else {
                    appEx.where(new Object[][]{
                        {"isTaken", "=", 1},
                        {"applicant_id", "=", HR1_Applicant.app_id}
                    }).get("COUNT(*) as c").stream().forEach(ex -> {
                        HashMap hashx = (HashMap) ex;

                        if (hashx.get("c").toString().equals("0")) {
                            Boolean f = appEx.where(new Object[][]{
                                {"applicant_id", "=", HR1_Applicant.app_id}
                            }).update(new Object[][]{
                                {"exam_id", examID},}).executeUpdate();

                            if (f) {
                                Notifications nBuilder = Notifications.create()
                                        .title("Success")
                                        .text("Exam was applied successfully")
                                        .hideAfter(Duration.seconds(5))
                                        .position(Pos.CENTER);

                                nBuilder.showConfirm();
                                dialog.close();
                            }
                        } else {
                            Notifications nBuilder = Notifications.create()
                                    .title("Success")
                                    .text("Applicant has already taken the exam")
                                    .hideAfter(Duration.seconds(2))
                                    .position(Pos.CENTER);

                            nBuilder.showError();
                        }

                    });
                }
            });

        });

        btncc.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setActions(btn, new JFXButton(), btncc);
        dialog.show();
    }

    public void OpeningStages(String id) {
        JFXButton[] Stages_Buttons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6};

        for (int i = 0; i < Stages_Buttons.length; i++) {
            if (i <= Integer.parseInt(id)) {
                Stages_Buttons[i].setDisable(false);
            } else {
                Stages_Buttons[i].setDisable(true);
            }
        }

    }

    public void generateSchedules() {
        listSchedules.getItems().clear();
        listSchedules.getColumns().removeAll(listSchedules.getColumns());

        TableColumn<TableModel_Schedules, String> Sdate = new TableColumn<>("Scheduled Date");

        Sdate.setCellValueFactory(value -> value.getValue().combination);

        listSchedules.getColumns().add(Sdate);

    }

    public void renderSchedules() {
        ObservableList<TableModel_Schedules> scheds = FXCollections.observableArrayList();

        applicant_schedules.where(new Object[][]{
            {"app_id", "=", HR1_Applicant.app_id}
        }).get().stream().forEach(callback -> {
            HashMap row = (HashMap) callback;

            scheds.add(new TableModel_Schedules(
                    row.get("schedule_id").toString(),
                    row.get("purpose").toString(),
                    row.get("scheduled_date").toString(),
                    (row.get("scheduled_date").toString() + " - " + row.get("purpose").toString()),
                    row.get("status").toString()
            ));
        });

        listSchedules.getItems().clear();
        listSchedules.setItems(scheds);
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
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_PdfView.fxml").getParent());
        md.open();
    }

    @FXML
    private void btnAppSched(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_ApplicantSched.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest((event1) -> {
            HR1_ApplicantSchedController.modalOpen = false;
        });

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

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btnSubmitInit.setOnMouseClicked(event -> {
            if (!dt.getValue().isBefore(LocalDate.now())) {
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

                    Helpers.EIS_Response.SuccessResponse("Success", "Applicant was successfully transfered to Initial Interview Stage");
                    dialog.close();
                    closeWind();

                }
            } else {
                Helpers.EIS_Response.ErrorResponse("Halt!", "You could not select date before the current date \n" + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            }

        });

        layout.setBody(vbox);
        layout.setActions(btnSubmitInit);

        dialog.show();
    }

    @FXML
    private void generateJobOffer(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_JobOffer.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(value -> {
            if (!HR1_JobOfferController.dateExpire.equals("x")) {

                if (applicant_schedules.insert(new Object[][]{
                    {"app_id", HR1_Applicant.app_id},
                    {"scheduled_date", HR1_JobOfferController.dateExpire},
                    {"purpose", "Job Offer Expiration"}
                })) {
                    Helpers.EIS_Response.SuccessResponse("Job Offer Expiration is set", HR1_JobOfferController.dateExpire + " was set to applicant as job offer exipiration");
                }
            }
        });
    }

    private void viewResults() {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_AppStages.fxml").getParent());
        md.open();
    }

    public void DenyApplication() {

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Are you sure you want to deny this application?"));

        layout.setBody(new Text("Closing or Denying this applicant will automatically archived the application"));

        JFXButton btn = new JFXButton("Execute");
        JFXButton btncc = new JFXButton("Cancel");

        btn.getStyleClass().add("btn-primary");
        btncc.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btn.setOnMouseClicked(value -> {
            if (applicant_pivot.update(new Object[][]{
                {"status", 99}
            }).where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Closing the application complete");
                dialog.close();
                offerOtherJob();
            }
        });

        btncc.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setActions(btn, new JFXButton(), btncc);
        dialog.show();

    }

    public void DeniedApplication() {

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Denied Job Offer"));

        layout.setBody(new Text("You were about to archieve this application. Because the applicant deny the job offer. Press Execute to continue"));

        JFXButton btn = new JFXButton("Execute");
        JFXButton btncc = new JFXButton("Cancel");

        btn.getStyleClass().add("btn-primary");
        btncc.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btn.setOnMouseClicked(value -> {
            if (applicant_pivot.update(new Object[][]{
                {"status", 101}
            }).where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Closing the application complete, Due of denied job offer.");
                dialog.close();
            }
        });

        btncc.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setActions(btn, new JFXButton(), btncc);
        dialog.show();

    }

    public void offerOtherJob() {

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Do you want to offer an posted job?"));

        ComboBox combo = new ComboBox();

        JobPosting jp = new JobPosting();
        jp.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            combo.getItems().add(row.get("id").toString() + " - " + row.get("title").toString());
        });

        VBox vbox = new VBox(
                new Text("As this applicantion was deny, Selecting a job below will transfer the application to other posted job?. "),
                new Label(""),
                combo
        );

        layout.setBody(vbox);

        JFXButton btn = new JFXButton("Offer New Job");
        JFXButton btncc = new JFXButton("Cancel");

        btn.getStyleClass().add("btn-primary");
        btncc.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btn.setOnMouseClicked(value -> {

            hiring_stages_results.delete().where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate();

            applicant_schedules.delete().where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate();

            if (applicant_pivot.update(new Object[][]{
                {"status", 0},
                {"jobPosted_id", combo.getSelectionModel().getSelectedItem().toString().split(" - ")[0]}
            }).where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Applicant Successfully migrated to other job");
                dialog.close();
                ((Stage) ((Node) value.getSource()).getScene().getWindow()).close();
            }

            //TODO: Email the applicant if the applicant was transfered
        });

        btncc.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setActions(btn, new JFXButton(), btncc);
        dialog.show();

    }

    public void AcceptedOffer() {

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Job Offer Accepted"));

        DatePicker dt = new DatePicker();

        VBox vbox = new VBox(
                new Label(""),
                new Label("Transitioning applicant to Contract Signing, Set a date for Contract Signing"),
                dt,
                new Label("")
        );

        layout.setBody(vbox);

        JFXButton btn = new JFXButton("Submit");
        JFXButton btncc = new JFXButton("Cancel");

        btn.getStyleClass().add("btn-primary");
        btncc.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btn.setOnMouseClicked(value -> {

            if (!dt.getValue().isBefore(LocalDate.now())) {
                LocalDate f = dt.getValue();
                f.format(DateTimeFormatter.ISO_LOCAL_DATE);
                if (applicant_schedules.insert(new Object[][]{
                    {"app_id", HR1_Applicant.app_id},
                    {"scheduled_date", f.toString()},
                    {"purpose", "Contract Signing"}
                })) {

                    applicant_pivot.update(new Object[][]{
                        {"status", 6}
                    }).where(new Object[][]{
                        {"app_id", "=", HR1_Applicant.app_id}
                    }).executeUpdate();

                    Helpers.EIS_Response.SuccessResponse("Success", "Applicant was successfully transfered to Contract Signing Stage");
                    dialog.close();

                }
            } else {
                Helpers.EIS_Response.ErrorResponse("Halt!", "You could not select date before the current date \n" + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            }

            //TODO: Email the applicant if the applicant was transfered
        });

        btncc.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setActions(btn, new JFXButton(), btncc);
        dialog.show();

    }

    public File file;

    @FXML
    private void btnOpenFile(ActionEvent event) {
        FileChooser f = new FileChooser();
        file = f.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        txtpath.setText(file.getPath());

    }

    Double salary = 0.00;

    String job_id = "";
    int returnPositions = 0;
    int real_jobID = 0;

    @FXML
    private void btnSubmitHire(ActionEvent event) {
        this.Hiring();
    }

    public void Hiring() {

        if (txtpath.getText() != null) {
            //Insert First to Table Hr4
            HR4_Employee emp = new HR4_Employee();
            HR4_Employee profile = new HR4_Employee("profile");
            HR4_Employee job = new HR4_Employee("job");
            Users u = new Users();
            UserPermissions up = new UserPermissions();
            HR1_JobOffers j = new HR1_JobOffers();

            j.where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).get().stream().forEach(action -> {
                salary = Double.parseDouble(((HashMap) action).get("salary").toString().replace(",", ""));
            });

            int emp_id = emp.insert(new Object[][]{
                {"status_id", 1},
                {"type_id", 5},
                {"datehired", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)},
                {"salary", salary}
            }, true);

            String EmpCode = "EMP181900" + emp_id;

            int uid = u.insert(new Object[][]{
                {"username", EmpCode},
                {"password", Synapse.Crypt.Encrypt(EmpCode)}
            }, true);

            if (uid != 0) {
                up.insert(new Object[][]{
                    {"user_id", uid},
                    {"permission_id", 53}
                });
            }

            emp.update(new Object[][]{
                {"employee_code", EmpCode},
                {"login_id", uid}
            }).where(new Object[][]{
                {"id", "=", emp_id}
            }).executeUpdate();

            int p_id = profile.InsertIntoSelect(
                    new Object[]{
                        "firstname",
                        "lastname",
                        "middlename",
                        "suffix_id",
                        "date_of_birth",
                        "place_of_birth",
                        "gender",
                        "email",
                        "civil_status_id",
                        "height",
                        "weight",
                        "contact_number"
                    }, "aerolink.tbl_hr1_applicants",
                    new Object[]{
                        "firstname",
                        "lastname",
                        "middlename",
                        "suffix_id",
                        "date_of_birth",
                        "place_of_birth",
                        "gender",
                        "email",
                        "civil_status_id",
                        "height",
                        "weight",
                        "contact_number"
                    }, new Object[][]{
                        {"aerolink.tbl_hr1_applicants.id", "=", HR1_Applicant.app_id}
                    }, true);

            Helpers.FileTransfer.SendFile("127.0.0.1", file.getPath());

            //profileUpdate
            profile.update(new Object[][]{
                {"employee_code", "EMP181900" + emp_id},
                {"employement_contract", file.getName()}
            }).where(new Object[][]{
                {"id", "=", p_id}
            }).executeUpdate();

            applicant_pivot.update(new Object[][]{
                {"status", 100}
            }).where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).executeUpdate();
            JobVacancy jv = new JobVacancy();
            JobPosting jp = new JobPosting();

            jp.where(new Object[][]{
                {"id", "=", HR1_Applicant.jobPosted_id}
            }).get().stream().forEach(action -> {
                job_id = ((HashMap) action).get("jobPosted_id").toString();
            });

            jv.where(new Object[][]{
                {"id", "=", job_id}
            }).get().stream().forEach(action -> {
                HashMap row = (HashMap) action;
                returnPositions = (Integer.parseInt(row.get("jobOpen").toString()) - 1);
                real_jobID = (Integer.parseInt(row.get("job_id").toString()));

            });

            //JobUpdate
            job.insert(new Object[][]{
                {"employee_code", "EMP181900" + emp_id},
                {"job_id", real_jobID}
            });

            if (returnPositions <= 0) {

                jp.delete().where(new Object[][]{
                    {"id", "=", HR1_Applicant.jobPosted_id}
                }).executeUpdate();

                jv.delete().where(new Object[][]{
                    {"id", "=", job_id}
                }).executeUpdate();

            } else {
                jv.update(new Object[][]{
                    {"jobOpen", returnPositions},}).where(new Object[][]{
                    {"id", "=", job_id}
                }).executeUpdate();
            }

            Helpers.EIS_Response.SuccessResponse("Success", "Applicant was successfully hired and make some task for proper welcome at New Hire On Board Module ");

            //TODO : Send welcome message, thru email ..  send employment contract to server using FieTransfer
        }

    }

    String fileName = "";

    public void ECGen() {

        HR1_JobOffers jobOffers = new HR1_JobOffers();

        jobOffers.where(new Object[][]{
            {"app_id", "=", HR1_Applicant.app_id}
        }).get().stream().forEach(event -> {
            HashMap row = (HashMap) event;

            fileName = HR1_GenerateEC.generateEC(
                    HR1_Applicant.job_title,
                    "PHP " + NumberFormat.getInstance().format(Double.parseDouble(row.get("salary").toString().replace(",", ""))),
                    LocalDate.parse(row.get("start_date").toString()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)),
                    HR1_Applicant.fullname,
                    row.get("reportTo").toString(),
                    row.get("location").toString(),
                    "APPLICANT_" + HR1_Applicant.app_id);

        });

        Helpers.EIS_Response.SuccessResponse("Success", "Employee Contract " + fileName + " Generated");

    }

    class ExamResult extends Synapse.Model {

        public ExamResult() {
            this.initTable("tbl_hr1_applicant_exam");
        }

    }
}
