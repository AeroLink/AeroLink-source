/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Jaeeeee
 */
public class HR4_SSSClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public SimpleStringProperty h;
    public SimpleStringProperty i;
    public SimpleStringProperty j;
    public SimpleStringProperty k;
    
    public HR4_SSSClass(String roc,String msc,String er1,String ee1,String total1,String ec_er,String er2,String ee2,String total2,String totalcon){
        this.a = new SimpleStringProperty(roc);
        this.c = new SimpleStringProperty(msc);
        this.d = new SimpleStringProperty(er1);
        this.e = new SimpleStringProperty(ee1);
        this.f = new SimpleStringProperty(total1);
        this.g = new SimpleStringProperty(ec_er);
        this.h = new SimpleStringProperty(er2);
        this.i = new SimpleStringProperty(ee2);
        this.j = new SimpleStringProperty(total2);
        this.k = new SimpleStringProperty(totalcon);
        
    }
    
}
