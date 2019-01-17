/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

/**
 *
 * @author Jaeeeee
 */
public class HR4_TaxModel extends Synapse.Model{
    public HR4_TaxModel()
    {
    setColumns("min","max","basic_amount","additional_rate","of_excess_over","total_tax");
    this.initTable("tbl_hr4_tax");
    }
    
}
