/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.CM_SkillReq_ModalClass;
import Model.HR2_CM_Skill_Requisition;
import Model.HR2_RequestStatus;
import Model.HR4_Departments;
import Synapse.Model;
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
    private JFXComboBox cbox_status;
    @FXML
    private JFXButton btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_dept.setText(CM_SkillReq_ModalClass.dept_name);
        txt_jp.setText(CM_SkillReq_ModalClass.title);
        txt_jp.setText(CM_SkillReq_ModalClass.title);
     /*   cbox_status.getItems().add(CM_SkillReq_ModalClass.req_status_id + " - " + CM_SkillReq_ModalClass.req_status);
        cbox_status.getSelectionModel().selectFirst();*/
        DisplayDataInLabel();
        DataInCB();
        
    }
        public void DataInCB() {
        HR2_RequestStatus rs = new HR2_RequestStatus();
        try {

            List c1 = rs.get();
            for (Object d2 : c1) {
                HashMap hm2 = (HashMap) d2;
                cbox_status.getItems().add("S00" + hm2.get("req_status_id") + " - " + hm2.get("req_status"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void DisplayDataInLabel() {

        HR2_CM_Skill_Requisition s_skill_req = new HR2_CM_Skill_Requisition();

        List skill_req_data = s_skill_req.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "employees", "=", "requested_by")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_request_status ", "req_status_id", "req_stat", "=", "req_status_id")
                .where(new Object[][]{{"aerolink.tbl_hr2_skill_requisition.sr_id", "=", CM_SkillReq_ModalClass.sr_id}})
                .get("sr_id","reason", "CONCAT('EMP', employees.id , ' - ',employees.firstname, ' ' ,employees.middlename, ' ',"
                        + "employees.lastname)as requested_by, date_requested, concat('S00',req_stat.req_status_id,' - ',req_stat.req_status) as request_status");
        Data(skill_req_data);

    }

    public void Data(List b) {
        b.stream().forEach(row -> {
           
            txt_reason.setText(((HashMap) row).get("reason").toString());
            txt_employee_code.setText(((HashMap) row).get("requested_by").toString());
            txt_date_req.setText(((HashMap) row).get("date_requested").toString());
            cbox_status.setValue(((HashMap) row).get("request_status").toString());
        });
    }

}
