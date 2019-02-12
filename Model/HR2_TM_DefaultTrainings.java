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
public class HR2_TM_DefaultTrainings extends Synapse.Model{
    
    public HR2_TM_DefaultTrainings(){
        setColumns("dt_id","job_id", "training_title", "training_desc", "trainor", "start_date", "end_date","start_time",
                "end_time","type_of_training_id","facility_id","vehicle_id","budget_id");
        this.initTable("aerolink.tbl_hr2_default_trainings");
    }
}
