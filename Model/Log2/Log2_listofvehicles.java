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
public class Log2_listofvehicles extends Synapse.Model{
    public Log2_listofvehicles(String path){
     switch (path) {
            case "listofvehicles":
                this.initTable("tbl_log1_AssetVehicles");
                break;
            default:
                break;
        }
}
    
}
