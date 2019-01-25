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
public class Financial_disbursement_voucher extends Synapse.Model{
    
       public Financial_disbursement_voucher(){
             
        setColumns("dv_id",
                "budget_req_no",
                "budget_date_req",
                "dv_department",
                "dv_claimant",
                "dv_requestor",
                "dv_description",
                "dv_priority_level",
                "dv_amount",
                "dv_budget_status",
                "dv_disbursed_status",
                "dv_date_released",
                "dv_received_by");
        this.initTable("tbl_finance_disbursement_voucher");
    
}
    
    
    
}
