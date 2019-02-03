/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.CM_SkillReq_ModalClass;
import FXMLS.HR2.ClassFiles.LM_ViewExamRequest;
import Model.HR2_CM_Skill_Requisition;
import Model.HR2_LM_Exam_Request;
import Model.HR2_RequestStatus;
import Synapse.Model;
import Synapse.STORED_PROC;
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
    private JFXButton btn_submit;
    @FXML
    private JFXComboBox cbox_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_department.setText(LM_ViewExamRequest.dept_id);
        lbl_job.setText(LM_ViewExamRequest.job_id);
        txt_reason.setText(LM_ViewExamRequest.reason);
        DataInCB();
        loadData();
    }

    public void DataInCB() {
        HR2_RequestStatus er = new HR2_RequestStatus();

        try {
            List c = er.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                cbox_status.getItems().add("S00" + hm1.get("req_status_id") + " - " + hm1.get("req_status"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadData() {
        HR2_LM_Exam_Request lmer = new HR2_LM_Exam_Request();

        List exam_req = lmer.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .where(new Object[][]{
            {"dept.dept_name", "=", lbl_department.getText()},
            {"aerolink.tbl_hr2_exam_requisition.isDeleted", "<>", "1"}
        }).get("er_id", "dept.dept_name,jobs.title,reason, concat('S00', aerolink.tbl_eis_request_status.req_status_id,' - ', aerolink.tbl_eis_request_status.req_status) as status");
        
        exam_req.stream().forEach(row -> {
            cbox_status.setValue(((HashMap) row).get("status").toString());
        });
    }
    String rq_id = "";
    
    @FXML
    public void SubmitApproval(){
           HR2_LM_Exam_Request sr = new HR2_LM_Exam_Request();
            List<HashMap> s = sr.where(new Object[][]{{"er_id", "=", LM_ViewExamRequest.er_id}}).get();
            
            s.stream().forEach(( HashMap hash) -> {
                rq_id = hash.get("request_id").toString();
            });
            
            STORED_PROC.executeCall("EIS_updateRequestStatus", new Object[][]{
                {"request_id", rq_id},
                {"request_status", cbox_status.getSelectionModel().getSelectedItem().toString().substring(3).split(" - ")[0]}
            });

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Submitted");
            saved.showAndWait();
        
    }

}
