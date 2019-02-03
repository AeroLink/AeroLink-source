/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class;
import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class_for_Modal;
import FXMLS.HR4.Filler.HR4_MIZ;
import Model.HR2_CM_Pivot;
import Model.HR2_CM_Skills;
import Model.HR4_Jobs;
import Synapse.Model;
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
    @FXML
    private JFXTextArea txt_job_desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbox_edit_select_job.getItems().add(HR2_CM_Skills_Class_for_Modal.j_title);
        cbox_edit_select_job.getSelectionModel().selectFirst();
        txt_job_desc.setText(HR2_CM_Skills_Class_for_Modal.j_Desc);
        txt_edit_skill.setText(HR2_CM_Skills_Class_for_Modal.j_Skill);
        txt_edit_skill_desc.setText(HR2_CM_Skills_Class_for_Modal.j_Skill_d);  
        
    }

    @FXML
    public void Update() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_CM_Skills skills = new HR2_CM_Skills();
        
            Boolean up = skills.where(new Object[][]{
                {"skill_id", "=", HR2_CM_Skills_Class_for_Modal.j_Skill_id}
            }).update(new Object[][]{
                {"skill", txt_edit_skill.getText()},
                {"skill_description", txt_edit_skill_desc.getText()}
            }).executeUpdate();
               Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText("Skill Successfully Updated");
            dropnotif.showAndWait();

        }
    }
}
