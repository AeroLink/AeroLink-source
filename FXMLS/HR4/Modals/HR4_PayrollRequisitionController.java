/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_NewPayrollFill;
import FXMLS.HR4.ClassFiles.HR4_NewPayrollFill2;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_PayrollRequisitionController implements Initializable {
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private JFXTextField pc;
    @FXML
    private JFXTextField pd;
    @FXML
    private JFXTextField ts;
    @FXML
    private JFXTextField dpt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        pc.setText(HR4_NewPayrollFill2.jae);
        pd.setText(HR4_NewPayrollFill2.jae1 + "-" + HR4_NewPayrollFill2.jae2);
        ts.setText(HR4_NewPayrollFill2.jae3);
        dpt.setText(HR4_NewPayrollFill2.jae4);
        
    }   
    
}
