/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.core1;

import Model.Core1_booking_table;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Core1_booking_formController implements Initializable {

    @FXML
    private JFXTextField txt_fn;
    @FXML
    private JFXTextField txt_mid;
    @FXML
    private JFXTextField txt_ln;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_contact;
    @FXML
    private JFXTextField txt_house;
    @FXML
    private JFXTextField txt_street;
    @FXML
    private JFXTextField txt_brgy;
    @FXML
    private JFXTextField txt_city;
    @FXML
    private JFXTextField txt_province;
    @FXML
    private JFXTextField txt_zip;
    @FXML
    private JFXTextField txt_company;
    @FXML
    private JFXTextField txt_customer_id;
    @FXML
    private JFXTextField txt_consignee_contact;
    @FXML
    private JFXTextField txt_consignee_contact2;
    @FXML
    private JFXTextField txt_consignee_contact1;
    @FXML
    private JFXTextField txt_consignee_contact11;
    @FXML
    private JFXTextField txt_consignee_contact111;
    @FXML
    private JFXTextField txt_fn1;
    @FXML
    private JFXTextField txt_mid1;
    @FXML
    private JFXTextField txt_ln1;
    @FXML
    private JFXTextField txt_email1;
    @FXML
    private JFXTextField txt_contact1;
    @FXML
    private JFXTextField txt_sender_house1;
    @FXML
    private JFXTextField txt_sender_street1;
    @FXML
    private JFXTextField txt_sender_brgy1;
    @FXML
    private JFXTextField txt_sender_city1;
    @FXML
    private JFXTextField txt_sender_province1;
    @FXML
    private JFXTextField txt_sender_zip1;
    @FXML
    private JFXTextField txt_contact121;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_clear;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    Stage stage = new Stage();
    @FXML
    private AnchorPane rootPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
    
    void setData(String customer_id, String fn , String mid ,String ln ,String email, String contact, String company, String house, String street, String brgy, String city, String province, String zip) {
        
        txt_customer_id.setText(customer_id);  
        txt_fn.setText(fn);  
        txt_mid.setText(mid);  
        txt_ln.setText(ln);  
        txt_email.setText(email);  
        txt_contact.setText(contact);  
        txt_company.setText(company);  
        txt_house.setText(house);  
        txt_street.setText(street);  
        txt_brgy.setText(brgy);
        txt_city.setText(city);  
        txt_province.setText(province);  
        txt_zip.setText(zip);  
         
        
    }
    


    @FXML
    private void UpdateButton(ActionEvent event) {
        
         if("".equals(txt_fn.getText()) || "".equals(txt_mid.getText()) || "".equals(txt_ln.getText()) || "".equals(txt_mid.getText()) || "".equals(txt_contact.getText()) || "".equals(txt_email.getText()) || "".equals(txt_company.getText()) || "".equals(txt_house.getText()) || "".equals(txt_street.getText()) || "".equals(txt_brgy.getText()) || "".equals(txt_city.getText()) || "".equals(txt_province.getText()) || "".equals(txt_zip.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Opps", "Error", "Please fill out all fields");
            alert.open();
            
             }else{
             
                    try{
                         Connection conn = DBConnector.getConnection();

                        String sql = "UPDATE `tbl_core1_customer_details` SET firstname = '"+txt_fn.getText()+"',lastname = '"+txt_ln.getText()+"'"
                                + ",middlename = '"+txt_mid.getText()+"',email = '"+txt_email.getText()+"' "
                                + ",`contact_no.` = '"+txt_contact.getText()+"',company = '"+txt_company.getText()+"' "
                                + ",house = '"+txt_house.getText()+"',street = '"+txt_street.getText()+"' "
                                + ",brgy = '"+txt_brgy.getText()+"',city = '"+txt_city.getText()+"' "
                                + ",province = '"+txt_province.getText()+"',zip = '"+txt_zip.getText()+"' "
                                     + "WHERE customer_id = '"+txt_customer_id.getText()+"' ";
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        
                        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "Saved!", "Successfully Added");
                         alert.open();
                         
                          AnchorPane newForm = FXMLLoader.load(getClass().getResource("Core1_booking.fxml"));
                                 rootPane.getChildren().setAll(newForm);
                             
                   }catch(Exception ex){
                        System.err.println(ex);
                         }
             }  
                 
    }


    @FXML
    private void clearData(ActionEvent event) {
    }

    
}
