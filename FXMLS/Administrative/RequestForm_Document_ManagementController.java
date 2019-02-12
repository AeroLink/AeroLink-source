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

    
    private Connection con = DBconnection.con();
    private PreparedStatement pst;
    @FXML
    private JFXTextArea descriptiontxt;
    private JFXTextField requesttxt;
    @FXML
    private TextField reqtitle;
    private JFXComboBox<String> combodep;
    @FXML
    private JFXComboBox<String> comboto;
    @FXML
    private JFXButton btntakephoto13;
    @FXML
    private JFXButton btntakephoto131;
    ObservableList<String> department = FXCollections.observableArrayList("Legal Management","Document Management");
    @FXML
    private JFXComboBox<String> comboreq;
    ObservableList<String> comboreq1 = FXCollections.observableArrayList("HR","Logistic","Core");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboto.setItems(department);
        comboreq.setItems(comboreq1);
    }   
    
    
    @FXML
    public void insert(){
        try{
            String insert = "insert into aerolink.admin_document_request values(?,?,?,?,?,?)";
            pst = con.prepareStatement(insert);
            
            pst.setString(1, reqtitle.getText());
           
            pst.setString(2, descriptiontxt.getText());
           java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(3, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(4, date);
            pst.setString(5, comboreq.getSelectionModel().getSelectedItem());
            pst.setString(6, "Pending");
            
            pst.execute();
            
            AlertBox.display("Alert","Your Request is now Pending");
            AlertBox.close(btntakephoto13);
        }catch(Exception ex){
            AlertBox.display("Alert", "Data cannot be empty");
        }
    }
    
    @FXML
    public void close(){
        AlertBox.close(btntakephoto131);
    }
    
}
