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
public class TableModel_NewHire {
    
    public StringProperty empCode;
    public StringProperty name;
    public StringProperty email;
    public StringProperty contact;

    public TableModel_NewHire(String empCode, String name, String email, String contact) {
        this.empCode = new SimpleStringProperty(empCode);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.contact = new SimpleStringProperty(contact);
    }
    
    
    
}
