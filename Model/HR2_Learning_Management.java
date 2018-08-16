/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Learning_Management extends Synapse.Model{
    
                public HR2_Learning_Management()
                {
                       
                    setColumns("emp_id","emp_name","course_title","course_description","coordinator","duration","status");
                    setTable("tbl_learning_management");
                }

}
