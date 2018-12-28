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
public class HR2_LM_AddExamModalClass {

    public static String jid, jt;
    public SimpleStringProperty eid, ename, edesc, cb;

    public static void AddExam(String job_title) {
      //  jid = job_id;
        jt = job_title;
    }

    public HR2_LM_AddExamModalClass(String exam_name, String exam_desc, String created_by) {
        ename = new SimpleStringProperty(exam_name);
        edesc = new SimpleStringProperty(exam_desc);
        cb = new SimpleStringProperty(created_by);
    }
}
