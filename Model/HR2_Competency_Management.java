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
 * @author Eden Ramoneda
 */
public class HR2_Competency_Management extends Synapse.Model{
        
         public HR2_Competency_Management()
            {
                   setColumns("emp_id","emp_name","age","date_of_birth","address","contact_number","email","birthplace","status","guardian","nationality","school_level");
                    setTable("tbl_competency_management");
                    
                      setColumns("emp_id","job_position");
                    setTable("tbl_job_positions");
                    
                      setColumns("emp_id","department");
                    setTable("tbl_department");
            }
}
