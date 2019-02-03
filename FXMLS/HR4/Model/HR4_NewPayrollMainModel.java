/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author Jaeeeee
 */
public class HR4_NewPayrollMainModel extends Synapse.Model{
    public HR4_NewPayrollMainModel(){
        setColumns("payroll_code","start_date","end_date","dept_id");
        this.initTable("tbl_hr4_payroll2");
    }
}
