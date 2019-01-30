/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.AlertBox;
import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.combo_service_type;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Booking_selectionController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    ObservableList service_type  = FXCollections.observableArrayList();
    
    private String ship_doc_select;
    private String ship_pack_select;

    @FXML
    private ToggleGroup group1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private RadioButton ship_doc;
    @FXML
    private RadioButton ship_pack;
    @FXML
    private DatePicker txt_date;
    @FXML
    private ComboBox<String> combo_service;
    private TextArea txt_description;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private JFXButton btn_cancel;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datedisable();
        fillServiceType();
        radioGroups();
        setDisabledradio();
    }    
    
    public void datedisable(){
        
        txt_date.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 1 );
        }
    });
        
    }

    @FXML
    private void confirm(ActionEvent event) {
        
        radioGroups();
        
       if("Document".equals(ship_doc_select)){
           
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("/FXMLS/Core1_Main/Modals/booking_ship_rec.fxml"));
        try{
            load.load(); 
                Booking_ship_recController vcc = load.getController();
                Parent p = load.getRoot();
                Stage st = new Stage();
                st.setScene(new Scene(p));
                st.setResizable(false);
                st.initStyle(StageStyle.DECORATED);
                st.show();
                vcc.setData(ship_doc.getText(), txt_date.getValue().toString(), combo_service.getValue());
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
                AlertBox.close(btn_confirm);
        
       }else if("Package".equals(ship_pack_select)){
           
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/FXMLS/Core1_Main/Modals/booking_ship_rec.fxml"));
            try{
                load.load(); 
                    Booking_ship_recController vcc = load.getController();
                    Parent p = load.getRoot();
                    Stage st = new Stage();
                    st.setScene(new Scene(p));
                    st.setResizable(false);
                    st.initStyle(StageStyle.DECORATED);
                    st.show();
                    vcc.setData(ship_pack.getText(), txt_date.getValue().toString(), combo_service.getValue());
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
                    AlertBox.close(btn_confirm);
           
       }else{
           
       }
         
    }
    
    public void fillServiceType(){
        
        try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core2_services";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                service_type.add(new combo_service_type(rs.getString("serv_id"), rs.getString("serv_name")).getServ_type_name());
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_selectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_service.setItems(service_type);
    }
    
    public void radioGroups(){
        
        ship_doc.setOnAction(e -> {
            ship_doc_select = ship_doc.getText();
            ship_pack_select = "";
        });
        
        ship_pack.setOnAction(e -> {
            ship_pack_select = ship_pack.getText();
            ship_doc_select = "";
        });
        
    }
    
    public void setDisabledradio(){
        
                
       if(ship_doc_select == ship_doc.getText()){
           
           txt_description.setDisable(true);
           txt_description.setText("n/a");

       }else if(ship_pack_select == ship_pack.getText()){
           
           txt_description.setDisable(false);
           txt_description.setText("");
           
       }
       
    }   

    @FXML
    private void cancel(ActionEvent event) {
        
        AlertBox.close(btn_cancel);
    }
    
}
