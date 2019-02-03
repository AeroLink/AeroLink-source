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

    public SimpleStringProperty vehicle_id, vehicle_type, vehicle_model, vehicleStatus;

    public HR2_Temp_VehicleClass(String vehicle_id, String vehicle_type, String vehicle_model, String vehicle_status) {
        this.vehicle_id = new SimpleStringProperty(vehicle_id);
        this.vehicle_type = new SimpleStringProperty(vehicle_type);
        this.vehicle_model = new SimpleStringProperty(vehicle_model);
        this.vehicleStatus = new SimpleStringProperty(vehicle_status);
    }
}
