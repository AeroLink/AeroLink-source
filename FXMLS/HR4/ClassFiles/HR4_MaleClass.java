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
public class HR4_MaleClass {
     public SimpleStringProperty gen;
     
    public HR4_MaleClass(String gender){
    this.gen = new SimpleStringProperty(gender);
    }
    
    
    
    
    
}
