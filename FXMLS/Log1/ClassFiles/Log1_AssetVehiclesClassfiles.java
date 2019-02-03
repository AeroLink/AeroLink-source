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
    public SimpleStringProperty BuildingName;
    public SimpleStringProperty VehicleType;
    public SimpleStringProperty VehicleModel;
    public SimpleStringProperty VehicleColor;
    public SimpleStringProperty VehicleSerialNumber;
    public SimpleStringProperty VehicleChassisNumber;
    public SimpleStringProperty VehicleYearBought;
    public SimpleStringProperty VehicleWarrantyDate;
    public SimpleStringProperty VehiclePurchasedPrice;
    public SimpleStringProperty VehicleFuelType;
    public SimpleStringProperty VehicleUsability;
    public SimpleStringProperty VehicleStatus;
    public SimpleStringProperty PriceUpdatedAt;
    public SimpleStringProperty CurrentPrice;
    
    public Log1_AssetVehiclesClassfiles(
            String VehicleID,
            String BuildingName,
            String AssetCategory,
            String VehicleType,
            String VehicleModel,
            String VehicleColor,
            String VehicleSerialNumber,
            String VehicleChassisNumber,
            String VehicleYearBought,
            String VehicleWarrantyDate,
            String VehiclePurchasedPrice,
            String VehicleFuelType,
            String VehicleUsability,
            String VehicleStatus,
            String PriceUpdatedAt,
            String CurrentPrice
    ){
        this.VehicleID = new SimpleStringProperty(VehicleID);
        this.BuildingName = new SimpleStringProperty(BuildingName);
        this.VehicleType = new SimpleStringProperty(VehicleType);
        this.VehicleModel = new SimpleStringProperty(VehicleModel);
        this.VehicleColor = new SimpleStringProperty(VehicleColor);
        this.VehicleSerialNumber = new SimpleStringProperty(VehicleSerialNumber);
        this.VehicleChassisNumber = new SimpleStringProperty(VehicleChassisNumber);
        this.VehicleYearBought = new SimpleStringProperty(VehicleYearBought);
        this.VehicleWarrantyDate = new SimpleStringProperty(VehicleWarrantyDate);
        this.VehiclePurchasedPrice = new SimpleStringProperty(VehiclePurchasedPrice);
        this.VehicleFuelType = new SimpleStringProperty(VehicleFuelType);
        this.VehicleUsability = new SimpleStringProperty(VehicleUsability);
        this.VehicleStatus = new SimpleStringProperty(VehicleStatus);
        this.PriceUpdatedAt = new SimpleStringProperty(PriceUpdatedAt);
        this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
    }
    
    public String getPriceUpdatedAt(){
        return PriceUpdatedAt.get();
    }
    public String getCurrentPrice(){
        return CurrentPrice.get();
    }

    public String getVehicleID() {
        return VehicleID.get();
    }

    public String getBuildingName() {
        return BuildingName.get();
    }

    public String getVehicleType() {
        return VehicleType.get();
    }

    public String getVehicleModel() {
        return VehicleModel.get();
    }

    public String getVehicleColor() {
        return VehicleColor.get();
    }

    public String getVehicleSerialNumber() {
        return VehicleSerialNumber.get();
    }

    public String getVehicleChassisNumber() {
        return VehicleChassisNumber.get();
    }


    public String getVehicleYearBought() {
        return VehicleYearBought.get();
    }

    public String getVehicleWarrantyDate() {
        return VehicleWarrantyDate.get();
    }

    public String getVehiclePurchasedPrice() {
        return VehiclePurchasedPrice.get();
    }

    public String getVehicleFuelType() {
        return VehicleFuelType.get();
    }

    public String getVehicleUsability() {
        return VehicleUsability.get();
    }

    public String getVehicleStatus() {
        return VehicleStatus.get();
    }

    
}
