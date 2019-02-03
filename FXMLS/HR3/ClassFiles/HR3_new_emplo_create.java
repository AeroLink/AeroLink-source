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
public class HR3_new_emplo_create {

    public SimpleStringProperty employee_code;
    public SimpleStringProperty name;
    public SimpleStringProperty title;
    public SimpleStringProperty dept;

    

    public HR3_new_emplo_create( String a, String b, String c, String d) {

        
        this.employee_code = new SimpleStringProperty(a);
        this.name = new SimpleStringProperty(b);
        this.title = new SimpleStringProperty(c);
        this.dept = new SimpleStringProperty(d);
       
      

    }
}
