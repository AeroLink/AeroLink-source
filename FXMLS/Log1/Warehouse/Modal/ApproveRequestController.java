
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseRequestItemClassfiles;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.HR4_EmployeeProfilesModel;
import Model.Log1.Log1_WarehouseActivityLogModel;
import Model.Log1.Log1_WarehouseItemModel;
import Model.Log1.Log1_WarehouseRequestItemModel;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ApproveRequestController implements Initializable {

    @FXML
    private Label dateToday_lbl;
    @FXML
    private Button done_btn;
    @FXML
    private Text reqTitle_txt;
    @FXML
    private ComboBox approver_txt;
    @FXML
    private TextArea remarkzzzzzzzz_txt;
    @FXML
    private Label reqID_txt;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayCurrentDate();
        loadEmployeeToCombx();
        done_btn.setOnMouseClicked(e->executeThisParaMakaGraduateNaPlease());
    }    

    public void close() {
        clearFields();
        Stage stage = (Stage) reqID_txt.getScene().getWindow();
        stage.close();
    }
    
    public void loadEmployeeToCombx(){
        HR4_EmployeeProfilesModel employeeDB = new HR4_EmployeeProfilesModel();
        
        List employeeProfile = employeeDB.get();

        employeeProfile.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            approver_txt.getItems().add(String.valueOf(hash.get("firstname")) + ", " + String.valueOf(hash.get("lastname")));
        });
    }
    
    
    public void displayCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//      get current date time with the power of friendship
        Date date = new Date();
        dateToday_lbl.setText(dateFormat.format(date));
    }
    
     public void clearFields(){
        reqTitle_txt.setText("");
    }
    
    public void populateFields(Log1_WarehouseRequestItemClassfiles x) {
        
        reqTitle_txt.setText(x.getRequestTitle());
        reqID_txt.setText(x.getRequestID());
    }
    
    public void executeThisParaMakaGraduateNaPlease(){
        String remork = remarkzzzzzzzz_txt.getText();
        
        Boolean flag = remork.isEmpty();
         
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//      get current date time with the power of friendship
        
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(timeFormat.format(cal.getTime()));
        
        
        Log1_WarehouseRequestItemModel whRqDB = new Log1_WarehouseRequestItemModel();
        
        
        if(!flag){
            try{
                if(whRqDB.update(new Object[][]{
                        {"RequestStatus", "Approved"},
                        {"ApprovedRequestRemarks", remarkzzzzzzzz_txt.getText()},
                        {"RequestApprover", approver_txt.getValue()},
                        {"DateApproved", dateToday_lbl.getText()},
                        {"TimeApproved", time}
                    }).where(new Object[][]{
                        {"RequestID", "=", reqID_txt.getText()}
                    }).executeUpdate()){
                    AlertMaker.showSimpleAlert("Congratulations!!", "Request Approved!");
                    clearFields();
                    close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            AlertMaker.showErrorMessage("", "Please, fillup remark.");
        }
        
    }   
    
    
}
