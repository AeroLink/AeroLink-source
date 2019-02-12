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
public class HR3_leave_record extends Synapse.Model{
     
     public HR3_leave_record()
         {
             setColumns("id","employee_code","leave_name","status");
             this.initTable("tbl_hr3_leave_record");
             
             
         }

    
}
