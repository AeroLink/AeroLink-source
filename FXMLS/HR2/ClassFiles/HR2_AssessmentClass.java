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
public class HR2_AssessmentClass {

    public SimpleStringProperty question_id;
    public SimpleStringProperty question;
    public SimpleStringProperty choice_id;
    public SimpleStringProperty course_id;

    public HR2_AssessmentClass(String question_id, String question,
            String choice_id, String course_id) {
        this.question_id = new SimpleStringProperty(question_id);
        this.question = new SimpleStringProperty(question);
        this.choice_id = new SimpleStringProperty(choice_id);
        this.course_id = new SimpleStringProperty(course_id);
    }
}
