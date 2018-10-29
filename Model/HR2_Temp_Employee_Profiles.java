/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_Temp_Employee_Profiles extends Synapse.Model{
    
       public HR2_Temp_Employee_Profiles() {

        setColumns("id", "employee_code", "firstname", "middlename", "lastname","suffix_id",
                "date_of_birth","place_of_birth","gender","email","civil_status_id","contact_number");
        this.initTable("aerolink.tbl_hr4_employee_profiles");

    }
}
