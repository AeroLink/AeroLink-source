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
public class Leave_ManagementRequestClass {

    // public SimpleStringProperty ID;
    public SimpleStringProperty employee_code;
    public SimpleStringProperty employee_name;
    public SimpleStringProperty position;
    public SimpleStringProperty department;
    public SimpleStringProperty leave_name;
    public SimpleStringProperty range_leave;
    public SimpleStringProperty date;
    public SimpleStringProperty date_start;
    public SimpleStringProperty date_end;
    public SimpleStringProperty reason;
    public SimpleStringProperty attachment;

    public Leave_ManagementRequestClass(String a,String a1,String a2,String a3, String b,String c, String d,String e,String f,String g,String h) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(a);
        this.employee_name = new SimpleStringProperty(a1);
        this.position = new SimpleStringProperty(a2);
        this.department = new SimpleStringProperty(a3);
        this.leave_name = new SimpleStringProperty(b);
        this.range_leave = new SimpleStringProperty(c);
        this.date = new SimpleStringProperty(d);
        this.date_start = new SimpleStringProperty(e);
        this.date_end = new SimpleStringProperty(f);
        this.reason = new SimpleStringProperty(g);
        this.attachment = new SimpleStringProperty(h);
        

    }

}
