/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import FXMLS.HR2.ClassFiles.HR2_Competency_ManagementClass;
import FXMLS.HR2.ClassFiles.HR2_JobsClass;
import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import Model.HR2_Competency;
import Model.HR2_Competency_Management;
import Model.HR2_Jobs;
import Model.HR2_Training_Management;
import Synapse.Model;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_new;
    @FXML

    private JFXButton btn_save;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXTextField txt_skill_id;
    @FXML
    private JFXTextField txt_skill;
    @FXML
    private JFXTextArea txtarea_skill_description;
    @FXML
    private JFXComboBox select_jobs;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_ID;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_Skill;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_Skill_Description;

    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_id;
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_title;
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_description;
    @FXML
    private TableView<HR2_Competency_ManagementClass> tbl_Skills;
    @FXML
    private TableView<HR2_JobsClass> tbl_Job_Skillsets;
    @FXML
    private JFXTextField txt_search_skills;

    @FXML
    private TableView<HR2_Competency_ManagementClass> tbl_Skillsets_related_to_Jobs;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_id1;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill1;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_description1;
    @FXML
    private JFXTextField txt_Search_Jobs;

    HR2_Competency competency;
    HR2_Competency_Management hr2hmc;
    @FXML
    private Pane txt_skill_description;
    @FXML
    private JFXButton btn_refresh_skills;
    @FXML
    private JFXButton btn_refresh_jobs_skills;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn_refresh_skills.setOnAction(e -> loadData());
        btn_refresh_jobs_skills.setOnAction(e -> loadData());
        btn_new.setOnMouseClicked(e -> {
            txt_skill_id.setText("");
            New();
            btn_save.setDisable(false);
            btn_edit.setDisable(true);
            btn_delete.setDisable(true);
            btn_update.setDisable(true);
        }
        );

        btn_save.setOnMouseClicked(e -> Save());
        tbl_Skills.setOnMouseClicked(
                e
                -> {
            HR2_Competency_ManagementClass cmc = tbl_Skills.getSelectionModel().getSelectedItem();
            btn_edit.setDisable(false);
            btn_delete.setDisable(false);
            btn_update.setDisable(true);
            txt_skill_id.setText(cmc.Skill_ID.getValue());
            txt_skill.setText(cmc.Skill.getValue());
            txtarea_skill_description.setText(cmc.Skill_Description.getValue());
            select_jobs.setValue(cmc.Jobs.getValue());
            btn_save.setDisable(true);
            txt_skill.setDisable(true);
            txtarea_skill_description.setDisable(true);
            select_jobs.setDisable(true);
        }
        );
        btn_delete.setOnMouseClicked(e
                -> {
            Delete();
            DisableComponents();
        });
        btn_edit.setOnMouseClicked(e
                -> {

            txt_skill.setDisable(false);
            txtarea_skill_description.setDisable(false);
            btn_save.setDisable(true);
            btn_update.setDisable(false);
            btn_delete.setDisable(true);
            select_jobs.setDisable(false);
        });
        btn_update.setOnAction(e -> Update());
        DisableComponents();
        DisplayData();
        selectJobs();
        loadData();

    }

    public void DisableComponents() {

        Node[] d = {
            btn_edit,
            btn_save,
            btn_update,
            btn_delete,
            txt_skill_id,
            txt_skill,
            txtarea_skill_description,
            select_jobs
        };

        try {
            for (Node c : d) {
                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(true);
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(true);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(true);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(true);
                }
                if (c instanceof JFXTextArea) {
                    JFXTextArea m4 = (JFXTextArea) c;
                    m4.setDisable(true);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void New() {

        Node[] d = {
            btn_save,
            txt_skill,
            txtarea_skill_description,
            select_jobs
        };

        try {
            for (Node c : d) {

                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(false);
                    m.setText("");
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(false);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(false);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                    m3.setValue("");
                }
                if (c instanceof JFXTextArea) {
                    JFXTextArea m3 = (JFXTextArea) c;
                    m3.setDisable(false);
                    m3.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void selectJobs() {
        HR2_Jobs jobs = new HR2_Jobs();

        List c = jobs.get();

        for (Object d : c) {
            HashMap hm1 = (HashMap) d;
            //RS
            select_jobs.getItems().add(hm1.get("job_id") + " - " + hm1.get("title"));
        }

    }

    @FXML
    public void Search_Skills() {

        HR2_Competency_Management hr2hmc = new HR2_Competency_Management();

        try {

            HR2_Competency_Management constQuery = hr2hmc;
            List listSkills;

            if (txt_search_skills.equals("")) {
                listSkills = constQuery.get();
            } else {
                listSkills = constQuery.where(new Object[][]{
                    {"skill", "like", "%" + txt_search_skills.getText() + "%"}
                }).get();

            }
            SearchRelatedSkillsTab(listSkills);

        } catch (Exception e) {
            System.out.println(e);
        }

        //  {"job_id", "like", " (SELECT id from tbl_jobs WHERE id = 1)"}
    }

    //Display Data
    private void loadData() {
        try {

            //Skills
            HR2_Competency_Management cm = new HR2_Competency_Management();

            ObservableList<HR2_Competency_ManagementClass> hmc = FXCollections.observableArrayList();
            List b = cm.get();

            for (Object d : b) {
                HashMap hm = (HashMap) d;
                //RS
                hm.get("skill_id");
                hm.get("skill");
                hm.get("skill_description");
                hm.get("select_jobs");
                
                hmc.add(
                        new HR2_Competency_ManagementClass(
                                String.valueOf(hm.get("skill_id")),
                                String.valueOf(hm.get("skill")),
                                String.valueOf(hm.get("skill_description")),
                                String.valueOf(hm.get("select_jobs"))
                        ));
            }
            tbl_Skills.setItems(hmc);

            //Job Skillsets                              
            HR2_Jobs jobs = new HR2_Jobs();

            ObservableList<HR2_JobsClass> hr2jc = FXCollections.observableArrayList();
            List c = jobs.get();

            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                hm1.get("job_id");
                hm1.get("title");
                hm1.get("description");

                hr2jc.add(
                        new HR2_JobsClass(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("description"))
                        ));

            }

            tbl_Job_Skillsets.setItems(hr2jc);
            tbl_Skillsets_related_to_Jobs.setItems(hmc);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //Initializing data
    private void DisplayData() {
        //Skills
        //  col_Job_ID.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Job_ID);
        col_skill_ID.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_ID);
        col_Skill.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill);
        col_Skill_Description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_Description);

        //Job SkillSets
        col_job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Job_ID);
        col_job_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Title);
        col_job_description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Description);

        col_skill_id1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_ID);
        col_skill1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill);
        col_skill_description1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_Description);

    }

    //In Job Skillsets
    //load tbl_hr4_jobs data in tbl_job_skillsets table
    @FXML
    public void SearchJobs() {

        HR2_Jobs j = new HR2_Jobs();
        HR2_Competency hrc = new HR2_Competency();

        try {

            List jobsRes;

            if (txt_Search_Jobs.equals("")) {
                jobsRes = j.get();

            } else {
                jobsRes = hrc
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                        .where(new Object[][]{
                    {"j.title", "like", "%" + txt_Search_Jobs.getText() + "%"}
                }).groupBy("aerolink.tbl_hr2_competency.job_id")
                        .get("MAX(j.job_id) as job_id",
                                "MAX(j.title) as title");

                List skillsRes = hrc
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                        .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "=", "skill_id")
                        .where(new Object[][]{
                    {"tbl_hr4_jobs.title", "like", "%" + txt_Search_Jobs.getText() + "%"}
                }).get();
                // .groupBy("tbl_hr2_competency.job_id

                SearchRelatedSkills(skillsRes);

            }

            //Load Jobs
            ObservableList<HR2_JobsClass> hr2jc1 = FXCollections.observableArrayList();

            for (Object d : jobsRes) {
                HashMap hm1 = (HashMap) d;
                //RS
                String id = String.valueOf(hm1.get("job_id"));
                Object desc = ((HashMap) j.where(new Object[][]{
                    {"job_id", "=", id}
                }).get().get(0)).get("description");

                hr2jc1.add(
                        new HR2_JobsClass(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(desc)
                        ));

            }

            tbl_Job_Skillsets.getItems().clear();
            tbl_Job_Skillsets.setItems(hr2jc1);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //In Job Skillsets
    private void SearchRelatedSkills(List skills) {

        tbl_Skillsets_related_to_Jobs.getItems().clear();

        ObservableList<HR2_Competency_ManagementClass> hr2jc = FXCollections.observableArrayList();

        try {
            for (Object d : skills) {
                HashMap hm1 = (HashMap) d;
                //RS
                hm1.get("skill_id");
                hm1.get("skill");
                hm1.get("skill_description");

                hr2jc.add(
                        new HR2_Competency_ManagementClass(
                                String.valueOf(hm1.get("skill_id")),
                                String.valueOf(hm1.get("skill")),
                                String.valueOf(hm1.get("skill_description")),
                                String.valueOf(hm1.get("select_jobs"))
                        ));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_Skillsets_related_to_Jobs.setItems(hr2jc);

    }

    private void SearchRelatedSkillsTab(List skills) {

        tbl_Skillsets_related_to_Jobs.getItems().clear();

        ObservableList<HR2_Competency_ManagementClass> hr2jc = FXCollections.observableArrayList();

        try {
            for (Object d : skills) {
                HashMap hm1 = (HashMap) d;
                //RS
                hm1.get("skill_id");
                hm1.get("skill");
                hm1.get("skill_description");

                hr2jc.add(
                        new HR2_Competency_ManagementClass(
                                String.valueOf(hm1.get("skill_id")),
                                String.valueOf(hm1.get("skill")),
                                String.valueOf(hm1.get("skill_description")),
                                String.valueOf(hm1.get("select_jobs"))
                        ));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_Skills.setItems(hr2jc);

    }

    public void Edit() {
        Node[] d = {
            btn_save,
            txt_skill,
            txtarea_skill_description,
            select_jobs,};

        try {
            for (Node c : d) {

                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(false);
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(false);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(false);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Save() {
        HR2_Competency_Management tm = new HR2_Competency_Management();
        HR2_Competency c = new HR2_Competency();
        if (txt_skill.getText().isEmpty() || txtarea_skill_description.getText().isEmpty() || select_jobs.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or More Fields are empty");
            alert.showAndWait();
        } else {
            try {

                String[][] cm_data
                        = {
                            {"skill", txt_skill.getText()},
                            {"skill_description", txtarea_skill_description.getText()},};

                int id = tm.insert(cm_data, true);
                Object[][] competency = {
                    {"skill_id", id},
                    {"job_id", select_jobs.getSelectionModel().getSelectedItem().toString().split(" - ")[0]}
                };

                c.insert(competency);

                // new HR2_Competency_ManagementController().tbl_Skills.setItems((ObservableList<HR2_Competency_ManagementClass>) hr2hmc);
                loadData();
                DisableComponents();
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void Delete() {
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> rs = delete.showAndWait();

        System.out.println(rs.get());
        if (rs.get() == ButtonType.OK) {
            System.out.println(tbl_Skills.getSelectionModel().getSelectedItem().Skill_ID.getValue());
            HR2_Competency_Management cm = new HR2_Competency_Management();

            cm.delete().where(new Object[][]{
                {"skill_id", "=", tbl_Skills.getSelectionModel().getSelectedItem().Skill_ID.getValue()}
            }).executeUpdate();
            loadData();

            Node[] d = {
                txt_skill_id,
                txt_skill,
                txtarea_skill_description,
                btn_edit,
                btn_delete,
                select_jobs
            };

            for (Node c : d) {
                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setText("");
                }

                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setValue("");
                }

                if (c instanceof JFXButton) {
                    JFXButton m4 = (JFXButton) c;
                    m4.setDisable(true);
                }

            }

        }

    }

    public void Update() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            //   System.out.println(tbl_Skills.getSelectionModel().getSelectedItem().Skill_ID.getValue());
            HR2_Competency_Management cm = new HR2_Competency_Management();

            Boolean a = cm.update(new Object[][]{
                {"skill", txt_skill.getText()},
                {"skill_description", txtarea_skill_description.getText()}

            }).where(new Object[][]{
                {"skill_id", "=", txt_skill_id.getText()}
            }).executeUpdate();

            System.out.println(a);

            loadData();
        }
    }
}
