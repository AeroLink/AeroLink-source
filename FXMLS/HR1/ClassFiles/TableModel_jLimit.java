/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import FXMLS.USM.Controllers.IUsers;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_jLimit extends RecursiveTreeObject<TableModel_jLimit>{
    
    public StringProperty id;
    public StringProperty job_id;
    public StringProperty job_open;
    public StringProperty job_title;
    public StringProperty status;
    public StringProperty opened_date;
    
    public TableModel_jLimit(String id, String job_id, String job_open, String job_title, String status, String open_date) {
        this.id = new SimpleStringProperty(id);
        this.job_id = new SimpleStringProperty(job_id);
        this.job_open = new SimpleStringProperty(job_open);
        this.job_title = new SimpleStringProperty(job_title);
        this.status = new SimpleStringProperty(status);
        this.opened_date = new SimpleStringProperty(open_date);
    }
    
    
    
}
