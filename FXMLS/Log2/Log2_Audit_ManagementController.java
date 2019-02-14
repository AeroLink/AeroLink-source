/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Audit_Management_loa;
import Model.Log2.Log2_loa;
import Model.Log2.Log2_reservationform;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Audit_ManagementController implements Initializable {

    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtadd;
    @FXML
    private JFXTextField txtcontact;
    @FXML
    private JFXTextField txtae;
    @FXML
    private JFXButton btn_saveauditor;
    @FXML
    private TableView<Log2_Audit_Management_loa> tblloa;
    private Connection con = DBconfig.con();
    private PreparedStatement pst= null;
    private ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateloa();
        this.loaddataloa();

       
    }

    private void generateloa() {

        tblloa.getColumns().removeAll(tblloa.getColumns());

        TableColumn<Log2_Audit_Management_loa, String> auditor = new TableColumn<>("Auditor");
        TableColumn<Log2_Audit_Management_loa, String> aeo = new TableColumn<>("Area of Experties");

        auditor.setCellValueFactory(value -> value.getValue().Auditor);
        aeo.setCellValueFactory(value -> value.getValue().Area_of_exp);

        tblloa.getColumns().addAll(auditor, aeo);
    }

    Log2_loa la = new Log2_loa("loa");
    ObservableList<Log2_Audit_Management_loa> listofauditor = FXCollections.observableArrayList();
    long DummyCount = 0;
    long GlobalCount = 0;

    private void loaddataloa() {

        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("log2am")) {
                la.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                    DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                });

                if (DummyCount != GlobalCount) {
                    tblloa.getItems().clear();
                    List<HashMap> hash = la.get();
                    hash.stream().forEach(e -> {
                        listofauditor.add(new Log2_Audit_Management_loa(
                                String.valueOf(e.get("name")),
                                String.valueOf(e.get("area_of_exp"))
                        ));
                    });
                    tblloa.setItems(listofauditor);
                    GlobalCount = DummyCount;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log2_Audit_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    @FXML
    public void Saveleop() {


    
    
    }
    
    @FXML
    public void save(){
         try{
         String insert = "insert into aerolink.tbl_Log2_list_of_auditor values(?,?,?,?) ";
         pst = con.prepareStatement(insert);
         pst.setString(1, txtname.getText());
         pst.setString(2, "No Value");
         pst.setString(3, "No Value");
         pst.setString(4, txtae.getText());
         pst.execute();
         
         
     }catch(Exception ex){
         System.out.println(ex.getMessage());

     }
    
    }
}
