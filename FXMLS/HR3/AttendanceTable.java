/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

/**
 *
 * @author my
 */
public class AttendanceTable {
    String ID,First_Name,Middle_Name,Last_Name,Position,Department,Date,Time_In,Time_Out;
    
    
    public AttendanceTable(String ID, String First_Name, String Middle_Name, String Last_Name, String Position, String Department, String Date, String Time_In, String Time_Out){
        this.ID = ID;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Position = Position;
        this.Department = Department;
        this.Date = Date;
        this.Time_In = Time_In;
        this.Time_Out = Time_Out;
    }
    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    
    public String getFirst_Name(){
        return First_Name;
    }
    public void setFirst_Name(String First_Name){
        this.First_Name = First_Name;
    }
    
    public String getMiddle_Name(){
        return Middle_Name;
    }
    public void setMiddle_Name(String Middle_Name){
        this.Middle_Name = Middle_Name;
    }
    
    public String getLast_Name(){
        return Last_Name;
    }
    public void setLast_Name(String Last_Name){
        this.Last_Name = Last_Name;
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
    
     public String getTime_In(){
        return Time_In;
    }
    public void setTime_In(String Time_In){
        this.Time_In = Time_In;
    }
        
      public String getTime_Out(){
        return Time_Out;
    }
    public void setTime_Out(String Time_Out){
        this.Time_Out = Time_Out;
    }   
}
