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
public class coa_clasfile {
    
       public SimpleStringProperty cid;
       public SimpleStringProperty code;
       public SimpleStringProperty acc_title;
        
        
        
        
        public coa_clasfile(String coaid,String code_no,String account_title){
            
             
            this.cid = new SimpleStringProperty(coaid); 
            this.code = new SimpleStringProperty(code_no); 
            this.acc_title = new SimpleStringProperty(account_title);
            
        }
            
             public String getCoaid(){
             return cid.get();
             }
            public String getCode_no(){
            return code.get();
             }
            public String getAccount_title(){
            return acc_title.get();
             }

}
