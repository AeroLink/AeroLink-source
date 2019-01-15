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
public class Financial_cm_model extends Synapse.Model{
     public Financial_cm_model(){
        
        setColumns("collection_id",
                         "po_id",
                         "asset_id" ,
                         "invoices1",
                         "col_name",
                         "col_description",
                         "col_date",
                         "col_type1",
                         "col_amount");
                                    
        
        this.initTable("tbl_financials_collection");
     }
}
