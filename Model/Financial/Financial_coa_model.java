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
public class Financial_coa_model extends Synapse.Model{
    
        
         public Financial_coa_model(){
             
        setColumns("coa_id","code_no","acc_title");
        this.initTable("tbl_financials_coa");
    
}
}
