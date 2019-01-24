/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.CM_SkillReq_ModalClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
        cbox_status.getItems().add(CM_SkillReq_ModalClass.req_status_id + " - " + CM_SkillReq_ModalClass.req_status);
        cbox_status.getSelectionModel().selectFirst();
    }

}
