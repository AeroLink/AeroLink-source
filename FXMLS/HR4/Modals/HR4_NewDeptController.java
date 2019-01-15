/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import Model.HR4_Departments;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_NewDeptController implements Initializable {
    HR4_Departments hrd = new HR4_Departments();

    @FXML
    private JFXButton SubmitBtn;
    @FXML
    private JFXTextField iid;
    @FXML
    private JFXTextField dn;
    @FXML
    private JFXTextArea dd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    SubmitBtn.setOnMouseClicked(e -> save());
    }    

    @FXML
    private void SubmitAction(ActionEvent event) {
        
    }
    public void save(){
           
            
        HR4_Departments cpp = new HR4_Departments();
            
          try
        {
           String[][] cpp_table =
        {
        {"dept_name" , dn.getText()},
        {"dept_desc" , dd.getText()}
        
        
        
        };           
           
           
        if(cpp.insert(cpp_table)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Saved");
             alert.setContentText("DATA HAS BEEN SAVE"); 
             alert.showAndWait();
             dn.clear();
             dd.clear();
             
             
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
