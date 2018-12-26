/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_CourseOutline extends Synapse.Model{

    public HR2_CourseOutline() {
        setColumns("co_id","course_id", "file", "isDeleted");
        this.initTable("tbl_hr2_course_outline");
    }
}
