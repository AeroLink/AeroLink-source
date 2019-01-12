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
public class HR3_TimeSheetRecordsClass {
     public SimpleStringProperty ID,Name, Datein, Timein ,Timeout, Overtime, Undertime;
     
     public HR3_TimeSheetRecordsClass(String ID, String Name, String Datein, String Timein, String Timeout, String Overtime, String Undertime){
    this.ID = new SimpleStringProperty(ID);
    this.Name = new SimpleStringProperty(Name);
    this.Datein = new SimpleStringProperty(Datein);
    this.Timein = new SimpleStringProperty(Timein);
    this.Timeout = new SimpleStringProperty(Timeout);
    this.Overtime = new SimpleStringProperty(Overtime);
    this.Undertime = new SimpleStringProperty(Undertime);

   
     } 
     
      
}
