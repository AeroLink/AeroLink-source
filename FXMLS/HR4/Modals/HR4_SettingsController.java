/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_SSSClass;
import FXMLS.HR4.ClassFiles.HR4_SalaryClass;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_SSSModel;
import Synapse.Session;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_SettingsController implements Initializable {
    @FXML
    private TableView<HR4_SSSClass> tri_tbl;
    ObservableList<HR4_SSSClass> obj = FXCollections.observableArrayList();
    HR4_SSSModel sssmodel = new HR4_SSSModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.generateTable();
        this.populateTable();
    }
        public void generateTable() {

        tri_tbl.getItems().clear();
        tri_tbl.getColumns().removeAll(tri_tbl.getColumns());
        TableColumn<HR4_SSSClass, String> roc = new TableColumn<>("Range of Compensation");
        TableColumn<HR4_SSSClass, String> msc = new TableColumn<>("Monthly Salary Credit");
        TableColumn<HR4_SSSClass, String> er1 = new TableColumn<>("Employer");
        TableColumn<HR4_SSSClass, String> ee1 = new TableColumn<>("Employee");
        TableColumn<HR4_SSSClass, String> total1 = new TableColumn<>("Total");
        TableColumn<HR4_SSSClass, String> ec_er = new TableColumn<>("Designation");
        TableColumn<HR4_SSSClass, String> er2 = new TableColumn<>("Employer");
        TableColumn<HR4_SSSClass, String> ee2 = new TableColumn<>("Employee");
        TableColumn<HR4_SSSClass, String> total2 = new TableColumn<>("Total");
        TableColumn<HR4_SSSClass, String> totalcon = new TableColumn<>("Total Contribution");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        roc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().a);
        msc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().c);
        er1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().d);
        ee1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().e);
        total1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().f);
        ec_er.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().g);
        er2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().h);
        ee2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().i);
        total2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().j);
        totalcon.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().k);

        //</editor-fold>
        tri_tbl.getColumns().addAll(roc, msc, er1, ee1, total1, ec_er, er2, ee2, total2, totalcon);
    }
        
    long DummyCount = 0;
    long GlobalCount = 0;
    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    sssmodel.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        tri_tbl.getItems();
                            List rs = sssmodel
                                    .get(
                                            "CONCAT(rocmin,' - ',rocmax) as roc",
                                            "msc",
                                            "er1",
                                            "ee1",
                                            "total1",
                                            "ec_er",
                                            "er2",
                                            "ee2",
                                            "total2",
                                            "totalcon");
                        AddJobToTable(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable(List rs) {
        obj.clear();
        tri_tbl.refresh();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String roc = String.valueOf(crow.get("roc"));
            String msc = (String) crow.get("msc");
            String er1 = (String) crow.get("er1");
            String ee1 = (String) crow.get("ee1");
            String total1 = (String) crow.get("total1");
            String ec_er = (String) crow.get("ec_er");
            String er2 = (String) crow.get("er2");
            String ee2 = (String) crow.get("ee2");
            String total2 = (String) crow.get("total2");
            String totalcon = (String) crow.get("totalcon");
            obj.add(new HR4_SSSClass(roc, msc, er1, ee1, total1,ec_er,er2,ee2,total2,totalcon));
        }
        tri_tbl.setItems(obj);
    
    }    
    
}
