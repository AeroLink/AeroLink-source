/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.TM_ViewTrainingReqClassModal;
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
}
