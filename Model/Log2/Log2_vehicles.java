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
public class Log2_vehicles extends Synapse.Model {
    
     public Log2_vehicles(String path){
         switch (path) {
            case "vehicles":
                this.initTable("tbl_log1_AssetVehicles");
                break;
            default:
                break;
        }
    }
}
