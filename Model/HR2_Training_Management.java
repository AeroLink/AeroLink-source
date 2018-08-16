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
                   setColumns("emp_id", "emp_name" , "job_position","title","trainor","type_of_training", "location","date_start","date_end","time_start","time_end","budget_cost","organizer");
                    setTable("tbl_training_management");
            }
}
