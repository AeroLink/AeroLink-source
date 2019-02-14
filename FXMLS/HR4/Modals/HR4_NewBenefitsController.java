/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Model.HR4_BenefitsModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_NewBenefitsController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField amount;
    @FXML
    private TextArea description;
    @FXML
    private TextField days;
    @FXML
    private JFXButton SubmitBtn;
    @FXML
    private JFXButton ClearBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    SubmitBtn.setOnMouseClicked(e ->SaveNewBenefits());
    }    
    public void SaveNewBenefits(){
        HR4_BenefitsModel fba = new HR4_BenefitsModel();
            
          try
        {
           String[][] fba_table =
        {
        {"title" , title.getText().toString()},
        {"amount" , amount.getText().toString()},
        {"description" , description.getText().toString()},
        {"days" , days.getText().toString()}
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
