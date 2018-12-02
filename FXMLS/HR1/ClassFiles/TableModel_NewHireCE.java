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
public class TableModel_NewHireCE {
    
    public StringProperty title;
    public StringProperty date_acquired;
    public StringProperty expiration;

    public TableModel_NewHireCE(String title, String date_acquired, String expiration) {
        this.title = new SimpleStringProperty(title);
        this.date_acquired = new SimpleStringProperty(date_acquired);
        this.expiration = new SimpleStringProperty(expiration);
    }
    
    
    
}
