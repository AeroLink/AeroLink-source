/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_Jobs{
    
    public StringProperty id;
    public StringProperty department;
    public StringProperty title;
    public StringProperty description;
    public StringProperty classification;
    public StringProperty designation;

    public TableModel_Jobs(String id, String department, String title, String description, String classification, String designation) {
        this.id = new SimpleStringProperty(id);
        this.department = new SimpleStringProperty(department);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.classification = new SimpleStringProperty(classification);
        this.designation = new SimpleStringProperty(designation);
    }

    
}
