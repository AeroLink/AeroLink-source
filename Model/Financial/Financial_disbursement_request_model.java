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
public class Financial_disbursement_request_model extends Synapse.Model {
    
    public Financial_disbursement_request_model(){
         
            setColumns("disburse_id",
                "disburse_datesend",
                "disburse_date_req",
                "disburse_department",
                "disburse_requestor",
                "diburse_description",
                "disburse_prioritylvl",
                "disburse_amount",
                "disburse_status");
        this.initTable("tbl_financials_disbursement_requests");
    
    }
}
