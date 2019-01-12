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
public class ShiftRequestModel extends Synapse.Model{
     
     public ShiftRequestModel()
         {
             setColumns("ID","R_ID","Date","Employee","Monday","Tuesday","Wednesday","Thursday","Friday");
             this.initTable("tbl_hr3_Shiftings");
             
             
         }
}
