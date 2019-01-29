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
import Synapse.Model;
import Synapse.Session;
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.SegmentedBar;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.textfield.CustomTextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_RecruitmentController implements Initializable {

    JobVacancy jobVacancy = new JobVacancy();

    public static BooleanProperty refresher;

    ObservableList<TableModel_jLimit> observableList = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieList = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieList_FullPart = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieList_Jobs = FXCollections.observableArrayList();
    ObservableList<TableModel_jPosted> observablePosting = FXCollections.observableArrayList();

    long GlobalCount = 0;
    long DummyCount = 0;
    Thread th;

    Label statusLabel = new Label("");

    JobPosting jp = new JobPosting();

    Object[][] objFilter;

    @FXML
    private TableView<TableModel_jLimit> tblOpenJobs;
    @FXML
    private ContextMenu contextMenuJobs;
    @FXML
    private MenuItem menuPost;
    @FXML
    private CustomTextField txtSearch;
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
    @FXML
    private MaskerPane maskpane;
    @FXML
    private StatusBar statusBar;
    @FXML
    private JFXCheckBox rdoPosted;
    @FXML
    private JFXCheckBox rdoPending;
    @FXML
    private JFXCheckBox rdoFullTime;
    @FXML
    private JFXCheckBox rdoPartTime;
    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart pieFullPart;
    @FXML
    private PieChart pieJobs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.generateTable();
        this.populateTable();

        //Loading mask
        maskpane.setText("Loading Data, Please Wait ... ");

//        pieList.add(new PieChart.Data("samssse", 2));
//        pieList.add(new PieChart.Data("zzxz", 3));
//        
        this.chartStats();
        //statusbar
        //refresher
        refresher = new SimpleBooleanProperty();

        refresher.setValue(false);
        refresher.addListener(listener -> {
            if (refresher.getValue()) {
                generateTable();
                GlobalCount = 0;
                refresher.setValue(false);
            }
        });
        tblOpenJobs.setContextMenu(this.contextMenuJobs);
        tblOpenJobs.setOnMouseClicked(e -> tableMouseEvent(e));

        menuPost.setOnAction(event -> {
            this.viewModalPostJob(tblOpenJobs.getSelectionModel().getSelectedItem());
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

    public void chartStats() {

        pieList.clear();
        pieList_FullPart.clear();
        pieList_Jobs.clear();
        
        List<HashMap> list = jobVacancy.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .groupBy("isPosted")
                .get("IIF(isPosted = 0, 'Pending', 'Posted' ) as PostedStatus, COUNT(isPosted) as total");
        list.forEach((row) -> {
            pieList.add(new PieChart.Data(row.get("PostedStatus").toString(), Double.parseDouble(row.get("total").toString())));
        });

        List<HashMap> list2x = jobVacancy.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .groupBy("status_type")
                .get("IIF(status_type = 0, 'Full Time', 'Part Time' ) as PostedStatus, COUNT(status_type) as total");
        list2x.forEach((row) -> {
            pieList_FullPart.add(new PieChart.Data(row.get("PostedStatus").toString(), Double.parseDouble(row.get("total").toString())));
        });

        List<HashMap> list3x = jobVacancy.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .groupBy("title")
                .get("title, COUNT(title) as total");
        list3x.forEach((row) -> {
            pieList_Jobs.add(new PieChart.Data(row.get("title").toString(), Double.parseDouble(row.get("total").toString())));
        });

        pieChart.setData(pieList);
        pieFullPart.setData(pieList_FullPart);
        pieJobs.setData(pieList_Jobs);

    }

    public void Search() {
        if (txtSearch.textProperty().getValue().isEmpty()) {
            populateTable();
        } else {
            tblOpenJobs.getItems().removeAll(tblOpenJobs.getItems());
            generateTable();

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
                        String salaryS = current_row.get("salary").toString();
                        String status = current_row.get("isPosted").toString().equals("0") ? "Pending" : "Posted";
                        String OpenDate = current_row.get("created_at").toString();
                        String eS = current_row.get("status_type").toString().equals("0") ? "Full Time" : "Part Time";
                        observableList.add(new TableModel_jLimit(id, job_id, job_open, job_title, status, OpenDate, salaryS, eS));
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
        TableColumn<TableModel_jLimit, String> salary = new TableColumn<>("Salary");
        TableColumn<TableModel_jLimit, String> empStatus = new TableColumn<>("empStatus");

        TableColumn btnAction = new TableColumn<>(" ");

        btnAction.setSortable(false);

        btnAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableModel_jLimit, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TableModel_jLimit, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });

        btnAction.setCellFactory(new Callback<TableColumn<TableModel_jLimit, Boolean>, TableCell<TableModel_jLimit, Boolean>>() {
            @Override
            public TableCell<TableModel_jLimit, Boolean> call(TableColumn<TableModel_jLimit, Boolean> param) {

                //return new Synapse.Components.MenuButtonCell<TableModel_jLimit>().create("Actions", PostJob);
                return new TableCell<TableModel_jLimit, Boolean>() {

                    FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
                    private final MenuButton btn = new MenuButton("Actions", f);

                    {
                        f.getStyleClass().add("fontIconTable");
                        btn.getStyleClass().add("btnTable");

                        MenuItem PostJob = new MenuItem("Post this Job");
                        FontAwesomeIconView fx = new FontAwesomeIconView(FontAwesomeIcon.PAPER_PLANE_ALT);
                        fx.getStyleClass().add("fontIconMenu");
                        PostJob.setGraphic(fx);
                        PostJob.setOnAction(event -> {
                            TableModel_jLimit jLimit = (TableModel_jLimit) getTableRow().getItem();
                            viewModalPostJob(jLimit);
                        });

                        btn.getItems().add(PostJob);

                    }

                    @Override
                    protected void updateItem(Boolean t, boolean empty) {
                        super.updateItem(t, empty);
                        if (!empty) {
                            setGraphic(btn);
                        }
                    }

                };
            }
        });

        job_title.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_title);
        job_open.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_open);
        status.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().status);
        openDate.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().opened_date);
        salary.setCellValueFactory(param -> param.getValue().salary);
        empStatus.setCellValueFactory(param -> param.getValue().empStatus);

        tblOpenJobs.getColumns().addAll(status, job_title, job_open, openDate, empStatus, salary, btnAction);

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

                    if (DummyCount == 0) {
                        TimeUnit.SECONDS.sleep(1);
                        maskpane.setVisible(false);
                    }

                    jobVacancy.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });
                    if (GlobalCount != DummyCount) {
                        Platform.runLater(() -> {
                            statusBar.setText("Loading ...");
                            statusBar.setProgress(0);
                        });

                        tblOpenJobs.getItems().removeAll(tblOpenJobs.getItems());

                        List<HashMap> list = jobVacancy.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id").get();

                        int totalResultSet = list.size();
                        int incrementItem = 1;
                        for (HashMap current_row : list) {
                            String id = current_row.get("id").toString();
                            String job_id = current_row.get("job_id").toString();
                            String job_open = current_row.get("jobOpen").toString();
                            String job_title = current_row.get("title").toString();
                            String status = current_row.get("isPosted").toString().equals("0") ? "Pending" : "Posted";
                            String OpenDate = current_row.get("created_at").toString();
                            String eS = current_row.get("status_type").toString().equals("0") ? "Full Time" : "Part Time";
                            String salary = current_row.get("salary").toString();
                            observableList.add(new TableModel_jLimit(id, job_id, job_open, job_title, status, OpenDate, salary, eS));
                            statusBar.setProgress(Double.parseDouble(String.valueOf((incrementItem / totalResultSet))));
                            incrementItem += 1;
                        }

                        if (incrementItem >= totalResultSet) {
                            tblOpenJobs.setItems(observableList);
                            GlobalCount = DummyCount;
                            Platform.runLater(() -> {
                                statusBar.setText("Status : Data Load Complete, " + String.valueOf(totalResultSet) + " results found. ");
                                statusBar.setProgress(0);
                                chartStats();
                            });

                        }

                    }
                } catch (Exception ex) {
                    System.err.println("Exception Vacancies -> " + ex.getMessage());
                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR1_RecruitmentController.class.getName()).log(Level.SEVERE, null, ex);
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
                    jp.get("ISNULL(CHECKSUM_AGG(BINARY_CHECKSUM(*)), 0) as chk").stream().forEach(row -> {
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
                    System.err.println("Exception Postings -> " + ex.getMessage());
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

    private void btnPostAJob(ActionEvent event) {
        this.viewModalPostJob(tblOpenJobs.getSelectionModel().getSelectedItem());
    }

    public void viewModalPostJob(TableModel_jLimit j) {
        if (!j.status.getValue().equals("Posted")) {
            HR1_PostJobSelection.id = j.id.getValue();
            HR1_PostJobSelection.jobID = j.job_id.getValue();
            HR1_PostJobSelection.jobTitle = j.job_title.getValue();
            HR1_PostJobSelection.OpenPos = j.job_open.getValue();
            HR1_PostJobSelection.salary = j.salary.getValue();
            HR1_PostJobSelection.status = j.empStatus.getValue();

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
        try {
            TableModel_jPosted tb = tblPostingJobs.getSelectionModel().getSelectedItem();
            HR1_EditJobSelection.id = tb.id.getValue();
            HR1_EditJobSelection.jPosted = tb.jPosted_id.getValue();
            HR1_EditJobSelection.salary = tb.salary.getValue();
            HR1_EditJobSelection.status = tb.status.getValue();
            HR1_EditJobSelection.publish_on = tb.publish_on.getValue();
            HR1_EditJobSelection.until = tb.until.getValue();
            HR1_EditJobSelection.title = tb.job_title.getValue();

            AnchorPane spx = FXMLLoader.load(getClass().getResource("/FXMLS/HR1/Modals/HR1_EditPost.fxml"));
            SysDialog dialog = new SysDialog();
            dialog.showDialog(spane, JFXDialog.DialogTransition.BOTTOM, (Node) spx, new JFXButton(""));

//        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_EditPost.fxml").getParent());
//        md.open();
        } catch (IOException ex) {
            Logger.getLogger(HR1_RecruitmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditPost(ActionEvent event) {
        this.viewModalEditJob();
    }

}
