/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Core2;

/**
 *
 * @author jpeg
 */
public class CORE2_package extends Synapse.Model {

    public CORE2_package() {
        this.initTable("tbl_core2_package");
        setColumns("package_no", "ref_no", "ship_name", "list_item", "item_value", "weight", "note", "status");
    }
}
