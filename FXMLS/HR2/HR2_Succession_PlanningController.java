/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import Model.HR2_Competency_Management;
import Model.HR2_Succession_Planning;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Succession_PlanningController implements Initializable {

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
    private JFXTextField txt_search;
    @FXML
    private JFXTextField emp_id;
    @FXML
    private JFXDatePicker date_of_retirement;
    @FXML
    private JFXComboBox<?> combobox_successor;
    @FXML
    private JFXTextField txt_successor_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                   
            Database d = Database.getInstance();
            d.DB_INIT(new MySql());
            d.startConnection();
            
        DisableComponents();
        btn_new.setOnMouseClicked(e -> New());
    }    
    
     public void DisableComponents()
    {
    
                    
            Node[] d = {
                        
                      
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        emp_id,
                        combobox_successor,
                        date_of_retirement,
                        txt_successor_name
                        
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
                        
                         btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        emp_id,
                        combobox_successor,
                        date_of_retirement,
                        txt_successor_name
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
                   HR2_Succession_Planning tm = new HR2_Succession_Planning();
                            
                            try
                            {


                                    String[][] sp =
                                    {
                                                    {"emp_id" , emp_id.getText()},
                                                    {"emp_name" , date_of_retirement.getValue().toString()}
                                                

                                    };
                                    
                                              String[][] successors =
                                    {
                                                    {"emp_id" , emp_id.getText()},
                                                    {"emp_name" , date_of_retirement.getValue().toString()}
                                                

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
                                                            tm.insert(successors);
                                                            tm.insert(sp);
                                                          //    tm.insert(jp);
                                                             //   tm.insert(department);
                                                           JOptionPane.showMessageDialog(null,"saved");
                            }catch(Exception e)
                            {
                                                            JOptionPane.showMessageDialog(null,e.getMessage());
                            }
    }
    
}
