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
public class HR3_table_schedule_record {

   // public SimpleStringProperty id;
    public SimpleStringProperty employee_code;
    public SimpleStringProperty name;
    public SimpleStringProperty department;
    public SimpleStringProperty position;
    public SimpleStringProperty monday;
    public SimpleStringProperty tuesday;
    public SimpleStringProperty wednesday;
    public SimpleStringProperty thursday;
    public SimpleStringProperty friday;

    public HR3_table_schedule_record( String a, String b, String c, String c1, String d, String e, String f, String g, String h) {
        
        //this.id = new SimpleStringProperty(z);
        this.employee_code = new SimpleStringProperty(a);
        this.name = new SimpleStringProperty(b);
        this.department = new SimpleStringProperty(c);
        this.position = new SimpleStringProperty(c1);
        this.monday = new SimpleStringProperty(d);
        this.tuesday = new SimpleStringProperty(e);
        this.wednesday = new SimpleStringProperty(f);
        this.thursday = new SimpleStringProperty(g);
        this.friday = new SimpleStringProperty(h);

    }
}
