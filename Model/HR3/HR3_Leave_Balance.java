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
public class HR3_Leave_Balance extends Synapse.Model{
     
     public HR3_Leave_Balance()
         {
             setColumns("id","employee_code","leave_id","leave_name","leave_taken","leave_balance");
             this.initTable("tbl_hr3_leave_balance");
             
             
         }
    
    
}
