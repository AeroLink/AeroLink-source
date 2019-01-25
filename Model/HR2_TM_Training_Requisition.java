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
public class HR2_TM_Training_Requisition extends Synapse.Model {

    public HR2_TM_Training_Requisition() {
        setColumns("tr_id", "dept_id", "job_id", "training_title",
                "no_of_participants", "total_hours", "from_day", "to_day", "reason",
                "request_status_id", "requested_by", "date_requested", "isDeleted");
        this.initTable("tbl_hr2_training_requisition");
    }

}
