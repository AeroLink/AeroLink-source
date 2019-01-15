/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Facility_Reservation_infoController implements Initializable {

    @FXML
    private ImageView facilityimage2;
    @FXML
    private JFXTextField facilityidentity;
    @FXML
    private JFXTextField facilityn;
    @FXML
    private JFXTextField rby;
    @FXML
    private JFXTextField ppose;
    @FXML
    private JFXTextField stat;
    @FXML
    private JFXDatePicker sdate;
    @FXML
    private JFXDatePicker edate;
    @FXML
    private JFXTimePicker stime;
    @FXML
    private JFXTimePicker etime;
    @FXML
    private JFXTextField cap;
    @FXML
    private JFXTextField loc;
    @FXML
    private JFXButton btndone;
    @FXML
    private JFXTextField foriddisplay;

        private Connection con  = DBconnection.con();
        private PreparedStatement pst = null;
        private ResultSet rs = null;
        private InputStream input;
        private OutputStream output;
        Facility_ReservationController frc = new Facility_ReservationController();
        
       
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton btncancel;
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    public void passtxt(String txt){
        foriddisplay.setText(txt);
        
    }
    
    public void displayinfo(){
        
        
        String query = "Select * from aerolink.admin_facility_reservation as aafr inner join aerolink.admin_facility as aaf on aafr.Facility_ID = aaf.Facility_ID where aafr.Facility_Reservation_ID = ?";
        try{
         pst = con.prepareStatement(query);
         pst.setString(1, foriddisplay.getText());
         
         rs = pst.executeQuery();
         while(rs.next()){
             facilityidentity.setText(rs.getString("Facility_ID"));
             cap.setText(rs.getString("Capacity"));
             loc.setText(rs.getString("Location"));
             facilityn.setText("Facility_Name");
             rby.setText(rs.getString("Reserved_By"));
             ppose.setText("Purpose");
             sdate.setValue(rs.getDate("Start_Date").toLocalDate());
             edate.setValue(rs.getDate("End_Date").toLocalDate());
             stime.setValue(rs.getTime("Start_Time").toLocalTime());
             etime.setValue(rs.getTime("End_Time").toLocalTime());
             stat.setText(rs.getString("Status"));
             
             input = rs.getBinaryStream("Image");
                output = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while((size = input.read(contents))!= -1){
                    output.write(contents, 0 ,size);
                }
              Image image = new Image("file:photo.jpg",facilityimage2.getFitWidth(),facilityimage2.getFitHeight(), true,true);
               facilityimage2.setImage(image);
         }
         pst.close();
         rs.close();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    
    }

    @FXML
     public void updateinfo(){
        
         String query = "update aerolink.admin_facility_reservation set Start_Date = ?, Start_Time = ?, End_Date = ?, End_Time = ? where Facility_Reservation_ID = ?";
         try{
             pst = con.prepareStatement(query);
             pst.setString(1, ((TextField)sdate.getEditor()).getText());
             pst.setString(2, ((TextField)stime.getEditor()).getText());
             pst.setString(3, ((TextField)edate.getEditor()).getText());
             pst.setString(4, ((TextField)etime.getEditor()).getText());
             pst.setString(5, foriddisplay.getText());
             
             pst.execute();
             AlertBox.display("Alert", "Update Successfully");
             Stage st =  (Stage) updatebtn.getScene().getWindow();
             
             st.close();
         }
         catch(Exception ex){
             System.err.print(ex.getMessage());
         }
       
     }
     
     public void cancelrequest(){
         String query = "update aerolink.admin_facility_reservation set Status = 'Canceled' where Facility_Reservation_ID = ? "; 
         AlertBox.confirm("Alert", "This Request will be Cancel",foriddisplay,query);
    
     }
     
     public void close(){
         AlertBox.close(btndone);
     }
         
 
    
}
