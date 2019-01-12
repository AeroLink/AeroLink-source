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
public class HR3_CreatingSchedule_Model extends Synapse.Model{
    public HR3_CreatingSchedule_Model()
         {
             setColumns("ID","Name","Position","Monday","Tuesday","Wednesday","Thursday","Friday");
             this.initTable("tbl_hr3_CreateSchedule");
             
             
         }

}
