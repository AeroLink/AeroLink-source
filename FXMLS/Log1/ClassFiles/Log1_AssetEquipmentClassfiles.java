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
public class Log1_AssetEquipmentClassfiles {
    public SimpleStringProperty EquipmentID;
    public SimpleStringProperty BuildingName;
    public SimpleStringProperty EquipmentName;
    public SimpleStringProperty EquipmentType;
    public SimpleStringProperty EquipmentLocation;
    public SimpleStringProperty EquipmentModelNumber;
    public SimpleStringProperty EquipmentSerialNumber;
    public SimpleStringProperty EquipmentWarrantyDate;
    public SimpleStringProperty EquipmentPrice;
    
    public Log1_AssetEquipmentClassfiles(
            String ID,
            String bName,
            String Name,
            String Type,
            String Location,
            String ModelNumber,
            String SerialNumber,
            String Date,
            String Price
    ){
        this.EquipmentID = new SimpleStringProperty(ID);
        this.BuildingName = new SimpleStringProperty(bName);
        this.EquipmentName = new SimpleStringProperty(Name);
        this.EquipmentType = new SimpleStringProperty(Type);
        this.EquipmentLocation = new SimpleStringProperty(Location);
        this.EquipmentModelNumber = new SimpleStringProperty(ModelNumber);
        this.EquipmentSerialNumber = new SimpleStringProperty(SerialNumber);
        this.EquipmentWarrantyDate = new SimpleStringProperty(Date);
        this.EquipmentPrice = new SimpleStringProperty(Price);
    }

    public String getBuildingName() {
        return BuildingName.get();
    }

    public String getEquipmentID() {
        return EquipmentID.get();
    }

    public String getEquipmentName() {
        return EquipmentName.get();
    }

    public String getEquipmentType() {
        return EquipmentType.get();
    }

    public String getEquipmentLocation() {
        return EquipmentLocation.get();
    }

    public String getEquipmentModelNumber() {
        return EquipmentModelNumber.get();
    }

    public String getEquipmentSerialNumber() {
        return EquipmentSerialNumber.get();
    }

    public String getEquipmentWarrantyDate() {
        return EquipmentWarrantyDate.get();
    }

    public String getEquipmentPrice() {
        return EquipmentPrice.get();
    }
}
