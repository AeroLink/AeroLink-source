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
public class Log2_Fleet_Management_Details {
    
    public SimpleStringProperty Itemname;
    public SimpleStringProperty Quantity;
  

    public Log2_Fleet_Management_Details(String itemname, String quantity) {
        this.Itemname = new SimpleStringProperty(itemname);
        this.Quantity = new SimpleStringProperty(quantity);
        
    }
}
