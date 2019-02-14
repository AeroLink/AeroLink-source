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
public class Log2_Fleet_ManagementRequest {

    private String vehicleid;
    private String department;
    private String item_name;
    private String size;
    private String quantity;
    private String destination;
    private String consignee;
    private String departure;

    public Log2_Fleet_ManagementRequest(String vehicleid, String department, String item_name, String size, String quantity, String destination, String consignee, String departure) {
        this.vehicleid = vehicleid;
        this.department = department;
        this.item_name = item_name;
        this.size = size;
        this.quantity = quantity;
        this.destination = destination;
        this.consignee = consignee;
        this.departure = departure;
    }

    /**
     * @return the vehicleid
     */
    public String getVehicleid() {
        return vehicleid;
    }

    /**
     * @param vehicleid the vehicleid to set
     */
    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the item_name
     */
    public String getItem_name() {
        return item_name;
    }

    /**
     * @param item_name the item_name to set
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the consignee
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * @param consignee the consignee to set
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    

}
