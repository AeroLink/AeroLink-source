/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_SalaryUpRequisitionController implements Initializable {

    @FXML
    private JFXButton SubmitBtn;
    @FXML
    private TextField ave;
    @FXML
    private TextArea desc;
    @FXML
    private TextField prod;
    @FXML
    private TextField qow;
    @FXML
    private TextField init;
    @FXML
    private TextField tw;
    @FXML
    private TextField ps;
    @FXML
    private TextField att;
    @FXML
    private ComboBox<?> req_pos;
    @FXML
    private TextField classification;
    @FXML
    private TextField dept;
    @FXML
    private TextField designation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
