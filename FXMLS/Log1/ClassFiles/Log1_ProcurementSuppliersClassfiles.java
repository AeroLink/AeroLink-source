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
public class Log1_ProcurementSuppliersClassfiles {
    public SimpleStringProperty SupplierID;
    public SimpleStringProperty SupplierName;
    public SimpleStringProperty SupplierRepresentative;
    public SimpleStringProperty SupplierContact;
    public SimpleStringProperty SupplierEmail;
    public SimpleStringProperty SupplierLocation;
    public SimpleStringProperty ContractStarted;
    public SimpleStringProperty ContractEnd;
    public SimpleStringProperty AwardedBy;
    public SimpleStringProperty Remarks;
    public SimpleStringProperty SupplierStatus;
    
    public Log1_ProcurementSuppliersClassfiles(
            String SupplierID,
            String SupplierName,
            String SupplierRepresentative,
            String SupplierContact,
            String SupplierEmail,
            String SupplierLocation,
            String ContractStarted,
            String ContractEnd,
            String AwardedBy,
            String Remarks,
            String SupplierStatus
    ){
        this.SupplierID = new SimpleStringProperty(SupplierID);
        this.SupplierName = new SimpleStringProperty(SupplierName);
        this.SupplierRepresentative = new SimpleStringProperty(SupplierRepresentative);
        this.SupplierContact = new SimpleStringProperty(SupplierContact);
        this.SupplierEmail = new SimpleStringProperty(SupplierEmail);
        this.SupplierLocation = new SimpleStringProperty(SupplierLocation);
        this.ContractStarted = new SimpleStringProperty(ContractStarted);
        this.ContractEnd = new SimpleStringProperty(ContractEnd);
        this.AwardedBy = new SimpleStringProperty(AwardedBy);
        this.Remarks = new SimpleStringProperty(Remarks);
        this.SupplierStatus = new SimpleStringProperty(SupplierStatus);
    }

    public String getSupplierID() {
        return SupplierID.get();
    }

    public String getSupplierName() {
        return SupplierName.get();
    }

    public String getSupplierRepresentative() {
        return SupplierRepresentative.get();
    }

    public String getSupplierContact() {
        return SupplierContact.get();
    }

    public String getSupplierEmail() {
        return SupplierEmail.get();
    }

    public String getSupplierLocation() {
        return SupplierLocation.get();
    }

    public String getContractStarted() {
        return ContractStarted.get();
    }

    public String getContractEnd() {
        return ContractEnd.get();
    }

    public String getAwardedBy() {
        return AwardedBy.get();
    }

    public String getRemarks() {
        return Remarks.get();
    }

    public String getSupplierStatus() {
        return SupplierStatus.get();
    }
}
