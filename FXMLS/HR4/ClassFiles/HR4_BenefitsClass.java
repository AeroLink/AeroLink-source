/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jaeeeee
 */
public class HR4_BenefitsClass {
    public SimpleStringProperty z;
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public HR4_BenefitsClass(String id,String title,String amount,String description,String days){
     this.z = new SimpleStringProperty(id);
     this.a = new SimpleStringProperty(title);
     this.b = new SimpleStringProperty(amount);
     this.c = new SimpleStringProperty(description);
     this.d = new SimpleStringProperty(days);
    }
    
}
