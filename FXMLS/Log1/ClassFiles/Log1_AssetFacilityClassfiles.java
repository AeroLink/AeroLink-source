package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetFacilityClassfiles {
    public SimpleStringProperty FacilityID;
    public SimpleStringProperty BuildingID;
    public SimpleStringProperty AssetID;
    public SimpleStringProperty fAssetTitle;
    public SimpleStringProperty fAssetDescription;
    public SimpleStringProperty FacilityBuilding;
    public SimpleStringProperty FacilityAddress;
    public SimpleStringProperty FacilityType;
    public SimpleStringProperty FacilityRoomNumber;
    public SimpleStringProperty FacilityDimension;
    public SimpleStringProperty FacilityStatus;
    public SimpleStringProperty AssetRegisteredDate;
    public SimpleStringProperty AssetCoreLocation;
    public SimpleStringProperty Cost;
    public SimpleStringProperty Currency;
    
    public Log1_AssetFacilityClassfiles(
            String facilityID,
            String BuildingID,
            String AssetID,
            String fAssetTitle,
            String fAssetDescription,
            String FacilityBuilding,
            String FacilityAddress,
            String FacilityType,
            String FacilityRoomNumber,
            String FacilityDimension,
            String FacilityStatus,
            String AssetRegisteredDate,
            String AssetCoreLocation,
            String Cost,
            String Currency
    ){
        this.FacilityID = new SimpleStringProperty(facilityID);
        this.BuildingID = new SimpleStringProperty(BuildingID);
        this.AssetID = new SimpleStringProperty(AssetID);
        this.fAssetTitle = new SimpleStringProperty(fAssetTitle);
        this.fAssetDescription = new SimpleStringProperty(fAssetDescription);
        this.FacilityBuilding = new SimpleStringProperty(FacilityBuilding);
        this.FacilityAddress = new SimpleStringProperty(FacilityAddress);
        this.FacilityType = new SimpleStringProperty(FacilityType);
        this.FacilityRoomNumber = new SimpleStringProperty(FacilityRoomNumber);
        this.FacilityDimension = new SimpleStringProperty(FacilityDimension);
        this.FacilityStatus = new SimpleStringProperty(FacilityStatus);
        this.AssetRegisteredDate = new SimpleStringProperty(AssetRegisteredDate);
        this.AssetCoreLocation = new SimpleStringProperty(AssetCoreLocation);
        this.Cost = new SimpleStringProperty(Cost);
        this.Currency = new SimpleStringProperty(Currency);
    }

    public String getFacilityID() {
        return FacilityID.get();
    }

    public String getBuildingID() {
        return BuildingID.get();
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getfAssetTitle() {
        return fAssetTitle.get();
    }

    public String getfAssetDescription() {
        return fAssetDescription.get();
    }

    public String getFacilityBuilding() {
        return FacilityBuilding.get();
    }

    public String getFacilityAddress() {
        return FacilityAddress.get();
    }

    public String getFacilityType() {
        return FacilityType.get();
    }

    public String getFacilityRoomNumber() {
        return FacilityRoomNumber.get();
    }

    public String getFacilityDimension() {
        return FacilityDimension.get();
    }

    public String getFacilityStatus() {
        return FacilityStatus.get();
    }

    public String getAssetRegisteredDate() {
        return AssetRegisteredDate.get();
    }

    public String getAssetCoreLocation() {
        return AssetCoreLocation.get();
    }

    public String getCost() {
        return Cost.get();
    }

    public SimpleStringProperty getCurrency() {
        return Currency;
    }
    
    
}
