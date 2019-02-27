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
public class Log1_ProcurementNewItemClassfiles {
    public SimpleStringProperty RequestID;
    public SimpleStringProperty RequestTitle;
    public SimpleStringProperty DateRequested;
    public SimpleStringProperty Requestor;
    public SimpleStringProperty Department;
    public SimpleStringProperty Location;
    public SimpleStringProperty RequestReason;
    public SimpleStringProperty PriorityLevel;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty RequestStatus;
    
    public Log1_ProcurementNewItemClassfiles(
            String RequestID,
            String RequestTitle,
            String DateRequested,
            String Requestor,
            String Department,
            String Location,
            String RequestReason,
            String PriorityLevel,
            String ItemName,
            String ItemUnit,
            String Quantity,
            String ItemDescription,
            String RequestStatus
    ){
        this.RequestID = new SimpleStringProperty(RequestID);
        this.RequestTitle = new SimpleStringProperty(RequestTitle);
        this.DateRequested = new SimpleStringProperty(DateRequested);
        this.Requestor = new SimpleStringProperty(Requestor);
        this.Department = new SimpleStringProperty(Department);
        this.Location = new SimpleStringProperty(Location);
        this.RequestReason = new SimpleStringProperty(RequestReason);
        this.PriorityLevel = new SimpleStringProperty(PriorityLevel);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.RequestStatus = new SimpleStringProperty(RequestStatus);
    }

    public String getRequestID() {
        return RequestID.get();
    }

    public String getRequestTitle() {
        return RequestTitle.get();
    }

    public String getRequestor() {
        return Requestor.get();
    }

    public String getDepartment() {
        return Department.get();
    }

    public String getLocation() {
        return Location.get();
    }

    public String getRequestReason() {
        return RequestReason.get();
    }

    public String getPriorityLevel() {
        return PriorityLevel.get();
    }

    public String getItemName() {
        return ItemName.get();
    }
    
    public String getItemUnit() {
        return ItemUnit.get();
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public String getItemDescription() {
        return ItemDescription.get();
    }

    public String getDateRequested() {
        return DateRequested.get();
    }
    
    public String getRequestStatus() {
        return RequestStatus.get();
    }
}

