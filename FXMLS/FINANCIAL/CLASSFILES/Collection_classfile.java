/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class Collection_classfile{
    
        public SimpleStringProperty poid;
        public SimpleStringProperty pono;
        public SimpleStringProperty podate;
        public SimpleStringProperty poinvoice;
        public SimpleStringProperty podescription;
        public SimpleStringProperty poamount;
        public SimpleStringProperty potype;
        
        
        
        
        public Collection_classfile(String po_id,
                String po_no,String po_date,String po_invoice,String po_description,String po_amount,String po_type){
            
            this.poid = new SimpleStringProperty(po_id); 
            this.pono = new SimpleStringProperty(po_no);
            this.podate = new SimpleStringProperty(po_date);
            this.poinvoice = new SimpleStringProperty(po_invoice);
            this.podescription = new SimpleStringProperty(po_description);
            this.poamount = new SimpleStringProperty(po_amount);
            this.potype = new SimpleStringProperty(po_type);
        }
 
    
}
