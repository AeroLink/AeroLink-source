/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author my
 */
public class new_emplo {
    
     public SimpleStringProperty employee_code;
     public SimpleStringProperty name;
     public SimpleStringProperty position;
     
   
     
     public new_emplo( String employee_code, String name, String position) {
        //this.ID = new SimpleStringProperty(ID);
        this.employee_code = new SimpleStringProperty(employee_code);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        
       
}
}
