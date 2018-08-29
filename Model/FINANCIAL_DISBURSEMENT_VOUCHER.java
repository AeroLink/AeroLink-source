/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;

/**
 *
 * @author JaeJae
 */
public class FINANCIAL_DISBURSEMENT_VOUCHER extends Synapse.Model {
    
    public FINANCIAL_DISBURSEMENT_VOUCHER(){
        
        setColumns("dv_no","date","claimants","particular","amount","budget_code");
        setTable("tbl_finance_disbursement_voucher");
        
     
        
        
         }    
    
}
