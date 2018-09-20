/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.ClassFiles.HR4_MIZ;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_Core_Human_Capital_ManagementController implements Initializable {

    @FXML
    private TableView<TableModel_Jobs> tbl_jobs;
    @FXML
    private JFXButton btnNewJob;

    @FXML
    private ContextMenu contextMenuJobs;

    ObservableList<TableModel_Jobs> obj = FXCollections.observableArrayList();
    int Global_Count = 0;

    ExecutorService e = Executors.newFixedThreadPool(1);

    HR4_Jobs jobs = new HR4_Jobs();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO -> TRYING TO ENTER THE QUANTUM REALM (https://youtu.be/nN6VR92V70M)

        obj.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tbl_jobs.setItems(obj);
        });

        this.generateTable();
        this.populateTable();
        tbl_jobs.setContextMenu(contextMenuJobs);

    }

    public void generateTable() {

        tbl_jobs.getItems().clear();
        tbl_jobs.getColumns().removeAll(tbl_jobs.getColumns());

        TableColumn<TableModel_Jobs, String> id = new TableColumn<>("ID");
        TableColumn<TableModel_Jobs, String> title = new TableColumn<>("Title");
        TableColumn<TableModel_Jobs, String> description = new TableColumn<>("Description");
        TableColumn<TableModel_Jobs, String> department = new TableColumn<>("Department");
        TableColumn<TableModel_Jobs, String> classification = new TableColumn<>("Classification");
        TableColumn<TableModel_Jobs, String> designation = new TableColumn<>("Designation");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        id.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().id);
        title.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().title);
        description.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().description);
        department.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().department);
        classification.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().classification);
        designation.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_Jobs, String> param) -> param.getValue().designation);

        //</editor-fold>
        tbl_jobs.getColumns().addAll(id, title, description, department, classification, designation);
    }

    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = jobs.get(("COUNT(*) as total"));

                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = jobs
                                    .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_department", "id", "tblD", "=", "dept_id")
                                    .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_job_classifications", "id", "tblC" ,"=", "classification_id")
                                    .join(Model.JOIN.LEFT, "aerolink.tbl_hr4_job_designations", "id", "tblDD" ,"=", "designation_id")
                                    .get("job_id",
                                            "title",
                                            "description",
                                            "CONCAT(tblD.id, ' - ', tblD.dept_name) as department",
                                            "CONCAT(tblC.id, ' - ', tblC.class_name) as classification",
                                            "CONCAT(tblDD.id, ' - ', tblDD.designation) as designation");
                            Global_Count = count;
                        }

                        return rs;
                    }).thenAccept((rs) -> {
                        if(!rs.isEmpty()) {
                            AddJobToTable(rs);
                        }
                    });
                    
                    Thread.sleep(3000);
                }
            }
        }
        );
        
        th.setDaemon(true);
        th.start();
    }

    public void AddJobToTable(List rs) {

        obj.clear();
        
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String id = String.valueOf(crow.get("job_id"));
            String department = (String) crow.get("department");
            String title1 = (String) crow.get("title");
            String description = (String) crow.get("description");
            String classification = (String) crow.get("classification");
            String designation = (String) crow.get("designation");
            obj.add(new TableModel_Jobs(id, department, title1, description, classification, designation));
        }
    }

    @FXML
    private void OpenModalNewJob(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_NewJob.fxml").getParent());
        md.open();
    }

    int click_count = 0;
    int current_row = 0;

    public void viewJob() {
        HR4_MIZ.init_viewJob(tbl_jobs.getSelectionModel().getSelectedItem().id.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().title.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().description.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().department.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().designation.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().classification.get());

        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_ViewJob.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewRow(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() >= 2) {
                viewJob();
            }
        }

        if (event.getButton() == MouseButton.SECONDARY) {
            contextMenuJobs.show(tbl_jobs, event.getX(), event.getSceneY());
        }

    }

}
