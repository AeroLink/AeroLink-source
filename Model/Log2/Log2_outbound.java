/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Log2;

/**
 *
 * @author Randelle
 */
public class Log2_outbound extends Synapse.Model {
    
    public Log2_outbound(String path){
         switch (path) {
            case "outbound":
                setColumns("id","vehiclemodel","plateno");
                this.initTable("tbl_log2_outbound");
                break;
            default:
                break;
        }
    }
    
}
