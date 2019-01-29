/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Document_TrackingClass;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Document_TrackingController implements Initializable {

    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> subject;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> purpose;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> referenceno;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> department;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> requestor;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> daterequest;
    @FXML
    private TableColumn<Log2_Document_TrackingClass, String> datereleased;
    @FXML
    private TableView<Log2_Document_TrackingClass> tbl_monitoring;

    ObservableList<Log2_Document_TrackingClass> monitoringdata = FXCollections.observableArrayList();
    @FXML
    private JFXButton deleterows;
    @FXML
    private JFXComboBox<String> combo_typeofdocument;
    
    ObservableList<String> tod = FXCollections.observableArrayList("Original", "Certified True Copy");
     ObservableList<String> dept = FXCollections.observableArrayList("HRdept", "Logistics", "Core", "Administrative", "Finance");
    @FXML
    private JFXComboBox<String> combo_dept;
    @FXML
    private Button btnsubmitrequest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monitoringcol();
        monitoringloaddata();
        
        combo_typeofdocument.setItems(tod);
        combo_typeofdocument.setPromptText("Select Docu. Type");
        
        combo_dept.setItems(dept);  
        combo_dept.setPromptText("Select Department");
        
    }

    private void monitoringcol() {
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        purpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        referenceno.setCellValueFactory(new PropertyValueFactory<>("referenceno"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        requestor.setCellValueFactory(new PropertyValueFactory<>("requestor"));
        daterequest.setCellValueFactory(new PropertyValueFactory<>("daterequest"));
        datereleased.setCellValueFactory(new PropertyValueFactory<>("datereleased"));

    }

    private void monitoringloaddata() {
        monitoringdata.removeAll(monitoringdata);
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","23","HR4","Manager","06/02/2018","06/03/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","check","54","Core1","Manager","12/25/2018","12/26/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","11","Financials","Manager","03/11/2018","03/12/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","26","HR3","Manager","05/23/2018","0524/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","29","Core2","Manager","09/24/2018","09/25/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","46","HR1","Manager","01/28/2018","01/29/2018"));
        monitoringdata.addAll(new Log2_Document_TrackingClass("legal","Check","43","HR1","Manager","08/15/2018","08/16/2018"));
        tbl_monitoring.getItems().addAll(monitoringdata);

    }
   

    @FXML
    private void deleterowact(MouseEvent event) {
        tbl_monitoring.getItems().removeAll(tbl_monitoring.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void submitrequest(MouseEvent event) {
        Alert saved = new Alert(Alert.AlertType.CONFIRMATION);
            saved.setContentText("Do you really want to submit?");
            saved.showAndWait();
    }
}
