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
public class Log1_AssetBuildingClassfiles {
        public SimpleStringProperty BuildingID;
        public SimpleStringProperty BuildingName;
        public SimpleStringProperty BuildingAddress;
        public SimpleStringProperty BuildingContact;
        public SimpleStringProperty BuildingArea;
        public SimpleStringProperty YearBuilt;
        public SimpleStringProperty BuildingStatus;
        public SimpleStringProperty BuildingFacilityType;
        
        public Log1_AssetBuildingClassfiles(
                String BuildingID,
                String BuildingName,
                String BuildingAddress,
                String BuildingContact,
                String BuildingArea,
                String YearBuilt,
                String BuildingStatus,
                String BuildingFacilityType
            )
        {
            this.BuildingID = new SimpleStringProperty(BuildingID);
            this.BuildingName = new SimpleStringProperty(BuildingName);
            this.BuildingAddress = new SimpleStringProperty(BuildingAddress);
            this.BuildingContact = new SimpleStringProperty(BuildingContact);
            this.BuildingArea = new SimpleStringProperty(BuildingArea);
            this.YearBuilt = new SimpleStringProperty(YearBuilt);
            this.BuildingStatus = new SimpleStringProperty(BuildingStatus);
            this.BuildingFacilityType = new SimpleStringProperty(BuildingFacilityType);
        }

    public String getBuildingFacilityType() {
        return BuildingFacilityType.get();
    }

    public String getBuildingStatus() {
        return BuildingStatus.get();
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

    public String getBuildingContact() {
        return BuildingContact.get();
    }

    public String getBuildingArea() {
        return BuildingArea.get();
    }

    public String getYearBuilt() {
        return YearBuilt.get();
    }
}
