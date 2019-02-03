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
public class HR3_ShiftStatus extends Synapse.Model {
    public HR3_ShiftStatus(String path) {
        switch (path) {
            case "shift":
                this.initTable("tbl_hr3_shift_status");
                break;
            default:
                break;
        }
    }
}
