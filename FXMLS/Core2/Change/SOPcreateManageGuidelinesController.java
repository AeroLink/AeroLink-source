/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.SOPTable_guidelines;
import FXMLS.Core2.ClassFiles.SOPTable_package_sorting;
import FXMLS.Core2.ClassFiles.SOPTable_policy;
import FXMLS.Core2.ClassFiles.SOPTable_terms_condition;
import Model.Core2.CORE2_guidelines_management;
import Model.Core2.CORE2_TermsCondition;
import Model.Core2.CORE2_PackageSorting;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class SOPcreateManageGuidelinesController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */

    CORE2_guidelines_management gm = new CORE2_guidelines_management();
    CORE2_guidelines_management tc = new CORE2_guidelines_management("terms-condition");
    CORE2_guidelines_management ps = new CORE2_guidelines_management("package-sorting");

    CORE2_TermsCondition TC = new CORE2_TermsCondition();
    CORE2_PackageSorting PS = new CORE2_PackageSorting();

    ObservableList<SOPTable_guidelines> OLg = FXCollections.observableArrayList();
    ObservableList<SOPTable_terms_condition> OLtc = FXCollections.observableArrayList();
    ObservableList<SOPTable_package_sorting> OLps = FXCollections.observableArrayList();

    @FXML
    private AnchorPane SOProotPane;
    @FXML
    private TableView<SOPTable_guidelines> tblGuidelines;
    @FXML
    private TableView<SOPTable_terms_condition> tblTAC;
    @FXML
    private TableView<SOPTable_package_sorting> tblPS;
    @FXML
    private JFXButton gpbtn;
    @FXML
    private TextArea TAgp;
    @FXML
    private TextArea TAtc;
    @FXML
    private JFXButton tcbtn;
    @FXML
    private TextArea TAps;
    @FXML
    private JFXButton psbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OLg.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblGuidelines.setItems(OLg);
        });
        OLtc.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblTAC.setItems(OLtc);
        });
        OLps.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblPS.setItems(OLps);
        });

        this.GenerateGL();
        this.GenerateTAC();
        this.GeneratePS();
        this.populateTableGL();
        this.populateTableTAC();
        this.populateTablePS();

        gpbtn.setOnMouseClicked(e -> Insert1());
        tcbtn.setOnMouseClicked(e -> Insert2());
        psbtn.setOnMouseClicked(e -> Insert3());
    }

    public void GenerateGL() {
        tblGuidelines.getItems().clear();
        tblGuidelines.getColumns().removeAll(tblGuidelines.getColumns());

        TableColumn<SOPTable_guidelines, String> description = new TableColumn<>("GUIDELINES & POLICY");
        description.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_guidelines, String> param) -> param.getValue().description);
        tblGuidelines.getColumns().addAll(description);
    }

    public void GenerateTAC() {
        tblTAC.getItems().clear();
        tblTAC.getColumns().removeAll(tblTAC.getColumns());

        TableColumn<SOPTable_terms_condition, String> description = new TableColumn<>("TERMS & CONDITION");
        description.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_terms_condition, String> param) -> param.getValue().description);
        tblTAC.getColumns().addAll(description);
    }

    public void GeneratePS() {
        tblPS.getItems().clear();
        tblPS.getColumns().removeAll(tblPS.getColumns());

        TableColumn<SOPTable_package_sorting, String> description = new TableColumn<>("PACKAGE SORTING");
        description.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_sorting, String> param) -> param.getValue().description);
        tblPS.getColumns().addAll(description);
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableGL() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = gm.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = gm
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id") 
                                    // yung nasa baba nito ayan yung query na select baka malito kayo eh 
                                    .get("description");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableGL(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }
    // dito isinasagawa yung pag query and etc or inner|left join

    public void populateTableTAC() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = tc.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = tc
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id") 
                                    // yung nasa baba nito ayan yung query na select baka malito kayo eh 
                                    .get("description");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableTAC(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }
    // dito isinasagawa yung pag query and etc or inner|left join

    public void populateTablePS() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = ps.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = ps
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id") 
                                    // yung nasa baba nito ayan yung query na select baka malito kayo eh 
                                    .get("description");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTablePS(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }
    // dito isinasagawa yung pag query and etc or inner|left join

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTableGL(List rs) {
        OLg.clear();

        for (Object row : rs) {
            HashMap grow = (HashMap) row;

            String description = (String) grow.get("description");

            OLg.add(new SOPTable_guidelines(description));
        }
    }

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTableTAC(List rs) {
        OLtc.clear();

        for (Object row : rs) {
            HashMap trow = (HashMap) row;

            String description = (String) trow.get("description");

            OLtc.add(new SOPTable_terms_condition(description));
        }
    }

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTablePS(List rs) {
        OLps.clear();

        for (Object row : rs) {
            HashMap psrow = (HashMap) row;

            String description = (String) psrow.get("description");

            OLps.add(new SOPTable_package_sorting(description));
        }
    }
    // need sya para lumabas yung result sa tableview ez LAWGIC

    // INSERT QUERY para sa mga textfield etc. ganon
    public void Insert1() {
        CORE2_guidelines_management gm = new CORE2_guidelines_management();
        // ginawa ko to para mag popup yung else sa loob ng try
        String TGP = TAgp.getText();

        try {
            String[][] sop_data1 = {
                {"description", TAgp.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (gm.insert(sop_data1)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                TAgp.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                if ((TGP.isEmpty())) {
                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateTableGL();
    }

    // INSERT QUERY para sa mga textfield etc. ganon
    public void Insert2() {
        CORE2_TermsCondition TC = new CORE2_TermsCondition();
        // ginawa ko to para mag popup yung else sa loob ng try
        String TTC = TAtc.getText();

        try {
            String[][] sop_data2 = {
                {"description", TAtc.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (TC.insert(sop_data2)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                TAtc.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                if ((TTC.isEmpty())) {
                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateTableTAC();
    }

    // INSERT QUERY para sa mga textfield etc. ganon
    public void Insert3() {
        CORE2_PackageSorting PS = new CORE2_PackageSorting();
        // ginawa ko to para mag popup yung else sa loob ng try
        String TPS = TAps.getText();

        try {
            String[][] sop_data3 = {
                {"description", TAps.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (PS.insert(sop_data3)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                TAps.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                if ((TPS.isEmpty())) {
                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        populateTablePS();
    }

    @FXML
    private void packInspect(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewPackageInspection.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void manageguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPcreateManageGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void prohibited(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewProhibited.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void originalPI(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/StandardOperationalProcedure.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

}
