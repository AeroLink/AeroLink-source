/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import Model.HR2_CM_Pivot;
import Model.HR2_CM_Skills;
import Model.HR2_Jobs;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Synapse.Model;
import Synapse.Session;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_set_skills;
    @FXML
    private JFXTextField txt_search_job;
    @FXML
    public TableView tbl_jobs;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job_desc;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_skills;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_skill_desc;
    @FXML
    private JFXButton btn_refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadJob();
        DisplayDataInTable();
        btn_refresh.setOnAction(e -> loadJob());
        // diplaycols();
        btn_set_skills.setOnAction(e -> {
            Modal set_skill_modal = Modal.getInstance(new Form("/FXMLS/HR2/Modals/Modal_SetSkills.fxml").getParent());
            set_skill_modal.open();
        });
    }

    long DummyCount = 0;
    long GlobalCount = 0;

    public void loadJob() {

        try {

            HR2_CM_Pivot cm_pivot = new HR2_CM_Pivot();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("competency_management")) {
                    cm_pivot.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List c = cm_pivot.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "s", "=", "skill_id")
                                .get("jobs.title", "jobs.description", "s.skill", "s.skill_description");

                        Data(c);
                        
                        GlobalCount = DummyCount;
                    }
                    
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(HR2_Competency_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                return 0;
            }, Session.SessionThreads);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Data(List cmgmt) {
        ObservableList<HR4_Jobs_Class> hr4_jobs = FXCollections.observableArrayList();
        hr4_jobs.clear();
        try {

            for (Object d : cmgmt) {
                HashMap hm1 = (HashMap) d;

                hr4_jobs.add(
                        new HR4_Jobs_Class(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("description")),
                                String.valueOf(hm1.get("skill_id")),
                                String.valueOf(hm1.get("skill")),
                                String.valueOf(hm1.get("skill_description"))
                        ));
            }

            tbl_jobs.setItems(hr4_jobs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInTable() {

        col_job.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Title);
        col_job_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Description);
        col_skills.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Skill);
        col_skill_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Skill_Description);

    }

    private void diplaycols() {
        col_skills.setVisible(false);
        col_skill_desc.setVisible(false);
        col_job_desc.setPrefWidth(1200);
    }

}
