/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_Temp_Facilities extends Synapse.Model{
    
    public HR2_Temp_Facilities(){
        setColumns("FacilityID","FacilityName","FacilityStatus");
        this.initTable("aerolink.tbl_log1_AssetFacility");
    }
    
}
