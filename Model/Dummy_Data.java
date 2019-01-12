/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author my
 */
public class Dummy_Data extends Synapse.Model{
    public Dummy_Data()
    {
          setColumns("ID","Name","Position","Department");
             this.initTable("tbl_hr3_dummy_data");
    }
}
