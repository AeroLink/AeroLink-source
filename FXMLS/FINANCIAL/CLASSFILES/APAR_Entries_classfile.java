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
public class APAR_Entries_classfile {
          public SimpleStringProperty IdEntries;
          public SimpleStringProperty DateEntries;
          public SimpleStringProperty InvoiceEntries;
          public SimpleStringProperty FirstnameEntries; 
          public SimpleStringProperty LastnameEntries; 
          public SimpleStringProperty DescriptionEntries;
          public SimpleStringProperty AmountEntries;
          public SimpleStringProperty StatusEntries;
          public SimpleStringProperty TypeStatusEntries;
          public SimpleStringProperty AccountStatusEntries;
          public SimpleStringProperty JournalStatusEntries;
          
        public APAR_Entries_classfile(String id,
                String date,
                String invoiceno,
                String firstname,
                String lastname,
                String description,
                String amount,
                String status,
                String typestatus,
                String accountstatus,
                String journalstatus){
            
            this.IdEntries = new SimpleStringProperty(id); 
            this.DateEntries = new SimpleStringProperty(date); 
            this.InvoiceEntries = new SimpleStringProperty(invoiceno);
            this.FirstnameEntries = new SimpleStringProperty(firstname);
            this.LastnameEntries = new SimpleStringProperty(lastname);
            this.DescriptionEntries = new SimpleStringProperty(description);
            this.AmountEntries = new SimpleStringProperty(amount); 
            this.StatusEntries = new SimpleStringProperty(status);
            this.TypeStatusEntries = new SimpleStringProperty(typestatus);
            this.AccountStatusEntries = new SimpleStringProperty(accountstatus);
            this.JournalStatusEntries = new SimpleStringProperty(journalstatus);
            
        }
        
       public String getId(){
       return IdEntries.get();
             }
        public String getDate(){
       return DateEntries.get();
             }
         public String getInvoiceno(){
       return InvoiceEntries.get();
             }
        public String getFirstname(){
       return FirstnameEntries.get();
             }
         public String getLastname(){
       return LastnameEntries.get();
             }
        public String getDescription(){
       return DescriptionEntries.get();
             }
         public String getAmount(){
       return AmountEntries.get();
             }
         public String getStatus(){
        return StatusEntries.get();
             }
          public String getTypeStatus(){
        return TypeStatusEntries.get();
             }
           public String getAccountstatus(){
        return AccountStatusEntries.get();
             }
           public String getJournalstatus(){
        return JournalStatusEntries.get();
             }
}
