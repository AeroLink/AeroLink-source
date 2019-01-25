/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.SOPTable_package_information;
import Model.Core2.CORE1_Customer_Details1;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SOPviewPackageInspectionController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
    @FXML
    private AnchorPane SOProotPane;
    @FXML
    private TableView<SOPTable_package_information> tblPackage;

    ObservableList<SOPTable_package_information> spi = FXCollections.observableArrayList();
    CORE1_Customer_Details1 con = new CORE1_Customer_Details1();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        spi.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblPackage.setItems(spi);
        });

//        this.GenerateTC();
//        this.populateTable();
    }

    public void GenerateTC() {
        tblPackage.getItems().clear();
        tblPackage.getColumns().removeAll(tblPackage.getColumns());

        TableColumn<SOPTable_package_information, String> book_id = new TableColumn<>("PACKAGE NUMBER");
        TableColumn<SOPTable_package_information, String> status = new TableColumn<>("STATUS");
        TableColumn<SOPTable_package_information, String> created_at = new TableColumn<>("DATE");
//        book_id.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().book_id);
//        status.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().status);
//        created_at.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_package_information, String> param) -> param.getValue().created_at);
        tblPackage.getColumns().addAll(book_id, status, created_at);
    }

//    public void populateTable() {
//        Thread th = new Thread(new Task() {
//            @Override
//            protected Object call() throws Exception {
//                while (true) {
//                    CompletableFuture.supplyAsync(() -> {
//                        List list = con.get(("COUNT(*) as total"));
//                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
//                    }).thenApply((count) -> {
//                        List rs = new ArrayList<>();
//                        if (count != Global_Count) {
//                            rs = con
//                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id")
//                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_package_details", "customer_id1", "pid", "=", "customer_id")
//                                    .get("book_id",
//                                            "status",
//                                            "created_at");
//                            Global_Count = count;
//                        }
//                        return rs;
//                    }).thenAccept((rs) -> {
//                        if (!rs.isEmpty()) {
//                            AddTable(rs);
//                        }
//                    });
//                    Thread.sleep(3000);
//                }
//            }
//        });
//        th.setDaemon(true);
//        th.start();
//    }
    // need sya para lumabas yung result sa tableview ez LAWGIC

//    public void AddTable(List rs) {
//        spi.clear();
//
//        for (Object row : rs) {
//            HashMap drow = (HashMap) row;
//
//            String book_id = String.valueOf(drow.get("book_id"));
//            String status = (String) drow.get("status");
//            String created_at = (String) drow.get("created_at");
//
//            spi.add(new SOPTable_package_information(book_id, status, created_at));
//        }
//    }

    
    
    // mga button sa taas
    @FXML
    private void inspectionguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPviewInspectionGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void manageguidelines(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SOPcreateManageGuidelines.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void packagedetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/StandardOperationalProcedure.fxml"));
        SOProotPane.getChildren().setAll(pane);
    }

}
