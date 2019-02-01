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
public class HR2_TM_TrainingInfo extends Synapse.Model{
    
    public HR2_TM_TrainingInfo(){
        setColumns("tr_id","trainor","start_time","end_time","type_of_training_id","facility_id","budget_id"
        ,"vehicle_id");
        this.initTable("aerolink.tbl_hr2_trainingInfo");
    }
    
}
