/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

/**
 *
 * @author my
 */
public class R_Shift_Update {
     String R_ID,Date, Employee, Monday, Tuesday, Wednesday, Thursday, Friday;
    public R_Shift_Update(String R_ID, String Date, String Employee, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday){
    this.R_ID = R_ID;
    this.Date = Date;
    this.Employee = Employee;
    this.Monday = Monday;
    this.Tuesday = Tuesday;
    this.Wednesday = Wednesday;
    this.Thursday = Thursday;
    this.Friday = Friday;
    
    }
     public String getR_ID(){
        return R_ID;
    }
     public void setR_ID(String R_ID){
        this.R_ID = R_ID;
    }
       public String getDate(){
        return Date;
    }
     public void setDate(String Date){
        this.Date = Date;
    }
     
     
      public String getEmployee(){
        return Employee;
    }
     public void setEmployee(String Employee){
        this.Employee = Employee;
    }
     
    
      public String getMonday(){
        return Monday;
    }
     public void setMonday(String Monday){
        this.Monday = Monday;
    }
     
      public String getTuesday(){
        return Tuesday;
    }
     public void setTuesday(String Tuesday){
        this.Tuesday = Tuesday;
    }
     
      public String getWednesday(){
        return Wednesday;
    }
     public void setWednesday(String Wednesday){
        this.Wednesday = Wednesday;
    }
      public String getThursday(){
        return Thursday;
    }
     public void setThursday(String Thursday){
        this.Thursday = Thursday;
    }
      public String getFriday(){
        return Friday;
    }
     public void setFriday(String Friday){
        this.Friday = Friday;
     }
}
