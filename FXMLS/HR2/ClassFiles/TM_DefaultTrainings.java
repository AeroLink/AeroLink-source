/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class TM_DefaultTrainings {
    
    public SimpleStringProperty dt_id, job_title, training_title;
    
    public TM_DefaultTrainings(String dt_id, String job_title, String training_title){
        this.dt_id = new SimpleStringProperty(dt_id);
        this.training_title = new SimpleStringProperty(training_title);
        this.job_title = new SimpleStringProperty(job_title);
    }
}
