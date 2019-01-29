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
public class Financial_ar_model extends Synapse.Model{
     public Financial_ar_model(){
             
        setColumns("ar_id",
                "dates",
                "invoiceno",
                "descriptions",
                "amount",
                "sstatus",
                "typess");
        this.initTable("tbl_finance_ar");
     }
}
