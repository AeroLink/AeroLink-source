/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS;

import Model.HR4.HR4_Employee;
import Model.Requisition.Requests;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class RequesitionController implements Initializable {

    @FXML
    private TableView<TableModel> tblListReq;

    class TableModel {

        SimpleStringProperty RequestID;
        SimpleStringProperty Requestor;
        SimpleStringProperty RequestTitle;
        SimpleStringProperty RequestDesc;
        SimpleStringProperty RequestStatus;
        SimpleStringProperty RequestCreated;
        SimpleStringProperty RequestUpdated;

        public TableModel(String id, String requestor, String title, String desc, String status, String created_at, String updated_at) {
            this.RequestID = new SimpleStringProperty(id);
            this.Requestor = new SimpleStringProperty(requestor);
            this.RequestDesc = new SimpleStringProperty(desc);
            this.RequestStatus = new SimpleStringProperty(status);
            this.RequestTitle = new SimpleStringProperty(title);
            this.RequestCreated = new SimpleStringProperty(created_at);
            this.RequestUpdated = new SimpleStringProperty(updated_at);
        }

    }

    HR4_Employee employee = new HR4_Employee();
    HR4_Employee employeeJob = new HR4_Employee("job");
    Requests req = new Requests();
    private ObservableList employeeReq = FXCollections.observableArrayList();
    long GlobalCount = 0;
    long DummyCount = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tblListReq.getItems().clear();
        tblListReq.getColumns().removeAll(tblListReq.getColumns());

        TableColumn<TableModel, String> Title = new TableColumn<>("Request");
        TableColumn<TableModel, String> Desc = new TableColumn<>("Request Description");
        TableColumn<TableModel, String> Status = new TableColumn<>("Request Status");
        TableColumn<TableModel, String> Created = new TableColumn<>("Request Created");
        TableColumn<TableModel, String> Updated = new TableColumn<>("Request Updated");

        Title.setCellValueFactory(value -> value.getValue().RequestTitle);
        Desc.setCellValueFactory(value -> value.getValue().RequestDesc);
        Status.setCellValueFactory(value -> value.getValue().RequestStatus);
        Created.setCellValueFactory(value -> value.getValue().RequestCreated);
        Updated.setCellValueFactory(value -> value.getValue().RequestUpdated);

        tblListReq.getColumns().addAll(Title, Desc, Status, Created, Updated);

        this.renderTable();

        tblListReq.setOnMouseClicked( value -> {
            
        });
    }

    
    public void renderTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("requisitions")) {
                req.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    System.err.println(DummyCount);
                });

                if (DummyCount != GlobalCount) {
                    
                    req.where(new Object[][]{
                        {"requestor_id", "=", Session.pull("employee_code")}
                    }).get().stream().forEach(v -> {
                        HashMap row = (HashMap) v;
                        employeeReq.add(new TableModel(
                                row.get("request_id").toString(), 
                                row.get("requestor_id").toString(),
                                row.get("request").toString(),
                                row.get("request_description").toString(),
                                row.get("request_status").toString(),
                                row.get("created_at").toString(), 
                                row.get("updated_at").toString()));
                    });
                    
                    tblListReq.getItems().clear();
                    tblListReq.setItems(employeeReq);
                    
                    GlobalCount = DummyCount;
                }
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RequesitionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

}
