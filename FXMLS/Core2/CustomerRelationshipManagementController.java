/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.ClassFiles.core1_TableModel_Customer_Details;
import Model.Core2.CORE1_Customer_Details;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXTextField;
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
 * @author JPEG
 */
public class CustomerRelationshipManagementController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */

 /* CONTAINERS,CONTROLS,CUSTOME,MENU START */
    @FXML
    private AnchorPane CRMrootPane;
    @FXML
    private TableView<core1_TableModel_Customer_Details> tblCustomer;
    /* CONTAINERS,CONTROLS,CUSTOME,MENU END */

 /* FOR TABLE START */
    ObservableList<core1_TableModel_Customer_Details> detail = FXCollections.observableArrayList();
    /* FOR TABLE END */

 /* MODEL INSTANTIATE START */
    CORE1_Customer_Details cONEcd = new CORE1_Customer_Details();
    /* MODEL INSTANTIATE END */
    @FXML
    private TextField crmSearchh;
    @FXML
    private ContextMenu contextView;
    @FXML
    private MenuItem menuView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // para sa generated tableColumn ito
        detail.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblCustomer.setItems(detail);
        });
        this.generateTableColumn();
        this.populateTable();
    }
    
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumn() {
        tblCustomer.getItems().clear();
        tblCustomer.getColumns().removeAll(tblCustomer.getColumns());

        TableColumn<core1_TableModel_Customer_Details, String> customer_id = new TableColumn<>("CUSTOMER NO.");
        TableColumn<core1_TableModel_Customer_Details, String> pack_id = new TableColumn<>("PACKAGE NO.");
        TableColumn<core1_TableModel_Customer_Details, String> shipper = new TableColumn<>("SHIPPER NAME");
        TableColumn<core1_TableModel_Customer_Details, String> consignee = new TableColumn<>("CONSIGNEE NAME");
        TableColumn<core1_TableModel_Customer_Details, String> status = new TableColumn<>("STATUS");

        customer_id.setCellValueFactory((TableColumn.CellDataFeatures<core1_TableModel_Customer_Details, String> param) -> param.getValue().a);
        pack_id.setCellValueFactory((TableColumn.CellDataFeatures<core1_TableModel_Customer_Details, String> param) -> param.getValue().b);
        shipper.setCellValueFactory((TableColumn.CellDataFeatures<core1_TableModel_Customer_Details, String> param) -> param.getValue().c);
        consignee.setCellValueFactory((TableColumn.CellDataFeatures<core1_TableModel_Customer_Details, String> param) -> param.getValue().d);
        status.setCellValueFactory((TableColumn.CellDataFeatures<core1_TableModel_Customer_Details, String> param) -> param.getValue().e);
        tblCustomer.getColumns().addAll(customer_id, pack_id, shipper, consignee, status);
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = cONEcd.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = cONEcd
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    .join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get(
                                            "CONCAT('CRM',customer_id) as customer_id",
                                            "pid.pack_id as pack_id",
                                            "CONCAT(UPPER(lastname),',',UPPER(firstname),' ',middlename) as shipper",
                                            "CONCAT(UPPER(receiver_lastname),',',receiver_firstname,' ',receiver_middlename) as consignee",
                                            "status");
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
        detail.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String a = String.valueOf(drow.get("customer_id")); // dapat ganto kapag primary key
            String b = String.valueOf(drow.get("pack_id")); // dapat ganto kapag primary key
            String c = (String) drow.get("shipper");
            String d = (String) drow.get("consignee");
            String e = (String) drow.get("status");

            detail.add(new core1_TableModel_Customer_Details(a, b, c, d, e));
        }
    }

    // ito yung modal kapag nag view gamit yung contextmenu 
    @FXML
    public void viewModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/CRMviewData.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.CRMviewDataController.modalOpen = false;
        });
    }

    @FXML
    private void viewComplaints(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewComplaints.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewReports(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewReport.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }
    
}
