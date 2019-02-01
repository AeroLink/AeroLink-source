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
    
        public SimpleStringProperty coldate;
        public SimpleStringProperty colinvoice;
        public SimpleStringProperty coldescription;
        public SimpleStringProperty colamount;
         public SimpleStringProperty assetamount;
        public SimpleStringProperty coltype;
         public SimpleStringProperty status2;
        
        
        
        
        public Collection_classfile(
                String col_date,
                String col_invoice,
                String col_description,
                String col_amount,
                String assetamount,
                String col_type,
                String ast_status2){
            
            this.coldate = new SimpleStringProperty(col_date);
            this.colinvoice = new SimpleStringProperty(col_invoice);
            this.coldescription = new SimpleStringProperty(col_description);
            this.colamount = new SimpleStringProperty(col_amount);
            this.assetamount = new SimpleStringProperty(assetamount);
            this.coltype = new SimpleStringProperty(col_type);
              this.status2 = new SimpleStringProperty(ast_status2);
            
        }
 
    
}
