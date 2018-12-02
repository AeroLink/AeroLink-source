/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHire_EducAttain;
import Model.HR4.HR4_Employee;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewEducAttain implements Initializable {

    HR4_Employee employee = new HR4_Employee();
    HR4_Employee ea_employee = new HR4_Employee("EducAttain");

    ObservableList elist = FXCollections.observableArrayList();

    @FXML
    private TextField txtSchool;
    @FXML
    private ComboBox cboLevel;
    @FXML
    private TextField txtCourse;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;
    @FXML
    private TableView<TableModel_NewHire_EducAttain> tblEducAttain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateTable();
        this.renderTable();
        
        String[] l = {"Primary", "Secondary", "Tertiary", "Masters", "Doctorate"};
        
        for(String s : l) {
            cboLevel.getItems().add(s);
        }
    }

    private void generateTable() {
        tblEducAttain.getColumns().removeAll(tblEducAttain.getColumns());

        TableColumn<TableModel_NewHire_EducAttain, String> educ = new TableColumn<>("Level");
        TableColumn<TableModel_NewHire_EducAttain, String> school = new TableColumn<>("School");
        TableColumn<TableModel_NewHire_EducAttain, String> course = new TableColumn<>("Course");
        TableColumn<TableModel_NewHire_EducAttain, String> duration = new TableColumn<>("Duration");

        educ.setCellValueFactory(value -> value.getValue().educ_level);
        school.setCellValueFactory(value -> value.getValue().school);
        course.setCellValueFactory(value -> value.getValue().course);
        duration.setCellValueFactory(value -> value.getValue().duration);

        tblEducAttain.getColumns().addAll(educ, school, course, duration);

    }

    public void renderTable() {
        ea_employee.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            elist.add(new TableModel_NewHire_EducAttain(row.get("educ_level").toString(), row.get("school").toString(), row.get("course").toString(), row.get("duration").toString()));
        });

        tblEducAttain.getItems().clear();
        tblEducAttain.setItems(elist);
    }

    @FXML
    private void SubmitInfo(ActionEvent event) {
        if (ea_employee.insert(new Object[][]{
            {"employee_code", HR1_NewHireClass.employee_code},
            {"educ_level", cboLevel.getSelectionModel().getSelectedItem()},
            {"school", txtSchool.getText()},
            {"course", txtCourse.getText()},
            {"duration", txtStart.getText() + " - ", txtEnd.getText()}
        })) {
            Helpers.EIS_Response.SuccessResponse("Success", "New education attaintment was added to the database.");
            this.renderTable();
        }
    }

}
