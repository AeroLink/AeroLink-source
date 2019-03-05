package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetBuildingClassfiles {
        public SimpleStringProperty BuildingID;
        public SimpleStringProperty AssetID;
        public SimpleStringProperty LandID;
        public SimpleStringProperty bAssetTitle;
        public SimpleStringProperty bAssetDescription;
        public SimpleStringProperty BuildingContact;
        public SimpleStringProperty BuildingYearBuilt;
        public SimpleStringProperty bAssetCoreLocation;
        public SimpleStringProperty AssetRegisteredDate;
        public SimpleStringProperty RoomNumberCapacity;
        public SimpleStringProperty TotalOfRoomNumber;
        public SimpleStringProperty Cost;
        public SimpleStringProperty Floors;
        public SimpleStringProperty Area;
        
        public Log1_AssetBuildingClassfiles(
                String BuildingID,
                String AssetID,
                String LandID,
                String bAssetTitle,
                String bAssetDescription,
                String BuildingContact,
                String BuildingYearBuilt,
                String bAssetCoreLocation,
                String AssetRegisteredDate,
                String RoomNumberCapacity,
                String TotalOfRoomNumber,
                String Cost,
                String Floors,
                String Area
            )
        {
            this.BuildingID = new SimpleStringProperty(BuildingID);
            this.AssetID = new SimpleStringProperty(AssetID);
            this.LandID = new SimpleStringProperty(LandID);
            this.bAssetTitle = new SimpleStringProperty(bAssetTitle);
            this.bAssetDescription = new SimpleStringProperty(bAssetDescription);
            this.BuildingContact = new SimpleStringProperty(BuildingContact);
            this.BuildingYearBuilt = new SimpleStringProperty(BuildingYearBuilt);
            this.bAssetCoreLocation = new SimpleStringProperty(bAssetCoreLocation);
            this.AssetRegisteredDate = new SimpleStringProperty(AssetRegisteredDate);
            this.RoomNumberCapacity = new SimpleStringProperty(RoomNumberCapacity);
            this.TotalOfRoomNumber = new SimpleStringProperty(TotalOfRoomNumber);
            this.Cost = new SimpleStringProperty(Cost);
            this.Floors = new SimpleStringProperty(Floors);
            this.Area = new SimpleStringProperty(Area);
        }

    public String getBuildingID() {
        return BuildingID.get();
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getLandID() {
        return LandID.get();
    }

    public String getbAssetTitle() {
        return bAssetTitle.get();
    }

    public String getbAssetDescription() {
        return bAssetDescription.get();
    }

    public String getBuildingContact() {
        return BuildingContact.get();
    }

    public String getBuildingYearBuilt() {
        return BuildingYearBuilt.get();
    }

    public String getbAssetCoreLocation() {
        return bAssetCoreLocation.get();
    }

    public String getAssetRegisteredDate() {
        return AssetRegisteredDate.get();
    }

    public String getRoomNumberCapacity() {
        return RoomNumberCapacity.get();
    }

    public String getTotalOfRoomNumber() {
        return TotalOfRoomNumber.get();
    }

    public String getCost() {
        return Cost.get();
    }

    public String getFloors() {
        return Floors.get();
    }

    public String getArea() {
        return Area.get();
    }
    
        
}
