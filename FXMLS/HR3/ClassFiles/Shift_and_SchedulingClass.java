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
public class Shift_and_SchedulingClass {
    
   String ID, Name, Position, Department, Date, Schedule;         
  
    
    public Shift_and_SchedulingClass(String ID, String Name, String Position, String Department,String Date, String Schedule){
       this.ID = ID;
       this.Name = Name;
       this.Position = Position;
       this.Department = Department;
       this.Date= Date;
       this.Schedule = Schedule;
       
    }

    

   
     public String getID(){
            return ID;
        }
      public void setID(String ID){
        this.ID = ID;
    }

    public String getName(){
        return Name;
    }
    
     public void setName(String Name){
        this.Name = Name;
    }
   
    public String getPosition(){
        return Position;
    }
     public void setPosition(String Position){
        this.Position = Position;
    }
  
    public String getDepartment(){
        return Department;
    }
    public void setDepartment(String Department){
        this.Department = Department;
    }
   
    public String getDate(){
        return Date;
    }
     public void setDate(String Date){
        this.Date = Date;
    }

    public String getSchedule(){
        return Schedule;
    }
     public void setSchedule(String Schedule){
        this.Schedule = Schedule;
    }
   
     
   
}

