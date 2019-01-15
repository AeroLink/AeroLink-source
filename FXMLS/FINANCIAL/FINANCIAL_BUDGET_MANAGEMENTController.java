/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CALLER.BUDGET_MORE_MODALController;
import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_BM_ALLOCATION;
import FXMLS.FINANCIAL.CLASSFILES.FINANCIAL_BR_CLASSFILE;
import Model.Financial.Financial_allocation_model;
import Model.Financial.Financial_br_model;
import Model.Financial.Financial_budget_request;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_BUDGET_MANAGEMENTController implements Initializable {

    private boolean forViewing = false;

    @FXML
    private TableView<FINANCIAL_BM_ALLOCATION> allocation_tbl;
    @FXML
    private JFXButton allocation_btn;
    @FXML
    private TableView<FINANCIAL_BR_CLASSFILE> tbl_bpr;
    @FXML
    private JFXComboBox<String> cmbobx_stats;

    ObservableList<String> voucher = FXCollections.observableArrayList("Pending", "Approved");
    @FXML
    private JFXButton view_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        view_btn.setOnMouseClicked(e -> ViewData());
        cmbobx_stats.setItems(voucher);

        allocation_btn.setOnMouseClicked(e -> OpenAllocation());

        TableBR();
        ShowDataBR();

        displayAlloc();
        loadAlloc();

        tbl_bpr.setOnMouseClicked(e -> {
            FINANCIAL_BR_CLASSFILE cc = tbl_bpr.getSelectionModel().getSelectedItem();
            view_btn.setDisable(false);

        });
    }

    private void searchStatus(List requestor) {

        //tbl_bpr.getItems().clear();

        ObservableList<FINANCIAL_BR_CLASSFILE> brc = FXCollections.observableArrayList();

        try {
            for (Object d : requestor) {
                HashMap hm = (HashMap) d;   //exquisite casting
                hm.get("budget_id");
                hm.get("budget_no");
                hm.get("budget_date_request");
                hm.get("budget_department");
                hm.get("budget_requestor");
                hm.get("budget_description");
                hm.get("budget_priority_lvl");
                hm.get("budget_amount");
                hm.get("budget_status");

                brc.add(new FINANCIAL_BR_CLASSFILE(
                        String.valueOf(hm.get("budget_id")),
                        String.valueOf(hm.get("budget_no")),
                        String.valueOf(hm.get("budget_date_request")),
                        String.valueOf(hm.get("budget_department")),
                        String.valueOf(hm.get("budget_requestor")),
                        String.valueOf(hm.get("budget_description")),
                        String.valueOf(hm.get("budget_priority_lvl")),
                        String.valueOf(hm.get("budget_amount")),
                        String.valueOf(hm.get("budget_status"))
                ));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_bpr.setItems(brc);

    }

    //Budget Request Module
    public void TableBR() {
        tbl_bpr.getItems().clear();
        tbl_bpr.getColumns().removeAll(tbl_bpr.getColumns());

        TableColumn<FINANCIAL_BR_CLASSFILE, String> dt = new TableColumn<>("Date Request");
        TableColumn<FINANCIAL_BR_CLASSFILE, String> dept = new TableColumn<>("Department");
        //TableColumn<FINANCIAL_BR_CLASSFILE, String> req = new TableColumn<>("Requestor");
        //TableColumn<FINANCIAL_BR_CLASSFILE, String> des = new TableColumn<>("Description");
        //TableColumn<FINANCIAL_BR_CLASSFILE, String> pl = new TableColumn<>("Priority Level");
        TableColumn<FINANCIAL_BR_CLASSFILE, String> tprce = new TableColumn<>("Amount");
        TableColumn<FINANCIAL_BR_CLASSFILE, String> st = new TableColumn<>("Status");
        // TableColumn More = new TableColumn<>("More Options");

        dt.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().dateRequest);
        dept.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().department);
        //req.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().requestor);
        // des.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().description);
        // pl.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().prioritylvl);
        tprce.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().amount);
        st.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, String> param) -> param.getValue().status);

        /* More.setSortable(false);

        More.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<FINANCIAL_BR_CLASSFILE, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });

        More.setCellFactory(new Callback<TableColumn<FINANCIAL_BR_CLASSFILE, Boolean>, TableCell<FINANCIAL_BR_CLASSFILE, Boolean>>() {
            @Override
            public TableCell<FINANCIAL_BR_CLASSFILE, Boolean> call(TableColumn<FINANCIAL_BR_CLASSFILE, Boolean> param) {
                return new Synapse.Components.ButtonInCell<FINANCIAL_BR_CLASSFILE>().create("More", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.err.println("Modal Has Been Open");
                        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_MORE_MODAL.fxml").getParent());
                        md.open();
                        
                     
                        
                    }
                });
            }
        });
         */
        tbl_bpr.getColumns().addAll(dt, dept, tprce, st);

    }

    long DummyCount = 0;
    long GlobalCount = 0;
    Financial_budget_request ba = new Financial_budget_request();
    ObservableList<FINANCIAL_BR_CLASSFILE> bamod = FXCollections.observableArrayList();

    public void ShowDataBR() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_budgetm")) {
                ba.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                           bamod.clear();
                    List b = ba.get();

                    for (Object d : b) {
                        HashMap hm = (HashMap) d;   //exquisite casting
                        hm.get("budget_id");
                        hm.get("budget_no");
                        hm.get("budget_date_request");
                        hm.get("budget_department");
                        hm.get("budget_requestor");
                        hm.get("budget_description");
                        hm.get("budget_priority_lvl");
                        hm.get("budget_amount");
                        hm.get("budget_status");

                        bamod.add(new FINANCIAL_BR_CLASSFILE(
                                String.valueOf(hm.get("budget_id")),
                                String.valueOf(hm.get("budget_no")),
                                String.valueOf(hm.get("budget_date_request")),
                                String.valueOf(hm.get("budget_department")),
                                String.valueOf(hm.get("budget_requestor")),
                                String.valueOf(hm.get("budget_description")),
                                String.valueOf(hm.get("budget_priority_lvl")),
                                String.valueOf(hm.get("budget_amount")),
                                String.valueOf(hm.get("budget_status"))
                        ));

                    }
                    tbl_bpr.setItems(bamod);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }
/*
    public void AddJobToTable(List rs) {
        tbl_bpr.refresh();
        bamod.clear();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;

            String bi = String.valueOf(crow.get("budget_id"));
            String bn = (String) crow.get("budget_no");
            String bdr = (String) crow.get("budget_date_request");
            String bd = (String) crow.get("budget_department");
            String br = (String) crow.get("budget_requestor");
            String bde = (String) crow.get("budget_description");
            String bp = (String) crow.get("budget_priority_lvl");
            String ba = (String) crow.get("budget_amount");
            String bs = (String) crow.get("budget_status");

            bamod.add(new FINANCIAL_BR_CLASSFILE(bi, bn, bdr, bd, br, bde, bp, ba, bs));
        }
        tbl_bpr.setItems(bamod);
    }*/

    public void ViewData() {
        FINANCIAL_BR_CLASSFILE fbrc = tbl_bpr.getSelectionModel().getSelectedItem();
        if (fbrc == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Caution!");
            alert.setContentText("You must click from table first");
            alert.showAndWait();
        } else {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/FINANCIAL/CALLER/BUDGET_MORE_MODAL.fxml"));
                Parent parent = loader.load();

                BUDGET_MORE_MODALController controller = (BUDGET_MORE_MODALController) loader.getController();
                controller.DisplayData(fbrc);

                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("View");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Budget Plan Module
    public void displayAlloc() {

        allocation_tbl.getItems().clear();
        allocation_tbl.getColumns().removeAll(allocation_tbl.getColumns());

        TableColumn<FINANCIAL_BM_ALLOCATION, String> d = new TableColumn<>("Department");
        TableColumn<FINANCIAL_BM_ALLOCATION, String> a = new TableColumn<>("Amount");

        d.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BM_ALLOCATION, String> param) -> param.getValue().department);
        a.setCellValueFactory((TableColumn.CellDataFeatures<FINANCIAL_BM_ALLOCATION, String> param) -> param.getValue().amount);

        allocation_tbl.getColumns().addAll(d, a);
    }

    Financial_allocation_model fba = new Financial_allocation_model();
    ObservableList<FINANCIAL_BM_ALLOCATION> allc = FXCollections.observableArrayList();

    public void loadAlloc() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_budgetm")) {
                fba.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {

                    allocation_tbl.getItems().clear();
                    List b = fba.get();

                    for (Object d : b) {
                        HashMap hm = (HashMap) d;   //exquisite casting

                        hm.get("department");
                        hm.get("allocated_amount");

                        allc.add(new FINANCIAL_BM_ALLOCATION(
                                String.valueOf(hm.get("department")),
                                String.valueOf(hm.get("allocated_amount"))
                        ));

                    }
                    allocation_tbl.setItems(allc);

                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void OpenAllocation() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/FINANCIAL/CALLER/BUDGET_NEWALLOCATION.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FINANCIAL_COLLECTIONController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Insert New");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void SearchStatus(ActionEvent event) {

        Financial_budget_request fbr = new Financial_budget_request();

        //    String SearchText = SearchDVR.getText().equals("") ? "[a-z]" : SearchDVR.getText();
        try {

            List listst = fbr.where(new Object[][]{
                {"budget_status", "=", cmbobx_stats.getSelectionModel().getSelectedItem().toString()}
            }).get();

            searchStatus(listst);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
