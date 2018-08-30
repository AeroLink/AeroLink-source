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
 * @author Randelle
 */
public class Log2_Fleet_Management extends Synapse.Model {
    
      public Log2_Fleet_Management()
            {
                   setColumns("transac_no","item_type","personnel_in_charge","date_recieved");
                    setTable("tbl_log2_incoming_delivery");
                    
            }   
}
