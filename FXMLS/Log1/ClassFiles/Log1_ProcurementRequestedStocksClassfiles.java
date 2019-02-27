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
public class Log1_ProcurementRequestedStocksClassfiles {
    public SimpleStringProperty ProcureStockItemID;
    public SimpleStringProperty ItemID;
    public SimpleStringProperty RequestTitle;
    public SimpleStringProperty DateRequested;
    public SimpleStringProperty Requestor;
    public SimpleStringProperty Department;
    public SimpleStringProperty Location;
    public SimpleStringProperty Reason;
    public SimpleStringProperty PriorityLevel;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty StockRequestStatus;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty ItemStatus;
    
    public Log1_ProcurementRequestedStocksClassfiles(
            String ProcureStockItemID,
            String ItemID,
            String RequestTitle,
            String DateRequested,
            String Requestor,
            String Department,
            String Location,
            String Reason,
            String PriorityLevel,
            String Quantity,
            String StockRequestStatus,
            String ItemName,
            String ItemDescription,
            String ItemStatus
    ){
        this.ProcureStockItemID = new SimpleStringProperty(ProcureStockItemID);
        this.ItemID = new SimpleStringProperty(ItemID);
        this.RequestTitle = new SimpleStringProperty(RequestTitle);
        this.DateRequested = new SimpleStringProperty(DateRequested);
        this.Requestor = new SimpleStringProperty(Requestor);
        this.Department = new SimpleStringProperty(Department);
        this.Location = new SimpleStringProperty(Location);
        this.Reason = new SimpleStringProperty(Reason);
        this.PriorityLevel = new SimpleStringProperty(PriorityLevel);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.StockRequestStatus = new SimpleStringProperty(StockRequestStatus);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.ItemStatus = new SimpleStringProperty(ItemStatus);
    }

    public String getProcureStockItemID() {
        return ProcureStockItemID.get();
    }
    public String getStockRequestStatus() {
        return StockRequestStatus.get();
    }

    public String getItemID() {
        return ItemID.get();
    }

    public String getRequestTitle() {
        return RequestTitle.get();
    }

    public String getDateRequested() {
        return DateRequested.get();
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

    public String getReason() {
        return Reason.get();
    }

    public String getPriorityLevel() {
        return PriorityLevel.get();
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public String getItemName() {
        return ItemName.get();
    }

    public String getItemDescription() {
        return ItemDescription.get();
    }

    public String getItemStatus() {
        return ItemStatus.get();
    }
}
