/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_outboundtable;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementRequest;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_reportscol;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_inboundsupplier;
import Model.Log2.Log2_dispatching;
import Model.Log2.Log2_reports;
import Model.Log2_Fleet_ManagementScheduling;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementController implements Initializable {

    private Connection con = DBconfig.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ObservableList<Log2_Fleet_ManagementClass> reqdata = FXCollections.observableArrayList();

    ObservableList<Log2_Fleet_ManagementClass> prodata = FXCollections.observableArrayList();

    ObservableList<Log2_Fleet_ManagementClass> proindata = FXCollections.observableArrayList();
    
    ObservableList<Log2_Fleet_Management_inboundsupplier> inbounddata = FXCollections.observableArrayList();

    ObservableList<String> vehicle = FXCollections.observableArrayList("Truck", "Motorcycle");

    ObservableList<String> vtype = FXCollections.observableArrayList();

    ObservableList<String> driver = FXCollections.observableArrayList("Marvin", "Pedro", "Juan");

    ObservableList<String> task = FXCollections.observableArrayList("Deliver", "", "");

    ObservableList<String> remarks = FXCollections.observableArrayList("Accomplish", "Unaccomplish");

    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestdept;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestitemname;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestsize;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestquantity;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestdestination;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestconsignee;
    @FXML
    private TableColumn<Log2_Fleet_ManagementRequest, String> requestdeparture;
    @FXML
    private TableView<Log2_Fleet_ManagementRequest> tblreq;
    ObservableList<Log2_Fleet_ManagementRequest> tblreq1 = FXCollections.observableArrayList();
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
    private TableView<Log2_Fleet_Management_outboundtable> tblob;
    @FXML
    private TableView<?> tbl_dispatched;
    @FXML
    private JFXTextField txt_vmodel;
    @FXML
    private JFXTextField txt_plateno;
    @FXML
    private JFXComboBox<String> combo_remarks;
    @FXML
    private TableView<Log2_Fleet_Management_reportscol> tblreports;
    ObservableList<Log2_Fleet_Management_reportscol> reports = FXCollections.observableArrayList();
    @FXML
    private JFXTextField searchtxt;
    @FXML
    private AnchorPane anchorinfo;
    @FXML
    private Tab inboundout;
    @FXML
    private JFXTabPane fleettab;
    @FXML
    private Tab outbound2;
    @FXML
    private GridPane gridpane;
    @FXML
    private JFXTextField vehicleid;
    @FXML
    private TableColumn<Log2_Fleet_Management_inboundsupplier , String> iitemname;
    @FXML
    private TableColumn<Log2_Fleet_Management_inboundsupplier , String> iquantity;
    @FXML
    private TableColumn<Log2_Fleet_Management_inboundsupplier , String> idateofdelivery;
    @FXML
    private TableColumn<Log2_Fleet_Management_inboundsupplier , String> istatus;
    @FXML
    private TableView<Log2_Fleet_Management_inboundsupplier> tblinbound;
    @FXML
    private JFXTextField txtinbounditemname;
    @FXML
    private JFXTextField txtinboundquantity;
    @FXML
    private JFXTextField txtinbounddate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generatetable();

        btnclick();
        this.loaddatarequestvehicles();

        //reports tab
        this.generatereportscol();
        this.loaddatareports();

        reqDisplaydata();
        displayreq();

        loadData();
        displaydata();
        display();

        getdataontableview();
        passingdatatotxtfield();

        schedvehicle.setItems(vehicle);
        schedvehicle.setPromptText("Choose Vehicle");

        vehiclemodel();
        schedvtype.setPromptText("Choose type of Vehicle");

        scheddriver.setItems(driver);
        scheddriver.setPromptText("Choose Driver");

        combo_remarks.setItems(remarks);
        combo_remarks.setPromptText("Choose Remarks");

        btnsubmit.setOnMouseClicked(e -> Save());

        // disable textfield.
        scheddept.setEditable(false);
        scheditemname.setEditable(false);
        schedquantity.setEditable(false);
        scheddestination.setEditable(false);
        scheddeparture.setEditable(false);

        //dispatch table...
        txt_vmodel.setEditable(false);
        txt_plateno.setEditable(false);

        //table rightclick...
        this.tblob.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (event.getClickCount() == 2) {
                    this.contextmenudetails.show(tblob, event.getX(), event.getY());
                }
            }
        });

        this.tblob.setContextMenu(contextmenudetails);
        this.detailspost.setOnAction(action -> viewdetails());

        // inbound na to!!!!
        inbounddisplay();
        inboundloaddata();
        inboundgetdata();
         txtinbounditemname.setEditable(false);
         txtinboundquantity.setEditable(false);
         txtinbounddate.setEditable(false);
    }

    private void viewdetails() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Log2/fm/modals/Log2_Fleet_Management_VehicleModel.fxml").getParent());
        md.open();
    }
    ObservableList<Log2_Fleet_Management_outboundtable> tblob1 = FXCollections.observableArrayList();

    // generate table ng outbound
    private void generatetable() {

        tblob.getColumns().removeAll(tblob.getColumns());

        TableColumn<Log2_Fleet_Management_outboundtable, String> vehicle_model = new TableColumn<>("Vehicle Model");
        TableColumn<Log2_Fleet_Management_outboundtable, String> plate_no = new TableColumn<>("Plate No");

        vehicle_model.setCellValueFactory(new PropertyValueFactory<>("Vehicle_model"));
        plate_no.setCellValueFactory(new PropertyValueFactory<>("Plate_no"));

        tblob.getColumns().addAll(vehicle_model, plate_no);

    }

    //Choose
    @FXML
    public void vehiclemodel() {
        vtype.clear();
        try {

            String choose = "select * from aerolink.tbl_log2_fleet_vehicle where fvehicle_type = '" + schedvehicle.getSelectionModel().getSelectedItem() + "' ";
            pst = con.prepareStatement(choose);
            rs = pst.executeQuery();
            while (rs.next()) {
                vtype.add(rs.getString("fvehicle_model"));

            }
            schedvtype.setItems(vtype);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void vehiclemodel1() {

        try {

            String choose = "select * from aerolink.tbl_log2_fleet_vehicle where fvehicle_model = '" + schedvtype.getSelectionModel().getSelectedItem() + "' ";
            pst = con.prepareStatement(choose);
            rs = pst.executeQuery();
            while (rs.next()) {
                schedvcapacity.setText(rs.getString("fvehicle_cap"));
                vehicleid.setText(rs.getString("fleet_vehicle_ID"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //need para sa loaddata..
    ObservableList<Log2_Fleet_Management_outboundtable> outbound = FXCollections.observableArrayList();
    long DummyCount = 0;
    long GlobalCount = 0;
    Log2_dispatching obdb = new Log2_dispatching("dispatching");

    /* private void loadDataoutbound() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2fm")) {
                obdb.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    tblob.getItems().clear();
                    List<HashMap> hash = obdb.get();
                    hash.stream().forEach(e -> {
                        outbound.add(new Log2_Fleet_Management_outboundtable(
                                String.valueOf(e.get("vehiclemodel")),
                                String.valueOf(e.get("plateno"))
                        ));
                    });
                    tblob.setItems(outbound);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Fleet_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }*/

    //reports col
    private void generatereportscol() {

        tblreports.getColumns().removeAll(tblreports.getColumns());

        TableColumn<Log2_Fleet_Management_reportscol, String> delivery_no = new TableColumn<>("Delivery Number");
        TableColumn<Log2_Fleet_Management_reportscol, String> vehicle_model = new TableColumn<>("Vehicle Model");
        TableColumn<Log2_Fleet_Management_reportscol, String> date_delivered = new TableColumn<>("Date Delivered");

        delivery_no.setCellValueFactory(new PropertyValueFactory<>("delivery_no"));
        vehicle_model.setCellValueFactory(new PropertyValueFactory<>("vehicle_model"));
        date_delivered.setCellValueFactory(new PropertyValueFactory<>("date_delivered"));

        tblreports.getColumns().addAll(delivery_no, vehicle_model, date_delivered);

    }

    // reports load data
    long DummyCount2 = 0;
    long GlobalCount2 = 0;
    Log2_reports rp = new Log2_reports("reports");

    private void loaddatareports() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2fm")) {
                rp.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount2 != GlobalCount2) {
                    tblreports.getItems().clear();
                    List<HashMap> hash = rp.get();
                    hash.stream().forEach(e -> {
                        reports.add(new Log2_Fleet_Management_reportscol(
                                String.valueOf(e.get("delivery_no")),
                                String.valueOf(e.get("vehicle_model")),
                                String.valueOf(e.get("date_delivered"))
                        ));
                    });
                    tblreports.setItems(reports);
                    GlobalCount2 = DummyCount2;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Fleet_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    private void loaddatarequestvehicles() {

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
    /*
    private void reqloaddata() {

        Model.Log2_Fleet_ManagementRequest fmr = new Model.Log2_Fleet_ManagementRequest();

        ObservableList<Log2_Fleet_ManagementRequest> fmrs = FXCollections.observableArrayList();
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
                    new Log2_Fleet_ManagementRequest(
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

    }*/

    ObservableList<Log2_Fleet_ManagementRequest> tblreq11 = FXCollections.observableArrayList();

    private void displayreq() {
        tblreq11.clear();
        try {
            String display = "select * from aerolink.tbl_Log2_request";
            pst = con.prepareStatement(display);
            rs = pst.executeQuery();
            while (rs.next()) {
                tblreq11.add(new Log2_Fleet_ManagementRequest("" + rs.getString("request_no"), rs.getString("department"), rs.getString("item_name"), rs.getString("size"), rs.getString("quantity"), rs.getString("destination"), rs.getString("consignee"), rs.getString("departure")));
            }
            tblreq.setItems(tblreq11);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getdataontableview() {
        tblreq.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Fleet_ManagementRequest rd = tblreq.getItems().get(tblreq.getSelectionModel().getSelectedIndex());
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

    public void btnclick() {
        btnsubmit.setOnMouseClicked(e -> {
            clearr();
        });
    }

    private void loadData() {

    }

    public void Save() {

        Log2_Fleet_ManagementScheduling fm = new Log2_Fleet_ManagementScheduling();

        try {

            String[][] fm_data
                    = {
                        {"fleet_vehicle_ID", vehicleid.getText()},
                        {"department", scheddept.getText()},
                        {"itemname", scheditemname.getText()},
                        {"quantity", schedquantity.getText()},
                        {"destination", scheddestination.getText()},
                        {"departure", scheddeparture.getText()},
                        {"driver", scheddriver.getValue()},
                        {"time", schedtime.getValue().toString()}

                    };

            fm.insert(fm_data);
            loadData();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            cleartxt();
            display();
            saved.showAndWait();
            
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Log2_Fleet_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btndispatch(MouseEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Log2/fm/modals/Log2_Fleet_Management_Dispatch.fxml").getParent());
        md.open();
    }

    @FXML
    private void btn_deliveryrecord(MouseEvent event) {

        Modal md = Modal.getInstance(new Form("/FXMLS/Log2/fm/modals/Log2_Fleet_Management_DeliveryRecord.fxml").getParent());
        md.open();

    }

    private void passingdatatotxtfield() {

    }

    @FXML
    public void printreports() {
        try {

            String src = "src\\FXMLS\\Log2\\newReport.jrxml";

            JasperReport jpr = JasperCompileManager.compileReport(src);
            HashMap<String, Object> hm1 = new HashMap<>();

            ArrayList<Log2_Fleet_Management_reportscol> docfiles = new ArrayList<>();
            for (Log2_Fleet_Management_reportscol tf : reports) {
                docfiles.add(new Log2_Fleet_Management_reportscol("" + tf.getDelivery_no(), "" + tf.getVehicle_model(), "" + tf.getDate_delivered()));
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(docfiles);
            JasperPrint jp = JasperFillManager.fillReport(jpr, hm1, jcs);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void searchrep() {

        reports.clear();
        try {
            String search = "select * from aerolink.tbl_log2_fleet_reports where delivery_no like '" + searchtxt.getText() + "%' or date_delivered like '" + searchtxt.getText() + "%' or vehicle_model like '" + searchtxt.getText() + "%'";
            pst = con.prepareStatement(search);
            rs = pst.executeQuery();
            while (rs.next()) {
                reports.add(new Log2_Fleet_Management_reportscol("" + rs.getString("delivery_no"), rs.getString("vehicle_model"), rs.getString("date_delivered")));

            }
            tblreports.setItems(reports);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void clearr() {

        fleettab.getSelectionModel().selectNext();

    }

    public void cleartxt() {
        TextField txt = null;
        for (Node n : gridpane.getChildren()) {
            if (n.getClass().toString().contains("TextField")) {
                txt = (TextField) n;
                txt.setText(null);
            }

        }

        ComboBox c = null;
        for (Node n : gridpane.getChildren()) {
            if (n.getClass().toString().contains("ComboBox")) {
                c = (ComboBox) n;
                c.setValue("");
            }
        }

        schedtime.setValue(null);
        tblreq.setPickOnBounds(false);
        reqDisplaydata();
        displayreq();
    }

    public void display() {
        tblob1.clear();
        try {
            String select = "select * from aerolink.tbl_log2_fleet_vehicle as atfv inner join aerolink.tbl_log2_requestdeliveryscheduling as atlr on atfv.fleet_vehicle_ID = atlr.fleet_vehicle_ID";
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            while (rs.next()) {
                tblob1.add(new Log2_Fleet_Management_outboundtable(rs.getString("fvehicle_model"), rs.getString("fplate_no")));

            }
            tblob.setItems(tblob1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleterow(MouseEvent event) {
        tblreq.getItems().removeAll(tblreq.getSelectionModel().getSelectedItem());
    }

    private void inbounddisplay(){ 
    
         iitemname.setCellValueFactory(new PropertyValueFactory<>("iitemname"));
        iquantity.setCellValueFactory(new PropertyValueFactory<>("iquantity"));
        idateofdelivery.setCellValueFactory(new PropertyValueFactory<>("idateofdelivery"));
        istatus.setCellValueFactory(new PropertyValueFactory<>("istatus"));
    }
    
    private void inboundloaddata(){
    
        inbounddata.removeAll(inbounddata);
        inbounddata.addAll(new Log2_Fleet_Management_inboundsupplier("Table", "450", "01/18/19", "Delivered"));
        inbounddata.addAll(new Log2_Fleet_Management_inboundsupplier("Chair","280","01/25/19","Delivered"));
        inbounddata.addAll(new Log2_Fleet_Management_inboundsupplier("Cabinet","350","01/29/19","Delivered"));
        inbounddata.addAll(new Log2_Fleet_Management_inboundsupplier("Table","450","02/02/19","Delivered"));
        tblinbound.getItems().addAll(inbounddata);
    }
   
   
    private void inboundgetdata(){
     
         tblinbound.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log2_Fleet_Management_inboundsupplier rd = tblinbound.getItems().get(tblinbound.getSelectionModel().getSelectedIndex());
                txtinbounditemname.setText(rd.getIitemname());
                txtinboundquantity.setText(rd.getIquantity());
                txtinbounddate.setText(rd.getIdateofdelivery());
          
           

            }
        });
     }
   

}
