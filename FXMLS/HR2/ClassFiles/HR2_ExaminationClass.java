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
public class HR2_ExaminationClass {

    public SimpleStringProperty exam_id, exam_name, exam_desc, id;

    public HR2_ExaminationClass(String exam_id, String exam_name, String exam_desc, String id) {
        this.exam_id = new SimpleStringProperty(exam_id);
        this.exam_name = new SimpleStringProperty(exam_name);
        this.exam_desc = new SimpleStringProperty(exam_desc);
        this.id = new SimpleStringProperty(id);

    }

}
