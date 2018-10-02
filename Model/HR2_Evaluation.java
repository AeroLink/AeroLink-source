/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Evaluation extends Synapse.Model{
    
    public HR2_Evaluation()
    {
          setColumns("choice_id", "question_id","choice"
        ,"choice_description","ischecked");
        this.initTable("tbl_hr2_evaluation");
    }
}
