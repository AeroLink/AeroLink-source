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
public class FINANCIAL_AP {
        private SimpleStringProperty ap_id;
        private SimpleStringProperty ap_date; 
        private SimpleStringProperty ap_payee;
        private SimpleStringProperty ap_particular; 
        private SimpleStringProperty ap_amount;
        
        public FINANCIAL_AP(String ap_id,String ap_date,String ap_payee,String ap_particular,String ap_amount){
            this.ap_id = new SimpleStringProperty(ap_id);
            this.ap_date = new SimpleStringProperty(ap_date);
            this.ap_payee = new SimpleStringProperty(ap_payee);
            this.ap_particular = new SimpleStringProperty(ap_particular);
            this.ap_amount = new SimpleStringProperty(ap_amount);
            
        }
        public String getAp_id(){
            return ap_id.get();
        }
        public String getAp_date(){
            return ap_date.get();
        }
        public String getAp_payee(){
            return ap_payee.get();
        }
        public String getAp_particular(){
            return ap_particular.get();
        }
        public String getAp_amount(){
            return ap_amount.get();
        }
                
}
