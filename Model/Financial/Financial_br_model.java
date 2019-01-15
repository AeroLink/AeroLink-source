/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Financial;

/**
 *
 * @author Gilbert
 */
public class Financial_br_model  extends Synapse.Model{
     public Financial_br_model(){
        setColumns("brr_id",
                "br_date",
                "br_department",
                "br_quantity",
                "br_itemname",
                "br_description",
                "br_price",
                "br_total_price",
                "br_status");
        this.initTable("tbl_finance_brr");
        
     }
}
