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
public class HR4_Benefits1Class {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public HR4_Benefits1Class(String benefits_id, String benN, String emp_code, String fnN, String balance,String araw,String files){
     this.a = new SimpleStringProperty(benefits_id);
     this.b = new SimpleStringProperty(benN);
     this.c = new SimpleStringProperty(emp_code);
     this.d = new SimpleStringProperty(fnN);
     this.e = new SimpleStringProperty(balance);
     this.f = new SimpleStringProperty(araw);
     this.g = new SimpleStringProperty(files);
    }
}
