package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;


public class Log1_WarehouseItemsClassfiles {
    public SimpleStringProperty ItemID;
    public SimpleStringProperty SupplierID;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemBrand;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty ItemLocation;
    public SimpleStringProperty ItemSKU;
    public SimpleStringProperty ItemSerialNumber;
    public SimpleStringProperty ItemPurchasedPrice;
    public SimpleStringProperty ItemPriceCurrency;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty ItemStock;
    public SimpleStringProperty ItemCriticalLevel;
    public SimpleStringProperty ItemRegisteredBy;
    public SimpleStringProperty ItemRegisteredDate;
    public SimpleStringProperty ItemStatus;
    
    public Log1_WarehouseItemsClassfiles(
            String ItemID,
            String SupplierID,
            String ItemName,
            String ItemBrand,
            String ItemDescription,
            String ItemLocation,
            String ItemSKU,
            String ItemSerialNumber,
            String ItemPurchasedPrice,
            String ItemPriceCurrency,
            String ItemUnit,
            String ItemStock,
            String ItemCriticalLevel,
            String ItemRegisteredBy,
            String ItemRegisteredDate,
            String ItemStatus
    ){
        this.ItemID = new SimpleStringProperty(ItemID);
        this.SupplierID = new SimpleStringProperty(SupplierID);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemBrand = new SimpleStringProperty(ItemBrand);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.ItemLocation = new SimpleStringProperty(ItemLocation);
        this.ItemSKU = new SimpleStringProperty(ItemSKU);
        this.ItemSerialNumber = new SimpleStringProperty(ItemSerialNumber);
        this.ItemPurchasedPrice = new SimpleStringProperty(ItemPurchasedPrice);
        this.ItemPriceCurrency = new SimpleStringProperty(ItemPriceCurrency);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.ItemStock = new SimpleStringProperty(ItemStock);
        this.ItemCriticalLevel = new SimpleStringProperty(ItemCriticalLevel);
        this.ItemRegisteredBy = new SimpleStringProperty(ItemRegisteredBy);
        this.ItemRegisteredDate = new SimpleStringProperty(ItemRegisteredDate);
        this.ItemStatus = new SimpleStringProperty(ItemStatus);
    }

    public String getItemID() {
        return ItemID.get();
    }
    
    public String getSupplierID() {
        return SupplierID.get();
    }

    public String getItemName() {
        return ItemName.get();
    }

    public String getItemBrand() {
        return ItemBrand.get();
    }

    public String getItemDescription() {
        return ItemDescription.get();
    }

    public String getItemLocation() {
        return ItemLocation.get();
    }

    public String getItemSKU() {
        return ItemSKU.get();
    }

    public String getItemSerialNumber() {
        return ItemSerialNumber.get();
    }

    public String getItemRegisteredBy() {
        return ItemRegisteredBy.get();
    }

    public String getItemPurchasedPrice() {
        return ItemPurchasedPrice.get();
    }

    public String getItemPriceCurrency() {
        return ItemPriceCurrency.get();
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

    public String getItemRegisteredDate() {
        return ItemRegisteredDate.get();
    }

    public String getItemStatus() {
        return ItemStatus.get();
    }

   
}
