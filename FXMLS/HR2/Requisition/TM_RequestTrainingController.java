/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Requisition;

import Model.HR2_LM_Exam_Request;
import Model.HR2_TM_Training_Requisition;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.STORED_PROC;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
public class TM_RequestTrainingController implements Initializable {

    @FXML
    private JFXComboBox cbox_department;
    @FXML
    private JFXComboBox cbox_job_position;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextField txt_no_of_participants;
    @FXML
    private JFXTextField txt_total_hours;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXDatePicker txt_from_day;
    @FXML
    private JFXDatePicker txt_to_day;
    @FXML
    private JFXButton btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayDataInCB();
    }

    public void DisplayDataInCB() {
        HR4_Departments dept = new HR4_Departments();
        HR4_Jobs j = new HR4_Jobs();
        try {
            List c = dept.get();
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                cbox_department.getItems().add("DEPT00" + hm1.get("id") + hm1.get("dept_name"));

            }

            List tm_dept = j.get();
            for (Object td : tm_dept) {
                HashMap hm3 = (HashMap) td;
                //RS
                cbox_job_position.getItems().add("J" + hm3.get("job_id") + hm3.get("title"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    String rq_id = ""; //needed for accepting the request_id

    @FXML
    public void SubmitRequest() {

        if (!cbox_department.getValue().toString().isEmpty() || !cbox_job_position.getValue().toString().isEmpty() || 
            !txt_training_title.getText().isEmpty() || !txt_no_of_participants.getText().isEmpty() || !txt_total_hours.getText().isEmpty() 
                || !txt_from_day.getValue().toString().isEmpty() || !txt_to_day.getValue().toString().isEmpty() || !txt_reason.getText().isEmpty()) {
            HR2_TM_Training_Requisition rs = new HR2_TM_Training_Requisition();

            List<HashMap> list = STORED_PROC.executeCall("EIS_CreateRequest", new Object[][]{
                {"request", "Training Request"},
                {"request_description", txt_reason.getText()},
                {"requestor_id", Session.pull("employee_code")}
            });

            list.stream().forEach((HashMap e) -> {
                rq_id = e.get("id").toString();
            });

            try {
                String[][] skill_req = {
                    {"dept_id", cbox_department.getSelectionModel().getSelectedItem().toString().substring(6).split(" - ")[0]},
                    {"job_id", cbox_job_position.getSelectionModel().getSelectedItem().toString().substring(1).split(" - ")[0]},
                    {"training_title", txt_training_title.getText()},
                    {"no_of_participants", txt_no_of_participants.getText()},
                    {"total_hours", txt_total_hours.getText()},
                    {"from_day", txt_from_day.getValue().toString()},
                    {"to_day", txt_to_day.getValue().toString()},
                    {"reason", txt_reason.getText()},
                    {"employee_code", Session.pull("employee_code").toString()},
                    {"request_id", rq_id}
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
