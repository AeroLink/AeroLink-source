/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Requisition;

import Model.HR2_CM_Skill_Requisition;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class CM_RequestSkillController implements Initializable {

    @FXML
    private JFXComboBox cbox_job_position;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXButton btn_submit;
    @FXML
    private JFXComboBox cbox_dept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectJobs();
    }

    public void selectJobs() {
        HR4_Jobs jobs = new HR4_Jobs();
        HR4_Departments dept = new HR4_Departments();
        try {
            List c = jobs.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object job : c) {
                HashMap hm1 = (HashMap) job;
                //RS
                String j_id = String.valueOf(hm1.get("job_id"));
                String sjobs = (String) hm1.get("title");

                cbox_job_position.getItems().add("J" + j_id + " - " + sjobs);

            }

            List d = dept.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object depts : d) {
                HashMap hm2 = (HashMap) depts;
                //RS
                String j_id = String.valueOf(hm2.get("id"));
                String sjobs = (String) hm2.get("dept_name");

                cbox_dept.getItems().add("DEPT00" + j_id + " - " + sjobs);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    public void SubmitRequest() {

        if (!cbox_job_position.getValue().toString().isEmpty() || !txt_reason.getText().isEmpty()) {
            HR2_CM_Skill_Requisition rs = new HR2_CM_Skill_Requisition();

            try {
                String[][] skill_req = {
                    {"dept_id", cbox_dept.getSelectionModel().getSelectedItem().toString().substring(6).split(" - ")[0]},
                    {"job_id", cbox_job_position.getSelectionModel().getSelectedItem().toString().substring(1).split(" - ")[0]},
                    {"reason", txt_reason.getText()},
                    {"requested_by", Session.pull("employee_code").toString()}
                };
                rs.insert(skill_req);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
            } catch (Exception e) {
                System.err.println(e);
            }

        } else {
            Alert saved = new Alert(Alert.AlertType.ERROR);
            saved.setContentText("One or More fields are empty");
            saved.showAndWait();
        }
    }
}
