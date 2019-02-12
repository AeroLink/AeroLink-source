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
public class HR3_dummy_shift extends Synapse.Model {

    public HR3_dummy_shift(String path) {
        switch (path) {
            case "dummyshift":
                this.initTable("tbl_hr3_dummy_request_shift");
                break;
            default:
                break;
        }
    }
    
}
