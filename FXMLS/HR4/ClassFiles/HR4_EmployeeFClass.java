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
public class HR4_EmployeeFClass {
    public SimpleStringProperty cn;
    public SimpleStringProperty fn;
    public HR4_EmployeeFClass(String emp_code,String fnn){
     this.cn = new SimpleStringProperty(emp_code);
     this.fn = new SimpleStringProperty(fnn);
    }
}
