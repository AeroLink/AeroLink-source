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
public class HR2_LM_ViewCourseModal {

    public static String cid,jid,ct,cd,cb;
    
    public static void EditCourse(String course_title, String course_description,
            String created_by){
        ct = course_title;
        cd = course_description;
        cb = created_by;
    }
}
