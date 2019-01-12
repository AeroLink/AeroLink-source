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
public class Time_and_AttendanceClass {
   
     String ID,Name,Datein,Timein,Timeout,Dateout,ElapseTime,Overtime,Undertime;
    
    
    public Time_and_AttendanceClass(String ID, String Name, String Datein, String Timein, String Timeout,String Dateout, String ElapseTime, String Overtime, String Undertime){
        this.ID = ID;
        this.Name = Name;
        this.Datein = Datein;
        this.Timein = Timein;
        this.Timeout = Timeout;
        this.Dateout = Dateout;
        this.ElapseTime = ElapseTime;
        this.Overtime = Overtime;
        this.Undertime = Undertime;
       
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
    
    public String getDatein(){
        return Datein;
    }
    public void setDatein(String Datein){
        this.Datein = Datein;
    }
    
    public String getTimein(){
        return Timein;
    }
    public void setTimein(String Timein){
        this.Timein = Timein;
    }
    
    
    public String getTimeout(){
        return Timeout;
    }
    public void setTimeout(String Timeout){
        this.Timeout = Timeout;
    }
    public String getDateout(){
        return Dateout;
    }
    public void setDateout(String Dateout){
        this.Dateout = Dateout;
    }
    
    public String getElapseTime(){
        return ElapseTime;
    }
    public void setElapseTime(String ElapseTime){
        this.ElapseTime = ElapseTime;
    }
    
    public String getOvertime(){
        return Overtime;
    }
    public void setOvertime(String Overtime){
        this.Overtime = Overtime;
    }
    
     public String getUndertime(){
        return Undertime;
    }
    public void setUndertime(String Undertime){
        this.Undertime = Undertime;
    }
    
}
