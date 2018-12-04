/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_LM_ViewCourseModal;
import Model.HR2_Courses;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class LM_ViewCourseController implements Initializable {

    
    private String course_id;
    @FXML
    private JFXComboBox cbox_ct;
    @FXML
    private JFXTextArea txt_c_description;
    @FXML
    private JFXComboBox cbox_created_by;
    @FXML
    private JFXButton btn_update;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbox_ct.getItems().add(HR2_LM_ViewCourseModal.ct);
        cbox_ct.getSelectionModel().selectFirst();
        loadDataInLabel();
        loadDataInComboBoxes();
    }

    public void loadDataInComboBoxes() {
        HR4_Jobs jobs = new HR4_Jobs();
        HR2_Temp_Employee_Profiles employees = new HR2_Temp_Employee_Profiles();
        try {

            List set_emp = employees.get();

            for (Object ep : set_emp) {
                HashMap hm2 = (HashMap) ep;
                //RS
                cbox_created_by.getItems().add("EMP" + hm2.get("id") + " - " + hm2.get("firstname") + " " + hm2.get("middlename") + " " + hm2.get("lastname"));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    
    public void loadDataInLabel() {
        HR2_Courses c = new HR2_Courses();
        List courses = c.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "emps", "=", "id")
                .where(new Object[][]{{"j.title", "=", cbox_ct.getValue().toString()}})
                .get("course_id, j.title as course_title", "course_description",
                        "concat(emps.id, ' - ', emps.firstname, ' ',emps.middlename, ' ',emps.lastname)as Created_by");

        courses.stream().forEach(row -> {
            txt_c_description.setText(((HashMap) row).get("course_description").toString());
            cbox_created_by.setValue("EMP" + ((HashMap) row).get("Created_by").toString());
            course_id = ((HashMap) row).get("course_id").toString();
        });
    }

    @FXML
    public void UpdateCourse() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_Courses c = new HR2_Courses();

            Boolean a = c.where(new Object[][]{
                {"course_id", "=", course_id}
            }).update(new Object[][]{
                {"course_description", txt_c_description.getText()},
                {"id", cbox_created_by.getSelectionModel().getSelectedItem().toString().split(" - ")[0].substring(3)},
            }).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText(cbox_ct.getSelectionModel().getSelectedItem().toString() + " Successfully Updated");
            dropnotif.showAndWait();
        }

    }
}
