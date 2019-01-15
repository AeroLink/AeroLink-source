/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Randelle
 */
public class Log2_Vehicle_ReservationClass {

    public SimpleStringProperty vehiclecode;
    public SimpleStringProperty vehiclemodel;
    public SimpleStringProperty lastmaintenance;
    private Button details;

    public SimpleStringProperty name;
    public SimpleStringProperty monitoringlocation;
    public SimpleStringProperty daterented;
    public SimpleStringProperty time;
    public SimpleStringProperty timeend;
    public SimpleStringProperty status;

    public Log2_Vehicle_ReservationClass(String vehiclecode, String vehiclemodel, String lastmaintenance) {
        // Status
        this.vehiclecode = new SimpleStringProperty(vehiclecode);
        this.vehiclemodel = new SimpleStringProperty(vehiclemodel);
        this.lastmaintenance = new SimpleStringProperty(lastmaintenance);
        this.details = new Button("Details");

    }

    //monitoring
    public Log2_Vehicle_ReservationClass(String name, String monitoringlocation, String daterented, String time, String timeend, String status) {

        this.name = new SimpleStringProperty(name);
        this.monitoringlocation = new SimpleStringProperty(monitoringlocation);
        this.daterented = new SimpleStringProperty(daterented);
        this.time = new SimpleStringProperty(time);
        this.timeend = new SimpleStringProperty(timeend);
        this.status = new SimpleStringProperty(status);

    }

    public String getVehiclecode() {
        return vehiclecode.get();
    }

    public String getVehiclemodel() {
        return vehiclemodel.get();
    }

    public String getLastmaintenance() {
        return lastmaintenance.get();
    }

    public void setButton(Button button) {
        this.details = button;
    }

    public Button getButton() {
        return details;
    }

    //monitoring
    public String getName() {
        return name.get();
    }

    public String getMonitoringlocation() {
        return monitoringlocation.get();
    }

    public String getDaterented() {
        return daterented.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getTimeend() {
        return timeend.get();
    }

    public String getStatus() {
        return status.get();
    }

}
