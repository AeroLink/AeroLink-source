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
public class C2_Serviceprovider extends Synapse.Model {
    
    public C2_Serviceprovider(){
        setColumns("apid",
                "company_name",
                "contact_number",
                "house_number",
                "street",
                "barangay",
                "city_monicipality",
                "zipcode",
                "email",
                "username",
                "password",
                "doortodoor",
                "pickup",
                "express",
                "other",
                "region",
                "country");
        setTable("tbl_core2_add_provider");
    }
    
}
