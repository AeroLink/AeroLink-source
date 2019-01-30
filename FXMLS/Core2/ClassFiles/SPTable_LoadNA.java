/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JPEG
 */
public class SPTable_LoadNA {

    public SimpleStringProperty provider_name;
    public SimpleStringProperty provider_address;
    public SimpleStringProperty provider_email;
    public SimpleStringProperty provider_contact;
    public SimpleStringProperty code;
    public SimpleStringProperty country;
    public SimpleStringProperty status;
    // tao
    public SimpleStringProperty PEfullname;
    public SimpleStringProperty PEemail;

    public SPTable_LoadNA(
            String provider_name,
            String provider_address,
            String provider_contact,
            String provider_email,
            String country,
            String status,
            String code,
            String pefn,
            String peemail) {

        this.provider_name = new SimpleStringProperty(provider_name);
        this.provider_address = new SimpleStringProperty(provider_address);
        this.provider_contact = new SimpleStringProperty(provider_contact);
        this.provider_email = new SimpleStringProperty(provider_email);
        this.country = new SimpleStringProperty(country);
        this.status = new SimpleStringProperty(status);
        this.code = new SimpleStringProperty(code);
        this.PEfullname = new SimpleStringProperty(pefn);
        this.PEemail = new SimpleStringProperty(peemail);
    }
}
