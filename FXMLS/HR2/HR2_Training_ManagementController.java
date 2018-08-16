/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import Model.HR2_Competency_Management;
import Model.HR2_Training_Management;
import Synapse.Database;
import Synapse.DB.MySql;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_ManagementController implements Initializable {

   
    @FXML
    private JFXButton btn_save;
    @FXML
    private TableView<?> list_of_trainees;
  
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXTextField txt_employee_name;
    @FXML
    private JFXTextField txt_job_position;
    @FXML
    private JFXTextField txt_title;
    @FXML
    private JFXTextField txt_location;
    @FXML
    private JFXTextField txt_date_start;
    @FXML
    private JFXTextField txt_date_end;
    @FXML
    private JFXTextField txt_time_start;
    @FXML
    private JFXTextField txt_budget_cost;
    @FXML
    private JFXTextField txt_organizer;
    @FXML
    private JFXComboBox<String> type_of_training;
    @FXML
    private JFXTextField txt_time_end;
    @FXML
    private JFXTextField txt_emp_id;
    @FXML
    private JFXTextField txt_trainor;


    /**
     * Initializes the controller class.
     */
    
    
     ObservableList<String> sdl = FXCollections.observableArrayList("Internal" , "External");
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Database d = Database.getInstance();
            d.DB_INIT(new MySql());
            d.startConnection();
            
             type_of_training.setItems(sdl);
                 type_of_training.setPromptText("Choose Level");
                 
            btn_new.setOnMouseClicked(e -> New());
            DisableComponents();
              
               
         //   btn_save.setOnAction(e -> Save());
    }    
    
     public void DisableComponents()
    {
    
                    
            Node[] d = {
                        
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_employee_name,
                         txt_job_position,
                         txt_title,
                        txt_location,
                        txt_date_start,
                        txt_date_end,
                        txt_time_start,
                        txt_budget_cost,
                        txt_organizer,
                        type_of_training,
                        txt_time_end,
                        txt_emp_id,
                        txt_trainor
                };
                        try
                        {
                            for(Node c : d)
                            {
                                    if(c instanceof JFXTextField)
                                    {
                                                JFXTextField m = (JFXTextField) c;
                                                  /*  validations v = new validations();
                                                         v.maximumChars(m, 25);
                                                         Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                         alert.initStyle(StageStyle.UNDECORATED);
                                                                         alert.setTitle("Error");
                                                                         alert.setContentText("Maximum 25 Character only"); 
                                                                         alert.showAndWait();      */          
                                        m.setDisable(true);
                                    }
                                    if(c instanceof JFXButton)
                                    {
                                        JFXButton m1 = (JFXButton) c;
                                        m1.setDisable(true);
                                    }
                                    if(c instanceof JFXDatePicker)
                                    {
                                        JFXDatePicker m2 = (JFXDatePicker) c;
                                        m2.setDisable(true);
                                    }
                                          if(c instanceof JFXComboBox)
                                    {
                                        JFXComboBox m3 = (JFXComboBox) c;
                                        m3.setDisable(true);
                                    }
                                    
                                            
                                           
                            }    
                            
                        }catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                        }
    }
    
    public void New()
    {
            
        Node[] d = {
                        
                         btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_employee_name,
                         txt_job_position,
                         txt_title,
                        txt_location,
                        txt_date_start,
                        txt_date_end,
                        txt_time_start,
                        txt_budget_cost,
                        txt_organizer,
                        type_of_training,
                        txt_time_end,
                        txt_emp_id,
                        txt_trainor
             
                };
                        try
                        {
                            for(Node c : d)
                            {
                                   
                                    if(c instanceof JFXTextField)
                                    {
                                        JFXTextField m = (JFXTextField) c;
                                        m.setDisable(false);
                                    }
                                    if(c instanceof JFXButton)
                                    {
                                        JFXButton m1 = (JFXButton) c;
                                        m1.setDisable(false);
                                    }
                                    if(c instanceof JFXDatePicker)
                                    {
                                        JFXDatePicker m2 = (JFXDatePicker) c;
                                        m2.setDisable(false);
                                    }
                                          if(c instanceof JFXComboBox)
                                    {
                                        JFXComboBox m3 = (JFXComboBox) c;
                                        m3.setDisable(false);
                                    }
                            }    
                            
                        }catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                        }
    }
    
     public void Save()
    {
                   HR2_Training_Management tm = new HR2_Training_Management();
                            
                            try
                            {


                                    String[][] cm_data =
                                    {
                                                    {"emp_id" , "sample"},
                                                    {"emp_name" , "sample"},
                                                    {"job_position" , txt_job_position.getText()},
                                                    {"title" , txt_title.getText()},
                                                    {"trainor" , txt_trainor.getText()},
                                                    {"type of training" , type_of_training.getValue().toString()},
                                                    {"location" , txt_location.getText()},
                                                    {"date start" , txt_date_start.getText()},
                                                    {"date end" , txt_date_end.getText()},
                                                    {"time start" , txt_time_start.getText()},
                                                    {"time end" , txt_time_end.getText()},
                                                    {"budget_cost" , txt_budget_cost.getText()},
                                                    {"organizer" , txt_organizer.getText()}

                                    };
                                    
                                 /*          String[][] jp =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"job_position" , txt_job_position.getText()}
                                                    
                                    };
                                           
                                                   String[][] department =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"department" , txt_department.getText()}
                                                    
                                    };*/
                                                            tm.insert(cm_data);
                                                          //    tm.insert(jp);
                                                             //   tm.insert(department);
                                                           JOptionPane.showMessageDialog(null,"saved");
                            }catch(Exception e)
                            {
                                                            JOptionPane.showMessageDialog(null,e.getMessage());
                            }
    }
}
