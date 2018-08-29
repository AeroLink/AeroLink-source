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
public class FINANCIAL_AR extends Synapse.Model{
   
    
    public FINANCIAL_AR(){
                setColumns("ar_id","invoice_no","date","payee","particular","amount");
                setTable("tbl_finance_accounts_receivable");
            }
   
}
    
