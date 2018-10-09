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
                   setColumns("vehicle_id");
                    this.initTable("tbl_log2_vehicle_status");
            }
}
