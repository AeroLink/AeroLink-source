/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Core2;

/**
 *
 * @author JPEG
 */
public class CORE2_surcharges extends Synapse.Model {

    public CORE2_surcharges() {
        this.initTable("tbl_core2_surcharges");
        setColumns("surcharges", "hwc", "add_charge");
    }
}
