/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR3;

/**
 *
 * @author BlackMoon
 */
public class HR3_Shifting extends Synapse.Model{
     
     public HR3_Shifting()
         {
             setColumns("id","employee_code","date","schedule","reason","attachment");
             this.initTable("tbl_hr3_shifting_request");
             
             
         }

}
