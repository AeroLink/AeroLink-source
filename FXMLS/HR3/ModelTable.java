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
public class ModelTable {
    String ID,Name,Position,Department,Schedule;
    
    
    public ModelTable(String ID, String Name, String Position, String Department, String Schedule){
        this.ID = ID;
        this.Name = Name;
        this.Position = Position;
        this.Department = Department;
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
    public void setStatus(String Status){
        this.Department = Department;
    }
    public String getSchedule(){
        return Schedule;
    }
    public void setSchedule(String Schedule){
        this.Schedule = Schedule;
    }
}
