/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Time_and_AttendanceController implements Initializable {

    @FXML
    private TableView<AttendanceTable> tbl_attendance;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_ID;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_fname;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_mname;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_lname;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_position;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_depart;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_date;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_timein;
    @FXML
    private TableColumn<AttendanceTable , String> tbl_timeout;
    @FXML
    private JFXTextField txt_ID;
    @FXML
    private JFXButton btn_search;

    ObservableList<AttendanceTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try{
             Connection CONNECTION = DBConnector.getConnection();
             ResultSet rs = CONNECTION.createStatement().executeQuery("Select * from tbl_hr3_attendance");
    
             while (rs.next()){
                 oblist.add(new AttendanceTable(rs.getString("ID"),rs.getString("First_Name"),rs.getString("Middle_Name"),rs.getString("Last_Name"),rs.getString("Position"),rs.getString("Department"),rs.getString("Date"),rs.getString("Time_In"),rs.getString("Time_Out")));
             }
            }catch(SQLException ex){
                    Logger.getLogger(HR3_Time_and_AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
}

        
        
        
      tbl_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
      tbl_fname.setCellValueFactory(new PropertyValueFactory<>("First_Name"));
      tbl_mname.setCellValueFactory(new PropertyValueFactory<>("Middle_Name"));
      tbl_lname.setCellValueFactory(new PropertyValueFactory<>("Last_Name"));
      tbl_position.setCellValueFactory(new PropertyValueFactory<>("Position"));
      tbl_depart.setCellValueFactory(new PropertyValueFactory<>("Department"));
      tbl_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
      tbl_timein.setCellValueFactory(new PropertyValueFactory<>("Time_In"));
      tbl_timeout.setCellValueFactory(new PropertyValueFactory<>("Time_Out"));
          
    tbl_attendance.setItems(oblist);
    }    
    
}
