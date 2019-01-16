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
public class Log1_SupplierClassfiles {
    public SimpleStringProperty SupplierID;
    public SimpleStringProperty SupplierName;
    public SimpleStringProperty SupplierAddress;
    public SimpleStringProperty SupplierContact;
    public SimpleStringProperty SupplierRepresentative;
    
    public Log1_SupplierClassfiles(
            String SupplierID,
            String SupplierName,
            String SupplierAddress,
            String SupplierContact,
            String SupplierRepresentative
            )
    {
        this.SupplierID = new SimpleStringProperty(SupplierID);
        this.SupplierName = new SimpleStringProperty(SupplierName);
        this.SupplierAddress = new SimpleStringProperty(SupplierAddress);
        this.SupplierContact = new SimpleStringProperty(SupplierContact);
        this.SupplierRepresentative = new SimpleStringProperty(SupplierRepresentative);
    }

    public String getSupplierID() {
        return SupplierID.get();
    }

    public String getSupplierName() {
        return SupplierName.get();
    }

    public String getSupplierAddress() {
        return SupplierAddress.get();
    }

    public String getSupplierContact() {
        return SupplierContact.get();
    }

    public String getSupplierRepresentative() {
        return SupplierRepresentative.get();
    }
}
