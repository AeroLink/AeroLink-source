/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author my
 */
public class Register_attendController implements Initializable {

    @FXML
    private JFXTextField txtid;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtposition;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btncancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
