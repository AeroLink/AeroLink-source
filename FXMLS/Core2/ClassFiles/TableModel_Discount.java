/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author JPEG
 */
public class TableModel_Discount {
    public StringProperty subject;
    public StringProperty discount;
    
    public TableModel_Discount(String subject, String discount){
        this.subject = new SimpleStringProperty(subject);
        this.discount = new SimpleStringProperty(discount);
    }

    public String getSubject(){
        return subject.get();
    }
    
    public String getDiscount(){
        return discount.get();
    }
}
