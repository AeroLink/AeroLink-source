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
public class coltotal_classfile {
    
        public SimpleStringProperty colsamount;
         public SimpleStringProperty colstatus;
        
        public coltotal_classfile(String col_amount, String status2){
            
            this.colsamount = new SimpleStringProperty(col_amount);
              this.colstatus = new SimpleStringProperty(status2);
        }
}
