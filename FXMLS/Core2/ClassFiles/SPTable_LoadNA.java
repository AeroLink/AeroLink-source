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
public class SPTable_LoadNA{

    public SimpleStringProperty provider_name;
    public SimpleStringProperty provider_address;
    public SimpleStringProperty provider_contact;
     public SimpleStringProperty country;

    public SPTable_LoadNA(
            String provider_name,
            String provider_address,
            String provider_contact,
            String country) {

        this.provider_name = new SimpleStringProperty(provider_name);
        this.provider_address = new SimpleStringProperty(provider_address);
        this.provider_contact = new SimpleStringProperty(provider_contact);
        this.country = new SimpleStringProperty(country);
    }
}
