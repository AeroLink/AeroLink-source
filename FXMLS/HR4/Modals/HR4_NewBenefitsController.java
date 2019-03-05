/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Model.HR4_BenefitsModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_NewBenefitsController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField amount;
    @FXML
    private TextArea description;
    @FXML
    private TextField days;
    @FXML
    private JFXButton SubmitBtn;
    @FXML
    private JFXButton ClearBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    SubmitBtn.setOnMouseClicked(e ->SaveNewBenefits());
    }    
    public void SaveNewBenefits(){
        HR4_BenefitsModel bm = new HR4_BenefitsModel();
        int benefits_id = bm.insert(new Object[][]{
        {"benefits_id", "BFTS00"},
        {"title" , title.getText().toString()},
        {"amount" , amount.getText().toString()},
        {"description" , description.getText().toString()},
        {"days" , days.getText().toString()},},
        true);      
        String BenCode = "BFTS00" + benefits_id;
            bm.update(new Object[][]{
                {"benefits_id", BenCode}
            }).where(new Object[][]{
                {"id", "=", benefits_id}
            }).executeUpdate();
    }
    
}
