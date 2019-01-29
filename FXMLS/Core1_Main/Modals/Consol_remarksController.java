/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.DBConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Consol_remarksController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lbl_ref;
    
    String ref_no;
    
    @FXML
    private TextArea txt_remarks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(String ref_no){
        this.ref_no = ref_no;
        lbl_ref.setText(ref_no);
    }

    @FXML
    private void submit(ActionEvent event) {
        if("".equals(txt_remarks)){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "ERROR", "Please enter your reason/s !");
                alert.open();
        }else{
        try{
    
                        conn = DBConnector.getConnection();

                        String sql = "INSERT INTO aerolink.tbl_core1_hold(ref_no, remarks) "
                                                                  + "VALUES (?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, lbl_ref.getText());
                        pst.setString(2, txt_remarks.getText());

                        pst.execute();
                        update();
                        
                        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "Hold Package", "Successful !");
                        alert.open();
                        txt_remarks.clear();
                        lbl_ref.setText("");

                   }catch(SQLException ex){
                        System.err.println(ex);
                }
        }
    }
    
    public void update(){
        
        try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='Hold' where ref_no='"+lbl_ref.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();

                         
        } catch (SQLException ex) {
            Logger.getLogger(Consol_remarksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
