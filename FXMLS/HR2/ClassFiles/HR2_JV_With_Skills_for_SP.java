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
public class HR2_JV_With_Skills_for_SP {

    public SimpleStringProperty title;
    public SimpleStringProperty department;
    public SimpleStringProperty skills;

    public HR2_JV_With_Skills_for_SP(String department,String title, String skill) {
        this.department = new SimpleStringProperty(department);
        this.title = new SimpleStringProperty(title);
        this.skills = new SimpleStringProperty(skill);
    }

}
