
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author my
 */
public class leaveaccountmodel extends Synapse.Model{
   
    public leaveaccountmodel()
         {
             setColumns("ID","L_ID","Name", "Gender", "Age", "DateBirth", "Date", "Address", "V_L","S_L","M_L","P_L","E_L");
             this.initTable("tbl_hr3_leave_account");
             
             
         }
}
