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
    
        private SimpleStringProperty department;
        private SimpleStringProperty item_name; 
        private SimpleStringProperty size;
        private SimpleStringProperty quantity; 
        private SimpleStringProperty destination;
        private SimpleStringProperty consignee;
        private SimpleStringProperty departure;
    
          public Log2_Fleet_ManagementRequests(String department,String item_name,String size,String quantity,String destination,
                  String consignee,String departure){
            this.department = new SimpleStringProperty(department);
            this.item_name = new SimpleStringProperty(item_name);
            this.size = new SimpleStringProperty(size);
            this.quantity = new SimpleStringProperty(quantity);
            this.destination = new SimpleStringProperty(destination);
            this.consignee = new SimpleStringProperty(consignee);
            this.departure = new SimpleStringProperty(departure);
            
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
                 
                
               
              
}