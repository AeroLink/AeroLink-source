/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseActivityLogClassfiles;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ViewFullActivityLogController implements Initializable {

    @FXML
    private DatePicker datePickur_txt;
    @FXML
    private JFXButton filter_btn;
    @FXML
    private FontAwesomeIconView refresh_btn;
    @FXML
    private Button close_btn;
    @FXML
    private TableView<Log1_WarehouseActivityLogClassfiles> actLog_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ref();
        close_btn.setOnMouseClicked(e-> close());
        refresh_btn.setOnMouseClicked(e->ref());
        filter_btn.setOnMouseClicked(e->filter());
        fixDateFormat();
    }    
    
    public void fixDateFormat(){
        String pattern = "MM/dd/yyyy";

//        datePickur_txt.setPromptText(pattern.toLowerCase());

        datePickur_txt.setConverter(new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

        @Override 
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                return null;
                }
            }
        });
    }
    
    public void close() {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        stage.close();
    }
    
    public void ref(){
        setActLogTbl();
        populateTbl();
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
          
            List b = actLogDB.get();
            
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

    public void filter(){
        Log1_WarehouseActivityLogModel actLogDB = new Log1_WarehouseActivityLogModel();
        ObservableList<Log1_WarehouseActivityLogClassfiles> actLog = FXCollections.observableArrayList();
          
            List b = actLogDB.where(new Object[][]{
                {"ActivityDate", "=", datePickur_txt.getEditor().getText()}
            }).get();
            
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
