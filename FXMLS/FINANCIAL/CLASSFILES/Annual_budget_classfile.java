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
public class Annual_budget_classfile {
          public SimpleStringProperty abYear;
          public SimpleStringProperty abBudget;
        
        public Annual_budget_classfile(String year,String budget){
            
            this.abYear = new SimpleStringProperty(year);
            this.abBudget = new SimpleStringProperty(budget);
            
        }
}
