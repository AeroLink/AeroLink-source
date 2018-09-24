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
public class TableModel_PostedJob {
    
    public StringProperty id;
    public StringProperty job_title;
    public StringProperty postingDate;
    public StringProperty views;
    public StringProperty applicants;

    public TableModel_PostedJob(String id, String job_title, String postingDate, String views, String applicants) {
        this.id = new SimpleStringProperty(id);
        this.job_title = new SimpleStringProperty(job_title);
        this.postingDate = new SimpleStringProperty(postingDate);
        this.views = new SimpleStringProperty(views);
        this.applicants = new SimpleStringProperty(applicants);
    }


    
    
    
    
    
    
}
