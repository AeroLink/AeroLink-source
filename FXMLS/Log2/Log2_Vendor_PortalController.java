/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Vendor_PortalController implements Initializable {

    @FXML
    private JFXButton btnvendorportal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnlink(MouseEvent event) {
           Desktop browser = Desktop.getDesktop();
        
        try{
            browser.browse(new URI ("http://localhost/Forms/loginadmin.php"));
            }
        
              catch(IOException err){
        
    }
              catch(URISyntaxException err){
        }    
    }   
        
}
