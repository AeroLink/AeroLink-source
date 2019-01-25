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
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
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
    private TableColumn<Log2_Fleet_ManagementRequests, String> requesttypeoftransaction;
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
    private JFXButton btnsubmit;
    @FXML
    private JFXTimePicker schedtime;
    @FXML
    private ContextMenu contextmenudetails;
    @FXML
    private MenuItem detailspost;
    @FXML
    private TableView<?> tblob;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reqDisplaydata();
        reqloaddata();

       
        loadData();
        displaydata();

        getdataontableview();

        schedvehicle.setItems(vehicle);
        schedvehicle.setPromptText("Choose Vehicle");

        schedvtype.setItems(vtype);
        schedvtype.setPromptText("Choose type of Vehicle");

        scheddriver.setItems(driver);
        scheddriver.setPromptText("Choose Driver");

        
        btnsubmit.setOnMouseClicked(e -> Save());
        
        // disable textfield.
        scheddept.setEditable(false);
        scheditemname.setEditable(false);
        schedquantity.setEditable(false);
        scheddestination.setEditable(false);
        scheddeparture.setEditable(false);
        
        //tableright click
        this.tblob.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (event.getClickCount() == 2) {
                    this.contextmenudetails.show(tblob, event.getX(), event.getY());
                }
            }
        });
        
        this.tblob.setContextMenu(contextmenudetails);
        this.detailspost.setOnAction(action -> viewdetails());
       
    }
    
    private void viewdetails() {
         Modal md = Modal.getInstance(new Form("/FXMLS/Log2/fm/modals/Log2_Fleet_Management_VehicleModel.fxml").getParent());
        md.open();
    }

    private void reqDisplaydata() {

        requestdept.setCellValueFactory(new PropertyValueFactory<>("department"));
        requestitemname.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        requestsize.setCellValueFactory(new PropertyValueFactory<>("size"));
        requestquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        requestdestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        requestconsignee.setCellValueFactory(new PropertyValueFactory<>("consignee"));
        requestdeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
         requesttypeoftransaction.setCellValueFactory(new PropertyValueFactory<>("typeoftransaction")); 

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
            hm.get("typeoftransaction");

            fmrs.add(
                    new Log2_Fleet_ManagementRequests(
                            String.valueOf(hm.get("department")),
                            String.valueOf(hm.get("item_name")),
                            String.valueOf(hm.get("size")),
                            String.valueOf(hm.get("quantity")),
                            String.valueOf(hm.get("destination")),
                            String.valueOf(hm.get("consignee")),
                            String.valueOf(hm.get("departure")),
                            String.valueOf(hm.get("typeoftransaction"))
                                                
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


    public void displaydata() {

    }

    private void loadData() {

    }

    public void Save() {

        Log2_Fleet_ManagementScheduling fm = new Log2_Fleet_ManagementScheduling();

        try {

            String [][] fm_data
                    = {
                        {"department", scheddept.getText()},
                        {"itemname", scheditemname.getText()},
                        {"quantity", schedquantity.getText()},
                        {"destination", scheddestination.getText()},
                        {"departure", scheddeparture.getText()},
                        {"vehicle", schedvehicle.getValue()},
                        {"vehicletype", schedvtype.getValue()},
                        {"vehiclecapacity", schedvcapacity.getText()},
                        {"driver", scheddriver.getValue()},
                        {"time", schedtime.getValue().toString()}

                    };

            fm.insert(fm_data);
         
            loadData();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();
            
            

        } catch (Exception e) {

        }

    }

    @FXML
    private void btnview(MouseEvent event) {
    }

    @FXML
    private void btndispatch(MouseEvent event) {
    }

}
