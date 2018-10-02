/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class AddSuccessorsController implements Initializable {

    @FXML
    private JFXComboBox<?> p_select_dept;
    @FXML
    private JFXComboBox<?> p_select_employee;
    @FXML
    private JFXButton btn_promote;
    @FXML
    private JFXComboBox<?> p_select_position;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
