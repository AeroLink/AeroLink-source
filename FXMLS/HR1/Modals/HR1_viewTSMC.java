/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NewHireTSM;
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
public class HR1_viewTSMC implements Initializable {

    HR4_Employee eTSM = new HR4_Employee("TSM");

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
    private TableView<TableModel_NewHireTSM> tblTSM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.generateTable();
        this.renderTable();
        
        String[] t = {"Rank and File", "Managerial", "Supervisory"};
        
        for(String T : t){
            cboType.getItems().add(T);
        }
        
    }

    public void generateTable() {
        tblTSM.getColumns().removeAll(tblTSM.getColumns());

        TableColumn<TableModel_NewHireTSM, String> title = new TableColumn<>("Title");
        TableColumn<TableModel_NewHireTSM, String> duration = new TableColumn<>("Duration");
        TableColumn<TableModel_NewHireTSM, String> type = new TableColumn<>("Type");
        TableColumn<TableModel_NewHireTSM, String> conducted_by = new TableColumn<>("Conducted");

        title.setCellValueFactory(value -> value.getValue().title);
        duration.setCellValueFactory(value -> value.getValue().duration);
        type.setCellValueFactory(value -> value.getValue().type);
        conducted_by.setCellValueFactory(value -> value.getValue().conducted_by);

        tblTSM.getColumns().addAll(title, duration, type, conducted_by);

    }

    
    public void renderTable() {
    
        ObservableList<TableModel_NewHireTSM> list = FXCollections.observableArrayList();
        
        eTSM.where(new Object[][] {
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
           
            list.add(new TableModel_NewHireTSM(row.get("title").toString(), row.get("duration").toString(), row.get("type").toString(), row.get("conducted_by").toString()));
            
        });
        
        tblTSM.getItems().clear();
        tblTSM.setItems(list);

    }

    @FXML
    private void SubmitInfo(ActionEvent event) {
        if(eTSM.insert(new Object[][] {
            {"title", txtTile.getText()},
            {"employee_code", HR1_NewHireClass.employee_code},
            {"duration", 
                (txtStart.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) 
                        + " to " + txtEnd.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) ) },
            {"type", cboType.getSelectionModel().getSelectedItem()},
            {"conducted_by", txtConducted.getText()}
        })){
            Helpers.EIS_Response.SuccessResponse("Success", "New Training and Seminar Information was added to the employee");
            this.renderTable();
        }
    }

}
