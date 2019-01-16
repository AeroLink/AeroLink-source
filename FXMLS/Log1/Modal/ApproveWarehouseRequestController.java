/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Modal;

import FXMLS.Log1.ClassFiles.Log1_ItemRequestsClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Model.Log1.Log1_WarehouseRequestItemModel;
import Model.Log1.Log1_WarehouseItemsModel;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ApproveWarehouseRequestController implements Initializable {

    @FXML
    private Label minusx_txt;
    @FXML
    private Label itemDescript_txt;
    @FXML
    private Label stock_txt;
    @FXML
    private Label status_txt;
    @FXML
    private Label itemLoc_txt;
    @FXML
    private JFXTextField secretStatus_txt;
    @FXML
    private JFXTextField StockQuantityPlusEnterAmount_txt;
    @FXML
    private JFXTextField CriticalQuantity_txt;
    @FXML
    private JFXTextField ItemID_txt;
    @FXML
    private Label ItemRequested_txt;
    @FXML
    private Label Requestor_txt;
    @FXML
    private Label Destination_txt;
    @FXML
    private Label DateRequested_txt;
    @FXML
    private Button Approve_btn;
    @FXML
    private Label RequestID_txt;
    @FXML
    private JFXTextField showInMainWindow_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Approve_btn.setOnAction(e ->StockOut());
    }    

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) ItemID_txt.getScene().getWindow();
        stage.close();
    }
    
    public void inflateUI(Log1_ItemRequestsClassfiles selectedForApproval) {
        RequestID_txt.setText(selectedForApproval.getRequestOnWarehouseID());
        ItemRequested_txt.setText(selectedForApproval.getItemDescription());
        Requestor_txt.setText(selectedForApproval.getRequestedBy());
        Destination_txt.setText(selectedForApproval.getDestination());
        DateRequested_txt.setText(selectedForApproval.getDateItemRequested());
        minusx_txt.setText(selectedForApproval.getQuantityRequested());
        
        itemDescript_txt.setText(selectedForApproval.getItemDescription());
        stock_txt.setText(selectedForApproval.getStockQuantity());
        status_txt.setText(selectedForApproval.getStatus());
        itemLoc_txt.setText(selectedForApproval.getItemLocation());
        CriticalQuantity_txt.setText(selectedForApproval.getCriticalQuantity());
        ItemID_txt.setText(selectedForApproval.getItemID());
    }
    
    public void StockOut(){
        
            int x=Integer.parseInt(stock_txt.getText());
            int y=Integer.parseInt(minusx_txt.getText());
            
        String answer=String.valueOf(x-y);
        StockQuantityPlusEnterAmount_txt.setText(answer);
        int w=Integer.parseInt(StockQuantityPlusEnterAmount_txt.getText());
        int z=Integer.parseInt(CriticalQuantity_txt.getText());
            if(w <= z){
                secretStatus_txt.setText("Need to Reorder!");
                showInMainWindow_txt.setText("yes");
            }else{
                secretStatus_txt.setText("Need to Reorder!");
                showInMainWindow_txt.setText("yes");
            }
        
        Log1_WarehouseActivityLogModel coa = new Log1_WarehouseActivityLogModel();
        Log1_WarehouseItemsModel coa1 = new Log1_WarehouseItemsModel();
        Log1_WarehouseRequestItemModel coa2 = new Log1_WarehouseRequestItemModel();
        coa.insert(new String [][]{
            {"ActivityItemName",itemDescript_txt.getText()},
            {"ActivityUser", "rb"},
            {"ActivityAction", "Take out"},
            {"ActivityValueAddedOrRemoved", minusx_txt.getText()}
            });
        try{coa1.update(new Object[][]{ 
                {"ItemDescription",itemDescript_txt.getText()},
                {"StockQuantity",StockQuantityPlusEnterAmount_txt.getText()},
                {"Status",secretStatus_txt.getText()},
                {"ShowInMainWindow", showInMainWindow_txt.getText()}
            }).where(new Object[][]{
                {"ItemID","=",ItemID_txt.getText()}
            }).executeUpdate();
            if(coa2.update(new Object [][]{
                {"RequestStatus", "Approved"}
            }).where(new Object[][]{
                {"RequestOnWarehouseID", "=", RequestID_txt.getText()}
            }).executeUpdate()){
                AlertMaker.showSimpleAlert(null,"Operation Success!");
                minusx_txt.setText("");
                return;
            }else{
                AlertMaker.showErrorMessage(null,"Operation Failed");
                return;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
