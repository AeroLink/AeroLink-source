/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import FXMLS.HR1.ClassFiles.TableModel_Applicants;
import FXMLS.HR1.ClassFiles.TableModel_PostedJob;
import Model.HR1.HR1_Applicants;
import Model.HR1.JobPosting;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_Applicant_ManagementController implements Initializable {

    ObservableList<TableModel_PostedJob> leftTable = FXCollections.observableArrayList();

    long DummyCount = 0;
    long GlobalCount = 0;

    JobPosting jobPostings = new JobPosting();

    HR1_Applicants hr1PivotApp = new HR1_Applicants(true);
    HR1_Applicants applicants = new HR1_Applicants();

    @FXML
    private TableView<TableModel_PostedJob> tblOpenJobs;
    @FXML
    private TableView<TableModel_Applicants> tblApplicantList;
    @FXML
    private Label lbljobTitle;
    @FXML
    private Label lblNumApp;
    @FXML
    private Label lblnumViews;
    @FXML
    private ContextMenu contextMeuApp;
    @FXML
    private MenuItem menuItemView;
    @FXML
    private TableView<TableModel_Applicants> tblArchive;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.renderTable();
        this.PopulateTable();
        this.renderApplicants();
        this.RenderArchive();
        
        tblApplicantList.setContextMenu(contextMeuApp);

        tblOpenJobs.setOnKeyReleased(value -> {
            tblApplicantList.setDisable(false);
            lbljobTitle.setText("Job Title : " + tblOpenJobs.getSelectionModel().getSelectedItem().job_title.getValue());
            lblNumApp.setText("Number of applications : " + tblOpenJobs.getSelectionModel().getSelectedItem().applicants.getValue());
            lblnumViews.setText("Number of views : " + tblOpenJobs.getSelectionModel().getSelectedItem().views.getValue());
            populateApplicants(tblOpenJobs.getSelectionModel().getSelectedItem().id.getValue());

        });

        tblApplicantList.setOnMouseClicked(value -> {
            if (!tblApplicantList.isDisabled() || !tblApplicantList.getItems().isEmpty()) {
                if (value.getButton() == MouseButton.SECONDARY) {
                    contextMeuApp.show(tblApplicantList, value.getX(), value.getY());
                }

                if (value.getButton() == MouseButton.PRIMARY) {
                    if (value.getClickCount() == 2) {
                        viewingApplicant();
                    }
                }
            }
        });

        tblOpenJobs.setOnMouseClicked(value -> {
            tblApplicantList.setDisable(false);
            lbljobTitle.setText("Job Title : " + tblOpenJobs.getSelectionModel().getSelectedItem().job_title.getValue());
            lblNumApp.setText("Number of applications : " + tblOpenJobs.getSelectionModel().getSelectedItem().applicants.getValue());
            lblnumViews.setText("Number of views : " + tblOpenJobs.getSelectionModel().getSelectedItem().views.getValue());
            populateApplicants(tblOpenJobs.getSelectionModel().getSelectedItem().id.getValue());
        });

        menuItemView.setOnAction(value -> viewingApplicant());

    }

    public void viewingApplicant() {

        HR1_Applicant.app_id = tblApplicantList.getSelectionModel().getSelectedItem().id.getValue();
        HR1_Applicant.job_title = lbljobTitle.getText();
        HR1_Applicant.fullname = tblApplicantList.getSelectionModel().getSelectedItem().fullname.getValue();
        HR1_Applicant.school = tblApplicantList.getSelectionModel().getSelectedItem().prevSchool.getValue();
        HR1_Applicant.educAttain = tblApplicantList.getSelectionModel().getSelectedItem().educAttain.getValue();
        HR1_Applicant.email = tblApplicantList.getSelectionModel().getSelectedItem().email.getValue();
        HR1_Applicant.stage_id = tblApplicantList.getSelectionModel().getSelectedItem().status_id.getValue();
        HR1_Applicant.jobPosted_id = tblOpenJobs.getSelectionModel().getSelectedItem().id.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_ViewApplicant.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(event -> this.renderApplicants());

    }

    public void renderTable() {
        tblOpenJobs.getItems().clear();
        tblOpenJobs.getColumns().removeAll(tblOpenJobs.getColumns());

        TableColumn<TableModel_PostedJob, String> id = new TableColumn<>("Job Title");
        TableColumn<TableModel_PostedJob, String> views = new TableColumn<>("Views");
        TableColumn<TableModel_PostedJob, String> applicants = new TableColumn<>("Applications");
        TableColumn<TableModel_PostedJob, String> postingDate = new TableColumn<>("Posting Date");

        id.setCellValueFactory((param) -> param.getValue().job_title);
        views.setCellValueFactory(param -> param.getValue().views);
        applicants.setCellValueFactory(param -> param.getValue().applicants);
        postingDate.setCellValueFactory(param -> param.getValue().postingDate);

        tblOpenJobs.getColumns().addAll(id, views, applicants, postingDate);

    }

    public void PopulateTable() {
        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("applicant")) {
                try {

                    jobPostings.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    hr1PivotApp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tblOpenJobs.getItems().clear();
                        jobPostings.get().stream().forEach(row -> {

                            HashMap current = (HashMap) row;

                            String id = current.get("id").toString();
                            String job_title = current.get("title").toString();
                            String postingDate = current.get("created_at").toString();
                            String views = current.get("views").toString();

                            String applicants = String.valueOf(hr1PivotApp.where(new Object[][]{{"jobPosted_id", "=", current.get("id")}, {"status", "<", 99}}).get().stream().count());

                            leftTable.add(new TableModel_PostedJob(id, job_title, postingDate, views, applicants));

                        });

                        this.populateApplicantsArchive();
                        tblOpenJobs.setItems(leftTable);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (Exception ex) {
                    System.err.println("Exception -> " + ex.getMessage());
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    public void renderApplicants() {

        tblApplicantList.getItems().clear();
        tblApplicantList.getColumns().removeAll(tblApplicantList.getColumns());

        TableColumn<TableModel_Applicants, String> fullname = new TableColumn<>("Full Name");
        TableColumn<TableModel_Applicants, String> applicantCode = new TableColumn<>("Code");
        TableColumn<TableModel_Applicants, String> educAttain = new TableColumn<>("Education Attainment");
        TableColumn<TableModel_Applicants, String> email = new TableColumn<>("Email");
        TableColumn<TableModel_Applicants, String> prevSchool = new TableColumn<>("Previous School");
        TableColumn<TableModel_Applicants, String> status = new TableColumn<>("Application Status");

        fullname.setCellValueFactory(value -> value.getValue().fullname);
        applicantCode.setCellValueFactory(value -> value.getValue().applicant_code);
        educAttain.setCellValueFactory(value -> value.getValue().educAttain);
        prevSchool.setCellValueFactory(value -> value.getValue().prevSchool);
        email.setCellValueFactory(value -> value.getValue().email);
        status.setCellValueFactory(value -> value.getValue().status);

        tblApplicantList.getColumns().addAll(applicantCode, fullname, email, educAttain, prevSchool, status);
    }

    public void RenderArchive() {
        tblArchive.getItems().clear();
        tblArchive.getColumns().removeAll(tblArchive.getColumns());

        TableColumn<TableModel_Applicants, String> Arcfullname = new TableColumn<>("Full Name");
        TableColumn<TableModel_Applicants, String> ArcapplicantCode = new TableColumn<>("Code");
        TableColumn<TableModel_Applicants, String> ArceducAttain = new TableColumn<>("Education Attainment");
        TableColumn<TableModel_Applicants, String> Arcemail = new TableColumn<>("Email");
        TableColumn<TableModel_Applicants, String> ArcprevSchool = new TableColumn<>("Previous School");
        TableColumn<TableModel_Applicants, String> Arcstatus = new TableColumn<>("Application Status");

        Arcfullname.setCellValueFactory(value -> value.getValue().fullname);
        ArcapplicantCode.setCellValueFactory(value -> value.getValue().applicant_code);
        ArceducAttain.setCellValueFactory(value -> value.getValue().educAttain);
        ArcprevSchool.setCellValueFactory(value -> value.getValue().prevSchool);
        Arcemail.setCellValueFactory(value -> value.getValue().email);
        Arcstatus.setCellValueFactory(value -> value.getValue().status);

        tblArchive.getColumns().addAll(ArcapplicantCode, Arcfullname, Arcemail, ArceducAttain, ArcprevSchool, Arcstatus);
    }

    public String AppStatus(String stats) {
        String returnStr = "";
        switch (stats) {
            case "0":
                returnStr = "Application";
                break;
            case "1":
                returnStr = "Initial Interview";
                break;
            case "2":
                returnStr = "Examinations";
                break;
            case "3":
                returnStr = "Reference Check";
                break;
            case "4":
                returnStr = "Final Interview";
                break;
            case "5":
                returnStr = "Job Offer";
                break;
            case "6":
                returnStr = "Contract Signing";
                break;
            default:
                break;
        }

        return returnStr;
    }

    public void populateApplicants(String jobid) {

        ObservableList<TableModel_Applicants> listApp = FXCollections.observableArrayList();

        hr1PivotApp
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_applicants", "id", "=", "app_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "=", "aerolink.tbl_hr1_applicants", "suffix_id", true)
                .where(new Object[][]{
            {"jobPosted_id", "=", jobid},
            {"status", "<", 99}
        }).get().stream().forEach((row) -> {
            HashMap action = (HashMap) row;
            listApp.add(new TableModel_Applicants(
                    action.get("app_id").toString(),
                    action.get("applicant_code").toString(),
                    (action.get("lastname").toString() + ", "
                    + action.get("firstname").toString() + " "
                    + (action.get("suffix_name").toString().equals("None") ? "" : action.get("suffix_name").toString()) + " "
                    + action.get("middlename").toString()),
                    action.get("email").toString(),
                    action.get("educAttain").toString(),
                    action.get("prevSchool").toString(),
                    AppStatus(action.get("status").toString()),
                    action.get("status").toString()
            )
            );
        });

        tblApplicantList.getItems().clear();
        tblApplicantList.setItems(listApp);
    }

    public void populateApplicantsArchive() {

        ObservableList<TableModel_Applicants> listApp = FXCollections.observableArrayList();

        hr1PivotApp
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_applicants", "id", "=", "app_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "=", "aerolink.tbl_hr1_applicants", "suffix_id", true)
                .where(new Object[][]{
            {"status", "=", 99}
        }).get().stream().forEach((row) -> {
            HashMap action = (HashMap) row;
            listApp.add(new TableModel_Applicants(
                    action.get("app_id").toString(),
                    action.get("applicant_code").toString(),
                    (action.get("lastname").toString() + ", "
                    + action.get("firstname").toString() + " "
                    + (action.get("suffix_name").toString().equals("None") ? "" : action.get("suffix_name").toString()) + " "
                    + action.get("middlename").toString()),
                    action.get("email").toString(),
                    action.get("educAttain").toString(),
                    action.get("prevSchool").toString(),
                    "Denied",
                    action.get("status").toString()
            )
            );
        });

        tblArchive.getItems().clear();
        tblArchive.setItems(listApp);
    }

    @FXML
    private void openWorkflowStages(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_ManageWorkflows.fxml").getParent());

        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.HR1.Modals.HR1_ManageWorkflowsController.modalOpen = false;
        });

    }

}
