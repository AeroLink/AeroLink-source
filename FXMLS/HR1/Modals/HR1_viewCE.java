/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHireCE;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewCE implements Initializable {

    HR4_Employee empCE = new HR4_Employee("CE");
    
    @FXML
    private TextField txtTile;
    @FXML
    private DatePicker txtStart;
    @FXML
    private DatePicker txtEnd;
    @FXML
    private TableView<TableModel_NewHireCE> tblCE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.generateTable();
        this.renderTable();
    }    
    
    public void generateTable(){
        tblCE.getColumns().removeAll(tblCE.getColumns());
        
        TableColumn<TableModel_NewHireCE, String> title = new TableColumn<>("Title");
        TableColumn<TableModel_NewHireCE, String> date_aq = new TableColumn<>("Date Acquired");
        TableColumn<TableModel_NewHireCE, String> date_ex = new TableColumn<>("Date Expiration");
        
        title.setCellValueFactory(value -> value.getValue().title);
        date_aq.setCellValueFactory(value -> value.getValue().date_acquired);
        date_ex.setCellValueFactory(value -> value.getValue().expiration);
        
        tblCE.getColumns().addAll(title, date_aq, date_ex);
    }
    
    public void renderTable(){
        
        ObservableList<TableModel_NewHireCE> list = FXCollections.observableArrayList();
        
        empCE.where(new Object[][] {
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_NewHireCE(row.get("certification_title").toString(), row.get("date_acquired").toString(), row.get("date_expiration").toString() ));
        });
    
        tblCE.getItems().clear();
        tblCE.setItems(list);
    }

    @FXML
    private void SubmitInfo(ActionEvent event) {
        if( empCE.insert(new Object[][] {
            {"employee_code", HR1_NewHireClass.employee_code},
            {"date_acquired", txtStart.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))},
            {"date_expiration", txtEnd.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))},
            {"certification_title", txtTile.getText()}
        })) {
            Helpers.EIS_Response.SuccessResponse("Success", "New Certification Added to the Employee");
            this.renderTable();
        }
    }
    
}
