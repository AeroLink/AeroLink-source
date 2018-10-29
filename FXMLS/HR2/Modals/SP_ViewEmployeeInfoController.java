/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
import Model.HR2_Temp_Employee_Profiles;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
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
public class SP_ViewEmployeeInfoController implements Initializable {

    @FXML
    private Label lbl_position;
    @FXML
    private Label lbl_department;
    @FXML
    private Label lbl_gender;
    @FXML
    private Label lbl_civil_status;
    @FXML
    private Label lbl_cn;
    @FXML
    private Label lbl_status;
    @FXML
    private Label lbl_date_hired;
    @FXML
    private Label lbl_productivity;
    @FXML
    private Label lbl_quality_of_work;
    @FXML
    private Label lbl_initiative;
    @FXML
    private Label lbl_teamwork;
    @FXML
    private Label lbl_problem_solving;
    @FXML
    private Label lbl_attendance;
    @FXML
    private Label lbl_rating;
    @FXML
    private Label lbl_remarks;
    @FXML
    private JFXButton btn_update_position;
    @FXML
    private JFXComboBox jfx_position;
    @FXML
    private Label lbl_suffix;
    @FXML
    private Label lbl_fullname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_fullname.setText(SP_Employee_Info_Modal.fullname);
        lbl_position.setText(SP_Employee_Info_Modal.title);
        populateLabels();
    }

    public void populateLabels() {
        HR2_Temp_Employee_Profiles emp = new HR2_Temp_Employee_Profiles();

        List e = emp.join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "s", "=", "suffix_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_civil_status", "id", "cs", "=", "civil_status_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "ej", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "ej", "job_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "emps", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employeeTypes", "type_id", "=", "emps", "type_id", true)
                .join(Model.JOIN.INNER, "temp_performace", "employee_code", "tp", "=", "employee_code")
                .where(new Object[][]{{"aerolink.tbl_hr4_employees.lastname","like" , "%",lbl_fullname.getText(), "%"}})
                .get("concat(aerolink.tbl_hr4_employee_profiles.firstname,' ',aerolink.tbl_hr4_employee_profiles.middlename,' ',aerolink.tbl_hr4_employee_profiles.lastname, ' ',s.suffix_name),aerolink.tbl_hr4_employee_profiles.gender,aerolink.tbl_hr4_jobs.title,aerolink.tbl_hr4_department.dept_name,\n"
                        + "cs.civil_status,aerolink.tbl_hr4_employee_profiles.contact_number,aerolink.tbl_hr4_employeeTypes.type_name,emps.datehired,tp.productivity,tp.qualityofwork,\n"
                        + "tp.Initiative,tp.teamwork,tp.problemsolving,tp.attendance");
    }
    
    

}
