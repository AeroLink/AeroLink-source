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
public class Log1_WarehouseRequestItemClassfiles {
    public SimpleStringProperty RequestID;
    public SimpleStringProperty RequestTitle;
    public SimpleStringProperty Requestor;
    public SimpleStringProperty RequestReason;
    public SimpleStringProperty RequestQuantity;
    public SimpleStringProperty RequestPriorityLevel;
    public SimpleStringProperty RequestorDepartment;
    public SimpleStringProperty RequestorLocation;
    public SimpleStringProperty DateRequested;
    public SimpleStringProperty RequestStatus;
    public SimpleStringProperty TermsOfRecieving;
    public SimpleStringProperty ApprovedRequestRemarks;
    public SimpleStringProperty RequestApprover;
    public SimpleStringProperty DateApproved;
    public SimpleStringProperty TimeApproved;
    
    
    public SimpleStringProperty RecievedFrom;
    public SimpleStringProperty PackagedBy;
    public SimpleStringProperty RecievedBy;
    public SimpleStringProperty RecievedRemarks;
    public SimpleStringProperty DateRecieved;
    public SimpleStringProperty TimeRecieved;
    
    
    
    public SimpleStringProperty ItemID;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemBrand;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty ItemLocation;
    public SimpleStringProperty ItemSKU;
    public SimpleStringProperty ItemSerialNumber;
    public SimpleStringProperty ItemPurchasedDate;
    public SimpleStringProperty ItemPurchasedPrice;
    public SimpleStringProperty ItemPriceCurrency;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty ItemStock;
    public SimpleStringProperty ItemCriticalLevel;
    public SimpleStringProperty ItemRegisteredDate;
    public SimpleStringProperty ItemStatus;
    
    public Log1_WarehouseRequestItemClassfiles(
            String RequestID,
            String RequestTitle,
            String Requestor,
            String RequestReason,
            String RequestQuantity,
            String RequestPriorityLevel,
            String RequestorDepartment,
            String RequestorLocation,
            String DateRequested,
            String RequestStatus,
            String TermsOfRecieving,
            String ApprovedRequestRemarks,
            String RequestApprover,
            String DateApproved,
            String TimeApproved,
            
            String RecievedFrom,
            String PackagedBy,
            String RecievedBy,
            String RecievedRemarks,
            String DateRecieved,
            String TimeRecieved,
            
            
            
            String ItemID,
            String ItemName,
            String ItemBrand,
            String ItemDescription,
            String ItemLocation,
            String ItemSKU,
            String ItemSerialNumber,
            String ItemPurchasedDate,
            String ItemPurchasedPrice,
            String ItemPriceCurrency,
            String ItemUnit,
            String ItemStock,
            String ItemCriticalLevel,
            String ItemRegisteredDate,
            String ItemStatus
    ){
        this.RequestID = new SimpleStringProperty(RequestID);
        this.RequestTitle = new SimpleStringProperty(RequestTitle);
        this.Requestor= new SimpleStringProperty(Requestor);
        this.RequestReason = new SimpleStringProperty(RequestReason);
        this.RequestQuantity = new SimpleStringProperty(RequestQuantity);
        this.RequestPriorityLevel = new SimpleStringProperty(RequestPriorityLevel);
        this.RequestorDepartment = new SimpleStringProperty(RequestorDepartment);
        this.RequestorLocation = new SimpleStringProperty(RequestorLocation);
        this.DateRequested = new SimpleStringProperty(DateRequested);
        this.RequestStatus = new SimpleStringProperty(RequestStatus);
        this.TermsOfRecieving = new SimpleStringProperty(TermsOfRecieving);
        this.ApprovedRequestRemarks = new SimpleStringProperty(ApprovedRequestRemarks);
        this.RequestApprover = new SimpleStringProperty(RequestApprover);
        this.DateApproved = new SimpleStringProperty(DateApproved);
        this.TimeApproved = new SimpleStringProperty(TimeApproved);
        
        this.RecievedFrom = new SimpleStringProperty(RecievedFrom);
        this.PackagedBy = new SimpleStringProperty(PackagedBy);
        this.RecievedBy = new SimpleStringProperty(RecievedBy);
        this.RecievedRemarks = new SimpleStringProperty(RecievedRemarks);
        this.DateRecieved = new SimpleStringProperty(DateRecieved);
        this.TimeRecieved = new SimpleStringProperty(TimeRecieved);
        
        
        
        this.ItemID = new SimpleStringProperty(ItemID);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemBrand = new SimpleStringProperty(ItemBrand);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.ItemLocation = new SimpleStringProperty(ItemLocation);
        this.ItemSKU = new SimpleStringProperty(ItemSKU);
        this.ItemSerialNumber = new SimpleStringProperty(ItemSerialNumber);
        this.ItemPurchasedDate = new SimpleStringProperty(ItemPurchasedDate);
        this.ItemPurchasedPrice = new SimpleStringProperty(ItemPurchasedPrice);
        this.ItemPriceCurrency = new SimpleStringProperty(ItemPriceCurrency);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.ItemStock = new SimpleStringProperty(ItemStock);
        this.ItemCriticalLevel = new SimpleStringProperty(ItemCriticalLevel);
        this.ItemRegisteredDate = new SimpleStringProperty(ItemRegisteredDate);
        this.ItemStatus = new SimpleStringProperty(ItemStatus);
    }

    public String getRequestID() {
        return RequestID.get();
    }
    public String getRecievedRemarks() {
        return RecievedRemarks.get();
    }
    public String getRecievedFrom() {
        return RecievedFrom.get();
    }
    public String getTimeRecieved() {
        return TimeRecieved.get();
    }
    public String getDateRecieved() {
        return DateRecieved.get();
    }
    public String getRecievedBy() {
        return RecievedBy.get();
    }
    public String getPackagedBy() {
        return PackagedBy.get();
    }
    public String getRequestApprover() {
        return RequestApprover.get();
    }
    public String getDateApproved() {
        return DateApproved.get();
    }
    public String getTimeApproved() {
        return TimeApproved.get();
    }
    public String getApprovedRequestRemarks() {
        return ApprovedRequestRemarks.get();
    }
    public String getTermsOfRecieving() {
        return TermsOfRecieving.get();
    }
    public String getRequestorDepartment() {
        return RequestorDepartment.get();
    }
    public String getRequestTitle() {
        return RequestTitle.get();
    }
    public String getRequestor() {
        return Requestor.get();
    }
    public String getRequestReason() {
        return RequestReason.get();
    }
    public String getRequestQuantity() {
        return RequestQuantity.get();
    }
    public String getRequestPriorityLevel() {
        return RequestPriorityLevel.get();
    }
    public String getRequestorLocation() {
        return RequestorLocation.get();
    }
    public String getDateRequested() {
        return DateRequested.get();
    }
    public String getRequestStatus() {
        return RequestStatus.get();
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
    public String getItemPurchasedDate() {
        return ItemPurchasedDate.get();
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
