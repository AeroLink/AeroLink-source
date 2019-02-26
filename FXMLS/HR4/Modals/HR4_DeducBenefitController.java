/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Filler.HR4_DeducBenefitsFill;
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
public class HR4_DeducBenefitController implements Initializable {

    @FXML
    private TextField benC;
    @FXML
    private TextField benT;
    @FXML
    private TextField empC;
    @FXML
    private TextField empN;
    @FXML
    private TextField benA;
    @FXML
    private TextField empB;
    @FXML
    private TextField sal;
    @FXML
    private TextField futB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        benC.setText(HR4_DeducBenefitsFill.a);
        benT.setText(HR4_DeducBenefitsFill.b);
        empC.setText(HR4_DeducBenefitsFill.c);
        empN.setText(HR4_DeducBenefitsFill.d);
        empB.setText(HR4_DeducBenefitsFill.e);
        benA.setText(HR4_DeducBenefitsFill.f);
    }
    
}
