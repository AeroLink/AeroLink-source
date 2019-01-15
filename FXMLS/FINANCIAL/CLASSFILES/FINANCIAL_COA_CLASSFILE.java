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
public class FINANCIAL_COA_CLASSFILE {
    
     private SimpleStringProperty cid; 
     public SimpleStringProperty cn; 
     public SimpleStringProperty at;
     
     public FINANCIAL_COA_CLASSFILE(String coa_id,String code_no,String acc_title){
            this.cid = new SimpleStringProperty(coa_id);
            this.cn = new SimpleStringProperty(code_no);
            this.at = new SimpleStringProperty(acc_title);
     }
            
            public String getCoa_id(){
            return cid.get();
             }
             public String getCode_no(){
            return cn.get();
             }
           
              public String getAcc_title(){
            return at.get();
             }
}
