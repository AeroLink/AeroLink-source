/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import FXMLS.Administrative.AlertBox;
import FXMLS.Administrative.DBconnection;
import FXMLS.Administrative.Facility_ReservationController;
import FXMLS.Administrative.tbl_facility_reservationreq;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class RequestForm_FacilityController implements Initializable {

    
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    @FXML
    private JFXDatePicker startdate;
    @FXML
    private JFXComboBox<String> urgencybox;
    ObservableList<String> urgencybox1 = FXCollections.observableArrayList("High","Medium","Low");
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton edit1;
    @FXML
    private JFXDatePicker enddate;
    @FXML
    private JFXComboBox<String> facilitybox;
    ObservableList<String> facilitybox1 = FXCollections.observableArrayList();
    @FXML
    private JFXTimePicker starttime;
    @FXML
    private JFXTimePicker endtime;
    @FXML
    private ImageView frreservationiview;
    @FXML
    private JFXTextField purposebox;
    @FXML
    private JFXTextField facilityidtxt;
    @FXML
    private TextField capacityfield;
    @FXML
    private TextField locationfield;
    @FXML
    private JFXTextField facilityid;
    @FXML
    private JFXComboBox<String> reservebox;
    ObservableList<String> reservebox1 = FXCollections.observableArrayList("HR1","HR2", "HR3","HR4","Logistic 1","Logistic 2", "Core 1", "Core 2");
    @FXML
    private TableView<tbl_facility_reservationreq> reservetable;
    ObservableList<tbl_facility_reservationreq> reservetable1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> rfacility;
    @FXML
    private TableColumn<?, ?> rrdate;
    @FXML
    private TableColumn<?, ?> rstatus;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayreq();
        selectbox();
        reservebox.setItems(reservebox1);
        urgencybox.setItems(urgencybox1);
    }    


    
    @FXML
    public void facilityreservationselect(){
        try {
             String query = "select *,aafrf.FacilityID from aerolink.admin_facility_reservation_facility as aafrf inner join aerolink.tbl_log1_AssetFacility as atla on aafrf.FacilityID = atla.FacilityID where atla.FacilityName = '"+facilitybox.getSelectionModel().getSelectedItem()+"' ";
       
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
          while(rs.next()){
                facilityidtxt.setText(rs.getString("FacilityID"));
                locationfield.setText(rs.getString("FacilityRoomNumber"));
                capacityfield.setText(rs.getString("Capacity"));
           
                InputStream input = rs.getBinaryStream("Image");
                OutputStream output = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while((size = input.read(contents))!= -1){
                    output.write(contents, 0 ,size);
                }
               Image image = new Image("file:photo.jpg",frreservationiview.getFitWidth(),frreservationiview.getFitHeight(), true,true);
               frreservationiview.setImage(image);
               
            }
        
            
        }catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    @FXML
    public void insertfacilityresevation(){
        try{
            String query = "insert into aerolink.admin_facility_reservation values(?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, facilityidtxt.getText());
            pst.setString(2, purposebox.getText());
            pst.setString(3, startdate.getValue().toString());
            pst.setString(4, starttime.getValue().toString());
            pst.setString(5, enddate.getValue().toString());
            pst.setString(6, endtime.getValue().toString());
            pst.setString(7, reservebox.getSelectionModel().getSelectedItem());
            pst.setString(8, urgencybox.getSelectionModel().getSelectedItem());
            pst.setString(9, "Pending");
            pst.execute();
            
            AlertBox.display("Alert", "Request Pending");
            AlertBox.close(edit);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    public void close(){
        AlertBox.close(edit1);
        }
    
    public void selectbox(){
        facilitybox1.clear();
            try{
                String query = "select *,aafrf.FacilityID from aerolink.admin_facility_reservation_facility as aafrf inner join aerolink.tbl_log1_AssetFacility as atla on aafrf.FacilityID = atla.FacilityID where aafrf.Status = 'Ready to Use' ";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    facilitybox1.add(rs.getString("FacilityName"));
                }
                facilitybox.setItems(facilitybox1);
            }catch(Exception ex){}
    }
    
    
    private void displayreq(){
            rfacility.setCellValueFactory(new PropertyValueFactory<>("rfacility"));
            rrdate.setCellValueFactory(new PropertyValueFactory<>("rdate"));
            rstatus.setCellValueFactory(new PropertyValueFactory<>("rstatus"));
            
        reservetable1.clear();
        try{
            String query = "Select * from aerolink.admin_facility_reservation as aafr inner join aerolink.tbl_log1_AssetFacility as atla on aafr.FacilityID = atla.FacilityID where Status = 'Pending' ";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                reservetable1.add(new tbl_facility_reservationreq(rs.getString("FacilityName"),""+rs.getDate("Start_Date"),rs.getString("Status")));
            }
            reservetable.setItems(reservetable1);
        }catch(Exception ex){}
    }
   
}
