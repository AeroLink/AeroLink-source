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
public class Log2_Vehicle_ReservationFormClass {
    
     public SimpleStringProperty Vehiclemodel;
    public SimpleStringProperty Datereserved;
    public SimpleStringProperty Time;

    public Log2_Vehicle_ReservationFormClass(String vmodel, String dr, String time) {
        this.Vehiclemodel = new SimpleStringProperty(vmodel);
        this.Datereserved = new SimpleStringProperty(dr);
        this. Time = new SimpleStringProperty(time);

    }
    
}
