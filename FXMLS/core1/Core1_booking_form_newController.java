/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.core1;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Core1_booking_form_newController implements Initializable {

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
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_clear;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txt_weight;
    @FXML
    private JFXComboBox<?> txt_type;
    @FXML
    private JFXTextField txt_quantity;
    @FXML
    private JFXTextField txt_length;
    @FXML
    private JFXTextField txt_width;
    @FXML
    private JFXTextField txt_height;
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    
    

    @FXML
    private void saveButton(ActionEvent event) {
        
        
         if("".equals(txt_fn.getText()) || "".equals(txt_mid.getText()) || "".equals(txt_ln.getText()) || "".equals(txt_mid.getText()) || "".equals(txt_contact.getText()) || "".equals(txt_email.getText()) || "".equals(txt_company.getText()) || "".equals(txt_house.getText()) || "".equals(txt_street.getText()) || "".equals(txt_brgy.getText()) || "".equals(txt_city.getText()) || "".equals(txt_province.getText()) || "".equals(txt_zip.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Opps", "Error", "Please fill out all fields");
            alert.open();
             }else{
                    try{
    
                        Connection conn = DBConnector.getConnection();

                        String sql = "INSERT INTO `tbl_core1_customer_details` (`firstname`, `middlename`, `lastname`, `email`, `contact_no.`, `company`, `house`, `street`, `brgy`, `city`, `province`, `zip`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, txt_fn.getText());
                        pst.setString(2, txt_mid.getText());
                        pst.setString(3, txt_ln.getText());
                        pst.setString(4, txt_email.getText());
                        pst.setString(5, txt_contact.getText());
                        pst.setString(6, txt_company.getText());
                        pst.setString(7, txt_house.getText());
                        pst.setString(8, txt_street.getText());
                        pst.setString(9, txt_brgy.getText());
                        pst.setString(10, txt_city.getText());
                        pst.setString(11, txt_province.getText());
                        pst.setString(12, txt_zip.getText());
                        
                        
                        
                         pst.execute();
                         
                         clearData();
                         
                         
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
    private void clearData() {
                
        try{            
                       
    
                        Connection conn = DBConnector.getConnection();

                        String query = "INSERT INTO `tbl_core1_package_details` (`pack_quantity`, `dimension(l*w*h)`, `weight(kg)`, customer_id) VALUES (?,?,?,?);";
                        pst = conn.prepareStatement(query);
                        
                        
                        pst.setString(1, txt_quantity.getText());
                        pst.setString(2, txt_length.getText() + txt_width.getText() + txt_height.getText());
                        pst.setString(3, txt_weight.getText());
                        pst.setString(4, txt_customer_id.getText());
                        
                         pst.execute();
        
         
    }catch(Exception ex){
                        System.err.println(ex);
                         }
        
        
        
        
    
}
}