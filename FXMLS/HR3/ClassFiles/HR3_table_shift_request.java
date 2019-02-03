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
public class HR3_table_shift_request {

    public SimpleStringProperty employee_code;
    public SimpleStringProperty employee_name;
    public SimpleStringProperty position;
    public SimpleStringProperty department;
    public SimpleStringProperty date;
    public SimpleStringProperty schedule;
    public SimpleStringProperty reason;
    public SimpleStringProperty attachment;

    public HR3_table_shift_request(String a, String b, String c, String d, String e, String f, String g, String h) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(a);
        this.employee_name = new SimpleStringProperty(b);
        this.position = new SimpleStringProperty(c);
        this.department = new SimpleStringProperty(d);
        this.date = new SimpleStringProperty(e);
        this.schedule = new SimpleStringProperty(f);
        this.reason = new SimpleStringProperty(g);
        this.attachment = new SimpleStringProperty(h);

    }
}
