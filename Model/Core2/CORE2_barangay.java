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
public class CORE2_barangay extends Synapse.Model{
    public CORE2_barangay(){
        this.initTable("tbl_core2_barangays");
        setColumns("barangay_id","barangay_name");
    }
}
