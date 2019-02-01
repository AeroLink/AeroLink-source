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
public class Financial_budget_request extends Synapse.Model {
    
    
    public Financial_budget_request(){
                setColumns("budget_id",
                "budget_date_request",
                "budget_department  ",
                "budget_requestor",
                "budget_description",
                "budget_priority_lvl",
                "budget_amount",
                "budget_status",
                "budget_disbursementstatus",
                "budget_approvedby",
                "budget_reason");
        this.initTable("tbl_financials_request_budget");
}
}
