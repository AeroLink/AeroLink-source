/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR4;

/**
 *
 * @author Lei
 */
public class HR4_Employee extends Synapse.Model {

    public HR4_Employee() {
        this.initTable("tbl_hr4_employees");
    }

    public HR4_Employee(String trigger) {

        switch (trigger) {
            case "profile":
                this.initTable("tbl_hr4_employee_profiles");
                break;
            case "familyBackground":
                this.initTable("tbl_hr4_employee_FB");
                break;
            case "EducAttain":
                this.initTable("tbl_hr4_employee_educAttain");
                break;
            case "TSM":
                this.initTable("tbl_hr4_employee_trainingSeminars");
                break;
            case "job":
                this.initTable("tbl_hr4_employee_jobs");
                break;
            case "WE":
                this.initTable("tbl_hr4_employee_workExp");
                break;
            case "CE":
                this.initTable("tbl_hr4_employee_certifications");
                break;
            case "AW":
                this.initTable("tbl_hr4_employee_academicAwards");
                break;
            case "GVID":
                this.initTable("tbl_hr4_employee_governmentIDs");
                break;
            default:
                break;
        }
    }

}
