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
public class HR4_TaxClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    
    public HR4_TaxClass(String min,String max,String basic_amount,String additional_rate,String of_excess_over,String total_max){
        this.a = new SimpleStringProperty(min);
        this.c = new SimpleStringProperty(max);
        this.d = new SimpleStringProperty(basic_amount);
        this.e = new SimpleStringProperty(additional_rate);
        this.f = new SimpleStringProperty(of_excess_over);
        this.g = new SimpleStringProperty(total_max);
        
    }
}
