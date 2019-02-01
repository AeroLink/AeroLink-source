/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_SP_ExamTaken extends Synapse.Model{
    
    public HR2_SP_ExamTaken(){
        setColumns("eer_id","employee_code","exam_id","score");
        this.initTable("aerolink.tbl_hr2_employee_exam_record");
    }
    
}
