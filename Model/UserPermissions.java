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
public class UserPermissions extends Synapse.Model{

    public UserPermissions() {
        this.initTable("tbl_user_permissions");
        setColumns("id", "user_id", "permission_id");
    }
    
    
}
