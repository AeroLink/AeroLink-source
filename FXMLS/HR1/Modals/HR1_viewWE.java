/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHireWE;
import Model.HR4.HR4_Employee;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewWE implements Initializable {

    HR4_Employee eWE = new HR4_Employee("WE");

    @FXML
    private TextField txtTile;
    @FXML
    private ComboBox cboType;
    @FXML
    private TextField txtConducted;
    @FXML
    private DatePicker txtStart;
    @FXML
    private DatePicker txtEnd;
    @FXML
    private TableView<TableModel_NewHireWE> tblWE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.generateTable();
        this.renderTable();

        Object[] es = {"Regular", "Contratual", "Casual", "Probationary"};
        cboType.getItems().addAll(es);

    }

    public void generateTable() {
        tblWE.getColumns().removeAll(tblWE.getColumns());

        TableColumn<TableModel_NewHireWE, String> position = new TableColumn<>("Position");
        TableColumn<TableModel_NewHireWE, String> type = new TableColumn<>("Status");
        TableColumn<TableModel_NewHireWE, String> company = new TableColumn<>("Company");
        TableColumn<TableModel_NewHireWE, String> duration = new TableColumn<>("Duration");

        position.setCellValueFactory(value -> value.getValue().position);
        type.setCellValueFactory(value -> value.getValue().status);
        company.setCellValueFactory(value -> value.getValue().company);
        duration.setCellValueFactory(value -> value.getValue().duration);

        tblWE.getColumns().addAll(position, type, company, duration);

    }

    public void renderTable() {

        ObservableList<TableModel_NewHireWE> list = FXCollections.observableArrayList();

        eWE.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {

            HashMap row = (HashMap) action;

            list.addAll(new TableModel_NewHireWE(row.get("position").toString(), row.get("duration").toString(), row.get("employment_status").toString(), row.get("company").toString()));
        });

        tblWE.getItems().clear();
        tblWE.setItems(list);

    }

    @FXML
    private void SubmitInfo(ActionEvent event) {
        if (eWE.insert(new Object[][]{
            {"position", txtTile.getText()},
            {"employee_code", HR1_NewHireClass.employee_code},
            {"employment_status", cboType.getSelectionModel().getSelectedItem()},
            {"company", txtConducted.getText()},
            {"duration",
                (txtStart.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                + " to " + txtEnd.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)))},})) {

            Helpers.EIS_Response.SuccessResponse("Success", "New Work Experience added to the employeee");
            this.renderTable();
            
            
        }
    }

}
