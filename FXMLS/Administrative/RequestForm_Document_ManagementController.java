/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class RequestForm_Document_ManagementController implements Initializable {

    
    Connection con = DBconnection.con();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private JFXTextArea txtdescription;
    @FXML
    private JFXTextField txtreqby;
    @FXML
    private TextField txttitle;
    @FXML
    private JFXComboBox<String> combodepartment;
    ObservableList<String> departmentbox = FXCollections.observableArrayList("HR","Logistics","Core","Administrative");
    @FXML
    private JFXComboBox<String> comboto;
    ObservableList<String> comto = FXCollections.observableArrayList("Document Management","Legal Management");
    @FXML
    private JFXButton btnsubmit;
    @FXML
    private JFXButton btncancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combodepartment.setItems(departmentbox);
        comboto.setItems(comto);
    }
    
    
    public void insertdata(){
        try{
            String query = "insert into aerolink.admin_document_request values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, txttitle.getText());
            pst.setString(2, txtdescription.getText());
           
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(3, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(4, date);
            
            pst.setString(5, comboto.getSelectionModel().getSelectedItem());
            pst.setString(6, txtreqby.getText());
            pst.setString(7, "Pending");
            
            pst.execute();
            AlertBox.display("Alert", "Request is now Pending");
            AlertBox.close(btnsubmit);
            
        }catch(Exception ex){
            ex.getMessage();
        }
        
        
    }
    
    public void cancel(){
        AlertBox.close(btncancel);
    }
    
    
}
