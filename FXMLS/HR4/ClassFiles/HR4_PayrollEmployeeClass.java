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
public class HR4_PayrollEmployeeClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    
    public HR4_PayrollEmployeeClass(String payroll_code,String employee_code,String Fullname,String job_id,String dept_id){
    this.a = new SimpleStringProperty(payroll_code);
    this.b = new SimpleStringProperty(employee_code);
    this.c = new SimpleStringProperty(Fullname);
    this.d = new SimpleStringProperty(job_id);
    this.e = new SimpleStringProperty(dept_id);
    
    }
}
