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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(String date_hired) {
        this.date_hired = date_hired;
    }

    public String getProductivity() {
        return productivity;
    }

    public void setProductivity(String productivity) {
        this.productivity = productivity;
    }

    public String getQualityofwork() {
        return qualityofwork;
    }

    public void setQualityofwork(String qualityofwork) {
        this.qualityofwork = qualityofwork;
    }

    public String getInitiative() {
        return initiative;
    }

    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }

    public String getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(String teamwork) {
        this.teamwork = teamwork;
    }

    public String getProblemsolving() {
        return problemsolving;
    }

    public void setProblemsolving(String problemsolving) {
        this.problemsolving = problemsolving;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

}
