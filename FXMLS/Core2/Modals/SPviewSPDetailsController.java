/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SPviewSPDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String pname = "";
    public static String paddress = "";
    
    public static Boolean modalOpen = true;
    @FXML
    private JFXTextField providername;
    @FXML
    private JFXTextField provideraddress;
    @FXML
    private JFXTextField provideremail;
    @FXML
    private JFXTextField providercontact;
    @FXML
    private JFXTextField PEfullname;
    @FXML
    private JFXTextField PEemail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        providername.setText(pname);
        provideraddress.setText(paddress);
    }    
    
}
