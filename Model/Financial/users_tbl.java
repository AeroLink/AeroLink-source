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
public class users_tbl extends Synapse.Model{
      public users_tbl(){
    setColumns("id",
            "username",
           "password");
    this.initTable("tbl_users");
   
}
}
