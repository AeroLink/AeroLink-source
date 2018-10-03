/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewPerf implements Initializable {

    @FXML
    private StackPane spane;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private Label lblAppFull;
    @FXML
    private MenuItem menuHiring;
    @FXML
    private MenuItem menuDeny;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtCivilStatus;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private JFXButton btn0;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn5;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewTrainingSem(ActionEvent event) {
    }

    @FXML
    private void viewCertification(ActionEvent event) {
    }

    @FXML
    private void viewWorkExp(ActionEvent event) {
    }

    @FXML
    private void viewFamilyBackGround(ActionEvent event) {
    }

    @FXML
    private void viewEducAttain(ActionEvent event) {
    }
    
}
