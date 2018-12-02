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
 * @author RAVEN
 */
public class TableModel_table2 {

    public StringProperty Department;
    public StringProperty Employee_Name;
    public StringProperty Ratings;
    public StringProperty Award;
    public StringProperty Reward;
    public StringProperty Remarks;
    public StringProperty Date_Posted;

    public TableModel_table2(String Department, String Employee_Name, String Ratings, String Award, String Reward, String Remarks, String Date_Posted) {
        this.Department = new SimpleStringProperty(Department);
        this.Employee_Name = new SimpleStringProperty(Employee_Name);
        this.Ratings = new SimpleStringProperty(Ratings);
        this.Award = new SimpleStringProperty(Award);
        this.Reward = new SimpleStringProperty(Reward);
        this.Remarks = new SimpleStringProperty(Remarks);
        this.Date_Posted = new SimpleStringProperty(Date_Posted);
    }
}
