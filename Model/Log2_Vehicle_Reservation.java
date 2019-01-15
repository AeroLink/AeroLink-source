/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Randelle
 */
public class Log2_Vehicle_Reservation extends Synapse.Model{
    
    public Log2_Vehicle_Reservation() {
        setColumns("purpose","location","typeofvehicle","vehicleid");
        this.initTable("aerolink.tbl_log2_reservation");
    }
    
}
