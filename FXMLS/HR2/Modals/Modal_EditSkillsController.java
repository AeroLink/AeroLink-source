/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class_for_Modal;
import FXMLS.HR4.ClassFiles.HR4_MIZ;
import Model.HR2_CM_Pivot;
import Model.HR2_CM_Skills;
import Model.HR4_Jobs;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class Modal_EditSkillsController implements Initializable {

    @FXML
    private JFXComboBox cbox_edit_select_job;
    @FXML
    private JFXTextField txt_edit_skill;
    @FXML
    private JFXTextArea txt_edit_skill_desc;
    @FXML
    private JFXButton btn_update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbox_edit_select_job.getItems().add(HR2_CM_Skills_Class_for_Modal.j_title);
        cbox_edit_select_job.getSelectionModel().selectFirst();
        txt_edit_skill.setText(HR2_CM_Skills_Class_for_Modal.j_Skill);
        txt_edit_skill_desc.setText(HR2_CM_Skills_Class_for_Modal.j_Skill_d);
        
        selectJobs();
        
    }

    public void selectJobs() {
        HR4_Jobs jobs = new HR4_Jobs();

        try {
            List c = jobs.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                String j_id = String.valueOf(hm1.get("job_id"));
                String sjobs = (String) hm1.get("title");

                cbox_edit_select_job.getItems().add("J" + j_id + " - " + sjobs);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Update() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_CM_Pivot skills_p = new HR2_CM_Pivot();
            HR2_CM_Skills skills = new HR2_CM_Skills();
            HR4_Jobs j = new HR4_Jobs();
            
            Boolean up = skills.update(new Object[][]{
              /*  {"job_id", cbox_edit_select_job.getSelectionModel().getSelectedItem().toString().substring(1)
                        .toString().split(" - ")[0]},*/
                {"skill", txt_edit_skill.getText()},
                {"skill_description", txt_edit_skill_desc.getText()}
            }).where(new Object[][]{
                {"job_id", "=", cbox_edit_select_job.idProperty().get()}
            }).executeUpdate();

        }
    }
}
