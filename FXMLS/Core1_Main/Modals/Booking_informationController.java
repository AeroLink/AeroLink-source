/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.AlertBox;
import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.combo_provinces;
import FXMLS.Core1_Main.Model.combo_box_size;
import FXMLS.Core1_Main.Model.combo_city;
import FXMLS.Core1_Main.Model.combo_service_type;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Booking_informationController implements Initializable {
    
   
//----------------------------------------------------------------------------------
    
//    public static Boolean modalOpen = true;

    Date date = new Date();
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    ObservableList service_type  = FXCollections.observableArrayList();
    ObservableList box_size  = FXCollections.observableArrayList();
    ObservableList ship_province  = FXCollections.observableArrayList();
    ObservableList ship_city  = FXCollections.observableArrayList();
    ObservableList rec_province  = FXCollections.observableArrayList();
    ObservableList rec_city  = FXCollections.observableArrayList();
    
    private String insuranceLabel;
    private String liabilityLabel;

//----------------------------------------------------------------------------------
    
    @FXML
    private ComboBox<String> combo_service_type;
    @FXML
    private ComboBox<String> combo_box_size;
    @FXML
    private TextField txt_quantity;
    @FXML
    private TextField txt_ship_name;
    @FXML
    private TextField txt_ship_add;
    @FXML
    private ComboBox<String> combo_ship_city;
    @FXML
    private ComboBox<String> combo_ship_province;
    @FXML
    private TextField txt_ship_zip;
    @FXML
    private TextField txt_ship_email;
    @FXML
    private TextField txt_ship_contact;
    @FXML
    private TextField txt_rec_name;
    @FXML
    private TextField txt_rec_add;
    @FXML
    private ComboBox<String> combo_rec_city;
    @FXML
    private ComboBox<String> combo_rec_province;
    @FXML
    private TextField txt_rec_contact;
    @FXML
    private TextField txt_rec_zip;
    @FXML
    private RadioButton insu_yes;
    @FXML
    private RadioButton insu_no;
    @FXML
    private RadioButton lia_yes;
    @FXML
    private RadioButton lia_no;
    @FXML
    private Label lbl_ref;
    @FXML
    private Label lbl_date;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lbl_add;
    @FXML
    private Label lbl_brgy;
    @FXML
    private Label lbl_city;
    @FXML
    private Label lbl_province;
    @FXML
    private Label lbl_zip;
    @FXML
    private Label lbl_name_error;
    @FXML
    private Label lbl_email_error;
    @FXML
    private Label lbl_contact_error;
    @FXML
    private Label lbl_name_error2;
    @FXML
    private Label lbl_contact_error2;
    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_contact;
    @FXML
    private Label lbl_name2;
    @FXML
    private Label lbl_contact2;
    @FXML
    private TextField txt_ship_brgy;
    @FXML
    private TextField txt_rec_brgy;
    @FXML
    private JFXButton cancel;
    @FXML
    private ToggleGroup insurance;
    @FXML
    private ToggleGroup liability;
    
    
//----------------------------------------------------------------------------------

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillServiceType();
        fillBoxSize();
        fillProvince();
        fillCity();
        radioGroups();
        windowOpen();
        initClock();
        setRef();
        combo_box_size.setValue("Select");
        
    }   
    
//----------------------------------------------------------------------------------    
    
     public void fillServiceType(){
        
        try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from tbl_core2_services";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                service_type.add(new combo_service_type(rs.getString("serv_id"), rs.getString("serv_name")).getServ_type_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_service_type.setItems(service_type);
    }
     
//----------------------------------------------------------------------------------     
    
    public void fillBoxSize(){
        
        try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from tbl_core2_boxes";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                box_size.add(new combo_box_size(rs.getString("box_no"), rs.getString("box_description")).getBox_size_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_box_size.setItems(box_size);
        
    }
    
    
//----------------------------------------------------------------------------------   

public void fillProvince(){
    
    ship_province.clear();
    rec_province.clear();

        try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from tbl_core2_provinces order by province_name asc";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ship_province.add(new combo_provinces(rs.getString("province_no"), rs.getString("province_name")).getProvince_name());
                rec_province.add(new combo_provinces(rs.getString("province_no"), rs.getString("province_name")).getProvince_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_ship_province.setItems(ship_province);
        combo_rec_province.setItems(rec_province);
        
    }
    
//----------------------------------------------------------------------------------  

public void fillCity(){
    ship_city.clear();
    rec_city.clear();
        
        try {   
            conn = DBConnector.getConnection();
            
            String query = "select DISTINCT city_name from tbl_core2_cities order by city_name asc";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ship_city.add(new combo_city(rs.getString("city_name"), rs.getString("city_name")).getCity_name());
                rec_city.add(new combo_city(rs.getString("city_name"), rs.getString("city_name")).getCity_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_ship_city.setItems(ship_city);
        combo_rec_city.setItems(rec_city);
        
    }
    
//----------------------------------------------------------------------------------  
    @FXML
    private void btn_confirm(ActionEvent event) {
        
        radioGroups();
       
            if("".equals(txt_ship_name.getText()) || "".equals(txt_ship_add.getText()) || "".equals(txt_ship_brgy.getText()) || "Select".equals(combo_ship_city.getValue()) || 
           "Select".equals(combo_ship_province.getValue()) || "".equals(txt_ship_zip.getText()) || "".equals(txt_ship_email.getText()) || "".equals(txt_ship_contact.getText()) || 
           "".equals(txt_rec_name.getText()) || "".equals(txt_rec_add.getText()) || "".equals(txt_rec_brgy.getText()) || "Select".equals(combo_rec_city.getValue()) || 
           "Select".equals(combo_rec_province.getValue()) || "".equals(txt_rec_zip.getText()) || "".equals(txt_rec_contact.getText()) ||
           "Select".equals(combo_service_type.getValue()) || "Select".equals(combo_box_size.getValue()) || "".equals(insuranceLabel) || "".equals(liabilityLabel))
                     {

            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Opps", "Error", "Please fill out all fields");
            alert.open();
    
               }else{
                    
                if(!validateEmail()){
                    
                    txt_ship_email.setStyle("-fx-text-fill: red"); 
                    lbl_email_error.setStyle("-fx-text-fill: red");
                    lbl_email.setStyle("-fx-text-fill: red");
                    lbl_email_error.setText("invalid email address");
                    
                }
                
                
               if(!validateShip_Contact()){
                    
                    txt_ship_contact.setStyle("-fx-text-fill: red"); 
                    lbl_contact_error.setStyle("-fx-text-fill: red");
                    lbl_contact.setStyle("-fx-text-fill: red");
                    lbl_contact_error.setText("invalid contact no.");
            
                    
                }
                
              if(!validateRec_Contact()){
                    
                    txt_rec_contact.setStyle("-fx-text-fill: red"); 
                    lbl_contact_error2.setStyle("-fx-text-fill: red");
                    lbl_contact2.setStyle("-fx-text-fill: red");
                    lbl_contact_error2.setText("invalid contact no.");
            
                    
                }
                else{
                  
                    try {

                        conn = DBConnector.getConnection();

                        String sql = "INSERT INTO aerolink.tbl_core1_booking(ship_name, ship_address, ship_brgy, ship_city, ship_province, ship_zip, ship_email, ship_contact, "
                                                                  + "rec_name, rec_address, rec_brgy, rec_city, rec_province, rec_zip, rec_contact, "
                                                                  + "serv_type, box, quantity, insurance, liability, book_date, status, ref_no) "
                                                                  + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, txt_ship_name.getText());
                        pst.setString(2, txt_ship_add.getText());
                        pst.setString(3, txt_ship_brgy.getText());
                        pst.setString(4, combo_ship_city.getValue());
                        pst.setString(5, combo_ship_province.getValue());
                        pst.setString(6, txt_ship_zip.getText());
                        pst.setString(7, txt_ship_email.getText());
                        pst.setString(8, txt_ship_contact.getText());
                        pst.setString(9, txt_rec_name.getText());
                        pst.setString(10, txt_rec_add.getText());
                        pst.setString(11, txt_rec_brgy.getText());
                        pst.setString(12, combo_rec_city.getValue());
                        pst.setString(13, combo_rec_province.getValue());
                        pst.setString(14, txt_rec_zip.getText());
                        pst.setString(15, txt_rec_contact.getText());
                        pst.setString(16, combo_service_type.getValue());
                        pst.setString(17, combo_box_size.getValue());
                        pst.setString(18, txt_quantity.getText());
                        pst.setString(19, insuranceLabel);
                        pst.setString(20, liabilityLabel);
                        pst.setString(21, lbl_date.getText());
                        pst.setString(22, "Pending");
                        pst.setString(23, lbl_ref.getText());
                        
                        
                        pst.execute();
                        
                        setRef();
                        clearData();
                        fillProvince();
                        fillCity();

                        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "Saved!", "Successfully Added");
                         alert.open();
                   
                         
                   }catch(SQLException ex){
                        System.err.println(ex);
                   }
                }
            } 
            
        }
    
//----------------------------------------------------------------------------------    
    
    public void clearData(){
        
        txt_ship_name.setText("");
        txt_ship_add.setText("");
        combo_ship_city.setValue("");
        combo_ship_province.setValue("Select");
        txt_ship_zip.setText("");
        txt_ship_email.setText("");
        txt_ship_contact.setText("");
        txt_rec_name.setText("");
        txt_rec_add.setText("");
        combo_rec_city.setValue("");
        combo_rec_province.setValue("Select");
        txt_rec_zip.setText("");
        txt_rec_contact.setText("");
        combo_service_type.setValue("Select");
        combo_box_size.setValue("Select");
        txt_quantity.setText("");
    }
    
//----------------------------------------------------------------------------------    
    
    public void radioGroups(){
        
        insu_yes.setOnAction(e -> {
            insuranceLabel = insu_yes.getText();
        });
        
        insu_no.setOnAction(e -> {
            insuranceLabel = insu_no.getText();
        });
        
         lia_yes.setOnAction(e -> {
            liabilityLabel = lia_yes.getText();
        });
        
        lia_no.setOnAction(e -> {
            liabilityLabel = lia_no.getText();
        });
    }
    
    
//----------------------------------------------------------------------------------    
    
    public void windowOpen(){
        
            combo_service_type.setValue("Door to Door");
            lbl_add.setText("Home No. / Street *");
            lbl_brgy.setText("Barangay *");
            lbl_city.setText("City / Municipal *");
            lbl_province.setText("Province *");
            lbl_zip.setText("Zip Code *");
            txt_rec_add.setText("");
            combo_rec_city.setValue("Select");
            combo_rec_province.setValue("Select");
            txt_rec_zip.setText("");
            
    }
    
    private void initClock() {

    Format formatter = new SimpleDateFormat("MMM-dd-yyyy");
    String s = formatter.format(date);
    lbl_date.setText(s);
}
    
    public void setRef(){
        
        String ref_no;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select max(book_no) as book_no from aerolink.tbl_core1_booking";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
               ref_no = String.valueOf(rs.getInt("book_no") + 1);
               lbl_ref.setText("AERO" + ref_no);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
      
    private boolean validateEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9. ]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txt_ship_email.getText());
        if(m.find() && m.group().equals(txt_ship_email.getText())){
            return true;
        }else{
            return false;
        }
    }
    
//    private boolean validateShip_Name(){
//        Pattern p = Pattern.compile("[a-zA-Z]+");
//        Matcher m = p.matcher(txt_ship_name.getText());
//        if(m.find() && m.group().equals(txt_ship_name.getText())){
//            return true;
//        }else{
//            
//            return false;
//        }
//    }
//    
//    private boolean validateRec_Name(){
//        Pattern p = Pattern.compile("[a-zA-Z]+");
//        Matcher m = p.matcher(txt_rec_name.getText());
//        if(m.find() && m.group().equals(txt_rec_name.getText())){
//            return true;
//        }else{
//            
//            return false;
//        }
//    }
    
    private boolean validateShip_Contact(){
        Pattern p = Pattern.compile("^(09|\\+639)\\d{9}$");
        Matcher m = p.matcher(txt_ship_contact.getText());
        if(m.find() && m.group().equals(txt_ship_contact.getText())){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean validateRec_Contact(){
        Pattern p = Pattern.compile("^(09|\\+639)\\d{9}$");
        Matcher m = p.matcher(txt_rec_contact.getText());
        if(m.find() && m.group().equals(txt_rec_contact.getText())){
            return true;
        }else{
            
            return false;
        }
    }

    @FXML
    private void email_focus(MouseEvent event) {
        
            txt_ship_email.setStyle("-fx-text-fill: black"); 
            lbl_email_error.setStyle("-fx-text-fill: black");
            lbl_email.setStyle("-fx-text-fill: black");
            lbl_email_error.setText("");
    }

    @FXML
    private void name_focus(MouseEvent event) {
        
            txt_ship_name.setStyle("-fx-text-fill: black"); 
            lbl_name_error.setStyle("-fx-text-fill: black");
            lbl_name.setStyle("-fx-text-fill: black");
            lbl_name_error.setText("");
        
    }

    @FXML
    private void contact_focus(MouseEvent event) {
        
            txt_ship_contact.setStyle("-fx-text-fill: black"); 
            lbl_contact_error.setStyle("-fx-text-fill: black");
            lbl_contact.setStyle("-fx-text-fill: black");
            lbl_contact_error.setText("");
        
    }

    @FXML
    private void name_focus2(MouseEvent event) {
            
            txt_rec_name.setStyle("-fx-text-fill: black"); 
            lbl_name_error2.setStyle("-fx-text-fill: black");
            lbl_name2.setStyle("-fx-text-fill: black");
            lbl_name_error2.setText("");
            
    }

    @FXML
    private void contact_focus2(MouseEvent event) {
        
            txt_rec_contact.setStyle("-fx-text-fill: black"); 
            lbl_contact_error2.setStyle("-fx-text-fill: black");
            lbl_contact2.setStyle("-fx-text-fill: black");
            lbl_contact_error2.setText("");
        
    }    
    
    @FXML
    public void close(){
        AlertBox.close(cancel);
    }

    @FXML
    private void setDisabled(ActionEvent event) {
    }
}

    