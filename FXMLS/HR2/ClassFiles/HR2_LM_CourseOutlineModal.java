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
public class HR2_LM_CourseOutlineModal {

    public static String cid, jid;
    
    //for tbl_files
    public SimpleStringProperty files;

    public static void courseOutline(String job_id) {
        jid = job_id;
    }
    
     //for tbl_files
    public HR2_LM_CourseOutlineModal(String file) {
        files = new SimpleStringProperty(file);
    }
}
