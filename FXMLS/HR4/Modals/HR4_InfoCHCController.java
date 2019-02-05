/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR2.ClassFiles.TM_DefaultTrainings;
import FXMLS.HR4.Filler.HR4_EmpInfoFill;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.Model.HR4_PayrollSettingsModel;
import Synapse.Model;
import Synapse.Session;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_InfoCHCController implements Initializable {

    @FXML
    private Tab tbl_pers;
    @FXML
    private TextField ln;
    @FXML
    private TextField fn;
    @FXML
    private TextField mn;
    @FXML
    private TextField suffix;
    @FXML
    private TextField gender;
    @FXML
    private TextField cs;
    @FXML
    private TextField wei;
    @FXML
    private TextField hei;
    @FXML
    private TextField cont_no;
    @FXML
    private TextField email;
    @FXML
    private TextField adds;
    @FXML
    private DatePicker dob;
    @FXML
    private DatePicker pob;
    @FXML
    private Label ec;
    @FXML
    private TableView<?> tbl_fb;
    @FXML
    private TableView<?> tbl_educ;
    @FXML
    private TableView<?> tbl_sems;
    @FXML
    private TableView<?> tbl_works;
    @FXML
    private Tab tbl_certs;
    @FXML
    private TableView<?> tbl_awards;
    @FXML
    private TableView<?> tbl_govs;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //For 201 file
        ec.setText(HR4_EmpInfoFill.a);
    }   
    
    
}
