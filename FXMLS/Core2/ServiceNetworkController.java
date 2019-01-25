/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.ClassFiles.SNTable_branch;
import Model.Core2.CORE2_Branch;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class ServiceNetworkController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
 /* FOR TABLE START */
    ObservableList<SNTable_branch> snb = FXCollections.observableArrayList();
    /* FOR TABLE END */
    CORE2_Branch branch = new CORE2_Branch();

    @FXML
    private AnchorPane SNrootPane;
    @FXML
    private TableView<SNTable_branch> tblBranch;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVT;
    @FXML
    private JFXButton SNviewM;
    @FXML
    private JFXButton SNviewR;
    @FXML
    private TextField srSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // para sa generated tableColumn ito
        snb.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblBranch.setItems(snb);
        });
        this.generateTableColumn();
        this.populateTable();
    }

    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumn() {
        tblBranch.getItems().clear();
        tblBranch.getColumns().removeAll(tblBranch.getColumns());

        TableColumn<SNTable_branch, String> code = new TableColumn<>("BRANCH CODE");
        TableColumn<SNTable_branch, String> assign = new TableColumn<>("PERSONNEL ASSIGN");
        TableColumn<SNTable_branch, String> address = new TableColumn<>("ADDRESS");
        TableColumn<SNTable_branch, String> country = new TableColumn<>("COUNTRY");

        code.setCellValueFactory((TableColumn.CellDataFeatures<SNTable_branch, String> param) -> param.getValue().branch_code);
        assign.setCellValueFactory((TableColumn.CellDataFeatures<SNTable_branch, String> param) -> param.getValue().personnel_assign);
        address.setCellValueFactory((TableColumn.CellDataFeatures<SNTable_branch, String> param) -> param.getValue().address);
        country.setCellValueFactory((TableColumn.CellDataFeatures<SNTable_branch, String> param) -> param.getValue().country);
        tblBranch.getColumns().addAll(code, assign, address,country);
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = branch.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = branch
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("branch_code", "country", "region", "city", "barangay",
                                            "street", "address", "province", "zipcode", "email",
                                            "number", "personnel_assign", "express", "pickup", "doortodoor");
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
        snb.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String branch_code = String.valueOf(drow.get("branch_code")); // dapat ganto kapag primary key
            String country = (String) drow.get("country");
            String region = (String) drow.get("region");
            String city = (String) drow.get("city");
            String barangay = (String) drow.get("barangay");
            String street = (String) drow.get("street");
            String address = (String) drow.get("address");
            String province = (String) drow.get("province");
            String zipcode = (String) drow.get("zipcode");
            String email = (String) drow.get("email");
            String number = (String) drow.get("number");
            String personnel_assign = (String) drow.get("personnel_assign"); // dapat ganto kapag primary key
            String ex = (String) drow.get("express");
            String pu = (String) drow.get("pickup");
            String dtd = (String) drow.get("doortodoor");

            snb.add(new SNTable_branch(branch_code, country, region, city, barangay, street, address, province,
                    zipcode, email, number, personnel_assign, ex, pu, dtd));
        }
    }

    @FXML
    private void BranchInfoModal(ActionEvent event) {
        FXMLS.Core2.Modals.SNviewCodeController.code = tblBranch.getSelectionModel().getSelectedItem().branch_code.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.region = tblBranch.getSelectionModel().getSelectedItem().region.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.province = tblBranch.getSelectionModel().getSelectedItem().province.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.barangay = tblBranch.getSelectionModel().getSelectedItem().barangay.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.zip = tblBranch.getSelectionModel().getSelectedItem().zipcode.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.address = tblBranch.getSelectionModel().getSelectedItem().address.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.email = tblBranch.getSelectionModel().getSelectedItem().email.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.number = tblBranch.getSelectionModel().getSelectedItem().number.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.fname = tblBranch.getSelectionModel().getSelectedItem().personnel_assign.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.express = tblBranch.getSelectionModel().getSelectedItem().ex.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.pickup = tblBranch.getSelectionModel().getSelectedItem().pu.getValue();
        FXMLS.Core2.Modals.SNviewCodeController.door = tblBranch.getSelectionModel().getSelectedItem().dtd.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewCode.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewCodeController.modalOpen = false;
        });
    }

    @FXML
    private void viewM(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewMonitoring.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewR(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewReport.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewRegistration(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewAddBranch.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewAddBranchController.modalOpen = false;
        });
    }

}
