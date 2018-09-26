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
public class TableModel_Schedules {

    public StringProperty scheduled_id;
    public StringProperty scheduled_purpose;
    public StringProperty scheduled_date;
    public StringProperty combination;
    public StringProperty status;
    public StringProperty remarks;

    public TableModel_Schedules(String scheduled_id, String scheduled_purpose, String scheduled_date, String c, String status) {
        this.scheduled_id = new SimpleStringProperty(scheduled_id);
        this.scheduled_purpose = new SimpleStringProperty(scheduled_purpose);
        this.scheduled_date = new SimpleStringProperty(scheduled_date);
        this.combination = new SimpleStringProperty(c);
        this.status = new SimpleStringProperty(status);
    }

    public TableModel_Schedules(String scheduled_id, String scheduled_purpose, String scheduled_date, String c, String status, String remarks) {
        this.scheduled_id = new SimpleStringProperty(scheduled_id);
        this.scheduled_purpose = new SimpleStringProperty(scheduled_purpose);
        this.scheduled_date = new SimpleStringProperty(scheduled_date);
        this.combination = new SimpleStringProperty(c);
        this.status = new SimpleStringProperty(status);
        this.remarks = new SimpleStringProperty(remarks);
    }

}
