/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

//import Model.Core2.CORE2_Branch;
//import Model.Core2.CORE2_services;
import Model.Core2.CORE2_Branch;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SNviewMonitoringController implements Initializable {

    @FXML
    private AnchorPane SNrootPane;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVP;
    @FXML
    private MenuItem menuVA;
    @FXML
    private JFXButton SNviewN;
    @FXML
    private JFXButton SNviewR;
    @FXML
    private ComboBox cbbFilter;
    @FXML
    private ComboBox cbbShowFilter;

    ObservableList<String> filter = FXCollections.observableArrayList("STATUS", "BRANCH", "SERVICE", "ORIGIN", "DESTINATION", "PROVIDER");

    ObservableList<String> services = FXCollections.observableArrayList("EXPRESS", "PICK UP", "DOOR TO DOOR");
    ObservableList<String> status = FXCollections.observableArrayList("PENDING", "HOLD", "ACTIVE", "DELIVERED", "ONBOARD");
    ObservableList<String> provider = FXCollections.observableArrayList("AIR 21", "BLACK ARROW", "LBC", "LAWGIC");
    ObservableList<String> branch = FXCollections.observableArrayList("BULACAN", "QUEZON CITY", "CALOOCAN");
//    CORE2_services serv = new CORE2_services();
    CORE2_Branch bch = new CORE2_Branch();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbbFilter.setValue("BRANCH");
        cbbFilter.setItems(filter);

        cbbShowFilter.setValue("PENDING");
        cbbShowFilter.setItems(status);

        cbbShowFilter.setValue("EXPRESS");
        cbbShowFilter.setItems(services);

        cbbShowFilter.setValue("AIR 21");
        cbbShowFilter.setItems(provider);

        cbbShowFilter.setValue("BULACAN");
        cbbShowFilter.setItems(branch);
// services
//        serv.get().stream().forEach(action -> {
//            HashMap row = (HashMap) action;
//            cbbShowFilter.getItems().add(row.get("serv_name").toString());
//        });
//        // branch

    }

    @FXML
    private void cbbMainFilter() {
        if (cbbFilter.getValue().equals("STATUS")) {
            cbbShowFilter.setValue("PENDING");
            cbbShowFilter.setItems(status);
        }
        if (cbbFilter.getValue().equals("SERVICE")) {
            cbbShowFilter.setValue("EXPRESS");
            cbbShowFilter.setItems(services);
        }
        if (cbbFilter.getValue().equals("PROVIDER")) {
            cbbShowFilter.setValue("AIR 21");
            cbbShowFilter.setItems(provider);
        }
        if (cbbFilter.getValue().equals("BRANCH")) {
            cbbShowFilter.setValue("BULACAN");
            cbbShowFilter.setItems(branch);
        }

    }

    @FXML
    private void pacnoModal(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewPackage.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewPackageController.modalOpen = false;
        });
    }

    @FXML
    private void airlineModal(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewAirline.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewAirlineController.modalOpen = false;
        });
    }

    @FXML
    public void viewN() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceNetwork.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewR(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewReport.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

}
