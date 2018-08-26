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
public class TimeSheetTable {
    String ID,Emp_Name,Position,Department,Date,Total_Hours,Absent,Undertime,Overtime;
    
    
    public TimeSheetTable(String ID, String Emp_Name, String Position, String Department, String Date, String Total_Hours, String Absent, String Undertime, String Overtime){
        this.ID = ID;
        this.Emp_Name = Emp_Name;
        this.Position = Position;
        this.Department = Department;
        this.Date = Date;
        this.Total_Hours = Total_Hours;
        this.Absent = Absent;
        this.Undertime = Undertime;
        this.Overtime = Overtime;
    }
    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    
    public String getEmp_Name(){
        return Emp_Name;
    }
    public void setEmp_Name(String Emp_Name){
        this.Emp_Name = Emp_Name;
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
    
     public String getTotal_Hours(){
        return Total_Hours;
    }
    public void setTotal_Hours(String Total_Hours){
        this.Total_Hours = Total_Hours;
    }
        
      public String getAbsent(){
        return Absent;
    }
    public void setAbsent(String Absent){
        this.Absent = Absent;
    }   

     public String getUndertime(){
        return Undertime;
    }
    public void setUndertime(String Undertime){
        this.Undertime = Undertime;
    }
        
      public String getOvertime(){
        return Overtime;
    }
    public void setOvertime(String Overtime){
        this.Overtime = Overtime;
    }   

}
