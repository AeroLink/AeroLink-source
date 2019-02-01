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
public class col_totalasset_classfile {
     public SimpleStringProperty colAstamount;
     public SimpleStringProperty colAststatus2;
        
        public col_totalasset_classfile(String colastamount, String colaststatus2){
            
            this.colAstamount = new SimpleStringProperty(colastamount);
              this.colAststatus2 = new SimpleStringProperty(colaststatus2);
        }
}
