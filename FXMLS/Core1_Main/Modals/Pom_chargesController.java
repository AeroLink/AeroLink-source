/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Pom_chargesController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lbl_insurance;
    @FXML
    private Label lbl_liability;
    String insurance;
    String liability;
    Double sub;
    Double insurance_cost;
    Double liability_cost = 240.0;
    Double zero = 0.0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }  
    
    public void setData(String insurance ,String liability, Double sub){
        
        
        this.insurance = insurance;
        this.liability = liability;
        this.insurance_cost = sub * 0.02;
        
        if(insurance.equals("Yes")){
        lbl_insurance.setText(new DecimalFormat("##.##").format(insurance_cost));
        }else{
            lbl_insurance.setText(zero.toString());
        }
        
        if(liability.equals("Yes")){
            lbl_liability.setText(liability_cost.toString());
        }else{
            lbl_liability.setText(zero.toString());
        }
    }
      
}
