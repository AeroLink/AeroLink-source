/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_NewPayrollMainController implements Initializable {

    private JFXDatePicker sd;
    private JFXDatePicker ed;
    long DummyCount = 0;
    long GlobalCount = 0;
    HR4_NewPayrollModel npm = new HR4_NewPayrollModel();
    @FXML
    private JFXButton create_btn;
    @FXML
    private DatePicker sds;
    @FXML
    private DatePicker eds;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       create_btn.setOnMouseClicked(e ->InsertNewPayrollIem());
        
        
        
    }   
    
     public void InsertNewPayrollIem(){
        HR4_NewPayrollModel payroll = new HR4_NewPayrollModel();
        
        
        int id = payroll.insert(new Object[][]{
                {"payroll_code", "PAYROLL000"},
                {"start_date" , sds.getValue().toString()},
                {"end_date" , eds.getValue().toString()},}, true);
        String PayCode = "PAYROLL000" + id;
            payroll.update(new Object[][]{
                {"payroll_code", PayCode}
            }).where(new Object[][]{
                {"id", "=", id}
            }).executeUpdate();
            
     }
}
