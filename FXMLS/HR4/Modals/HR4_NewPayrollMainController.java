/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import FXMLS.HR4.Model.HR4_NewPayrollModel;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_NewPayrollMainController implements Initializable {

    @FXML
    private JFXComboBox<?> dept_cb;
    @FXML
    private JFXDatePicker sd;
    @FXML
    private JFXDatePicker ed;
    long DummyCount = 0;
    long GlobalCount = 0;
    HR4_NewPayrollModel npm = new HR4_NewPayrollModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
     public void InsertNewPayrollIem(){
         HR4_NewPayrollModel fba = new HR4_NewPayrollModel();
            
          try
        {
           String[][] fba_table =
        {
        //{"payroll_code","PAYROLL00" + id},
        {"dept_id" , dept_cb.getValue().toString()},
        {"start_date" , sd.getValue().toString()},
        {"end_date" , ed.getValue().toString()}
        };           
           
           
        if(fba.insert(fba_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("Data has been saved"); 
             alert.showAndWait();
            
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("PLEASE FILL THE EMPTY FIELDS"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
   
        }
    
}
