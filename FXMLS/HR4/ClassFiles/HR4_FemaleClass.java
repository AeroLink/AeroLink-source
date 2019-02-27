/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class HR4_FemaleClass {
    public SimpleStringProperty gen1;
     
    public HR4_FemaleClass(String gender){
    this.gen1 = new SimpleStringProperty(gender);
    }
    
    
}
