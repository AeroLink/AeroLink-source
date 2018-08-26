/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import Model.HR2_Competency_Management;
import Synapse.Database;
import Synapse.DB.MySql;
import static Synapse.Session.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_browse_image;
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
    private JFXTextField txt_emp_id;
    @FXML
    private JFXTextField txt_emp_name;
    @FXML
    private JFXTextField txt_age;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_contact;
    @FXML
    private JFXDatePicker date_of_birth;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_birthplace;
    @FXML
    private JFXTextField txt_status;
    @FXML
    private JFXTextField txt_nationality;
    @FXML
    private JFXComboBox<String> school_level;
    @FXML
    private JFXTextField txt_guardian;
    @FXML
    private TableView<?> cm_data;
    @FXML
    private JFXTextField txt_job_position;
    @FXML
    private JFXTextField txt_department;


   
    //Choose Level
        ObservableList<String> sl = FXCollections.observableArrayList("Tertiary" , "Secondary" , "Primary");

   

             @Override
            public void initialize(URL url, ResourceBundle rb) {
                  
                   //combobox
                   
              
                  school_level.setItems(sl);
                  school_level.setPromptText("Choose Level");
                  DisableComponents();

                   //Buttons
                   btn_new.setOnMouseClicked(e -> New());
                   btn_save.setOnMouseClicked(e -> Save());
                  /* try
                   {
                  FXMLS.HR2.Controllers.Components.DisableComponents(this.d);
                   }
                   catch(Exception e)
                   {
                       JOptionPane.showMessageDialog(null, e);
                       e.printStackTrace();
                   }*/
            }    
   
    
                  /*       public EventHandler evt = new EventHandler<KeyEvent>()
                            {


                                     @Override
                                     public void handle(KeyEvent event) {
                                        validations n = new validations();
                                                 JFXTextField jft = (JFXTextField) event.getSource();

                                                     if(!n.maximumChars(jft, 25))
                                                     {
                                                                       Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                                          alert.initStyle(StageStyle.UNDECORATED);
                                                                                          alert.setTitle("Error");
                                                                                          alert.setContentText("Maximum 25 Character only"); 
                                                                                          alert.showAndWait();
                                                     }
                                     }*/
                    
    public void DisableComponents()
    {
    
                    
            Node[] d = {
                        
                        btn_browse_image,
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_emp_id,
                        txt_emp_name,
                        txt_age,
                        txt_address,
                        txt_contact,
                        date_of_birth,
                        txt_email,
                        txt_birthplace,
                        txt_status,
                        txt_nationality,
                        school_level,
                        txt_guardian,
                        txt_job_position,
                        txt_department,
                        cm_data
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
                        
                        btn_browse_image,
                        btn_edit,
                        btn_save,
                        btn_update,
                        btn_delete,
                        txt_emp_id,
                        txt_emp_name,
                        txt_age,
                        txt_address,
                        txt_contact,
                        date_of_birth,
                        txt_email,
                        txt_birthplace,
                        txt_status,
                        txt_nationality,
                        school_level,
                        txt_guardian,
                        txt_job_position,
                        txt_department,
                        cm_data
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
                   HR2_Competency_Management tm = new HR2_Competency_Management();
                            
                            try
                            {


                                    String[][] cm_data =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"emp_name" , txt_emp_name.getText()},
                                                    {"age" , txt_age.getText()},
                                                    {"date_of_birth" , date_of_birth.getValue().toString()},
                                                    {"address" , txt_address.getText()},
                                                    {"contact_number" , txt_contact.getText()},
                                                    {"email" , txt_email.getText()},
                                                    {"birthplace" , txt_birthplace.getText()},
                                                    {"status" , txt_status.getText()},
                                                    {"guardian" , txt_guardian.getText()},
                                                    {"nationality" , txt_nationality.getText()},
                                                    {"school_level" , school_level.getValue().toString()}

                                    };
                                    
                                           String[][] jp =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"job_position" , txt_job_position.getText()}
                                                    
                                    };
                                           
                                                   String[][] department =
                                    {
                                                    {"emp_id" , txt_emp_id.getText()},
                                                    {"department" , txt_department.getText()}
                                                    
                                    };
                                                            tm.insert(cm_data);
                                                              tm.insert(jp);
                                                                tm.insert(department);
                                                           JOptionPane.showMessageDialog(null,"saved");
                            }catch(Exception e)
                            {
                                                            JOptionPane.showMessageDialog(null,e.getMessage());
                            }
    }
    
}

