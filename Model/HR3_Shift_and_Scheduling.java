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
public class HR3_Shift_and_Scheduling extends Synapse.Model{
    
    public HR3_Shift_and_Scheduling()
         {
             setColumns("tbl_ID","tbl_name","tbl_position","tbl_status","tblschedule");
             setTable("tablesns");
             
             
         }

    
}
