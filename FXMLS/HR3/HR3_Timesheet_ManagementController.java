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
    private TableView<?> tbl_timesheet;
    @FXML
    private TableColumn<?,?> tbl_ID;
    @FXML
    private TableColumn<?,?> tbl_Emp_Name;
    @FXML
    private TableColumn<?,?> tbl_Position;
    @FXML
    private TableColumn<?,?> tbl_Date;
    @FXML
    private TableColumn<?,?> tbl_Total;
    @FXML
    private TableColumn<?,?> tbl_Absent;
    @FXML
    private TableColumn<?,?> tbl_undertime;
    @FXML
    private TableColumn<?,?> tbl_overtime;
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
    @FXML
    private TableView<?> tbl_dailyreport;
    @FXML
    private TableColumn<?, ?> tbl_id;
    @FXML
    private TableColumn<?, ?> tbl_date;
    @FXML
    private TableColumn<?, ?> tbl_timein;
    @FXML
    private TableColumn<?, ?> tbl_timeout;
    @FXML
    private TableColumn<?, ?> tbl_total;
    @FXML
    private TableColumn<?, ?> tbl_balance;

    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

        
        
        
     
    
    
}
