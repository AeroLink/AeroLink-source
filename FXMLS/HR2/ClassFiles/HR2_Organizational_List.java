/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Organizational_List {
    
    public SimpleStringProperty employee_code;
    public SimpleStringProperty Fullname;
    public SimpleStringProperty Job_Title;
    public SimpleStringProperty classification;

    public HR2_Organizational_List(String employee_code, String Job_Title,String Fullname, String Classification) {
        this.employee_code = new SimpleStringProperty(employee_code);
        this.Job_Title = new SimpleStringProperty(Job_Title );
        this.Fullname = new SimpleStringProperty(Fullname);
        this.classification = new SimpleStringProperty(Classification);
    }

}
