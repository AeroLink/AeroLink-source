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
public class SP_ExamTakenClass {
    public SimpleStringProperty eer_id, exam_name, score;

    public SP_ExamTakenClass(String eer_id, String exam_name, String score) {
        this.eer_id = new SimpleStringProperty(eer_id);
        this.exam_name = new SimpleStringProperty(exam_name);
        this.score = new SimpleStringProperty(score);
    }
    
    
    
}
