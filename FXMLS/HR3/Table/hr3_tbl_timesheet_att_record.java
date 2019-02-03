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
public class hr3_tbl_timesheet_att_record {

    public SimpleStringProperty employee_code;
    public SimpleStringProperty employee_name;
    public SimpleStringProperty position;
    public SimpleStringProperty date;
    public SimpleStringProperty time_in;
    public SimpleStringProperty time_out;
    public SimpleStringProperty late;
    public SimpleStringProperty overtime;
    public SimpleStringProperty undertime;
    public SimpleStringProperty daily_total_hours;

    public hr3_tbl_timesheet_att_record(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(a);
        this.employee_name = new SimpleStringProperty(b);
        this.position = new SimpleStringProperty(c);
        this.date = new SimpleStringProperty(d);
        this.time_in = new SimpleStringProperty(e);
        this.time_out = new SimpleStringProperty(f);
        this.late = new SimpleStringProperty(g);
        this.overtime = new SimpleStringProperty(h);
        this.undertime = new SimpleStringProperty(i);
        this.daily_total_hours = new SimpleStringProperty(j);

    }

}
