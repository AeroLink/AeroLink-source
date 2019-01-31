/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class TM_AddTrainingController implements Initializable {

    @FXML
    private JFXComboBox<?> cbox_jp;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextArea txt_training_desc;
    @FXML
    private JFXComboBox<?> cbox_trainor;
    @FXML
    private JFXDatePicker txt_date;
    @FXML
    private JFXDatePicker txt_date2;
    @FXML
    private JFXTextField txt_total_hours;
    @FXML
    private JFXComboBox<?> cbox_type_of_training;
    @FXML
    private JFXTextField txt_limit_participants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
