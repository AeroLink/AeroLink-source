package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetFacilityClassfiles {
    public SimpleStringProperty FacilityID;
    public SimpleStringProperty BuildingName;
    public SimpleStringProperty FacilityName;
    public SimpleStringProperty FacilityType;
    public SimpleStringProperty FacilityRoomNumber;
    public SimpleStringProperty FacilityCapacity;
    public SimpleStringProperty FacilityStatus;
    public SimpleStringProperty AssetCategory;
    
    public Log1_AssetFacilityClassfiles(
            String facilityID,
            String buildingName,
            String facilityName,
            String facilityType,
            String facilityRoomNumber,
            String facilityCapacity,
            String facilityStatus,
            String AssetCategory
    ){
        this.FacilityID = new SimpleStringProperty(facilityID);
        this.BuildingName = new SimpleStringProperty(buildingName);
        this.FacilityName = new SimpleStringProperty(facilityName);
        this.FacilityType = new SimpleStringProperty(facilityType);
        this.FacilityRoomNumber = new SimpleStringProperty(facilityRoomNumber);
        this.FacilityCapacity = new SimpleStringProperty(facilityCapacity);
        this.FacilityStatus = new SimpleStringProperty(facilityStatus);
        this.AssetCategory = new SimpleStringProperty(AssetCategory);
    }
    
    public String getAssetCategory(){
        return AssetCategory.get();
    }

    public String getFacilityID() {
        return FacilityID.get();
    }

    public String getBuildingName() {
        return BuildingName.get();
    }

    public String getFacilityName() {
        return FacilityName.get();
    }

    public String getFacilityType() {
        return FacilityType.get();
    }

    public String getFacilityRoomNumber() {
        return FacilityRoomNumber.get();
    }

    public String getFacilityCapacity() {
        return FacilityCapacity.get();
    }

    public String getFacilityStatus() {
        return FacilityStatus.get();
    }
}
