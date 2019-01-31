/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_JV_With_Skills_for_SP;
import FXMLS.HR2.ClassFiles.SP_EmployeeInfo_in_Modal;
import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
import Model.HR2_Courses;
import Model.HR2_Temp_Employee_Jobs;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR4_Classification;
import Model.HR4_Jobs;
import Model.VirtualTables.VT_HR2;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private Label lbl_suffix;
    @FXML
    private Label lbl_fullname;

    ObservableList<SP_EmployeeInfo_in_Modal> emp = FXCollections.observableArrayList();
    @FXML
    private Label lbl_employee_code;
    @FXML
    private JFXButton btn_req_promotion;
    @FXML
    private JFXComboBox cbox_select_position;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_fullname.setText(SP_Employee_Info_Modal.fullname);
        lbl_position.setText(SP_Employee_Info_Modal.title);
        populateLabels();
        selectJobs();

    }
    public void selectJobs() {
        HR4_Jobs jobs = new HR4_Jobs();

        try {
            List c = jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_department","id","dept","=","dept_id")
                    .where(new Object[][]{{"dept.dept_name","=",lbl_department.getText()}})
                    .get();
            //"concat(substring(title,0,2), job_id) as job_id, title"
            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                String j_id = String.valueOf(hm1.get("job_id"));
                String sjobs = (String) hm1.get("title");

                cbox_select_position.getItems().add("J" + j_id + " - " + sjobs);

            }
        }catch(Exception e)
        {
            System.out.println(e);
        }

    }
    @FXML
    public void RequestPromotion(){
        Modal req_promotion = Modal.getInstance(new Form("/FXMLS/HR2/Modals/SP_RequestPromotion.fxml").getParent());
        req_promotion.open();
    }
    public void populateLabels() {
        VT_HR2 vthr2 = new VT_HR2();

        List list = vthr2.join(Model.JOIN.INNER, "aerolink.tbl_hr1_civil_status", "id", "cs", "=", "e", "civil_status_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "ej", "=", "e", "employee_code", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "ej", "job_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "emps", "=", "e", "employee_code", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employeeTypes", "type_id", "=", "emps", "type_id", true)
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_perfGrading", "employee_code", "tp", "=", "e", "employee_code", true)
                .where(new Object[][]{{"e.fullname", "like", "%" + lbl_fullname.getText() + "%"}})
                .get("ej.employee_code,e.fullname,e.suffix_name,e.gender,aerolink.tbl_hr4_jobs.title,aerolink.tbl_hr4_department.dept_name,"
                        + "cs.civil_status,e.contact_number,aerolink.tbl_hr4_employeeTypes.type_name,emps.datehired,tp.productivity,tp.qualityofwork,"
                        + "tp.Initiative,tp.teamwork,tp.problemsolving,tp.attendance");
        list.stream().forEach(row -> {
            lbl_employee_code.setText(((HashMap) row).get("employee_code").toString());
            lbl_suffix.setText(((HashMap) row).get("suffix_name").toString());
            lbl_department.setText(((HashMap) row).get("dept_name").toString());
            lbl_gender.setText(((HashMap) row).get("gender").toString());
            lbl_civil_status.setText(((HashMap) row).get("civil_status").toString());
            lbl_cn.setText(((HashMap) row).get("contact_number").toString());
            lbl_status.setText(((HashMap) row).get("type_name").toString());
            lbl_date_hired.setText(((HashMap) row).get("datehired").toString());
            lbl_productivity.setText(((HashMap) row).get("productivity").toString());
            lbl_quality_of_work.setText(((HashMap) row).get("qualityofwork").toString());
            lbl_initiative.setText(((HashMap) row).get("Initiative").toString());
            lbl_teamwork.setText(((HashMap) row).get("teamwork").toString());
            lbl_problem_solving.setText(((HashMap) row).get("problemsolving").toString());
            lbl_attendance.setText(((HashMap) row).get("attendance").toString());
        });
    }

   /* public void UpdatePosition() {
             Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to promote " + lbl_fullname.getText() + " as " +
                jfx_position.getSelectionModel().getSelectedItem().toString() + "?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_Temp_Employee_Jobs e_jobs = new HR2_Temp_Employee_Jobs();

            Boolean a = e_jobs.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
                    .where(new Object[][]{
                {"employee_code", "=", lbl_employee_code.getText()}
            }).update(new Object[][]{
                {"aerolink.tbl_hr4_employee_jobs.job_id", jfx_position.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]}}).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText(lbl_fullname.getText() + " Successfully Promoted ");
            dropnotif.showAndWait();
    }*/
}
