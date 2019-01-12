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
public class Shift_and_Scheduling extends Synapse.Model{
    
    public Shift_and_Scheduling()
            
         {
             setColumns("ID","R_ID","Date","Name","Monday","Tuesday","Wednesday","Thursday","Friday","Status");
             this.initTable("tbl_hr3_ShiftingStatus");
            
         }

    
}
