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
          public SimpleStringProperty apDepartment; 
          public SimpleStringProperty apAmount;
          public SimpleStringProperty apStatus;
        
        
        
        
        public AP_classfile(String date,String department,String amount,String status){
            
            this.apDate = new SimpleStringProperty(date); 
            this.apDepartment = new SimpleStringProperty(department);
            this.apAmount = new SimpleStringProperty(amount);
            this.apStatus = new SimpleStringProperty(status);
            
        }
        
           public String getAmount(){
            return apAmount.get();
             }
           

}
