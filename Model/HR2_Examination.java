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
public class HR2_Examination extends Synapse.Model{
    
        public HR2_Examination() {
        setColumns("exam_id","exam_name", "exam_desc","course_id","id");
        this.initTable("tbl_hr2_examination");
    }
    
}
