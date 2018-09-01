/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import Model.HR2_Learning_Management;
import Model.HR3_Shift_and_Scheduling;
import Synapse.DB.MYSQL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Shift_and_SchedulingController implements Initializable {

Connection connection=null;
PreparedStatement pst = null;
ResultSet rs = null;

    
       
    
    @FXML
    private TableView<ModelTable> tablesns;
    @FXML
    private TableColumn<ModelTable, String> tbl_ID;
    @FXML
    private TableColumn<ModelTable, String> tbl_name;
    @FXML
    private TableColumn<ModelTable, String> tbl_position;
    @FXML
    private TableColumn<ModelTable, String> tbl_status;
    @FXML
    private TableColumn<ModelTable, String> tblschedule;
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXTextField txtid;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtposition;
    @FXML
    private JFXComboBox<String> cmbstatus;
    @FXML
    private JFXComboBox<?> cmbduty;
    @FXML
    private Button btnsave;
    
ObservableList<String> sl = FXCollections.observableArrayList("Operation","Administrator");
ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cmbstatus.setItems(sl);
                  cmbstatus.setPromptText("Select Department");
                  
                   btnsave.setOnMouseClicked(e -> Save());

        
         try{
             Connection CONNECTION = DBConnector.getConnection();
             ResultSet rs = CONNECTION.createStatement().executeQuery("Select * from tbl_hr3_shift");
    
             while (rs.next()){
                 oblist.add(new ModelTable(rs.getString("ID"),rs.getString("Name"),rs.getString("Position"),rs.getString("Department"),rs.getString("Schedule")));
             }
            }catch(SQLException ex){
                    Logger.getLogger(HR3_Shift_and_SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
}

        
        
        
      tbl_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
      tbl_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
      tbl_position.setCellValueFactory(new PropertyValueFactory<>("Position"));
      tbl_status.setCellValueFactory(new PropertyValueFactory<>("Department"));
      tblschedule.setCellValueFactory(new PropertyValueFactory<>("Schedule"));
          
    tablesns.setItems(oblist);
    }    
    
    
    @FXML
    private void btnsaves(MouseEvent event) {
         
    }
    
    public void Save()
    {
            HR3_Shift_and_Scheduling sns = new HR3_Shift_and_Scheduling();
            
                try {
                    String[][] tablesns =
                        {
                            {"ID" , txtid.getText()},
                            {"Name" , txtname.getText()},
                            {"Position" , txtposition.getText()},
                            {"ID" , cmbstatus.getValue().toString()},
                            {"Schedule" , cmbduty.getValue().toString()},
                    
                        };
                                    sns.insert(tablesns);
                                JOptionPane.showMessageDialog(null,"saved");
                }catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }
    }
}
