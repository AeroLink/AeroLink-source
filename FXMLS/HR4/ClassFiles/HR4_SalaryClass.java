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
public class HR4_SalaryClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public SimpleStringProperty h;
    public SimpleStringProperty i;
    
    public HR4_SalaryClass(String salary_grade,String step1,String step2, String step3,String step4,String step5,String step6,String step7,String step8){
        this.a = new SimpleStringProperty(salary_grade);
        this.b = new SimpleStringProperty(step1);
        this.c = new SimpleStringProperty(step2);
        this.d = new SimpleStringProperty(step3);
        this.e = new SimpleStringProperty(step4);
        this.f = new SimpleStringProperty(step5);
        this.g = new SimpleStringProperty(step6);
        this.h = new SimpleStringProperty(step7);
        this.i = new SimpleStringProperty(step8);

    }
}