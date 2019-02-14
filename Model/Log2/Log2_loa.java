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
public class Log2_loa extends Synapse.Model{
    
    public Log2_loa(String path) {
        
         switch (path) {
            case "loa":
                this.initTable("tbl_log2_list_of_auditor");
                break;
            default:
                break;
        }
    }
    
}
