/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log1.ProcurementController;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_outboundtable;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationClass;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationFormClass;
import FXMLS.USM.Controllers.IUsers;
import Model.Log2.Log2_reservationform;
import Model.Log2_Fleet_Management;
import Model.Log2_Vehicle_Reservation;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Vehicle_ReservationController implements Initializable {

    ObservableList<Log2_Vehicle_ReservationClass> data = FXCollections.observableArrayList();

    ObservableList<Log2_Vehicle_ReservationClass> monitoringdata = FXCollections.observableArrayList();

    @FXML
    private JFXButton model;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> vehiclecode;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> vehiclemodel;
    @FXML
    private TableView<Log2_Vehicle_ReservationClass> vehiclemaintenance;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> lastmaintenance;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> details;
    @FXML
    private JFXButton btnsubmitreservation;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> name;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> daterented;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> time;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> timeend;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> status;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> monitoringlocation;
    @FXML
    private TableView<Log2_Vehicle_ReservationClass> tblmonitoring;
    @FXML
    private TableView<Log2_Vehicle_ReservationFormClass> tblreservation;
    @FXML
    private JFXDatePicker date_ofreservation;
    @FXML
    private JFXTimePicker reservationtime;
    @FXML
    private JFXTextField txtvmodel;
    @FXML
    private JFXTextField txtpurpose;
    @FXML
    private JFXTextField txtdestination;
   
    @FXML
    private JFXTextField txtdriver;
    @FXML
    private JFXTextField txtplateno;
    @FXML
    private JFXTextField txtcap;
    @FXML
    private JFXTextField txtstype;
    @FXML
    private JFXTextField txtvtype;
    @FXML
    private JFXTextField txtspec;

    /**
     * -
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Disabled fields
        txtvmodel.setDisable(true);
        txtvtype.setDisable(true);
        txtspec.setDisable(true);
        txtstype.setDisable(true);
        txtplateno.setDisable(true);
        txtcap.setDisable(true);
        txtdriver.setDisable(false);
        txtpurpose.setDisable(false);
        txtdestination.setDisable(false);
        date_ofreservation.setDisable(false);
        reservationtime.setDisable(false);
        
        
        loaddata();
        Cols();

        reserveCols();
        reserveloaddata();

        btnsubmitreservation.setOnMouseClicked(e -> Savereservation());
        
        

        this.generatetable();
        this.reservationformloaddata();
        
     

    }

    private void Cols() {
        vehiclecode.setCellValueFactory(new PropertyValueFactory<>("vehiclecode"));
        vehiclemodel.setCellValueFactory(new PropertyValueFactory<>("vehiclemodel"));
        lastmaintenance.setCellValueFactory(new PropertyValueFactory<>("lastmaintenance"));
        details.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    private void loaddata() {
        data.removeAll(data);
        data.addAll(new Log2_Vehicle_ReservationClass("1kz23", "Yamaha", "07/02/18"));
        data.addAll(new Log2_Vehicle_ReservationClass("5kf2m", "Ford", "04/12/18"));
        data.addAll(new Log2_Vehicle_ReservationClass("6ls4k7", "Honda", "11/22/18"));
        vehiclemaintenance.getItems().addAll(data);

    }

    private void reserveCols() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        monitoringlocation.setCellValueFactory(new PropertyValueFactory<>("monitoringlocation"));
        daterented.setCellValueFactory(new PropertyValueFactory<>("daterented"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeend.setCellValueFactory(new PropertyValueFactory<>("timeend"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void reserveloaddata() {
        monitoringdata.removeAll(monitoringdata);
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Truck", "Batangas", "10/21/18", "6am", "11am", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Motor", "Cubao", "10/25/18", "9am", "1pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Van", "Makati", "10/16/18", "11am", "5pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Car", "Laguna", "11/11/18", "7am", "2pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Truck", "Bulacan", "11/26/18", "10am", "3pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Van", "Marikina", "12/21/18", "1pm", "7pm", "Ongoing"));
        tblmonitoring.getItems().addAll(monitoringdata);
    }

    private void generatetable() {

        tblreservation.getColumns().removeAll(tblreservation.getColumns());

        TableColumn<Log2_Vehicle_ReservationFormClass, String> vehiclemodel = new TableColumn<>("Vehicle Model");
        TableColumn<Log2_Vehicle_ReservationFormClass, String> datereserved = new TableColumn<>("Date of Reservation");
        TableColumn<Log2_Vehicle_ReservationFormClass, String> time = new TableColumn<>("Time");

        vehiclemodel.setCellValueFactory(value -> value.getValue().Vehiclemodel);
        datereserved.setCellValueFactory(value -> value.getValue().Datereserved);
        time.setCellValueFactory(value -> value.getValue().Time);

        tblreservation.getColumns().addAll(vehiclemodel, datereserved, time);

    }

    Log2_reservationform rf = new Log2_reservationform("reservation");
    ObservableList<Log2_Vehicle_ReservationFormClass> vrreservation = FXCollections.observableArrayList();
    long DummyCount = 0;
    long GlobalCount = 0;

    private void reservationformloaddata() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2vr")) {
                rf.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    tblreservation.getItems().clear();
                    List<HashMap> hash = rf.get();
                    hash.stream().forEach(e -> {
                        vrreservation.add(new Log2_Vehicle_ReservationFormClass(
                                String.valueOf(e.get("vehicle_model")),
                                String.valueOf(e.get("date_of_reservation")),
                                String.valueOf(e.get("time"))
                        ));
                    });
                    tblreservation.setItems(vrreservation);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Vehicle_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    public void Savereservation() {
        
         rf.insert(new Object[][]{
            {"vehiclemodel", txtvmodel.getText()},
            {"datereserved", date_ofreservation.getValue().toString()},
            {"time", reservationtime.getValue().toString()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New reservation was successfully added");
        

    }
    
    


    @FXML
    private void btn_selectmodel(MouseEvent event) throws IOException {

        Modal md = Modal.getInstance(new Form("/FXMLS/Log2/vr/modals/Log2_Vehicle_Reservation_SelectV.fxml").getParent());
        md.open();
    }

}
