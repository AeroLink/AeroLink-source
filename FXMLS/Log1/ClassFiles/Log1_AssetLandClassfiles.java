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
public class Log1_AssetLandClassfiles {
        public SimpleStringProperty LandID;
        public SimpleStringProperty LandName;
        public SimpleStringProperty LandArea;
        public SimpleStringProperty LandAddress;
        public SimpleStringProperty LandPricePerSqMeters;
        public SimpleStringProperty LandPurchasedDate;
        public SimpleStringProperty AssetCategory;
        public SimpleStringProperty LandStatus;
        
        public Log1_AssetLandClassfiles(
                String LandID,
                String AssetCategory,
                String LandName,
                String LaneArea,
                String LandAddress,
                String LandPricePerSqMeters,
                String LandPurchasedDate,
                String LandStatus
                
            )
        {
            this.LandID = new SimpleStringProperty(LandID);
            this.LandName = new SimpleStringProperty(LandName);
            this.LandArea = new SimpleStringProperty(LaneArea);
            this.LandAddress = new SimpleStringProperty(LandAddress);
            this.LandPricePerSqMeters = new SimpleStringProperty(LandPricePerSqMeters);
            this.LandPurchasedDate = new SimpleStringProperty(LandPurchasedDate);
            this.AssetCategory = new SimpleStringProperty(AssetCategory);
            this.LandStatus = new SimpleStringProperty(LandStatus);
        }
        
    public String getAssetCategory(){
        return AssetCategory.get();
    }    

    public String getLandID() {
        return LandID.get();
    }

    public String getLandName() {
        return LandName.get();
    }

    public String getLandArea() {
        return LandArea.get();
    }

    public String getLandAddress() {
        return LandAddress.get();
    }

    public String getLandPricePerSqMeters() {
        return LandPricePerSqMeters.get();
    }

    public String getLandPurchasedDate() {
        return LandPurchasedDate.get();
    }
}
