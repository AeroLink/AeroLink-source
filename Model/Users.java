/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



/**
 *
 * @author Lei
 */
public class Users extends Synapse.Model{

    public Users() {
        setTable("tbl_users");
        setColumns("id", 
                "username", 
                "password");
        
    }
    
}
