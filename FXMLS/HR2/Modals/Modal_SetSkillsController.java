/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class Modal_SetSkillsController implements Initializable {

    @FXML
    private JFXComboBox cbox_select_job;
    @FXML
    private JFXTextField txt_skill;
    @FXML
    private JFXTextArea txt_skill_desc;
    @FXML
    private JFXButton btn_save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_save.setOnAction(e -> {
            Save();
            cbox_select_job.setValue(null);
            txt_skill.setText("");
            txt_skill_desc.setText("");
        });
        selectJobs();
        btn_save.setDisable(true);

        txt_skill_desc.setOnKeyTyped(e -> validate());
    }

    public void validate() {
        if (!cbox_select_job.getValue().toString().isEmpty() && !txt_skill.getText().isEmpty() && !txt_skill_desc.getText().isEmpty()) {

            btn_save.setDisable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill out the required fields");
            alert.showAndWait();
            btn_save.setDisable(true);
        }
    }

    public void selectJobs() {
        HR4_Jobs jobs = new HR4_Jobs();

        List c = jobs.get();

        for (Object d : c) {
            HashMap hm1 = (HashMap) d;
            //RS
            cbox_select_job.getItems().add(hm1.get("job_id") + " - " + hm1.get("title"));
        }

    }

    public void Save() {
        HR2_CM_Skills skillset = new HR2_CM_Skills();
        HR2_CM_Pivot cm_pivot = new HR2_CM_Pivot();

        if (txt_skill.getText().isEmpty() || txt_skill_desc.getText().isEmpty() || cbox_select_job.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or More Fields are empty");
            alert.showAndWait();

        } else {
            try {

                String[][] cm_data
                        = {
                            {"skill", txt_skill.getText()},
                            {"skill_description", txt_skill_desc.getText()},};

                int id = skillset.insert(cm_data, true);
                Object[][] competency = {
                    {"skill_id", id},
                    {"job_id", cbox_select_job.getSelectionModel().getSelectedItem().toString().split(" - ")[0]}
                };

                cm_pivot.insert(competency);

                // new HR2_Competency_ManagementController().tbl_Skills.setItems((ObservableList<HR2_Competency_ManagementClass>) hr2hmc);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                btn_save.setDisable(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

}
