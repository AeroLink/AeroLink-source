/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class AddfacilityController implements Initializable {

    @FXML
    private JFXTextField fname;
    @FXML
    private JFXButton fsave;
    @FXML
    private JFXTextField flocation;
    @FXML
    private JFXTextField fcapacity;
    @FXML
    private JFXTextField fimage;
    @FXML
    private Button fopen;
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void insert(){
        try{
            String query = "insert into aerolink.admin_facility values(?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, fname.getText());
            pst.setString(2, flocation.getText());
            pst.setString(3, fcapacity.getText());
          
            File f = new File(""+fimage.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(4, fs);
            pst.setString(5,"Ready");
            pst.execute();
            AlertBox.display("Alert", "Facility Added");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void open(){
          FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PNG Files", "*.png");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("JPG files", "*.jpg");
          
            fc.getExtensionFilters().addAll(exf1,ext2);
            File f = fc.showOpenDialog(null);
            fimage.setText(""+f);
    }
}
