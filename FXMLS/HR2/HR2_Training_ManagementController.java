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
import javafx.scene.control.Alert;
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
    private JFXButton btn_new;
    private JFXButton btn_edit;
    @FXML
    private JFXTextField txt_training_id;
    @FXML
    private JFXTextField txt_job_position;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextField txt_training_description;
    @FXML
    private JFXTextField txt_trainor;
    @FXML
    private JFXTextField txt_start_time;
    @FXML
    private JFXTextField txt_end_time;
    @FXML
    private JFXDatePicker txt_start_date;
    @FXML
    private JFXDatePicker txt_end_date;
    @FXML
    private JFXTextField txt_location;
    @FXML
    private JFXTextField txt_vehicle;
    @FXML
    private JFXTextField txt_budget_cost;
    @FXML
    private JFXComboBox<?> txt_type_of_training;
    @FXML
    private JFXTextField txt_search_trainings;
    @FXML
    private TableView<?> training_management_data;
    @FXML
    private TableColumn<?, ?> col_training_ID;
    @FXML
    private TableColumn<?, ?> col_job_position;
    @FXML
    private TableColumn<?, ?> col_training_title;
    @FXML
    private TableColumn<?, ?> col_training_description;
    @FXML
    private TableColumn<?, ?> col_trainor;
    @FXML
    private TableColumn<?, ?> col_start_date;
    @FXML
    private TableColumn<?, ?> col_end_date;
    @FXML
    private TableColumn<?, ?> col_start_time;
    @FXML
    private TableColumn<?, ?> col_end_time;
    @FXML
    private TableColumn<?, ?> col_type_of_training;
    @FXML
    private TableColumn<?, ?> col_location;
    @FXML
    private TableColumn<?, ?> col_vehicle;
    @FXML
    private TableColumn<?, ?> col_budget_cost;
    @FXML
    private TableColumn<?, ?> col_alter;
   

  //  ObservableList<String> sdl = FXCollections.observableArrayList("Internal", "External");

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     //   type_of_training.setItems(sdl);
     //   type_of_training.setPromptText("Choose Level");

        btn_new.setOnMouseClicked(e -> New());
        DisableComponents();

        DisplayData();

        loadData();

        //   btn_save.setOnAction(e -> Save());
    }

    private void DisplayData() {
       
     
    }

    private void loadData() {

        HR2_Training_Management tm = new HR2_Training_Management();

        ObservableList<Training_ManagementClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
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
                    ));
        }
    

    }

    public void DisableComponents() {

        Node[] d = {
            btn_edit,
            btn_save,
       
        };
        try {
            for (Node c : d) {
                if (c instanceof JFXTextField) {
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
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(true);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(true);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(true);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void New() {

        Node[] d = {
            btn_edit,
            btn_save,
            

        };
        
        try {
            for (Node c : d) {

                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(false);
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(false);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(false);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Save() {
        HR2_Training_Management tm = new HR2_Training_Management();

        try {

            String[][] cm_data
                    = {
                     

                    };

            tm.insert(cm_data);

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
