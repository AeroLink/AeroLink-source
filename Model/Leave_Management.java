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
 * @author my
 */
public class Leave_Management extends Synapse.Model{
    
    public Leave_Management()
         {
             setColumns("ID","Emp_Name","Position","Type_of_Leave","Range_of_Leave","Date_Start","Date_End","Status");
             this.initTable("tbl_hr3_leave");
             setTable("tbl_hr3_leave");
             
             
             
         }

  
    
}
