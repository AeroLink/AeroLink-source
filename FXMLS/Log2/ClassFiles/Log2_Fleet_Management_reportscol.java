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
public class Log2_Fleet_Management_reportscol {

    private String delivery_no;
    private String vehicle_model;
    private String date_delivered;

    public Log2_Fleet_Management_reportscol(String delivery_no, String vehicle_model, String date_delivered) {
        this.delivery_no = delivery_no;
        this.vehicle_model = vehicle_model;
        this.date_delivered = date_delivered;
    }

    /**
     * @return the delivery_no
     */
    public String getDelivery_no() {
        return delivery_no;
    }

    /**
     * @param delivery_no the delivery_no to set
     */
    public void setDelivery_no(String delivery_no) {
        this.delivery_no = delivery_no;
    }

    /**
     * @return the vehicle_model
     */
    public String getVehicle_model() {
        return vehicle_model;
    }

    /**
     * @param vehicle_model the vehicle_model to set
     */
    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    /**
     * @return the date_delivered
     */
    public String getDate_delivered() {
        return date_delivered;
    }

    /**
     * @param date_delivered the date_delivered to set
     */
    public void setDate_delivered(String date_delivered) {
        this.date_delivered = date_delivered;
    }

}
