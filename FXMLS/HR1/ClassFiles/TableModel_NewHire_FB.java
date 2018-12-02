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
public class TableModel_NewHire_FB {

    public StringProperty complete_name;
    public StringProperty relationship;
    public StringProperty contact_number;

    public TableModel_NewHire_FB(String complete_name, String relationship, String contact_number) {
        this.complete_name = new SimpleStringProperty(complete_name);
        this.relationship = new SimpleStringProperty(relationship);
        this.contact_number = new SimpleStringProperty(contact_number);
    }
    
    
    
}
