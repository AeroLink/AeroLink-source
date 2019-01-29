/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SPviewSPDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    // info ng company
    public static String pname = "";
    public static String paddress = "";
    public static String pemail = "";
    public static String pcontact = "";
    public static String pcode = "";
    public static String pcountry = "";
    // info ng tao
    public static String pefn = "";
    public static String peemail = "";

    public static Boolean modalOpen = true;
    // para sa info ng company
    @FXML
    private JFXTextField providername;
    @FXML
    private JFXTextField provideraddress;
    @FXML
    private JFXTextField provideremail;
    @FXML
    private JFXTextField providercontact;
    @FXML
    private Label providercode;
    @FXML
    private JFXTextField providercountry;
    // para sa info ng tao
    @FXML
    private JFXTextField PEfullname;
    @FXML
    private JFXTextField PEemail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // company
        providername.setText(pname);
        provideraddress.setText(paddress);
        provideremail.setText(pemail);
        providercontact.setText(pcontact);
        providercode.setText(pcode);
        providercountry.setText(pcountry);
        // tao
        PEfullname.setText(pefn);
        PEemail.setText(peemail);
    }

}
