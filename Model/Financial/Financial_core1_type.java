/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Financial;

import static Synapse.Model.setColumns;

/**
 *
 * @author Gilbert
 */
public class Financial_core1_type extends Synapse.Model{
    public Financial_core1_type(){
     setColumns("id",
            "title");
    this.initTable("tbl_type");
     }
}
