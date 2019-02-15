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
public class TableModel_table1 {
    public SimpleStringProperty Department;
    public SimpleStringProperty Employee_Code;
    public SimpleStringProperty Employee_Name;
    public SimpleStringProperty Ratings;
    
    public TableModel_table1(String Department, String Employee_Code, String Employee_Name, String Ratings) {
        this.Department = new SimpleStringProperty(Department);
        this.Employee_Code = new SimpleStringProperty(Employee_Code);
        this.Employee_Name = new SimpleStringProperty(Employee_Name);
        this.Ratings = new SimpleStringProperty(Ratings);
    }
}
