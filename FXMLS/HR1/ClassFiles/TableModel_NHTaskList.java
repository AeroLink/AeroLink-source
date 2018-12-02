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
public class TableModel_NHTaskList {
    
    public StringProperty taskname;
    public StringProperty taskdesc;
    public StringProperty task_id;
    public StringProperty enddate;
    public StringProperty startdate;
    public StringProperty status;

    public TableModel_NHTaskList(String taskname, String taskdesc, String task_id, String enddate, String startdate, String status) {
        this.taskname = new SimpleStringProperty(taskname);
        this.taskdesc = new SimpleStringProperty(taskdesc);
        this.task_id = new SimpleStringProperty(task_id);
        this.enddate = new SimpleStringProperty(enddate);
        this.startdate = new SimpleStringProperty(startdate);
        this.status = new SimpleStringProperty(status);
    }
    
    
}
