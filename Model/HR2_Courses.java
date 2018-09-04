/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eden Ramoneda
 */
public class HR2_Courses extends Synapse.Model {

    public HR2_Courses() {
        setColumns("course_id", "course_title", "course_description",
                 "number_of_questions", "created_by");
        setTable("tbl_hr2_courses");
    }
}
