/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Document_TrackingClass;
import FXMLS.Log2.ClassFiles.Log2_Document_Tracking_t;
import FXMLS.Log2.ClassFiles.Log2_Document_Trackingrequestlist;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationClass;
import Model.Log2.Log2_requestlist;
import Model.Log2.Log2_reservationform;
import Model.Log2.Log2_t;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    ObservableList<String> tod = FXCollections.observableArrayList("Original", "Certified True Copy");

    ObservableList<String> dept = FXCollections.observableArrayList("HR", "Logistics", "Core", "Administrative", "Finance");

    ObservableList<String> sub = FXCollections.observableArrayList("sample", "sample2");

    @FXML
    private JFXComboBox<String> combo_dept;
    @FXML
    private Button btnsubmitrequest;
    @FXML
    private Button btnsubmitrequest1;
    @FXML
    private Button btnsubmitrequest11;
    @FXML
    private TableView<Log2_Document_Trackingrequestlist> tblrequestlist;
    @FXML
    private JFXTextArea txtpurpose;
    @FXML
    private JFXTextField txtposition;
    @FXML
    private JFXDatePicker requestdate;
    @FXML
    private JFXTextField txtrequestor;
    @FXML
    private JFXComboBox<String> combotypeofdocu;
    @FXML
    private JFXComboBox<String> combosubject;
    @FXML
    private TableView<Log2_Document_Tracking_t> tbltracking;
    @FXML
    private TableColumn<Log2_Document_Tracking_t, String> tdept;
    @FXML
    private TableColumn<Log2_Document_Tracking_t, String> tsubject;
    @FXML
    private TableColumn<Log2_Document_Tracking_t, String> tlastdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monitoringcol();
        monitoringloaddata();

        combo_dept.setItems(dept);
        combo_dept.setPromptText("Select Department");

        combosubject.setItems(sub);
        combosubject.setPromptText("Select Subject");

        combotypeofdocu.setItems(tod);
        combotypeofdocu.setPromptText("Select Type of Document");

        this.generatetablerequestlist();
        this.requestlistloaddata();

        btnsubmitrequest.setOnMouseClicked(e -> Savesubmitrequest());
        
        // tracking dito
        trackingdata();
        trackingloaddata();
    }

    private void generatetablerequestlist() {

        tblrequestlist.getColumns().removeAll(tblrequestlist.getColumns());

        TableColumn<Log2_Document_Trackingrequestlist, String> requestor = new TableColumn<>("Requestor");
        TableColumn<Log2_Document_Trackingrequestlist, String> position = new TableColumn<>("Position");
        TableColumn<Log2_Document_Trackingrequestlist, String> subject = new TableColumn<>("Subject");
        TableColumn<Log2_Document_Trackingrequestlist, String> typeofdocu = new TableColumn<>("Type of Document");
        TableColumn<Log2_Document_Trackingrequestlist, String> department = new TableColumn<>("Department");
        TableColumn<Log2_Document_Trackingrequestlist, String> purpose = new TableColumn<>("Purpose");
        TableColumn<Log2_Document_Trackingrequestlist, String> requestdate = new TableColumn<>("Request Date");
        TableColumn<Log2_Document_Trackingrequestlist, String> status = new TableColumn<>("Status");

        requestor.setCellValueFactory(value -> value.getValue().getRequestrequestor());
        position.setCellValueFactory(value -> value.getValue().getRequestposition());
        subject.setCellValueFactory(value -> value.getValue().getRequestsubject());
        typeofdocu.setCellValueFactory(value -> value.getValue().getRequesttypeofdocu());
        department.setCellValueFactory(value -> value.getValue().getRequestdepartment());
        purpose.setCellValueFactory(value -> value.getValue().getRequestpurpose());
        requestdate.setCellValueFactory(value -> value.getValue().getRequestrequestdate());
        status.setCellValueFactory(value -> value.getValue().getStatus());

        tblrequestlist.getColumns().addAll(requestor, position, subject, typeofdocu, department, purpose, requestdate, status);

    }

    Log2_requestlist rf = new Log2_requestlist("requestlist");
    ObservableList<Log2_Document_Trackingrequestlist> rlist = FXCollections.observableArrayList();
    long DummyCount = 0;
    long GlobalCount = 0;

    private void requestlistloaddata() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2dt")) {
                rf.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    tblrequestlist.getItems().clear();
                    List<HashMap> hash = rf.get();
                    hash.stream().forEach(e -> {
                        rlist.add(new Log2_Document_Trackingrequestlist(
                                String.valueOf(e.get("requestor")),
                                String.valueOf(e.get("position")),
                                String.valueOf(e.get("subject")),
                                String.valueOf(e.get("typeofdocu")),
                                String.valueOf(e.get("department")),
                                String.valueOf(e.get("purpose")),
                                String.valueOf(e.get("requestdate")),
                                String.valueOf(e.get("status"))
                        ));
                    });
                    tblrequestlist.setItems(rlist);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Document_TrackingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    private void Savesubmitrequest() {

        rf.insert(new Object[][]{
            {"requestor", txtrequestor.getText()},
            {"position", txtposition.getText()},
            {"subject", combosubject.getValue().toString()},
            {"typeofdocu", combotypeofdocu.getValue().toString()},
            {"department", combo_dept.getValue().toString()},
            {"purpose", txtpurpose.getText()},
            {"requestdate", requestdate.getValue().toString()}
        });
        Helpers.EIS_Response.SuccessResponse("Success!", "New request was successfully added");
    }
    
    // tracking dito !!!
    
    private void trackingdata(){
    
        tdept.setCellValueFactory(new PropertyValueFactory<>("tdepartment"));
        tsubject.setCellValueFactory(new PropertyValueFactory<>("tsubject"));
        tlastdate.setCellValueFactory(new PropertyValueFactory<>("tlastdatesubmitted"));
    }
    
    Log2_t tk = new Log2_t("tracking");
    ObservableList<Log2_Document_Tracking_t> t = FXCollections.observableArrayList();
    long DummyCountt = 0;
    long GlobalCountt = 0;
    private void trackingloaddata(){
    
         CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2dt")) {
                tk.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCountt = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCountt != GlobalCountt) {
                    tbltracking.getItems().clear();
                    List<HashMap> hash = tk.get();
                    hash.stream().forEach(e -> {
                        t.add(new Log2_Document_Tracking_t(
                                String.valueOf(e.get("tdepartment")),
                                String.valueOf(e.get("tsubject")),
                                String.valueOf(e.get("tlastdatesubmitted"))
                            
                        ));
                    });
                    tbltracking.setItems(t);
                    GlobalCountt = DummyCountt;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Document_TrackingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    private void monitoringcol() {
       

    }

    private void monitoringloaddata() {
       
    }

    @FXML
    private void submitrequest(MouseEvent event) {
        Alert saved = new Alert(Alert.AlertType.CONFIRMATION);
        saved.setContentText("Do you really want to submit?");
        saved.showAndWait();
    }
}
