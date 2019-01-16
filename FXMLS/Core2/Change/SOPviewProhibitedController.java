/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import Model.Core2.CORE2_Prohibited;
import FXMLS.Core2.ClassFiles.SOPTable_prohibited;
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
public class SOPviewProhibitedController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
    @FXML
    private AnchorPane SOProotPane;
    @FXML
    private TableView<SOPTable_prohibited> tblPI;
    @FXML
    private JFXButton gpbtn;
    @FXML
    private TextArea TAgp;

    CORE2_Prohibited p = new CORE2_Prohibited();
    ObservableList<SOPTable_prohibited> pi = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.GenerateGL();
        this.populateTableGL();

        pi.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblPI.setItems(pi);
        });
        gpbtn.setOnMouseClicked(e -> Insert1());
    }

    public void GenerateGL() {
        tblPI.getItems().clear();
        tblPI.getColumns().removeAll(tblPI.getColumns());

        TableColumn<SOPTable_prohibited, String> description = new TableColumn<>("PROHIBITED ITEMS");
        description.setCellValueFactory((TableColumn.CellDataFeatures<SOPTable_prohibited, String> param) -> param.getValue().description);
        tblPI.getColumns().addAll(description);
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTableGL() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = p.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = p
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

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTableGL(List rs) {
        pi.clear();

        for (Object row : rs) {
            HashMap grow = (HashMap) row;

            String description = (String) grow.get("description");

            pi.add(new SOPTable_prohibited(description));
        }
    }

    // INSERT QUERY para sa mga textfield etc. ganon
    public void Insert1() {
        CORE2_Prohibited pi = new CORE2_Prohibited();
        // ginawa ko to para mag popup yung else sa loob ng try
        String TGP = TAgp.getText();

        try {
            String[][] sop_data1 = {
                {"description", TAgp.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (pi.insert(sop_data1)) {
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
