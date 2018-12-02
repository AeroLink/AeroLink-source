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
public class TableModel_NewHireTSM {
    
    public StringProperty title;
    public StringProperty duration;
    public StringProperty type;
    public StringProperty conducted_by;

    public TableModel_NewHireTSM(String title, String duration, String type, String conducted_by) {
        this.title = new SimpleStringProperty(title);
        this.duration = new SimpleStringProperty(duration);
        this.type = new SimpleStringProperty(type);
        this.conducted_by = new SimpleStringProperty(conducted_by);
    }
    
    
    
}
