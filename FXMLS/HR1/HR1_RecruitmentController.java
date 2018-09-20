/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.HR1_PostJobSelection;
import FXMLS.HR1.ClassFiles.TableModel_jLimit;
import Model.HR1.JobVacancy;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_RecruitmentController implements Initializable {

    JobVacancy jobVacancy = new JobVacancy();

    ObservableList<TableModel_jLimit> observableList = FXCollections.observableArrayList();
    long GlobalCount = 0;
    Thread th;

    @FXML
    private StackPane stackpane;
    @FXML
    private TableView<TableModel_jLimit> tblOpenJobs;
    @FXML
    private ContextMenu contextMenuJobs;
    @FXML
    private MenuItem menuPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateTable();
        this.populateTable();
        tblOpenJobs.setContextMenu(this.contextMenuJobs);
        tblOpenJobs.setOnMouseClicked(e -> tableMouseEvent(e));
        menuPost.setOnAction(event -> { this.viewModalPostJob(); });
    }

    public void generateTable() {

        tblOpenJobs.getItems().clear();
        tblOpenJobs.getColumns().removeAll(tblOpenJobs.getColumns());

        TableColumn<TableModel_jLimit, String> id = new TableColumn<>("ID");
        TableColumn<TableModel_jLimit, String> job_id = new TableColumn<>("Job ID");
        TableColumn<TableModel_jLimit, String> job_title = new TableColumn<>("Job Title");
        TableColumn<TableModel_jLimit, String> job_open = new TableColumn<>("Number of Available Positions");

        id.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().id);
        job_id.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_id);
        job_title.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_title);
        job_open.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_jLimit, String> param) -> param.getValue().job_open);

        tblOpenJobs.getColumns().addAll(id, job_id, job_title, job_open);
    }

    public void populateTable() {

        CompletableFuture.supplyAsync( () -> {
                        while (Session.CurrentRoute.equals("hr1RCC")) {
                            try {
                                long count = jobVacancy.get().stream().count();
                                if (GlobalCount != count) {
                                    
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
                                                observableList.add(new TableModel_jLimit(id, job_id, job_open, job_title));
                                            });
                                    
                                    tblOpenJobs.setItems(observableList);
                                    GlobalCount = count;
                                }
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                               //Block
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

    @FXML
    private void btnPostAJob(ActionEvent event) {
        this.viewModalPostJob();
    }
    
    public void viewModalPostJob() {
        HR1_PostJobSelection.id = tblOpenJobs.getSelectionModel().getSelectedItem().id.getValue();
        HR1_PostJobSelection.jobID = tblOpenJobs.getSelectionModel().getSelectedItem().job_id.getValue();
        HR1_PostJobSelection.jobTitle = tblOpenJobs.getSelectionModel().getSelectedItem().job_title.getValue();
        HR1_PostJobSelection.OpenPos = tblOpenJobs.getSelectionModel().getSelectedItem().job_open.getValue();
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_PostJob.fxml").getParent());
        md.open();
    }
}
