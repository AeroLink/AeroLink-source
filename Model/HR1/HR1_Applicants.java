/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR1;

/**
 *
 * @author Lei
 */
public class HR1_Applicants extends Synapse.Model {

    public HR1_Applicants() {
        this.initTable("tbl_hr1_applicants");
    }

    public HR1_Applicants(Boolean pivot) {
        if (pivot) {
            this.initTable("tbl_hr1_appJob_pivot");
        }
    }

    public HR1_Applicants(String pivot) {
        switch (pivot) {
            case "pre_screening":
                this.initTable("tbl_hr1_answerPreScreening");
                break;
            case "app_schedule":
                this.initTable("tbl_hr1_appSched");
                break;
            default:
                break;
        }
    }

}
