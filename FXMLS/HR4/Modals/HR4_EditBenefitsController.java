/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Filler.HR4_NewCompensationFill;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_BenefitsModel;
import FXMLS.HR4.Model.HR4_PayrollSettingsModel;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_EditBenefitsController implements Initializable {

    @FXML
    private TextField z;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextArea c;
    @FXML
    private TextField d;
    @FXML
    private JFXButton SaveBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SaveBtn.setOnMouseClicked(event -> UpdateSettings());
        a.setText(HR4_NewCompensationFill.a);
        b.setText(HR4_NewCompensationFill.b);
        c.setText(HR4_NewCompensationFill.c);
        d.setText(HR4_NewCompensationFill.d);
        z.setText(HR4_NewCompensationFill.z);
    }   
    
    private void UpdateSettings(){
    HR4_BenefitsModel m = new HR4_BenefitsModel();

            Boolean ab = m.where(new Object[][]{
                {"id", "=", z.getText()}
            }).update(new Object[][]{
                {"title", a.getText()},
                {"amount", b.getText()},
                {"description", c.getText()},
                {"days", d.getText()}
            
            }).executeUpdate();
    
    }
    
}
