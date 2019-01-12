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
public class HR3_CreatingScheduleClass {
     String ID, Name, Position, Monday, Tuesday, Wednesday, Thursday, Friday;
    public HR3_CreatingScheduleClass(String ID, String Name, String Position, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday){
    this.ID = ID;
    this.Name = Name;
    this.Position = Position;
    this.Monday = Monday;
    this.Tuesday = Tuesday;
    this.Wednesday = Wednesday;
    this.Thursday = Thursday;
    this.Friday = Friday;
    
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
