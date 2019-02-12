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
public class Log2_vm extends Synapse.Model {

 public Log2_vm(String path) {
        switch (path) {
            case "vm":
                this.initTable("aerolink.tbl_log2_vm");
                break;
            default:
                break;
        }
    }    
}
