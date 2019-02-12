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
public class disbursement_tbl_received_classfile {
    public SimpleStringProperty status;
    public SimpleStringProperty disbursed_status;
        
        public disbursement_tbl_received_classfile(String status1, String dstatus){
            
            this.status = new SimpleStringProperty(status1);
            this.disbursed_status = new SimpleStringProperty(dstatus);
        }
    
}
