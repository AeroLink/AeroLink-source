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
public class HR4_PHClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public SimpleStringProperty h;
    public SimpleStringProperty i;
    
    public HR4_PHClass(String mbs_min,String mbs_max,String monthly_premium_min,String monthly_premium_max,String ee_share_min,String ee_share_max,String er_share_min,String er_share_max){
        this.a = new SimpleStringProperty(mbs_min);
        this.c = new SimpleStringProperty(mbs_max);
        this.d = new SimpleStringProperty(monthly_premium_min);
        this.e = new SimpleStringProperty(monthly_premium_max);
        this.f = new SimpleStringProperty(ee_share_min);
        this.g = new SimpleStringProperty(ee_share_max);
        this.h = new SimpleStringProperty(er_share_min);
        this.i = new SimpleStringProperty(er_share_max);
        
    }
    
}
