/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author my
 */
public class ClaimClass {
    public SimpleStringProperty employee_code;
    public SimpleStringProperty department;
    public SimpleStringProperty date;
    public SimpleStringProperty amount;
    public SimpleStringProperty description;
    public SimpleStringProperty deduction;
    public SimpleStringProperty earnings;
    public SimpleStringProperty salary;
    public SimpleStringProperty net_amount;

    public ClaimClass(String a, String  b, String c, String d, String e, String f, String  g, String h, String i) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(a);
        this.department = new SimpleStringProperty(b);
        this.date = new SimpleStringProperty(c);
        this.amount = new SimpleStringProperty(d);
        this.description = new SimpleStringProperty(e);
        this.deduction = new SimpleStringProperty(f);
        this.earnings = new SimpleStringProperty(g);
        this.salary = new SimpleStringProperty(h);
        this.net_amount = new SimpleStringProperty(i);
        

    }
}
