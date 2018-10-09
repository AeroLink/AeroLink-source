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

        setColumns("id", "employee_code", "firstname", "middlename", "lastname");
        this.initTable("aerolink.tbl_hr4_employee_profiles");

    }
}
