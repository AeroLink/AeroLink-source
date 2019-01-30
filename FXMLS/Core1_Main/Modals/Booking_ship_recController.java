/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.AlertBox;
import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.combo_box_size;
import FXMLS.Core1_Main.Model.combo_item;
import FXMLS.Core1_Main.Model.db_booking;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Booking_ship_recController implements Initializable {
    
    Date date_now = new Date();
    String date_today;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private String docu_pack;
    private String date;
    private String service;
    
    ObservableList box_size  = FXCollections.observableArrayList();
    ObservableList item_type  = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lbl_name;
    private Label lbl_name_error;
    @FXML
    private TextField txt_ship_name;
    @FXML
    private TextField txt_ship_add;
    @FXML
    private TextField txt_ship_brgy;
    @FXML
    private TextField txt_ship_zip;
    @FXML
    private ComboBox<String> combo_ship_city;
    @FXML
    private ComboBox<String> combo_ship_province;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_email_error;
    @FXML
    private TextField txt_ship_email;
    @FXML
    private Label lbl_contact;
    @FXML
    private Label lbl_contact_error;
    @FXML
    private TextField txt_ship_contact;
    @FXML
    private Label lbl_name2;
    @FXML
    private Label lbl_name_error2;
    @FXML
    private TextField txt_rec_name;
    @FXML
    private Label lbl_add;
    @FXML
    private TextField txt_rec_add;
    @FXML
    private Label lbl_brgy;
    @FXML
    private TextField txt_rec_brgy;
    @FXML
    private Label lbl_zip;
    @FXML
    private TextField txt_rec_zip;
    @FXML
    private Label lbl_city;
    @FXML
    private ComboBox<String> combo_rec_city;
    @FXML
    private Label lbl_province;
    @FXML
    private ComboBox<String> combo_rec_province;
    @FXML
    private Label lbl_contact2;
    @FXML
    private Label lbl_contact_error2;
    @FXML
    private TextField txt_rec_contact;
    @FXML
    private Label lbl_name11;
    @FXML
    private Label lbl_name1;
    @FXML
    private TextField txt_quantity;
    @FXML
    private Label lbl_name111;
    @FXML
    private ComboBox<String> combo_box;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private JFXButton btn_back;
    
    @FXML
    private ComboBox<String> combo_item_type;
    @FXML
    private Label lbl_ref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillBoxSize();
        fillItemType();
        setRef();
        initClock();
        combo_ship_city.setValue("Select");
        combo_ship_province.setValue("Select");
        combo_rec_city.setValue("Select");
        combo_rec_province.setValue("Select");
       
    }   
    
    public void setData(String docu_pack , String date , String service){
        this.docu_pack = docu_pack;
        this.date = date;
        this.service = service;
        
        if("Document".equals(docu_pack)){
            combo_item_type.setDisable(true);
            combo_item_type.setValue("Documents");
            combo_box.setDisable(true);
            combo_box.setValue("Documents");
        }else if("Package".equals(docu_pack)){
            combo_item_type.setDisable(false);
            combo_box.setDisable(false);
        }
    }
    
    public void fillBoxSize(){
        
            try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core2_boxes";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                box_size.add(new combo_box_size(rs.getString("box_id"), rs.getString("box_desc")).getBox_size_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_ship_recController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_box.setItems(box_size);
        
        
    }
    
        public void fillItemType(){
        
            try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core2_itmrate";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                item_type.add(new combo_item(rs.getString("irid"), rs.getString("dec_items")).getItem_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_ship_recController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_item_type.setItems(item_type);
        
    }

    @FXML
    private void email_focus(MouseEvent event) {
            txt_ship_email.setStyle("-fx-text-fill: black"); 
            lbl_email_error.setStyle("-fx-text-fill: black");
            lbl_email.setStyle("-fx-text-fill: black");
            lbl_email_error.setText("");
    }

    @FXML
    private void contact_focus(MouseEvent event) {
            txt_ship_contact.setStyle("-fx-text-fill: black"); 
            lbl_contact_error.setStyle("-fx-text-fill: black");
            lbl_contact.setStyle("-fx-text-fill: black");
            lbl_contact_error.setText("");
    }

    @FXML
    private void contact_focus2(MouseEvent event) {
            txt_rec_contact.setStyle("-fx-text-fill: black"); 
            lbl_contact_error2.setStyle("-fx-text-fill: black");
            lbl_contact2.setStyle("-fx-text-fill: black");
            lbl_contact_error2.setText("");
    }

    @FXML
    private void confirm(ActionEvent event) {
       
            if("".equals(txt_ship_name.getText()) || "".equals(txt_ship_add.getText()) || "".equals(txt_ship_brgy.getText()) || 
           "".equals(txt_ship_zip.getText()) || "".equals(txt_ship_email.getText()) || "".equals(txt_ship_contact.getText()) || 
           "".equals(txt_rec_name.getText()) || "".equals(txt_rec_add.getText()) || "".equals(txt_rec_brgy.getText()) ||
           "".equals(txt_rec_zip.getText()) || "".equals(txt_rec_contact.getText()) || "".equals(combo_box.getValue()))
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
                                                                  + "serv_type, box, quantity, item_type, pick_update, book_date, status, ref_no) "
                                                                  + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, txt_ship_name.getText());
                        pst.setString(2, txt_ship_add.getText());
                        pst.setString(3, txt_ship_brgy.getText());
                        pst.setString(4, "");
                        pst.setString(5, "");
                        pst.setString(6, txt_ship_zip.getText());
                        pst.setString(7, txt_ship_email.getText());
                        pst.setString(8, txt_ship_contact.getText());
                        pst.setString(9, txt_rec_name.getText());
                        pst.setString(10, txt_rec_add.getText());
                        pst.setString(11, txt_rec_brgy.getText());
                        pst.setString(12, "");
                        pst.setString(13, "");
                        pst.setString(14, txt_rec_zip.getText());
                        pst.setString(15, txt_rec_contact.getText());
                        pst.setString(16, service);
                        pst.setString(17, combo_box.getValue());
                        pst.setString(18, txt_quantity.getText());
                        pst.setString(19, combo_item_type.getValue());
                        pst.setString(20, date);
                        pst.setString(21, date_today);
                        pst.setString(22, "Pending");
                        pst.setString(23, lbl_ref.getText());
                        
                        
                        pst.execute();
                        
                        setRef();

                        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "Saved!", "Successfully Added");
                         alert.open();
                   
                         
                   }catch(SQLException ex){
                        System.err.println(ex);
                   }
                }
            } 
            
        
        
        
    }

    @FXML
    private void back(ActionEvent event) {
        
        AlertBox a =  new AlertBox();
        Booking_selectionController ad = new Booking_selectionController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/booking_selection.fxml", ad);
        
        AlertBox.close(btn_back);
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
            Logger.getLogger(Booking_ship_recController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
    private void initClock() {

    Format formatter = new SimpleDateFormat("MMM-dd-yyyy");
    date_today = formatter.format(date_now);
}  

    @FXML
    private void name_focus(MouseEvent event) {
    }
}
