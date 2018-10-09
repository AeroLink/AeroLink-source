/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Temp_VehicleClass {
    
     public SimpleStringProperty vehicle_id;
     
     public HR2_Temp_VehicleClass(String vehicle_id)
     {
         this.vehicle_id = new SimpleStringProperty(vehicle_id);
     }
}
