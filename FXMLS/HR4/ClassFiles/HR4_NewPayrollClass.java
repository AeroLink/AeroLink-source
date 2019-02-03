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
public class HR4_NewPayrollClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public HR4_NewPayrollClass(String payroll_code,String start_date,String end_date,String total_salaries,String dept_id){
     this.a = new SimpleStringProperty(payroll_code);
        this.b = new SimpleStringProperty(start_date);
        this.c = new SimpleStringProperty(end_date);
        this.d = new SimpleStringProperty(total_salaries);
        this.e = new SimpleStringProperty(dept_id);
        
    }
    
}
