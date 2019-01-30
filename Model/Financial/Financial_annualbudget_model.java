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
public class Financial_annualbudget_model extends Synapse.Model{
     public Financial_annualbudget_model(){
             
        setColumns("ab_id",
                "ab_date",
                "ab_year",
                "ab_amount");
        this.initTable("tbl_finance_annual_budget");
     }
}
