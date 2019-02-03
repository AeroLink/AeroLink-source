/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.CM_SkillReq_ModalClass;
import FXMLS.HR2.ClassFiles.TM_ViewTrainingReqClassModal;
import Model.EIS_Requisition;
import Model.HR2_CM_Skill_Requisition;
import Model.HR2_RequestStatus;
import Model.HR2_TM_Training_Requisition;
import Model.HR4_Departments;
import Synapse.Model;
import Synapse.STORED_PROC;
import Synapse.Session;
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

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class CM_Skill_Req_DetailsController implements Initializable {

    @FXML
    private JFXTextField txt_dept;
    @FXML
    private JFXTextField txt_jp;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXTextField txt_employee_code;
    @FXML
    private JFXTextField txt_date_req;
    @FXML
    private JFXButton btn_submit;
    @FXML
    private JFXComboBox cbox_select_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_dept.setText(CM_SkillReq_ModalClass.dept_name);
        txt_jp.setText(CM_SkillReq_ModalClass.title);
        /*   cbox_status.getItems().add(CM_SkillReq_ModalClass.req_status_id + " - " + CM_SkillReq_ModalClass.req_status);
        cbox_status.getSelectionModel().selectFirst();*/
        // DisplayDataInLabel();
        Status();
        DisplayDataInLabel();
    }

    public void Status() {
        HR2_RequestStatus er = new HR2_RequestStatus();

        try {
            List c = er.get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                cbox_select_status.getItems().add("S00" + hm1.get("req_status_id") + " - " + hm1.get("req_status"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInLabel() {

        HR2_CM_Skill_Requisition s_skill_req = new HR2_CM_Skill_Requisition();

        List skill_req_data = s_skill_req.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "employees", "=", "requested_by")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_requisition", "request_id", "rs", "=", "request_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_eis_request_status", "req_status_id", "=", "rs", "request_status", true)
                .where(new Object[][]{{"aerolink.tbl_hr2_skill_requisition.sr_id", "=", CM_SkillReq_ModalClass.sr_id}})
                .get("sr_id", "reason", "CONCAT('EMP', employees.employee_code , ' - ',employees.firstname, ' ' ,employees.middlename, ' ',"
                        + "employees.lastname)as requested_by, date_requested","CONCAT('S00',aerolink.tbl_eis_request_status.req_status_id,' - ',aerolink.tbl_eis_request_status.req_status) as status");
        Data(skill_req_data);

    }

    public void Data(List b) {
        b.stream().forEach(row -> {

            txt_reason.setText(((HashMap) row).get("reason").toString());
            txt_employee_code.setText(((HashMap) row).get("requested_by").toString());
            txt_date_req.setText(((HashMap) row).get("date_requested").toString());
            cbox_select_status.setValue(((HashMap) row).get("status").toString());
        });
    }
    String rq_id = "";

    @FXML
    public void Submit() {
        try {
            HR2_CM_Skill_Requisition sr = new HR2_CM_Skill_Requisition();
            List<HashMap> s = sr.where(new Object[][]{{"sr_id", "=", CM_SkillReq_ModalClass.sr_id}}).get();
            
            s.stream().forEach(( HashMap hash) -> {
                rq_id = hash.get("request_id").toString();
            });
            
            STORED_PROC.executeCall("EIS_updateRequestStatus", new Object[][]{
                {"request_id", rq_id},
                {"request_status", cbox_select_status.getSelectionModel().getSelectedItem().toString().substring(3).split(" - ")[0]}
            });

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
