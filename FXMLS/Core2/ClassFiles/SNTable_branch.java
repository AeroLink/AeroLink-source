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
public class SNTable_branch {

    public SimpleStringProperty branch_code;
    public SimpleStringProperty country;
    public SimpleStringProperty region;
    public SimpleStringProperty city;
    public SimpleStringProperty barangay;
    public SimpleStringProperty street;
    public SimpleStringProperty address;
    public SimpleStringProperty province;
    public SimpleStringProperty zipcode;
    public SimpleStringProperty email;
    public SimpleStringProperty number;
    public SimpleStringProperty personnel_assign;
    public SimpleStringProperty ex;
    public SimpleStringProperty pu;
    public SimpleStringProperty dtd;

    public SNTable_branch(String branch_code, String country,
            String region, String city, String barangay,
            String street, String address, String province,
            String zipcode, String email, String number,
            String personnel_assign, String ex,
            String pu, String dtd) {

        this.branch_code = new SimpleStringProperty(branch_code);
        this.country = new SimpleStringProperty(country);
        this.region = new SimpleStringProperty(region);
        this.city = new SimpleStringProperty(city);
        this.barangay = new SimpleStringProperty(barangay);
        this.street = new SimpleStringProperty(street);
        this.address = new SimpleStringProperty(address);
        this.province = new SimpleStringProperty(province);
        this.zipcode = new SimpleStringProperty(zipcode);
        this.email = new SimpleStringProperty(email);
        this.number = new SimpleStringProperty(number);
        this.personnel_assign = new SimpleStringProperty(personnel_assign);
        this.ex = new SimpleStringProperty(ex);
        this.pu = new SimpleStringProperty(pu);
        this.dtd = new SimpleStringProperty(dtd);
    }
}
