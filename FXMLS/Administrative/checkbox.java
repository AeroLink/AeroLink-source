/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import FXMLS.Administrative.Facility_ReservationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


/**
 *
 * @author Onodera
 */
public class checkbox {
    
    
    private static Connection con = DBconnection.con();
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;
    
    private static ObservableList<ADMINfacility> adm = FXCollections.observableArrayList();
    private static ObservableList<String> samp = FXCollections.observableArrayList();
    
    public static void Arrangement(CheckBox jan,CheckBox feb, CheckBox march,CheckBox april,CheckBox may,CheckBox june,CheckBox july,CheckBox august,CheckBox september,CheckBox october,CheckBox november,CheckBox december, TableView tbl ){
        
        //January
       if(jan.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-01-01' and '2019-01-31' and Status = 'Approved' or Start_Date between '2019-01-01' and '2019-01-31' and Status = 'Canceled'  ");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }
           tbl.setItems(adm);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
       }
       
       //February
       else if(feb.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-02-01' and '2019-02-28' and Status = 'Approved' or Start_Date between '2019-02-01' and '2019-02-28' and Status = 'Canceled' ");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
        //March
        else if(march.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-03-01' and '2019-03-31' and Status = 'Approved' or Start_Date between '2019-03-01' and '2019-03-31' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(april.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-04-01' and '2019-04-30' and Status = 'Approved' or Start_Date between '2019-04-01' and '2019-04-30' and Status = 'Canceled' ");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(may.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-05-01' and '2019-05-31' and Status = 'Approved' or Start_Date between '2019-05-01' and '2019-05-31' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(june.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-06-01' and '2019-06-30' and Status = 'Approved' or Start_Date between '2019-06-01' and '2019-06-30' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(july.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-07-01' and '2019-07-31' and Status = 'Approved' or Start_Date between '2019-07-01' and '2019-07-31' and Status = 'Canceled' ");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(august.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-08-01' and '2019-08-31' and Status = 'Approved' or Start_Date between '2019-08-01' and '2019-08-31' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(september.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-09-01' and '2019-09-30' and Status = 'Approved' or Start_Date between '2019-09-01' and '2019-09-30' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(october.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-10-01' and '2019-10-31' and Status = 'Approved' or Start_Date between '2019-10-01' and '2019-10-31' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(november.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-11-01' and '2019-11-30' and Status = 'Approved' or Start_Date between '2019-11-01' and '2019-11-30' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
        
         //March
        else if(december.isSelected()){
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Start_Date between '2019-12-01' and '2019-12-31' and Status = 'Approved' or Start_Date between '2019-12-01' and '2019-12-31' and Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        }tbl.setItems(adm);
        } 
        catch (SQLException ex) {System.out.print(ex.getMessage());           }
       }
       
       
       //all reserved
       else{
          adm.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Status = 'Approved' or Status = 'Canceled'");
            rs = pst.executeQuery();
            while(rs.next()){
            adm.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),rs.getString("Facility_ID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        } 
            tbl.setItems(adm);
        } 
        catch (SQLException ex){ System.out.print(ex.getMessage());}
     }
    
    
    
    }
   
}
