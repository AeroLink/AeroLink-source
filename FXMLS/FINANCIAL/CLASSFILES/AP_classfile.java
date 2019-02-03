/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class AP_classfile {
          public SimpleStringProperty apDate;
          public SimpleStringProperty apinvoice;
          public SimpleStringProperty apDescription; 
          public SimpleStringProperty apAmount;
          public SimpleStringProperty apStatus;
           public SimpleStringProperty apJournalStatus;
           public SimpleStringProperty apName; 
        
        
        
        
        public AP_classfile(String date,
                String invoice,
                String description,
                String amount,
                String status,
                String js,
                String Name){
            
            this.apDate = new SimpleStringProperty(date); 
            this.apinvoice = new SimpleStringProperty(invoice); 
            this.apDescription = new SimpleStringProperty(description);
            this.apAmount = new SimpleStringProperty(amount);
            this.apStatus = new SimpleStringProperty(status);
            this.apJournalStatus = new SimpleStringProperty(js);
             this.apName = new SimpleStringProperty(Name);
            
        }
         public String getDate(){
            return apDate.get();
             }
         public String getInvoice(){
            return apinvoice.get();
             }
         public String getDescription(){
            return apDescription.get();
             }
         public String getAmount(){
            return apAmount.get();
             }
         public String getStatus(){
            return apStatus.get();
             }
         public String getJs(){
            return apJournalStatus.get();
             }
           public String getName(){
            return apName.get();
             }
        
        
        
      

}
