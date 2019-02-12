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
public class Log2_Fleet_Management_outboundtable {


    private String Vehicle_model;
    private String Plate_no;

    public Log2_Fleet_Management_outboundtable(String Vehicle_model, String Plate_no) {
        this.Vehicle_model = Vehicle_model;
        this.Plate_no = Plate_no;
    }

    /**
     * @return the Vehicle_model
     */
    public String getVehicle_model() {
        return Vehicle_model;
    }

    /**
     * @param Vehicle_model the Vehicle_model to set
     */
    public void setVehicle_model(String Vehicle_model) {
        this.Vehicle_model = Vehicle_model;
    }

    /**
     * @return the Plate_no
     */
    public String getPlate_no() {
        return Plate_no;
    }

    /**
     * @param Plate_no the Plate_no to set
     */
    public void setPlate_no(String Plate_no) {
        this.Plate_no = Plate_no;
    }

}
