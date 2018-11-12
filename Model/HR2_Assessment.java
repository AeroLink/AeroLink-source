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
public class HR2_Assessment extends Synapse.Model {

    public HR2_Assessment() {
        setColumns("question_id", "question_number", "question",
                 "choice_id", "course_id","isDeleted");
        this.initTable("tbl_hr2_assessment");
    }
}
