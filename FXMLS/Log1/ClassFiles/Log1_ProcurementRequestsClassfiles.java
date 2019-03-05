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
public class Log1_ProcurementRequestsClassfiles {
    public SimpleStringProperty ProcureID;
    public SimpleStringProperty RequestTitle;
    public SimpleStringProperty NameOfRequestor;
    public SimpleStringProperty Department;
    public SimpleStringProperty Location;
    public SimpleStringProperty Reason;
    public SimpleStringProperty PriorityLevel;
    public SimpleStringProperty Item;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty DateRequested;
    public SimpleStringProperty RequestStatus;
    public SimpleStringProperty RequestType;
    
    public Log1_ProcurementRequestsClassfiles(
            String ProcureID,
            String RequestTitle,
            String NameOfRequestor,
            String Department,
            String Location,
            String Reason,
            String PriorityLevel,
            String Item,
            String Quantity,
            String ItemDescription,
            String DateRequested,
            String RequestStatus,
            String RequestType
    ){
        this.ProcureID = new SimpleStringProperty(ProcureID);
        this.RequestTitle = new SimpleStringProperty(RequestTitle);
        this.NameOfRequestor = new SimpleStringProperty(NameOfRequestor);
        this.Department = new SimpleStringProperty(Department);
        this.Location = new SimpleStringProperty(Location);
        this.Reason = new SimpleStringProperty(Reason);
        this.PriorityLevel = new SimpleStringProperty(PriorityLevel);
        this.Item = new SimpleStringProperty(Item);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.DateRequested = new SimpleStringProperty(DateRequested);
        this.RequestStatus = new SimpleStringProperty(RequestStatus);
        this.RequestType = new SimpleStringProperty(RequestType);
    }

    public String getProcureID() {
        return ProcureID.get();
    }

    public String getRequestTitle() {
        return RequestTitle.get();
    }

    public String getNameOfRequestor() {
        return NameOfRequestor.get();
    }

    public String getDepartment() {
        return Department.get();
    }

    public String getLocation() {
        return Location.get();
    }

    public String getReason() {
        return Reason.get();
    }

    public String getPriorityLevel() {
        return PriorityLevel.get();
    }

    public String getItem() {
        return Item.get();
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
    
    public String getRequestType() {
        return RequestType.get();
    }
}
