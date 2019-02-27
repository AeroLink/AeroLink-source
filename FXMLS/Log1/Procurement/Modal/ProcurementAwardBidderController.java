/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcurementBiddersClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ProcurementBiddersModel;
import Model.Log1.Log1_ProcurementPostedItemStatusModel;
import Model.Log1.Log1_ProcurementPurchaseListModel;
import Model.Log1.Log1_ProcurementSupplierCountModel;
import Model.Log1.Log1_ProcurementSupplierModel;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ProcurementAwardBidderController implements Initializable {

    
    @FXML
    private TextField fname_txt;
    @FXML
    private TextField lname_txt;
    @FXML
    private TextField dateToday_txt;
    @FXML
    private DatePicker contractEnd_dp;
    @FXML
    private TextArea remarks_txt;
    
    @FXML
    private Label bidderName_txt;
    @FXML
    private Label bidderRepresentative_txt;
    @FXML
    private Label bidderContact_txt;
    @FXML
    private Label bidderEmail_txt;
    @FXML
    private Label bidderLocation_txt;
    @FXML
    private Label supplierCount_txt;
    @FXML
    private Label itemID_txt;
    @FXML
    private TextField bidderPrice_txt;
    @FXML
    private Label bidderID_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCurrentDate();
        baseSupplierCount();
    }    

    public void displayCurrentDate(){
     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    get current date time with Date()
        Date date = new Date();
        dateToday_txt.setText(dateFormat.format(date));
    }
    
    @FXML
    public void award(ActionEvent event) {
        String fname = fname_txt.getText();
        String lname = lname_txt.getText();
        String cEnd = contractEnd_dp.getEditor().getText();
        String remarks = remarks_txt.getText();
        
        Boolean flag = fname.isEmpty() || lname.isEmpty() || cEnd.isEmpty() || remarks.isEmpty();
        
        int x = Integer.parseInt(supplierCount_txt.getText());
        String suppID = String.valueOf(x+1);
        
        Log1_ProcurementSupplierModel suppDB = new Log1_ProcurementSupplierModel();
        Log1_ProcurementPurchaseListModel purchListDB = new Log1_ProcurementPurchaseListModel();
        Log1_ProcurementPostedItemStatusModel purchItemStatsDB = new Log1_ProcurementPostedItemStatusModel();
        Log1_ProcurementBiddersModel bidderDB = new Log1_ProcurementBiddersModel();
        Log1_ProcurementSupplierCountModel supplierCount = new Log1_ProcurementSupplierCountModel();
        if(flag){
            AlertMaker.showErrorMessage("","Please fill up all the details");
            return;
        }else{
            String[][] supplierData ={
                {"SupplierName", bidderName_txt.getText()},
                {"SupplierRepresentative", bidderRepresentative_txt.getText()},
                {"SupplierContact", bidderContact_txt.getText()},
                {"SupplierEmail", bidderEmail_txt.getText()},
                {"SupplierLocation", bidderLocation_txt.getText()},
                {"ContractStarted", dateToday_txt.getText()},
                {"ContractEnd", contractEnd_dp.getEditor().getText()},
                {"AwardedBy",fname_txt.getText()+", "+lname_txt.getText()},
                {"Remarks",remarks_txt.getText()},
                {"SupplierStatus","Active"}
            };
            String[][] purchItemData ={
                {"ItemID", itemID_txt.getText()},
                {"SupplierID",suppID},
                {"Price", bidderPrice_txt.getText()},
                {"Status", "readyForBudgetRequest"}
            };
            if(suppDB.insert(supplierData) && purchListDB.insert(purchItemData)){
                AlertMaker.showSimpleAlert("", "Bidder Awarded!");
                purchItemStatsDB.update(new Object[][]{
                    {"Status", "Supplier Selected"}
                }).where(new Object[][]{
                    {"ItemID", "=", itemID_txt.getText()}
                }).executeUpdate();
                bidderDB.update(new Object[][]{
                    {"BidderStatus","Bidder Selected"}
                }).where(new Object[][]{
                    {"BidderID","=",bidderID_txt.getText()}
                }).executeUpdate();
                supplierCount.update(new Object[][]{
                    {"SupplierCount", suppID}
                }).where(new Object[][]{
                    {"id", "=", "1"}
                }).executeUpdate();
                close();
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) dateToday_txt.getScene().getWindow();
        stage.close();
    }

    public void close(){
        Stage stage = (Stage) dateToday_txt.getScene().getWindow();
        stage.close();
    }
    
    public void getBidderName(Log1_ProcurementBiddersClassfiles a) {
        bidderName_txt.setText(a.getBidderName());
        bidderRepresentative_txt.setText(a.getBidderRepresentative());
        bidderContact_txt.setText(a.getBidderContact());
        bidderEmail_txt.setText(a.getBidderEmail());
        bidderLocation_txt.setText(a.getBidderLocation());
        itemID_txt.setText(a.getItemID());
        bidderID_txt.setText(a.getBidderID());
        bidderPrice_txt.setText(a.getBidderPrice());
        if (bidderPrice_txt.getText().isEmpty()) {
            bidderPrice_txt.setText("0");
            }else{
            bidderPrice_txt.setText(NumberFormat.getInstance().format(Long.parseLong(bidderPrice_txt.getText().substring(4).replace(",", ""))));
            bidderPrice_txt.end();
            }
    }
    
    public void baseSupplierCount(){  
        Log1_ProcurementSupplierCountModel supplierCount = new Log1_ProcurementSupplierCountModel();
        List count = supplierCount.get();

        count.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            supplierCount_txt.setText((String.valueOf(hash.get("SupplierCount"))));
        });
    }
}
