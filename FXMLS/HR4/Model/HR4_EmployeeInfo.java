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
public class HR4_EmployeeInfo extends Synapse.Model{
    public HR4_EmployeeInfo()
    {
        setColumns("id","employee_code","login_id","status_id","type_id","datehired","salary");
        this.initTable("tbl_hr4_employees");
    }
    
}
