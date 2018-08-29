/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author JaeJae
 */
public class FINANCIAL_APR extends Synapse.Model{
        
            public FINANCIAL_APR(){
                setColumns("ap_id","ap_date","ap_payee","ap_particular","ap_amount");
                setTable("tbl_finance_accounts_payable");
                
                
            }
    
}
