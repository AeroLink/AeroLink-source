/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHire_FB;
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
public class HR1_viewFBController implements Initializable {

    HR4_Employee employee = new HR4_Employee();
    HR4_Employee fb_employee = new HR4_Employee("familyBackground");

    ObservableList fblist = FXCollections.observableArrayList();

    @FXML
    private TextField txtName;
    @FXML
    private ComboBox cboRelationship;
    @FXML
    private TextField txtContact;
    @FXML
    private TableView<TableModel_NewHire_FB> tblListings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateTable();
        this.renderTable();
        
        String[] rel = {"Father", "Mother", "Sister", "Brother", "Grandfather", "GrandMother", "Aunt"};
        
        for(String rx : rel) {
            cboRelationship.getItems().add(rx);
        }
        
    }

    public void generateTable() {
        tblListings.getColumns().removeAll(tblListings.getColumns());
        
        TableColumn<TableModel_NewHire_FB, String> cn = new TableColumn<>("Complete Name");
        TableColumn<TableModel_NewHire_FB, String> r = new TableColumn<>("Relationship");
        TableColumn<TableModel_NewHire_FB, String> cnum = new TableColumn<>("Contact number");
        
        cn.setCellValueFactory(value -> value.getValue().complete_name);
        r.setCellValueFactory(value -> value.getValue().relationship);
        cnum.setCellValueFactory(value -> value.getValue().contact_number);
        
        tblListings.getColumns().addAll(cn, r, cnum);
    }

    public void renderTable() {
        fb_employee.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            fblist.add(new TableModel_NewHire_FB(row.get("complete_name").toString(), row.get("relationship").toString(), row.get("contact_number").toString()));
        });

        tblListings.getItems().clear();
        tblListings.setItems(fblist);
    }

    @FXML
    private void SubmitInfo(ActionEvent event) {
        
        
        if(fb_employee.insert(new Object[][]{
            {"complete_name", txtName.getText()},
            {"relationship", cboRelationship.getSelectionModel().getSelectedItem()},
            {"contact_number", txtContact.getText()},
            {"employee_code", HR1_NewHireClass.employee_code}
        })){
            Helpers.EIS_Response.SuccessResponse("Success", "New family member was added to the database.");
            this.renderTable();
        }
        
        
    }

}
