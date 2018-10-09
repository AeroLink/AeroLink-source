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
public class HR4_Temp_Employee_Profiles_Class {

    public SimpleStringProperty id;
    public SimpleStringProperty employee_code;
    public SimpleStringProperty firstname;
    public SimpleStringProperty middlename;
    public SimpleStringProperty lastname;
    public SimpleStringProperty fullname;
    public HR4_Temp_Employee_Profiles_Class(String training_id, String employee_code, String firstname,
            String middlename, String lastname) {

        this.id = new SimpleStringProperty(training_id);
        this.employee_code = new SimpleStringProperty(employee_code);
        this.firstname = new SimpleStringProperty(firstname);
        this.middlename = new SimpleStringProperty(middlename);
        this.lastname = new SimpleStringProperty(lastname);
    }
}
