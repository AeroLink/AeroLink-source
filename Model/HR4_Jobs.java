/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;

/**
 * 
 * @author Eden RAMONEDA
 */
public class HR4_Jobs extends Synapse.Model{
            
         public HR4_Jobs()
            {
                setColumns("job_id","title","description", "classification", "designation", "department");
                this.initTable("tbl_hr4_jobs");
            }
}
