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
public class HR3_sending_budget extends Synapse.Model {

    public HR3_sending_budget(String path) {
        switch (path) {
            case "dummy":
                this.initTable("tbl_hr3_budget_dummy");
                break;
            default:
                break;
        }
    }
}
