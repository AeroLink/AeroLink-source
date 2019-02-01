/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Table;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author my
 */
public class hr3_tbl_schedule_records {

    public SimpleStringProperty employee_code;
    public SimpleStringProperty employee_name;
    public SimpleStringProperty position;
    public SimpleStringProperty monday;
    public SimpleStringProperty tuesday;
    public SimpleStringProperty wednesday;
    public SimpleStringProperty thursday;
    public SimpleStringProperty friday;


    public hr3_tbl_schedule_records(String a, String b, String c, String d, String e, String f, String g, String h) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(a);
        this.employee_name = new SimpleStringProperty(b);
        this.position = new SimpleStringProperty(c);
        this.monday = new SimpleStringProperty(d);
        this.tuesday = new SimpleStringProperty(e);
        this.wednesday = new SimpleStringProperty(f);
        this.thursday = new SimpleStringProperty(g);
        this.friday = new SimpleStringProperty(h);
        

    }

}
