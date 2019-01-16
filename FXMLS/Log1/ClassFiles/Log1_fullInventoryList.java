package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;


public class Log1_fullInventoryList{

    
    public SimpleStringProperty itemID;
    public SimpleStringProperty supplierName;
    public SimpleStringProperty itemDescription;
    public SimpleStringProperty itemType;
    public SimpleStringProperty itemLocation;
    public SimpleStringProperty itemUnit;
    public SimpleStringProperty unitPrice;
    public SimpleStringProperty stockQuantity;
    public SimpleStringProperty criticalQuantity;
    public SimpleStringProperty disposalDate;
    public SimpleStringProperty status;

    

    public Log1_fullInventoryList(
            String itemID,
            String supplierName,
            String itemDescription,
            String itemType,
            String itemLocation,
            String itemUnit,
            String unitPrice,
            String stockQuantity,
            String criticalQuantity,
            String disposalDate,
            String status
        ) 
    {
        this.itemID = new SimpleStringProperty(itemID);
        this.supplierName = new SimpleStringProperty(supplierName);
        this.itemDescription = new SimpleStringProperty(itemDescription);
        this.itemType = new SimpleStringProperty(itemType);
        this.itemLocation = new SimpleStringProperty(itemLocation);
        this.itemUnit = new SimpleStringProperty(itemUnit);
        this.unitPrice = new SimpleStringProperty(unitPrice);
        this.stockQuantity = new SimpleStringProperty(stockQuantity);
        this.criticalQuantity = new SimpleStringProperty(criticalQuantity);
        this.disposalDate = new SimpleStringProperty(disposalDate);
        this.status = new SimpleStringProperty(status);
    }

    public String getSupplierName() {
        return supplierName.get();
    }
    
    public String getItemID() {
        return itemID.get();
    }
    public String getItemDescription() {
        return itemDescription.get();
    }
    public String getItemType() {
        return itemType.get();
    }
    public String getItemLocation() {
        return itemLocation.get();
    }
    public String getItemUnit() {
        return itemUnit.get();
    }    
    public String getUnitPrice() {
        return unitPrice.get();
    }   
    public String getStockQuantity() {
        return stockQuantity.get();
    } 
    public String getCriticalQuantity() {
        return criticalQuantity.get();
    }
    public String getDisposalDate() {
        return disposalDate.get();
    }
     public String getStatus() {
        return status.get();
   }
    
}
