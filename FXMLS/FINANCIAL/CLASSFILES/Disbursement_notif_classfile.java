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
public class Disbursement_notif_classfile {
        public SimpleStringProperty dvno;
        public SimpleStringProperty status;
        
        public Disbursement_notif_classfile(
                String dv_no,
                String status){
            
            this.dvno = new SimpleStringProperty(dv_no);
            this.status = new SimpleStringProperty(status);
            
        }
}
