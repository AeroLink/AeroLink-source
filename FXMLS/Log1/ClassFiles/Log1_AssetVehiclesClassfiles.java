/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Crenz
 */
public class Log1_AssetVehiclesClassfiles {
    public SimpleStringProperty Vid;
    public SimpleStringProperty Vtype;
    public SimpleStringProperty Vdescription;
    public SimpleStringProperty VPrice;
    public SimpleStringProperty ChassisNumber;
    public SimpleStringProperty dateBought;
    public SimpleStringProperty Vlocation;
    public SimpleStringProperty VManufacturer;
    public SimpleStringProperty VWarranty;
    public SimpleStringProperty VStatus;
    
    public Log1_AssetVehiclesClassfiles(
            String id,
            String type,
            String description,
            String price,
            String chasisNumber,
            String dateBought,
            String location,
            String manufacturer,
            String warranty,
            String status
    ){
        this.Vid = new SimpleStringProperty(id);
        this.Vtype = new SimpleStringProperty(type);
        this.Vdescription = new SimpleStringProperty(description);
        this.VPrice = new SimpleStringProperty(price);
        this.ChassisNumber = new SimpleStringProperty(chasisNumber);
        this.dateBought = new SimpleStringProperty(dateBought);
        this.Vlocation = new SimpleStringProperty(location);
        this.VManufacturer = new SimpleStringProperty(manufacturer);
        this.VWarranty = new SimpleStringProperty(warranty);
        this.VStatus = new SimpleStringProperty(status);
    }

    public String getVid() {
        return Vid.get();
    }

    public String getVtype() {
        return Vtype.get();
    }

    public String getVdescription() {
        return Vdescription.get();
    }

    public String getVPrice() {
        return VPrice.get();
    }

    public String getChassisNumber() {
        return ChassisNumber.get();
    }

    public String getDateBought() {
        return dateBought.get();
    }

    public String getVlocation() {
        return Vlocation.get();
    }

    public String getVManufacturer() {
        return VManufacturer.get();
    }

    public String getVWarranty() {
        return VWarranty.get();
    }

    public String getVStatus() {
        return VStatus.get();
    }
}
