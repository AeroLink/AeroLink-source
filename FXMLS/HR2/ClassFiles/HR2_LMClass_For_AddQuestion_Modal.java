/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import static FXMLS.HR2.ClassFiles.HR2_TM_Class_for_Modal.jp;
import static FXMLS.HR2.ClassFiles.HR2_TM_Class_for_Modal.t_title;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_LMClass_For_AddQuestion_Modal {

    public static String exam_id, exam_name, exam_desc, created_by, question;

    public static void initCourseTitle(String eid, String en, String ed, String cb) {
        exam_id = eid;
        exam_name = en;
        exam_desc = ed;
        created_by = cb;
    }

    public static void initCourseQuestion(String q) {
        question = q;
    }
}
