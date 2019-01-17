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
public class HR4_PayrollModel extends Synapse.Model{
    public HR4_PayrollModel()
    {
    setColumns("id","employee_code","job_id","dept_id","classification_id","gross_pay","net_pay","start_date","end_date");
    this.initTable("tbl_hr4_payroll");
    }
    
}
