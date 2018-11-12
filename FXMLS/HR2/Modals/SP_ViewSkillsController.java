/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_JV_With_Skills_for_SP;
import FXMLS.HR2.ClassFiles.SP_JV_with_Skills_Modal;
import Model.HR2_CM_Skills;
import Model.HR4_Jobs;
import Synapse.Model;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class SP_ViewSkillsController implements Initializable {

    @FXML
    private Label lbl_position;
    @FXML
    private TableView<SP_JV_with_Skills_Modal> tbl_skills;
    @FXML
    private TableColumn<SP_JV_with_Skills_Modal, String> col_skill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_position.setText(SP_JV_with_Skills_Modal.jobs);
        viewSkill();
        col_skill.setCellValueFactory((TableColumn.CellDataFeatures<SP_JV_with_Skills_Modal, String> param) -> param.getValue().j_skills);
    }

    public void viewSkill() {
        HR2_CM_Skills s = new HR2_CM_Skills();
        List jv_data = s
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_competency_pivot", "skill_id", "cp", "=", "skill_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "cp", "job_id", true)
                .where(new Object[][]{{"aerolink.tbl_hr4_jobs.title", "=", lbl_position.getText()}})
                .get("aerolink.tbl_hr2_skillset.skill");
        ListSkills(jv_data);
    }

    public void ListSkills(List skills) {
        ObservableList<SP_JV_with_Skills_Modal> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : skills) {
                HashMap hm = (HashMap) d;
                obj.add(
                        new SP_JV_with_Skills_Modal(
                                String.valueOf(hm.get("skill"))
                        ));

            }
            tbl_skills.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
