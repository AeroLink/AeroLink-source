/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author my
 */
public class Leave_ManagementRequestClass {
     public SimpleStringProperty ID;
     public SimpleStringProperty employee_code;
     public SimpleStringProperty leave_id;
     public SimpleStringProperty Range_of_leave;
     public SimpleStringProperty Date_Start;
     public SimpleStringProperty Date_End;
     public SimpleStringProperty leave_name;
   
     
     public Leave_ManagementRequestClass(String ID, String employee_code, String leave_id, String Range_of_leave, String Date_Start, String Date_End, String leave_name) {
        this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(employee_code);
        this.leave_id = new SimpleStringProperty(leave_id);
        this.Range_of_leave = new SimpleStringProperty(Range_of_leave);
        this.Date_Start = new SimpleStringProperty(Date_Start);
        this.Date_End = new SimpleStringProperty(Date_End);
        this.leave_name = new SimpleStringProperty(leave_name);
       
}
     
}
