/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Filler.HR4_DeductionsFill;
import FXMLS.HR4.Model.HR4_DeductionsModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_EditDeductionsController implements Initializable {

    @FXML
    private Label label_did;
    @FXML
    private TextField title_txfield;
    @FXML
    private JFXButton updateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_did.setText(HR4_DeductionsFill.a);
        title_txfield.setText(HR4_DeductionsFill.b);
        updateBtn.setOnMouseClicked(e -> {
            UpdateSettings();
        });
    }    
    private void UpdateSettings(){
            HR4_DeductionsModel m = new HR4_DeductionsModel();

            Boolean ab = m.where(new Object[][]{
                {"deduc_code", "=", label_did.getText()}
            }).update(new Object[][]{
                {"title", title_txfield.getText()}
            }).executeUpdate();
    
    }
    
}
