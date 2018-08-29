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
public class HR2_Competency extends Synapse.Model {
    
       public HR2_Competency()
            {
                   setColumns("job_id","skill_id");
                    setTable("tbl_hr2_competency");

            }


}
