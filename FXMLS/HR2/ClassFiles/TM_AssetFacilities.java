/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class TM_AssetFacilities {

    public SimpleStringProperty facilityID, facilityName, facilityRoomNumber, facilityCapacity, BuildingName, facilityStatus;

    public TM_AssetFacilities(String fID, String fName, String frn, String fc, String BName, String fStatus) {
        this.facilityID = new SimpleStringProperty(fID);
        this.facilityName = new SimpleStringProperty(fName);
        this.facilityRoomNumber = new SimpleStringProperty(frn);
        this.facilityCapacity = new SimpleStringProperty(fc);
        this.BuildingName = new SimpleStringProperty(BName);
        this.facilityStatus = new SimpleStringProperty(fStatus);

    }

}
