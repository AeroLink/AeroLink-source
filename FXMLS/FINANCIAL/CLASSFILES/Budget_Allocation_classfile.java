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
public class Budget_Allocation_classfile {
    
          public SimpleStringProperty badate;
          public SimpleStringProperty badepartment; 
          public SimpleStringProperty baamount;
        
        
        
        
        public Budget_Allocation_classfile(String ba_date,String ba_department,String ba_amount){
            
            this.badate = new SimpleStringProperty(ba_date); 
            this.badepartment = new SimpleStringProperty(ba_department);
            this.baamount = new SimpleStringProperty(ba_amount);
        }
}
