/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;
import FXMLS.HR4.Filler.HR4_EmployeeFill;
import FXMLS.HR4.Model.HR4_Benefits1Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_SavingController implements Initializable {
    HR4_AddEmployeeToBenefitsController ae = new HR4_AddEmployeeToBenefitsController();
    
    
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField tiT;
    @FXML
    private TextField benC;
    @FXML
    private TextField Bal;
    @FXML
    private TextField valD;
    @FXML
    private JFXButton submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a.setText(HR4_EmployeeFill.a);
        b.setText(HR4_EmployeeFill.b);
        
        submit.setOnMouseClicked(e -> Submit());
    }
    public void setText(String idxx, String balC, String dd, String tt){
        this.benC.setText(idxx);
        this.tiT.setText(tt);
        this.Bal.setText(balC);
        this.valD.setText(dd);
        
    }
    public void Submit(){
        
        HR4_Benefits1Model fbr = new HR4_Benefits1Model();
        try
        {
           String[][] b_tbl =
        {
        {"benefits_id",benC.getText()},
        {"emp_code",a.getText()},
        {"balance",Bal.getText()},
        {"araw",valD.getText()},
        };           
           
        if(fbr.insert(b_tbl)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Added");
             alert.setContentText("Employee Added"); 
             alert.showAndWait();
              
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    }
}
