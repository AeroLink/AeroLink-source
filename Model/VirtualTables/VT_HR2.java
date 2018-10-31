/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.VirtualTables;

/**
 *
 * @author EdenRamoneda
 */
public class VT_HR2 extends Synapse.Model{
    
    public VT_HR2() {
        this.initTable(""
                + "(SELECT CONCAT(ep.firstname,' ',ep.middlename,' ',ep.lastname,' ')as fullname,s.suffix_name, ep.civil_status_id," +
                   "ep.employee_code,ep.gender,ep.contact_number from aerolink.tbl_hr4_employee_profiles as ep inner join " +
                    "aerolink.tbl_hr1_suffix s on ep.suffix_id = s.id)as e", Boolean.TRUE);
    }
    
    
}
