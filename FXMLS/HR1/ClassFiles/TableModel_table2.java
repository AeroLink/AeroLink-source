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

    public StringProperty id;
    public StringProperty employee_code;
    public StringProperty employee_name;
    public StringProperty Ratings;
    public StringProperty award_id;
    public StringProperty reward_id;
    public StringProperty remarks;
    public StringProperty date_posted;

    public TableModel_table2(String Department,String Employee_code, String Employee_name, String Ratings, String Award, String Reward, String Remarks, String Date_Posted) {
        this.id = new SimpleStringProperty(Department);
        this.employee_code = new SimpleStringProperty(Employee_code);
        this.employee_name = new SimpleStringProperty(Employee_name);
        this.Ratings = new SimpleStringProperty(Ratings);
        this.award_id = new SimpleStringProperty(Award);
        this.reward_id = new SimpleStringProperty(Reward);
        this.remarks = new SimpleStringProperty(Remarks);
        this.date_posted = new SimpleStringProperty(Date_Posted);
    }
}
