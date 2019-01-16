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
public class CORE2_optional_service extends Synapse.Model {

    public CORE2_optional_service() {
        this.initTable("tbl_core2_optional_services");
        setColumns("optional_service", "hwc", "add_charge");
    }
}
