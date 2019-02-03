/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.fm.modals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        
    
    
}
