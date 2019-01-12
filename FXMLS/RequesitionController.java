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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class RequesitionController implements Initializable {

    @FXML
    private TextField txtSearchReq;
    @FXML
    private JFXButton btnNewRequest;
    @FXML
    private TextField txtTitle;
    @FXML
    private HTMLEditor txtMessage;
    @FXML
    private ListView<?> lstComments;
    @FXML
    private TextArea txtReply;
    @FXML
    private JFXButton btnReply;
    @FXML
    private TableView<TableModel> tblListReq;

    class TableModel {

        SimpleStringProperty RequestID;
        SimpleStringProperty Requestor;
        SimpleStringProperty RequestTitle;

        public TableModel(String id, String requestor, String title) {
            this.RequestID = new SimpleStringProperty(id);
            this.Requestor = new SimpleStringProperty(requestor);
            this.RequestTitle = new SimpleStringProperty(title);
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

        btnNewRequest.setOnMouseClicked(evt -> {
            Modal md = Modal.getInstance(new Form("/FXMLS/NewRequest.fxml").getParent());
            md.open();
        });

        tblListReq.getItems().clear();
        tblListReq.getColumns().removeAll(tblListReq.getColumns());

        TableColumn<TableModel, String> Requestor = new TableColumn<>("Request From");
        TableColumn<TableModel, String> Title = new TableColumn<>("Request Title");

        Requestor.setCellValueFactory(value -> value.getValue().Requestor);
        Title.setCellValueFactory(value -> value.getValue().RequestTitle);

        tblListReq.getColumns().addAll(Requestor, Title);

        this.renderTable();

        tblListReq.setOnMouseClicked( value -> {
            openRequest(tblListReq.getSelectionModel().getSelectedItem().RequestID.getValue());
        });
    }

    public void openRequest(String id) {
        List<HashMap> list = req.where("request_id", "=", id).get();
        
        list.stream().forEach(v -> {
            txtMessage.setHtmlText(String.valueOf(v.get("request_content")));
            txtTitle.setText(String.valueOf(v.get("request_title")));
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

                    List<HashMap> list = employee.where("login_id", "=", Session.pull("user_id").toString())
                            .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_jobs", "employee_code", "=", "employee_code")
                            .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "aerolink.tbl_hr4_employee_jobs", "job_id",  true)
                            .get("aerolink.tbl_hr4_jobs.dept_id", "aerolink.tbl_hr4_employee_jobs.employee_code");

                    req.orWhere(new Object[][]{
                        {"request_to", "=", "*"},
                        {"request_to", "=", list.get(0).get("employee_code")}
                    },true).andWhere("target_dept", "=", list.get(0).get("dept_id").toString()).get().stream().forEach(v -> {

                        HashMap row = (HashMap) v;
                        employeeReq.add(new TableModel(row.get("request_id").toString(), row.get("request_from").toString(), row.get("request_title").toString()));
                       
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
