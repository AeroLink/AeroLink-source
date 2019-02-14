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
public class Log2_t extends Synapse.Model{
    
    public Log2_t(String path) {
        switch (path) {
            case "tracking":
                this.initTable("aerolink.tbl_log2_tracking");
                break;
            default:
                break;
        }
    }
}
