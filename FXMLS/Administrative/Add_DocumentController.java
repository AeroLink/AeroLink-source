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
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class Add_DocumentController implements Initializable {

    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private String directory = "";
   
    @FXML
    private JFXButton btncancel;
    @FXML
    private TextField txtdocname;
    @FXML
    private JFXComboBox<String> docdep;
    @FXML
    private JFXComboBox<String> doccategory;
    @FXML
    private JFXComboBox<String> doctype;
    @FXML
    private JFXTextField txtpathfile;
    
    ObservableList<String> depbox = FXCollections.observableArrayList("HR","Logistics","Core","Administrative");
    ObservableList<String> categorybox = FXCollections.observableArrayList();
    ObservableList<String> typebox = FXCollections.observableArrayList(".pdf");
    @FXML
    private JFXButton btnsubmit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     docdep.setItems(depbox);
     category2();
     doctype.setItems(typebox);
     
    }    
    
    @FXML
    public void close(){
       AlertBox.display("Alert", "Adding Documents has been Cancel");
       AlertBox.close(btncancel);
    }
        
    
    @FXML
    public void insertdocument(){
        String sql  = "insert into aerolink.admin_document_reqstoring values(?,?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtdocname.getText());
            pst.setString(2, docdep.getSelectionModel().getSelectedItem());
            pst.setString(3, doccategory.getSelectionModel().getSelectedItem());
            pst.setString(4, doctype.getSelectionModel().getSelectedItem());
            
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(5, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(6, date);
            
            File f = new File(directory+txtpathfile.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(7, fs);
            pst.setString(8, "Pending");
            
            pst.execute();
            
          
            AlertBox.display("Alert", "Document is now Pending");
            AlertBox.close(btnsubmit);
            
            
    }   catch (SQLException ex) {
            Logger.getLogger(Add_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Add_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    public void opendialog(){
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
            fc.getExtensionFilters().add(exf1);
            File f = fc.showOpenDialog(null);
            if(f != null){
           
                txtpathfile.setText(""+f);
            }
            else{
               AlertBox.display("Alert", "No File Selected");
            }
 }

  public void category2(){
      categorybox.clear();
        try{
             String query = "select * from aerolink.admin_document_category where [Category ID] > 2";
             pst = con.prepareStatement(query);
             rs = pst.executeQuery();
             while(rs.next()){
                 categorybox.add(rs.getString("Category"));
             }
             doccategory.setItems(categorybox);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
      
  }
}
