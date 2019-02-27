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
public class Log1_ProcurementPurchaseListClassfiles {
    public SimpleStringProperty PurchasedID;
    public SimpleStringProperty ItemID;
    public SimpleStringProperty SupplierID;
    public SimpleStringProperty Price;
    public SimpleStringProperty Currency;
    public SimpleStringProperty Status;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty SupplierName;
    public SimpleStringProperty SupplierRepresentative;
    public SimpleStringProperty SupplierContact;
    public SimpleStringProperty SupplierEmail;
    public SimpleStringProperty SupplierLocation;
    
    public Log1_ProcurementPurchaseListClassfiles(
            String PurchasedID,
            String ItemID,
            String SupplierID,
            String Price,
            String Currency,
            String Status,
            String ItemName,
            String ItemDescription,
            String ItemUnit,
            String Quantity,
            String SupplierName,
            String SupplierRepresentative,
            String SupplierContact,
            String SupplierEmail,
            String SupplierLocation
    ){
        this.PurchasedID = new SimpleStringProperty(PurchasedID);
        this.ItemID = new SimpleStringProperty(ItemID);
        this.SupplierID = new SimpleStringProperty(SupplierID);
        this.Price = new SimpleStringProperty(Price);
        this.Currency = new SimpleStringProperty(Currency);
        this.Status = new SimpleStringProperty(Status);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.SupplierName = new SimpleStringProperty(SupplierName);
        this.SupplierRepresentative = new SimpleStringProperty(SupplierRepresentative);
        this.SupplierContact = new SimpleStringProperty(SupplierContact);
        this.SupplierEmail = new SimpleStringProperty(SupplierEmail);
        this.SupplierLocation = new SimpleStringProperty(SupplierLocation);
    }

    public String getPurchasedID() {
        return PurchasedID.get();
    }

    public String getItemID() {
        return ItemID.get();
    }

    public String getSupplierID() {
        return SupplierID.get();
    }

    public String getPrice() {
        return Price.get();
    }

    public String getCurrency() {
        return Currency.get();
    }

    public String getStatus() {
        return Status.get();
    }

    public String getItemName() {
        return ItemName.get();
    }

    public String getItemDescription() {
        return ItemDescription.get();
    }

    public String getItemUnit() {
        return ItemUnit.get();
    }

    public String getQuantity() {
        return Quantity.get();
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
}
