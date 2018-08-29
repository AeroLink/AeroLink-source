/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jpeg
 */
public class ServiceProviderTableController {
    
    private StringProperty apid;
    private StringProperty company_name;
    private StringProperty contact_number;
    private StringProperty house_number;
    private StringProperty street;
    private StringProperty barangay;
    private StringProperty city_monicipality;
    private StringProperty zipcode;
    private StringProperty email;
    private StringProperty doortodoor;
    private StringProperty pickup;
    private StringProperty express;
    private StringProperty other;
    private StringProperty region;
    private StringProperty country;
    
    public ServiceProviderTableController(String apid, String company_name, String contact_number, String house_number, String street, String barangay, String city_monicipality, String zipcode, String email, String doortodoor, String pickup, String express, String other, String region, String country){
                
        this.apid = new SimpleStringProperty(apid);
        this.company_name = new SimpleStringProperty(company_name);
        this.contact_number = new SimpleStringProperty(contact_number);
        this.house_number = new SimpleStringProperty(house_number);
        this.street = new SimpleStringProperty(street);
        this.barangay = new SimpleStringProperty(barangay);
        this.city_monicipality = new SimpleStringProperty(city_monicipality);
        this.zipcode = new SimpleStringProperty(zipcode);
        this.email = new SimpleStringProperty(email);
        this.doortodoor = new SimpleStringProperty(doortodoor);
        this.pickup = new SimpleStringProperty(pickup);
        this.express = new SimpleStringProperty(express);
        this.other = new SimpleStringProperty(other);
        this.region = new SimpleStringProperty(region);
        this.country = new SimpleStringProperty(country);
    }

    public String getApid() {
        return apid.get();
    }

    public String getCompany_name() {
        return company_name.get();
    }

    public String getContact_number() {
        return contact_number.get();
    }

    public String getHouse_number() {
        return house_number.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getBarangay() {
        return barangay.get();
    }

    public String getCity_monicipality() {
        return city_monicipality.get();
    }

    public String getZipcode() {
        return zipcode.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getDoortodoor() {
        return doortodoor.get();
    }

    public String getPickup() {
        return pickup.get();
    }

    public String getExpress() {
        return express.get();
    }

    public String getOther() {
        return other.get();
    }

    public String getRegion() {
        return region.get();
    }

    public String getCountry() {
        return country.get();
    }

}
