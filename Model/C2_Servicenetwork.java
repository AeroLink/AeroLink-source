/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jpeg
 */
public class C2_Servicenetwork extends Synapse.Model {
    
    public C2_Servicenetwork(){
        setColumns("id",
                "branch_code",
                "branch_location",
                "branch_address",
                "branch_email",
                "branch_contact",
                "branch_manager");
        setTable("tbl_core2_add_branch");
    }
}

