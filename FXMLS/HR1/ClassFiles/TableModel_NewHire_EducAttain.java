/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_NewHire_EducAttain {

    public StringProperty educ_level;
    public StringProperty school;
    public StringProperty course;
    public StringProperty duration;

    public TableModel_NewHire_EducAttain(String educ_level, String school, String course, String duration) {
        this.educ_level = new SimpleStringProperty(educ_level);
        this.school = new SimpleStringProperty(school);
        this.course = new  SimpleStringProperty(course);
        this.duration = new SimpleStringProperty(duration);
    }
    
    

    
    
    
}
