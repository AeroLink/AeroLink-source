/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class SOPviewPackageDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Boolean modalOpen = true;
    @FXML
    private Label lblpacknumber;
    @FXML
    private TextArea TApackage;
    @FXML
    private TextArea TAnotes;
    @FXML
    private TextField TFvalue;
    @FXML
    private TextField TFweight;
    @FXML
    private TextField TFstatus;

    public static String packno = "";
    public static String pack = "";
    public static String note = "";
    public static String value = "";
    public static String weight = "";
    public static String status = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblpacknumber.setText(packno);
        TApackage.setText(pack);
        TAnotes.setText(note);
        TFvalue.setText(value);
        TFweight.setText(weight);
        TFstatus.setText(status);
    }

}
