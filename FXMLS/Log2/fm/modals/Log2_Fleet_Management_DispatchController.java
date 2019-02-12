/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.fm.modals;

import FXMLS.Log2.DBconfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_Management_DispatchController implements Initializable {

    @FXML
    private AnchorPane anchorattachment;
    private FileChooser filechooser;
    private File file; 
    @FXML
    private ImageView attachimage;
    @FXML
    private JFXButton btnsubmitattach;
    @FXML
    private JFXTextField pathfile;
    private Connection con = DBconfig.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void btn_attachment(MouseEvent event) {
       
         FileChooser fileChooser = new FileChooser();
              
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
             FileChooser.ExtensionFilter extFilterdoc = 
                    new FileChooser.ExtensionFilter("doc files (*.txt)", "*.txt");
             FileChooser.ExtensionFilter extFilterallFiles = 
                    new FileChooser.ExtensionFilter("all files (*.*)", "*.*");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterdoc, extFilterallFiles);
 
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
             
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                attachimage.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(JavaFxPixel.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    

    };
        
    public void insert() {
  
         try{
             String insert = "insert into aerolink.tbl_log2_attachment values(?)";
             pst = con.prepareStatement(insert);
             
            File f = new File(""+pathfile.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(1, fs);
            pst.execute();
            
            
           Alert saved = new Alert(Alert.AlertType.INFORMATION);
           saved.setContentText("Saved");   
           saved.showAndWait();
           
         }catch(FileNotFoundException | SQLException ex){
            
         }
         
    }
    
    public void open() throws IOException{
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PNG Files", "*.png");
            fc.getExtensionFilters().add(exf1);
            File f = fc.showOpenDialog(null);
            pathfile.setText(""+f);
                 BufferedImage bufferedImage = ImageIO.read(f);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                attachimage.setImage(image);
           
    
    }
    
}
