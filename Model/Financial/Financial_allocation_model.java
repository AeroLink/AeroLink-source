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
public class Financial_allocation_model extends Synapse.Model{
    public Financial_allocation_model(){
    setColumns("ba_id",
            "ba_date",
            "ba_no",
           "ba_department",
            "ba_amount",
            "ba_status");
    this.initTable("tbl_finance_budgetallocation");
   
}
}
