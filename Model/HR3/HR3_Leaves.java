    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR3;

/**
 *
 * @author BlackMoon
 */
public class HR3_Leaves extends Synapse.Model {

    public HR3_Leaves(String path) {
        switch (path) {
            case "typeofleaves":
                this.initTable("tbl_hr3_typeofleaves");
                break;
            default:
                break;
        }
    }

}
