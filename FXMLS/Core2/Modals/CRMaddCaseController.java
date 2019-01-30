/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import Model.Core2.CORE2_Customer_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class CRMaddCaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Boolean modalOpen = true;
    
    @FXML
    private ComboBox cbbCategory;
    ObservableList<String> category = FXCollections.observableArrayList("PACKAGE ISSUE", "MISDIRECT");
    @FXML
    private DatePicker date;
    @FXML
    private TextField txtRefNo;
    @FXML
    private TextArea TAissue;
    @FXML
    private TextField txtEmpID;
    @FXML
    private TextField txtLevel;
    @FXML
    private TextField txtStatus;
    @FXML
    private JFXButton btnSubmit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // show the data of category in combobox
        cbbCategory.setValue("PACKAGE ISSUE");
        cbbCategory.setItems(category);
        // show date in datepicker
        date.setValue(LocalDate.now());
        btnSubmit.setOnMouseClicked(e -> Insert());
    }

        public void Insert() {
        CORE2_Customer_Service cus = new CORE2_Customer_Service();
        try {
            String[][] cc = {
                {"ref_no", txtRefNo.getText()},
                {"emp_id", txtEmpID.getText()},
                {"category", cbbCategory.getValue().toString()},
                {"issue", TAissue.getText()},
                {"date", date.getValue().toString()},
                {"lvl", txtLevel.getText()},
                {"status", txtStatus.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (cus.insert(cc)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtRefNo.setText("");
                txtEmpID.setText("");;
                TAissue.setText("");
                txtLevel.setText("");
                txtStatus.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
//                if ((txtR.isEmpty()||txtC.isEmpty()||txtB.isEmpty()||txtS.isEmpty()
//                        ||txtA.isEmpty()||txtP.isEmpty()||txtZ.isEmpty()||txtE.isEmpty()
//                        ||txtCN.isEmpty()||txtM.isEmpty())) {
//                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
