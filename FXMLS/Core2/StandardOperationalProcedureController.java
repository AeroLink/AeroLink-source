/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.ClassFiles.BOOKING_info;
import FXMLS.Core2.ClassFiles.SOPTable_package_information;
import Model.Core2.CORE1_Book;
import Model.Core2.CORE2_package;
import Model.Core2.CORE1_Customer_Details;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class StandardOperationalProcedureController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
    @FXML
    private AnchorPane SOProotPane;
    @FXML
    private TableView<SOPTable_package_information> tblPackage;

    ObservableList<SOPTable_package_information> spi = FXCollections.observableArrayList();

    CORE2_package cp = new CORE2_package();

    CORE1_Book bk = new CORE1_Book();

    ObservableList<BOOKING_info> b = FXCollections.observableArrayList();

    @FXML
    private TextField searchS;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private TextArea TAitem;
    @FXML
    private TextArea TAnotes;
    @FXML
    private TableView<BOOKING_info> tblViewSN;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVPD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        spi.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblPackage.setItems(spi);
        });

        b.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblViewSN.setItems(b);
        });
        // generate ng reference number & shipper name
        this.Generate();
        this.populate();
        // generate ng package number & status & shipper name
        this.GenerateTC();
        this.populateTable();

        // search for name in same textfield
        searchS.setOnKeyTyped(e -> searchBookCus());
        btnSubmit.setOnMouseClicked(e -> Insert());
    }

    // para sa search
    // generate ng table column para sa paglalabasan ng data dun sa search
    public void Generate() {
        tblViewSN.getItems().clear();
        tblViewSN.getColumns().removeAll(tblViewSN.getColumns());

        TableColumn<BOOKING_info, String> ref_no = new TableColumn<>("REFERENCE NO");
        TableColumn<BOOKING_info, String> ship_name = new TableColumn<>("SHIPPER NAME");
        ref_no.setCellValueFactory((param) -> param.getValue().ref_no);
        ship_name.setCellValueFactory((param) -> param.getValue().ship_name);
        tblViewSN.getColumns().addAll(ref_no, ship_name);
    }

    // para sa search
    public void populate() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = bk.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = bk
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .where(new Object[][]{{"status", "=", "Pending"}})
                                    .get("ref_no", "ship_name");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTablee(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }

    public void AddTablee(List rs) {
        b.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String book_no = String.valueOf(drow.get("book_no"));
            String ref_no = (String) drow.get("ref_no");
            String ship_name = (String) drow.get("ship_name");
            String ship_address = (String) drow.get("ship_address");
            String ship_brgy = (String) drow.get("ship_brgy");
            String ship_city = (String) drow.get("ship_city");
            String ship_province = (String) drow.get("ship_province");
            String ship_zip = (String) drow.get("ship_zip");
            String ship_email = (String) drow.get("ship_email");
            String ship_contact = (String) drow.get("ship_contact");
            String rec_name = (String) drow.get("rec_name");
            String rec_address = (String) drow.get("rec_address");
            String rec_brgy = (String) drow.get("rec_brgy");
            String rec_city = (String) drow.get("rec_city");
            String rec_province = (String) drow.get("rec_province");
            String rec_zip = (String) drow.get("rec_zip");
            String rec_contact = (String) drow.get("rec_contact");
            String serv_type = (String) drow.get("serv_type");
            String box = (String) drow.get("box");
            String quantity = (String) drow.get("quantity");
            String insurance = (String) drow.get("insurance");
            String liability = (String) drow.get("liability");
            String status = (String) drow.get("status");
            String book_date = (String) drow.get("book_date");

            b.add(new BOOKING_info(book_no, ref_no, ship_name, ship_address,
                    ship_brgy, ship_city, ship_province, ship_zip, ship_email, ship_contact,
                    rec_name, rec_address, rec_brgy, rec_city, rec_province, rec_zip,
                    rec_contact, serv_type, box, quantity, insurance, liability, status,
                    book_date));
        }
    }

    // search for name
    public void searchBookCus() {
        CORE1_Book book = new CORE1_Book();

        String SearchText = searchS.getText().equals("") ? "[a-z]" : searchS.getText();

        try {

            List listreq = book.where(new Object[][]{
                {"ship_name", "Like", "%" + SearchText + "%"},
                {"status", "=", "Pending"}
            }).get();
            searchRequestor(listreq);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void searchRequestor(List requestor) {
        tblViewSN.getItems().clear();
        ObservableList<BOOKING_info> booked = FXCollections.observableArrayList();
        try {
            for (Object d : requestor) {
                HashMap hm = (HashMap) d;   //exquisite casting
                hm.get("book_no");
                hm.get("ref_no");
                hm.get("ship_name");
                hm.get("ship_address");
                hm.get("ship_brgy");
                hm.get("ship_city");
                hm.get("ship_province");
                hm.get("ship_zip");
                hm.get("ship_email");
                hm.get("ship_contact");
                hm.get("rec_name");
                hm.get("rec_address");
                hm.get("rec_brgy");
                hm.get("rec_city");
                hm.get("rec_province");
                hm.get("rec_zip");
                hm.get("rec_contact");
                hm.get("serv_type");
                hm.get("box");
                hm.get("quantity");
                hm.get("insurance");
                hm.get("liability");
                hm.get("status");
                hm.get("book_date");

                booked.add(new BOOKING_info(
                        String.valueOf(hm.get("book_no")),
                        String.valueOf(hm.get("ref_no")),
                        String.valueOf(hm.get("ship_name")),
                        String.valueOf(hm.get("ship_address")),
                        String.valueOf(hm.get("ship_brgy")),
                        String.valueOf(hm.get("ship_city")),
                        String.valueOf(hm.get("ship_province")),
                        String.valueOf(hm.get("ship_zip")),
                        String.valueOf(hm.get("ship_email")),
                        String.valueOf(hm.get("ship_contact")),
                        String.valueOf(hm.get("rec_name")),
                        String.valueOf(hm.get("rec_address")),
                        String.valueOf(hm.get("rec_brgy")),
                        String.valueOf(hm.get("rec_city")),
                        String.valueOf(hm.get("rec_province")),
                        String.valueOf(hm.get("rec_zip")),
                        String.valueOf(hm.get("rec_contact")),
                        String.valueOf(hm.get("serv_type")),
                        String.valueOf(hm.get("box")),
                        String.valueOf(hm.get("quantity")),
                        String.valueOf(hm.get("insurance")),
                        String.valueOf(hm.get("liability")),
                        String.valueOf(hm.get("status")),
                        String.valueOf(hm.get("book_date"))
                ));
                tblViewSN.setItems(booked);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void GenerateTC() {
        tblPackage.getItems().clear();
        tblPackage.getColumns().removeAll(tblPackage.getColumns());

        TableColumn<SOPTable_package_information, String> package_no = new TableColumn<>("PACKAGE NUMBER");
        TableColumn<SOPTable_package_information, String> ref_no = new TableColumn<>("REFERENCE NUMBER");
        TableColumn<SOPTable_package_information, String> ship_name = new TableColumn<>("SHIPPER NAME");
//        TableColumn<SOPTable_package_information, String> book_date = new TableColumn<>("BOOK DATE");
//        TableColumn<SOPTable_package_information, String> status = new TableColumn<>("STATUS");
        package_no.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().package_no);
        ref_no.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().ref_no);
        ship_name.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().ship_name);
//        book_date.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().book_date);
//        status.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().status);
        tblPackage.getColumns().addAll(package_no,ref_no,ship_name);// book_date, status
    }

    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = cp.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = cp
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.INNER, "aerolink.tbl_core1_booking", "book_no", "bk", "=", "ref_no")
                                    .get(
                                            "package_no",
                                            "ref_no",
                                            "ship_name",
                                            "list_item",
                                            "note"
                                    //                                            ,"bk.rec_name",
                                    //                                            "CONCAT(bk.rec_province,',',bk.rec_brgy,' ',bk.rec_address,','bk.city,','bk.zip) as address",
                                    //                                            "bk.rec_contact",
                                    //                                            "bk.serv_type",
                                    //                                            "bk.box",
                                    //                                            "bk.quantity",
                                    //                                            "bk.status",
                                    //                                            "bk.book_date"
                                    );
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTable(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTable(List rs) {
        spi.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String package_no = String.valueOf(drow.get("package_no"));
            String ref_no = (String) drow.get("ref_no");
            String ship_name = (String) drow.get("ship_name");
            String list_item = (String) drow.get("list_item");
            String note = (String) drow.get("note");
//            String rec_name = (String) drow.get("rec_name");
//            String CompleteAddress = (String) drow.get("CompleteAddress");
//            String rec_contact = (String) drow.get("rec_contact");
//            String serv_type = (String) drow.get("serv_type");
//            String box = (String) drow.get("box");
//            String quantity = (String) drow.get("quantity");
//            String status = (String) drow.get("status");
//            String book_date = (String) drow.get("book_date");

            spi.add(new SOPTable_package_information(package_no, ref_no, ship_name,
                     list_item, note
            //                    rec_name,CompleteAddress,rec_contact,serv_type,box,quantity,status,book_date
            ));
        }
    }

    public void Insert() {
        CORE2_package pack = new CORE2_package();
        try {
            String[][] pk = {
                {"ref_no", tblViewSN.getSelectionModel().getSelectedItem().ref_no.getValue()},
                {"ship_name", tblViewSN.getSelectionModel().getSelectedItem().ship_name.getValue()},
                {"list_item", TAitem.getText()},
                {"note", TAnotes.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (pack.insert(pk)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                tblViewSN.getSelectionModel().getSelectedItem().ref_no.getValue();
                tblViewSN.getSelectionModel().getSelectedItem().ship_name.getValue();
                TAitem.setText("");
                TAnotes.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
//                if ((txtR.isEmpty()||txtC.isEmpty()||txtB.isEmpty()||txtS.isEmpty()
//                        ||txtA.isEmpty()||txtP.isEmpty()||txtZ.isEmpty()||txtE.isEmpty()
//                        ||txtCN.isEmpty()||txtM.isEmpty())) {
//                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // mga button sa taas
    @FXML
    private void inspectionguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewInspectionGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void manageguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPcreateManageGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void packageinspection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewPackageInspection.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

}
