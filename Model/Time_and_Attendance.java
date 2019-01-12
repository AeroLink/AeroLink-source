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
public class Time_and_Attendance extends Synapse.Model{
    
     public Time_and_Attendance()
         {
             setColumns("ID","Name","Datein","Timein","Dateout","Timeout","ElapseTime","Overtime","Undertime");
             this.initTable("tbl_hr3_CountTime");
             
           
         }

  

    
}
