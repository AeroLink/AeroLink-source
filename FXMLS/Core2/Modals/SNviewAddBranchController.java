/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import Model.Core2.CORE2_Cities;
import Model.Core2.CORE2_Branch;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class SNviewAddBranchController implements Initializable {

    CORE2_Cities city = new CORE2_Cities();

    public static Boolean modalOpen = true;
    @FXML
    private AnchorPane SNrootPane;
    @FXML
    private TextField txtRegion;
    @FXML
    private ComboBox txtCity;
    @FXML
    private TextField txtBarangay;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtZip;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCellNumber;
    @FXML
    private TextField txtManager;
    @FXML
    private JFXButton submit;
    @FXML
    private JFXCheckBox cbbDtd;
    @FXML
    private JFXCheckBox cbbExpress;
    @FXML
    private JFXCheckBox cbbPickup;
    @FXML
    private Label lblCountry;
    @FXML
    private TextField txtStreet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        submit.setOnMouseClicked(e -> Insert());
        
        city.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            txtCity.getItems().add(row.get("city_name").toString());
        });
    }

    public void Insert() {
        CORE2_Branch brch = new CORE2_Branch();
        // ginawa ko to para mag popup yung else sa loob ng try
        String txtR = txtRegion.getText();
        String txtC = txtCity.getSelectionModel().getSelectedItem().toString();
        String txtB = txtBarangay.getText();
        String txtS = txtStreet.getText();
        String txtA = txtAddress.getText();
        String txtP = txtProvince.getText();
        String txtZ = txtZip.getText();
        String txtE = txtEmail.getText();
        String txtCN = txtCellNumber.getText();
        String txtM = txtManager.getText();

        try {
            String[][] sn = {
                {"country", lblCountry.getText()},
                {"region", txtRegion.getText()},
                {"city", txtCity.getSelectionModel().getSelectedItem().toString()},
                {"barangay", txtBarangay.getText()},
                {"street", txtStreet.getText()},
                {"address", txtAddress.getText()},
                {"province", txtProvince.getText()},
                {"zipcode", txtZip.getText()},
                {"email", txtEmail.getText()},
                {"number", txtCellNumber.getText()},
                {"personnel_assign", txtManager.getText()},
                {"express", cbbExpress.getText()},
                {"pickup", cbbPickup.getText()},
                {"doortodoor", cbbDtd.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (brch.insert(sn)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                lblCountry.setText("");
                txtRegion.setText("");
//                txtCity.setText("");
                txtBarangay.setText("");
                txtStreet.setText("");
                txtAddress.setText("");
                txtProvince.setText("");
                txtZip.setText("");
                txtEmail.setText("");
                txtCellNumber.setText("");
                txtManager.setText("");

            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                if ((txtR.isEmpty() || txtC.isEmpty() || txtB.isEmpty() || txtS.isEmpty()
                        || txtA.isEmpty() || txtP.isEmpty() || txtZ.isEmpty() || txtE.isEmpty()
                        || txtCN.isEmpty() || txtM.isEmpty())) {
                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//tblOpenJobs.getSelectionModel().getSelectedItem().job_title.getValue()
