package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetBuildingClassfiles {
        public SimpleStringProperty BuildingID;
        public SimpleStringProperty BuildingName;
        public SimpleStringProperty BuildingDescription;
        public SimpleStringProperty LandName;
        public SimpleStringProperty LandAddress;
        public SimpleStringProperty BuildingContact;
        public SimpleStringProperty BuildingYearBuilt;
        public SimpleStringProperty AssetCategory;
        
        public Log1_AssetBuildingClassfiles(
                String BuildingID,
                String BuildingName,
                String BuildingDescription,
                String LandName,
                String LandAddress,
                String BuildingContact,
                String BuildingYearBuilt,
                String AssetCategory
            )
        {
            this.BuildingID = new SimpleStringProperty(BuildingID);
            this.BuildingName = new SimpleStringProperty(BuildingName);
            this.BuildingDescription = new SimpleStringProperty(BuildingDescription);
            this.LandName = new SimpleStringProperty(LandName);
            this.LandAddress = new SimpleStringProperty(LandAddress);
            this.BuildingContact = new SimpleStringProperty(BuildingContact);
            this.BuildingYearBuilt = new SimpleStringProperty(BuildingYearBuilt);
            this.AssetCategory = new SimpleStringProperty(AssetCategory);
        }
    
    public String getAssetCategory(){
        return AssetCategory.get();
    }

    public String getBuildingID() {
        return BuildingID.get();
    }

    public String getBuildingName() {
        return BuildingName.get();
    }

    public String getBuildingDescription() {
        return BuildingDescription.get();
    }

    public String getLandName() {
        return LandName.get();
    }

    public String getLandAddress() {
        return LandAddress.get();
    }

    public String getBuildingContact() {
        return BuildingContact.get();
    }

    public String getBuildingYearBuilt() {
        return BuildingYearBuilt.get();
    }
}
