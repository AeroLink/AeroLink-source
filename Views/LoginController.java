/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.USM.loginController;
import Helpers.Form;
import Synapse.Route;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnSignIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        if(loginController.doLogin(txtUsername.getText(), txtPassword.getText())) {
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "Login Success", "Passing to Dashboard");
            alert.open();
            
            new Form(Route.routes.get("Main").toString()).open(StageStyle.UNDECORATED, true);
            Form.close(btnSignIn);
        }else {
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Opps", "Login Failed", "Please check your credentials and login again");
            alert.open();
        }
    }
    
}
