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
public class FINANCIAL_CLASS_AR {
      private SimpleStringProperty ar_id;
        private SimpleStringProperty invoice_no; 
        private SimpleStringProperty date;
        private SimpleStringProperty payee; 
        private SimpleStringProperty particular; 
        private SimpleStringProperty amount;
        
        
        public FINANCIAL_CLASS_AR(String ar_id,String invoice_no,String date,String payee,String particular,String amount){
            this.ar_id = new SimpleStringProperty(ar_id);
            this.invoice_no = new SimpleStringProperty(invoice_no);
            this.date = new SimpleStringProperty(date);
            this.payee = new SimpleStringProperty(payee);
            this.particular = new SimpleStringProperty(particular);
             this.amount = new SimpleStringProperty(amount);
        
        }     
        
         public String getAr_id(){
            return ar_id.get();
        }
         public String getInvoice_no(){
            return invoice_no.get();
        }
         public String getDate(){
            return date.get();
        }
         public String getPayee(){
            return payee.get();
        }
         public String getParticular(){
            return particular.get();
        }
         public String getAmount(){
            return amount.get();
        }
         
}
