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
public class HR4_PayrollEmployeeModel extends Synapse.Model{
    public HR4_PayrollEmployeeModel()
    {
    setColumns("id","payroll_code","employee_code");
    this.initTable("tbl_hr4_payroll3");
    }
}
