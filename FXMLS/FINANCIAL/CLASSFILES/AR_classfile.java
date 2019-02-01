/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class AR_classfile {
          public SimpleStringProperty arDate;
          public SimpleStringProperty arInvoiceno; 
          public SimpleStringProperty arFirstname;
          public SimpleStringProperty arLastname;
          public SimpleStringProperty arDescription;
          public SimpleStringProperty arAmount;
          public SimpleStringProperty arStatus;
          public SimpleStringProperty arTypeStatus;
          public SimpleStringProperty arJournalStatus;
        
        
        
        
        public AR_classfile(String date,
                            String invoiceno,
                            String firstname,
                            String lastname,
                            String description,
                            String amount,
                            String status,
                            String typestatus,
                            String jstatus){
            
            this.arDate = new SimpleStringProperty(date); 
            this.arInvoiceno = new SimpleStringProperty(invoiceno);
            this.arFirstname = new SimpleStringProperty(firstname);
            this.arLastname = new SimpleStringProperty(lastname);
            this.arDescription = new SimpleStringProperty(description);
            this.arAmount = new SimpleStringProperty(amount);
            this.arStatus = new SimpleStringProperty(status);
            this.arTypeStatus = new SimpleStringProperty(typestatus);
            this.arJournalStatus = new SimpleStringProperty(jstatus);
            
        }
             public String getDate(){
            return arDate.get();
             }
              public String getInvoiceno(){
              return arInvoiceno.get();
            
             }
              public String getFirstname(){
              return arFirstname.get();
             }
               public String getLastname(){
               return arLastname.get();
             }
               public String getAmount(){
               return arAmount.get();
             }
               public String getDescription(){
               return arDescription.get();
             }
               public String getStatus(){
               return arStatus.get();
             }
               public String getTypestatus(){
               return arTypeStatus.get();
             }
           public String getJournalstatus(){
            return arJournalStatus.get();
            
             }
           
}
