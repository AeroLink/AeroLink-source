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
public class FINANCIAL_BM_ALLOCATION {
     public SimpleStringProperty department;
     public SimpleStringProperty amount;
        
        
        
        public FINANCIAL_BM_ALLOCATION(String deprtmnt, String amnt){
            
            this.department = new SimpleStringProperty(deprtmnt); 
            this.amount = new SimpleStringProperty(amnt);
        }

    

   
        public String getDrtmnt(){
            return department.get();
        }
        public String getAmnt(){
            return amount.get();
        }
    
    
}
