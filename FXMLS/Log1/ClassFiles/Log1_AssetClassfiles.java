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
public class Log1_AssetClassfiles {
    public SimpleStringProperty AssetID;
    public SimpleStringProperty CategoryID;
    public SimpleStringProperty AssetTitle;
    public SimpleStringProperty AssetDescription;
    public SimpleStringProperty AssetCategory;
    public SimpleStringProperty AssetType;
    public SimpleStringProperty AssetBrand;
    public SimpleStringProperty AssetSerialNumber;
    public SimpleStringProperty AssetPurchasedPrice;
    public SimpleStringProperty AssetPurchasedDate;
    public SimpleStringProperty AssetLifeSpan;
    public SimpleStringProperty AssetWarranty;
    public SimpleStringProperty AssetStatus;
    public SimpleStringProperty AssetCoreLocation;
    public SimpleStringProperty CurrentPrice;
    public SimpleStringProperty PriceUpdatedAt;
    public SimpleStringProperty AssetPriceCurrency;
    public SimpleStringProperty AssetRegisteredDate;
    public SimpleStringProperty AssetSalvageValue;
    
    public Log1_AssetClassfiles(
            String AssetID,
            String CategoryID,
            String AssetTitle,
            String AssetDescription,
            String AssetCategory,
            String AssetType,
            String AssetBrand,
            String AssetSerialNumber,
            String AssetPurchasedPrice,
            String AssetPurchasedDate,
            String AssetLifeSpan,
            String AssetWarranty,
            String AssetStatus,
            String AssetCoreLocation,
            String CurrentPrice,
            String PriceUpdatedAt,
            String AssetPriceCurrency,
            String AssetRegisteredDate,
            String AssetSalvageValue
    ){
        this.AssetID = new SimpleStringProperty(AssetID);
        this.CategoryID = new SimpleStringProperty(CategoryID);
        this.AssetTitle = new SimpleStringProperty(AssetTitle);
        this.AssetDescription = new SimpleStringProperty(AssetDescription);
        this.AssetCategory = new SimpleStringProperty(AssetCategory);
        this.AssetType = new SimpleStringProperty(AssetType);
        this.AssetBrand = new SimpleStringProperty(AssetBrand);
        this.AssetSerialNumber = new SimpleStringProperty(AssetSerialNumber);
        this.AssetPurchasedPrice = new SimpleStringProperty(AssetPurchasedPrice);
        this.AssetPurchasedDate = new SimpleStringProperty(AssetPurchasedDate);
        this.AssetLifeSpan = new SimpleStringProperty(AssetLifeSpan);
        this.AssetWarranty = new SimpleStringProperty(AssetWarranty);
        this.AssetStatus = new SimpleStringProperty(AssetStatus);
        this.AssetCoreLocation = new SimpleStringProperty(AssetCoreLocation);
        this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
        this.PriceUpdatedAt = new SimpleStringProperty(PriceUpdatedAt);
        this.AssetPriceCurrency = new SimpleStringProperty(AssetPriceCurrency);
        this.AssetRegisteredDate = new SimpleStringProperty(AssetRegisteredDate);
        this.AssetSalvageValue = new SimpleStringProperty(AssetSalvageValue);
    }

    public String getAssetID() {
        return AssetID.get();
    }
    
    public String getCategoryID() {
        return CategoryID.get();
    }
    
    public String getAssetSalvageValue() {
        return AssetSalvageValue.get();
    }
    
    public String getAssetRegisteredDate() {
        return AssetRegisteredDate.get();
    }

    public String getAssetTitle() {
        return AssetTitle.get();
    }

    public String getAssetDescription() {
        return AssetDescription.get();
    }

    public String getAssetCategory() {
        return AssetCategory.get();
    }

    public String getAssetType() {
        return AssetType.get();
    }

    public String getAssetBrand() {
        return AssetBrand.get();
    }

    public String getAssetSerialNumber() {
        return AssetSerialNumber.get();
    }

    public String getAssetPurchasedPrice() {
        return AssetPurchasedPrice.get();
    }

    public String getAssetPurchasedDate() {
        return AssetPurchasedDate.get();
    }

    public String getAssetLifeSpan() {
        return AssetLifeSpan.get();
    }

    public String getAssetWarranty() {
        return AssetWarranty.get();
    }

    public String getAssetStatus() {
        return AssetStatus.get();
    }

    public String getAssetCoreLocation() {
        return AssetCoreLocation.get();
    }

    public String getCurrentPrice() {
        return CurrentPrice.get();
    }

    public String getPriceUpdatedAt() {
        return PriceUpdatedAt.get();
    }

    public String getAssetPriceCurrency() {
        return AssetPriceCurrency.get();
    }
}
