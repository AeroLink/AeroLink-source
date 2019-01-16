/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Server_Error
 */
public class Admin_Legal_Management extends Synapse.Model {
    
    public Admin_Legal_Management(){
    setColumns("Archive_ID","Archive_Name","Date","Time","doctype");
    this.initTable("tbl_admin_archive");
    }
}
