/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.HR1_EditJobSelection;
import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import FXMLS.HR1.ClassFiles.TableModel_jLimit;
import FXMLS.HR1.ClassFiles.TableModel_jPosted;
import Model.HR1.JobPosting;
import Model.HR1.JobVacancy;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_RecruitmentController implements Initializable {

    JobVacancy jobVacancy = new JobVacancy();

    ObservableList<TableModel_jLimit> observableList = FXCollections.observableArrayList();

    ObservableList<TableModel_jPosted> observablePosting = FXCollections.observableArrayList();

    long GlobalCount = 0;
    long DummyCount = 0;
    Thread th;

    JobPosting jp = new JobPosting();

    @FXML
    private TableView<TableModel_jLimit> tblOpenJobs;
    @FXML
    private ContextMenu contextMenuJobs;
    @FXML
    private MenuItem menuPost;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXTextField txtSearchPostings;
    @FXML
    private JFXButton btnSearchPostings;
    @FXML
    private TableView<TableModel_jPosted> tblPostingJobs;
    @FXML
    private ContextMenu contextPostingsJobs;
    @FXML
    private MenuItem menuPosting;
    @FXML
    private StackPane spane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.generateTable();
        this.populateTable();

        tblOpenJobs.setContextMenu(this.contextMenuJobs);
        tblOpenJobs.setOnMouseClicked(e -> tableMouseEvent(e));

        menuPost.setOnAction(event -> {
            this.viewModalPostJob();
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            Search();
        });

        btnSearch.setOnMouseClicked(event -> Search());

        //Posting
        this.generatePostingTable();
        this.populatePostingTable();

        tblPostingJobs.setContextMenu(this.contextPostingsJobs);
        menuPosting.setOnAction(event -> viewModalEditJob());
    }

    public void Search() {
        if (txtSearch.textProperty().getValue().isEmpty()) {
            populateTable();
        } else {
            tblOpenJobs.getItems().clear();

            jobVacancy
                    .join(Model.JOIN.INNER,
                            "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                    .where(new Object[][]{
                {"jobOpen", "<>", "0"}, {"title", "like", "%" + txtSearch.getText() + "%"}
            })
                    .get()
                    .stream()
                    .forEach(row -> {
                        HashMap current_row = (HashMap) row;
                        String id = current_row.get("id").toString();
                        String job_id = current_row.get("job_id").toString();
                        String job_open = current_row.get("jobOpen").toString();
                        String job_title = current_row.get("title").toString();
                        String status = current_row.get("isPosted").toString().equals("0") ? "Pending" : "Posted";
                        String OpenDate = current_row.get("created_at").toString();
                        observableList.add(new TableModel_jLimit(id, job_id, job_open, job_title, status, OpenDate));
                    });

            tblOpenJobs.setItems(observableList);
        }

    }

    public void generateTable() {

        tblOpenJobs.getItems().clear();
        tblOpenJobs.getColumns().removeAll(tblOpenJobs.getColumns());

        TableColumn<TableModel_jLimit, String> job_title = new TableColumn<>("Job Title");
        TableColumn<TableModel_jLimit, String> job_open = new TableColumn<>("Number of Available Positions");
        TableColumn<TableModel_jLimit, String> status = new TableColumn<>("Posting Status");
        TableColumn<TableModel_jLimit, String> openDate = new TableColumn<>("Open Date");

        job_title.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_title);
        job_open.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_open);
        status.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().status);
        openDate.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().opened_date);

        tblOpenJobs.getColumns().addAll(status, job_title, job_open, openDate);

    }

    public void generatePostingTable() {

        tblPostingJobs.getItems().clear();
        tblPostingJobs.getColumns().removeAll(tblPostingJobs.getColumns());

        TableColumn<TableModel_jPosted, String> job_title = new TableColumn<>("Job Title");
        TableColumn<TableModel_jPosted, String> status = new TableColumn<>("Employment Status");
        TableColumn<TableModel_jPosted, String> salary = new TableColumn<>("Assumed Salary");
        TableColumn<TableModel_jPosted, String> postingDate = new TableColumn<>("Posting Date");
        TableColumn<TableModel_jPosted, String> publish_date = new TableColumn<>("Publish Date");
        TableColumn<TableModel_jPosted, String> expire_date = new TableColumn<>("Expire Date");

        job_title.setCellValueFactory((param) -> param.getValue().job_title);
        status.setCellValueFactory(param -> param.getValue().status);
        salary.setCellValueFactory(param -> param.getValue().salary);
        postingDate.setCellValueFactory(param -> param.getValue().postingDate);
        publish_date.setCellValueFactory(param -> param.getValue().publish_on);
        expire_date.setCellValueFactory(param -> param.getValue().until);

        tblPostingJobs.getColumns().addAll(job_title, status, salary, publish_date, expire_date, postingDate);
    }

    public void populateTable() {

        CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("hr1RCC")) {
                try {
                    jobVacancy.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });
                    if (GlobalCount != DummyCount) {

                        tblOpenJobs.getItems().clear();

                        jobVacancy
                                .join(Model.JOIN.INNER,
                                        "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                                .where(new Object[][]{
                            {"jobOpen", "<>", "0"}
                        })
                                .get()
                                .stream()
                                .forEach(row -> {
                                    HashMap current_row = (HashMap) row;
                                    String id = current_row.get("id").toString();
                                    String job_id = current_row.get("job_id").toString();
                                    String job_open = current_row.get("jobOpen").toString();
                                    String job_title = current_row.get("title").toString();
                                    String status = current_row.get("isPosted").toString().equals("0") ? "Pending" : "Posted";
                                    String OpenDate = current_row.get("created_at").toString();
                                    observableList.add(new TableModel_jLimit(id, job_id, job_open, job_title, status, OpenDate));
                                });

                        tblOpenJobs.setItems(observableList);
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

    long jpCount = 0;
    long totalCount = 0;

    public void populatePostingTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr1RCC")) {
                try {
                    jp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        totalCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (totalCount != jpCount) {
                        tblPostingJobs.getItems().clear();

                        jp.get()
                                .stream()
                                .forEach(row -> {
                                    HashMap hash = (HashMap) row;
                                    String id = hash.get("id").toString();
                                    String jPosted = hash.get("jobPosted_id").toString();
                                    String salary = hash.get("salary").toString();
                                    String status = hash.get("status").toString();
                                    String publish_on = hash.get("publish_on").toString();
                                    String until = hash.get("until").toString();
                                    String c = hash.get("created_at").toString();
                                    String title = hash.get("title").toString();
                                    observablePosting.add(new TableModel_jPosted(
                                            id,
                                            jPosted,
                                            salary,
                                            status,
                                            publish_on,
                                            until,
                                            c,
                                            title
                                    ));

                                });

                        tblPostingJobs.setItems(observablePosting);
                        jpCount = totalCount;
                    }

                    Thread.sleep(3000);
                } catch (Exception ex) {
                    System.err.println("Exception -> " + ex.getMessage());
                }
            }
            return 0;

        }, Session.SessionThreads);
    }

    public void tableMouseEvent(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            this.contextMenuJobs.show(tblOpenJobs, e.getX(), e.getY());
        }
    }

    public void PostingMouseEvent(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            this.contextPostingsJobs.show(tblPostingJobs, e.getX(), e.getY());
        }
    }

    @FXML
    private void btnPostAJob(ActionEvent event) {
        this.viewModalPostJob();
    }

    public void viewModalPostJob() {
        if (!tblOpenJobs.getSelectionModel().getSelectedItem().status.getValue().equals("Posted")) {
            HR1_PostJobSelection.id = tblOpenJobs.getSelectionModel().getSelectedItem().id.getValue();
            HR1_PostJobSelection.jobID = tblOpenJobs.getSelectionModel().getSelectedItem().job_id.getValue();
            HR1_PostJobSelection.jobTitle = tblOpenJobs.getSelectionModel().getSelectedItem().job_title.getValue();
            HR1_PostJobSelection.OpenPos = tblOpenJobs.getSelectionModel().getSelectedItem().job_open.getValue();
            
            try {
                AnchorPane spx = FXMLLoader.load(getClass().getResource("/FXMLS/HR1/Modals/HR1_PostJob.fxml"));
                SysDialog dialog = new SysDialog();
                dialog.showDialog(spane, JFXDialog.DialogTransition.BOTTOM, (Node) spx, new JFXButton(""));
                //Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_PostJob.fxml").getParent());
                //md.open();
            } catch (IOException ex) {
                Logger.getLogger(HR1_RecruitmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            Helpers.EIS_Response.ErrorResponse("Halt!", "Job was already posted");
        }
    }

    public void viewModalEditJob() {
        TableModel_jPosted tb = tblPostingJobs.getSelectionModel().getSelectedItem();
        HR1_EditJobSelection.id = tb.id.getValue();
        HR1_EditJobSelection.jPosted = tb.jPosted_id.getValue();
        HR1_EditJobSelection.salary = tb.salary.getValue();
        HR1_EditJobSelection.status = tb.status.getValue();
        HR1_EditJobSelection.publish_on = tb.publish_on.getValue();
        HR1_EditJobSelection.until = tb.until.getValue();
        HR1_EditJobSelection.title = tb.job_title.getValue();
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_EditPost.fxml").getParent());
        md.open();
    }

    @FXML
    private void btnEditPost(ActionEvent event) {
        this.viewModalEditJob();
    }

}
