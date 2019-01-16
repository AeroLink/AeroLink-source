/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.util.Log1Util;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ProcurementRequestApprovalController implements Initializable {

    @FXML
    private Label itemRequested_txt;
    @FXML
    private Label quantity_txt;
    @FXML
    private Label unit_txt;
    @FXML
    private Label price_txt;
    @FXML
    private Label priority_txt;
    @FXML
    private Label requestedFrom_txt;
    @FXML
    private Label requestedBy_txt;
    @FXML
    private Label dateRequested_txt;
    @FXML
    private JFXButton send_btn;
    @FXML
    private JFXButton cancel_bts;
    @FXML
    private DatePicker dateToday_txt;
    @FXML
    private ComboBox<?> priorityLevel_combox;
    @FXML
    private TextField description_txt;
    @FXML
    private Label budgetRequestor_txt;
    @FXML
    private Label totalAmount_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
