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
public class HR4_PayrollSettingsClass {
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
    public SimpleStringProperty l;
    
    public HR4_PayrollSettingsClass(String cola,String overtime,String special_holiday,String special_holiday_ot,String regular_holiday,String regular_holiday_ot,String double_holiday,String double_holiday_ot,String night_differentials,String special_holiday_ns,String regular_holiday_ns,String double_holiday_ns){
    this.a = new SimpleStringProperty(cola);
    this.b = new SimpleStringProperty(overtime);
    this.c = new SimpleStringProperty(special_holiday);
    this.d = new SimpleStringProperty(special_holiday_ot);
    this.e = new SimpleStringProperty(regular_holiday);
    this.f = new SimpleStringProperty(regular_holiday_ot);
    this.g = new SimpleStringProperty(double_holiday);
    this.h = new SimpleStringProperty(double_holiday_ot);
    this.i = new SimpleStringProperty(night_differentials);
    this.j = new SimpleStringProperty(special_holiday_ns);
    this.k = new SimpleStringProperty(regular_holiday_ns);
    this.l = new SimpleStringProperty(double_holiday_ns);

    }
}