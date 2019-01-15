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
public class Log1_ItemRequestsClassfiles {
    public SimpleStringProperty RequestOnWarehouseID;
    
    public SimpleStringProperty ItemID;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty StockQuantity;
    public SimpleStringProperty Status;
    public SimpleStringProperty ItemLocation;
    public SimpleStringProperty CriticalQuantity;
    
    public SimpleStringProperty QuantityRequested;
    public SimpleStringProperty RequestedBy;
    public SimpleStringProperty Destination; 
    public SimpleStringProperty DateItemRequested;
    public SimpleStringProperty RequestStatus;
    
    public Log1_ItemRequestsClassfiles(
            String RequestOnWarehouseID,
            
            String ItemID,
            String itemDescription,
            String StockQuantity,
            String Status,
            String ItemLocation,
            String CriticalQuantity,
            
            String quantity,
            String requestedBy,
            String destination,
            String DateItemRequested,
            String RequestStatus
    ){
        this.RequestOnWarehouseID = new SimpleStringProperty(RequestOnWarehouseID);
        
        this.ItemID = new SimpleStringProperty(ItemID);
        this.ItemDescription = new SimpleStringProperty(itemDescription);
        this.StockQuantity = new SimpleStringProperty(StockQuantity);
        this.Status = new SimpleStringProperty(Status);
        this.ItemLocation = new SimpleStringProperty(ItemLocation);
        this.CriticalQuantity = new SimpleStringProperty(CriticalQuantity);
        
        this.QuantityRequested = new SimpleStringProperty(quantity);
        this.RequestedBy = new SimpleStringProperty(requestedBy);
        this.Destination = new SimpleStringProperty(destination);
        this.DateItemRequested = new SimpleStringProperty(DateItemRequested);
        this.RequestStatus = new SimpleStringProperty(RequestStatus);
    }

    public String getRequestOnWarehouseID() {
        return RequestOnWarehouseID.get();
    }

    public String getItemID() {
        return ItemID.get();
    }

    public String getItemDescription() {
        return ItemDescription.get();
    }

    public String getStockQuantity() {
        return StockQuantity.get();
    }

    public String getStatus() {
        return Status.get();
    }

    public String getItemLocation() {
        return ItemLocation.get();
    }

    public String getCriticalQuantity() {
        return CriticalQuantity.get();
    }

    public String getQuantityRequested() {
        return QuantityRequested.get();
    }

    public String getRequestedBy() {
        return RequestedBy.get();
    }

    public String getDestination() {
        return Destination.get();
    }

    public String getDateItemRequested() {
        return DateItemRequested.get();
    }

    public String getRequestStatus() {
        return RequestStatus.get();
    }

}
