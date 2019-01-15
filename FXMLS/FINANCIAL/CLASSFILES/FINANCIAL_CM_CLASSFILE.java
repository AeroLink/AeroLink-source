/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Gilbert
 */
public class FINANCIAL_CM_CLASSFILE {
     /*   public SimpleStringProperty id;  
        public SimpleStringProperty poid;
        public SimpleStringProperty assetid;*/
        public SimpleStringProperty inv;
        public SimpleStringProperty name;
        public SimpleStringProperty desc;
        public SimpleStringProperty date;
        public SimpleStringProperty type;
        public SimpleStringProperty amount;
        private CheckBox select;
        
        
        
        
        public FINANCIAL_CM_CLASSFILE(String invoice,
                String name,String desc,String date, String type,String amount){
            
           /* this.id = new SimpleStringProperty(id);
            this.poid = new SimpleStringProperty(po_id);
            this.assetid = new SimpleStringProperty(asset_id);*/
            this.inv = new SimpleStringProperty(invoice); 
            this.name = new SimpleStringProperty(name);
            this.desc = new SimpleStringProperty(desc);
            this.type = new SimpleStringProperty(type);
            this.date = new SimpleStringProperty(date);  
            this.amount = new SimpleStringProperty(amount); 
            this.select = new CheckBox(); 
        }
    public CheckBox getSelect(){
         return select;
     }
     public void setSelect(CheckBox select){
         this.select = select;
     }
          
}
