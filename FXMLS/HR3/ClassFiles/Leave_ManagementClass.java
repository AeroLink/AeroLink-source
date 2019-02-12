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
public class Leave_ManagementClass {
   // public SimpleStringProperty ID;
    // public SimpleStringProperty ID;
    public SimpleStringProperty emp_code;
    public SimpleStringProperty emp_name;
    public SimpleStringProperty emp_position;
    public SimpleStringProperty emp_department;
    public SimpleStringProperty emp_leave_name;
    public SimpleStringProperty emp_range_leave;
    public SimpleStringProperty emp_date;
    public SimpleStringProperty emp_date_start;
    public SimpleStringProperty emp_date_end;
    public SimpleStringProperty emp_reason;
    public SimpleStringProperty emp_attachment;
    public SimpleStringProperty emp_status;

    public Leave_ManagementClass(String a,String a1,String a2,String a3, String b,String c, String d,String e,String f,String g,String h, String s) {
        //this.ID = new SimpleStringProperty(ID);
        this.emp_code = new SimpleStringProperty(a);
        this.emp_name = new SimpleStringProperty(a1);
        this.emp_position = new SimpleStringProperty(a2);
        this.emp_department = new SimpleStringProperty(a3);
        this.emp_leave_name = new SimpleStringProperty(b);
        this.emp_range_leave = new SimpleStringProperty(c);
        this.emp_date = new SimpleStringProperty(d);
        this.emp_date_start = new SimpleStringProperty(e);
        this.emp_date_end = new SimpleStringProperty(f);
        this.emp_reason = new SimpleStringProperty(g);
        this.emp_attachment = new SimpleStringProperty(h);
        this.emp_status = new SimpleStringProperty(s);
        

    }
}
