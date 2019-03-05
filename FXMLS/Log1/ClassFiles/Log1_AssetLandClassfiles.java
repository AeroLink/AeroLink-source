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
        public SimpleStringProperty AssetID;
        public SimpleStringProperty LAssetTitle;
        public SimpleStringProperty LAssetDescription;
        public SimpleStringProperty LAssetCoreLocation;
        public SimpleStringProperty LandYearBought;
        public SimpleStringProperty LandArea;
        public SimpleStringProperty LandPricePerSqMeters;
        public SimpleStringProperty LAssetPurchasedPrice;
        public SimpleStringProperty LAssetPurchasedDate;
        public SimpleStringProperty LAssetStatus;
        public SimpleStringProperty LAssetPriceCurrency;
        public SimpleStringProperty CurrentPrice;
        public SimpleStringProperty PriceUpdatedAt;
        public SimpleStringProperty AssetRegisteredDate;
        
        public Log1_AssetLandClassfiles(
                String LandID,
                String AssetID,
                String LAssetTitle,
                String LAssetDescription,
                String LAssetCoreLocation,
                String LandYearBought,
                String LandArea,
                String LandPricePerSqMeters,
                String LAssetPurchasedPrice,
                String LAssetPurchasedDate,
                String LAssetStatus,
                String LAssetPriceCurrency,
                String CurrentPrice,
                String PriceUpdatedAt,
                String AssetRegisteredDate
                
            )
        {
            this.LandID = new SimpleStringProperty(LandID);
            this.AssetID = new SimpleStringProperty(AssetID);
            this.LAssetTitle = new SimpleStringProperty(LAssetTitle);
            this.LAssetDescription = new SimpleStringProperty(LAssetDescription);
            this.LAssetCoreLocation = new SimpleStringProperty(LAssetCoreLocation);
            this.LandYearBought = new SimpleStringProperty(LandYearBought);
            this.LandArea = new SimpleStringProperty(LandArea);
            this.LandPricePerSqMeters = new SimpleStringProperty(LandPricePerSqMeters);
            this.LAssetPurchasedPrice = new SimpleStringProperty(LAssetPurchasedPrice);
            this.LAssetPurchasedDate = new SimpleStringProperty(LAssetPurchasedDate);
            this.LAssetStatus = new SimpleStringProperty(LAssetStatus);
            this.LAssetPriceCurrency = new SimpleStringProperty(LAssetPriceCurrency);
            this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
            this.PriceUpdatedAt = new SimpleStringProperty(PriceUpdatedAt);
            this.AssetRegisteredDate = new SimpleStringProperty(AssetRegisteredDate);
        }

    public String getLandID() {
        return LandID.get();
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getLAssetTitle() {
        return LAssetTitle.get();
    }

    public String getLAssetDescription() {
        return LAssetDescription.get();
    }

    public String getLAssetCoreLocation() {
        return LAssetCoreLocation.get();
    }

    public String getLandYearBought() {
        return LandYearBought.get();
    }

    public String getLandArea() {
        return LandArea.get();
    }

    public String getLandPricePerSqMeters() {
        return LandPricePerSqMeters.get();
    }

    public String getLAssetPurchasedPrice() {
        return LAssetPurchasedPrice.get();
    }

    public String getLAssetPurchasedDate() {
        return LAssetPurchasedDate.get();
    }

    public String getLAssetStatus() {
        return LAssetStatus.get();
    }

    public String getLAssetPriceCurrency() {
        return LAssetPriceCurrency.get();
    }

    public String getCurrentPrice() {
        return CurrentPrice.get();
    }

    public String getPriceUpdatedAt() {
        return PriceUpdatedAt.get();
    }

    public String getAssetRegisteredDate() {
        return AssetRegisteredDate.get();
    }
        

}
