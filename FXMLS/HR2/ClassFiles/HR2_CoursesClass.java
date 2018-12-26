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
public class HR2_CoursesClass {

    public SimpleStringProperty course_id,job_title;

    public HR2_CoursesClass(String course_id,String job_title) {
        this.job_title = new SimpleStringProperty(job_title);
    }
}
