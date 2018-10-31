/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_JV_With_Skills_for_SP;
import FXMLS.HR2.ClassFiles.SP_EmployeeInfo_in_Modal;
import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
import FXMLS.HR2.Database;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ObservableList<SP_EmployeeInfo_in_Modal> emp = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_fullname.setText(SP_Employee_Info_Modal.fullname);
        lbl_position.setText(SP_Employee_Info_Modal.title);

       try {
            PreparedStatement ps = Database.getPreparedStatement("select e.fullname, e.gender, j.title, d.dept_name,cs.civil_status,e.contact_number,et.type_name,emps.datehired,\n"
                    + "tp.productivity,tp.qualityofwork,tp.Initiative,tp.teamwork,tp.problemsolving,tp.attendance from\n"
                    + "( SELECT CONCAT(ep.firstname,' ',ep.middlename,' ',ep.lastname,' ',s.suffix_name)as fullname,ep.civil_status_id,\n"
                    + "ep.employee_code,ep.gender,ep.contact_number from aerolink.tbl_hr4_employee_profiles as ep inner join \n"
                    + "aerolink.tbl_hr1_suffix s on ep.suffix_id = s.id)as e inner join\n"
                    + "aerolink.tbl_hr1_civil_status cs on e.civil_status_id = cs.id inner join aerolink.tbl_hr4_employee_jobs ej on \n"
                    + "e.employee_code = ej.employee_code inner join aerolink.tbl_hr4_jobs j on ej.job_id = j.job_id inner join\n"
                    + "aerolink.tbl_hr4_department d on j.dept_id = d.id inner join aerolink.tbl_hr4_employees emps on e.employee_code = \n"
                    + "emps.employee_code inner join aerolink.tbl_hr4_employeeTypes et on emps.type_id = et.type_id inner join \n"
                    + "temp_performace tp on e.employee_code = tp.employee_code where e.fullname = ?");
            ps.setString(1, lbl_fullname.getText());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String f = rs.getString("fullname");
                String g = rs.getString("gender");
                String t = rs.getString("title");
                String dn = rs.getString("dept_name");
                String cs = rs.getString("civil_status");
                String cb = rs.getString("contact_number");
                String tn = rs.getString("type_name");
                String dh = rs.getString("datehired");
                String p = rs.getString("productivity");
                String qow = rs.getString("qualityofwork");
                String i =  rs.getString("Initiative");
                String tw = rs.getString("teamwork");
                String prs = rs.getString("problemsolving");
                String a = rs.getString("attendance");
                emp.add(new SP_EmployeeInfo_in_Modal(f,g,t, dn, cs, cb, tn, dh, p, qow, i, tw, prs, a));
                 System.out.println("Eto ung data" + f);
                 lbl_gender.setText(g);
                     
            }    
            
            
        } catch (SQLException ex) {
             System.out.println(ex);
        } catch (ClassNotFoundException ex) {
           System.out.println(ex);
        }
                         
               
      //  populateLabels();

    }

    public void populateLabels() {
        // HR2_Temp_Employee_Profiles emp = new HR2_Temp_Employee_Profiles();

        /*   List e = emp//.join(Model.JOIN.INNER, "aerolink.tbl_hr1_suffix", "id", "s", "=", "suffix_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_civil_status", "id", "cs", "=", "civil_status_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "ej", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "ej", "job_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "emps", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employeeTypes", "type_id", "=", "emps", "type_id", true)
                .join(Model.JOIN.INNER, "temp_performace", "employee_code", "tp", "=", "employee_code")
                .where(new Object[][]{{"aerolink.tbl_hr4_employees.lastname","like" , "%",lbl_fullname.getText(), "%"}})
                .get("aerolink.tbl_hr4_employee_profiles.fullname,aerolink.tbl_hr4_employee_profiles.gender,aerolink.tbl_hr4_jobs.title,aerolink.tbl_hr4_department.dept_name,\n"
                        + "cs.civil_status,aerolink.tbl_hr4_employee_profiles.contact_number,aerolink.tbl_hr4_employeeTypes.type_name,emps.datehired,tp.productivity,tp.qualityofwork,\n"
                        + "tp.Initiative,tp.teamwork,tp.problemsolving,tp.attendance");*/
 /*     HR4_Jobs j = new HR4_Jobs();
     List e = j.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id","d", "=", "dept_id")
               .join(Model.JOIN.INNER, "( SELECT CONCAT(ep.firstname,' ',ep.middlename,' ',ep.lastname,' ',s.suffix_name)as fullname,ep.civil_status_id,\n" +
"ep.employee_code,ep.gender,ep.contact_number from aerolink.tbl_hr4_employee_profiles as ep inner join \n" +
"aerolink.tbl_hr1_suffix s on ep.suffix_id = s.id)as e", "employee_code", "=","aerolink.tbl_hr4_employee_jobs", "employee_code",true)
             .get();*/
    }

}
