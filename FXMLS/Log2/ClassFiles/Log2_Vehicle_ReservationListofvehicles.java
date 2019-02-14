/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Vehicle_ReservationListofvehicles {
    
    public SimpleStringProperty ListVehiclemodel;
    public SimpleStringProperty ListVehicletype;
    public SimpleStringProperty ListPlateno;

    public Log2_Vehicle_ReservationListofvehicles(String vmodel, String vtype, String plateno) {
        this.ListVehiclemodel = new SimpleStringProperty(vmodel);
        this.ListVehicletype = new SimpleStringProperty(vtype);
        this.ListPlateno = new SimpleStringProperty(plateno);

    }
    
}
