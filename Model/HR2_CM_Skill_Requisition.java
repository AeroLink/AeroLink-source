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
public class HR2_CM_Skill_Requisition extends Synapse.Model{
    
       public HR2_CM_Skill_Requisition() {
        setColumns("sr_id", "job_id", "reason",
                 "requested_by", "date_requested","req_status_id","isDeleted");
        this.initTable("tbl_hr2_skill_requisition");
    }
}
