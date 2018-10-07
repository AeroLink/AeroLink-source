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
    private TableView<HR4_Jobs_Class> tbl_jobs;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job_desc;
    @FXML
    private TableColumn<HR2_CM_Skills_Class, String> col_skills;
    @FXML
    private TableColumn<HR2_CM_Skills_Class, String> col_skill_desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        DisplayDataInTable();
        // diplaycols();
    }

    private void loadData() {
        try {
            
             //Skills
            HR2_CM_Skills cm = new HR2_CM_Skills();

            ObservableList<HR2_CM_Skills_Class> hmc = FXCollections.observableArrayList();
            List b = cm.get();

            for (Object d : b) {
                HashMap hm = (HashMap) d;
                //RS
                hm.get("skill_id");
                hm.get("skill");
                hm.get("skill_description");

                hmc.add(
                        new HR2_CM_Skills_Class(
                                String.valueOf(hm.get("skill_id")),
                                String.valueOf(hm.get("skill")),
                                String.valueOf(hm.get("skill_description")),
                                String.valueOf(hm.get("select_jobs"))
                        ));
            }
            tbl_Skills.setItems(hmc);

            
            //Job Skillsets                              
            HR2_Jobs jobs = new HR2_Jobs();

            ObservableList<HR4_Jobs_Class> hr4_jobs = FXCollections.observableArrayList();
            List c = jobs.get();

            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS

                hr4_jobs.add(
                        new HR4_Jobs_Class(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("description"))
                        ));

            }

            tbl_jobs.setItems(hr4_jobs);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void DisplayDataInTable() {

        col_job.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Title);
        col_job_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Description);
        col_skills.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CM_Skills_Class, String> param) -> param.getValue().Skill);
        col_skill_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CM_Skills_Class, String> param) -> param.getValue().Skill_Description);

    }

    private void diplaycols() {
        col_skills.setVisible(false);
        col_skill_desc.setVisible(false);
        col_job_desc.setPrefWidth(1200);
    }

    @FXML
    public void searchJob() {
        HR2_Jobs j = new HR2_Jobs();
        HR2_CM_Pivot cm_pivot = new HR2_CM_Pivot();

        try {

            List jobsRes;

            if (txt_search_job.equals("")) {
                jobsRes = j.get();

            } else {
                jobsRes = cm_pivot
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                        .where(new Object[][]{
                    {"j.title", "like", "%" + txt_search_job.getText() + "%"}
                }).groupBy("aerolink.tbl_hr2_competency_pivot.job_id")
                        .get("MAX(j.job_id) as job_id",
                                "MAX(j.title) as title");

                List skillsRes = cm_pivot
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "=", "skill_id")
                        .where(new Object[][]{
                    {"tbl_hr4_jobs.title", "like", "%" + txt_search_job.getText() + "%"}
                }).get();
                // .groupBy("tbl_hr2_competency.job_id

                SearchRelatedSkills(skillsRes);

            }

            //Load Jobs
            ObservableList<HR4_Jobs_Class> hr2jc1 = FXCollections.observableArrayList();

            for (Object d : jobsRes) {
                HashMap hm1 = (HashMap) d;
                //RS
                String id = String.valueOf(hm1.get("job_id"));
                Object desc = ((HashMap) j.where(new Object[][]{
                    {"job_id", "=", id}
                }).get().get(0)).get("description");

                hr2jc1.add(
                        new HR4_Jobs_Class(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(desc)
                        ));

            }

            tbl_jobs.getItems().clear();
            tbl_jobs.setItems(hr2jc1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void SearchRelatedSkills(List skills) {

        TableView<HR2_CM_Skills_Class> tbl_jobs = null;

        tbl_jobs.getItems().clear();

        ObservableList<HR2_CM_Skills_Class> hr2jc = FXCollections.observableArrayList();

        try {
            for (Object d : skills) {
                HashMap hm1 = (HashMap) d;
                //RS
                hm1.get("skill_id");
                hm1.get("skill");
                hm1.get("skill_description");

                hr2jc.add(
                        new HR2_CM_Skills_Class(
                                String.valueOf(hm1.get("skill_id")),
                                String.valueOf(hm1.get("skill")),
                                String.valueOf(hm1.get("skill_description")),
                                String.valueOf(hm1.get("select_jobs"))
                        ));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_jobs.setItems(hr2jc);

    }
}
