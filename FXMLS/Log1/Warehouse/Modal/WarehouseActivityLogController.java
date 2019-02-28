package FXMLS.Log1.Warehouse.Modal;


import FXMLS.Log1.ClassFiles.Log1_WarehouseActivityLogClassfiles;
import FXMLS.Log1.util.Log1Util;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class WarehouseActivityLogController implements Initializable {

    @FXML
    private TableView<Log1_WarehouseActivityLogClassfiles> actLog_tbl;
    @FXML
    private Label date_txt;
    @FXML
    private JFXButton close_btn;
    @FXML
    private JFXButton viewAll_btn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayCurrentDate();
        close_btn.setOnMouseClicked(e-> close());
        setActLogTbl();
        populateTbl();
        viewAll_btn.setOnMouseClicked(e->viewAllLogs());
    }    
    
    public void viewAllLogs(){
        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ViewFullActivityLog.fxml"),
                 "", null);
    }
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//      get current date time with Date()
        Date date = new Date();
        date_txt.setText(dateFormat.format(date));
    }
    public void close() {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }
    
    public void setActLogTbl(){
        actLog_tbl.getItems().clear();
        actLog_tbl.getColumns().removeAll(actLog_tbl.getColumns());

        TableColumn<Log1_WarehouseActivityLogClassfiles, String> User = new TableColumn<>("Login User");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> Item = new TableColumn<>("Item");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> Stock = new TableColumn<>("Stock");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> Action = new TableColumn<>("Action");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> value = new TableColumn<>("Amount");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> stockRemaining = new TableColumn<>("Remaining");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> purpose = new TableColumn<>("Purpose");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> date = new TableColumn<>("Date");
        TableColumn<Log1_WarehouseActivityLogClassfiles, String> time = new TableColumn<>("Time");

        User.setCellValueFactory((param) -> param.getValue().ActivityUser);
        Item.setCellValueFactory((param) -> param.getValue().ActivityItem);
        Stock.setCellValueFactory((param) -> param.getValue().ActivityItemStock);
        Action.setCellValueFactory((param) -> param.getValue().ActivityAction);
        value.setCellValueFactory((param) -> param.getValue().ActivityValue);
        stockRemaining.setCellValueFactory((param) -> param.getValue().ActivityItemStockRemaining);
        purpose.setCellValueFactory((param) -> param.getValue().ActivityPurpose);
        date.setCellValueFactory((param) -> param.getValue().ActivityDate);
        time.setCellValueFactory((param) -> param.getValue().ActivityTime);

        actLog_tbl.getColumns().addAll(User, Item, Stock, Action
                , value, stockRemaining, purpose, date, time);
    }

    public void populateTbl(){
        Log1_WarehouseActivityLogModel actLogDB = new Log1_WarehouseActivityLogModel();
        ObservableList<Log1_WarehouseActivityLogClassfiles> actLog = FXCollections.observableArrayList();
          
            List b = actLogDB.where(new Object [][]{
                {"ActivityDate", "=", date_txt.getText()}
            }).orderBy("created_at", Model.Sort.DESC).get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                actLog.add(new Log1_WarehouseActivityLogClassfiles(
                
                String.valueOf(hm.get("ActivityID")),
                String.valueOf(hm.get("ActivityUser")),
                String.valueOf(hm.get("ActivityItem")),
                String.valueOf(hm.get("ActivityItemStock")),
                String.valueOf(hm.get("ActivityAction")),
                String.valueOf(hm.get("ActivityValue")),
                String.valueOf(hm.get("ActivityItemStockRemaining")),
                String.valueOf(hm.get("ActivityPurpose")),
                String.valueOf(hm.get("ActivityDate")),
                String.valueOf(hm.get("ActivityTime"))
                ));       
        }
        actLog_tbl.getItems().clear();
        actLog_tbl.setItems(actLog);
    }
}