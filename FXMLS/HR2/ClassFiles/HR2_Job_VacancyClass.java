/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_Job_VacancyClass {

    public SimpleStringProperty title;
    public SimpleStringProperty department;
    
    public HR2_Job_VacancyClass(String title, String department) {
        this.title = new SimpleStringProperty(title);
        this.department = new SimpleStringProperty(department);
    }

}
