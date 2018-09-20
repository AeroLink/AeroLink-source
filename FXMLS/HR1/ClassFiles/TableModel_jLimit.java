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
public class TableModel_jLimit {
    
    public StringProperty id;
    public StringProperty job_id;
    public StringProperty job_open;
    public StringProperty job_title;

    public TableModel_jLimit(String id, String job_id, String job_open, String job_title) {
        this.id = new SimpleStringProperty(id);
        this.job_id = new SimpleStringProperty(job_id);
        this.job_open = new SimpleStringProperty(job_open);
        this.job_title = new SimpleStringProperty(job_title);
    }
    
    
    
}
