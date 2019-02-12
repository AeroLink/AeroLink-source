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
public class Log2_avehicles extends Synapse.Model{
    
     public Log2_avehicles(String path) {
            switch (path) {
            case "vehicles":  
        setColumns("VehicleModel","VehicleType","VehiclePlateNumber","VehicleFuelType","VehicleFuelCapacity",
                "VehicleColor","VehicleCapacity");
        this.initTable("aerolink.tbl_log1_AssetVehicles");
         break;
            default:
                break;
    }
     }
}
