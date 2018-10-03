/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import Model.HR4.HR4_Employee;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewGovIDx implements Initializable {

    HR4_Employee empGV = new HR4_Employee("GVID");

    @FXML
    private TextField txtSSS;
    @FXML
    private TextField txtPH;
    @FXML
    private TextField txtTin;
    @FXML
    private TextField txtPagIBIG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        empGV.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            txtSSS.setText(row.get("SSS_num").toString());
            txtTin.setText(row.get("TIN_num").toString());
            txtPagIBIG.setText(row.get("Pagibig_num").toString());
            txtPH.setText(row.get("Philhealth_num").toString());

        });
    }

    @FXML
    private void SubmitInfo(ActionEvent event) {

        if (empGV.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().count() == 0) {
            empGV.insert(new Object[][]{
                {"employee_code", HR1_NewHireClass.employee_code},
                {"SSS_num", txtSSS.getText()},
                {"TIN_num", txtTin.getText()},
                {"Pagibig_num", txtPagIBIG.getText()},
                {"Philhealth_num", txtPH.getText()}
            });
        } else {
            empGV.update(new Object[][]{
                {"SSS_num", txtSSS.getText()},
                {"TIN_num", txtTin.getText()},
                {"Pagibig_num", txtPagIBIG.getText()},
                {"Philhealth_num", txtPH.getText()}
            }).where(new Object[][]{
                {"employee_code", "=", HR1_NewHireClass.employee_code}
            }).executeUpdate();
        }
        
        Helpers.EIS_Response.SuccessResponse("Succes", "Government IDs Successfully Updated");
        
    }

}
