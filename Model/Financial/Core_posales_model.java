/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Financial;

import static Synapse.Model.setColumns;

/**
 *
 * @author Gilbert
 */
public class Core_posales_model extends Synapse.Model{
     public Core_posales_model(){
             
        setColumns("po_id",
                "po_date",
                "po_invoice",
                "po_description",
                "po_amount",
                "po_status",
                "po_type");
        this.initTable("tbl_pom_core1");
    
}
}
