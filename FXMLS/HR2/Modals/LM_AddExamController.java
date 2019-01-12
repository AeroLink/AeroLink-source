/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_LM_AddExamModalClass;
import Model.HR2_Courses;
import Model.HR2_Examination;
import Model.HR2_Temp_Employee_Profiles;
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
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class LM_AddExamController implements Initializable {

    @FXML
    private JFXTextField txt_exam_name;
    @FXML
    private JFXTextArea txt_exam_desc;
    @FXML
    private JFXComboBox txt_created_by;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXComboBox cbox_job_title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbox_job_title.getItems().add("JOB00" + HR2_LM_AddExamModalClass.jid + " - " + HR2_LM_AddExamModalClass.jt);
        cbox_job_title.getSelectionModel().selectFirst();
        loadDataInComboBoxes();
    }

    public void loadDataInComboBoxes() {
        HR4_Jobs jobs = new HR4_Jobs();
        HR2_Temp_Employee_Profiles employees = new HR2_Temp_Employee_Profiles();
        try {
            List c = jobs.get();

            for (Object d : c) {    


                List set_emp = employees.get();

                for (Object ep : set_emp) {
                    HashMap hm2 = (HashMap) ep;
                    //RS
                    txt_created_by.getItems().add("EMP" + hm2.get("id") + " - " + hm2.get("firstname") + " " + hm2.get("middlename") + " " + hm2.get("lastname"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void Save() {
        if (txt_exam_name.getText().isEmpty() || txt_exam_desc.getText().isEmpty()
                || txt_created_by.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
        } else {
            try {
                HR2_Examination c = new HR2_Examination();

                String[][] cm_data
                        = {
                            {"exam_name", txt_exam_name.getText()},
                            {"exam_desc", txt_exam_desc.getText()},
                            {"course_id", cbox_job_title.getSelectionModel().getSelectedItem().toString().substring(5).toString().split(" - ")[0]},
                            {"id", txt_created_by.getSelectionModel().getSelectedItem().toString().substring(3).toString().split(" - ")[0]},
                            {"isDeleted", "0"},};
                //int id = model.insert(vals, true);
                c.insert(cm_data);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                txt_exam_name.setText("");
                txt_exam_desc.setText("");
                txt_created_by.setValue(null);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error" + e);
                alert.showAndWait();
            }
        }
    }

}
