/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementRequests;
import Model.Log2_Fleet_ManagementAddtask;
import Model.Log2_Fleet_ManagementAddvehicle;
import Model.Log2_Fleet_ManagementRequest;
import Model.Log2_Fleet_ManagementScheduling;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.lang.String;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementController implements Initializable {

    ObservableList<Log2_Fleet_ManagementClass> reqdata = FXCollections.observableArrayList();

    ObservableList<Log2_Fleet_ManagementClass> prodata = FXCollections.observableArrayList();

    ObservableList<Log2_Fleet_ManagementClass> proindata = FXCollections.observableArrayList();

    ObservableList<String> vehicle = FXCollections.observableArrayList("Truck", "Car", "Motor");

    ObservableList<String> vtype = FXCollections.observableArrayList("Truck", "Car", "Motor");

    ObservableList<String> driver = FXCollections.observableArrayList("d1", "d2", "d3");

    ObservableList<String> task = FXCollections.observableArrayList("Deliver", "", "");
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestdept;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestitemname;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestsize;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestquantity;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestdestination;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestconsignee;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequests, String> requestdeparture;
    @FXML
    private TableView<Log2_Fleet_ManagementRequests> tblreq;
    @FXML
    private JFXTextField scheddept;
    @FXML
    private JFXTextField scheditemname;
    @FXML
    private JFXTextField schedquantity;
    @FXML
    private JFXTextField scheddestination;
    @FXML
    private JFXTextField scheddeparture;
    @FXML
    private JFXTextField schedvcapacity;
    @FXML
    private JFXComboBox<String> schedvehicle;
    @FXML
    private JFXComboBox<String> schedvtype;
    @FXML
    private JFXComboBox<String> scheddriver;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> pvehicleno;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> ptypeofvehicle;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> pdestination;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> ptime;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> pstatus;
    @FXML
    private TableView<Log2_Fleet_ManagementClass> prooutbound;
    @FXML
    private TableView<Log2_Fleet_ManagementClass> proinbound;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> proinvehicleno;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> prointypeofvehicle;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> proinitemname;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> proindestination;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> prointime;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> proinremarks;
    @FXML
    private JFXButton btnsubmit;
    @FXML
    private TableColumn<?, ?> scheduledepartment;
    @FXML
    private TableColumn<?, ?> scheduleitemname;
    @FXML
    private TableColumn<?, ?> scheduledestination;
    @FXML
    private TableColumn<?, ?> scheduled;
    @FXML
    private TableColumn<?, ?> scheduleassignvehicle;
    @FXML
    private TableColumn<?, ?> scheduledriver;
    @FXML
    private TableColumn<?, ?> schedulestatus;
    @FXML
    private JFXDatePicker vehicledate;
    @FXML
    private JFXComboBox<String> combovehicletask;
    @FXML
    private JFXButton btnsubmittask;
    private JFXTextField txtvehicle;
    private JFXTextField vehicletype;
    private JFXTextField plateno;
    private JFXTextField chassisno;
    private JFXButton btnsubmitaddvehicle;
    @FXML
    private TableView<?> tblsched;
    @FXML
    private JFXButton addvehicle;
    @FXML
    private JFXButton deletetask;
    @FXML
    private JFXButton edittask;
    @FXML
    private JFXButton addtask;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reqDisplaydata();
        reqloaddata();

        proCols();
        proloaddata();

        proinCols();
        proinloaddata();
        loadData();
        displaydata();
       
        getdataontableview();

        schedvehicle.setItems(vehicle);
        schedvehicle.setPromptText("Choose Vehicle");

        schedvtype.setItems(vtype);
        schedvtype.setPromptText("Choose type of Vehicle");

        scheddriver.setItems(driver);
        scheddriver.setPromptText("Choose Driver");

        combovehicletask.setItems(task);
        combovehicletask.setPromptText("Choose Task");

        btnsubmit.setOnMouseClicked(e -> Save());

        btnsubmittask.setOnMouseClicked(e -> Savetask());

        

    }

    private void reqDisplaydata() {

        requestdept.setCellValueFactory(new PropertyValueFactory<>("department"));
        requestitemname.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        requestsize.setCellValueFactory(new PropertyValueFactory<>("size"));
        requestquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        requestdestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        requestconsignee.setCellValueFactory(new PropertyValueFactory<>("consignee"));
        requestdeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));

    }

    private void reqloaddata() {

        Log2_Fleet_ManagementRequest fmr = new Log2_Fleet_ManagementRequest();

        ObservableList<Log2_Fleet_ManagementRequests> fmrs = FXCollections.observableArrayList();
        List b = fmr.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("department");
            hm.get("item_name");
            hm.get("size");
            hm.get("quantity");
            hm.get("destination");
            hm.get("consignee");
            hm.get("departure");

            fmrs.add(
                    new Log2_Fleet_ManagementRequests(
                            String.valueOf(hm.get("department")),
                            String.valueOf(hm.get("item_name")),
                            String.valueOf(hm.get("size")),
                            String.valueOf(hm.get("quantity")),
                            String.valueOf(hm.get("destination")),
                            String.valueOf(hm.get("consignee")),
                            String.valueOf(hm.get("departure"))
                    ));
        }
        tblreq.setItems(fmrs);

    }

    private void getdataontableview() {
        tblreq.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Fleet_ManagementRequests rd = tblreq.getItems().get(tblreq.getSelectionModel().getSelectedIndex());
                scheddept.setText(rd.getDepartment());
                scheditemname.setText(rd.getItem_name());
                schedquantity.setText(rd.getQuantity());
                scheddestination.setText(rd.getDestination());
                scheddeparture.setText(rd.getDeparture());

            }
        });
    }

    private void proCols() {

        pvehicleno.setCellValueFactory(new PropertyValueFactory<>("pvehicleno"));
        ptypeofvehicle.setCellValueFactory(new PropertyValueFactory<>("ptypeofvehicle"));
        pdestination.setCellValueFactory(new PropertyValueFactory<>("pdestination"));
        ptime.setCellValueFactory(new PropertyValueFactory<>("ptime"));
        pstatus.setCellValueFactory(new PropertyValueFactory<>("pstatus"));

    }

    private void proloaddata() {

        prodata.removeAll(prodata);
        prodata.addAll(new Log2_Fleet_ManagementClass("1001", "Motor", "Makati", "6am", "ongoing"));
        prodata.addAll(new Log2_Fleet_ManagementClass("1002", "Truck", "Batangas", "6am", "ongoing"));
        prodata.addAll(new Log2_Fleet_ManagementClass("1003", "Motor", "Cubao", "6am", "ongoing"));
        prodata.addAll(new Log2_Fleet_ManagementClass("1004", "Car", "Bulacan", "6am", "ongoing"));
        prodata.addAll(new Log2_Fleet_ManagementClass("1005", "Truck", "Leyte", "6am", "ongoing"));
        prooutbound.getItems().addAll(prodata);

    }

    private void proinCols() {

        proinvehicleno.setCellValueFactory(new PropertyValueFactory<>("proinvehicleno"));
        prointypeofvehicle.setCellValueFactory(new PropertyValueFactory<>("prointypeofvehicle"));
        proinitemname.setCellValueFactory(new PropertyValueFactory<>("proinitemname"));
        proindestination.setCellValueFactory(new PropertyValueFactory<>("proindestination"));
        prointime.setCellValueFactory(new PropertyValueFactory<>("prointime"));
        proinremarks.setCellValueFactory(new PropertyValueFactory<>("proinremarks"));

    }

    private void proinloaddata() {

        prodata.removeAll(proindata);
        proindata.addAll(new Log2_Fleet_ManagementClass("1111", "Truck", "Chair", "Cubao", "7am", "Manager"));
        proindata.addAll(new Log2_Fleet_ManagementClass("1112", "Motor", "Table", "Batangas", "7am", "Manager"));
        proindata.addAll(new Log2_Fleet_ManagementClass("1113", "Motor", "Tools", "Makati", "7am", "Manager"));
        proindata.addAll(new Log2_Fleet_ManagementClass("1114", "Motor", "Chair", "Leyte", "7am", "Manager"));
        proindata.addAll(new Log2_Fleet_ManagementClass("1115", "Truck", "Table", "Marikina", "7am", "Manager"));
        proindata.addAll(new Log2_Fleet_ManagementClass("1116", "Truck", "Table", "Pangasinan", "7am", "Manager"));
        proinbound.getItems().addAll(proindata);

    }

    public void displaydata() {

    }

    private void loadData() {

    }

    public void Save() {

        Log2_Fleet_ManagementScheduling fm = new Log2_Fleet_ManagementScheduling();

        try {

            String[][] fm_data
                    = {
                        {"department", scheddept.getText()},
                        {"itemname", scheditemname.getText()},
                        {"quantity", schedquantity.getText()},
                        {"destination", scheddestination.getText()},
                        {"departure", scheddeparture.getText()},
                        {"vehicle", schedvehicle.getValue()},
                        {"vehicletype", schedvtype.getValue()},
                        {"vehiclecapacity", schedvcapacity.getText()},
                        {"driver", scheddriver.getValue()}

                    };

            fm.insert(fm_data);
            loadData();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();

        } catch (Exception e) {

        }

    }

    public void Savetask() {

        Log2_Fleet_ManagementAddtask fm = new Log2_Fleet_ManagementAddtask();

        try {

            String[][] fm_data
                    = {
                        {"task", combovehicletask.getValue().toString()},
                        {"datetask", vehicledate.getValue().toString()}

                    };

            fm.insert(fm_data);
            loadData();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();

        } catch (Exception e) {

        }

    }

    public void Saveaddvehicle() {

        Log2_Fleet_ManagementAddvehicle fm = new Log2_Fleet_ManagementAddvehicle();

        try {

            String[][] fm_data
                    = {
                        {"vehicle", txtvehicle.getText()},
                        {"vehicletype", vehicletype.getText()},
                        {"plateno", plateno.getText()},
                        {"chassisno", chassisno.getText()}

                    };

            fm.insert(fm_data);
            loadData();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.show();

        } catch (Exception e) {

        }

    }

    @FXML
    private void btn_av(MouseEvent event) throws IOException {
        
         Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("Log2_Audit_Management_AddVehicle.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void btn_et(MouseEvent event) throws IOException {
        
         Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("Log2_Audit_Management_EditTask.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
    }

    

  
}
