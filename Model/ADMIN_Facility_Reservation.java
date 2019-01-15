/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Onodera
 */
public class ADMIN_Facility_Reservation extends Synapse.Model{
    
    public ADMIN_Facility_Reservation(){
    setColumns("Facility_Reseravation_ID","Reserved_Facility","Purpose","Start_Date","Start_Time","End_Date","End_Time","Reserved_By","Urgency_Level");
    this.initTable("admin_facility_reservation");
    }
}
