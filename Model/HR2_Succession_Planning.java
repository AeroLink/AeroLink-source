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
 * @author Eden Ramoneda
 */
public class HR2_Succession_Planning extends Synapse.Model{
        
         public HR2_Succession_Planning()
         {
             setColumns("emp_id","date_of_retirement");
             setTable("tb_succession_planning");
             
             setColumns("successor_id","emp_id");
             setTable("tb_successors");
             
         }
}
