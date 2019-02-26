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
public class HR4_Deductions1Class {
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
    public SimpleStringProperty m;
    public SimpleStringProperty n;
    public SimpleStringProperty o;
    public SimpleStringProperty p;
    public SimpleStringProperty z;
    public HR4_Deductions1Class(String id,String deduc_code,String C1, String C2,String C3,String C4,String C5,String C6,String C7,String C8,String C9,String C10,String C11,String C12,String C13,String C14,String C15){
     this.z = new SimpleStringProperty(id);
     this.a = new SimpleStringProperty(deduc_code);
     this.b = new SimpleStringProperty(C1);
     this.c = new SimpleStringProperty(C2);
     this.d = new SimpleStringProperty(C3);
     this.e = new SimpleStringProperty(C4);
     this.f = new SimpleStringProperty(C5);
     this.g = new SimpleStringProperty(C6);
     this.h = new SimpleStringProperty(C7);
     this.i = new SimpleStringProperty(C8);
     this.j = new SimpleStringProperty(C9);
     this.k = new SimpleStringProperty(C10);
     this.l = new SimpleStringProperty(C11);
     this.m = new SimpleStringProperty(C12);
     this.n = new SimpleStringProperty(C13);
     this.o = new SimpleStringProperty(C14);
     this.p = new SimpleStringProperty(C15);
    }
   public String getID(){
       return z.get();
             }
   public String getC1(){
       return b.get();
             }
   public String getC2(){
       return c.get();
             }
   public String getC3(){
       return d.get();
             }
   public String getC4(){
       return e.get();
             }
   public String getC5(){
       return f.get();
             }
   public String getC6(){
       return g.get();
             }
   public String getC7(){
       return h.get();
             }
   public String getC8(){
       return i.get();
             }
   public String getC9(){
       return j.get();
             }
   public String getC10(){
       return k.get();
             }
   public String getC11(){
       return l.get();
             }
   public String getC12(){
       return m.get();
             }
   public String getC13(){
       return n.get();
             }
   public String getC14(){
       return o.get();
             }
   public String getC15(){
       return p.get();
             }
}
