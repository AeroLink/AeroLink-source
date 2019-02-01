/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.ClassFiles.SPTable_LoadNA;
import FXMLS.Core2.Modals.SPviewSPDetailsController;
import static FXMLS.Core2.Modals.SPviewSPDetailsController.pcountry;
import static FXMLS.Core2.Modals.SPviewSPDetailsController.peemail;
import static FXMLS.Core2.Modals.SPviewSPDetailsController.pefn;
import static FXMLS.Core2.Modals.SPviewSPDetailsController.pemail;
import Model.Core2.CORE2_ProvRegistration;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class ServiceProviderController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
    // dito lalabas yung another scene na tinatawag 
    @FXML
    private AnchorPane SProotPane;
    @FXML
    private JFXButton SPviewR;
    @FXML
    private TextField searchP;

    ObservableList<SPTable_LoadNA> sptbl = FXCollections.observableArrayList();
    CORE2_ProvRegistration cONEcd = new CORE2_ProvRegistration();
    @FXML
    private TableView<SPTable_LoadNA> tblOpenDetails;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVD;
    @FXML
    private MenuItem menuVO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sptbl.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblOpenDetails.setItems(sptbl);
        });

        this.GenerateTC();
        this.populateTable();

        searchP.setOnKeyTyped(e -> searchProvider());
    }

    public void GenerateTC() {
        tblOpenDetails.getItems().clear();
        tblOpenDetails.getColumns().removeAll(tblOpenDetails.getColumns());

        TableColumn<SPTable_LoadNA, String> name = new TableColumn<>("PROVIDER NAME");
        TableColumn<SPTable_LoadNA, String> address = new TableColumn<>("PROVIDER ADDRESS");
        TableColumn<SPTable_LoadNA, String> contact = new TableColumn<>("CONTACT");
        TableColumn<SPTable_LoadNA, String> country = new TableColumn<>("COUNTRY");
        TableColumn<SPTable_LoadNA, String> status = new TableColumn<>("STATUS");
        name.setCellValueFactory((param) -> param.getValue().provider_name);
        address.setCellValueFactory((param) -> param.getValue().provider_address);
        contact.setCellValueFactory((param) -> param.getValue().provider_contact);
        country.setCellValueFactory((param) -> param.getValue().country);
        status.setCellValueFactory((param) -> param.getValue().status);
        tblOpenDetails.getColumns().addAll(name, address, contact, country,status);
    }

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
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("provider_name",
                                            "provider_address",
                                            "provider_contact",
                                            "provider_email",
                                            "country",
                                            "status",
                                            "CONCAT('SP000',id) as code",
                                            "CONCAT(last_name,',',first_name,' ',middle_name) as fullname",
                                            "email");
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
        sptbl.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;
            String provider_name = (String) drow.get("provider_name");
            String provider_address = (String) drow.get("provider_address");
            String provider_contact = (String) drow.get("provider_contact");
            String provider_email = (String) drow.get("provider_email");
            String country = (String) drow.get("country");
            String status = (String) drow.get("status");
            String code = (String) drow.get("code");
            String pefn = (String) drow.get("fullname");
            String peemail = (String) drow.get("email");
            
            sptbl.add(new SPTable_LoadNA(provider_name, provider_address,
                    provider_contact,provider_email,country,status,code,pefn,peemail));
        }
    }

    @FXML
    public void spModal() {
        // sya yung ginamit pang tawag ng data via controller
        FXMLS.Core2.Modals.SPviewSPDetailsController.pname = tblOpenDetails.getSelectionModel().getSelectedItem().provider_name.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.paddress = tblOpenDetails.getSelectionModel().getSelectedItem().provider_address.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.pemail = tblOpenDetails.getSelectionModel().getSelectedItem().provider_email.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.pcontact = tblOpenDetails.getSelectionModel().getSelectedItem().provider_contact.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.pcode = tblOpenDetails.getSelectionModel().getSelectedItem().code.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.pcountry = tblOpenDetails.getSelectionModel().getSelectedItem().country.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.pefn = tblOpenDetails.getSelectionModel().getSelectedItem().PEfullname.getValue();
        FXMLS.Core2.Modals.SPviewSPDetailsController.peemail = tblOpenDetails.getSelectionModel().getSelectedItem().PEemail.getValue();

        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SPviewSPDetails.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SPviewSPDetailsController.modalOpen = false;
        });
    }

    @FXML
    public void ordercountModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SPviewOrder.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SPviewOrderController.modalOpen = false;
        });
    }

    // search
    public void searchProvider() {
        CORE2_ProvRegistration rgs = new CORE2_ProvRegistration();

        String SearchText = searchP.getText().equals("") ? "[a-z]" : searchP.getText();

        try {

            List listreq = rgs.where(new Object[][]{
                {"provider_name", "Like", "%" + SearchText + "%"}
            }).get();

            searchRequestor(listreq);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void searchRequestor(List requestor) {

        tblOpenDetails.getItems().clear();

        ObservableList<SPTable_LoadNA> sptbl = FXCollections.observableArrayList();

        try {

            for (Object d : requestor) {
                HashMap hm = (HashMap) d;   //exquisite casting
                hm.get("code");
                hm.get("provider_name");
                hm.get("provider_address");
                hm.get("provider_contact");
                hm.get("provider_email");
                hm.get("country");
                hm.get("status");
                hm.get("code");
                hm.get("pefn");
                hm.get("peemail");

                sptbl.add(new SPTable_LoadNA(
                        String.valueOf(hm.get("code")), // dapat ganto kapag primary key
                        String.valueOf(hm.get("provider_name")),
                        String.valueOf(hm.get("provider_address")),
                        String.valueOf(hm.get("provider_contact")),
                        String.valueOf(hm.get("provider_email")),
                        String.valueOf(hm.get("country")),
                        String.valueOf(hm.get("status")),
                        String.valueOf(hm.get("pefn")),
                        String.valueOf(hm.get("peemail"))
                ));
                tblOpenDetails.setItems(sptbl);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //ito yung pag call ng another scene pero hindi magiging modal 
    @FXML
    private void viewR(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SPviewReport.fxml"));
        SProotPane.getChildren().setAll(pane);
    }
}
