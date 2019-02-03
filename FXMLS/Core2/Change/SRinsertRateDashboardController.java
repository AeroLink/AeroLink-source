/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.TableModel_Discount;
import FXMLS.Core2.ClassFiles.TableModel_M;
import FXMLS.Core2.ClassFiles.TableModel_Oservices;
import FXMLS.Core2.ClassFiles.TableModel_Packrequirements;
import FXMLS.Core2.ClassFiles.TableModel_Rate;
import FXMLS.Core2.ClassFiles.TableModel_surcharges;
import FXMLS.Core2.ClassFiles.TableModel_NCR;
import FXMLS.Core2.ClassFiles.TableModel_NL;
import FXMLS.Core2.ClassFiles.TableModel_SL;
import FXMLS.Core2.ClassFiles.TableModel_V;
import FXMLS.Core2.ClassFiles.TableModel_I;
import FXMLS.Core2.ClassFiles.TableModel_ID;
import FXMLS.Core2.ClassFiles.TableModel_IND;
import Model.Core2.CORE2_SR_INDrates;
import Model.Core2.CORE2_SR_IDrates;
import Model.Core2.CORE2_SR_Irates;
import Model.Core2.CORE2_SR_Mrates;
import Model.Core2.CORE2_SR_NCRrates;
import Model.Core2.CORE2_SR_NLrates;
import Model.Core2.CORE2_SR_SLrates;
import Model.Core2.CORE2_SR_Vrates;
import Model.Core2.CORE2_discount;
import Model.Core2.CORE2_itmrate;
import Model.Core2.CORE2_optional_service;
import Model.Core2.CORE2_pack_requirements;
import Model.Core2.CORE2_surcharges;
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
import javafx.scene.control.Label;
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

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
 /* MODEL INSTANTIATE START */
//    CORE2_type type = new CORE2_type();
    /* MODEL INSTANTIATE END */

 /* FOR TABLE START */
    ObservableList<TableModel_Discount> dis = FXCollections.observableArrayList();
    ObservableList<TableModel_Rate> rat = FXCollections.observableArrayList();
    ObservableList<TableModel_Packrequirements> pac = FXCollections.observableArrayList();
    ObservableList<TableModel_surcharges> sur = FXCollections.observableArrayList();
    ObservableList<TableModel_Oservices> opt = FXCollections.observableArrayList();
    ObservableList<TableModel_NCR> ncr = FXCollections.observableArrayList();
    ObservableList<TableModel_SL> sl = FXCollections.observableArrayList();
    ObservableList<TableModel_NL> nl = FXCollections.observableArrayList();
    ObservableList<TableModel_V> v = FXCollections.observableArrayList();
    ObservableList<TableModel_M> m = FXCollections.observableArrayList();
    ObservableList<TableModel_I> i = FXCollections.observableArrayList();
    ObservableList<TableModel_ID> d = FXCollections.observableArrayList();
    ObservableList<TableModel_IND> nd = FXCollections.observableArrayList();
    /* FOR TABLE END */

    CORE2_SR_NCRrates ncrrt = new CORE2_SR_NCRrates();
    CORE2_SR_SLrates slrt = new CORE2_SR_SLrates();
    CORE2_SR_NLrates nlrt = new CORE2_SR_NLrates();
    CORE2_SR_Vrates vrt = new CORE2_SR_Vrates();
    CORE2_SR_Mrates mrt = new CORE2_SR_Mrates();
    CORE2_SR_Irates irt = new CORE2_SR_Irates();
    CORE2_SR_IDrates drt = new CORE2_SR_IDrates();
    CORE2_SR_INDrates ndrt = new CORE2_SR_INDrates();

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
    @FXML
    private TableView<TableModel_NCR> tblNCR;
    @FXML
    private TableView<TableModel_SL> tblSL;
    @FXML
    private TableView<TableModel_NL> tblNL;
    @FXML
    private TableView<TableModel_V> tblV;
    @FXML
    private TableView<TableModel_M> tblM;
    @FXML
    private TableView<TableModel_I> tblISLAND;
    @FXML
    private TableView<TableModel_ID> tblID;
    @FXML
    private TableView<TableModel_IND> tblIND;

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

        this.generateTableColumnNCR();
        this.populateTableNCR();
        // para sa generated tableColumn ng ncr rate
        ncr.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblNCR.setItems(ncr);
        });
        this.generateTableColumnSL();
        this.populateTableSL();
        // para sa generated tableColumn ng south luzon rate
        sl.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblSL.setItems(sl);
        });
        this.generateTableColumnNL();
        this.populateTableNL();
        // para sa generated tableColumn ng south luzon rate
        nl.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblNL.setItems(nl);
        });
        this.generateTableColumnV();
        this.populateTableV();
        // para sa generated tableColumn ng south luzon rate
        v.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblV.setItems(v);
        });
        this.generateTableColumnM();
        this.populateTableM();
        // para sa generated tableColumn ng south luzon rate
        m.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblM.setItems(m);
        });
        this.generateTableColumnI();
        this.populateTableI();
        // para sa generated tableColumn ng south luzon rate
        i.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblISLAND.setItems(i);
        });
        this.generateTableColumnID();
        this.populateTableID();
        // para sa generated tableColumn ng south luzon rate
        d.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblID.setItems(d);
        });
        this.generateTableColumnIND();
        this.populateTableIND();
        // para sa generated tableColumn ng south luzon rate
        nd.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblIND.setItems(nd);
        });
//        this.generateNCR();
//        this.loadNCR();
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

    public void viewAS() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SRviewAirlineSchedule.fxml"));
        SRrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewSDM(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ScheduleRates.fxml"));
        SRrootPane.getChildren().setAll(pane);
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnNCR() {
        tblNCR.getItems().clear();
        tblNCR.getColumns().removeAll(tblNCR.getColumns());

        TableColumn<TableModel_NCR, String> TCorigin = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_NCR, String> APOUCHregular = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_NCR, String> APOUCHxl = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_NCR, String> APOUCHss = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_NCR, String> APOUCHa = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_NCR, String> APACKsmall = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_NCR, String> APACKlarge = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_NCR, String> KILOBOXmini = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_NCR, String> KILOBOXsmall = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_NCR, String> KILOBOXslim = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_NCR, String> KILOBOXmedium = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_NCR, String> KILOBOXlarge = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_NCR, String> KILOBOXxl = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_NCR, String> KILOBOXd1 = new TableColumn<>("D1(PER KG RATE)");

        TCorigin.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().originNCR);
        APOUCHregular.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APHregular);
        APOUCHxl.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APHxl);
        APOUCHss.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APHss);
        APOUCHa.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APHa);
        APACKsmall.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APksmall);
        APACKlarge.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().APKlarge);
        KILOBOXmini.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBmini);
        KILOBOXsmall.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBsmall);
        KILOBOXslim.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBslim);
        KILOBOXmedium.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBmedium);
        KILOBOXlarge.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBlarge);
        KILOBOXxl.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBxl);
        KILOBOXd1.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NCR, String> param) -> param.getValue().KBd1);
        tblNCR.getColumns().addAll(TCorigin, APOUCHregular, APOUCHxl,
                APOUCHss, APOUCHa, APACKsmall, APACKlarge,
                KILOBOXmini, KILOBOXsmall, KILOBOXslim, KILOBOXmedium,
                KILOBOXlarge, KILOBOXxl, KILOBOXd1);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableNCR() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = ncrrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = ncrrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableNCR(rs);
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
    public void AddTableNCR(List rs) {
        ncr.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originNCR = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregular = (String) drow.get("aero_pouch_regular");
            String APHxl = (String) drow.get("aero_pouch_xl");
            String APHss = (String) drow.get("aero_pouch_ss");
            String APHa = (String) drow.get("a_pouch");
            String APksmall = (String) drow.get("aero_pack_small");
            String APKlarge = (String) drow.get("aero_pack_large");
            String KBmini = (String) drow.get("kilobox_mini");
            String KBsmall = (String) drow.get("kilobox_small");
            String KBslim = (String) drow.get("kilobox_slim");
            String KBmedium = (String) drow.get("kilobox_medium");
            String KBlarge = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxl = (String) drow.get("kilobox_xl");
            String KBd1 = (String) drow.get("d1");

            ncr.add(new TableModel_NCR(originNCR, APHregular, APHxl, APHss, APHa, APksmall, APKlarge,
                    KBmini, KBsmall, KBslim, KBmedium, KBlarge, KBxl, KBd1));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnSL() {
        tblSL.getItems().clear();
        tblSL.getColumns().removeAll(tblSL.getColumns());

        TableColumn<TableModel_SL, String> TCoriginSL = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_SL, String> APOUCHregularSL = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_SL, String> APOUCHxlSL = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_SL, String> APOUCHssSL = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_SL, String> APOUCHaSL = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_SL, String> APACKsmallSL = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_SL, String> APACKlargeSL = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_SL, String> KILOBOXminiSL = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_SL, String> KILOBOXsmallSL = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_SL, String> KILOBOXslimSL = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_SL, String> KILOBOXmediumSL = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_SL, String> KILOBOXlargeSL = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_SL, String> KILOBOXxlSL = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_SL, String> KILOBOXd1SL = new TableColumn<>("D1(PER KG RATE)");

        TCoriginSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().originSL);
        APOUCHregularSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APHregularSL);
        APOUCHxlSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APHxlSL);
        APOUCHssSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APHssSL);
        APOUCHaSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APHaSL);
        APACKsmallSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APksmallSL);
        APACKlargeSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().APKlargeSL);
        KILOBOXminiSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBminiSL);
        KILOBOXsmallSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBsmallSL);
        KILOBOXslimSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBslimSL);
        KILOBOXmediumSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBmediumSL);
        KILOBOXlargeSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBlargeSL);
        KILOBOXxlSL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBxlSL);
        KILOBOXd1SL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_SL, String> param) -> param.getValue().KBd1SL);
        tblSL.getColumns().addAll(TCoriginSL, APOUCHregularSL, APOUCHxlSL,
                APOUCHssSL, APOUCHaSL, APACKsmallSL, APACKlargeSL,
                KILOBOXminiSL, KILOBOXsmallSL, KILOBOXslimSL, KILOBOXmediumSL,
                KILOBOXlargeSL, KILOBOXxlSL, KILOBOXd1SL);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableSL() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = slrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = slrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableSL(rs);
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
    public void AddTableSL(List rs) {
        sl.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originSL = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregularSL = (String) drow.get("aero_pouch_regular");
            String APHxlSL = (String) drow.get("aero_pouch_xl");
            String APHssSL = (String) drow.get("aero_pouch_ss");
            String APHaSL = (String) drow.get("a_pouch");
            String APksmallSL = (String) drow.get("aero_pack_small");
            String APKlargeSL = (String) drow.get("aero_pack_large");
            String KBminiSL = (String) drow.get("kilobox_mini");
            String KBsmallSL = (String) drow.get("kilobox_small");
            String KBslimSL = (String) drow.get("kilobox_slim");
            String KBmediumSL = (String) drow.get("kilobox_medium");
            String KBlargeSL = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxlSL = (String) drow.get("kilobox_xl");
            String KBd1SL = (String) drow.get("d1");

            sl.add(new TableModel_SL(originSL, APHregularSL, APHxlSL, APHssSL, APHaSL,
                    APksmallSL, APKlargeSL, KBminiSL, KBsmallSL,
                    KBslimSL, KBmediumSL, KBlargeSL, KBxlSL, KBd1SL));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnNL() {
        tblNL.getItems().clear();
        tblNL.getColumns().removeAll(tblNL.getColumns());

        TableColumn<TableModel_NL, String> TCoriginNL = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_NL, String> APOUCHregularNL = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_NL, String> APOUCHxlNL = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_NL, String> APOUCHssNL = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_NL, String> APOUCHaNL = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_NL, String> APACKsmallNL = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_NL, String> APACKlargeNL = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_NL, String> KILOBOXminiNL = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_NL, String> KILOBOXsmallNL = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_NL, String> KILOBOXslimNL = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_NL, String> KILOBOXmediumNL = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_NL, String> KILOBOXlargeNL = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_NL, String> KILOBOXxlNL = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_NL, String> KILOBOXd1NL = new TableColumn<>("D1(PER KG RATE)");

        TCoriginNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().originNL);
        APOUCHregularNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APHregularNL);
        APOUCHxlNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APHxlNL);
        APOUCHssNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APHssNL);
        APOUCHaNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APHaNL);
        APACKsmallNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APksmallNL);
        APACKlargeNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().APKlargeNL);
        KILOBOXminiNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBminiNL);
        KILOBOXsmallNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBsmallNL);
        KILOBOXslimNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBslimNL);
        KILOBOXmediumNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBmediumNL);
        KILOBOXlargeNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBlargeNL);
        KILOBOXxlNL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBxlNL);
        KILOBOXd1NL.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_NL, String> param) -> param.getValue().KBd1NL);
        tblNL.getColumns().addAll(TCoriginNL, APOUCHregularNL, APOUCHxlNL,
                APOUCHssNL, APOUCHaNL, APACKsmallNL, APACKlargeNL,
                KILOBOXminiNL, KILOBOXsmallNL, KILOBOXslimNL, KILOBOXmediumNL,
                KILOBOXlargeNL, KILOBOXxlNL, KILOBOXd1NL);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableNL() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = nlrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = nlrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableNL(rs);
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
    public void AddTableNL(List rs) {
        nl.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originSL = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregularSL = (String) drow.get("aero_pouch_regular");
            String APHxlSL = (String) drow.get("aero_pouch_xl");
            String APHssSL = (String) drow.get("aero_pouch_ss");
            String APHaSL = (String) drow.get("a_pouch");
            String APksmallSL = (String) drow.get("aero_pack_small");
            String APKlargeSL = (String) drow.get("aero_pack_large");
            String KBminiSL = (String) drow.get("kilobox_mini");
            String KBsmallSL = (String) drow.get("kilobox_small");
            String KBslimSL = (String) drow.get("kilobox_slim");
            String KBmediumSL = (String) drow.get("kilobox_medium");
            String KBlargeSL = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxlSL = (String) drow.get("kilobox_xl");
            String KBd1SL = (String) drow.get("d1");

            nl.add(new TableModel_NL(originSL, APHregularSL, APHxlSL, APHssSL, APHaSL,
                    APksmallSL, APKlargeSL, KBminiSL, KBsmallSL,
                    KBslimSL, KBmediumSL, KBlargeSL, KBxlSL, KBd1SL));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnV() {
        tblV.getItems().clear();
        tblV.getColumns().removeAll(tblV.getColumns());

        TableColumn<TableModel_V, String> TCoriginV = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_V, String> APOUCHregularV = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_V, String> APOUCHxlV = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_V, String> APOUCHssV = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_V, String> APOUCHaV = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_V, String> APACKsmallV = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_V, String> APACKlargeV = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_V, String> KILOBOXminiV = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_V, String> KILOBOXsmallV = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_V, String> KILOBOXslimV = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_V, String> KILOBOXmediumV = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_V, String> KILOBOXlargeV = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_V, String> KILOBOXxlV = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_V, String> KILOBOXd1V = new TableColumn<>("D1(PER KG RATE)");

        TCoriginV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().originV);
        APOUCHregularV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APHregularV);
        APOUCHxlV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APHxlV);
        APOUCHssV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APHssV);
        APOUCHaV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APHaV);
        APACKsmallV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APksmallV);
        APACKlargeV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().APKlargeV);
        KILOBOXminiV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBminiV);
        KILOBOXsmallV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBsmallV);
        KILOBOXslimV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBslimV);
        KILOBOXmediumV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBmediumV);
        KILOBOXlargeV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBlargeV);
        KILOBOXxlV.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBxlV);
        KILOBOXd1V.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_V, String> param) -> param.getValue().KBd1V);
        tblV.getColumns().addAll(TCoriginV, APOUCHregularV, APOUCHxlV,
                APOUCHssV, APOUCHaV, APACKsmallV, APACKlargeV,
                KILOBOXminiV, KILOBOXsmallV, KILOBOXslimV, KILOBOXmediumV,
                KILOBOXlargeV, KILOBOXxlV, KILOBOXd1V);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableV() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = vrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = vrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableV(rs);
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
    public void AddTableV(List rs) {
        v.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originV = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregularV = (String) drow.get("aero_pouch_regular");
            String APHxlV = (String) drow.get("aero_pouch_xl");
            String APHssV = (String) drow.get("aero_pouch_ss");
            String APHaV = (String) drow.get("a_pouch");
            String APksmallV = (String) drow.get("aero_pack_small");
            String APKlargeV = (String) drow.get("aero_pack_large");
            String KBminiV = (String) drow.get("kilobox_mini");
            String KBsmallV = (String) drow.get("kilobox_small");
            String KBslimV = (String) drow.get("kilobox_slim");
            String KBmediumV = (String) drow.get("kilobox_medium");
            String KBlargeV = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxlV = (String) drow.get("kilobox_xl");
            String KBd1V = (String) drow.get("d1");

            v.add(new TableModel_V(originV, APHregularV, APHxlV, APHssV, APHaV,
                    APksmallV, APKlargeV, KBminiV, KBsmallV,
                    KBslimV, KBmediumV, KBlargeV, KBxlV, KBd1V));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnM() {
        tblM.getItems().clear();
        tblM.getColumns().removeAll(tblM.getColumns());

        TableColumn<TableModel_M, String> TCoriginM = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_M, String> APOUCHregularM = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_M, String> APOUCHxlM = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_M, String> APOUCHssM = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_M, String> APOUCHaM = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_M, String> APACKsmallM = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_M, String> APACKlargeM = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_M, String> KILOBOXminiM = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_M, String> KILOBOXsmallM = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_M, String> KILOBOXslimM = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_M, String> KILOBOXmediumM = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_M, String> KILOBOXlargeM = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_M, String> KILOBOXxlM = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_M, String> KILOBOXd1M = new TableColumn<>("D1(PER KG RATE)");

        TCoriginM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().originM);
        APOUCHregularM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APHregularM);
        APOUCHxlM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APHxlM);
        APOUCHssM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APHssM);
        APOUCHaM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APHaM);
        APACKsmallM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APksmallM);
        APACKlargeM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().APKlargeM);
        KILOBOXminiM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBminiM);
        KILOBOXsmallM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBsmallM);
        KILOBOXslimM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBslimM);
        KILOBOXmediumM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBmediumM);
        KILOBOXlargeM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBlargeM);
        KILOBOXxlM.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBxlM);
        KILOBOXd1M.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_M, String> param) -> param.getValue().KBd1M);
        tblM.getColumns().addAll(TCoriginM, APOUCHregularM, APOUCHxlM,
                APOUCHssM, APOUCHaM, APACKsmallM, APACKlargeM,
                KILOBOXminiM, KILOBOXsmallM, KILOBOXslimM, KILOBOXmediumM,
                KILOBOXlargeM, KILOBOXxlM, KILOBOXd1M);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableM() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = mrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = mrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableM(rs);
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
    public void AddTableM(List rs) {
        m.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originM = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregularM = (String) drow.get("aero_pouch_regular");
            String APHxlM = (String) drow.get("aero_pouch_xl");
            String APHssM = (String) drow.get("aero_pouch_ss");
            String APHaM = (String) drow.get("a_pouch");
            String APksmallM = (String) drow.get("aero_pack_small");
            String APKlargeM = (String) drow.get("aero_pack_large");
            String KBminiM = (String) drow.get("kilobox_mini");
            String KBsmallM = (String) drow.get("kilobox_small");
            String KBslimM = (String) drow.get("kilobox_slim");
            String KBmediumM = (String) drow.get("kilobox_medium");
            String KBlargeM = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxlM = (String) drow.get("kilobox_xl");
            String KBd1M = (String) drow.get("d1");

            m.add(new TableModel_M(originM, APHregularM, APHxlM, APHssM, APHaM,
                    APksmallM, APKlargeM, KBminiM, KBsmallM,
                    KBslimM, KBmediumM, KBlargeM, KBxlM, KBd1M));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnI() {
        tblISLAND.getItems().clear();
        tblISLAND.getColumns().removeAll(tblISLAND.getColumns());

        TableColumn<TableModel_I, String> TCoriginI = new TableColumn<>("ORIGIN");
        TableColumn<TableModel_I, String> APOUCHregularI = new TableColumn<>("AERO-POUCH REGULAR");
        TableColumn<TableModel_I, String> APOUCHxlI = new TableColumn<>("AERO-POUCH XL");
        TableColumn<TableModel_I, String> APOUCHssI = new TableColumn<>("AERO-POUCH SS");
        TableColumn<TableModel_I, String> APOUCHaI = new TableColumn<>("A-POUCH");
        TableColumn<TableModel_I, String> APACKsmallI = new TableColumn<>("AERO-PACK SMALL");
        TableColumn<TableModel_I, String> APACKlargeI = new TableColumn<>("AERO-PACK LARGE");
        TableColumn<TableModel_I, String> KILOBOXminiI = new TableColumn<>("KILOBOX MINI");
        TableColumn<TableModel_I, String> KILOBOXsmallI = new TableColumn<>("KILOBOX SMALL");
        TableColumn<TableModel_I, String> KILOBOXslimI = new TableColumn<>("KILOBOX SLIM");
        TableColumn<TableModel_I, String> KILOBOXmediumI = new TableColumn<>("KILOBOX MEDIUM");
        TableColumn<TableModel_I, String> KILOBOXlargeI = new TableColumn<>("KILOBOX LARGE");
        TableColumn<TableModel_I, String> KILOBOXxlI = new TableColumn<>("KILOBOX XL");
        TableColumn<TableModel_I, String> KILOBOXd1I = new TableColumn<>("D1(PER KG RATE)");

        TCoriginI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().originI);
        APOUCHregularI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APHregularI);
        APOUCHxlI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APHxlI);
        APOUCHssI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APHssI);
        APOUCHaI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APHaI);
        APACKsmallI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APksmallI);
        APACKlargeI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().APKlargeI);
        KILOBOXminiI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBminiI);
        KILOBOXsmallI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBsmallI);
        KILOBOXslimI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBslimI);
        KILOBOXmediumI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBmediumI);
        KILOBOXlargeI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBlargeI);
        KILOBOXxlI.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBxlI);
        KILOBOXd1I.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_I, String> param) -> param.getValue().KBd1I);
        tblISLAND.getColumns().addAll(TCoriginI, APOUCHregularI, APOUCHxlI,
                APOUCHssI, APOUCHaI, APACKsmallI, APACKlargeI,
                KILOBOXminiI, KILOBOXsmallI, KILOBOXslimI, KILOBOXmediumI,
                KILOBOXlargeI, KILOBOXxlI, KILOBOXd1I);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableI() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = irt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = irt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("destination", "aero_pouch_regular", "aero_pouch_xl", "aero_pouch_ss", "a_pouch",
                                            "aero_pack_small", "aero_pack_large", "kilobox_mini", "kilobox_small", "kilobox_slim",
                                            "kilobox_medium", "kilobox_large", "kilobox_xl", "d1");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableI(rs);
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
    public void AddTableI(List rs) {
        i.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String originI = String.valueOf(drow.get("destination")); // dapat ganto kapag primary key
            String APHregularI = (String) drow.get("aero_pouch_regular");
            String APHxlI = (String) drow.get("aero_pouch_xl");
            String APHssI = (String) drow.get("aero_pouch_ss");
            String APHaI = (String) drow.get("a_pouch");
            String APksmallI = (String) drow.get("aero_pack_small");
            String APKlargeI = (String) drow.get("aero_pack_large");
            String KBminiI = (String) drow.get("kilobox_mini");
            String KBsmallI = (String) drow.get("kilobox_small");
            String KBslimI = (String) drow.get("kilobox_slim");
            String KBmediumI = (String) drow.get("kilobox_medium");
            String KBlargeI = (String) drow.get("kilobox_large"); // dapat ganto kapag primary key
            String KBxlI = (String) drow.get("kilobox_xl");
            String KBd1I = (String) drow.get("d1");

            i.add(new TableModel_I(originI, APHregularI, APHxlI, APHssI, APHaI,
                    APksmallI, APKlargeI, KBminiI, KBsmallI,
                    KBslimI, KBmediumI, KBlargeI, KBxlI, KBd1I));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnID() {
        tblID.getItems().clear();
        tblID.getColumns().removeAll(tblID.getColumns());

        TableColumn<TableModel_ID, String> drate = new TableColumn<>("DOCUMENT");
        TableColumn<TableModel_ID, String> dcountry = new TableColumn<>("SINGAPORE");

        drate.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_ID, String> param) -> param.getValue().drate);
        dcountry.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_ID, String> param) -> param.getValue().dcountry);
        tblID.getColumns().addAll(drate, dcountry);

    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableID() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = drt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = drt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("document_rate", "singapore");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableID(rs);
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
    public void AddTableID(List rs) {
        d.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String drate = String.valueOf(drow.get("document_rate")); // dapat ganto kapag primary key
            String dcountry = (String) drow.get("singapore");

            d.add(new TableModel_ID(drate, dcountry));
        }
    }

    // realtime update via inserting data to modal
    // ito yung mga tablecolumn kasi wala akung nilagay sa tableview dun sa scene
    public void generateTableColumnIND() {
        tblIND.getItems().clear();
        tblIND.getColumns().removeAll(tblIND.getColumns());

        TableColumn<TableModel_IND, String> ndrate = new TableColumn<>("NON-DOCUMENT");
        TableColumn<TableModel_IND, String> ndcountry = new TableColumn<>("SINGAPORE");

        ndrate.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_IND, String> param) -> param.getValue().ndrate);
        ndcountry.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_IND, String> param) -> param.getValue().ndcountry);
        tblIND.getColumns().addAll(ndrate, ndcountry);

    }

    // PARA SA PAG GAWA NG TOTAL VIA TABLEVIEW
    // NOTE: DAPAT NAKA INT YUNG DATA TYPE NG TABLE COLUMN MO SA DATABASE
//    double total = 0;
//    public void total() {
//
//        for (int i = 0; i < tblIND.getItems().size(); i++) {
//            int amount = Integer.parseInt(tblIND.getItems().get(i).ndcountry.getValue());
//            total = total + amount;
//        }
//        totalView.setText(String.valueOf(total));
//        tblIND.setItems(nd);
//    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableIND() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = ndrt.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = ndrt
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
                                    .get("non_document_rate", "singapore");
//                            AddTableIND(rs);
//                            total();
//                            {
//
//                            }
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableIND(rs);
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
    public void AddTableIND(List rs) {
        nd.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String ndrate = String.valueOf(drow.get("non_document_rate")); // dapat ganto kapag primary key
            String ndcountry = (String) drow.get("singapore");

            nd.add(new TableModel_IND(ndrate, ndcountry));
        }
    }
}
