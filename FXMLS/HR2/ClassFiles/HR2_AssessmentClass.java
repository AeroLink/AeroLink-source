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


    public HR2_AssessmentClass(String question_id, String question) {
        this.question_id = new SimpleStringProperty(question_id);
        this.question = new SimpleStringProperty(question);

    }
}
