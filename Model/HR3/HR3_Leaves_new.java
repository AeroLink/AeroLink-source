/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR3;

/**
 *
 * @author my
 */
public class HR3_Leaves_new extends Synapse.Model{
     
     public HR3_Leaves_new()
         {
             setColumns("id","employee_code","leave_name","range_leave","date","date_start","date_end","reason","attachment");
             this.initTable("tbl_hr3_leave_request_new");
             
             
         }
    
}
