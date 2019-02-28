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
public class Log1_AssetVehiclesClassfiles {
    public SimpleStringProperty VehicleID;
    public SimpleStringProperty AssetID;
    public SimpleStringProperty vAssetTitle;
    public SimpleStringProperty vAssetDescription;
    public SimpleStringProperty VehicleType;
    public SimpleStringProperty VehicleBrand;
    public SimpleStringProperty VehicleModel;
    public SimpleStringProperty VehicleColor;
    public SimpleStringProperty VehicleCapacity;
    public SimpleStringProperty VehicleYearSpan;
    public SimpleStringProperty VehicleYearBought;
    public SimpleStringProperty VehicleWarrantyDate;
    public SimpleStringProperty VehiclePlateNumber;
    public SimpleStringProperty VehicleChassisNumber;
    public SimpleStringProperty ORCnumber;
    public SimpleStringProperty VehiclePurchasedPrice;
    public SimpleStringProperty AssetSalvageValue;
    public SimpleStringProperty VehicleFuelType;
    public SimpleStringProperty VehicleFuelCapacity;
    public SimpleStringProperty vAssetCoreLocation;
    public SimpleStringProperty AssetRegisteredDate;
    public SimpleStringProperty VehicleStatus;
    public SimpleStringProperty PriceUpdatedAt;
    public SimpleStringProperty CurrentPrice;
    public SimpleStringProperty PriceCurrency;
    
    public Log1_AssetVehiclesClassfiles(
            String VehicleID,
            String AssetID,
            String vAssetTitle,
            String vAssetDescription,
            String VehicleType,
            String VehicleBrand,
            String VehicleModel,
            String VehicleColor,
            String VehicleCapacity,
            String VehicleYearSpan,
            String VehicleYearBought,
            String VehicleWarrantyDate,
            String VehiclePlateNumber,
            String VehicleChassisNumber,
            String ORCnumber,
            String VehiclePurchasedPrice,
            String AssetSalvageValue,
            String VehicleFuelType,
            String VehicleFuelCapacity,
            String vAssetCoreLocation,
            String AssetRegisteredDate,
            String VehicleStatus,
            String PriceUpdatedAt,
            String CurrentPrice,
            String PriceCurrency
    ){
        this.VehicleID = new SimpleStringProperty(VehicleID);
        this.AssetID = new SimpleStringProperty(AssetID);
        this.vAssetTitle = new SimpleStringProperty(vAssetTitle);
        this.vAssetDescription = new SimpleStringProperty(vAssetDescription);
        this.VehicleType = new SimpleStringProperty(VehicleType);
        this.VehicleBrand = new SimpleStringProperty(VehicleBrand);
        this.VehicleModel = new SimpleStringProperty(VehicleModel);
        this.VehicleColor = new SimpleStringProperty(VehicleColor);
        this.VehicleCapacity = new SimpleStringProperty(VehicleCapacity);
        this.VehicleYearSpan = new SimpleStringProperty(VehicleYearSpan);
        this.VehicleYearBought = new SimpleStringProperty(VehicleYearBought);
        this.VehicleWarrantyDate = new SimpleStringProperty(VehicleWarrantyDate);
        this.VehiclePlateNumber = new SimpleStringProperty(VehiclePlateNumber);
        this.VehicleChassisNumber = new SimpleStringProperty(VehicleChassisNumber);
        this.ORCnumber = new SimpleStringProperty(ORCnumber);
        this.VehiclePurchasedPrice = new SimpleStringProperty(VehiclePurchasedPrice);
        this.AssetSalvageValue = new SimpleStringProperty(AssetSalvageValue);
        this.VehicleFuelType = new SimpleStringProperty(VehicleFuelType);
        this.VehicleFuelCapacity = new SimpleStringProperty(VehicleFuelCapacity);
        this.vAssetCoreLocation = new SimpleStringProperty(vAssetCoreLocation);
        this.AssetRegisteredDate = new SimpleStringProperty(AssetRegisteredDate);
        this.VehicleStatus = new SimpleStringProperty(VehicleStatus);
        this.PriceUpdatedAt = new SimpleStringProperty(PriceUpdatedAt);
        this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
        this.PriceCurrency = new SimpleStringProperty(PriceCurrency);
    }

    public String getVehicleID() {
        return VehicleID.get();
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getvAssetTitle() {
        return vAssetTitle.get();
    }

    public String getvAssetDescription() {
        return vAssetDescription.get();
    }

    public String getVehicleType() {
        return VehicleType.get();
    }

    public String getVehicleBrand() {
        return VehicleBrand.get();
    }

    public String getVehicleModel() {
        return VehicleModel.get();
    }

    public String getVehicleColor() {
        return VehicleColor.get();
    }

    public String getVehicleCapacity() {
        return VehicleCapacity.get();
    }

    public String getVehicleYearSpan() {
        return VehicleYearSpan.get();
    }

    public String getVehicleYearBought() {
        return VehicleYearBought.get();
    }

    public String getVehicleWarrantyDate() {
        return VehicleWarrantyDate.get();
    }

    public String getVehiclePlateNumber() {
        return VehiclePlateNumber.get();
    }

    public String getVehicleChassisNumber() {
        return VehicleChassisNumber.get();
    }

    public String getORCnumber() {
        return ORCnumber.get();
    }

    public String getVehiclePurchasedPrice() {
        return VehiclePurchasedPrice.get();
    }

    public String getAssetSalvageValue() {
        return AssetSalvageValue.get();
    }

    public String getVehicleFuelType() {
        return VehicleFuelType.get();
    }

    public String getVehicleFuelCapacity() {
        return VehicleFuelCapacity.get();
    }

    public String getvAssetCoreLocation() {
        return vAssetCoreLocation.get();
    }

    public String getAssetRegisteredDate() {
        return AssetRegisteredDate.get();
    }

    public String getVehicleStatus() {
        return VehicleStatus.get();
    }

    public String getPriceUpdatedAt() {
        return PriceUpdatedAt.get();
    }

    public String getCurrentPrice() {
        return CurrentPrice.get();
    }

    public String getPriceCurrency() {
        return PriceCurrency.get();
    }

}
