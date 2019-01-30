/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Log2;

import static Synapse.Model.setColumns;

/**
 *
 * @author Randelle
 */
public class Log2_reservationform extends Synapse.Model {
    
     public Log2_reservationform(String path){
         switch (path) {
            case "reservation":
                this.initTable("tbl_log2_reservationformtbl");
                break;
            default:
                break;
        }
    }
    
}
