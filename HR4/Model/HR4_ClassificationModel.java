/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author Jaeeeee
 */
public class HR4_ClassificationModel extends Synapse.Model {
    public HR4_ClassificationModel()
    {
        setColumns("id","class_name","class_desc");
        this.initTable("tbl_hr4_job_classifications");
    }
    
}