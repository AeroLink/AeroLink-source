/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM.Modal;

import Model.UserPermissions;
import Model.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_NewUserController implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXPasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void submitUser(ActionEvent event) {
        Users u = new Users();
        UserPermissions up = new UserPermissions();

        int id = u.insert(new Object[][]{
            {"username", txtUsername.getText()},
            {"password", Synapse.Crypt.Encrypt(txtPassword.getText())}
        }, true);
        if (id != 0) {
            up.insert(new Object[][]{
                {"user_id", id},
                {"permission_id", 53}
            });
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Congratulations", "New User was added", "User" + txtUsername.getText() + " was added to the database");
            alert.open();
        }
    }

}
