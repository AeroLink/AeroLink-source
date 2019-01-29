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
public class Log1_ProcRequestsClassFile {
    public SimpleStringProperty requestID;
    public SimpleStringProperty SupplierID;
    public SimpleStringProperty requestDate;
    public SimpleStringProperty requestDescription;
    public SimpleStringProperty requestor;
    public SimpleStringProperty requestorPosition;
    public SimpleStringProperty requestDepartment;
    public SimpleStringProperty requestPriorityLevel;
    public SimpleStringProperty requestQuantity;
    public SimpleStringProperty requestItemUnit;
    public SimpleStringProperty requestPricePerUnit;
    public SimpleStringProperty requestTotalPrice;
    public SimpleStringProperty requestBudget;
    public SimpleStringProperty requestStatus;
    
    public Log1_ProcRequestsClassFile(
            String requestID,
            String supplierID,
            String requestDate,
            String requestDescription,
            String requestor,
            String requestorPosition,
            String requestDepartment,
            String requestPriorityLevel,
            String requestQuantity,
            String requestItemUnit,
            String requestPricePerUnit,
            String requestTotalPrice,
            String requestBudget,
            String requestStatus
        ) 
    {
        this.requestID = new SimpleStringProperty(requestID);
        this.SupplierID = new SimpleStringProperty(supplierID);
        this.requestDescription = new SimpleStringProperty(requestDescription);
        this.requestor = new SimpleStringProperty(requestor);
        this.requestorPosition = new SimpleStringProperty(requestorPosition);
        this.requestDepartment = new SimpleStringProperty(requestDepartment);
        this.requestPriorityLevel = new SimpleStringProperty(requestPriorityLevel);
        this.requestQuantity = new SimpleStringProperty(requestQuantity);
        this.requestItemUnit = new SimpleStringProperty(requestItemUnit);
        this.requestPricePerUnit = new SimpleStringProperty(requestPricePerUnit);
        this.requestTotalPrice = new SimpleStringProperty(requestTotalPrice);
        this.requestBudget = new SimpleStringProperty(requestBudget);
        this.requestStatus = new SimpleStringProperty(requestStatus);
        this.requestDate = new SimpleStringProperty(requestDate);
    }

    
    public String getRequestID() {
        return requestID.get();
    }

    public String getSupplierID() {
        return SupplierID.get();
    }

    public String getRequestDate() {
        return requestDate.get();
    }

    public String getRequestDescription() {
        return requestDescription.get();
    }

    public String getRequestor() {
        return requestor.get();
    }

    public String getRequestorPosition() {
        return requestorPosition.get();
    }

    public String getRequestDepartment() {
        return requestDepartment.get();
    }

    public String getRequestPriorityLevel() {
        return requestPriorityLevel.get();
    }

    public String getRequestQuantity() {
        return requestQuantity.get();
    }

    public String getRequestItemUnit() {
        return requestItemUnit.get();
    }

    public String getRequestPricePerUnit() {
        return requestPricePerUnit.get();
    }

    public String getRequestTotalPrice() {
        return requestTotalPrice.get();
    }

    public String getRequestBudget() {
        return requestBudget.get();
    }

    public String getRequestStatus() {
        return requestStatus.get();
    }

   
}
