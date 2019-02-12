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
public class Pendingbudget_classfile {
    public SimpleStringProperty id;
        
public Pendingbudget_classfile(String p_id){
            
          this.id = new SimpleStringProperty(p_id);  
          
        }
        public String getId(){
            return id.get();
        }
}
