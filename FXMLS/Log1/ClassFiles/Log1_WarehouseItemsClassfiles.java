package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;


public class Log1_WarehouseItemsClassfiles {
    public SimpleStringProperty ItemID;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemBrand;
    public SimpleStringProperty ItemLocation;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty ItemStock;
    public SimpleStringProperty ItemCriticalLevel;
    public SimpleStringProperty ItemExpirationDate;
    public SimpleStringProperty ItemPurchasedPrice;
    public SimpleStringProperty ItemStatus;
    
    public Log1_WarehouseItemsClassfiles(
            String ItemID,
            String ItemName,
            String ItemBrand,
            String ItemLocation,
            String ItemUnit,
            String ItemStock,
            String ItemCriticalLevel,
            String ItemExpirationDate,
            String ItemPurchasedPrice,
            String ItemStatus
    ){
        this.ItemID = new SimpleStringProperty(ItemID);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemBrand = new SimpleStringProperty(ItemBrand);
        this.ItemLocation = new SimpleStringProperty(ItemLocation);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.ItemStock = new SimpleStringProperty(ItemStock);
        this.ItemCriticalLevel = new SimpleStringProperty(ItemCriticalLevel);
        this.ItemExpirationDate = new SimpleStringProperty(ItemExpirationDate);
        this.ItemPurchasedPrice = new SimpleStringProperty(ItemPurchasedPrice);
        this.ItemStatus = new SimpleStringProperty(ItemStatus);
    }

    public String getItemID() {
        return ItemID.get();
    }

    public String getItemName() {
        return ItemName.get();
    }

    public String getItemBrand() {
        return ItemBrand.get();
    }

    public String getItemLocation() {
        return ItemLocation.get();
    }

    public String getItemUnit() {
        return ItemUnit.get();
    }

    public String getItemStock() {
        return ItemStock.get();
    }

    public String getItemCriticalLevel() {
        return ItemCriticalLevel.get();
    }

    public String getItemExpirationDate() {
        return ItemExpirationDate.get();
    }

    public String getItemPurchasedPrice() {
        return ItemPurchasedPrice.get();
    }

    public String getItemStatus() {
        return ItemStatus.get();
    }
}
