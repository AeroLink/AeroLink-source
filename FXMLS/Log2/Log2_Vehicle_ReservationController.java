/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import static FXMLS.Administrative.DBconnection.con;
import FXMLS.Log1.ProcurementController;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementRequest;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_outboundtable;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_reportscol;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationClass;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationListofvehicles;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_Reservation_AVehicles;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_Reservation_vd;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_Reservation_vm;
import FXMLS.USM.Controllers.IUsers;
import Model.Log2.Log2_avehicles;
import Model.Log2.Log2_dispatching;
import Model.Log2.Log2_listofvehicles;
import Model.Log2.Log2_reservationform;
import Model.Log2.Log2_vd;
import Model.Log2.Log2_vehicles;
import Model.Log2.Log2_vm;
import Model.Log2_Fleet_Management;
import Model.Log2_Vehicle_Reservation;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.geometry.Pos;
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
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
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

    private Connection con = DBconfig.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ObservableList<Log2_Vehicle_ReservationClass> data = FXCollections.observableArrayList();

    ObservableList<Log2_Vehicle_ReservationClass> monitoringdata = FXCollections.observableArrayList();

    ObservableList<String> combostatus = FXCollections.observableArrayList("Returned", "Byahe pa!");

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
    @FXML
    private TableView<Log2_Vehicle_Reservation_AVehicles> tblavailablevehicles;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> avmodel;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> avtype;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> aplateno;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> afueltype;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> afuelcap;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> acolor;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_AVehicles, String> acap;
    @FXML
    private JFXTextField txtrequestor;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdrequestor;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdvmodel;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdplateno;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdpurpose;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vddor;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdtime;
    @FXML
    private TableView<Log2_Vehicle_Reservation_vd> tblvd;
    @FXML
    private JFXTextField txtvdrequestor;
    @FXML
    private JFXTextField txtvdvmodel;
    @FXML
    private JFXTextField txtvdplateno;
    @FXML
    private JFXTextField txtvdpurpose;
    @FXML
    private JFXTextField txtvddor;
    @FXML
    private JFXTextField txtvdtime;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vd, String> vdreservationno;
    @FXML
    private JFXTextField txtsearchvd;
    @FXML
    private JFXButton btnsubmitdispatch;
    @FXML
    private TableView<Log2_Vehicle_Reservation_vm> tblvm;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmrequestor;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmvmodel;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmplateno;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmpurpose;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmdate;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmtime;
    @FXML
    private TableColumn<Log2_Vehicle_Reservation_vm, String> vmstatus;
    @FXML
    private JFXTextField txtvmrequestor;
    @FXML
    private JFXTextField txtvmvmodel;
    @FXML
    private JFXTextField txtvmplateno;
    @FXML
    private JFXTextField txtvmpurpose;
    @FXML
    private JFXTextField txtvmdate;
    @FXML
    private JFXTextField txtvmtime;
    @FXML
    private JFXComboBox<String> txtvmstatus;
    @FXML
    private JFXButton btnupdatestatus;
    @FXML
    private TableColumn<?, ?> vmmonitoringid;
    @FXML
    private JFXTextField txtsearchvm;

    /**
     * -
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Disabled fields avehicles
        txtvmodel.setEditable(false);
        txtvtype.setEditable(false);
        txtspec.setEditable(false);
        txtstype.setEditable(false);
        txtplateno.setEditable(false);
        txtcap.setEditable(false);
        txtdriver.setEditable(false);
        txtpurpose.setDisable(false);
        txtdestination.setDisable(false);
        date_ofreservation.setDisable(false);
        reservationtime.setDisable(false);
        
        // v center text
        txtrequestor.setAlignment(Pos.CENTER);
        txtvmodel.setAlignment(Pos.CENTER);
        txtvtype.setAlignment(Pos.CENTER);
        txtspec.setAlignment(Pos.CENTER);
        txtstype.setAlignment(Pos.CENTER);
        txtplateno.setAlignment(Pos.CENTER);
        txtcap.setAlignment(Pos.CENTER);
        txtdriver.setAlignment(Pos.CENTER);
        txtpurpose.setAlignment(Pos.CENTER);
        txtdestination.setAlignment(Pos.CENTER);

        // disabled fields vd
        txtvdrequestor.setEditable(false);
        txtvdvmodel.setEditable(false);
        txtvdplateno.setEditable(false);
        txtvdpurpose.setEditable(false);
        txtvddor.setEditable(false);
        txtvdtime.setEditable(false);

        txtvdrequestor.setAlignment(Pos.CENTER);
        txtvdvmodel.setAlignment(Pos.CENTER);
        txtvdplateno.setAlignment(Pos.CENTER);
        txtvdpurpose.setAlignment(Pos.CENTER);
        txtvddor.setAlignment(Pos.CENTER);
        txtvdtime.setAlignment(Pos.CENTER);

        btnsubmitreservation.setOnMouseClicked(e -> Savereservation());

        /* this.generatevehicles(); */
        // avehicle data
        availabledata();
        availableloaddata();
        getdata();

        // vd data
        vddata();
        vdloaddata();
        vdgetdata();

        // vm data
        vmdata();
        vmloaddata();
        vmgetdata();
        txtvmstatus.setItems(combostatus);
        txtvmstatus.setPromptText("Update Status");

        
        // vm center text
        txtvmrequestor.setAlignment(Pos.CENTER);
        txtvmvmodel.setAlignment(Pos.CENTER);
        txtvmplateno.setAlignment(Pos.CENTER);
        txtvmpurpose.setAlignment(Pos.CENTER);
        txtvmdate.setAlignment(Pos.CENTER);
        txtvmtime.setAlignment(Pos.CENTER);

        btnupdatestatus.setOnMouseClicked(e -> Updatestatus());
        // vd dispatch button
        btnsubmitdispatch.setOnMouseClicked(e -> Submitdispatch());

    }

    // available vehicle data
    public void availabledata() {

        avmodel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        avtype.setCellValueFactory(new PropertyValueFactory<>("VehicleType"));
        aplateno.setCellValueFactory(new PropertyValueFactory<>("VehiclePlateNumber"));
        afueltype.setCellValueFactory(new PropertyValueFactory<>("VehicleFuelType"));
        afuelcap.setCellValueFactory(new PropertyValueFactory<>("VehicleFuelCapacity"));
        acolor.setCellValueFactory(new PropertyValueFactory<>("VehicleColor"));
        acap.setCellValueFactory(new PropertyValueFactory<>("VehicleCapacity"));

    }

    // available vehicle loaddata
    public void availableloaddata() {

        Log2_avehicles vehicles = new Log2_avehicles("vehicles");

        ObservableList<Log2_Vehicle_Reservation_AVehicles> fmrs = FXCollections.observableArrayList();
        List b = vehicles.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("VehicleModel");
            hm.get("VehicleType");
            hm.get("VehiclePlateNumber");
            hm.get("VehicleFuelType");
            hm.get("VehicleFuelCapacity");
            hm.get("VehicleColor");
            hm.get("VehicleCapacity");

            fmrs.add(
                    new Log2_Vehicle_Reservation_AVehicles(
                            String.valueOf(hm.get("VehicleModel")),
                            String.valueOf(hm.get("VehicleType")),
                            String.valueOf(hm.get("VehiclePlateNumber")),
                            String.valueOf(hm.get("VehicleFuelType")),
                            String.valueOf(hm.get("VehicleFuelCapacity")),
                            String.valueOf(hm.get("VehicleColor")),
                            String.valueOf(hm.get("VehicleCapacity"))
                    ));
        }
        tblavailablevehicles.setItems(fmrs);

    }

    // available vehicle getdata
    private void getdata() {

        tblavailablevehicles.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Vehicle_Reservation_AVehicles rd = tblavailablevehicles.getItems().get(tblavailablevehicles.getSelectionModel().getSelectedIndex());
                txtvmodel.setText(rd.getVehicleModel());
                txtvtype.setText(rd.getVehicleType());
                txtspec.setText(rd.getVehiclePlateNumber());
                txtstype.setText(rd.getVehicleFuelType());
                txtplateno.setText(rd.getVehicleFuelCapacity());
                txtcap.setText(rd.getVehicleColor());
                txtdriver.setText(rd.getVehicleCapacity());

            }
        });
    }

    // sa vehicle dispatching to!!!
    private void vddata() {

        vdreservationno.setCellValueFactory(new PropertyValueFactory<>("vd_id"));
        vdrequestor.setCellValueFactory(new PropertyValueFactory<>("vd_requestor"));
        vdvmodel.setCellValueFactory(new PropertyValueFactory<>("vd_vmodel"));
        vdplateno.setCellValueFactory(new PropertyValueFactory<>("vd_plateno"));
        vdpurpose.setCellValueFactory(new PropertyValueFactory<>("vd_purpose"));
        vddor.setCellValueFactory(new PropertyValueFactory<>("vd_dateofreservation"));
        vdtime.setCellValueFactory(new PropertyValueFactory<>("time"));

    }

    // sa vehicle dispatching to!!!
    ObservableList<Log2_Vehicle_Reservation_vd> vdtbl = FXCollections.observableArrayList();
    long DummyCount2 = 0;
    long GlobalCount2 = 0;
    Log2_vd vd = new Log2_vd("vd");

    private void vdloaddata() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2vr")) {
                vd.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount2 != GlobalCount2) {
                    tblvd.getItems().clear();
                    List<HashMap> hash = vd.get();
                    hash.stream().forEach(e -> {
                        vdtbl.add(new Log2_Vehicle_Reservation_vd(
                                String.valueOf(e.get("vd_id")),
                                String.valueOf(e.get("vd_requestor")),
                                String.valueOf(e.get("vd_vmodel")),
                                String.valueOf(e.get("vd_plateno")),
                                String.valueOf(e.get("vd_purpose")),
                                String.valueOf(e.get("vd_dateofreservation")),
                                String.valueOf(e.get("time"))
                        ));
                    });
                    tblvd.setItems(vdtbl);
                    GlobalCount2 = DummyCount2;
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

    // sa vehicle dispatching to!!!
    private void Savereservation() {
        vd.insert(new Object[][]{
            {"vd_requestor", txtrequestor.getText()},
            {"vd_vmodel", txtvmodel.getText()},
            {"vd_plateno", txtspec.getText()},
            {"vd_purpose", txtpurpose.getText()},
            {"vd_dateofreservation", date_ofreservation.getValue().toString()},
            {"time", reservationtime.getValue().toString()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New reservation was submit!");

    }

    // sa vehicle dispatching to!!!
    private void vdgetdata() {

        tblvd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Vehicle_Reservation_vd rd = tblvd.getItems().get(tblvd.getSelectionModel().getSelectedIndex());
                txtvdrequestor.setText(rd.getVd_requestor());
                txtvdvmodel.setText(rd.getVd_vmodel());
                txtvdplateno.setText(rd.getVd_plateno());
                txtvdpurpose.setText(rd.getVd_purpose());
                txtvddor.setText(rd.getVd_dateofreservation());
                txtvdtime.setText(rd.getTime());

            }
        });
    }

    // vm na to !!!
    private void vmdata() {

        vmmonitoringid.setCellValueFactory(new PropertyValueFactory<>("vm_id"));
        vmrequestor.setCellValueFactory(new PropertyValueFactory<>("vm_requestor"));
        vmvmodel.setCellValueFactory(new PropertyValueFactory<>("vm_vmodel"));
        vmplateno.setCellValueFactory(new PropertyValueFactory<>("vm_plateno"));
        vmpurpose.setCellValueFactory(new PropertyValueFactory<>("vm_purpose"));
        vmdate.setCellValueFactory(new PropertyValueFactory<>("vm_date"));
        vmtime.setCellValueFactory(new PropertyValueFactory<>("vm_time"));
        vmstatus.setCellValueFactory(new PropertyValueFactory<>("vm_status"));
    }

    // vm na to !!!!
    ObservableList<Log2_Vehicle_Reservation_vm> vmtbl = FXCollections.observableArrayList();
    long DummyCountvm = 0;
    long GlobalCountvm = 0;
    Log2_vm vm = new Log2_vm("vm");

    private void vmloaddata() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2vr")) {
                vm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCountvm = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCountvm != GlobalCountvm) {
                    tblvm.getItems().clear();
                    List<HashMap> hash = vm.get();
                    hash.stream().forEach(e -> {
                        vmtbl.add(new Log2_Vehicle_Reservation_vm(
                                String.valueOf(e.get("vm_id")),
                                String.valueOf(e.get("vm_requestor")),
                                String.valueOf(e.get("vm_vmodel")),
                                String.valueOf(e.get("vm_plateno")),
                                String.valueOf(e.get("vm_purpose")),
                                String.valueOf(e.get("vm_date")),
                                String.valueOf(e.get("vm_time")),
                                String.valueOf(e.get("vm_status"))
                        ));
                    });
                    tblvm.setItems(vmtbl);
                    GlobalCountvm = DummyCountvm;
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

    // vm na to !!!!
    private void Submitdispatch() {

        vm.insert(new Object[][]{
            {"vm_requestor", txtvdrequestor.getText()},
            {"vm_vmodel", txtvdvmodel.getText()},
            {"vm_plateno", txtvdplateno.getText()},
            {"vm_purpose", txtvdpurpose.getText()},
            {"vm_date", txtvddor.getText()},
            {"vm_time", txtvdtime.getText()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New reservation was Dispatched!");

    }

    private void vmgetdata() {

        tblvm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Vehicle_Reservation_vm rd = tblvm.getItems().get(tblvm.getSelectionModel().getSelectedIndex());
                txtvmrequestor.setText(rd.getVm_requestor());
                txtvmvmodel.setText(rd.getVm_vmodel());
                txtvmplateno.setText(rd.getVm_plateno());
                txtvmpurpose.setText(rd.getVm_purpose());
                txtvmdate.setText(rd.getVm_date());
                txtvmtime.setText(rd.getVm_time());

            }
        });
    }

    private void Updatestatus() {

        vm.where(new Object[][]{
            {"vm_id", "=", tblvm.getSelectionModel().getSelectedItem().getVm_id()}
        }).update(new Object[][]{
            {"vm_requestor", txtvmrequestor.getText()},
            {"vm_vmodel", txtvmvmodel.getText()},
            {"vm_plateno", txtvmplateno.getText()},
            {"vm_purpose", txtvmpurpose.getText()},
            {"vm_date", txtvmdate.getText()},
            {"vm_time", txtvmtime.getText()},
            {"vm_status", txtvmstatus.getValue()}
        }).executeUpdate();

        Helpers.EIS_Response.SuccessResponse("Success!", "Status was update!!!");

    }

    private void btn_selectmodel(MouseEvent event) throws IOException {

        Modal md = Modal.getInstance(new Form("/FXMLS/Log2/vr/modals/Log2_Vehicle_Reservation_SelectV.fxml").getParent());
        md.open();
    }

    @FXML
    private void event(KeyEvent evt) {

    }

    @FXML
    private void searchvd(KeyEvent event) {

        vdtbl.clear();
        try {
            String search = "select * from aerolink.tbl_log2_vd_listofreservation where vd_id like '" + txtsearchvd.getText() + "%' or vd_requestor like '" + txtsearchvd.getText() + "%' or vd_vmodel like '" + txtsearchvd.getText() + "%'";
            pst = con.prepareStatement(search);
            rs = pst.executeQuery();
            while (rs.next()) {
                vdtbl.add(new Log2_Vehicle_Reservation_vd("" + rs.getString("vd_id"), rs.getString("vd_requestor"), rs.getString("vd_vmodel"), rs.getString("vd_plateno"), rs.getString("vd_purpose"), rs.getString("vd_dateofreservation"), rs.getString("time")));

            }
            tblvd.setItems(vdtbl);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void searchvm(KeyEvent event) {

        vmtbl.clear();
        try {
            String search = "select * from aerolink.tbl_log2_vm where vm_id like '" + txtsearchvm.getText() + "%' or vm_requestor like '" + txtsearchvm.getText() + "%'";
            pst = con.prepareStatement(search);
            rs = pst.executeQuery();
            while (rs.next()) {
                vmtbl.add(new Log2_Vehicle_Reservation_vm("" + rs.getString("vm_id"), rs.getString("vm_requestor"), rs.getString("vm_vmodel"), rs.getString("vm_plateno"), rs.getString("vm_purpose"), rs.getString("vm_date"), rs.getString("vm_time"), rs.getString("vm_status")));

            }
            tblvm.setItems(vmtbl);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
