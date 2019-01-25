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
          public SimpleStringProperty dvdreq;
          public SimpleStringProperty dvdep;
          public SimpleStringProperty dvdes;
          public SimpleStringProperty dvclaimant;
          public SimpleStringProperty dvamount; 
          public SimpleStringProperty dvreceivedby; 
          public SimpleStringProperty dvdatereleased;
        
public Disbursement_Voucher_classfile(String dreq,String dep,
        String des,
        String claimant,
        String amount,
        String receivedby,
        String datereleased){
            
          this.dvdreq = new SimpleStringProperty(dreq); 
          this.dvdep = new SimpleStringProperty(dep); 
          this.dvdes = new SimpleStringProperty(des); 
          this.dvclaimant = new SimpleStringProperty(claimant); 
          this.dvamount = new SimpleStringProperty(amount);
          this.dvreceivedby = new SimpleStringProperty(receivedby);
          this.dvdatereleased = new SimpleStringProperty(datereleased);
}
}
