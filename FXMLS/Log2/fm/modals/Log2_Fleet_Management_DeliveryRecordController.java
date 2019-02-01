/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.fm.modals;

import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_DeliveryRecord;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_Details;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_Management_DeliveryRecordController implements Initializable {

    @FXML
    private TableView<Log2_Fleet_Management_DeliveryRecord> tbl_deliveryrecord;
    @FXML
    private ContextMenu menurecord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         tbl_deliveryrecord.getColumns().removeAll(tbl_deliveryrecord.getColumns());
        
        TableColumn<Log2_Fleet_Management_DeliveryRecord, String> vehiclemodel = new TableColumn<>("Vehicle Model");
        TableColumn<Log2_Fleet_Management_DeliveryRecord, String> plateno = new TableColumn<>("Plate no.");
        TableColumn<Log2_Fleet_Management_DeliveryRecord, String> datedelivered = new TableColumn<>("Date Delivered");

        vehiclemodel.setCellValueFactory(value -> value.getValue().Vehiclemodel);
        plateno.setCellValueFactory(value -> value.getValue().Plateno);
        datedelivered.setCellValueFactory(value -> value.getValue().Datedelivered);
        
        tbl_deliveryrecord.getColumns().addAll(vehiclemodel, plateno, datedelivered);
    }    
    
}
