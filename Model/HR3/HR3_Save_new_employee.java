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
public class HR3_Save_new_employee extends Synapse.Model {
    public HR3_Save_new_employee(String path) {
        switch (path) {
            case "yup":
                this.initTable("tbl_hr3_weekdays");
                break;
            default:
                break;
        }
    }
    
}
