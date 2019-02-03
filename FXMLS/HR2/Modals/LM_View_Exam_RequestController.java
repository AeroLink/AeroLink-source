/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.LM_ViewExamRequest;
import Model.HR2_RequestStatus;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class LM_View_Exam_RequestController implements Initializable {

    @FXML
    private Label lbl_department;
    @FXML
    private Label lbl_job;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXComboBox cbox_position;
    @FXML
    private JFXButton btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_department.setText(LM_ViewExamRequest.dept_id);
        lbl_job.setText(LM_ViewExamRequest.job_id);
        txt_reason.setText(LM_ViewExamRequest.reason);
        DataInCB();
    }

    public void DataInCB() {
        HR2_RequestStatus er = new HR2_RequestStatus();

        try {
            List c = er.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                cbox_position.getItems().add("S00" + hm1.get("req_status_id") + " - " + hm1.get("req_status"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
