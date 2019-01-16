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
    public SimpleStringProperty requestDate;
    public SimpleStringProperty requestDescription;
    public SimpleStringProperty requestor;
    public SimpleStringProperty requestorPosition;
    public SimpleStringProperty requestDepartment;
    public SimpleStringProperty requestPriorityLevel;
    public SimpleStringProperty requestQuantity;
    public SimpleStringProperty requestPrice;
    public SimpleStringProperty requestStatus;
    
    public Log1_ProcRequestsClassFile(
            String requestID,
            String requestDate,
            String requestDescription,
            String requestor,
            String requestorPosition,
            String requestDepartment,
            String requestPriorityLevel,
            String requestQuantity,
            String requestPrice,
            String requestStatus
        ) 
    {
        this.requestID = new SimpleStringProperty(requestID);
        this.requestDate = new SimpleStringProperty(requestDate);
        this.requestDescription = new SimpleStringProperty(requestDescription);
        this.requestor = new SimpleStringProperty(requestor);
        this.requestorPosition = new SimpleStringProperty(requestorPosition);
        this.requestDepartment = new SimpleStringProperty(requestDepartment);
        this.requestPriorityLevel = new SimpleStringProperty(requestPriorityLevel);
        this.requestQuantity = new SimpleStringProperty(requestQuantity);
        this.requestPrice = new SimpleStringProperty(requestPrice);
        this.requestStatus = new SimpleStringProperty(requestStatus);
    }

    public String getRequestID() {
        return requestID.get();
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

    public String getRequestPrice() {
        return requestPrice.get();
    }

    public String getRequestStatus() {
        return requestStatus.get();
    }

    public String getRequestDate() {
        return requestDate.get();
    }
}
