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
public class Reimbursement_RequestClass {
     String RequestID,Date,Department,Recieve_php,Expenses,Total_Amount,Particulars,Status,Cash_Return,Amount_Cleared;
    
    
    public Reimbursement_RequestClass(String RequestID, String Date, String Department, String Recieve_php,String Expenses, String Total_Amount, String Particulars, String Status, String Cash_Return, String Amount_Cleared){
        this.RequestID = RequestID;
        this.Date = Date;
        this.Department = Department;
        this.Recieve_php = Recieve_php;
        this.Expenses = Expenses;
        this.Total_Amount = Total_Amount;
        this.Particulars = Particulars;
        this.Status = Status;
        this.Cash_Return = Cash_Return;
        this.Amount_Cleared = Amount_Cleared;
        
    }
    public String getRequestID(){
        return RequestID;
    }
    public void setRequestID(String RequestID){
        this.RequestID = RequestID;
    }
    public String getDate(){
        return Date;
    }
    public void Date(String Date){
        this.Date = Date;
    
    }
   
    public String getDepartment(){
        return Department;
    }
    public void setDepartment(String Department){
        this.Department = Department;
        
    }
     public String getRecieve_php(){
        return Recieve_php;
    }
    public void setRecieve_php(String Recieve_php){
        this.Recieve_php = Recieve_php;
    }
    public String getExpenses(){
        return Expenses;
    }
    public void setExpenses(String Expenses){
        this.Expenses = Expenses;
    }
    public String getTotal_Amount(){
        return Total_Amount;
        
    }
    
    public void setTotal_Amount(String Total_Amount){
        this.Total_Amount = Total_Amount;
    }
    public String getParticulars(){
        return Particulars;
    }
    public void setParticulars(String Particulars){
        this.Particulars = Particulars;
    }
    
    public String getStatus(){
        return Status;
    }
    public void setStatus(String Status){
        this.Status = Status;
    }
    public String getCash_Return(){
        return Cash_Return;
        
    }
    
    public void setCash_Return(String Cash_Return){
        this.Cash_Return = Cash_Return;
    }
    public String getAmount_Cleared(){
        return Amount_Cleared;
    }
    public void setAmount_Cleared(String Amount_Cleared){
        this.Amount_Cleared = Amount_Cleared;
    }
}
