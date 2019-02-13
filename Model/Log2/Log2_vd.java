/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Log2;

import static Synapse.Model.setColumns;

/**
 *
 * @author Randelle
 */
public class Log2_vd extends Synapse.Model {

    public Log2_vd(String path) {
        switch (path) {
            case "vd":
                this.initTable("aerolink.tbl_log2_vd_listofreservation");
                break;
            default:
                break;
        }
    }
}
