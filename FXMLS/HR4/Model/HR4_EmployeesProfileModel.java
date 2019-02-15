/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author Gilbert
 */
public class HR4_EmployeesProfileModel extends Synapse.Model{
      public HR4_EmployeesProfileModel(){
    setColumns("id","employee_code","firstname","lastname","middlename","suffix_id",
                "date_of_birth","place_of_birth","gender","email","civil_status_id"
                ,"height","weight","contact_number","employement_contract","address");
    this.initTable("tbl_hr4_employee_profiles");
    }
}
