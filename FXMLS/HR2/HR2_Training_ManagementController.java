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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_ManagementController implements Initializable {

   
    @FXML
    private JFXButton btn_save;
    @FXML
    private TableView<Training_ManagementClass> list_of_trainees;
  
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
    private TableColumn<Training_ManagementClass, String> tbl_type_of_training;
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
    @FXML
    private TableColumn<Training_ManagementClass, String> emp_id;
    @FXML
    private TableColumn<Training_ManagementClass, String> emp_name;
    @FXML
    private TableColumn<Training_ManagementClass, String> job_position;
    @FXML
    private TableColumn<Training_ManagementClass, String> title;
    @FXML
    private TableColumn<Training_ManagementClass, String> trainor;
    @FXML
    private TableColumn<Training_ManagementClass, String> location;
    @FXML
    private TableColumn<Training_ManagementClass, String> date_start;
    @FXML
    private TableColumn<Training_ManagementClass, String> date_end;
    @FXML
    private TableColumn<Training_ManagementClass, String> time_start;
    @FXML
    private TableColumn<Training_ManagementClass, String> time_end;
    @FXML
    private TableColumn<Training_ManagementClass, String> budget_cost;
    @FXML
    private TableColumn<Training_ManagementClass, String> organizer;
    @FXML

    private JFXComboBox<String> type_of_training;
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
             type_of_training.setItems(sdl);
                 type_of_training.setPromptText("Choose Level");
                 
            btn_new.setOnMouseClicked(e -> New());
            DisableComponents();
            
            DisplayData();
            
            loadData();
              
               
         //   btn_save.setOnAction(e -> Save());
    }    
    private void DisplayData()
    {
        emp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
           emp_name.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
              job_position.setCellValueFactory(new PropertyValueFactory<>("job_position"));
                 title.setCellValueFactory(new PropertyValueFactory<>("title"));
                    trainor.setCellValueFactory(new PropertyValueFactory<>("trainor"));
                    tbl_type_of_training.setCellValueFactory(new PropertyValueFactory<>("tbl_type_of_training"));
                       location.setCellValueFactory(new PropertyValueFactory<>("location"));
                          date_start.setCellValueFactory(new PropertyValueFactory<>("date_start"));
                             date_end.setCellValueFactory(new PropertyValueFactory<>("date_end"));
                                time_start.setCellValueFactory(new PropertyValueFactory<>("time_start"));
                                   time_end.setCellValueFactory(new PropertyValueFactory<>("time_end"));
                                      budget_cost.setCellValueFactory(new PropertyValueFactory<>("budget_cost"));
                                         organizer.setCellValueFactory(new PropertyValueFactory<>("organizer"));
                                        
    }
    
    private void loadData()
    {
           
        HR2_Training_Management tm = new HR2_Training_Management();
        
        ObservableList<Training_ManagementClass> dv = FXCollections.observableArrayList();
            List b = tm.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;
                
                hm.get("emp_id");
                hm.get("emp_name");
                hm.get("job_position");
                hm.get("title");
                hm.get("trainor");
                hm.get("tbl_type_of_training");
                hm.get("location");
                hm.get("date_start");
                hm.get("date_end");
                hm.get("time_start");
                hm.get("time_end");
                hm.get("budget_cost");
                hm.get("organizer");
                
               dv.add(
                    new Training_ManagementClass(
                            String.valueOf(hm.get("emp_id")),
                            String.valueOf(hm.get("emp_name")),
                            String.valueOf(hm.get("job_position")),
                            String.valueOf(hm.get("title")),
                            String.valueOf(hm.get("trainor")),
                            String.valueOf(hm.get("tbl_type_of_training")),
                            String.valueOf(hm.get("location")),
                            String.valueOf(hm.get("date_start")),
                            String.valueOf(hm.get("date_end")),
                            String.valueOf(hm.get("time_start")),
                            String.valueOf(hm.get("time_end")),
                            String.valueOf(hm.get("budget_cost")),
                            String.valueOf(hm.get("organizer"))

                    ) );   
            }
            list_of_trainees.setItems(dv);
                
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
    
    @FXML
     public void Save()
    {
                   HR2_Training_Management tm = new HR2_Training_Management();
                            
                            try
                            {


                                    String[][] cm_data =
                                    {
                                                    {"emp_id" , "1"},
                                                    {"emp_name" , "sample"},
                                                    {"job_position" , txt_job_position.getText()},
                                                    {"title" , txt_title.getText()},
                                                    {"trainor" , txt_trainor.getText()},
                                                    {"type_of_training" , type_of_training.getValue().toString()},
                                                    {"location" , txt_location.getText()},
                                                    {"date_start" , txt_date_start.getText()},
                                                    {"date_end" , txt_date_end.getText()},
                                                    {"time_start" , txt_time_start.getText()},
                                                    {"time_end" , txt_time_end.getText()},
                                                    {"budget_cost" , txt_budget_cost.getText()},
                                                    {"organizer" , txt_organizer.getText()}

                                    };
                                   
                           
                                                            tm.insert(cm_data);
                                        
                                                           JOptionPane.showMessageDialog(null,"Saved");
                            }catch(Exception e)
                            {
                                                               JOptionPane.showMessageDialog(null,e);
                            }
    }
}
