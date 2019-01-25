/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.fm.modals;

import FXMLS.HR3.ClassFiles.HR3_tpLeaves;
import FXMLS.Log2.ClassFiles.Log2_Fleet_Management_Details;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_Management_VehicleModelController implements Initializable {


    @FXML
    private TableView<Log2_Fleet_Management_Details> tbldetails;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generatetable();
        
    }    
    
    private void generatetable() {
        tbldetails.getColumns().removeAll(tbldetails.getColumns());
        
        TableColumn<Log2_Fleet_Management_Details, String> itemname = new TableColumn<>("Item Name");
        TableColumn<Log2_Fleet_Management_Details, String> quantity = new TableColumn<>("Quantity");
        

        itemname.setCellValueFactory(value -> value.getValue().Itemname);
        quantity.setCellValueFactory(value -> value.getValue().Quantity);
        
        tbldetails.getColumns().addAll(itemname, quantity);
       
    }
}
