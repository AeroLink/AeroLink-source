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
    public SimpleStringProperty ItemID;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty Requestor;
    public SimpleStringProperty RequestTitle;
    public SimpleStringProperty RequestLocation;
    public SimpleStringProperty RequestQuantity;
    public SimpleStringProperty RequestPurpose;
    public SimpleStringProperty RequestPriorityLevel;
    public SimpleStringProperty DateRequested;
    public SimpleStringProperty RequestStatus;
    
    public Log1_WarehouseRequestItemClassfiles(
            String RequestID,
            String ItemID,
            String ItemName,
            String ItemUnit,
            String Requestor,
            String RequestTitle,
            String RequestLocation,
            String RequestQuantity,
            String RequestPurpose,
            String RequestPriorityLevel,
            String DateRequested,
            String RequestStatus
    ){
        this.RequestID = new SimpleStringProperty(RequestID);
        this.ItemID = new SimpleStringProperty(ItemID);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.Requestor = new SimpleStringProperty(Requestor);
        this.RequestTitle= new SimpleStringProperty(RequestTitle);
        this.RequestLocation = new SimpleStringProperty(RequestLocation);
        this.RequestQuantity = new SimpleStringProperty(RequestQuantity);
        this.RequestPurpose = new SimpleStringProperty(RequestPurpose);
        this.RequestPriorityLevel = new SimpleStringProperty(RequestPriorityLevel);
        this.DateRequested = new SimpleStringProperty(DateRequested);
        this.RequestStatus = new SimpleStringProperty(RequestStatus);
    }

    public String getRequestStatus(){
        return RequestStatus.get();
    }
    public String getItemID(){
        return ItemID.get();
    }
    
    public String getDateRequested(){
        return DateRequested.get();
    }
    
    public String getRequestID() {
        return RequestID.get();
    }

    public String getItemName() {
        return ItemName.get();
    }

    public String getItemUnit() {
        return ItemUnit.get();
    }

    public String getRequestor() {
        return Requestor.get();
    }

    public String getRequestTitle() {
        return RequestTitle.get();
    }

    public String getRequestLocation() {
        return RequestLocation.get();
    }

    public String getRequestQuantity() {
        return RequestQuantity.get();
    }

    public String getRequestPurpose() {
        return RequestPurpose.get();
    }

    public String getRequestPriorityLevel() {
        return RequestPriorityLevel.get();
    }
}
