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
public class Claim_PendingClass {
    String ID ,Date, Payroll_reference, Name, Department, Position, Payroll_type, Rewards;         
  
    
    public Claim_PendingClass(String ID, String Date, String Payroll_reference, String Name,String Department, String Position, String Payroll_type, String Rewards){
       this.ID = ID;
       this.Date = Date;
       this.Payroll_reference = Payroll_reference;
       this.Name = Name;
       this.Department= Department;
       this.Position = Position;
       this.Payroll_type = Payroll_type;
       this.Rewards = Rewards;
    }

    

   
     public String getID(){
            return ID;
        }
      public void setID(String ID){
        this.ID = ID;
    }

    public String getDate(){
        return Date;
    }
    
     public void setDate(String Date){
        this.Date = Date;
    }
   
    public String getPayroll_reference(){
        return Payroll_reference;
    }
     public void setPayroll_reference(String Payroll_reference){
        this.Payroll_reference = Payroll_reference;
    }
  
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
   
    public String getDepartment(){
        return Department;
    }
     public void setDepartment(String Department){
        this.Department = Department;
    }

    public String getPosition(){
        return Position;
    }
     public void setPosition(String Position){
        this.Position = Position;
    }
     public String getPayroll_type(){
        return Payroll_type;
    }
     public void setPayroll_type(String Payroll_type){
        this.Payroll_type = Payroll_type;
    }
      public String getRewards(){
        return Rewards;
    }
     public void setRewards(String Rewards){
        this.Rewards = Rewards;
    }
    
}
