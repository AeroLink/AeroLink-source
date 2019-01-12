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
public class PendingClass {
     String Description ,Amount, Total_Earnings, Gross_Salary, Details, Value, Descrption_new, Amount_new, Total_Deduction, Gross_Salary_new, Net_Amount;         
  
    
    public PendingClass(String Description, String Amount, String Total_Earnings, String Gross_Salary,String Details, String Value, String Descrption_new, String Amount_new, String Total_Deduction, String Gross_Salary_new, String Net_Amount){
       this.Description = Description;
       this.Amount = Amount;
       this.Total_Earnings = Total_Earnings;
       this. Gross_Salary =  Gross_Salary;
       this.Details= Details;
       this.Value = Value;
       this.Descrption_new = Descrption_new;
       this.Amount_new = Amount_new;
       this.Total_Deduction = Total_Deduction;
       this.Gross_Salary_new = Gross_Salary_new;
       this.Net_Amount = Net_Amount;
    }

    

   
     public String getDescription(){
            return Description;
        }
      public void setDescription(String Description){
        this.Description = Description;
    }

    public String getAmount(){
        return Amount;
    }
    
     public void setAmount(String Amount){
        this.Amount = Amount;
    }
   
    public String getTotal_Earnings(){
        return Total_Earnings;
    }
     public void setTotal_Earnings(String Total_Earnings){
        this.Total_Earnings = Total_Earnings;
    }
  
    public String getGross_Salary(){
        return Gross_Salary;
    }
    public void setGross_Salary(String Gross_Salary){
        this.Gross_Salary = Gross_Salary;
    }
   
    public String getDetails(){
        return Details;
    }
     public void setDetails(String Details){
        this.Details = Details;
    }

    public String getValue(){
        return Value;
    }
     public void setValue(String Value){
        this.Value = Value;
    }
     public String getDescrption_new(){
        return Descrption_new;
    }
     public void setDescription_new(String Descrption_new){
        this.Descrption_new = Descrption_new;
    }
      public String getAmount_new(){
        return Amount_new;
    }
     public void setAmount_new(String Amount_new){
        this.Amount_new = Amount_new;
    }
   
      public String getTotal_Deduction(){
        return Total_Deduction;
    }
     public void setTotal_Deduction(String Total_Deduction){
        this.Total_Deduction = Total_Deduction;
    }
      public String getGross_Salary_new(){
        return Gross_Salary_new;
    }
     public void setGross_Salary_new(String Gross_Salary_new){
        this.Gross_Salary_new = Gross_Salary_new;
    }
      public String getNet_Amount(){
        return Net_Amount;
    }
     public void setNet_Amount(String Net_Amount){
        this.Net_Amount = Net_Amount;
    }
    
}
