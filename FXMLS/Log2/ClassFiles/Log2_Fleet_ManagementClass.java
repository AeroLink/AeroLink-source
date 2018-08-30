/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementClass {
    
      private SimpleStringProperty transaction_no;
        private SimpleStringProperty item_type; 
         private SimpleStringProperty personnel_in_charge;
        private SimpleStringProperty date_recieved; 
         
         
        public Log2_Fleet_ManagementClass(String transaction_no,String item_type,String personnel_in_charge, String date_recieved)
         {
             this.transaction_no = new SimpleStringProperty(transaction_no);
             this.item_type = new SimpleStringProperty(item_type);
             this.personnel_in_charge = new SimpleStringProperty(personnel_in_charge);
             this.date_recieved = new SimpleStringProperty(date_recieved);
             
         }
        
                    
             public String getTransaction_no()
             {
                 return transaction_no.get();
             }
             
             public String getItem_type()
             {
                 return item_type.get();
             }
             
             public String getPersonnel_in_charge()
             {
                 return personnel_in_charge.get();
             }
             
             public String getDate_recieved()
             {
                 return date_recieved.get();
             }
             
}
