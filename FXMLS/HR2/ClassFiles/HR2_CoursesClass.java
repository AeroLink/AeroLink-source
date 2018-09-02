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

    public SimpleStringProperty course_id;
    public SimpleStringProperty course_title;
    public SimpleStringProperty course_description;
    public SimpleStringProperty number_of_questions;
    public SimpleStringProperty created_by;

    public HR2_CoursesClass(String course_id, String course_title,String course_description,
            String number_of_questions,String created_by) {
        this.course_id = new SimpleStringProperty(course_id);
        this.course_title = new SimpleStringProperty(course_title);
        this.course_description = new SimpleStringProperty(course_description);
        this.number_of_questions = new SimpleStringProperty(number_of_questions);
        this.created_by = new SimpleStringProperty(created_by);
    }
}
