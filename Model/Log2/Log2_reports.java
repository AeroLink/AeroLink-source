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
public class Log2_reports extends Synapse.Model{
    
    public Log2_reports(String path){
         switch (path) {
            case "reports":
                this.initTable("tbl_log2_fleet_reports");
                break;
            default:
                break;
        }
    }
}
