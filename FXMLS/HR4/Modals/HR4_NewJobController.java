/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import Model.HR4_Classification;
import Model.HR4_Departments;
import Model.HR4_Designation;
import Model.HR4_JobLimits;
import FXMLS.HR4.Model.HR4_Jobs;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR4_NewJobController implements Initializable {

    HR4_Departments dept = new HR4_Departments();
    HR4_Designation designation = new HR4_Designation();
    HR4_Classification classification = new HR4_Classification();
    HR4_Jobs jobs = new HR4_Jobs();
    HR4_JobLimits jobLimit = new HR4_JobLimits();

    @FXML
    private JFXTextField txtJobTitle;
    @FXML
    private JFXTextArea txtJobDesc;
    @FXML
    private JFXComboBox cboDes;
    @FXML
    private JFXComboBox cboClass;
    @FXML
    private JFXComboBox cboDept;
    @FXML
    private JFXButton btnSubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBox();
    }

    public void populateComboBox() {
        List dept_list = dept.get();
        List designation_list = designation.get();
        List classification_list = classification.get();

        for (Object o : dept_list) {
            HashMap hm = (HashMap) o;
            cboDept.getItems().add(hm.get("id") + " - " + hm.get("dept_name"));
        }

        for (Object o : classification_list) {
            HashMap hm = (HashMap) o;
            cboClass.getItems().add(hm.get("id") + " - " + hm.get("class_name"));
        }

        for (Object o : designation_list) {
            HashMap hm = (HashMap) o;
            cboDes.getItems().add(hm.get("id") + " - " + hm.get("designation"));
        }
    }

    @FXML
    private void submitJob(ActionEvent event) {

        int job_id = jobs.insert(new Object[][]{
            {"dept_id", cboDept.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"classification_id", cboClass.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"designation_id", cboDes.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
            {"title", txtJobTitle.getText()},
            {"description", txtJobDesc.getText()},
            {"population_limit", 1}
        }, true);

        if (job_id != 0) {
            Helpers.EIS_Response.SuccessResponse("Successfully Saved", txtJobTitle.getText() + " is successfuly saved");
        }

    }

}
