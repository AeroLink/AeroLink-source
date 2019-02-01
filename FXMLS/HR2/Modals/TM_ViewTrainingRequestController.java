/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.TM_ViewTrainingReqClassModal;
import Model.HR2_RequestStatus;
import Model.HR2_TM_Training_Requisition;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.Arrays;
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
public class TM_ViewTrainingRequestController implements Initializable {

    @FXML
    private Label lbl_dept;
    @FXML
    private Label lbl_jp;
    @FXML
    private Label lbl_date_req;
    @FXML
    private Label lbl_training_title;
    @FXML
    private Label lbl_no_of_participants;
    @FXML
    private Label lbl_total_hours;
    @FXML
    private Label lbl_total_hours1;
    @FXML
    private Label lbl_from_day;
    @FXML
    private Label lbl_total_hours11;
    @FXML
    private Label lbl_to_day;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXComboBox cbox_status;
    @FXML
    private Label lbl_req_by;
    @FXML
    private JFXButton btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_dept.setText(TM_ViewTrainingReqClassModal.dept);
        lbl_jp.setText(TM_ViewTrainingReqClassModal.job_position);
        lbl_date_req.setText(TM_ViewTrainingReqClassModal.date_req);
        /*  cbox_status.getItems().add("S00" + TM_ViewTrainingReqClassModal.status_id + " - " + TM_ViewTrainingReqClassModal.status);
        cbox_status.getSelectionModel().selectFirst();*/
        DataInCB();
        loadDataInJLabels();
    }

    public void DataInCB() {
        HR2_RequestStatus rst = new HR2_RequestStatus();
        List req_status = rst.get();

        for (Object h : req_status) {
            HashMap hm5 = (HashMap) h;
            //RS
            cbox_status.getItems().add("S00" + hm5.get("req_status_id") + " - " + hm5.get("req_status"));
        }
    }

    public void loadDataInJLabels() {
        try {
            HR2_TM_Training_Requisition tr = new HR2_TM_Training_Requisition();
            List training_req_archive = tr.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_request_status", "req_status_id", "rs", "=", "req_status_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp", "=", "requested_by")
                    .where(new Object[][]{
                {"aerolink.tbl_hr2_training_requisition.tr_id", "=", TM_ViewTrainingReqClassModal.tr_id},
                {"aerolink.tbl_hr2_training_requisition.isDeleted", "=", "0"}})
                    .get("aerolink.tbl_hr2_training_requisition.tr_id,training_title,no_of_participants,total_hours,"
                            + "concat(emp.firstname,' ',emp.middlename,' ',emp.lastname) as requested_by,"
                            + "from_day, to_day, reason, concat('S00',rs.req_status_id,' - ',rs.req_status)as status");
            Data(training_req_archive);

            System.err.println(Arrays.asList(training_req_archive.toArray()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Data(List c) {
        c.stream().forEach(row -> {
            lbl_training_title.setText(((HashMap) row).get("training_title").toString());
            lbl_no_of_participants.setText(((HashMap) row).get("no_of_participants").toString());
            lbl_total_hours.setText(((HashMap) row).get("total_hours").toString());
            lbl_from_day.setText(((HashMap) row).get("from_day").toString());
            lbl_to_day.setText(((HashMap) row).get("to_day").toString());
            txt_reason.setText(((HashMap) row).get("reason").toString());
            lbl_req_by.setText(((HashMap) row).get("requested_by").toString());
            cbox_status.setValue(((HashMap) row).get("status").toString());
        });
    }
}
