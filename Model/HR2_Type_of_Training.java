/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import static Synapse.Model.setColumns;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Type_of_Training extends Synapse.Model{

          public HR2_Type_of_Training()
            {
                   setColumns("type_of_training_id","type_of_training");
                    this.initTable("tbl_hr2_type_of_training");
            }
}
