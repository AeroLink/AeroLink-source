/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseRequestItemModel;
import Model.Log1.Log1_WarehouseItemsModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class RequestItemsOnWarehouseController implements Initializable {

    @FXML
    private Label itemRequested_txt;
    @FXML
    private TextField quantity_txt;
    @FXML
    private TextField requestor_txt;
    @FXML
    private TextField destination_txt;
    @FXML
    private JFXButton Request_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TableView<Log1_fullInventoryList> selectItemforRequest_tbl;
    @FXML
    private TableColumn<Log1_fullInventoryList, String> Item_col;
    @FXML
    private TableColumn<Log1_fullInventoryList, String> status_col;
    @FXML
    private Label itemID_txt;
    @FXML
    private DatePicker DateRequested_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Request_btn.setOnAction(e ->saveItemType());
        cancel_btn.setOnAction(e ->close());
        callWarehouseItems();
        displayWarehouseItem();
    }

    public void close(){
        Stage stage = (Stage) destination_txt.getScene().getWindow();
        stage.close();
    }

    public void saveItemType(){
        String quant = quantity_txt.getText();
        String reqstr = requestor_txt.getText();
        String dstntion = destination_txt.getText();
        
        Boolean flag =  quant.isEmpty()
                || reqstr.isEmpty() || dstntion.isEmpty();
        if(flag){
            AlertMaker.showErrorMessage("Invalid Input", "Please fill up all details");
            return;
        }
        Log1_WarehouseRequestItemModel coa = new Log1_WarehouseRequestItemModel();
        try{String [][] coa_table ={
            {"ItemID", itemID_txt.getText()},
            {"QuantityRequested", quantity_txt.getText()},
            {"RequestedBy", requestor_txt.getText()},
            {"Destination", destination_txt.getText()},
            {"RequestStatus", "For Approval"}
            };
            if(coa.insert(coa_table)){
                AlertMaker.showSimpleAlert("Saved", ""+ itemRequested_txt.getText()+" has been requested.");
                clearFields();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void clearFields() {
        itemRequested_txt.setText("");
        quantity_txt.setText("");
        requestor_txt.setText("");
        destination_txt.setText("");
    }

    @FXML
    private void selectItemToRequestAction(MouseEvent event) {
        itemID_txt.setText(selectItemforRequest_tbl.getSelectionModel().getSelectedItem().getItemID());
        itemRequested_txt.setText(selectItemforRequest_tbl.getSelectionModel().getSelectedItem().getItemDescription());
    }
    
    public void callWarehouseItems(){
//         Log1_WarehouseItemsModel coa = new Log1_WarehouseItemsModel();
//         ObservableList<Log1_fullInventoryList> ItemsXD = FXCollections.observableArrayList();
//          
//            List b = coa.join
//        (Model.JOIN.INNER, "aerolink.tbl_log1_suppliers", "SupplierID", "=", "SupplierID").where
//        (new Object [][]{
//            {"Status", "=", "Good on Stock"}
//        }).get();
//
//            for(Object d : b)
//                {
// 
//                    //rs = hm
//                HashMap hm = (HashMap) d;   //exquisite casting
//                
//               ItemsXD.add(new Log1_fullInventoryList(
//                
//                String.valueOf(hm.get("ItemID")),
//                String.valueOf(hm.get("SupplierID")),
//                String.valueOf(hm.get("SupplierName")),
//                String.valueOf(hm.get("ItemDescription")),
//                String.valueOf(hm.get("ItemType")),
//                String.valueOf(hm.get("ItemLocation")),
//                String.valueOf(hm.get("ItemUnit")),
//                String.valueOf(hm.get("UnitPrice")),
//                String.valueOf(hm.get("StockQuantity")),
//                String.valueOf(hm.get("CriticalQuantity")),
//                String.valueOf(hm.get("DisposalDate")),
//                String.valueOf(hm.get("Status"))
//                
//                ));       
//        }
//        selectItemforRequest_tbl.setItems(ItemsXD);
    }
    
    public void displayWarehouseItem(){
        Item_col.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }
}
