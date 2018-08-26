/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JaeJae
 */
public class FINANCIAL_GL_COA {
 
     private SimpleStringProperty code_no; 
     private SimpleStringProperty account_title;
     
     public FINANCIAL_GL_COA(String code_no,String account_title){
       
            this.code_no = new SimpleStringProperty(code_no);
            this.account_title = new SimpleStringProperty(account_title);
     }
            
             public String getCode_no(){
            return code_no.get();
             }
              public String getAccount_title(){
            return account_title.get();
             }
}

