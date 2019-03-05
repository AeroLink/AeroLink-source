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
public class HR4_DeductionsClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public HR4_DeductionsClass(String deduc_code,String title){
        this.a = new SimpleStringProperty(deduc_code);
        this.b = new SimpleStringProperty(title);
    }
}
