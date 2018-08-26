/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import Model.HR2_Competency_Management;
import Model.HR2_Learning_Management;
import Synapse.Database;
import Synapse.DB.MySql;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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
public class HR2_Learning_ManagementController implements Initializable {

    @FXML
    private JFXTextField txt_emp_id;
    @FXML
    private JFXTextField txt_emp_name;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private TableView<?> cm_data;
    @FXML
    private JFXTextField txt_course_title;
    @FXML
    private JFXTextField txt_coordinator;
    @FXML
    private JFXTextField txt_duration;
    @FXML
    private JFXTextField txt_status;
    @FXML
    private JFXTextField txt_course_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                   
            Database d = Database.getInstance();
            d.DB_INIT(new MySql());
            d.startConnection();
            
        DisableComponents();
        
        btn_new.setOnAction(e -> New());
    }    
    
    
     public void DisableComponents()
    {
    
                    
            Node[] d = {
                        
                
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_emp_id,
                        txt_emp_name,
                        txt_course_title,
                        txt_course_description,
                        txt_coordinator,
                        txt_duration,
                        txt_status
            
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
    
    //TEMPORARY CODE //WILL FIX THE REDUNDANCY
    public void New()
    {
            
        Node[] d = {
                        
                    
                 
                        txt_emp_id,
                        txt_emp_name,
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_emp_id,
                        txt_emp_name,
                        txt_course_title,
                        txt_course_description,
                        txt_coordinator,
                        txt_duration,
                        txt_status
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
                   HR2_Learning_Management lm = new HR2_Learning_Management();

                            try
                            {


                                    String[][] lm1 =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"emp_name" , txt_emp_name.getText()},
                                                      {"course_title" , txt_course_title.getText()},
                                                    {"course_description" , txt_course_description.getText()},
                                                      {"coordinator" , txt_coordinator.getText()},
                                                    {"duration" , txt_duration.getText()},
                                                     {"status" , txt_status.getText()}

                                    };
                                                            lm.insert(lm1);
                                                           JOptionPane.showMessageDialog(null,"saved");
                            }catch(Exception e)
                            {
                                                            JOptionPane.showMessageDialog(null,e.getMessage());
                            }
    }
    
    
}
