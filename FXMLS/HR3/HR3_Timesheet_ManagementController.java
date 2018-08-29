/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Timesheet_ManagementController implements Initializable {

    @FXML
    private TableView<TimeSheetTable> tbl_timesheet;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_ID;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Emp_Name;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Position;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Depart;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Date;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Total;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_Absent;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_undertime;
    @FXML
    private TableColumn<TimeSheetTable , String> tbl_overtime;
    @FXML
    private JFXTextField txt_ID;
    @FXML
    private JFXTextField txt_position;
    @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXTextField txt_depart;
    @FXML
    private JFXDatePicker dt_start;
    @FXML
    private JFXDatePicker dt_end;
    @FXML
    private JFXTextField txt_search;
    @FXML
    private Button btn_record;

    ObservableList<TimeSheetTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try{
             Connection CONNECTION = DBConnector.getConnection();
             ResultSet rs = CONNECTION.createStatement().executeQuery("Select * from tbl_hr3_timesheet");
    
             while (rs.next()){
                 oblist.add(new TimeSheetTable(rs.getString("ID"),rs.getString("Emp_Name"),rs.getString("Position"),rs.getString("Department"),rs.getString("Date"),rs.getString("Total_Hours"),rs.getString("Absent"),rs.getString("Undertime"),rs.getString("Overtime")));
             }
            }catch(SQLException ex){
                    Logger.getLogger(HR3_Timesheet_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
}

        
        
        
      tbl_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
      tbl_Emp_Name.setCellValueFactory(new PropertyValueFactory<>("Emp_Name"));
      tbl_Position.setCellValueFactory(new PropertyValueFactory<>("Position"));
      tbl_Depart.setCellValueFactory(new PropertyValueFactory<>("Department"));
      tbl_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
      tbl_Total.setCellValueFactory(new PropertyValueFactory<>("Total_Hours"));
      tbl_Absent.setCellValueFactory(new PropertyValueFactory<>("Absent"));
      tbl_undertime.setCellValueFactory(new PropertyValueFactory<>("Undertime"));
      tbl_overtime.setCellValueFactory(new PropertyValueFactory<>("Overtime"));
          
    tbl_timesheet.setItems(oblist);
    }    
    
    
}
