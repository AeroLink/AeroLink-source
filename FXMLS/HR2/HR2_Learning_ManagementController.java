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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Learning_ManagementController implements Initializable {

    @FXML
    private JFXTextField txt_course_id;
    @FXML
    private JFXTextField txt_course_title;
    @FXML
    private JFXTextField txt_course_description;
    @FXML
    private JFXTextField txt_course_created_by;
    @FXML
    private JFXTextField txt_number_of_questions;
    @FXML
    private TableView<?> tbl_course_data;
    @FXML
    private TableView<?> tbl_questions_data;
    @FXML
    private MenuItem item_add_new_question;
    @FXML
    private TableView<?> tbl_choices_data;
    @FXML
    private MenuItem menu_add_new_course;
    @FXML
    private MenuItem menu_save_new_course;
    @FXML
    private MenuItem menu_edit_course;
    @FXML
    private MenuItem menu_update_course;
    @FXML
    private MenuItem menu_delete_course;
    @FXML
    private MenuItem item_save_new_question;
    @FXML
    private MenuItem item_edit_question;
    @FXML
    private MenuItem item_update_question;
    @FXML
    private MenuItem item_delete_question;
    @FXML
    private MenuItem menu_add_new_choice;
    @FXML
    private MenuItem menu_save_new_choice;
    @FXML
    private MenuItem menu_edit_choice;
    @FXML
    private MenuItem menu_update_choice;
    @FXML
    private MenuItem menu_delete_choice;
    @FXML
    private TableColumn<?, ?> col_course_id_pk;
    @FXML
    private TableColumn<?, ?> col_course_title;
    @FXML
    private TableColumn<?, ?> col_course_description;
    @FXML
    private TableColumn<?, ?> col_created_by;
    @FXML
    private TableColumn<?, ?> col_number_of_questions;
    @FXML
    private TableColumn<?, ?> col_question_id_pk;
    @FXML
    private TableColumn<?, ?> col_question_number;
    @FXML
    private TableColumn<?, ?> col_question;
    @FXML
    private TableColumn<?, ?> col_choice_id_fk;
    @FXML
    private TableColumn<?, ?> col_course_id_fk1;
    @FXML
    private TableColumn<?, ?> col_choice_id_pk;
    @FXML
    private TableColumn<?, ?> col_choices;
    @FXML
    private TableColumn<?, ?> col_question_fk;
    @FXML
    private TableColumn<?, ?> col_couse_id_fk;
    @FXML
    private TableColumn<?, ?> col_is_checked;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Database d = Database.getInstance();
        d.DB_INIT(new MySql());
        d.startConnection();

        DisableComponents();
        
        //for course section
        menu_add_new_course.setOnAction(e -> New());
        
        item_add_new_question.setOnAction(e -> Open_LM_Questions());
        
        DisableComponents();
        
    }
    
   
    public void Open_LM_Questions() 
    {
        
                try
                {
                    Parent p = FXMLLoader.load(getClass().getResource("HR2_LM_Questions.fxml"));
                    
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                    stage.initStyle(StageStyle.UNDECORATED);
                    
                } catch (IOException e) {
                  
                    System.out.println(e.getMessage());
                }
    }

    public void DisableComponents() {

        Node[] d = {       
                
                        txt_course_id,
                        txt_course_title,
                        txt_course_description,
                        txt_course_created_by
            
                };
                        try
                        {
                            for(Node c : d)
                            {
                                    if(c instanceof JFXTextField)
                                    {
                                                JFXTextField m = (JFXTextField) c;
                                            /*        validations v = new validations();
                                                         v.maximumChars(m, 25);
                                                         Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                         alert.initStyle(StageStyle.UNDECORATED);
                                                                         alert.setTitle("Error");
                                                                         alert.setContentText("Maximum 25 Character only"); 
                                                                         alert.showAndWait();     */        
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

                txt_course_id,
                txt_course_title,
                txt_course_description,
                txt_course_created_by
                
                
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
    
   /* public void Save()
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
    
     */

 }