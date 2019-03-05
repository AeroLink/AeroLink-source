package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_EmployeeProfilesModel;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Model.Log1.Log1_WarehouseItemModel;
import Model.Log1.Log1_WarehouseRequestItemModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemRecieveController implements Initializable {

    @FXML
    private JFXButton dp_save_btn;
    @FXML
    private Label dp_automatedDate_txt;
    @FXML
    private TextArea dp_remarks_txt;
    @FXML
    private TextField dp_firstname_txt;
    @FXML
    private TextField dp_lastName_txt;
    @FXML
    private ListView<String> req_view;
    @FXML
    private ComboBox selectEmployee_combx;
    @FXML
    private Label itemID_txt;
    @FXML
    private Label stock_txt;
    @FXML
    private Label rQuantity_txt;
    @FXML
    private Label status_txt;
    @FXML
    private Label critQty_txt;
    @FXML
    private ComboBox selectPackager_combox;
    @FXML
    private Label itemName_txt;
    @FXML
    private Label reqID_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCurrentDate();
        loadEmployeeToCombx();
        loadEmployeeToCombx2();
    }    

    public void dp_cancel() {
        Stage stage = (Stage) dp_automatedDate_txt.getScene().getWindow();
        stage.close();
    }
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//      get current date time with the power of friendship
        Date date = new Date();
        dp_automatedDate_txt.setText(dateFormat.format(date));
        
        
    }

    public void loadEmployeeToCombx(){
        HR4_EmployeeProfilesModel employeeDB = new HR4_EmployeeProfilesModel();
        
        List employeeProfile = employeeDB.get();

        employeeProfile.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            selectEmployee_combx.getItems().add(String.valueOf(hash.get("firstname")) + ", " + String.valueOf(hash.get("lastname")));
        });
    }
    public void loadEmployeeToCombx2(){
        HR4_EmployeeProfilesModel employeeDB = new HR4_EmployeeProfilesModel();
        
        List employeeProfile = employeeDB.get();

        employeeProfile.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            selectPackager_combox.getItems().add(String.valueOf(hash.get("firstname")) + ", " + String.valueOf(hash.get("lastname")));
        });
    }
    
    public void populateFields(Log1_WarehouseRequestItemClassfiles forPickUp) {
        ObservableList<String> reqDetails = FXCollections.observableArrayList();
        
        reqDetails.add("Request title:  "+forPickUp.getRequestTitle());
        reqDetails.add("Requestor:  "+forPickUp.getRequestor());
        reqDetails.add("Department:  "+forPickUp.getRequestorDepartment());
        reqDetails.add("Approved By:  "+forPickUp.getRequestApprover());
        reqDetails.add("Date Approved:  "+forPickUp.getDateApproved());
        reqDetails.add("Item Requested:  "+forPickUp.getItemName());
        reqDetails.add("Quantity:  "+forPickUp.getRequestQuantity());
        
        req_view.getItems().setAll(reqDetails);
        
        itemID_txt.setText(forPickUp.getItemID());
        itemName_txt.setText(forPickUp.getItemName());
        stock_txt.setText(forPickUp.getItemStock());
        rQuantity_txt.setText(forPickUp.getRequestQuantity());
        critQty_txt.setText(forPickUp.getItemCriticalLevel());
        reqID_txt.setText(forPickUp.getRequestID());
        
    }

    @FXML
    private void save(ActionEvent event) {
        String remork = dp_remarks_txt.getText();
        String fname = dp_firstname_txt.getText();
        String lname = dp_lastName_txt.getText();
        
        Boolean flag = remork.isEmpty() || fname.isEmpty() || lname.isEmpty();
        
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        Calendar cal = Calendar.getInstance();
        String timeRecieved = String.valueOf(timeFormat.format(cal.getTime()));
        
        
        int currentStock = Integer.parseInt(stock_txt.getText());
        int addedStock=Integer.parseInt(rQuantity_txt.getText());
            
        int newCurrentStock = Integer.valueOf(currentStock-addedStock);
        String forDB = String.valueOf(currentStock-addedStock);
        
//        StockQuantityPlusEnterAmount_txt.setText(answer);
//        int w=Integer.parseInt(StockQuantityPlusEnterAmount_txt.getText());
        int z =Integer.parseInt(critQty_txt.getText());
        
            if(newCurrentStock <= z){
                status_txt.setText("Need to Reorder!");
            }else{
                status_txt.setText("Good in Stock");
            }
            
        Log1_WarehouseItemModel itemDB = new Log1_WarehouseItemModel();  
        Log1_WarehouseActivityLogModel actLogDB = new Log1_WarehouseActivityLogModel();
        Log1_WarehouseRequestItemModel whRqDB = new Log1_WarehouseRequestItemModel();
        
        if(!flag){
            try{
                String [][] actlog_data ={
                    {"ActivityUser", selectPackager_combox.getValue().toString()},
                    {"ActivityItem",itemName_txt.getText()},
                    {"ActivityItemStock", stock_txt.getText()},
                    {"ActivityAction", "Take out"},
                    {"ActivityValue", rQuantity_txt.getText()},
                    {"ActivityItemStockRemaining",forDB},
                    {"ActivityPurpose", "Requested"},
                    {"ActivityDate", dp_automatedDate_txt.getText()},
                    {"ActivityTime",timeRecieved},
                };
                if(actLogDB.insert(actlog_data)){
                    AlertMaker.showSimpleAlert(null,"Operation Success!");
                    itemDB.update(new Object [][]{
                        {"ItemStock", forDB},
                        {"ItemStatus", status_txt.getText()},
                    }).where(new Object [][]{
                        {"ItemID","=",itemID_txt.getText()}
                    }).executeUpdate();
                    whRqDB.update(new Object[][]{
                        {"RecievedFrom", selectEmployee_combx.getValue().toString()},
                        {"PackagedBy", selectPackager_combox.getValue().toString()},
                        {"RecievedBy", dp_firstname_txt.getText()+", "+dp_lastName_txt.getText()},
                        {"RecievedRemarks", dp_remarks_txt.getText()},
                        {"DateRecieved", dp_automatedDate_txt.getText()},
                        {"TimeRecieved", timeRecieved},
                        {"RequestStatus", "Request Completed!"},
                    }).where(new Object[][]{
                        {"RequestID", "=", reqID_txt.getText()}
                    }).executeUpdate();
                    dp_cancel();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("", "Please, fillup remark.");
        }
    }

    
    
}
