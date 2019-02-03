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
public class Disbursement_Voucher_classfile {
             public SimpleStringProperty no;
          public SimpleStringProperty dvdreq;
          public SimpleStringProperty dvdep;
          public SimpleStringProperty dvdes;
          public SimpleStringProperty dvclaimant;
          public SimpleStringProperty dvamount;
          public SimpleStringProperty dvdatereleased;
          public SimpleStringProperty dvreceivedby; 
        
public Disbursement_Voucher_classfile(String dno,String dreq,String dep,
        String des,
        String claimant,
        String amount,
        String datereleased,
        String receivedby){
            
            this.no = new SimpleStringProperty(dno); 
          this.dvdreq = new SimpleStringProperty(dreq); 
          this.dvdep = new SimpleStringProperty(dep); 
          this.dvdes = new SimpleStringProperty(des); 
          this.dvclaimant = new SimpleStringProperty(claimant); 
          this.dvamount = new SimpleStringProperty(amount);
          this.dvdatereleased = new SimpleStringProperty(datereleased);
          this.dvreceivedby = new SimpleStringProperty(receivedby);
}
        public String getDno(){
            return no.get();
        }

}
