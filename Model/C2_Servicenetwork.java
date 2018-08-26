/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;


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

    public C2_Servicenetwork(String code, String location, String address, String email, String contact, String manager) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet execQuery(String qu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
