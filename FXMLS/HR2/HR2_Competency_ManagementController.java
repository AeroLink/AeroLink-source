/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import FXMLS.HR2.ClassFiles.HR2_Competency_ManagementClass;
import FXMLS.HR2.ClassFiles.HR2_JobsClass;
import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import Model.HR2_Competency_Management;
import Model.HR2_Jobs;
import Model.HR2_Training_Management;
import Synapse.Model;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagementController implements Initializable {

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
    private JFXTextField txt_skill_id;
    @FXML
    private JFXTextField txt_skill;
    @FXML
    private JFXTextField txt_skill_description;
    @FXML
    private JFXTextField txt_job_description;
    @FXML
    private JFXTextField txt_job_ID;
    @FXML
    private JFXTextField txt_job_title;
    
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_ID;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_Skill;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_Skill_Description;
 
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_id;
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_title;
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job_description;
    @FXML
    private TableView<HR2_Competency_ManagementClass> tbl_Skills;
    @FXML
    private TableView<HR2_JobsClass> tbl_Job_Skillsets;
    @FXML
    private JFXTextField txt_search_skills;
    
    
    @FXML
    private TableView<HR2_Competency_ManagementClass> tbl_Skillsets_related_to_Jobs;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_job_id1;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_id1;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill1;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skill_description1;
    @FXML
    private JFXTextField txt_Search_Jobs;

    
    HR2_Competency_Management hr2hmc;
    HR2_Jobs j;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
                btn_new.setOnMouseClicked(e -> New());

                 btn_save.setOnMouseClicked(e -> Save());

                DisableComponents();

                DisplayData();
                
                loadData();
                
               // btn_edit.setOnAction(e -> sample_button());
                
    }    
     /* public void sample_button()
    {
       
        try
        {
           FXMLLoader l = new FXMLLoader(getClass().getResource("sample.fxml"));

                          Parent p = (Parent) l.load();
                          
                          Stage stage = new Stage();
                          stage.setScene(new Scene(p));
                          stage.show();
                            }
                            catch(IOException  e)
                            {
                                System.out.println(e.getMessage());
                            } 
    }*/
         public void DisableComponents()
                 
                {


                            Node[] d = {

                                      btn_edit,
                                        btn_save,
                                        btn_update,
                                        btn_delete,
                                        txt_skill_id,
                                        txt_skill,
                                        txt_skill_description,
                                        txt_job_ID,
                                        txt_job_title,
                                        txt_job_description
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
                                            txt_skill_id,
                                            txt_skill,
                                            txt_skill_description


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
    public void SearchSkills()
    {
           hr2hmc = new HR2_Competency_Management();
           
           
                        try

                        {
                                 
                                  List listSkills = hr2hmc.where(new Object[][]{
                                      {"job_id", "like", "%" + txt_Search_Jobs.getText() +  "%"}
                                  }).get();
                                  

                                 /* 
                                  
                                        {"job_id", "like", " (SELECT id from tbl_jobs WHERE id = 1)"}
                                  

                                    */
                                 SearchJobs(txt_Search_Jobs.getText());
                                  SearchRelatedSkills(listSkills);


                        }catch(Exception e)
                        {
                            System.out.println(e);
                        }
           
   }

     
     //Initializing data
         private void DisplayData()
                 
            {           
                        //Skills
                      //  col_Job_ID.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Job_ID);
                        col_skill_ID.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_ID);
                        col_Skill.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill);
                        col_Skill_Description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_Description);


                               //Job SkillSets
                        col_job_id.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Job_ID);
                        col_job_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Title);
                        col_job_description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Description);

                        col_job_id1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Job_ID);
                        col_skill_id1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_ID);
                        col_skill1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill);
                        col_skill_description1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill_Description);

            }
         
         //Job Skillsets
         private void SearchJobs(String jobs)
                 
            {
                
                tbl_Job_Skillsets.getItems().clear();
                

                                  
                HR2_Jobs jobxs = new HR2_Jobs();
                List listjobs = jobxs.where(new Object[][]{
                      {"job_id", "like", "%" + jobs +  "%"}
                }).get();
                       ObservableList<HR2_JobsClass> hr2jc1 = FXCollections.observableArrayList();

                          for(Object d : listjobs)
                              
                                {
                                    HashMap hm1 = (HashMap) d;
                                    //RS
                                    hm1.get("job_id");
                                    hm1.get("title");
                                    hm1.get("description");


                                            hr2jc1.add(
                                                 new HR2_JobsClass(
                                                         String.valueOf(hm1.get("job_id")),
                                                          String.valueOf(hm1.get("title")),
                                                          String.valueOf(hm1.get("description"))


                                                 ));  

                                }

                                            tbl_Job_Skillsets.setItems(hr2jc1);
            }
         
         private void SearchRelatedSkills(List skills)
         {
                tbl_Skillsets_related_to_Jobs.getItems().clear();
             
                        ObservableList<HR2_Competency_ManagementClass> hr2jc = FXCollections.observableArrayList();
                       
                            try
                            {
                                for(Object d : skills)

                                  {
                                          HashMap hm1 = (HashMap) d;
                                          //RS
                                          hm1.get("job_id");
                                          hm1.get("skill_id");
                                          hm1.get("skill");
                                          hm1.get("skill_description");


                                                  hr2jc.add(
                                                       new HR2_Competency_ManagementClass(
                                                               String.valueOf(hm1.get("job_id")),
                                                               String.valueOf(hm1.get("skill_id")),
                                                                String.valueOf(hm1.get("skill")),
                                                               String.valueOf(hm1.get("skill_description"))
                                                       ));  

                                  }
                            }catch(Exception e)
                            {
                                System.out.println(e);
                            }

                                               tbl_Skillsets_related_to_Jobs.setItems(hr2jc);
         }
   
         
         //Display Data
        private void loadData()
            {
                        try
                        {
                                 HR2_Competency_Management tm = new HR2_Competency_Management();

                                        ObservableList<HR2_Competency_ManagementClass> hmc = FXCollections.observableArrayList();
                                            List b = tm.get();

                                                for(Object d : b)
                                                {
                                                    HashMap hm = (HashMap) d;
                                                    //RS
                                                    hm.get("job_id");
                                                    hm.get("skill_id");
                                                    hm.get("skill");
                                                    hm.get("skill_description");

                                                            hmc.add(
                                                                 new HR2_Competency_ManagementClass(
                                                                          String.valueOf(hm.get("job_id")),
                                                                         String.valueOf(hm.get("skill_id")),
                                                                          String.valueOf(hm.get("skill")),
                                                                          String.valueOf(hm.get("skill_description"))


                                                                 ));   
                                                }
                                                                 tbl_Skills.setItems(hmc);




                                   //Job Skillsets                              
                                  HR2_Jobs jobs = new HR2_Jobs ();

                                        ObservableList<HR2_JobsClass> hr2jc = FXCollections.observableArrayList();
                                            List c = jobs .get();

                                                for(Object d : c)
                                                {
                                                    HashMap hm1 = (HashMap) d;
                                                    //RS
                                                    hm1.get("job_id");
                                                    hm1.get("title");
                                                    hm1.get("description");


                                                            hr2jc.add(
                                                                 new HR2_JobsClass(
                                                                         String.valueOf(hm1.get("job_id")),
                                                                          String.valueOf(hm1.get("title")),
                                                                          String.valueOf(hm1.get("description"))


                                                                 ));  

                                                }

                                                            tbl_Job_Skillsets.setItems(hr2jc);
                                                            tbl_Skillsets_related_to_Jobs.setItems(hmc);

                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
            }

       
       
     public void Save()
    {
                   HR2_Competency_Management tm = new HR2_Competency_Management();
                            
                            try
                            {


                                    String[][] cm_data =
                                    {
                     
                                                    {"skill_id" , txt_skill_id.getText()},
                                                    {"skill" , txt_skill.getText()},
                                                    {"skill_description" , txt_skill_description.getText()},
                                              
                                    };
                                   
                           
                                                            tm.insert(cm_data);
                                        
                                                           Alert saved = new Alert(Alert.AlertType.INFORMATION);
                                                                  saved.setContentText("Saved");
                                                                  saved.showAndWait();
                                                                  
                                                                  
                            }catch(Exception e)
                            {
                                                               JOptionPane.showMessageDialog(null,e);
                            }
    }
     
}
