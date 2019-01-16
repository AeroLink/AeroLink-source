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
public class Log1_ABforEquipmentClassfiles {
        public SimpleStringProperty BuildingID;
        public SimpleStringProperty BuildingName;
        public SimpleStringProperty BuildingAddress;
        
        public Log1_ABforEquipmentClassfiles(
                String BuildingID,
                String BuildingName,
                String BuildingAddress
            )
        {
            this.BuildingID = new SimpleStringProperty(BuildingID);
            this.BuildingName = new SimpleStringProperty(BuildingName);
            this.BuildingAddress = new SimpleStringProperty(BuildingAddress);
        }

    public String getBuildingID() {
        return BuildingID.get();
    }

    public String getBuildingName() {
        return BuildingName.get();
    }

    public String getBuildingAddress() {
        return BuildingAddress.get();
    }
}
