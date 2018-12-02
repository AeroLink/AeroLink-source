/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_EmployeeList {
    
    public StringProperty empCode;
    public StringProperty name;
    public StringProperty email;
    public StringProperty contact;
    public StringProperty job;
    public StringProperty status;
    
    public TableModel_EmployeeList(String empCode, String name, String email, String contact, String job, String status) {
        this.empCode = new SimpleStringProperty(empCode);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.contact = new SimpleStringProperty(contact);
        this.job = new SimpleStringProperty(job);
        this.status = new SimpleStringProperty(status);
    }
    
    
    
}
