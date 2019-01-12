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
public class Leave_ManagementClass {
    
    String ID,Emp_Name,Position,Type_of_Leave,Range_of_Leave,Date_Start,Date_End,Status;
    
    
    public Leave_ManagementClass(String ID, String Emp_Name, String Position, String Type_of_Leave,String Range_of_Leave, String Date_Start,String Date_End,String Status){
        this.ID = ID;
        this.Emp_Name = Emp_Name;
        this.Position = Position;
        this.Type_of_Leave = Type_of_Leave;
        this.Range_of_Leave = Range_of_Leave;
        this.Date_Start = Date_Start;
        this.Date_End = Date_End;
        this.Status = Status;
        
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
     public String getType_of_Leave(){
        return Type_of_Leave;
    }
    public void setType_of_Leave(String Type_of_Leave){
        this.Type_of_Leave = Type_of_Leave;
    }
    public String getRange_of_Leave(){
        return Range_of_Leave;
    }
    public void setRange_of_Leave(String Range_of_Leave){
        this.Range_of_Leave = Range_of_Leave;
    }
    public String getDate_Start(){
        return Date_Start;
    }
    public void setDate_Start(String Date_Start){
        this.Date_Start = Date_Start;
    }
    public String getDate_End(){
        return Date_End;
    }
    public void setDate_End(String Date_End){
        this.Date_End = Date_End;
    }
     public String getStatus(){
        return Status;
    }
    public void setStatus(String Status){
        this.Status = Status;
    }
}
