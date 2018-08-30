/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JaeJae
 */
public class FINANCIAL_DVC_CLASS {
        
     private SimpleStringProperty dv_no; 
     private SimpleStringProperty date;
     private SimpleStringProperty claimants;
     private SimpleStringProperty particular;
     private SimpleStringProperty amount;
     
     public FINANCIAL_DVC_CLASS(String dv_no,String date,String claimants,String particular, String amount){
         this.dv_no = new SimpleStringProperty(dv_no);
         this.date = new SimpleStringProperty(date);
         this.claimants = new SimpleStringProperty(claimants);
         this.particular = new SimpleStringProperty(particular);
         this.amount = new SimpleStringProperty(amount);
         
     }
     public String getDv_no(){
            return dv_no.get();
             }
     public String getDate(){
            return date.get();
             }
     public String getClaimants(){
            return claimants.get();
             }
     public String getParticular(){
            return particular.get();
             }
       public String getAmount(){
            return amount.get();
             }
}
