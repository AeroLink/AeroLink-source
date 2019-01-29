/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class AddfilesController implements Initializable {

    private Connection con = DBconnection.con();
    private PreparedStatement pst;
    private ResultSet rs;
    private String directory = "";
    @FXML
    private JFXButton btntoarch;
    @FXML
    private JFXButton btntoarch1;
    @FXML
    private TextField adddocname;
    @FXML
    private JFXComboBox<String> addbox;
    ObservableList<String> addbox1 = FXCollections.observableArrayList("Contracts","Permit");
    @FXML
    private JFXTextField addpathtxt;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void insertvalue(){
    
        try{
            String query = "insert into aerolink.admin_document_files values(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, adddocname.getText());
            pst.setString(2, addbox.getSelectionModel().getSelectedItem());
               
            File f = new File(directory+addpathtxt.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(3, fs);
            pst.setString(4, "Retrieved");
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(5, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(6, date);
            
            AlertBox.display("Alert", "Document is now Ready");
        }catch(Exception ex){
            AlertBox.display("Alert", "Data Cannot be Empty");
        }
    }
    
    public void opendialog(){
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
            File f = fc.showOpenDialog(null);
            if(f != null){
                addpathtxt.setText(""+f);
            }
            else{
               AlertBox.display("Alert", "No File Selected");
            }
    }
    
    public void cancel(){
        AlertBox.close(btntoarch1);
    }
    
}
