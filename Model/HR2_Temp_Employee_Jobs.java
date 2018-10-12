/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Temp_Employee_Jobs extends Synapse.Model{
    
     public HR2_Temp_Employee_Jobs()
    {
          setColumns("employee_code","job_id");
        this.initTable("aerolink.tbl_hr4_employee_jobs");
    }
}
