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
public class Log1_ProcurementBiddersClassfiles {
    public SimpleStringProperty BidderID;
    public SimpleStringProperty ItemID;
    public SimpleStringProperty BidderName;
    public SimpleStringProperty BidderRepresentative;
    public SimpleStringProperty BidderContact;
    public SimpleStringProperty BidderEmail;
    public SimpleStringProperty BidderLocation;
    public SimpleStringProperty BidderPrice;
    public SimpleStringProperty BidderOtherOffer;
    
    public Log1_ProcurementBiddersClassfiles(
            String BidderID,
            String ItemID,
            String BidderName,
            String BidderRepresentative,
            String BidderContact,
            String BidderEmail,
            String BidderLocation,
            String BidderPrice,
            String BidderOtherOffer
    ){
        this.BidderID = new SimpleStringProperty(BidderID);
        this.ItemID = new SimpleStringProperty(ItemID);
        this.BidderName = new SimpleStringProperty(BidderName);
        this.BidderRepresentative = new SimpleStringProperty(BidderRepresentative);
        this.BidderContact = new SimpleStringProperty(BidderContact);
        this.BidderEmail = new SimpleStringProperty(BidderEmail);
        this.BidderLocation = new SimpleStringProperty(BidderLocation);
        this.BidderPrice = new SimpleStringProperty(BidderPrice);
        this.BidderOtherOffer = new SimpleStringProperty(BidderOtherOffer);
    }

    public String getBidderID() {
        return BidderID.get();
    }
    
    public String getBidderOtherOffer() {
        return BidderOtherOffer.get();
    }
    
//    public String getCurrency() {
//        return Currency.get();
//    }

    public String getItemID() {
        return ItemID.get();
    }

    public String getBidderName() {
        return BidderName.get();
    }

    public String getBidderRepresentative() {
        return BidderRepresentative.get();
    }

    public String getBidderContact() {
        return BidderContact.get();
    }

    public String getBidderEmail() {
        return BidderEmail.get();
    }

    public String getBidderLocation() {
        return BidderLocation.get();
    }

    public String getBidderPrice() {
        return BidderPrice.get();
    }
}
