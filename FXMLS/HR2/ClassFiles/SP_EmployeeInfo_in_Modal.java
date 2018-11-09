/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class SP_EmployeeInfo_in_Modal {

    public String fullname, gender, title, dept_name, civil_status, contact_number, type_name,
            date_hired, productivity, qualityofwork, initiative, teamwork, problemsolving, attendance;

    public SP_EmployeeInfo_in_Modal(String fullname, String gender,
            String dept_name, String title, String civil_status, String contact_number,
            String type_name, String date_hired, String productivity,
            String qualityofwork, String initiative, String teamwork,
            String problemsolving, String attendance) {

        this.fullname = fullname;
        this.gender = gender;
        this.title = title;
        this.dept_name = dept_name;
        this.civil_status = civil_status;
        this.contact_number = contact_number;
        this.type_name = type_name;
        this.date_hired = date_hired;
        this.productivity = productivity;
        this.qualityofwork = qualityofwork;
        this.initiative = initiative;
        this.teamwork = teamwork;
        this.problemsolving = problemsolving;
        this.attendance = attendance;
    }

}
