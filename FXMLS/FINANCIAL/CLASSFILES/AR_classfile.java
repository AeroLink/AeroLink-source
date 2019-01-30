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
          public SimpleStringProperty arDescription;
          public SimpleStringProperty arAmount;
          public SimpleStringProperty arStatus;
          public SimpleStringProperty arJournalStatus;
        
        
        
        
        public AR_classfile(String date,String invoiceno,String description,String amount,String status,String jstatus){
            
            this.arDate = new SimpleStringProperty(date); 
            this.arInvoiceno = new SimpleStringProperty(invoiceno);
            this.arDescription = new SimpleStringProperty(description);
            this.arAmount = new SimpleStringProperty(amount);
            this.arStatus = new SimpleStringProperty(status);
            this.arJournalStatus = new SimpleStringProperty(jstatus);
            
        }
        
           public String getAmount(){
            return arAmount.get();
            
             }
           
}
