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
public class HR2_Training_Management extends Synapse.Model{
        
            public HR2_Training_Management()
            {
                   setColumns("training_id","job_position","training_title","training_description",
                           "trainor","start_date","end_date","start_time","end_time",
                           "type_of_training","location","vehicle","budget_cost");
                    this.initTable("tbl_hr2_training");
            }
}
