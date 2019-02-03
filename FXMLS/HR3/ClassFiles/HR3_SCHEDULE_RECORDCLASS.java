/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author my
 */
public class HR3_SCHEDULE_RECORDCLASS extends RecursiveTreeObject{
    public StringProperty id;
    public StringProperty employee_id;
    public StringProperty monday;
    public StringProperty tuesday_;
    public StringProperty wednesday;
    public StringProperty thursday;
    public StringProperty friday;
    
    public HR3_SCHEDULE_RECORDCLASS(String a, String b, String c, String d, String e, String f, String g ){
        this.id = new SimpleStringProperty(a);
        this.employee_id = new SimpleStringProperty(b);
        this.monday = new SimpleStringProperty(c);
        this.tuesday_ = new SimpleStringProperty(d);
        this.wednesday = new SimpleStringProperty(e);
        this.thursday = new SimpleStringProperty(f);
        this.friday = new SimpleStringProperty(g);
        
    }
    
}
