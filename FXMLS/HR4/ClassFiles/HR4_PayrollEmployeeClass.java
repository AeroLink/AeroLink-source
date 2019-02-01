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
    public HR4_PayrollEmployeeClass(String payroll_code,String employee_code){
    this.a = new SimpleStringProperty(payroll_code);
    this.b = new SimpleStringProperty(employee_code);
    }
}
