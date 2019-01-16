/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import Model.Core2.CORE2_barangay;
import Model.Core2.CORE2_province;
import Model.Core2.CORE2_region;
import Model.Core2.CORE2_type;
import Model.Core2.CORE2_zip;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class ServiceNetworkController implements Initializable {

//    ObservableList<String> regionList = FXCollections.observableArrayList("Region 1", "Region 2");
//    ObservableList<String> R1 = FXCollections.observableArrayList("Ilocos Norte", "Ilocos Sur", "La Union", "Pangasinan");
//    ObservableList<String> R2 = FXCollections.observableArrayList("Batanes", "Cagayan", "Isabela", "Nueva Vizcaya", "Quirino");

    CORE2_type type = new CORE2_type();
//    CORE2_region region = new CORE2_region();
//    CORE2_province province = new CORE2_province();
//    CORE2_barangay barangay = new CORE2_barangay();
//    CORE2_zip zip = new CORE2_zip();

    @FXML
    private AnchorPane SNrootPane;
    @FXML
    private JFXButton SNviewN;
    @FXML
    private JFXButton SNviewM;
    @FXML
    private JFXButton SNviewR;
    @FXML
    private JFXButton SNviewI;
    @FXML
    private ComboBox cbmType;
    @FXML
    private ComboBox cbmDriver;
    @FXML
    private JFXButton submit;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTnumber;
    @FXML
    private TextField txtMnumber;
    @FXML
    private TextField txtBaddress;
    @FXML
    private TextField txtS1;
    @FXML
    private TextField txtS3;
    @FXML
    private TextField txtS2;
    @FXML
    private ComboBox<?> cbmIcharge;
    @FXML
    private TableView<?> tVcharge;
    @FXML
    private TableView<?> tVdriver;
    @FXML
    private TextField txtRegion;
    @FXML
    private TextField txtBarangay;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtZipcode;
    @FXML
    private TextField txtEMPID1;
    @FXML
    private TextField txtEMPID2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //combobox kapag pipili ka kung philippines or singapore
        type.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            cbmType.getItems().add(row.get("country").toString());
        });

//        region.get().stream().forEach(action -> {
//            HashMap row = (HashMap) action;
//            cbmRegion.getItems().add(row.get("region_name").toString());
//        });
//
//        province.get().stream().forEach(action -> {
//            HashMap row = (HashMap) action;
//            cbmProvince.getItems().add(row.get("province_name").toString());
//        });

//        cbmRegion.setValue("Region 1");
//        cbmRegion.setItems(regionList);
//
//        cbmProvince.setValue("Ilocos Norte");
//        cbmProvince.setItems(R1);
//
//        zip.get().stream().forEach(action -> {
//            HashMap row = (HashMap) action;
//            cbmZip.getItems().add(row.get("zipcode").toString());
//        });
//
//        barangay.get().stream().forEach(action -> {
//            HashMap row = (HashMap) action;
//            cbmBarangay.getItems().add(row.get("barangay_name").toString());
//        });
    }

//    @FXML
//    private void provinceChange() {
//        if (cbmRegion.getValue().equals("Region 1")) {
//            cbmProvince.setValue("Ilocos Norte");
//            cbmProvince.setItems(R1);
//        }else{
//            cbmProvince.setValue("Batanes");
//            cbmProvince.setItems(R2);
//        }
//    }

    @FXML
    public void viewN() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewBranch.fxml"));
        SNrootPane.getChildren().setAll(pane);
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
    private void viewI(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SNviewInternationalBranch.fxml"));
        SNrootPane.getChildren().setAll(pane);
    }

}
