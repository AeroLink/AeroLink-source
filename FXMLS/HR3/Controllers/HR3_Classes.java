/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Controllers;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author my
 */
public class HR3_Classes {
    public SimpleStringProperty R_ID;
    public SimpleStringProperty Date;
    public SimpleStringProperty Employee;
    public SimpleStringProperty Monday;
    public SimpleStringProperty Tuesday;
    public SimpleStringProperty Wednesday;
    public SimpleStringProperty Thursday;
    public SimpleStringProperty Friday;
    public SimpleStringProperty Status;
        
        public HR3_Classes(String R_ID, String Date, String Employee, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday, String Status){
            this.R_ID = new SimpleStringProperty(R_ID);
            this.Employee = new SimpleStringProperty(Employee);
            this.Monday = new SimpleStringProperty(Monday);
            this.Tuesday = new SimpleStringProperty(Tuesday);
            this.Wednesday = new SimpleStringProperty(Wednesday);
            this.Thursday = new SimpleStringProperty(Thursday);
            this.Monday = new SimpleStringProperty(Monday);
            this.Friday = new SimpleStringProperty(Friday);
            this.Status = new SimpleStringProperty(Status);
        }
    
}
