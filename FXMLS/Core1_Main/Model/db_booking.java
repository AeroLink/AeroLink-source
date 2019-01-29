/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 *
 * @author lemnovo
 */
public class db_booking {
    
    private ReadOnlyStringWrapper shipper_address = new ReadOnlyStringWrapper();
    private ReadOnlyStringWrapper receiver_address = new ReadOnlyStringWrapper();
    private ReadOnlyStringWrapper contact_person = new ReadOnlyStringWrapper();
    
    private String book_no;
    private String book_date;
    
    private String ship_name;
    private String ship_address;
    private String ship_brgy;
    private String ship_city;
    private String ship_province;
    private String ship_zip;
    private String ship_email;
    private String ship_contact;
    
    private String rec_name;
    private String rec_address;
    private String rec_brgy;
    private String rec_city;
    private String rec_province;
    private String rec_zip;
    private String rec_contact;
    
    private String serv_type;
    private String serv_box;
    private String serv_quantity;
    private String serv_insurance;
    private String serv_liability;
    private String status;
    private String ref_no;

    public db_booking(String book_no, String book_date, String ship_name, String ship_address, String ship_brgy, String ship_city, String ship_province, String ship_zip, String ship_email, String ship_contact, String rec_name, String rec_address, String rec_brgy, String rec_city, String rec_province, String rec_zip, String rec_contact, String serv_type, String serv_box, String serv_quantity, String serv_insurance, String serv_liability, String status, String ref_no) {
       
        shipper_address.bind(Bindings.concat(ship_address, " ", ship_brgy, " ", ship_city, ", ", ship_province , ", ", ship_zip));
        receiver_address.bind(Bindings.concat(rec_address, " ", rec_brgy, " ", rec_city, ", ", rec_province , ", ", rec_zip));
        contact_person.bind(Bindings.concat(ship_name, ", " , ship_email));
        
        this.book_no = book_no;
        this.book_date = book_date;
        this.ship_name = ship_name;
        this.ship_address = ship_address;
        this.ship_brgy = ship_brgy;
        this.ship_city = ship_city;
        this.ship_province = ship_province;
        this.ship_zip = ship_zip;
        this.ship_email = ship_email;
        this.ship_contact = ship_contact;
        this.rec_name = rec_name;
        this.rec_address = rec_address;
        this.rec_brgy = rec_brgy;
        this.rec_city = rec_city;
        this.rec_province = rec_province;
        this.rec_zip = rec_zip;
        this.rec_contact = rec_contact;
        this.serv_type = serv_type;
        this.serv_box = serv_box;
        this.serv_quantity = serv_quantity;
        this.serv_insurance = serv_insurance;
        this.serv_liability = serv_liability;
        this.status = status;
        this.ref_no = ref_no;
    }

    /**
     * @return the shipper_address
     */
    public ReadOnlyStringWrapper getShipper_address() {
        return shipper_address;
    }

    /**
     * @param shipper_address the shipper_address to set
     */
    public void setShipper_address(ReadOnlyStringWrapper shipper_address) {
        this.shipper_address = shipper_address;
    }

    /**
     * @return the receiver_address
     */
    public ReadOnlyStringWrapper getReceiver_address() {
        return receiver_address;
    }

    /**
     * @param receiver_address the receiver_address to set
     */
    public void setReceiver_address(ReadOnlyStringWrapper receiver_address) {
        this.receiver_address = receiver_address;
    }
    
    public ReadOnlyStringWrapper getContact_person() {
        return contact_person;
    }

    /**
     * @param contact_person
     */
    public void setContact_person(ReadOnlyStringWrapper contact_person) {
        this.contact_person = contact_person;
    }

    /**
     * @return the book_no
     */
    public String getBook_no() {
        return book_no;
    }

    /**
     * @param book_no the book_no to set
     */
    public void setBook_no(String book_no) {
        this.book_no = book_no;
    }

    /**
     * @return the book_date
     */
    public String getBook_date() {
        return book_date;
    }

    /**
     * @param book_date the book_date to set
     */
    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    /**
     * @return the ship_name
     */
    public String getShip_name() {
        return ship_name;
    }

    /**
     * @param ship_name the ship_name to set
     */
    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    /**
     * @return the ship_address
     */
    public String getShip_address() {
        return ship_address;
    }

    /**
     * @param ship_address the ship_address to set
     */
    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }

    /**
     * @return the ship_brgy
     */
    public String getShip_brgy() {
        return ship_brgy;
    }

    /**
     * @param ship_brgy the ship_brgy to set
     */
    public void setShip_brgy(String ship_brgy) {
        this.ship_brgy = ship_brgy;
    }

    /**
     * @return the ship_city
     */
    public String getShip_city() {
        return ship_city;
    }

    /**
     * @param ship_city the ship_city to set
     */
    public void setShip_city(String ship_city) {
        this.ship_city = ship_city;
    }

    /**
     * @return the ship_province
     */
    public String getShip_province() {
        return ship_province;
    }

    /**
     * @param ship_province the ship_province to set
     */
    public void setShip_province(String ship_province) {
        this.ship_province = ship_province;
    }

    /**
     * @return the ship_zip
     */
    public String getShip_zip() {
        return ship_zip;
    }

    /**
     * @param ship_zip the ship_zip to set
     */
    public void setShip_zip(String ship_zip) {
        this.ship_zip = ship_zip;
    }

    /**
     * @return the ship_email
     */
    public String getShip_email() {
        return ship_email;
    }

    /**
     * @param ship_email the ship_email to set
     */
    public void setShip_email(String ship_email) {
        this.ship_email = ship_email;
    }

    /**
     * @return the ship_contact
     */
    public String getShip_contact() {
        return ship_contact;
    }

    /**
     * @param ship_contact the ship_contact to set
     */
    public void setShip_contact(String ship_contact) {
        this.ship_contact = ship_contact;
    }

    /**
     * @return the rec_name
     */
    public String getRec_name() {
        return rec_name;
    }

    /**
     * @param rec_name the rec_name to set
     */
    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    /**
     * @return the rec_address
     */
    public String getRec_address() {
        return rec_address;
    }

    /**
     * @param rec_address the rec_address to set
     */
    public void setRec_address(String rec_address) {
        this.rec_address = rec_address;
    }

    /**
     * @return the rec_brgy
     */
    public String getRec_brgy() {
        return rec_brgy;
    }

    /**
     * @param rec_brgy the rec_brgy to set
     */
    public void setRec_brgy(String rec_brgy) {
        this.rec_brgy = rec_brgy;
    }

    /**
     * @return the rec_city
     */
    public String getRec_city() {
        return rec_city;
    }

    /**
     * @param rec_city the rec_city to set
     */
    public void setRec_city(String rec_city) {
        this.rec_city = rec_city;
    }

    /**
     * @return the rec_province
     */
    public String getRec_province() {
        return rec_province;
    }

    /**
     * @param rec_province the rec_province to set
     */
    public void setRec_province(String rec_province) {
        this.rec_province = rec_province;
    }

    /**
     * @return the rec_zip
     */
    public String getRec_zip() {
        return rec_zip;
    }

    /**
     * @param rec_zip the rec_zip to set
     */
    public void setRec_zip(String rec_zip) {
        this.rec_zip = rec_zip;
    }

    /**
     * @return the rec_contact
     */
    public String getRec_contact() {
        return rec_contact;
    }

    /**
     * @param rec_contact the rec_contact to set
     */
    public void setRec_contact(String rec_contact) {
        this.rec_contact = rec_contact;
    }

    /**
     * @return the serv_type
     */
    public String getServ_type() {
        return serv_type;
    }

    /**
     * @param serv_type the serv_type to set
     */
    public void setServ_type(String serv_type) {
        this.serv_type = serv_type;
    }

    /**
     * @return the serv_box
     */
    public String getServ_box() {
        return serv_box;
    }

    /**
     * @param serv_box the serv_box to set
     */
    public void setServ_box(String serv_box) {
        this.serv_box = serv_box;
    }

    /**
     * @return the serv_quantity
     */
    public String getServ_quantity() {
        return serv_quantity;
    }

    /**
     * @param serv_quantity the serv_quantity to set
     */
    public void setServ_quantity(String serv_quantity) {
        this.serv_quantity = serv_quantity;
    }

    /**
     * @return the serv_insurance
     */
    public String getServ_insurance() {
        return serv_insurance;
    }

    /**
     * @param serv_insurance the serv_insurance to set
     */
    public void setServ_insurance(String serv_insurance) {
        this.serv_insurance = serv_insurance;
    }

    /**
     * @return the serv_liability
     */
    public String getServ_liability() {
        return serv_liability;
    }

    /**
     * @param serv_liability the serv_liability to set
     */
    public void setServ_liability(String serv_liability) {
        this.serv_liability = serv_liability;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the ref_no
     */
    public String getRef_no() {
        return ref_no;
    }

    /**
     * @param ref_no the ref_no to set
     */
    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

   
    
}
