/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Temp_Vehicles extends Synapse.Model{
    
         public HR2_Temp_Vehicles()
            {
                   setColumns("VehicleID","VehicleModel");
                    this.initTable("tbl_log1_AssetVehicles");
            }
}
