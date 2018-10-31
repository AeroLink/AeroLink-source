/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import Model.HR2_Courses;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR2_Temp_Vehicles;
import Model.HR2_Training_Info;
import Model.HR2_Type_of_Training;
import Model.HR4_Jobs;
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
 * @author Eden Ramoneda
 */
public class Add_CoursesController implements Initializable {

    @FXML
    private JFXComboBox cbox_select_job_position;
    @FXML
    private JFXTextArea txt_course_desc;
    @FXML
    private JFXComboBox cbox_select_employees;
    @FXML
    private JFXButton btn_save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataInComboBoxes();
        btn_save.setOnAction(e -> Save());
    //    cbox_select_employees.getSelectionModel().getSelectedItem().(e -> validate());
    }

    public void loadDataInComboBoxes() {
        HR4_Jobs jobs = new HR4_Jobs();
        HR2_Temp_Employee_Profiles employees = new HR2_Temp_Employee_Profiles();
        try {
            List c = jobs.get();

            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                String j_id = String.valueOf(hm1.get("job_id"));
                String sjobs = (String) hm1.get("title");

                cbox_select_job_position.getItems().add("J" + j_id + " - " + sjobs);

                List set_emp = employees.get();

                for (Object ep : set_emp) {
                    HashMap hm2 = (HashMap) ep;
                    //RS
                    cbox_select_employees.getItems().add("EMP" + hm2.get("id") + " - " + hm2.get("firstname") + " " + hm2.get("middlename") + " " + hm2.get("lastname"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void validate() {
        if (!cbox_select_job_position.getValue().toString().isEmpty() && !txt_course_desc.getText().isEmpty() && !cbox_select_job_position.getValue().toString().isEmpty()) {

            btn_save.setDisable(false);
        } else {
            btn_save.setDisable(true);
        }
    }

    public void Save() {
        if (cbox_select_job_position.getValue().toString().isEmpty() || txt_course_desc.getText().isEmpty()
                || cbox_select_job_position.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
        } else {
            try {
                HR2_Courses c = new HR2_Courses();

                String[][] cm_data
                        = {
                            {"job_id", cbox_select_job_position.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                            {"course_description", txt_course_desc.getText()},
                            {"id", cbox_select_employees.getSelectionModel().getSelectedItem().toString().substring(3).toString().split(" - ")[0]}
                        };
                //int id = model.insert(vals, true);
                c.insert(cm_data);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                cbox_select_job_position.setValue(null);
                txt_course_desc.setText("");
                cbox_select_employees.setValue(null);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error" + e);
                alert.showAndWait();
            }
        }
    }
}
