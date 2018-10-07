/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;
import java.util.List;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_CM_Pivot extends Synapse.Model {
    
       public HR2_CM_Pivot()
            {
                   setColumns("job_id","skill_id");
                    this.initTable("tbl_hr2_competency_pivot");

            }


}
