/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jpeg
 */
public class BOOKING_info {

    public SimpleStringProperty book_no;
    public SimpleStringProperty ref_no;
    public SimpleStringProperty ship_name;
    public SimpleStringProperty ship_address;
    public SimpleStringProperty ship_brgy;
    public SimpleStringProperty ship_city;
    public SimpleStringProperty ship_province;
    public SimpleStringProperty ship_zip;
    public SimpleStringProperty ship_email;
    public SimpleStringProperty ship_contact;
    public SimpleStringProperty rec_name;
    public SimpleStringProperty rec_address;
    public SimpleStringProperty rec_brgy;
    public SimpleStringProperty rec_city;
    public SimpleStringProperty rec_province;
    public SimpleStringProperty rec_zip;
    public SimpleStringProperty rec_contact;
    public SimpleStringProperty serv_type;
    public SimpleStringProperty box;
    public SimpleStringProperty quantity;
    public SimpleStringProperty insurance;
    public SimpleStringProperty liability;
    public SimpleStringProperty status;
    public SimpleStringProperty book_date;

    public BOOKING_info(
            String book_no,
            String ref_no,
            String ship_name,
            String ship_address,
            String ship_brgy,
            String ship_city,
            String ship_province,
            String ship_zip,
            String ship_email,
            String ship_contact,
            String rec_name,
            String rec_address,
            String rec_brgy,
            String rec_city,
            String rec_province,
            String rec_zip,
            String rec_contact,
            String serv_type,
            String box,
            String quantity,
            String insurance,
            String liability,
            String status,
            String book_date) {

        this.book_no = new SimpleStringProperty(book_no);
        this.ref_no = new SimpleStringProperty(ref_no);
        this.ship_name = new SimpleStringProperty(ship_name);
        this.ship_address = new SimpleStringProperty(ship_address);
        this.ship_brgy = new SimpleStringProperty(ship_brgy);
        this.ship_city = new SimpleStringProperty(ship_city);
        this.ship_province = new SimpleStringProperty(ship_province);
        this.ship_zip = new SimpleStringProperty(ship_zip);
        this.ship_email = new SimpleStringProperty(ship_email);
        this.ship_contact = new SimpleStringProperty(ship_contact);
        this.rec_name = new SimpleStringProperty(rec_name);
        this.rec_address = new SimpleStringProperty(rec_address);
        this.rec_brgy = new SimpleStringProperty(rec_brgy);
        this.rec_city = new SimpleStringProperty(rec_city);
        this.rec_province = new SimpleStringProperty(rec_province);
        this.rec_zip = new SimpleStringProperty(rec_zip);
        this.rec_contact = new SimpleStringProperty(rec_contact);
        this.serv_type = new SimpleStringProperty(serv_type);
        this.box = new SimpleStringProperty(box);
        this.quantity = new SimpleStringProperty(quantity);
        this.insurance = new SimpleStringProperty(insurance);
        this.liability = new SimpleStringProperty(liability);
        this.status = new SimpleStringProperty(status);
        this.book_date = new SimpleStringProperty(book_date);
    }
}
