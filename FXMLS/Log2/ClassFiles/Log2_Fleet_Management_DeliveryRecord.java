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
public class Log2_Fleet_Management_DeliveryRecord {
    
     public SimpleStringProperty Vehiclemodel;
    public SimpleStringProperty Plateno;
     public SimpleStringProperty Datedelivered;  

    public Log2_Fleet_Management_DeliveryRecord(String vehiclemodel, String plateno, String datedelivered) {
        this.Vehiclemodel = new SimpleStringProperty(vehiclemodel);
        this.Plateno = new SimpleStringProperty(plateno);
        this.Datedelivered = new SimpleStringProperty(datedelivered);
    
    }
    
}
