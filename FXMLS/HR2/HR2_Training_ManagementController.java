/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_ManagementController implements Initializable {

    @FXML
    private JFXComboBox cbox_select_jobs;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextArea txt_training_desc;
    @FXML
    private JFXComboBox cbox_trainor;
    @FXML
    private JFXDatePicker txt_start_date;
    @FXML
    private JFXDatePicker txt_end_date;
    @FXML
    private JFXTextField txt_start_time;
    @FXML
    private JFXComboBox<String> cbox1_meridiem;
    @FXML
    private JFXTextField txt_end_time;
    @FXML
    private JFXComboBox<String> cbox2_meridiem;
    @FXML
    private JFXComboBox<String> cbox_select_type_of_training;
    @FXML
    private JFXTextField txt_location;
    @FXML
    private JFXComboBox cbox_vehicle;
    @FXML
    private JFXTextField txt_budget_cost;
    @FXML
    private JFXTextField txt_no_of_participants;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXTextField txt_search_training;
    @FXML
    private TableView<?> tbl_trainings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
