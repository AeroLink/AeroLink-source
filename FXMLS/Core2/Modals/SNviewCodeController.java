/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SNviewCodeController implements Initializable {

    //
    public static String code = "";
    public static String region = "";
    public static String province = "";
    public static String barangay = "";
    public static String zip = "";
    public static String address = "";
    public static String email = "";
    public static String number = "";
    //
    public static String fname = "";
    public static String express = "";
    public static String pickup = "";
    public static String door = "";

//    public static String vehicle = "";
//    public static String to = "";
//    public static String from = "";
    // para sa modal kasi false sya sa kabilang scene kaya need na mag true sya para gumana
    public static Boolean modalOpen = true;
    @FXML
    private JFXTextField lblRegion;
    @FXML
    private JFXTextField lblProvince;
    @FXML
    private JFXTextField lblBarangay;
    @FXML
    private JFXTextField lblZip;
    @FXML
    private JFXTextField lblAddress;
    @FXML
    private JFXTextField lblEmail;
    @FXML
    private JFXTextField lblNumber;
    @FXML
    private Label lblCode;
    @FXML
    private JFXTextField lblFn;
    @FXML
    private JFXCheckBox cbEx;
    @FXML
    private JFXCheckBox cbPu;
    @FXML
    private JFXCheckBox cbDtd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblCode.setText(code);
        lblRegion.setText(region);
        lblProvince.setText(province);
        lblBarangay.setText(barangay);
        lblZip.setText(zip);
        lblAddress.setText(address);
        lblEmail.setText(email);
        lblNumber.setText(number);
        //
        lblFn.setText(fname);
        //
        cbEx.setText(express);
        cbPu.setText(pickup);
        cbDtd.setText(door);
    }

    // ILAGAY NLNG SA ON ACTION FUNCTION SA-->CODE 
    // PARA GUMANA YUNG CALL NG MODAL
}
