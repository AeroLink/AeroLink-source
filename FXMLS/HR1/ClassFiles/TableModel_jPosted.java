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
public class TableModel_jPosted {
    
    public StringProperty id;
    public StringProperty jPosted_id;
    public StringProperty job_title;
    public StringProperty salary;
    public StringProperty status;
    public StringProperty publish_on;
    public StringProperty until;
    public StringProperty postingDate;

    public TableModel_jPosted(String id, String jPosted, String salary, String status, String publish_on, String until, String pd, String jt) {
        this.id = new SimpleStringProperty(id);
        this.jPosted_id = new SimpleStringProperty(jPosted);
        this.salary = new SimpleStringProperty(salary);
        this.status = new SimpleStringProperty(status);
        this.publish_on = new SimpleStringProperty(publish_on);
        this.until = new SimpleStringProperty(until);
        this.postingDate = new SimpleStringProperty(pd);
        this.job_title = new SimpleStringProperty(jt);
    }
    
    
    
    
    
    
}
