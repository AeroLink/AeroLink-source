/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_AW;
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
public class HR1_viewAW implements Initializable {

    HR4_Employee empAW = new  HR4_Employee("AW");
    
    @FXML
    private TextField txtTile;
    @FXML
    private DatePicker txtStart;
    @FXML
    private TableView<TableModel_AW> tblAW;

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
        tblAW.getColumns().removeAll(tblAW.getColumns());
        
        TableColumn<TableModel_AW, String> title = new TableColumn<>("Title");
        TableColumn<TableModel_AW, String> dateAw = new TableColumn<>("Date Awarded");
        
        tblAW.getColumns().addAll(title, dateAw);
        
    }
    
    public void renderTable(){
        ObservableList<TableModel_AW> list = FXCollections.observableArrayList();
        empAW.where(new Object[][] {
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_AW(row.get("title").toString(), row.get("date_awarded").toString()));
        });
        
        tblAW.getItems().clear();
        tblAW.setItems(list);
    }
    
    @FXML
    private void SubmitInfo(ActionEvent event) {
        if(empAW.insert(new Object[][] {
            {"employee_code", HR1_NewHireClass.employee_code},
            {"title", txtTile.getText()},
            {"date_awarded", txtStart.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))}
        })) {
            Helpers.EIS_Response.SuccessResponse("Success", "New Academic Award added");
            this.renderTable();
        }
    }
    
}
