/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.USM.loginController;
import Helpers.FormSession;
import Synapse.Form;
import Synapse.Route;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
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
    @FXML
    private ProgressIndicator btnLoader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        txtUsername.setOnKeyPressed(value -> {
            if (value.getCode() == KeyCode.ENTER) {
                txtPassword.requestFocus();
            }
        });
        txtPassword.setOnKeyPressed(value -> {
            if (value.getCode() == KeyCode.ENTER) {
                doLogin();
            }
        });
    }

    @FXML
    private void login(ActionEvent event) {
        doLogin();
    }

    private void doLogin() {
        btnLoader.setVisible(true);
        btnSignIn.setText("");
        if (loginController.doLogin(txtUsername.getText(), txtPassword.getText())) {
            
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "Login Success", "Passing to Dashboard");
            alert.open();

            Session.putIfNotExist("username", txtUsername.getText());
            
            System.out.println(Arrays.asList(Session.getPermissions()));

            new Form(Route.routes.get("Main").toString()).open(StageStyle.UNDECORATED, true);
            Form.close(btnSignIn);
            
        } else {
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Opps", "Login Failed", "Please check your credentials and login again");
            alert.open();

            btnLoader.setVisible(false);
            btnSignIn.setText("Sign In");
        }
    }

}
