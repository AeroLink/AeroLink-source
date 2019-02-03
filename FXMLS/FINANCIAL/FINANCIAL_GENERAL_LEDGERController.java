/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_GENERAL_LEDGERController implements Initializable {

    @FXML
    private JFXButton coa_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        coa_btn.setOnMouseClicked(e -> OpenCOA());
    }    
    
    
    
    public void OpenCOA(){
        
                Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/GL_COA.fxml").getParent());
        md.open();
    }
}
