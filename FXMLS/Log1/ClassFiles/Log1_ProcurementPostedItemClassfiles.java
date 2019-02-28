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
public class Log1_ProcurementPostedItemClassfiles {
    public SimpleStringProperty ItemID;
    public SimpleStringProperty ItemName;
    public SimpleStringProperty ItemDescription;
    public SimpleStringProperty ItemUnit;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty NumberOfBidders;
    public SimpleStringProperty NumberOfViews;
    public SimpleStringProperty DatePostedStarted;
    public SimpleStringProperty DatePostedEnded;
    
    public Log1_ProcurementPostedItemClassfiles(
            String ItemID,
            String ItemName,
            String ItemDescription,
            String ItemUnit,
            String Quantity,
            String NumberOfBidders,
            String NumberOfViews,
            String DatePostedStarted,
            String DatePostedEnded
    ){
        this.ItemID = new SimpleStringProperty(ItemID);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.ItemUnit = new SimpleStringProperty(ItemUnit);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.NumberOfBidders = new SimpleStringProperty(NumberOfBidders);
        this.NumberOfViews = new SimpleStringProperty(NumberOfViews);
        this.DatePostedStarted = new SimpleStringProperty(DatePostedStarted);
        this.DatePostedEnded = new SimpleStringProperty(DatePostedEnded);
    }

    public String getItemID() {
        return ItemID.get();
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

    public String getNumberOfBidders() {
        return NumberOfBidders.get();
    }

    public String getNumberOfViews() {
        return NumberOfViews.get();
    }

    public String getDatePostedStarted() {
        return DatePostedStarted.get();
    }

    public String getDatePostedEnded() {
        return DatePostedEnded.get();
    }


}
