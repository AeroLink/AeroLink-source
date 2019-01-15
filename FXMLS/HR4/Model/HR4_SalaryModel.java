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
public class HR4_SalaryModel extends Synapse.Model{
   public HR4_SalaryModel()
            {
                setColumns("salary_grade","step1","step2", "step3", "step4", "step5", "step6", "step7", "step8");
                this.initTable("tbl_hr4_salarygrade");
            } 
}
