/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementRequests  {
    
        private final SimpleStringProperty department;
        private final SimpleStringProperty item_name; 
        private final SimpleStringProperty size;
        private final SimpleStringProperty quantity; 
        private final SimpleStringProperty destination;
        private final SimpleStringProperty consignee;
        private final SimpleStringProperty departure;
        private final SimpleStringProperty typeoftransaction;
    
          public Log2_Fleet_ManagementRequests(String department,String item_name,String size,String quantity,String destination,
                  String consignee,String departure,String typeoftransaction){
            this.department = new SimpleStringProperty(department);
            this.item_name = new SimpleStringProperty(item_name);
            this.size = new SimpleStringProperty(size);
            this.quantity = new SimpleStringProperty(quantity);
            this.destination = new SimpleStringProperty(destination);
            this.consignee = new SimpleStringProperty(consignee);
            this.departure = new SimpleStringProperty(departure);
            this.typeoftransaction = new SimpleStringProperty(typeoftransaction);
            
}
           public String getDepartment(){
            return department.get();
           }
            
             public String getItem_name(){
            return item_name.get();
             }
             
             public String getSize(){
            return size.get();
             }
             
              public String getQuantity(){
            return quantity.get(); 
              }
              
               public String getDestination(){
            return destination.get();
               }
               
                public String getConsignee(){
            return consignee.get();
                }
                
                 public String getDeparture(){
            return departure.get();
                 }
                 
                 public String getTypeoftransaction(){
            return typeoftransaction.get();
                 }
                 
                
               
              
}