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
public class HR2_EvaluationClass {

    public SimpleStringProperty choice_id;
    public SimpleStringProperty question_id;
    public SimpleStringProperty choice;
    public SimpleStringProperty choice_description;
    public SimpleStringProperty ischecked;

    public HR2_EvaluationClass(String choice_id, String question_id, String choice,
            String choice_description, String ischecked) {
        this.choice_id = new SimpleStringProperty(choice_id);
        this.question_id = new SimpleStringProperty(question_id);
        this.choice = new SimpleStringProperty(choice);
        this.choice_description = new SimpleStringProperty(choice_description);
        this.ischecked = new SimpleStringProperty(ischecked);
    }
}
