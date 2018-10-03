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
public class TableModel_NewHireWE {
    
    public StringProperty position;
    public StringProperty duration;
    public StringProperty status;
    public StringProperty company;

    public TableModel_NewHireWE(String title, String duration, String type, String conducted_by) {
        this.position = new SimpleStringProperty(title);
        this.duration = new SimpleStringProperty(duration);
        this.status = new SimpleStringProperty(type);
        this.company = new SimpleStringProperty(conducted_by);
    }
    
    
    
}
