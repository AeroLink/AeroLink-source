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
public class HR3_Request_Edwin {
     String or_no, date, department, expenses, totalamount, particulars;         
  
    
    public HR3_Request_Edwin(String or_no, String date, String department, String expenses,String totalamount, String particulars){
       this.or_no = or_no;
       this.date = date;
       this.department = department;
       this.expenses = expenses;
       this.totalamount= totalamount;
       this.particulars = particulars;
       
    }

    

   
     public String getor_no(){
            return or_no;
        }
      public void setor_no(String or_no){
        this.or_no = or_no;
    }

    public String getdate(){
        return date;
    }
    
     public void setdate(String date){
        this.date = date;
    }
   
    public String getdepartment(){
        return department;
    }
     public void setdepartment(String department){
        this.department = department;
    }
  
    public String getexpenses(){
        return expenses;
    }
    public void setexpenses(String expenses){
        this.expenses = expenses;
    }
   
    public String gettotalamount(){
        return totalamount;
    }
     public void settotalamount(String totalamount){
        this.totalamount = totalamount;
    }

    public String getparticulars(){
        return particulars;
    }
     public void setparticulars(String particulars){
        this.particulars = particulars;
    }

   
    }
   

