/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Employee;
import Model.HR1.HR1_PerformanceGrade;
import Model.HR4.HR4_Employee;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewPerfXcri implements Initializable {

    @FXML
    private StackPane spane;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private Label lblAppFull;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtContactNumber;

    private String empCode;

    HR4_Employee employee_profile = new HR4_Employee("profile");
    HR1_PerformanceGrade grd = new HR1_PerformanceGrade();
    @FXML
    private TextField txtProductivity;
    @FXML
    private TextField txtQualityOfWork;
    @FXML
    private TextField txtInitiative;
    @FXML
    private TextField txtTeamWork;
    @FXML
    private TextField txtProblemSolving;
    @FXML
    private TextField txtAttendance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lblAppFull.setText(HR1_Employee.fullname);
        empCode = HR1_Employee.emp_code;
        jobTitle.setText(HR1_Employee.job_title);

        employee_profile.where(new Object[][]{
            {"employee_code", "=", empCode}
        }).get().stream().forEach(action -> {

            HashMap row = (HashMap) action;

            txtDate.setText(row.get("date_of_birth").toString());
            txtPlace.setText(row.get("place_of_birth").toString());
            txtGender.setText(row.get("gender").toString());
            txtEmail.setText(row.get("email").toString());
            txtHeight.setText(row.get("height").toString());
            txtWeight.setText(row.get("weight").toString());
            txtContactNumber.setText(row.get("contact_number").toString());

        });

        grd.where(new Object[][]{
            {"employee_code", "=", empCode}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            
            txtAttendance.setText(row.get("attendance").toString());
            txtProblemSolving.setText(row.get("problemsolving").toString());
            txtProductivity.setText(row.get("productivity").toString());
            txtInitiative.setText(row.get("Initiative").toString());
            txtTeamWork.setText(row.get("teamwork").toString());
            txtQualityOfWork.setText(row.get("qualityofwork").toString());
            
        });
        
    }

}
