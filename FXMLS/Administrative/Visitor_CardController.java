/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Visitor_CardController implements Initializable {
       private Connection con = DBconnection.con();
       private ResultSet rs = null;
       private PreparedStatement pst = null;
       private String capturedirectory = "src/FXMLS/Administrative/CaptureImage/";
       private String qrdirectory = "src/FXMLS/Administrative/QRdir/";
    
    
    @FXML
    private JFXButton btncancel;
    @FXML
    private Label txtname;
    @FXML
    private Label txtptm;
    @FXML
    private Label txtdest;
    @FXML
    private Label txtpurpose;
    @FXML
    private ImageView captureimg;
    @FXML
    private ImageView qrimg;
    @FXML
    private AnchorPane visitorpassanchor;
    @FXML
    private JFXButton btnprint;
    @FXML
    private Label companyid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void closewindow(){
        AlertBox.display("Alert", "Registration has been Cancel");
        AlertBox.close(btncancel);
    }
    
 
    BufferedImage bfm;
    public void visitorinfo(String fn, String mn, String sn, String ptm, String dest, String purp, TextField fn1,TextField mi1,TextField sn1, String cid) throws IOException{
        txtname.setText(fn+ " "+ mn + " " + sn);
        txtptm.setText(ptm);
        txtdest.setText(dest);
        txtpurpose.setText(purp);
        companyid.setText(cid);
    
        File f = new File(capturedirectory+(fn1.getText()+" "+mi1.getText()+" "+sn1.getText())+".png");
        bfm = ImageIO.read(f);
        Image capture = SwingFXUtils.toFXImage(bfm, null);
        FileInputStream fis = new FileInputStream(f);
        captureimg.setImage(capture);
        
        File f1 = new File(qrdirectory+(fn1.getText()+" "+mi1.getText()+" "+sn1.getText())+".png");
        bfm = ImageIO.read(f1);
        Image qrcode = SwingFXUtils.toFXImage(bfm, null);
        FileInputStream fis1 = new FileInputStream(f1);
        
        qrimg.setImage(qrcode);
    }
    
    public void printpass(){
         String sql = "insert into aerolink.admin_visitor_registration values(?,?,?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtname.getText());
            pst.setString(2, companyid.getText());
            pst.setString(3, txtpurpose.getText());
          
            
            File f = new File(capturedirectory+txtname.getText()+".png");
            bfm = ImageIO.read(f);
            Image image = SwingFXUtils.toFXImage(bfm, null);
            captureimg.setImage(image);
            FileInputStream fin = new FileInputStream(f);
            int len = (int)f.length();
            pst.setBinaryStream(4,fin ,len);
          
            File f1 = new File(qrdirectory+txtname.getText()+".png");
            bfm = ImageIO.read(f1);
            Image qrcode = SwingFXUtils.toFXImage(bfm, null);
            qrimg.setImage(qrcode);
            FileInputStream fin1 = new FileInputStream(f1);
            int len1 = (int)f1.length();
            pst.setBinaryStream(5,fin1 ,len1);
           
         
           java.util.Date d = new java.util.Date();
           long t = d.getTime();
           java.sql.Time st = new java.sql.Time(t);
           pst.setTime(6, st);
           pst.setTime(7, st);
           java.sql.Date date = new java.sql.Date(t);
           pst.setDate(8, date);
           pst.setString(9, "Checked In");
           pst.execute();
            AlertBox.display("Alert", "Data Succesfully Save");
            AlertBox.close(btnprint);
            Visitor_ManagementController vmc = new Visitor_ManagementController();
     
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    
    }

    
    
    
}
