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
public class Financial_disbursement_request_model extends Synapse.Model{
    public Financial_disbursement_request_model(){
             
        setColumns("dr_id",
                "dr_requestno",
                "dr_daterequest",
                "dr_department",
                "dr_requestor",
                "dr_description",
                "dr_prioritylvl",
                "dr_amount",
                "dr_budget_status",
                "dr_status");
        this.initTable("tbl_finance_disbursement_request");
    
}
}
