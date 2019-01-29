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
          public SimpleStringProperty apDepartment; 
          public SimpleStringProperty apAmount;
          public SimpleStringProperty apStatus;
           public SimpleStringProperty apJournalStatus;
        
        
        
        
        public AP_classfile(String date,String invoice,String department,String amount,String status,String js){
            
            this.apDate = new SimpleStringProperty(date); 
            this.apinvoice = new SimpleStringProperty(invoice); 
            this.apDepartment = new SimpleStringProperty(department);
            this.apAmount = new SimpleStringProperty(amount);
            this.apStatus = new SimpleStringProperty(status);
            this.apJournalStatus = new SimpleStringProperty(js);
            
        }
        
      

}
