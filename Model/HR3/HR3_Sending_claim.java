/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR3;

/**
 *
 * @author my
 */
public class HR3_Sending_claim extends Synapse.Model {

    public HR3_Sending_claim(String path) {
        switch (path) {
            case "dummy":
                this.initTable("tbl_hr3_payroll_dummy");
                break;
            default:
                break;
        }
    }
}
