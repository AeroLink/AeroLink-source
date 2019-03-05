/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Model.HR4_DeductionsModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_AddNewDeductionsController implements Initializable {

    @FXML
    private TextField title_lbx;
    @FXML
    private JFXButton submitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submitBtn.setOnMouseClicked(e ->{
        Submit(); 
        });
    }    
    public void Submit(){
        HR4_DeductionsModel deduc = new HR4_DeductionsModel();
        
        
        int id = deduc.insert(new Object[][]{
                {"deduc_code", "DDCT00"},
                {"title" , title_lbx.getText()}}, true);
        String DeducCode = "DDCT00" + id;
            deduc.update(new Object[][]{
                {"deduc_code", DeducCode}
            }).where(new Object[][]{
                {"id", "=", id}
            }).executeUpdate();
    }
    
}
