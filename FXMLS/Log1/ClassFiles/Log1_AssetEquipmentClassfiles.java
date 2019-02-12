package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetEquipmentClassfiles {
    public SimpleStringProperty EquipmentID;
    public SimpleStringProperty BuildingName;
    public SimpleStringProperty EquipmentRoomNumber;
    public SimpleStringProperty EquipmentName;
    public SimpleStringProperty EquipmentType;
    public SimpleStringProperty EquipmentBrand;
    public SimpleStringProperty EquipmentPurchasedPrice;
    public SimpleStringProperty EquipmentPurchasedDate;
    public SimpleStringProperty EquipmentLifeSpan;
    public SimpleStringProperty EquipmentWarranty;
    public SimpleStringProperty CurrentPrice;
    public SimpleStringProperty PriceUpdatedAt;
    
    public Log1_AssetEquipmentClassfiles(
            String Eid,
            String BuildingName,
            String EquipmentName,
            String EquipmentRoomNumber,
            String EquipmentType,
            String EquipmentBrand,
            String EquipmentPurchasedPrice,
            String EquipmentPurchasedDate,
            String EquipmentLifeSpan,
            String EquipmentWarranty,
            String CurrentPrice,
            String PriceUpdatedAt
    ){
        this.EquipmentID = new SimpleStringProperty(Eid);
        this.BuildingName = new SimpleStringProperty(BuildingName);
        this.EquipmentRoomNumber = new SimpleStringProperty(EquipmentRoomNumber);
        this.EquipmentName = new SimpleStringProperty(EquipmentName);
        this.EquipmentType = new SimpleStringProperty(EquipmentType);
        this.EquipmentBrand = new SimpleStringProperty(EquipmentBrand);
        this.EquipmentPurchasedPrice = new SimpleStringProperty(EquipmentPurchasedPrice);
        this.EquipmentPurchasedDate = new SimpleStringProperty(EquipmentPurchasedDate);
        this.EquipmentLifeSpan = new SimpleStringProperty(EquipmentLifeSpan);
        this.EquipmentWarranty = new SimpleStringProperty(EquipmentWarranty);
        this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
        this.PriceUpdatedAt = new SimpleStringProperty(PriceUpdatedAt);
    }
    
    public String getCurrentPrice(){
        return CurrentPrice.get();
    }
    
    public String getPriceUpdateAt(){
        return PriceUpdatedAt.get();
    }

    public String getEquipmentID() {
        return EquipmentID.get();
    }

    public String getEquipmentRoomNumber() {
        return EquipmentRoomNumber.get();
    }

    public String getEquipmentName() {
        return EquipmentName.get();
    }

    public String getEquipmentType() {
        return EquipmentType.get();
    }

    public String getEquipmentBrand() {
        return EquipmentBrand.get();
    }

    public String getEquipmentPurchasedPrice() {
        return EquipmentPurchasedPrice.get();
    }

    public String getEquipmentPurchasedDate() {
        return EquipmentPurchasedDate.get();
    }

    public String getEquipmentLifeSpan() {
        return EquipmentLifeSpan.get();
    }

    public String getEquipmentWarranty() {
        return EquipmentWarranty.get();
    }
}
