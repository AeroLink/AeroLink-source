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
public class CORE2_itmrate extends Synapse.Model{
    public CORE2_itmrate(){
        this.initTable("tbl_core2_itmrate");
        setColumns("dec_items","percentage","quantity_desc","hwc","vat_gst");
    }
}
