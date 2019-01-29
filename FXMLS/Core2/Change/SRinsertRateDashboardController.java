/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.TableModel_Discount;
import FXMLS.Core2.ClassFiles.TableModel_Oservices;
import FXMLS.Core2.ClassFiles.TableModel_Packrequirements;
import FXMLS.Core2.ClassFiles.TableModel_Rate;
import FXMLS.Core2.ClassFiles.TableModel_surcharges;
import Model.Core2.CORE2_discount;
import Model.Core2.CORE2_itmrate;
import Model.Core2.CORE2_optional_service;
import Model.Core2.CORE2_pack_requirements;
import Model.Core2.CORE2_surcharges;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SRinsertRateDashboardController implements Initializable {

    /* MODEL INSTANTIATE START */
//    CORE2_type type = new CORE2_type();
    /* MODEL INSTANTIATE END */

 /* FOR TABLE START */
    ObservableList<TableModel_Discount> dis = FXCollections.observableArrayList();
    ObservableList<TableModel_Rate> rat = FXCollections.observableArrayList();
    ObservableList<TableModel_Packrequirements> pac = FXCollections.observableArrayList();
    ObservableList<TableModel_surcharges> sur = FXCollections.observableArrayList();
    ObservableList<TableModel_Oservices> opt = FXCollections.observableArrayList();
    /* FOR TABLE END */

 /* CONTAINERS,CONTROLS,CUSTOME,MENU START */
    @FXML
    private TableView<TableModel_Discount> tblDiscount;
    @FXML
    private TableColumn<TableModel_Discount, String> colSub;
    @FXML
    private TableColumn<TableModel_Discount, String> colDis;
    @FXML
    private TableView<TableModel_Rate> tblRate;
    @FXML
    private TableColumn<TableModel_Rate, String> colItem;
    @FXML
    private TableColumn<TableModel_Rate, String> colPer;
    @FXML
    private TableColumn<TableModel_Rate, String> colQuan;
    @FXML
    private TableColumn<TableModel_Rate, String> colHWC;
    @FXML
    private TableColumn<TableModel_Rate, String> colVG;
    @FXML
    private TableView<TableModel_Packrequirements> tblTray;
    @FXML
    private TableColumn<TableModel_Packrequirements, String> colTray;
    @FXML
    private TableColumn<TableModel_Packrequirements, String> colSize;
    @FXML
    private TableColumn<TableModel_Packrequirements, String> colMinw;
    @FXML
    private TableColumn<TableModel_Packrequirements, String> colAmount;
    @FXML
    private TableView<TableModel_surcharges> tblSur;
    @FXML
    private TableColumn<TableModel_surcharges, String> colSur;
    @FXML
    private TableColumn<TableModel_surcharges, String> colShwc;
    @FXML
    private TableColumn<TableModel_surcharges, String> colSac;
    @FXML
    private TableView<TableModel_Oservices> tblOptional;
    @FXML
    private TableColumn<TableModel_Oservices, String> colOpt;
    @FXML
    private TableColumn<TableModel_Oservices, String> colOhwc;
    @FXML
    private TableColumn<TableModel_Oservices, String> colOac;
    @FXML
    private JFXButton SRviewRD;
    /* CONTAINERS,CONTROLS,CUSTOME,MENU END */
    @FXML
    private AnchorPane SRrootPane;
    // SURCHARGES
    @FXML
    private TextField txtSS;
    @FXML
    private TextField txtSHWC;
    @FXML
    private TextField txtSAC;
    @FXML
    private JFXButton btnSurcharges;
    @FXML
    private JFXButton btnSurchargesCancel;
    // OPTIONAL SERVICES
    @FXML
    private TextField txtOS;
    @FXML
    private TextField txtOSHWC;
    @FXML
    private TextField txtOSAC;
    @FXML
    private JFXButton btnOptionalServices;
    @FXML
    private JFXButton btnOptionalServicesCancel;
    // DECLARED ITEM
    @FXML
    private TextField txtDI;
    @FXML
    private TextField txtDIP;
    @FXML
    private TextField txtDIQD;
    @FXML
    private TextField txtDIHWC;
    @FXML
    private TextField txtDIVAT;
    @FXML
    private JFXButton btnDeclaredItem;
    @FXML
    private JFXButton btnDeclaredItemCancel;
    // DISCOUNT
    @FXML
    private TextField txtDS;
    @FXML
    private TextField txtDP;
    @FXML
    private JFXButton btnDiscount;
    @FXML
    private JFXButton btnDiscountCancel;
    // 
    @FXML
    private TextField txtEST;
    @FXML
    private TextField txtESS;
    @FXML
    private TextField txtESMW;
    @FXML
    private TextField txtESA;
    @FXML
    private JFXButton btnExtraService;
    @FXML
    private JFXButton btnExtraServiceCancel;
    @FXML
    private TextArea TAphilippines;
    @FXML
    private TextArea TAsingapore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generateDiscount();
        loadDiscount();
        generateRate();
        loadRate();
        generatePack();
        loadPack();
        generateSurcharges();
        loadSurcharges();
        generateOptServ();
        loadOptServices();
        // surcharges
        btnSurcharges.setOnMouseClicked(e -> InsertSurcharges());
        btnSurchargesCancel.setOnMouseClicked(e -> CancelSurcharges());
        // optional services
        btnOptionalServices.setOnMouseClicked(e -> InsertOptionalservices());
        btnOptionalServicesCancel.setOnMouseClicked(e -> CancelOptionalservices());
        // declared item
        btnDeclaredItem.setOnMouseClicked(e -> InsertDeclaredItem());
        btnDeclaredItemCancel.setOnMouseClicked(e -> CancelDeclaredItem());
        // discount
        btnDiscount.setOnMouseClicked(e -> InsertDiscount());
        btnDiscountCancel.setOnMouseClicked(e -> CancelDiscount());
        // extra service
        btnExtraService.setOnMouseClicked(e -> InsertExtraService());
        btnExtraServiceCancel.setOnMouseClicked(e -> CancelExtraService());

    }

    public void generateDiscount() {
        colSub.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colDis.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    public void generateRate() {
        colItem.setCellValueFactory(new PropertyValueFactory<>("dec_items"));
        colPer.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        colQuan.setCellValueFactory(new PropertyValueFactory<>("quantity_desc"));
        colHWC.setCellValueFactory(new PropertyValueFactory<>("hwc"));
        colVG.setCellValueFactory(new PropertyValueFactory<>("vat_gst"));
    }

    public void generatePack() {
        colTray.setCellValueFactory(new PropertyValueFactory<>("tray"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colMinw.setCellValueFactory(new PropertyValueFactory<>("min_weight"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void generateSurcharges() {
        colSur.setCellValueFactory(new PropertyValueFactory<>("surcharges"));
        colShwc.setCellValueFactory(new PropertyValueFactory<>("hwc"));
        colSac.setCellValueFactory(new PropertyValueFactory<>("add_charge"));
    }

    public void generateOptServ() {
        colOpt.setCellValueFactory(new PropertyValueFactory<>("optional_service"));
        colOhwc.setCellValueFactory(new PropertyValueFactory<>("hwc"));
        colOac.setCellValueFactory(new PropertyValueFactory<>("add_charge"));
    }

    public void loadDiscount() {
        CORE2_discount dc = new CORE2_discount();
        ObservableList<TableModel_Discount> discount = FXCollections.observableArrayList();
        List b = dc.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("subject");
            hm.get("discount");

            discount.add(
                    new TableModel_Discount(
                            String.valueOf(hm.get("subject")),
                            String.valueOf(hm.get("discount"))
                    )
            );
        }
        tblDiscount.setItems(discount);
    }

    public void loadRate() {
        CORE2_itmrate ir = new CORE2_itmrate();
        ObservableList<TableModel_Rate> rate = FXCollections.observableArrayList();
        List b = ir.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("dec_items");
            hm.get("percentage");
            hm.get("quantity_desc");
            hm.get("hwc");
            hm.get("vat_gst");

            rate.add(
                    new TableModel_Rate(
                            String.valueOf(hm.get("dec_items")),
                            String.valueOf(hm.get("percentage")),
                            String.valueOf(hm.get("quantity_desc")),
                            String.valueOf(hm.get("hwc")),
                            String.valueOf(hm.get("vat_gst"))
                    )
            );
        }
        tblRate.setItems(rate);
    }

    public void loadPack() {
        CORE2_pack_requirements pr = new CORE2_pack_requirements();
        ObservableList<TableModel_Packrequirements> pack = FXCollections.observableArrayList();
        List b = pr.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("tray");
            hm.get("size");
            hm.get("min_weight");
            hm.get("amount");

            pack.add(
                    new TableModel_Packrequirements(
                            String.valueOf(hm.get("tray")),
                            String.valueOf(hm.get("size")),
                            String.valueOf(hm.get("min_weight")),
                            String.valueOf(hm.get("amount"))
                    )
            );
        }
        tblTray.setItems(pack);
    }

    public void loadSurcharges() {
        CORE2_surcharges sc = new CORE2_surcharges();
        ObservableList<TableModel_surcharges> sur = FXCollections.observableArrayList();
        List b = sc.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("surcharges");
            hm.get("hwc");
            hm.get("add_charge");

            sur.add(
                    new TableModel_surcharges(
                            String.valueOf(hm.get("surcharges")),
                            String.valueOf(hm.get("hwc")),
                            String.valueOf(hm.get("add_charge"))
                    )
            );
        }
        tblSur.setItems(sur);
    }

    public void loadOptServices() {
        CORE2_optional_service os = new CORE2_optional_service();
        ObservableList<TableModel_Oservices> optserc = FXCollections.observableArrayList();
        List b = os.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("optional_service");
            hm.get("hwc");
            hm.get("add_charge");

            optserc.add(
                    new TableModel_Oservices(
                            String.valueOf(hm.get("optional_service")),
                            String.valueOf(hm.get("hwc")),
                            String.valueOf(hm.get("add_charge"))
                    )
            );
        }
        tblOptional.setItems(optserc);
    }

    // INSERT QUERY para SURCHARGES
    public void InsertSurcharges() {
        CORE2_surcharges surcharges = new CORE2_surcharges();
        // ginawa ko to para mag popup yung else sa loob ng try

        try {
            String[][] surcharge = {
                {"surcharges", txtSS.getText()},
                {"hwc", txtSHWC.getText()},
                {"add_charge", txtSAC.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (surcharges.insert(surcharge)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtSS.setText("");
                txtSHWC.setText("");
                txtSAC.setText("");
            } else {
                Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadSurcharges();
    }

    public void CancelSurcharges() {
        txtSS.setText("");
        txtSHWC.setText("");
        txtSAC.setText("");
    }

    // INSERT QUERY para OPTIONAL SERVICES
    public void InsertOptionalservices() {
        CORE2_optional_service optional = new CORE2_optional_service();
        // ginawa ko to para mag popup yung else sa loob ng try

        try {
            String[][] option = {
                {"optional_service", txtOS.getText()},
                {"hwc", txtOSHWC.getText()},
                {"add_charge", txtOSAC.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (optional.insert(option)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtOS.setText("");
                txtOSHWC.setText("");
                txtOSAC.setText("");
            } else {
                Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadOptServices();
    }

    public void CancelOptionalservices() {
        txtOS.setText("");
        txtOSHWC.setText("");
        txtOSAC.setText("");
    }

    // INSERT QUERY para DECLARED ITEM
    public void InsertDeclaredItem() {
        CORE2_itmrate item = new CORE2_itmrate();
        // ginawa ko to para mag popup yung else sa loob ng try

        try {
            String[][] declared = {
                {"dec_items", txtDI.getText()},
                {"percentage", txtDIP.getText()},
                {"quantity_desc", txtDIQD.getText()},
                {"hwc", txtDIHWC.getText()},
                {"vat_gst", txtDIVAT.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (item.insert(declared)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtDI.setText("");
                txtDIP.setText("");
                txtDIQD.setText("");
                txtDIHWC.setText("");
                txtDIVAT.setText("");
            } else {
                Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadRate();
    }

    public void CancelDeclaredItem() {
        txtDI.setText("");
        txtDIP.setText("");
        txtDIQD.setText("");
        txtDIHWC.setText("");
        txtDIVAT.setText("");
    }

    // INSERT QUERY para DISCOUNT
    public void InsertDiscount() {
        CORE2_discount disc = new CORE2_discount();
        // ginawa ko to para mag popup yung else sa loob ng try

        try {
            String[][] dis = {
                {"subject", txtDS.getText()},
                {"discount", txtDP.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (disc.insert(dis)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtDS.setText("");
                txtDP.setText("");
            } else {
                Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadDiscount();
    }

    public void CancelDiscount() {
        txtDS.setText("");
        txtDP.setText("");
    }

    // INSERT QUERY para EXTRA SERVICES
    public void InsertExtraService() {
        CORE2_pack_requirements pr = new CORE2_pack_requirements();
        // ginawa ko to para mag popup yung else sa loob ng try

        try {
            String[][] dis = {
                {"tray", txtEST.getText()},
                {"size", txtESS.getText()},
                {"min_weight", txtESMW.getText()},
                {"amount", txtESA.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (pr.insert(dis)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                txtEST.setText("");
                txtESS.setText("");
                txtESMW.setText("");
                txtESA.setText("");
            } else {
                Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPack();
    }

    public void CancelExtraService() {
        txtEST.setText("");
        txtESS.setText("");
        txtESMW.setText("");
        txtESA.setText("");
    }

    @FXML
    private void viewRD(ActionEvent event) {
    }

    @FXML
    public void viewAS() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SRviewAirlineSchedule.fxml"));
        SRrootPane.getChildren().setAll(pane);
    }
}
