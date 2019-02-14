/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jaeeeee
 */
public class HR4_EmpInfoFillClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public SimpleStringProperty h;
    public SimpleStringProperty i;
    public SimpleStringProperty j;
    public SimpleStringProperty k;
    public SimpleStringProperty l;
    public SimpleStringProperty m;
    public SimpleStringProperty n;
    
    public HR4_EmpInfoFillClass(String employee_code,String lastname, String firstname,String middlename,String suffix, String gender,String civil_status, String weight, String height,String contact_no,String email,String address,String dateofbirth,String placeofbirth){
        this.a = new SimpleStringProperty(employee_code);
        this.b = new SimpleStringProperty(lastname);
        this.c = new SimpleStringProperty(firstname);
        this.d = new SimpleStringProperty(middlename);
        this.e = new SimpleStringProperty(suffix);
        this.f = new SimpleStringProperty(gender);
        this.g = new SimpleStringProperty(civil_status);
        this.h = new SimpleStringProperty(weight);
        this.i = new SimpleStringProperty(height);
        this.j = new SimpleStringProperty(contact_no);
        this.k = new SimpleStringProperty(email);
        this.l = new SimpleStringProperty(address);
        this.m = new SimpleStringProperty(dateofbirth);
        this.n = new SimpleStringProperty(placeofbirth);
    }
    
}
