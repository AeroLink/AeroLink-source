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
public class HR4_SalaryGradeUpModel extends Synapse.Model{
    public HR4_SalaryGradeUpModel()
    {
        setColumns("id","req_code","emp_code","productivity","qualityofwork","initiative","teamwork","prob_solv","attendance","ave","req_dept_id","req_classification_id","req_job_id","req_designation_id","status","created_at","updated_at");
        this.initTable("tbl_hr4_compegrade");
    }
    
    
}
